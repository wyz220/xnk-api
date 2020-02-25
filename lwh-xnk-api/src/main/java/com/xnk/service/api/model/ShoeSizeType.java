package com.xnk.service.api.model;

import java.util.Date;

import com.xnk.service.entity.Entity;

public class ShoeSizeType extends Entity<Long,ShoeSizeType>{
    /**
	 * 尺码类型  对应某品牌下
	 */
	private static final long serialVersionUID = -8114981451990867410L;

	private Long id;

	private Integer type ;
	
    private String name;

    private Long brandId;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}