package com.xnk.service.api.model;

import java.util.Date;

import com.xnk.service.entity.Entity;

public class FollowShoeColor extends Entity<Long, FollowShoeColor>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 9033905946934420869L;

	private Long id;

    private Long userId;

    private Long followId;

    private Integer type;

    private Integer status;
    
    private Date createTime;

    private String name;

	private Double releasePrice;

	private String sn;

	private Integer year;

	private Integer evaluationNum;

	private Integer userNum;

	private Integer colorNum;

	private Integer hotNum;

	private Integer orderNum;
	
	private Long authorId;
	
	private Long shoeColorId;
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getReleasePrice() {
		return releasePrice;
	}

	public void setReleasePrice(Double releasePrice) {
		this.releasePrice = releasePrice;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getEvaluationNum() {
		return evaluationNum;
	}

	public void setEvaluationNum(Integer evaluationNum) {
		this.evaluationNum = evaluationNum;
	}

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	public Integer getColorNum() {
		return colorNum;
	}

	public void setColorNum(Integer colorNum) {
		this.colorNum = colorNum;
	}

	public Integer getHotNum() {
		return hotNum;
	}

	public void setHotNum(Integer hotNum) {
		this.hotNum = hotNum;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getShoeColorId() {
		return shoeColorId;
	}

	public void setShoeColorId(Long shoeColorId) {
		this.shoeColorId = shoeColorId;
	}

	public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFollowId() {
        return followId;
    }

    public void setFollowId(Long followId) {
        this.followId = followId;
    }


    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}