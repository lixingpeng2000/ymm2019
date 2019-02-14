package com.lxp.dao;

import java.sql.SQLException;

import com.lxp.entity.EmployEntity;

public interface IEmpDAO {
	public boolean doCreate(EmployEntity vo) throws SQLException;
	public int doRemove(String id) throws SQLException;
	public EmployEntity findByid(int id) throws SQLException;
	public Object findByTel(EmployEntity vo) throws SQLException;

}
