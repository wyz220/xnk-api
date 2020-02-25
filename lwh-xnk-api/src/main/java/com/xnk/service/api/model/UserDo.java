package com.xnk.service.api.model;

import java.util.Date;

import com.xnk.service.entity.Entity;

public class UserDo extends Entity<Long, UserDo>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8601042282006070223L;

	private Long id;

    private Long userId;

    private Long evaluationId;

    private Integer type;

    private Integer childType;

    private Integer status;

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
    
    public enum Type {
		Recommed(1), Need(2) , EvealCommentChild(3) ;
//1推荐测评 2 想买测评 3测评项点赞
		private int type;

		private Type(int type) {
			this.type = type;
		}

		public int getType() {
			return type;
		}

		public static Type getType(int type) {
			switch (type) {
			case 1:
				return Recommed;
			case 2:
				return Need;
			case 3:
				return EvealCommentChild;
			}
			return Need;
		}
	}
}