package com.camelot.jedis.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-common.xml"})
public class RedisDBTest {
	@Autowired
	RedisDB redisDB;
	
	@Test
	public void testAdd() throws FileNotFoundException, IOException {
		redisDB.addObject("one", "hello");
	}
	
	@Test
	public void testGet() {
		Object result = redisDB.get("one");
		System.out.println("------" + result);
	}
	@Test
	public void testDel() {
		
	}
	@Test
	public void testIsExist() {
		
	}
}
