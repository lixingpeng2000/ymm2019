package com.lxp.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lxp.factory.ServiceFactory;
import com.lxp.json.JobListJson;
import com.lxp.service.IJobService;

@RestController
@RequestMapping("/jobselect")
public class JobSelectController {
	private static final Logger logger = LoggerFactory.getLogger(JobSelectController.class);	
	@Autowired
	private IJobService jobService;
	@RequestMapping(method = RequestMethod.GET)
	public String getJobSelect(HttpServletRequest req) throws UnsupportedEncodingException {
		System.out.println("命中jobselect接口的get方法...");
		String city = req.getParameter("city");
		String jclass = req.getParameter("jclass");
		String dept = req.getParameter("dept");
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		System.out.println("city:" + city);
		System.out.println("jclass:" + jclass);
		System.out.println("dept:" + dept);
		System.out.println("pageNum:" + pageNum);
		JobListJson jl=new JobListJson();
		if ("请选择职能类型".equals(jclass) && "请选择部门".equals(dept)) {
			if (null != jobService.findBycityList(city)) {
				List cityList = jobService.findBycityList(city);
				System.out.println(cityList);
				List newcityList = new ArrayList();
				int lastNum;
				if (cityList.size() % 5 == 0) {
					lastNum = (cityList.size() / 5);
				} else {
					lastNum = (cityList.size() / 5) + 1;
				}

				int lastdatanum = cityList.size() - (lastNum - 1) * 5;
				int pageCount = lastNum;
				if (pageCount == 0) {
					lastdatanum = 0;
				}
				String objjsonlist = "";
				if (pageCount == 0) {
					objjsonlist = "";
				} else {
					if (pageNum != lastNum) {
						newcityList.add(cityList.get((pageNum - 1) * 5 + 0));
						newcityList.add(cityList.get((pageNum - 1) * 5 + 1));
						newcityList.add(cityList.get((pageNum - 1) * 5 + 2));
						newcityList.add(cityList.get((pageNum - 1) * 5 + 3));
						newcityList.add(cityList.get((pageNum - 1) * 5 + 4));
					} else {
						for (int i = 0; i < lastdatanum; i++) {
							newcityList.add(cityList.get((pageNum - 1) * 5 + i));
						}
					}
					}
				jl.setCode("200");
				jl.setPageCount(pageCount);
				jl.setPageNum(pageNum);
				jl.setData(newcityList);
				return JSON.toJSONString(jl);

			} else {
				jl.setCode("500");
				return JSON.toJSONString(jl);
			}

		} else if (!"请选择职能类型".equals(jclass) && "请选择部门".equals(dept)) {

			if (null != jobService.findByCityandJclassList(city, jclass)) {
				List cityList = jobService.findByCityandJclassList(city, jclass);
				System.out.println(cityList);
				List newcityList = new ArrayList();
				int lastNum;
				if (cityList.size() % 5 == 0) {
					lastNum = (cityList.size() / 5);
				} else {
					lastNum = (cityList.size() / 5) + 1;
				}
				int lastdatanum = cityList.size() - (lastNum - 1) * 5;
				int pageCount = lastNum;
				if (pageCount == 0) {
					lastdatanum = 0;
				}
				String objjsonlist = "";
				if (pageCount == 0) {
					objjsonlist = "";
				} else {
					if (pageNum != lastNum) {// �ж��Ƿ��������һҳ����
						newcityList.add(cityList.get((pageNum - 1) * 5 + 0));
						newcityList.add(cityList.get((pageNum - 1) * 5 + 1));
						newcityList.add(cityList.get((pageNum - 1) * 5 + 2));
						newcityList.add(cityList.get((pageNum - 1) * 5 + 3));
						newcityList.add(cityList.get((pageNum - 1) * 5 + 4));
					} else {
						for (int i = 0; i < lastdatanum; i++) {
							newcityList.add(cityList.get((pageNum - 1) * 5 + i));
						}
					}
				}
				jl.setCode("200");
				jl.setPageCount(pageCount);
				jl.setPageNum(pageNum);
				jl.setData(newcityList);
				return JSON.toJSONString(jl);

			} else {
				jl.setCode("500");
				return JSON.toJSONString(jl);
			}

		} else if ("请选择职能类型".equals(jclass) && !"请选择部门".equals(dept)) {

			if (null != jobService.findByCityandDeptList(city, dept)) {
				List cityList = jobService.findByCityandDeptList(city, dept);
				List newcityList = new ArrayList();
				int lastNum;
				if (cityList.size() % 5 == 0) {
					lastNum = (cityList.size() / 5);
				} else {
					lastNum = (cityList.size() / 5) + 1;
				}
				int lastdatanum = cityList.size() - (lastNum - 1) * 5;
				int pageCount = lastNum;
				if (pageCount == 0) {
					lastdatanum = 0;
				}
				String objjsonlist = "";
				if (pageCount == 0) {
					objjsonlist = "";
				} else {
					if (pageNum != lastNum) {// �ж��Ƿ��������һҳ����
						newcityList.add(cityList.get((pageNum - 1) * 5 + 0));
						newcityList.add(cityList.get((pageNum - 1) * 5 + 1));
						newcityList.add(cityList.get((pageNum - 1) * 5 + 2));
						newcityList.add(cityList.get((pageNum - 1) * 5 + 3));
						newcityList.add(cityList.get((pageNum - 1) * 5 + 4));
					} else {
						for (int i = 0; i < lastdatanum; i++) {
							newcityList.add(cityList.get((pageNum - 1) * 5 + i));
						}
					}
				}
				jl.setCode("200");
				jl.setPageCount(pageCount);
				jl.setPageNum(pageNum);
				jl.setData(newcityList);
				return JSON.toJSONString(jl);

			} else {
				if (null != jobService.findBySelectAllList(city, jclass, dept)) {
					System.out.println(city);
					System.out.println(jclass);
					System.out.println(dept);
					List cityList = jobService.findBySelectAllList(city, jclass, dept);
					System.out.println(cityList);
					List newcityList = new ArrayList();
					int lastNum;
					if (cityList.size() % 5 == 0) {
						lastNum = (cityList.size() / 5);
					} else {
						lastNum = (cityList.size() / 5) + 1;
					}
					int lastdatanum = cityList.size() - (lastNum - 1) * 5;
					int pageCount = lastNum;
					if (pageCount == 0) {
						lastdatanum = 0;
					}
					String objjsonlist = "";
					if (pageCount == 0) {
						objjsonlist = "";
					} else {
						if (pageNum != lastNum) {
							newcityList.add(cityList.get((pageNum - 1) * 5 + 0));
							newcityList.add(cityList.get((pageNum - 1) * 5 + 1));
							newcityList.add(cityList.get((pageNum - 1) * 5 + 2));
							newcityList.add(cityList.get((pageNum - 1) * 5 + 3));
							newcityList.add(cityList.get((pageNum - 1) * 5 + 4));
						} else {
							for (int i = 0; i < lastdatanum; i++) {
								newcityList.add(cityList.get((pageNum - 1) * 5 + i));
							}
						}
						}
					jl.setCode("200");
					jl.setPageCount(pageCount);
					jl.setPageNum(pageNum);
					jl.setData(newcityList);
					return JSON.toJSONString(jl);
				}
			}

		} else {

			if (null != jobService.findBySelectAllList(city, jclass, dept)) {
				List cityList = jobService.findBySelectAllList(city, jclass, dept);
				List newcityList = new ArrayList();
				int lastNum;
				if (cityList.size() % 5 == 0) {
					lastNum = (cityList.size() / 5);
				} else {
					lastNum = (cityList.size() / 5) + 1;
				}
				int lastdatanum = cityList.size() - (lastNum - 1) * 5;
				int pageCount = lastNum;
				if (pageCount == 0) {
					lastdatanum = 0;
				}
				String objjsonlist = "";
				if (pageCount == 0) {
					objjsonlist = "''";
				} else {
					if (pageNum != lastNum) {// �ж��Ƿ��������һҳ����
						newcityList.add(cityList.get((pageNum - 1) * 5 + 0));
						newcityList.add(cityList.get((pageNum - 1) * 5 + 1));
						newcityList.add(cityList.get((pageNum - 1) * 5 + 2));
						newcityList.add(cityList.get((pageNum - 1) * 5 + 3));
						newcityList.add(cityList.get((pageNum - 1) * 5 + 4));
					} else {
						for (int i = 0; i < lastdatanum; i++) {
							newcityList.add(cityList.get((pageNum - 1) * 5 + i));
						}
					}
				}
				jl.setCode("200");
				jl.setPageCount(pageCount);
				jl.setPageNum(pageNum);
				jl.setData(newcityList);
				return JSON.toJSONString(jl);

			} else {
				jl.setCode("500");
				return JSON.toJSONString(jl);
			}
		}
		return null;
	}
}