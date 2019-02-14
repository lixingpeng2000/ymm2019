package com.lxp.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lxp.factory.ServiceFactory;
import com.lxp.json.CommonJson;
import com.lxp.service.IJobService;

@RestController
@RequestMapping("/jobBrief")
public class JobBriefController {
	private static final Logger logger = LoggerFactory.getLogger(JobBriefController.class);
	@Autowired
	private IJobService jobService;
	@RequestMapping(method = RequestMethod.GET)
	public String getJobBrief(@RequestParam("jcity") String jcity, @RequestParam("jname") String jname) {
		System.out.println("命中jobBrief接口的get方法...");
		System.out.println(jcity);
		System.out.println(jname);
		logger.info("param:" + jcity + "--" + "jname:" + jname);
		CommonJson cj=new CommonJson();
		if (null != jobService.findByJnameandCityList(jname, jcity)) {
			List cityList = jobService.findByJnameandCityList(jname, jcity);
			logger.info("param:" + cityList);
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
