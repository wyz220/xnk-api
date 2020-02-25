/**
 * 
 */
package com.xnk.service.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author lindaofen
 *
 */
public abstract class Entity<PK extends java.io.Serializable,T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 删除标记（0：正常；1：删除；2：审核；）
	 */
	public static final int DEL_FLAG_NORMAL = 0;
	public static final int DEL_FLAG_DELETE = 1;
	public static final int DEL_FLAG_AUDIT = 2;

	protected Page<T> page;

	@JsonIgnore
	@XmlTransient
	public Page<T> getPage() {
		if (page == null){
			page = new Page<T>();
		}
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}
	
}
