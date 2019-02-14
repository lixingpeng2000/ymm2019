package com.lxp.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtil {
	//定义超时时间为60分钟
		private static final int experTime = 60*60*1000;
		private static final String secret = "lxp18550400076";
		/**
		 * 定义签名方法即生成token
		 * param userName
		 * @return token
		 */
		
		public static String sign(String userName){
			try {
				//过期时间
				Date date=new Date(System.currentTimeMillis() + experTime);
				//定义头部信息
				Map<String,Object> header=new HashMap<String, Object>();
				header.put("alg", "HS256");
				//定义加密算法
			    Algorithm algorithm = Algorithm.HMAC256(secret);
			    //生成token
			    String token = JWT.create()
			        .withHeader(header)
			        .withClaim("userName", userName)
			        .withExpiresAt(date)
			        .sign(algorithm);
			    return token;
			} catch (UnsupportedEncodingException exception){
			    return "UnsupportedEncodingException：编码方式不支持";
			} catch (JWTCreationException exception){
				return "JWTCreationException：JWT创建异常";
			}
			
		}

		/**
		 * 定义token校验方法
		 * @return
		 * @throws UnsupportedEncodingException 
		 * @throws IllegalArgumentException 
		 */
		public static boolean verify(String token) {
			//定义加密算法
		    Algorithm algorithm;
			try {		
				algorithm = Algorithm.HMAC256(secret);
				JWTVerifier jwtverify=JWT.require(algorithm).build();
				DecodedJWT djwt=jwtverify.verify(token);
				return true;
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  		
			return false;
			
		}
}
