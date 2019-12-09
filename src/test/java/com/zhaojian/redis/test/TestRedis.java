/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: TestRedis.java 
 * @Prject: zhaojian-redis-test
 * @Package: com.zhaojian.redis.test 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年12月9日
 * @version: V1.0   
 */
package com.zhaojian.redis.test;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhaojian.DateUtils;
import com.zhaojian.EmailUtils;
import com.zhaojian.StringUtils;
import com.zhaojian.beans.User;

/** 
 * @ClassName: TestRedis 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年12月9日 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:redis.xml")
public class TestRedis {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	RedisTemplate redisTemplate;
	
	//4.使用JDK系列化方式保存5万个user随机对象到Redis，并计算耗时（18分）
	@SuppressWarnings("unchecked")
	@Test
	public void testJDKSeri() {
	  /*(1)ID使用1-5万的顺序号。（2分）
		(2)姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法。（4分）
		(3)性别在女和男两个值中随机。（4分）
		(4)手机以13开头+9位随机数模拟。（4分）
		(5)邮箱以3-20个随机字母 + @qq.com  | @163.com | @sian.com | @gmail.com | @sohu.com | @hotmail.com | @foxmail.com模拟。（4分）
		(6)生日要模拟18-70岁之间，即日期从1949年到2001年之间。（4分）
		*/
		//开始时间
		long start = System.currentTimeMillis();
		for (int i = 1; i <= 50000; i++) {
			User u = new User();
			//id
			u.setId(i);
			//姓名
			u.setName(StringUtils.getName());
			//性别
			u.setGender(StringUtils.getSex());
			//手机号
			u.setPhone("13"+StringUtils.getRandomNum(9));
			//邮箱
			u.setEmail(EmailUtils.getMail());
			//生日
			u.setBirthday(DateUtils.getBirthday());
			//添加到Redis数据库中
			redisTemplate.opsForValue().set("user"+i, u);
		}
		//结束时间
		long end = System.currentTimeMillis();
		System.out.println("使用JDK序列化方式存5w个user对象到Redis数据库");
		System.out.println("共耗时"+(end-start)+"毫秒");
		System.out.println("共存入5w条数据");
		System.out.println("======================================");
	}
	
	//5.使用JSON系列化方式保存5万个user随机对象到Redis，并计算耗时（18分）
	@SuppressWarnings("unchecked")
	@Test
	public void testJSONSeri() {
	  /*(1)ID使用1-5万的顺序号。（2分）
		(2)姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法。（4分）
		(3)性别在女和男两个值中随机。（4分）
		(4)手机以13开头+9位随机数模拟。（4分）
		(5)邮箱以3-20个随机字母 + @qq.com  | @163.com | @sian.com | @gmail.com | @sohu.com | @hotmail.com | @foxmail.com模拟。（4分）
		(6)生日要模拟18-70岁之间，即日期从1949年到2001年之间。（4分）
		*/
		//开始时间
		long start = System.currentTimeMillis();
		for (int i = 1; i <= 50000; i++) {
			User u = new User();
			//id
			u.setId(i);
			//姓名
			u.setName(StringUtils.getName());
			//性别
			u.setGender(StringUtils.getSex());
			//手机号
			u.setPhone("13"+StringUtils.getRandomNum(9));
			//邮箱
			u.setEmail(EmailUtils.getMail());
			//生日
			u.setBirthday(DateUtils.getBirthday());
			//添加到Redis数据库中
			redisTemplate.opsForValue().set("user"+i, u);
		}
		//结束时间
		long end = System.currentTimeMillis();
		System.out.println("使用JSON序列化方式存5w个user对象到Redis数据库");
		System.out.println("共耗时"+(end-start)+"毫秒");
		System.out.println("共存入5w条数据");
		System.out.println("======================================");
	}
	
	
	//6.使用Redis的Hash类型保存5万个user随机对象到Redis，并计算耗时（18分）
		@SuppressWarnings("unchecked")
		@Test
		public void testHashSeri() {
		  /*(1)ID使用1-5万的顺序号。（2分）
			(2)姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法。（4分）
			(3)性别在女和男两个值中随机。（4分）
			(4)手机以13开头+9位随机数模拟。（4分）
			(5)邮箱以3-20个随机字母 + @qq.com  | @163.com | @sian.com | @gmail.com | @sohu.com | @hotmail.com | @foxmail.com模拟。（4分）
			(6)生日要模拟18-70岁之间，即日期从1949年到2001年之间。（4分）
			*/
			//开始时间
			long start = System.currentTimeMillis();
			for (int i = 1; i <= 50000; i++) {
				HashMap<String,User> map = new HashMap<String,User>();
				User u = new User();
				//id
				u.setId(i);
				//姓名
				u.setName(StringUtils.getName());
				//性别
				u.setGender(StringUtils.getSex());
				//手机号
				u.setPhone("13"+StringUtils.getRandomNum(9));
				//邮箱
				u.setEmail(EmailUtils.getMail());
				//生日
				u.setBirthday(DateUtils.getBirthday());
				map.put("user"+i, u);
				//添加到Redis数据库中
				redisTemplate.opsForHash().put("user", "user"+i, map.toString());
			}
			//结束时间
			long end = System.currentTimeMillis();
			System.out.println("使用Hash序列化方式存5w个user对象到Redis数据库");
			System.out.println("共耗时"+(end-start)+"毫秒");
			System.out.println("共存入5w条数据");
			System.out.println("======================================");
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
