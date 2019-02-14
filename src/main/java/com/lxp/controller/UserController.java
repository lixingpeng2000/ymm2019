package com.lxp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lxp.entity.UserEntity;
import com.lxp.factory.ServiceFactory;
import com.lxp.json.CommonJson;
import com.lxp.service.IUserService;
import com.lxp.util.JWTUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(method = RequestMethod.POST)
	public String postUser(HttpServletRequest req) throws UnsupportedEncodingException, IOException {
		System.out.println("user接口的post方法....");
		BufferedReader br = new BufferedReader(
				new InputStreamReader((ServletInputStream) req.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer("");
		String temp;
		while ((temp = br.readLine()) != null) {
			sb.append(temp);
		}
		br.close();
		String params = sb.toString();
		System.out.println(params);
		String newStr = params.substring(1, params.length() - 1);
		String[] sourceStrArray = newStr.split(",");
		String data[] = new String[2];
		String[] strdata = new String[2];
		for (int i = 0; i < 2; i++) {
			strdata = sourceStrArray[i].split(":");
			data[i] = strdata[1];
		}
		String username = data[0];
		String pwd = data[1];
		System.out.println("username:" + username + ",pwd:" + pwd);
		UserEntity user = new UserEntity();
		user.setUsername(username.substring(1, username.length() - 1));
		user.setPwd(pwd.substring(1, pwd.length() - 1));
		user.setId(UUID.randomUUID().toString());
		logger.info("参数为：" + user);
		CommonJson cj=new CommonJson();
		if(userService.findByuserName(user.getUsername())){
			cj.setCode("401");
			cj.setMessage("用户名存在");
			return JSON.toJSONString(cj);
		}
		try {
			// if (ServiceFactory.getIUserServiceIstance().insert(user)) {
			if (userService.insert(user)) {
				cj.setCode("200");
				cj.setMessage("注册成功");
				return JSON.toJSONString(cj);
			} else {
				cj.setCode("500");
				cj.setMessage("注册失败");
				return JSON.toJSONString(cj);
			}
		} catch (SQLException e) {
			logger.error("exception:" + e);

		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getUser(UserEntity user, HttpServletRequest req) {
		System.out.println("命中user接口的get方法...");
		String username = user.getUsername();
		String pwd = user.getPwd();
		System.out.println("获取到的用户名和密码:" + username + ":" + pwd);
		logger.info("入参为:" + user);
		try {
			System.out.println(userService.find(user));
			if (null != userService.find(user)) {
				UserEntity us = userService.find(user);
				// 生成token
				System.out.println("获取token的方法中来了....");
				String token = JWTUtil.sign(username);
				System.out.println(token);
				HttpSession session = req.getSession();
				session.setAttribute("username", username);
				session.setAttribute("pwd", pwd);
				String usernameSes = session.getAttribute("username").toString();
				System.out.println(usernameSes);
				System.out.println("{\"resultCode\":\"200\"" + ",\"username\":\"" + us.getUsername() + "\",\"pwd\":\""
						+ us.getPwd() + "\",\"token\":\"" + token + "\"}");
				// return
				// "{\"resultCode\":\"200\""+",\"username\":\""+us.getUsername()+"\",\"pwd\":\""+us.getPwd()+"\"}";
				logger.info("result结果为:" + "{\"resultCode\":\"200\"" + ",\"username\":\"" + us.getUsername()
						+ "\",\"pwd\":\"" + us.getPwd() + "\",\"token\":\"" + token + "\"}");
				return "{\"resultCode\":\"200\"" + ",\"username\":\"" + us.getUsername() + "\",\"pwd\":\"" + us.getPwd()
						+ "\",\"token\":\"" + token + "\"}";
			} else {
				return "{\"resultCode\":\"500\"}";
			}
		} catch (SQLException e) {
			logger.error("exception:" + e);
		}
		return null;

	}

	@RequestMapping(method = RequestMethod.DELETE)
	public String doDelete(@RequestParam("username") String username, HttpServletRequest req) {
		System.out.println("命中user的dodelete方法....");
		System.out.println(username);
		logger.info("入参:" + username);
		HttpSession session = req.getSession();
		session.removeAttribute("username");
		return "{\"resultCode\":\"200\"}";

	}
}
