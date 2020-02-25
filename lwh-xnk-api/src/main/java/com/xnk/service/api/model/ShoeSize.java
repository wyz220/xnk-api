package com.xnk.service.api.model;

import java.util.Date;

import com.xnk.service.entity.Entity;

public class ShoeSize extends Entity<Long,ShoeSize>{
    /**
	 * 尺码大小  对应某尺码类型下
	 */
	private static final long serialVersionUID = 5331854748616712249L;

	private Long id;

    private Double size;

    private Integer num;

    private Byte type;

    private Long shoeSizeType;

    private String shoeSizeTypeName;

    private Long brandId;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Long getShoeSizeType() {
        return shoeSizeType;
    }

    public void setShoeSizeType(Long shoeSizeType) {
        this.shoeSizeType = shoeSizeType;
    }

    public String getShoeSizeTypeName() {
        return shoeSizeTypeName;
    }

    public void setShoeSizeTypeName(String shoeSizeTypeName) {
        this.shoeSizeTypeName = shoeSizeTypeName == null ? null : shoeSizeTypeName.trim();
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