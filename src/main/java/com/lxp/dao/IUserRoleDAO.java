package com.lxp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lxp.entity.UserEntity;
import com.lxp.entity.UserRoleEntity;

public interface IUserRoleDAO {
	public int doCreate(UserRoleEntity ur) throws SQLException;

	public int doRemove(String userName) throws SQLException;

	public List doFind(String userName) throws SQLException;

	public List dofindUserName() throws SQLException;

	public List dofindByName(ArrayList alist) throws SQLException;
}
