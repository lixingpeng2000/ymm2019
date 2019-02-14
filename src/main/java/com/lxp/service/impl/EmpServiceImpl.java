package com.lxp.service.impl;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lxp.dao.IEmpDAO;
import com.lxp.entity.EmployEntity;
import com.lxp.service.IEmpService;

public class EmpServiceImpl implements IEmpService {
	private static final Logger logger = LoggerFactory.getLogger(EmpServiceImpl.class);
	private SqlSession session = null;

	public EmpServiceImpl() {
		String resource = "conf.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		this.session = sessionFactory.openSession(true);
	}

	public boolean insert(EmployEntity vo) throws SQLException {
		System.out.println("service-param:"+vo);
		IEmpDAO dao = session.getMapper(IEmpDAO.class);// �����ݿ����Ӵ���dao��
		logger.info("param:"+vo);
		System.out.println(dao);
		return dao.doCreate(vo);
	}

	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteEmployById(String id) throws SQLException {
		IEmpDAO dao = session.getMapper(IEmpDAO.class);// �����ݿ����Ӵ���dao��
		logger.info("param:"+id);
		if(dao.doRemove(id)>0){
			return true;
		}else{
			return false;
		}
		
	}

	public boolean findBytel(String tel, String jname, String jcity) throws SQLException {
		EmployEntity emp = new EmployEntity();
		emp.setTel(tel);
		emp.setJname(jname);
		emp.setJcity(jcity);
		logger.info("param:"+emp);
		IEmpDAO dao = session.getMapper(IEmpDAO.class);
		if(null == dao.findByTel(emp)){
			return false;
		}else{
			return true;
		}
	}

}
