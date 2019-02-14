package com.lxp.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxp.dao.IUserDAO;
import com.lxp.dao.IUserRoleDAO;
import com.lxp.entity.UserEntity;
import com.lxp.entity.UserRoleEntity;
import com.lxp.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDAO dao;
	@Autowired
	private IUserRoleDAO roledao;
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Override
	@Transactional(rollbackFor=Exception.class)
		public boolean insert(UserEntity user) throws SQLException {
		System.out.println("执行...insert");
		logger.info("param:"+user);
		if(dao.doCreate(user)>0){
			System.out.println("执行到此处，说明用户增加成功了....");
			UserRoleEntity ure=new UserRoleEntity();
			ure.setUserName(user.getUsername());
			ure.setRoleName("account");
			ure.setWeight(1);
			System.out.println("ure-->"+ure);
			roledao.doCreate(ure);
			logger.info("docreate success");
			return true;
		}else{
			logger.error("docreate failue");
			return false;
		}	
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserEntity find(UserEntity user) throws SQLException {
		System.out.println("命中service层的...find");
		logger.info("param:"+user);
		System.out.println("传参为:"+user);
		System.out.println(dao);
		String username = user.getUsername();
		String pwd = user.getPwd();
		System.out.println("username:"+username);
		return dao.doFind(username,pwd);
	}

	@Override
	public UserEntity findBycity() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findAll() throws SQLException {
		System.out.println("进入service层的...findAll");
		System.out.println(dao);
		return dao.doFindAll();
	}

	@Override
	public boolean findByuserName(String username) {
		System.out.println("进入serivce层，param:"+username);
		try {
			if(null != dao.doFindByusername(username)){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

}
