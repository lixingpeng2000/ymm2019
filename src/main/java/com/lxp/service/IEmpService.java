package com.lxp.service;

import java.sql.SQLException;

import com.lxp.entity.EmployEntity;

public interface IEmpService {
	public boolean insert(EmployEntity vo) throws SQLException;
	public boolean delete(int id);
	public boolean deleteEmployById(String id) throws SQLException;
	public boolean findBytel(String tel,String jname,String jcity) throws SQLException;

}
