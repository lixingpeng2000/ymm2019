package com.lxp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lxp.entity.UserEntity;

@RestController
@RequestMapping("/userLogininfo")
public class UserLogininfoController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getUserLogininfo(HttpServletRequest req){
		HttpSession session=req.getSession();
		if(null != session.getAttribute("username")){
			String usernameSes=session.getAttribute("username").toString();
			String pwdSes=session.getAttribute("pwd").toString();
			System.out.println(usernameSes);
			System.out.println(pwdSes);
			return "{\"resultCode\":\"200\""+",\"username\":\""+usernameSes+"\",\"pwd\":\""+pwdSes+"\"}";
		}else {
			return "{\"resultCode\":\"500\"}";
		}
		
	}

}
