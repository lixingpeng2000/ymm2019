package com.lxp.controller;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String  params="{\"name\":\"annie\",\"tel\":\"18550400079\",\"mail\":\"lxp@qq.com\",\"jname\":\"Java初级工程师\",\"jcity\":\"苏州\",\"cv\":\"http://pk80mh7y9.bkt.clouddn.com/25814711-55b9-4e28-8c1a-6299e355b966.jpg\"}"; 

		System.out.println(params);
		String newStr = params.substring(1, params.length() - 1);
		String[] sourceStrArray = newStr.split(",");
		String data[] = new String[6];
		String[] strdata = new String[6];
		for (int i = 0; i < 6; i++) {
			strdata = sourceStrArray[i].split(":");
			data[i] = strdata[1];
		}
		String name = data[0].substring(1, data[0].length() - 1);
		String tel = data[1].substring(1, data[1].length() - 1);
		;
		String mail = data[2].substring(1, data[2].length() - 1);
		String jname = data[3].substring(1, data[3].length() - 1);
		String jcity = data[4].substring(1, data[4].length() - 1);
		String cvname = data[5].substring(1, data[5].length() - 1);
		System.out.println("cvname--->"+cvname);
		System.out.println("#######&&&&&&&&&&&&&");
		System.out.println("name:" + name + ",tel:" + tel + ",mail:" + mail + ",jname:" + jname + ",jcity:" + jcity+ ",cv:" + cvname);
	}

}
