package com.lxp.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.lxp.entity.Staff;


public interface StaffService {
	String saveStaffWithRollBack() throws SQLException;//回滚
    Staff saveStaffWithoutRollBack(Staff staff);//不回滚

}
