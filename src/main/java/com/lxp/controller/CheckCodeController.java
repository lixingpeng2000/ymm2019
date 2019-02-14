package com.lxp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.lxp.json.CommonJson;
@RestController
@RequestMapping("/checkCode")
public class CheckCodeController {	
	private static final Logger logger = LoggerFactory.getLogger(CheckCodeController.class);
	@RequestMapping(method = RequestMethod.POST)//设置权限
	public String checkCode(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
	//public String checkCode(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        System.out.println("CheckCode的checkCode方法...");
        System.out.println("checkCode接口的post方法....");
		BufferedReader br = new BufferedReader(
				new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer("");
		String temp;
		while ((temp = br.readLine()) != null) {
			sb.append(temp);
		}
		br.close();
		String params = sb.toString();
		System.out.println(params);
		String newStr = params.substring(1, params.length() - 1);
		System.out.println( "获取到的参数--->" + newStr);
		logger.info("param:"+newStr);
		String data[] = new String[2];
		String[] strdata = new String[2];	
		strdata = newStr.split(":");
		String code = strdata[1].substring(1, strdata[1].length()-1);
		System.out.println("code--->"+code);
        // 验证验证码
        String sessionCode = request.getSession().getAttribute("code").toString();
        System.out.println("sessionCode--->"+sessionCode);
        logger.info("获取到的验证码为:"+sessionCode);
        if (code != null && !"".equals(code) && sessionCode != null && !"".equals(sessionCode)) {
            if (code.equalsIgnoreCase(sessionCode)) {
            	System.out.println("到此分支1.....");
            	CommonJson cj=new CommonJson();
            	cj.setCode("200");
            	cj.setMessage("验证通过");
            	logger.info("result json为:"+JSON.toJSONString(cj));
            	return JSON.toJSONString(cj);
            } else {
            	System.out.println("到此分支2.....");
            	CommonJson cj=new CommonJson();
            	cj.setCode("500");
            	cj.setMessage("验证失败");
            	logger.info("result json为:"+JSON.toJSONString(cj));
            	return JSON.toJSONString(cj);
            }
        } else {
        	System.out.println("到此分支3.....");
        	CommonJson cj=new CommonJson();
        	cj.setCode("501");
        	cj.setMessage("验证失败");
        	logger.info("result json为:"+JSON.toJSONString(cj));
        	return JSON.toJSONString(cj);
        }		
	}

}
