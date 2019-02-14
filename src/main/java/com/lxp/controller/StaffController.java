package com.lxp.controller;

import java.sql.SQLException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lxp.entity.Staff;
import com.lxp.service.StaffService;
import com.lxp.service.impl.StaffServiceImpl;

@RestController
@RequestMapping("/staff")
public class StaffController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);	
	
	@Autowired
	private StaffService service;

	@RequestMapping(value="/rollback",method = RequestMethod.GET)
    public String rollback(Staff staff) throws SQLException {
    	System.out.println("命中staffController类的rollback方法...");
    	logger.debug(">>>debug日志是否打印....");
    	logger.info(">>>>logback生效打印日志开始....");
    	logger.error("1313");
    	Staff stf=new Staff();
    	stf.setName(staff.getName());
    	stf.setAge(staff.getAge());
    	stf.setId(UUID.randomUUID().toString());
    	System.out.println(">>>>>>>"+stf);
        return  service.saveStaffWithRollBack();
    }
    //测试不回滚情况
	@RequestMapping(value="/notrollback",method = RequestMethod.GET)
    public Staff noRollBack(Staff staff) {
    	Staff stf=new Staff();
    	stf.setName(staff.getName());
    	stf.setAge(staff.getAge());
    	stf.setId(UUID.randomUUID().toString());
    	System.out.println("###>>>>>>>"+stf);
        return new StaffServiceImpl().saveStaffWithoutRollBack(stf);
    }
}