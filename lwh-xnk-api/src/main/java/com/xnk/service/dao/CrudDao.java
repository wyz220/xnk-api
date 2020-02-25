package com.xnk.service.dao;

import java.util.List;

/**
 * DAO支持类实现
 */
public interface CrudDao<T> extends BaseDao<T> {
	

	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insertBatch(List<T> list);
	
}