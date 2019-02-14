package com.lxp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lxp.entity.JobEntity;
import com.lxp.factory.ServiceFactory;
import com.lxp.json.CommonJson;
import com.lxp.service.IJobService;

@RestController
@RequestMapping("/admin/job")
public class JobPostController {
	@Autowired
	private IJobService jobService;
	private static final Logger logger = LoggerFactory.getLogger(JobPostController.class);	
	@RequestMapping(method = RequestMethod.POST)
	public String createJob(HttpServletRequest req)
			throws UnsupportedEncodingException, IOException {
		//如果用@RequestBody的javabean接受，会去掉换行符号
		System.out.println("命中job接口的post方法");
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
		JobEntity je = (JobEntity) JSON.parseObject(params, JobEntity.class);
		String jobName = je.getJname();
		String city = je.getJcity();
		String dept = je.getDept();
		String jobClass = je.getJclass();
		String initduty = je.getDuty();
		String initrequire = je.getReq();
		String nature = je.getNature();
		String duty = initduty.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		String require = initrequire.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		JobEntity job = new JobEntity(jobName, city, dept, jobClass, duty, require, nature);
		job.setId(UUID.randomUUID().toString());
		System.out.println("param:"+job);
		logger.info("param:"+job);
		if (jobService.findByALLConditionerList(je.getJname(), je.getJcity(),
				je.getDept(), je.getJclass(), je.getNature()).size() != 0) {
			CommonJson cj=new CommonJson();
			cj.setCode("204");
			return JSON.toJSONString(cj);
		} else {
			if (jobService.insert(job)) {
				CommonJson cj=new CommonJson();
				cj.setCode("200");
				return JSON.toJSONString(cj);
			} else {
				CommonJson cj=new CommonJson();
				cj.setCode("500");
				return JSON.toJSONString(cj);
			}

		}

	}
}
