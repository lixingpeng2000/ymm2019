package com.lxp.service.impl;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lxp.dao.IJobDAO;
import com.lxp.entity.JobEntity;
import com.lxp.service.IJobService;

@Service
public class JobServiceImpl implements IJobService {
	@Autowired
	private IJobDAO dao;
	private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);	

	@Override
	public boolean insert(JobEntity job) {
		System.out.println("命中...insert...");
		logger.info("service层param:"+job);
		System.out.println("job:"+job);
		try {
			if(dao.doCreate(job)>0){
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JobEntity find(JobEntity job) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findBycity() {
		System.out.println("命中findBycity方法...");
		System.out.println("dao:"+dao);
		try {
			return dao.findByCity();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List findBycityList(String city) {
		System.out.println("命中MyBatis...findBycityList...");
		try {
			return dao.findByCityList(city);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List findJobClassList() {
		System.out.println("命中MyBatis...findJobClassList...");
		try {
			return dao.findJobClassList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List findJobDeptList() {
		System.out.println("命中MyBatis...findJobDeptList...");
		try {
			return dao.findJobDeptList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List findByJnameList(String jKeyword, String city) {
		System.out.println("命中MyBatis...findByJnameList...");
		System.out.println("keyword:"+jKeyword);
		System.out.println("dao:"+dao);
		JobEntity job=new JobEntity();
		String kw="%"+jKeyword+"%";
		job.setJcity(city);
		job.setJname(kw);
		try {
			return dao.findJobNameList(job);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List findByCityandJclassList(String city, String jclass) {
		System.out.println("命中MyBatis...findByCityandJclassList...");
		JobEntity job=new JobEntity();
		job.setJcity(city);
		job.setJclass(jclass);
		try {
			return dao.findByCityandJclassList(job);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List findByCityandDeptList(String city, String dept) {
		System.out.println("命中MyBatis...findByCityandDeptList...");
		JobEntity job=new JobEntity();
		job.setJcity(city);
		job.setDept(dept);
		try {
			return dao.findByCityandDeptList(job);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List findBySelectAllList(String city, String jclass, String dept) {
		System.out.println("命中MyBatis...findBySelectAllList...");
		JobEntity job=new JobEntity();
		job.setJcity(city);
		job.setJclass(jclass);
		job.setDept(dept);
		try {
			return dao.findBySelectAllList(job);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}

	@Override
	public List findByJnameandCityList(String jname, String city) {
		System.out.println("命中MyBatis...findByJnameandCityList...");
		JobEntity job=new JobEntity();
		job.setJname(jname);
		job.setJcity(city);
		try {
			return dao.findByJnameandCityList(job);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List findJobAllList(String flag) {
		System.out.println("命中MyBatis...findJobAllList...");
		logger.info("进入service的findJobAllList方法...");
		System.out.println(flag);
		if(flag.equals("true")){
			System.out.println("进入此分支，表示查找没被删除的数据");
			try {
				return dao.findJobAllList();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("进入此分支，表示查找软删除的数据...");
			try {
				return dao.findJobAllListDelete();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public boolean deleteJobById(String id) {
		System.out.println("命中MyBatis...deleteJobById...");
		try {
			if(dao.deleteJobById(id)>1){
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean deleteBatch(List jnolist) {
		// 此处进行复杂操作,启用数据库事务，删除一起成功或者一起失败
				logger.info("业务层params:"+jnolist);
				Iterator it=jnolist.iterator();
				while(it.hasNext()){
					String jno=it.next().toString();
					System.out.println("需要删除的id：" + jno);
					try {			
						dao.deleteJobById(jno);
						logger.info("删除id"+jno+"成功!");
						//此处如果有异常会进行回滚操作
					} catch (SQLException e) {
						e.printStackTrace();
						logger.error("删除失败，应该删除的id为"+jno);
					}
				}
				return true;
	}

	@Override
	public boolean update(JobEntity job) {
		System.out.println("命中MyBatis...update...");
		System.out.println("job:"+job);
		try {
			if(dao.update(job)>0){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List findEmployJobList() {
		System.out.println("命中MyBatis...findEmployJobList...");
		try {
			System.out.println("dao:"+dao);
			return dao.findEmployJobList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List findByALLConditionerList(String jname, String city, String dept, String jclass, String nature) {
		System.out.println("命中"+city+"**"+jname+"**"+dept+"**"+jclass+"**"+nature);
		System.out.println("命中MyBatis...findByALLConditionerList..");
		JobEntity job=new JobEntity();
		job.setJcity(city);
		job.setJname(jname);
		job.setDept(dept);
		job.setJclass(jclass);
		job.setNature(nature);
		try {
			return dao.findByALLConditionerList(job);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean rescue(String id) {
		System.out.println("命中MyBatis...rescue...");
		try {
			if(dao.rescue(id)>1){
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
