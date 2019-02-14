package com.lxp.service.impl;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lxp.controller.UserController;
import com.lxp.dao.StaffDAO;
import com.lxp.entity.Staff;
import com.lxp.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffDAO dao;
	private static final Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);	
	// 使用@Transactional注解的rollbackFor属性，指定特定异常时，数据回滚。
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String saveStaffWithRollBack() {
		System.out.println("进入到要回滚的业务层....注意了，要回滚了...");
			int i = 0;
			Staff sf01=new Staff("003","lxp01",12);
			System.out.println("dao:"+dao);
			dao.save(sf01);
			logger.info("进入到servie层1.....");
			Staff sf02=new Staff("002","lxp02",1234123);
			dao.save(sf02);
			return "SUCCESS";
	}

	@Override
	public Staff saveStaffWithoutRollBack(Staff staff) {
		System.out.println("进入到不用回滚的业务层....");
		int i = 0;
		logger.info("进入到servie层1.....");
		try {
			i = dao.save(staff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return staff;
	}

}
