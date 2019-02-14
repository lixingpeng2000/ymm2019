package com.lxp.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;

import com.lxp.util.JWTUtil;


@Component
public class InterceptorConfig  implements HandlerInterceptor{  
  
    /** 
     * 进入controller层之前拦截请求 
     * @param httpServletRequest 
     * @param httpServletResponse 
     * @param o 
     * @return 
     * @throws Exception 
     */  
    @Override  
    public boolean preHandle(HttpServletRequest res, HttpServletResponse httpServletResponse, Object o) throws Exception {  
    	System.out.println("进入此拦截器...");
        String Mytoken=res.getHeader("X-Auth-Token");
        System.out.println("获取到的token是：");
        System.out.println(Mytoken);
        if(JWTUtil.verify(Mytoken)){
        	System.out.println("校验成功...");
        	return true;
        }else{
        System.out.println("token校验失败");
         return false;
        }
    }
    
}
