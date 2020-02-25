package com.xnk.service.provider.redis.pool;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xnk.service.provider.utils.PropertyUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

public class JedisPoolManager {
	
	private static Logger logger = LoggerFactory.getLogger(JedisPoolManager.class);
	
	private volatile static JedisPoolManager manager;
	
	private final JedisPool jedisPool;
	
	/** sesssion库 协调者库 */
	public static final int DEFAULT_DB = 0; 
	public static final int TEMP_DB = 4;//存放用户注册邮箱激活验证码
	public static final int VERIFY_CODE_DB = 5;//存放用户忘记密码验证码
	
	/**
	 * 缓存生存时间
	 */
	private final int expire = 60000;
	public static final int EXPIRE_SEC = 1;
	public static final int EXPIRE_SEC_10 = EXPIRE_SEC*10;
	public static final int EXPIRE_MINUTE = 60;
	public static final int EXPIRE_MINUTE_2 = EXPIRE_MINUTE*2;
	public static final int EXPIRE_MINUTE_3 = EXPIRE_MINUTE*3;
	public static final int EXPIRE_MINUTE_5 = EXPIRE_MINUTE*5;
	public static final int EXPIRE_MINUTE_10 = EXPIRE_MINUTE*10;
	public static final int EXPIRE_HALF_HOUR = EXPIRE_MINUTE*30;
	public static final int EXPIRE_HOUR = EXPIRE_MINUTE*60;
	public static final int EXPIRE_DAY = EXPIRE_HOUR*24;
	public static final int EXPIRE_DAY_3 = EXPIRE_DAY*3;
	public static final int EXPIRE_WEEK = EXPIRE_DAY*7;
	public static final int EXPIRE_MONTH = EXPIRE_DAY*30;
	
	private JedisPoolManager() {

		try {
			//加载redis配置
			Properties props = PropertyUtils.load("redis.properties");
			
			String host = props.getProperty("redis.host");
			int port = Integer.valueOf(props.getProperty("redis.port", "6379"));
			String password =  props.getProperty("redis.password", null);
			if (StringUtils.isBlank(password)){
				password = null;
			}

			int timeout = Integer.valueOf(props.getProperty("redis.timeout", "10000"));
			
			int maxTotal = Integer.valueOf(props.getProperty("redis.pool.maxTotal", "1024"));
			int maxIdle = Integer.valueOf(props.getProperty("redis.pool.maxIdle", "100"));
			int minIdle = Integer.valueOf(props.getProperty("redis.pool.minIdle", "10"));
			int maxWait = Integer.valueOf(props.getProperty("redis.pool.maxWait", "10000"));
			
			boolean testOnBorrow = Boolean.valueOf(props.getProperty("redis.pool.testOnBorrow", "true"));
			boolean testOnReturn = Boolean.valueOf(props.getProperty("redis.pool.testOnReturn", "true"));
			
			
            JedisPoolConfig poolConfig = new JedisPoolConfig();
//            redis.pool.maxTotal=1024 
//            		redis.pool.maxIdle=200 
//            		redis.pool.minIdle=5 
//            		redis.pool.maxWaitMillis=1000 
//            		redis.pool.testOnBorrow=true 
//            		redis.pool.testOnReturn=true 
//          redis.pool.maxActive=200  #最大连接数：能够同时建立的“最大链接个数”  
//    		redis.pool.maxIdle=20     #最大空闲数：空闲链接数大于maxIdle时，将进行回收
//    		redis.pool.minIdle=5      #最小空闲数：低于minIdle时，将创建新的链接
//    		redis.pool.maxWait=3000    #最大等待时间：单位ms
//    		redis.pool.testOnBorrow=true   #使用连接时，检测连接是否成功 
//    		redis.pool.testOnReturn=true  #返回连接时，检测连接是否成功 
      
            poolConfig.setMaxTotal(maxTotal);
            poolConfig.setMaxIdle(maxIdle); 
            poolConfig.setMinIdle(minIdle);
            poolConfig.setMaxWaitMillis(maxWait);
            poolConfig.setTestOnBorrow(testOnBorrow);
            poolConfig.setTestOnReturn(testOnReturn);
            
            jedisPool = new JedisPool(poolConfig,  host, port,
            		 timeout, password);    
			
		} catch (IOException e) {
			throw new IllegalArgumentException("init JedisPool error", e);
		}
		
	}

	public static JedisPoolManager getMgr() {
		if (manager == null) {
			synchronized (JedisPoolManager.class) {
				if (manager == null) {
					manager = new JedisPoolManager();
				}
			}
		}
		return manager;
	}

	public Jedis getJedis() {
		Jedis jedis = jedisPool.getResource();
		jedis.select(DEFAULT_DB);
		
		return jedis;
	}

	public void destroy() {
		// when closing your application:
		jedisPool.destroy();
	}
	
	public void close() {
		jedisPool.close();
	}
	
