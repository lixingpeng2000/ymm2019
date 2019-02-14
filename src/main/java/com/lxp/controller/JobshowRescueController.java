package com.lxp.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lxp.json.CommonJson;
import com.lxp.service.IJobService;

@RestController
@RequestMapping("/admin/jobshowRescue")
public class JobshowRescueController {
	
	private static final Logger logger = LoggerFactory.getLogger(JobshowRescueController.class);	
	@Autowired
	private IJobService jobService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getjobshow(@RequestParam("id") String id) throws SQLException {
		logger.info("param:"+id);
		System.out.println("要恢复的id为:"+id);
		jobService.rescue(id);
		CommonJson cj=new CommonJson();
		cj.setCode("200");
		cj.setMessage("数据恢复成功");
		System.out.println("result:"+JSON.toJSONString(cj, true));
		return JSON.toJSONString(cj, true);
		
	}

}
