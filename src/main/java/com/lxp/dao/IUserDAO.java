package com.lxp.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lxp.entity.EmployEntity;
import com.lxp.entity.UserEntity;

public interface IUserDAO {

	public int doCreate(UserEntity user) throws SQLException;

	public boolean doRemove(String id);

	public UserEntity findByid(String id);
	public UserEntity doFind(@Param("username") String username,@Param("pwd")String pwd) throws SQLException;

	public List doFindAll() throws SQLException;

	public UserEntity doFindByusername(String username) throws SQLException;

}