	public void returnJedis(Jedis jedis) {
		 if (jedis != null) {
			try {
				jedis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			jedis = null; 
        }
	}
	
	public void set(String key, int seconds, String value, int db, boolean override) {
		if (value == null || value.equals("")) {
			logger.info("key: " + key + " 对应的value为空");
			return;
		}
		
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(db);
			if(override)
			   jedis.set(key, value);
			else{
			   jedis.setnx(key, value);
			   jedis.expire(key, seconds);
			}
		} catch (JedisException  e) {
			logger.error(e.getMessage());
			returnJedis(jedis);
		} catch (Exception  e) {
			logger.error(e.getMessage());
		} finally {
			returnJedis(jedis);
		}
	}
	
	public void del(String key, int db) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(db);
			jedis.del(key);			
		} catch (JedisException  e) {
			logger.error(e.getMessage());
			returnJedis(jedis);
		} catch (Exception  e) {
			logger.error(e.getMessage());
		} finally {
			returnJedis(jedis);
		}

	}
	
	public void hset(String key, String field, int seconds, String value, int db, boolean override) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(db);
			if(override)
			   jedis.hset(key, field, value);
			else{
			   jedis.hsetnx(key, field, value);
			   jedis.expire(key, seconds);
			}
		} catch (JedisException  e) {
			logger.error(e.getMessage());
			returnJedis(jedis);
		} catch (Exception  e) {
			logger.error(e.getMessage());
		} finally {
			returnJedis(jedis);
		}
	}
	
	public void hmset(String key, Map<String, String> hash, int seconds, int db, boolean override) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(db);
			if (override){
				jedis.hmset(key, hash);
			}else{
				jedis.hmset(key, hash);
				jedis.expire(key, seconds);
			}
		} catch (JedisException  e) {
			logger.error(e.getMessage());
			returnJedis(jedis);
		} catch (Exception  e) {
			logger.error(e.getMessage());
		} finally {
			returnJedis(jedis);
		}
	}
	
	public String hget(String key, String field, int db) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(db);
			value = jedis.hget(key, field);
		} catch (JedisException  e) {
			logger.error(e.getMessage());
			returnJedis(jedis);
		} catch (Exception  e) {
			logger.error(e.getMessage());
		} finally {
			returnJedis(jedis);
		}
		return value;
	}
	
	public Map<String, String> hgetall(String key, int db) {
		Map<String, String> value = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(db);
			value = jedis.hgetAll(key);
		} catch (JedisException  e) {
			logger.error(e.getMessage());
			returnJedis(jedis);
		} catch (Exception  e) {
			logger.error(e.getMessage());
		} finally {
			returnJedis(jedis);
		}
		return value;
	}
	
	public String get(String key, int db) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(db);
			value = jedis.get(key);
		} catch (JedisException  e) {
			logger.error(e.getMessage());
			returnJedis(jedis);
		} catch (Exception  e) {
			logger.error(e.getMessage());
			returnJedis(jedis);
		} finally {
			returnJedis(jedis);
		}
		
		return value;

	}
	
	/*public static interface RedisExecutor<T>{
		
		public T execute(Jedis jedis); 
		
	}
	
	private <T> T redisOp(RedisExecutor<T> executor,Object ... args){
		Jedis jedis = getJedis();
		try{
			T result = executor.execute(jedis);
			return result;
		}catch(Exception e){
			 logger.info(e.getMessage());
		}finally{
			returnJedis(jedis);
		}
		
		
		return null;
	}
	
	*//**
	 * 删除缓存
	 * @param key
	 *//*
	public void del(final String key){
		this.redisOp(new RedisExecutor<Void>(){
			@Override
			public Void execute(Jedis jedis) {
				byte[] genKey = genKey(key);
				jedis.del(genKey);
				return null;
			}
		}, "del",key);
	}

	private byte[] genKey(String key){
		try {
			return key.getBytes("utf-8");
			//return SerializeUtil.serialize(key);
		} catch (Exception e) {
			//can't happen
			return key.getBytes();
		}
	}
	
	private String byte2String(byte[] key){
		try {
			return new String(key,"utf-8");
		} catch (UnsupportedEncodingException e) {
			return new String(key);
		}
	}*/
	
	public Object evalsha(String script,int keycount,String[] params, int db) {
		Object value = null;
		Jedis jedis = null;
		String sha1 = null;
		try {
			jedis = getJedis();
			jedis.select(db);
			if (sha1 == null) {
				sha1 = jedis.scriptLoad(script);
			}
			value = jedis.evalsha(sha1, keycount, params);
		} catch (JedisException  e) {
			logger.error(e.getMessage());
			returnJedis(jedis);
		} catch (Exception  e) {
			logger.error(e.getMessage());
			returnJedis(jedis);
		} finally {
			returnJedis(jedis);
		}
		
		return value;

	}
	
	public Object evalsha(String script,List<String> keys,List<String> args, int db) {
		Object value = null;
		Jedis jedis = null;
		String sha1 = null;
		try {
			jedis = getJedis();
			jedis.select(db);
			if (sha1 == null) {
				sha1 = jedis.scriptLoad(script);
			}
			value = jedis.evalsha(sha1, keys, args);
		} catch (JedisException  e) {
			logger.error(e.getMessage());
			returnJedis(jedis);
		} catch (Exception  e) {
			logger.error(e.getMessage());
			returnJedis(jedis);
		} finally {
			returnJedis(jedis);
		}
		
		return value;

	}
}
