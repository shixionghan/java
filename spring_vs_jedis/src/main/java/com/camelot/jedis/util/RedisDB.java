package com.camelot.jedis.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component("redisDB")
public class RedisDB {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {

		String value = (String) redisTemplate.opsForValue().get(key);
		return value;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 添加对象
	 * 
	 * @param key
	 * @param object
	 */
	public void addObject(String key, Object object) {
		redisTemplate.opsForValue().set(key, object);
	}

	/**
	 * 带生命周期的对象
	 * 
	 * @param key
	 * @param object
	 */
	public void addObject(String key, Object object, int seconds) {
		redisTemplate.opsForValue().set(key, object);
		redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
	}

	/**
	 * 获取对象
	 * 
	 * @param key
	 * @param object
	 */
	public Object getObject(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * 
	 * @param key
	 */
	public void del(String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 添加值，并且设置过期时间
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public void setAndExpire(String key, String value, int seconds) {
		redisTemplate.opsForValue().set(key, value);
		redisTemplate.expire(key, seconds, TimeUnit.SECONDS);

	}

	/**
	 * 存入redis的hash
	 * 
	 * @param key
	 *            hashID
	 * @param field
	 *            字段�?	 * @param value
	 */
	public void setHash(String key, String field, String value) {
		redisTemplate.opsForHash().put(key, field, value);
	}

	/**
	 * 根据key和字段�?获取内容�?	 * 
	 * @param key
	 * @param field
	 * @return value 内容
	 */
	public String getHash(String key, String field) {
		return (String) redisTemplate.opsForHash().get(key, field);
	}

	/**
	 * 设置key的过期时间，endTime格式：yyyy-MM-dd hh:mm:ss
	 * 
	 * @param key
	 * @param endTime
	 */
	public void setExpire(String key, Date endTime) {
		long seconds = endTime.getTime() - new Date().getTime();
		redisTemplate.expire(key, (int) (seconds / 1000), TimeUnit.SECONDS);
	}

	public void flushDB() {
		redisTemplate.getConnectionFactory().getConnection().flushDb();
	}

	/**
	 * 在redis消息队列队尾插入数据
	 * 
	 * @param key
	 * @param value
	 */
	public void tailPush(String key, Object object) {
		redisTemplate.opsForList().rightPush(key, object);
	}

	/**
	 * 在redis消息队列对头插入数据
	 * 
	 * @param key
	 * @param value
	 */
	public void headPush(String key, Object object) {
		redisTemplate.opsForList().leftPush(key, object);
	}

	/**
	 * 在redis消息队列队尾删除数据
	 * 
	 * @param key
	 */
	public Object tailPop(String key) {
		return redisTemplate.opsForList().rightPop(key);

	}

	/**
	 * 在redis消息队列队头删除数据
	 * 
	 * @param key
	 */
	public Object headPop(String key) {
		return redisTemplate.opsForList().leftPop(key);
	}
}