package com.wqj.redis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.wqj.common.controller.CommonController;
import com.wqj.common.util.JedisUtil;
import com.wqj.redis.entity.User;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/redis")
public class RedisController extends CommonController {
	
	private MyJedisPubSub str = new MyJedisPubSub();
	
	
	@RequestMapping("/getT")
	@ResponseBody
	public String getT(){
		User user = JedisUtil.get("user:1", User.class);
		System.out.println(user);
		return "";
	}
	
	//发布  http://kingxss.iteye.com/blog/1420264
	/**
	 * 运行之后，出现如题所示的异常：
	 * redis.clients.jedis.exceptions.JedisDataException: ERR only (P)SUBSCRIBE / (P)UNSUBSCRIBE / QUIT allowed in this context
	 * http://raising.iteye.com/blog/2207602
	 */
	@RequestMapping("/publish")
	@ResponseBody
	public Long publish(){
		Long result = JedisUtil.publish("hello_test", "hello watson");  
		return result;
	}
	
	//订阅
	@RequestMapping("/psubscribe")
	@ResponseBody
	public void psubscribe(){
		JedisUtil.psubscribe(new MyJedisPubSub(), "hello_test");
	}
	
	//订阅
	@RequestMapping("/subscribe")
	@ResponseBody
	public void subscribe(){
		JedisUtil.psubscribe(str, new String[]{"hello_*"});//使用模式匹配的方式设置频道
		//将该用户的订阅放到jedis中【便于去掉订阅时使用】
	}
	
	//取消订阅
	@RequestMapping("/unsubscribe")
	@ResponseBody
	public void unsubscribe(){
		JedisUtil.unsubscribe(str, new String[]{"hello_test"});//使用模式匹配的方式设置频道
		
	}
	
	public static void main(String[] args) {
		/*User user = new User(1,"wqj");
		String result = setUser(user);
		System.out.println(result);*/
		User user = getUser(1);
		System.out.println(user);
	}
	
	
	public static User getUser(long id){
		JedisPool jedisPoll = new JedisPool("127.0.0.1",6379);
		Jedis jedis = jedisPoll.getResource();
		try {
			String key = "user:" + id;
			 //  反序列化
			RuntimeSchema<User> schema = RuntimeSchema.createFrom(User.class);
			byte[] data = jedis.get(key.getBytes());
			if(data != null){
				User message = schema.newMessage();   //空对象
				ProtostuffIOUtil.mergeFrom(data, message, schema);
				return message;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jedis.close();
		}
		return null;
	}
	
	public static String setUser(User user){
		JedisPool jedisPoll = new JedisPool("127.0.0.1",6379);
		Jedis jedis = jedisPoll.getResource();
		try {
			String key = "user:" + user.getId();
			RuntimeSchema<User> schema = RuntimeSchema.createFrom(User.class);
			// 序列化 
			byte[] byteArray = ProtostuffIOUtil.toByteArray(user, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
			String result = jedis.setex(key.getBytes(), 60, byteArray);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jedis.close();
		}
		return null;
	}
	
	// 从左边添加
	public static Long lpush(String key,User user){
		JedisPool jedisPoll = new JedisPool("127.0.0.1",6379);
		Jedis jedis = jedisPoll.getResource();
		try {
			RuntimeSchema<User> schema = RuntimeSchema.createFrom(User.class);
			// 序列化 
			byte[] byteArray = ProtostuffIOUtil.toByteArray(user, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
			Long result = jedis.lpush(key.getBytes(), byteArray);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jedis.close();
		}
		return null;
	}
	
	// 从左边删除
	public static User lpop(String key){
		JedisPool jedisPoll = new JedisPool("127.0.0.1",6379);
		Jedis jedis = jedisPoll.getResource();
		try {
			 //  反序列化
			RuntimeSchema<User> schema = RuntimeSchema.createFrom(User.class);
			byte[] data = jedis.lpop(key.getBytes());
			if(data != null){
				User message = schema.newMessage();   //空对象
				ProtostuffIOUtil.mergeFrom(data, message, schema);
				return message;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jedis.close();
		}
		return null;
	}
	
	// 从右边添加
	public static Long rpush(String key,User user){
		JedisPool jedisPoll = new JedisPool("127.0.0.1",6379);
		Jedis jedis = jedisPoll.getResource();
		try {
			RuntimeSchema<User> schema = RuntimeSchema.createFrom(User.class);
			// 序列化 
			byte[] byteArray = ProtostuffIOUtil.toByteArray(user, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
			Long result = jedis.rpush(key.getBytes(), byteArray);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jedis.close();
		}
		return null;
	}
	
	// 从右边删除
	public static User rpop(String key){
		JedisPool jedisPoll = new JedisPool("127.0.0.1",6379);
		Jedis jedis = jedisPoll.getResource();
		try {
			 //  反序列化
			RuntimeSchema<User> schema = RuntimeSchema.createFrom(User.class);
			byte[] data = jedis.rpop(key.getBytes());
			if(data != null){
				User message = schema.newMessage();   //空对象
				ProtostuffIOUtil.mergeFrom(data, message, schema);
				return message;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jedis.close();
		}
		return null;
	}
	
	// 列表中元素的个数
	public static Long llen(String key){
		JedisPool jedisPoll = new JedisPool("127.0.0.1",6379);
		Jedis jedis = jedisPoll.getResource();
		try {
			Long length = jedis.llen(key);
			return length;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jedis.close();
		}
		return null;
	}
	
	
	
	
}
