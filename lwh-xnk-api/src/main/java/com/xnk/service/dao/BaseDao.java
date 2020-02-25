package com.xnk.service.dao;

import java.util.List;

/**
 * DAO支持类实现
 */
public interface BaseDao<T> {

	/**
	 * @param entity
	 * @return
	 */
	public int delete(T entity);
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity);
	
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(T entity);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int update(T entity);
	
	/**
	 * 条件查询
	 * @param ids
	 * @return
	 */
	public List<T> list(T entity);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int count(T entity);
	
	/**
	 * 分页查询
	 * @param entity
	 * @return
	 */
	public List<T> page(T entity);
	
	
}