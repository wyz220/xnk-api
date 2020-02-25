package com.xnk.service.api.model;

import java.util.Date;
import java.util.List;

import com.xnk.service.entity.Entity;

public class EvaluationCommentItem extends Entity<Long, EvaluationCommentItem>{
    /**
	 * 评论内容
	 */
	private static final long serialVersionUID = -2771031771120069158L;

	private Long id;

    private Long parentId;

    private Long evaluationId;

    private Long userId;

    private String userName;

    private Integer type;

    private String content;

    private Date createTime;

    private List<EvaluationCommentItem> child;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    
    public List<EvaluationCommentItem> getChild() {
		return child;
	}

	public void setChild(List<EvaluationCommentItem> child) {
		this.child = child;
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


    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}