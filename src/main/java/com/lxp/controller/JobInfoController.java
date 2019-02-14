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
import com.lxp.entity.JobEntity;
import com.lxp.factory.ServiceFactory;
import com.lxp.json.CommonJson;
import com.lxp.service.IJobService;

@RestController
@RequestMapping("/jobInfo")
public class JobInfoController {
	private static final Logger logger = LoggerFactory.getLogger(JobInfoController.class);
	@Autowired
	private IJobService jobService;

	@RequestMapping(method = RequestMethod.GET)
	public String getJobInfo(JobEntity job) {
		System.out.println("命中jobinfo接口的get方法...");
		System.out.println(job);
		String jname = job.getJname();
		String city = job.getJcity();
		String dept = job.getDept();
		String jclass = job.getJclass();
		String nature = job.getNature();
		CommonJson cj = new CommonJson();
		if (null != jobService.findByALLConditionerList(jname, city, dept, jclass, nature)) {
			List cityList = (List) jobService.findByALLConditionerList(jname, city, dept, jclass, nature);
			cj.setCode("200");
			cj.setData(cityList);
			cj.setMessage("获取成功");
			return JSON.toJSONString(cj);

		} else {
			cj.setCode("500");
			cj.setMessage("获取失败");
			return JSON.toJSONString(cj);
		}

	}

}
