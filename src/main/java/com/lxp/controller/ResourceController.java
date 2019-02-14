package com.lxp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lxp.entity.JobCityEntity;
import com.lxp.entity.JobEntity;
import com.lxp.entity.UserEntity;
import com.lxp.entity.UserRoleEntity;
import com.lxp.entity.UserRoleManagerEntity;
import com.lxp.factory.ServiceFactory;
import com.lxp.json.CommonJson;
import com.lxp.service.IJobService;
import com.lxp.service.IUserRoleService;

@RestController
@RequestMapping("admin/resource")
public class ResourceController {
	@Autowired
	private IUserRoleService userRoleService;
	@RequestMapping(method = RequestMethod.GET)//获取所有用户的权限列表
	public String findUserName(@RequestParam("userName") String userName) {
		UserRoleEntity uem=null;
		try {
			List userRoleList=new ArrayList();
			List userlist=userRoleService.find(userName);
			int weight = 0;
			for(int j=0;j<userlist.size();j++){
				UserRoleEntity ue = (UserRoleEntity) userlist.get(j);
				weight += ue.getWeight();
			}
		 uem=new UserRoleEntity();
		 uem.setUserName(userName);
		 uem.setWeight(weight);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CommonJson cj=new CommonJson();
		cj.setCode("200");
		cj.setMessage("获取权限成功");
		cj.setData(uem);
		return JSON.toJSONString(cj);		
	}	
}
