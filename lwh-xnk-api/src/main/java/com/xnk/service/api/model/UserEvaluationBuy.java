package com.xnk.service.api.model;

import java.util.Date;

import com.xnk.service.entity.Entity;

public class UserEvaluationBuy extends Entity<Long,UserEvaluationBuy>{
    /**
	 * 
	 */
	private static final long serialVersionUID = -9007153817508193089L;

	private Long id;

    private Long userId;

    private Long evaluationId;

    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}