package com.lxp.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CodeUtil {
	private static int codeCount = 4;// 定义图片上显示验证码的个数
	private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	/**
	 * 生成一个map集合 code为生成的验证码 codePic为生成的验证码BufferedImage对象
	 * 
	 * @return
	 */
	public static String generateCodeAndPic() {
		// 创建一个随机数生成器类
		Random random = new Random();
		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的验证码数字。
			String code = String.valueOf(codeSequence[random.nextInt(36)]);
			randomCode.append(code);
			// 将产生的四个随机数组合在一起。 
		}
		Map<String, Object> map = new HashMap<String, Object>();
		// 存放验证码
		System.out.println("randomCode--->"+randomCode);
		return randomCode.toString();
	}

}
