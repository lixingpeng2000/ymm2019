package com.lxp.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lxp.entity.EmpJobEntity;
import com.lxp.factory.ServiceFactory;
import com.lxp.json.CommonJson;
import com.lxp.service.IJobService;

@RestController
@RequestMapping("/admin/employJobshow")
public class EmployJobshowController {
	private static final Logger logger = LoggerFactory.getLogger(EmployJobshowController.class);
	@Autowired
	private IJobService jobService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getEmployJobshow() {
		System.out.println("命中employJobshow接口的get方法");
		logger.info("命中employJobshow接口的get方法");
		CommonJson cj=new CommonJson();
		if (null != jobService.findEmployJobList()) {
			List cityList = jobService.findEmployJobList();
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

	@RequestMapping(method = RequestMethod.DELETE)
	public String deleteEmployJobshow(@RequestParam("id") String id) {
		logger.info("入参id:" + id);
		System.out.println("命中jobshow接口的delete方法...");
		System.out.println(id);
		CommonJson cj=new CommonJson();
		try {
			if (!ServiceFactory.getIEmpServiceIstance().deleteEmployById(id)) {
				ServiceFactory.getIEmpServiceIstance().deleteEmployById(id);
				System.out.println(ServiceFactory.getIEmpServiceIstance().deleteEmployById(id));
				cj.setCode("200");
				cj.setMessage("删除成功");
				return JSON.toJSONString(cj);

			} else {
				cj.setCode("500");
				cj.setMessage("删除失败");
				return JSON.toJSONString(cj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
