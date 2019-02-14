package com.lxp.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.lxp.entity.JobEntity;
import com.lxp.entity.UserEntity;

public interface IJobService {
	public boolean insert(JobEntity job) ;
	public boolean delete(int id);	
	public JobEntity find(JobEntity job) ;
	public List findBycity() ;
	public List findBycityList(String city) ;
	public List findJobClassList() ;
	public List findJobDeptList();
	public List findByJnameList(String jKeyword,String city) ;
	public List findByCityandJclassList(String city,String jclass) ;
	public List findByCityandDeptList(String city, String dept) ;
	public List findBySelectAllList(String city, String jclass, String dept);
	public List findByJnameandCityList(String jname, String city) ;
	public List findJobAllList(String flag) ;
	public boolean deleteJobById(String id) ;
	public boolean deleteBatch(List jnolist) ;
	public boolean update(JobEntity job) ;
	public List findEmployJobList() ;
	public List findByALLConditionerList(String jname, String city, String dept, String jclass, String nature) ;
	public boolean rescue(String id);

}
