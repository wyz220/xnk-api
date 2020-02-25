package com.xnk.service.api.model;

import java.util.Date;

import com.xnk.service.entity.Entity;

public class Brand extends Entity<Long,Brand>{
    /**
	 * 品牌
	 */
	private static final long serialVersionUID = 2527971953803822930L;

	private Long id;

    private String name;

    private String englishName;

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

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName == null ? null : englishName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}