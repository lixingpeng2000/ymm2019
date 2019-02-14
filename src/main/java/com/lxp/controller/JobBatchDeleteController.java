package com.lxp.controller;

import java.awt.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lxp.form.JobBatchDeleteForm;
import com.lxp.service.IJobService;

@RestController
@RequestMapping("/admin/jobBatchDelete")
public class JobBatchDeleteController {
	
	@Autowired
	private IJobService jobservice; 
	private static final Logger logger = LoggerFactory.getLogger(JobBatchDeleteController.class);	
	@RequestMapping(method = RequestMethod.POST)
	public String jobBatchDelete(@RequestBody JobBatchDeleteForm jbdf) throws SQLException{
		System.out.println("命中此方法...jobBatchDelete,param:" + jbdf);
		logger.info("命中此方法...parm" + jbdf);
		ArrayList mylist = (ArrayList) jbdf.getJno();
		Iterator it=mylist.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		jobservice.deleteBatch(mylist);
		return null;
		
	}

}
