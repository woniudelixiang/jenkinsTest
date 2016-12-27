package com.wqj.common.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

@Component
public class JedisUtil {
	
	@Autowired
	private  static JedisPool jedisPoll;
	
	/**
	 * 保存数据
	 * @param key
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String set(String key, Object value) throws Exception {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = getJedis();
			byte[] bytes = objectToBytes(value.getClass());
			result = jedis.set(key.getBytes(), bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 保存数据（超时）
	 * @param key
	 * @param obj
	 * @param time  单位：秒
	 */
	public static String setex(String key, Object value, int time) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = getJedis();
			byte[] bytes = objectToBytes(value.getClass());
			result = jedis.setex(key.getBytes(), time, bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 获取数据
	 * @param key
	 * @param clazz
	 * @return
	 */
	public static <T> T get(String key, Class<T> clazz) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			byte[] bytes = jedis.get(key.getBytes());
			T obj = bytesToObject(bytes, clazz);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(jedis);
		}
		return null;
	}
	
	/**
	 * 存储redis队列 顺序存储
	 * @param byte[] key reids键名
	 * @param byte[] value 键值
	 */
	public static Long lpush(String key, Object value) {
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = getJedis();
			byte[] bytes = objectToBytes(value.getClass());
			result = jedis.lpush(key.getBytes(), bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 存储redis队列 反向存储
	 * @param key   键
	 * @param value 值
	 * @return
	 */
	public static Long rpush(String key, Object value) {
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = getJedis();
			byte[] bytes = objectToBytes(value.getClass());
			result = jedis.rpush(key.getBytes(), bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 获取队列数据
	 * @param key 键名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> lpopList(String key, int from, int to) {
		Jedis jedis = null;
		List<T> list = null;
		try {
			jedis = getJedis();
			list = (List<T>) jedis.lrange(key.getBytes(), from, to);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(jedis);
		}
		return list;
	}
	
	
	/**
	 * 订阅-确定的频道
	 * @param jedisPubSub
	 * @param patterns
	 */
	public static void subscribe(final JedisPubSub jedisPubSub, final String... channels) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			//可以订阅多个频道 , 订阅得到信息在lister的onMessage(...)方法中进行处理  
			jedis.subscribe(jedisPubSub, channels);  
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(jedis);
		}
	}
	
	/**
	 * 订阅-使用模式匹配的方式设置频道
	 * @param jedisPubSub
	 * @param patterns
	 */
	public static void psubscribe(final JedisPubSub jedisPubSub, final String... patterns) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			//这里启动了订阅监听，线程将在这里被阻塞 , 订阅得到信息在lister的onPMessage(...)方法中进行处理  
			jedis.psubscribe(jedisPubSub, patterns);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(jedis);
		}
	}
	
	/**
	 * 发布
	 * @param channel
	 * @param message
	 * @return
	 */
	public static Long publish(final String channel, final String message){
		Long result = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			result = jedis.publish(channel, message);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(jedis);
		}
		return result;
	}
	
	/**
	 * 取消订阅
	 * @param jedisPubSub
	 * @param patterns
	 */
	public static void unsubscribe(final JedisPubSub jedisPubSub, final String... channels) {
		jedisPubSub.unsubscribe(channels);
	}
	
	/**
	 * 获取RuntimeSchema
	 * @param clazz
	 * @return
	 */
	public static <T> RuntimeSchema<T> getRuntimeSchema(Class<T> clazz) {
		RuntimeSchema<T> schema = RuntimeSchema.createFrom(clazz);
		return schema;
	}
	
	/**
	 * 对象转byte[]
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> byte[] objectToBytes(Class<T> clazz) throws Exception {
		RuntimeSchema<T> schema = getRuntimeSchema(clazz);
		byte[] bytes = ProtostuffIOUtil.toByteArray(clazz.newInstance(), schema,
				LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
		return bytes;
	}
	
	/**byte[]转对象
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static <T> T bytesToObject(byte[] bytes, Class<T> clazz) throws Exception {
		RuntimeSchema<T> schema = getRuntimeSchema(clazz);
		if (bytes != null) {
			T message = schema.newMessage(); // 空对象
			ProtostuffIOUtil.mergeFrom(bytes, message, schema);
			return message;
		}
		return null;
	}

	/**
	 * 获取jedis
	 * @return
	 */
	public static Jedis getJedis() {
		return  jedisPoll.getResource();
	}
	
	/**
	 * 关闭jedis
	 */
	public static void close(Jedis jedis ) {
		if (jedis != null) {
			jedis.close();
		}
	}

	public static JedisPool getJedisPoll() {
		return jedisPoll;
	}

	public static void setJedisPoll(JedisPool jedisPoll) {
		System.out.println("*****************   setJedisPoll   ***********************");
		JedisUtil.jedisPoll = jedisPoll;
	}
}
