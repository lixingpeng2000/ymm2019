package com.lxp.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lxp.entity.UserRoleEntity;
import com.qiniu.util.Auth;

@RestController
@RequestMapping("/qiniuUpToken")
public class QiniuTokenController {

	@RequestMapping(method = RequestMethod.POST)
	public void QiniuUpToken(HttpServletRequest req, HttpServletResponse response)
			throws Exception {
		System.out.println("命中QiniuUpToken的post方法....");
		// 生成实际路径名
		BufferedReader br = new BufferedReader(
				new InputStreamReader((ServletInputStream) req.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer("");
		String temp;
		while ((temp = br.readLine()) != null) {
			sb.append(temp);
		}
		br.close();
		String params = sb.toString();
		System.out.println("myData-->" + params);
		String subPara = params.substring(1,params.length() - 1);
		System.out.println(subPara);
		String paramArray[]=subPara.split(":");
		String suffix = paramArray[1].substring(1, paramArray[1].length()-1);
		System.out.println("suffix--->"+suffix);
		PrintWriter out = null;
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			System.out.println("进入到此处...11");
			String accessKey = "MQX-8A9XHJzLSJSe395SCP7Vi7KPyhugqIV99EN9"; // 访问秘钥
			String secretKey = "vARTLG_RfqPS02NEunFNH1rC5kmsorVYR517TteX"; // 授权秘钥
			String bucket = "lixingpeng"; // 存储空间名称
			Auth auth = Auth.create(accessKey, secretKey); // 验证七牛云身份是否通过
			System.out.println("进入到此处...222,s说明获取到了auth...");
			out = response.getWriter();
			// 生成凭证
			String upToken = auth.uploadToken(bucket);
			System.out.println("进入到此处...222,s说明获取到了token成功...");
			System.out.println("upToken--->..." + upToken);
			result.put("token", upToken);
			// 是否可以上传的图片格式
			/*
			 * boolean flag = false; String [] imgTypes= new
			 * String[]{"jpg","jpeg","bmp","gif","png"}; for(String fileSuffix
			 * :imgTypes) { if(suffix.substring(suffix.lastIndexOf(".") +
			 * 1).equalsIgnoreCase(fileSuffix)) { flag = true; break; } }
			 * if(!flag) { throw new Exception("图片：" + suffix + " 上传格式不对！"); }
			 */

		
			String randomFileName = UUID.randomUUID().toString() + suffix;
			result.put("imgUrl", randomFileName);
			result.put("success", 1);
			out.write(JSON.toJSONString(result));
		} catch (Exception e) {
			System.out.println("进入到此处...245672278622,获取token失败...");
			result.put("success", 0);
			result.put("message", "获取凭证失败，" + e.getMessage());
			out.write(JSON.toJSONString(result));
		}
	}

}
