package com.lxp.service;

import java.sql.SQLException;
import java.util.List;

import com.lxp.entity.EmployEntity;
import com.lxp.entity.UserEntity;
import org.springframework.stereotype.Service;

public interface IUserService {

		public boolean insert(UserEntity user) throws SQLException;
		public boolean delete(int id);
		public UserEntity find(UserEntity user) throws SQLException;
		public UserEntity findBycity() throws SQLException;
		public List findAll() throws SQLException;
		public boolean findByuserName(String username);
}
