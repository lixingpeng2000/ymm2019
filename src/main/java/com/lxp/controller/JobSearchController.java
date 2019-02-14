package com.lxp.controller;

import java.sql.SQLException;
import java.util.ArrayList;
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
import com.lxp.json.JobListJson;
import com.lxp.service.IJobService;

@RestController
@RequestMapping("/jobnameSearch")
public class JobSearchController {
	private static final Logger logger = LoggerFactory.getLogger(JobSearchController.class);
	@Autowired
	private IJobService jobService;

	@RequestMapping(method = RequestMethod.GET)
	public String jobSearch(@RequestParam("keyword") String jKeyword, @RequestParam("city") String city,
			@RequestParam("pageNum") String pagenum) {
		System.out.println("命中jobnameSearch接口的get方法...");
		System.out.println("获取到的参数是:" + jKeyword + city + pagenum);
		int pageNum = Integer.parseInt(pagenum);
		JobListJson jl=new JobListJson();
		if (null != jobService.findByJnameList(jKeyword, city)) {
			List cityList = jobService.findByJnameList(jKeyword, city);
			System.out.println(cityList);
			List newcityList = new ArrayList();
			int lastNum;
			if (cityList.size() % 5 == 0) {
				lastNum = (cityList.size() / 5);
			} else {
				lastNum = (cityList.size() / 5) + 1;
			}

			int lastdatanum = cityList.size() - (lastNum - 1) * 5;
			int pageCount = lastNum;
			if (pageCount == 0) {
				lastdatanum = 0;
			}
			String objjsonlist = "";
			if (pageCount == 0) {
				objjsonlist = "";
			} else {
				if (pageNum != lastNum) {// �ж��Ƿ��������һҳ����
					newcityList.add(cityList.get((pageNum - 1) * 5 + 0));
					newcityList.add(cityList.get((pageNum - 1) * 5 + 1));
					newcityList.add(cityList.get((pageNum - 1) * 5 + 2));
					newcityList.add(cityList.get((pageNum - 1) * 5 + 3));
					newcityList.add(cityList.get((pageNum - 1) * 5 + 4));
				} else {
					for (int i = 0; i < lastdatanum; i++) {
						newcityList.add(cityList.get((pageNum - 1) * 5 + i));
					}
				}
				objjsonlist = JSON.toJSONString(newcityList, true);

			}
			jl.setCode("200");
			jl.setPageCount(pageCount);
			jl.setPageNum(pageNum);
			jl.setData(newcityList);
			return JSON.toJSONString(jl);

		} else {
			jl.setCode("500");
			return JSON.toJSONString(jl);
		}

	}

}
