package com.xnk.service.api.model;

import java.util.Date;

import com.xnk.service.entity.Entity;

public class FollowUser extends Entity<Long, FollowUser>{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3404499849041601806L;

	private Long id;

    private Long userId;

    private Long followUserId;

    private Integer status;
    
    private Integer type;//1关注  2特别关注
    
    private Date createTime;

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFollowUserId() {
        return followUserId;
    }

    public void setFollowUserId(Long followUserId) {
        this.followUserId = followUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}