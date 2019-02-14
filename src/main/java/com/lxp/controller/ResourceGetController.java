package com.lxp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lxp.entity.UserEntity;
import com.lxp.entity.UserRoleEntity;
import com.lxp.entity.UserRoleManagerEntity;
import com.lxp.factory.ServiceFactory;
import com.lxp.json.CommonJson;
import com.lxp.json.UserRoleManagerJson;
import com.lxp.service.IUserRoleService;
import com.lxp.service.IUserService;

@RestController
@RequestMapping("admin/getResource")
public class ResourceGetController {
	@Autowired
    private IUserService userService;
	@Autowired
	private IUserRoleService userRoleService;
	@RequestMapping(method = RequestMethod.GET)//获取所有用户的权限列表
	public String findUsers() {
		System.out.println("命中ResourceGetController类的get...方法....");
		//1、从数据库中获取设置了角色的用户
		ArrayList<String> alist = new ArrayList();
		try {
			List userList = userRoleService.findUserName();
			for(int i=0;i<userList.size();i++){
				UserRoleEntity ur = (UserRoleEntity) userList.get(i);
				System.out.println(ur.getUserName());
				alist.add(ur.getUserName());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("有角色的用户数目为"+alist.size());
		//2、获取所有的用户
		List<String> userNameList = new ArrayList();
		try {
			List listuser=userService.findAll();
			for(int j=0;j<listuser.size();j++){
				UserEntity ue = (UserEntity) listuser.get(j);
				userNameList.add(ue.getUsername());
			}
			for(int i=0;i<userNameList.size();i++){
				System.out.println("获取到的所有用户名---》" + userNameList.get(i));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserRoleManagerEntity[] allUserList =  new UserRoleManagerEntity[userNameList.size()];//用于存放所有的用户角色json格式
		System.out.print("所有的用户数目为"+userNameList.size());
		//3、可以算出没有设置角色的用户
		userNameList.removeAll(alist);
		//4、生成需要的json格式	
		try {
			//4.1、第一个for循环，将有角色的用户存放到集合中
			for(int j=0;j<alist.size();j++){
				List userList = new ArrayList();//用于存放单个用户的角色
				userList.clear();
				List miduserList=userRoleService.find(alist.get(j));//获取到当前用户名的所有角色
				for(int m=0;m < miduserList.size(); m++){
					UserRoleEntity ur = (UserRoleEntity) miduserList.get(m);
					System.out.println(ur);
					System.out.println(ur.getRoleName());
					userList.add(ur.getRoleName());			
				}
				UserRoleManagerEntity urm=new UserRoleManagerEntity(alist.get(j),userList);
				allUserList[j]=urm;
			}	
			//4.2、将没有角色的用户继续存入集合
			for(int i=0;i<userNameList.size();i++){
				System.out.println("差集为"+userNameList.get(i));
				UserRoleManagerEntity newur=new UserRoleManagerEntity(userNameList.get(i),new ArrayList());
				System.out.println("集合存放空角色的下标为" +( alist.size() + i));
				allUserList[alist.size() + i]=newur;
				System.out.println(JSON.toJSONString(newur));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		System.out.println("datalist部分数据---》"+JSON.toJSONString(allUserList));
		UserRoleManagerJson urmj=new UserRoleManagerJson("200",allUserList);
		String objjsonlist = JSON.toJSONString(urmj, true);
		System.out.println(objjsonlist);
		return objjsonlist;
	}


	@RequestMapping(method = RequestMethod.POST)//设置权限
	public String postResource(HttpServletRequest req) throws UnsupportedEncodingException, IOException {
		System.out.println("resource接口的post方法....");
		BufferedReader br = new BufferedReader(
				new InputStreamReader((ServletInputStream) req.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer("");
		String temp;
		while ((temp = br.readLine()) != null) {
			sb.append(temp);
		}
		br.close();
		String params = sb.toString();
		UserRoleEntity userole = (UserRoleEntity) JSON.parseObject(params, UserRoleEntity.class);
		String userName = userole.getUserName();
		String parseRoleData = userole.getRoleName();
		String myData = parseRoleData.substring(1, parseRoleData.length() - 1);
		System.out.println("myData-->" + myData);
		String roleData[] = myData.split(",");
		System.out.println("roleData.length-->" + roleData.length);
		List<UserRoleEntity> urlist = null;
		// 加判断
		if ("".equals(myData)) {
			System.out.println("传入的是空数组,操作简单直接删了该用户的数据");
			try {
				userRoleService.remove(userName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			System.out.println("传入的是非空数组....");
			// 1、获取到传参
			urlist = new ArrayList();// 传参的数据
			for (int i = 0; i < roleData.length; i++) {
				int weight = 0;
				System.out.println(roleData[i]);
				UserRoleEntity ur = new UserRoleEntity();
				ur.setUserName(userName);
				ur.setRoleName(roleData[i].substring(1, roleData[i].length() - 1));
				// 根据roleName判断weight值；
				if (roleData[i].substring(1, roleData[i].length() - 1).equals("root")) {
					weight = 5;
				} else if (roleData[i].substring(1, roleData[i].length() - 1).equals("admin")) {
					weight = 3;
				} else {
					weight = 1;
				}
				System.out.println("角色:" + roleData[i].substring(1, roleData[i].length() - 1) + ",weight:" + weight);
				ur.setWeight(weight);
				urlist.add(ur);
			}
			for (int j = 0; j < urlist.size(); j++) {
				System.out.println(urlist.get(j));
			}
			System.out.println("数据处理结束");
			List userList = null;
			UserRoleEntity ur = null;
			// 2、从数据库中取出该用户名的所有记录即该用户对应的角色数目
			try {
				userList = userRoleService.find(urlist.get(0).getUserName());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				// 先判断一下该用户角色是否已经添加过；
				if (null != userList) { // 改用户曾经添加过角色
					// 先删再创建
					userRoleService.remove(userName);
					for (int i = 0; i < urlist.size(); i++) {// 挨个创建用户角色
						userRoleService.insert(urlist.get(i));

					}

				} else {
					// 不存在该用户角色，直接插入数据，不用改
					System.out.println("命中此处，说明数据库中不存在该用户，直接插入数据");
					for (int i = 0; i < urlist.size(); i++) {// 挨个创建用户角色
						userRoleService.insert(urlist.get(i));
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
		CommonJson cj=new CommonJson();
		cj.setCode("200");
		cj.setMessage("操作成功");
		return JSON.toJSONString(cj);
	}


}
