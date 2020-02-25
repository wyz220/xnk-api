package com.xnk.service.api.model;

import java.util.Date;

import com.xnk.service.entity.Entity;

public class SysConstant extends Entity<Long, SysConstant>{
    /**
	 * 系统静态配置
	 */
	private static final long serialVersionUID = 608250855479928252L;

	private Long id;

    private Integer type;

    private Integer childType;

    private Integer seq;

    private String name;

    private String value;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getChildType() {
		return childType;
	}

	public void setChildType(Integer childType) {
		this.childType = childType;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

    
}