package com.lxp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lxp.entity.UserEntity;
import com.lxp.entity.UserRoleEntity;

public interface IUserRoleService {
	
	public boolean insert(UserRoleEntity ur) throws SQLException;

	public boolean remove(String userName) throws SQLException;

	public List find(String userName) throws SQLException;

	public List findUserName() throws SQLException;

	public List findByName(ArrayList alist) throws SQLException;


}
