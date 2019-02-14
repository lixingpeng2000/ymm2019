package com.lxp.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lxp.factory.ServiceFactory;
import com.lxp.json.CommonJson;
import com.lxp.service.IJobService;

@RestController
@RequestMapping("/jobDept")
public class JobDeptController {
	@Autowired
	private IJobService jobService;
	
	private static final Logger logger = LoggerFactory.getLogger(JobDeptController.class);
	@RequestMapping(method = RequestMethod.GET)
	public String getJobDept(){
		System.out.println("命中jobDept的get方法...");
		logger.info("命中jobDept的get方法...");
		CommonJson cj=new CommonJson();
			if (null != jobService.findJobDeptList()) {
				List cityList = jobService.findJobDeptList();
				cj.setCode("200");
				cj.setMessage("获取成功");
				cj.setData(cityList);
				return JSON.toJSONString(cj);

			} else {
				cj.setCode("500");
				cj.setMessage("获取失败");
				return JSON.toJSONString(cj);
			}
	
	}

}
