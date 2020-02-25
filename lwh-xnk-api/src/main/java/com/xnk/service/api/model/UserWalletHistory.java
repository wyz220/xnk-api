package com.xnk.service.api.model;

import java.util.Date;

import com.xnk.service.entity.Entity;

public class UserWalletHistory extends Entity<Long, UserWalletHistory>{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8378900406501550674L;

	private Long id;

    private Long userId;

    private Long evaluationId;

    private Integer type;

    private Integer childType;

    private Double amount;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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