package com.lxp.controller;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lxp.json.CommonJson;
import com.lxp.util.CodeUtil;
@RestController
@RequestMapping("/getCode")
public class CodeController {
	private static final Logger logger = LoggerFactory.getLogger(CodeController.class);
	@RequestMapping(method = RequestMethod.GET)//设置权限
	public String getCode(HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException, IOException {
		System.out.println("生成uuid..." + UUID.randomUUID().toString());
		// 调用工具类生成的验证码和验证码图片
		System.out.println("命中CodeController的getCode方法....");
        String code = CodeUtil.generateCodeAndPic();
        System.out.println("获取到的code---->"+code);
        // 将四位数字的验证码保存到Session中。
        HttpSession session = req.getSession();
        session.setAttribute("code", code);      
        CommonJson cj= new CommonJson();
        cj.setCode("200");
        cj.setMessage("验证码获取成功");
        cj.setData(code);
        logger.info("result:"+cj);
		return JSON.toJSONString(cj);
		
	}

}
