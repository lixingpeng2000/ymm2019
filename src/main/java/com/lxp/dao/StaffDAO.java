package com.lxp.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.lxp.entity.Staff;
public interface StaffDAO {
	
	public int save(Staff sf);

}
