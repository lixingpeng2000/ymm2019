package com.lxp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PageRouteControl {  
    @GetMapping("/ymm")
	public String index(){
		System.out.println("前端首页访问");
		return "index";
	}  
    @GetMapping("/ymm/SocialRecruitment.html")
	public String SocialRecruitment(){
		return "SocialRecruitment";
	} 
    
    @GetMapping("/ymm/logout.html")
	public String logout(){
		return "logout";
	} 
    
    @GetMapping("/ymm/login.html")
	public String login(){
		return "login";
	} 
    
    @GetMapping("/ymm/register.html")
	public String register(){
		return "register";
	} 
    
    @GetMapping("/ymm/jobUpdate.html")
   	public String jobUpdate(){
    	System.out.println("命中jobUpdate路径...");
   		return "jobUpdate";
   	} 
    
   
 
}
