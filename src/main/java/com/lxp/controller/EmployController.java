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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lxp.entity.EmployEntity;
import com.lxp.factory.ServiceFactory;
import com.lxp.json.CommonJson;

@RestController
@RequestMapping("/employ")
public class EmployController {
	private static final Logger logger = LoggerFactory.getLogger(EmployController.class);
	@RequestMapping(method = RequestMethod.POST)
	public String postEmploy(@RequestBody EmployEntity emp) throws UnsupportedEncodingException, IOException {
		System.out.println("命中employ接口的post方法....");
		System.out.println(emp);
		emp.setEmpNo(UUID.randomUUID().toString());
		System.out.println("#######");
		System.out.println(emp.getCv());
		System.out.println("#######");
		System.out.println("控制层接受到的参数:"+emp);
		CommonJson cj = null;
		try {
			if (ServiceFactory.getIEmpServiceIstance().findBytel(emp.getTel(), emp.getJname(), emp.getJcity())) {
				cj = new CommonJson();
				cj.setCode("501");
				cj.setMessage("提交失败");
				logger.info("result:"+cj);
				return JSON.toJSONString(cj);
			} else {
				try {
					if (ServiceFactory.getIEmpServiceIstance().insert(emp)) {
						cj = new CommonJson();
						cj.setCode("200");
						cj.setMessage("提交成功");
						logger.info("result:"+cj);
						return JSON.toJSONString(cj);
					} else {
						cj.setCode("500");
						cj.setMessage("提交失败");
						logger.error("result:"+cj);
						return JSON.toJSONString(cj);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		cj.setCode("500");
		cj.setMessage("提交失败");
		logger.error("result:"+cj);
		return JSON.toJSONString(cj);
	}

}
