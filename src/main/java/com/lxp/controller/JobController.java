package com.lxp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lxp.entity.JobCityEntity;
import com.lxp.entity.JobEntity;
import com.lxp.factory.ServiceFactory;
import com.lxp.json.CommonJson;
import com.lxp.service.IJobService;

@RestController
@RequestMapping("/job")
public class JobController {
	@Autowired
	private IJobService jobService;

	@RequestMapping(method = RequestMethod.GET)
	public String getJob() {
		System.out.println("测试方法命中此方法.....");
		CommonJson cj=new CommonJson();
			if (null != jobService.findBycity()) {
				List cityList = jobService.findBycity();	
				cj.setCode("200");
				cj.setMessage("查找成功");
				cj.setData(cityList);
				System.out.println("result--->"+JSON.toJSONString(cj));
				return JSON.toJSONString(cj);
				} else {
				System.out.println("请求失败.....");
				cj.setCode("500");
				cj.setMessage("请求失败");
				return JSON.toJSONString(cj);
			}
	
	}

}
