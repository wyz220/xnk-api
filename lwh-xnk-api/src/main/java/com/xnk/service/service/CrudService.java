package com.xnk.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xnk.service.dao.CrudDao;
import com.xnk.service.entity.Entity;
import com.xnk.service.entity.Page;

/**
 * Service基类
 */
public abstract class CrudService<D extends CrudDao<T>, T extends Entity<Long,T>> {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	

	/**
	 * @param entity
	 * @return
	 */
	public int delete(T entity){
		return dao.delete(entity);
	}
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity){
		return dao.get(entity);
	}
	
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(T entity){
		return dao.insert(entity);
	}
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int update(T entity){
		return dao.update(entity);
	}
	
	/**
	 * 条件查询
	 * @param ids
	 * @return
	 */
	public List<T> list(T entity){
		return dao.list(entity);
	}
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int count(T entity){
		return dao.count(entity);
	}
	
	public Page<T> page(T entity){
		entity.getPage().setCount(this.dao.count(entity));
		entity.getPage().setList(this.dao.page(entity));
		return entity.getPage();
	}
	
}
