package com.lxp.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lxp.entity.JobEntity;
import com.lxp.factory.ServiceFactory;
import com.lxp.json.CommonJson;
import com.lxp.service.IJobService;

@RestController
@RequestMapping("/admin/jobshow")
public class JobShowController {
	private static final Logger logger = LoggerFactory.getLogger(JobShowController.class);	
	@Autowired
	private IJobService jobService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getjobshow(@RequestParam("flag") String flag) throws SQLException {
			System.out.println("命中jobshow接口的get方法...");	
			System.out.println("获取到的flag为:" + flag);
			if (null != jobService.findJobAllList(flag)) {
				List cityList = jobService.findJobAllList(flag);
				String objjsonlist = JSON.toJSONString(cityList, true);
				CommonJson cj=new CommonJson();
				cj.setCode("200");
				cj.setMessage("获取数据成功");
				cj.setData(cityList);
				System.out.println("result:"+JSON.toJSONString(cj, true));
				return JSON.toJSONString(cj, true);

			} else {
				CommonJson cj=new CommonJson();
				cj.setCode("500");
				cj.setMessage("获取数据失败");
				return JSON.toJSONString(cj);
			}		
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public String removejobshow(@RequestParam("id") String id) {
		System.out.println("命中jobshow接口的delete方法...");
		System.out.println(id);
	
			if (!jobService.deleteJobById(id)) {
				jobService.deleteJobById(id);
				System.out.println(jobService.deleteJobById(id));
				CommonJson cj=new CommonJson();
				cj.setCode("200");
				cj.setMessage("删除数据成功");
				return JSON.toJSONString(cj);
			} else {
				CommonJson cj=new CommonJson();
				cj.setCode("500");
				cj.setMessage("删除数据失败");
				return JSON.toJSONString(cj);
			}
		
		
		
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String updatejobshow(@RequestBody JobEntity job){
		System.out.println("命中jobshow接口的put方法...");
		System.out.println(job);
		logger.info("获取到的param:"+job);	
		CommonJson cj=new CommonJson();	
		if(jobService.update(job)){
			cj.setCode("200");
			cj.setMessage("数据更新成功");
			System.out.println(JSON.toJSONString(cj));
			return JSON.toJSONString(cj);
		};
		cj.setCode("500");
		cj.setMessage("数据更新失败");
		return JSON.toJSONString(cj);

		
	}
}
