package com.xnk.service.api.model;

import java.util.Date;
import java.util.List;

import com.xnk.service.entity.Entity;

public class EvaluationUserWriteRecomItem extends Entity<Long, EvaluationUserWriteRecomItem>{
    /**
	 * 用户写推荐序
	 */
	private static final long serialVersionUID = 5377146268195051199L;

	private Long id;

    private Long evaluationId;

    private Long userId;

    private String userName;

    private Integer type;//测评项
    
    private Date createTime;

    private Date updateTime;

    private String types;//1,2 推荐序组
    
    List<Object> childs;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public List<Object> getChilds() {
		return childs;
	}

	public void setChilds(List<Object> childs) {
		this.childs = childs;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}