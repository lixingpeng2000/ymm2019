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
@RequestMapping("/jobClass")
public class JobClassController {
	@Autowired
	private IJobService jobService;
	private static final Logger logger = LoggerFactory.getLogger(JobClassController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String getJobClass() {
		System.out.println("命中jobclass的get方法....");
		CommonJson cj=new CommonJson();
		if (null != jobService.findJobClassList()) {
			List cityList = jobService.findJobClassList();
			cj.setCode("200");
			cj.setMessage("获取成功");
			cj.setData(cityList);
			logger.info("result:" + cj);
			return JSON.toJSONString(cj);
		} else {
			cj.setCode("500");
			cj.setMessage("数据获取失败");
			return JSON.toJSONString(cj);
		}

	}

}
