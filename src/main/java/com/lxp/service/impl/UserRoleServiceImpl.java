package com.lxp.service.impl;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxp.dao.IUserDAO;
import com.lxp.dao.IUserRoleDAO;
import com.lxp.entity.UserRoleEntity;
import com.lxp.service.IUserRoleService;
@Service
public class UserRoleServiceImpl implements IUserRoleService {
	@Autowired
	private IUserRoleDAO dao;
	private static final Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);

	@Override
	public boolean insert(UserRoleEntity ur) throws SQLException {
		System.out.println("userRoleService接口的...insert");
		if(dao.doCreate(ur)>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean remove(String userName) throws SQLException {
		System.out.println("userRoleService接口的...remove");
		if(dao.doRemove(userName)>0){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public List find(String userName) throws SQLException {
		System.out.println("userRoleService接口的...find");
		return dao.doFind(userName);
		
	}


	@Override
	public List findUserName() throws SQLException {
		System.out.println("userRoleService接口的...findUserName");
		return dao.dofindUserName();
	}

	@Override
	public List findByName(ArrayList alist) throws SQLException {
		System.out.println("userRoleService接口的...findByName");
		return dao.dofindByName(alist);
	}

}
