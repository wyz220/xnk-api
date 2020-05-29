package com.xnk.service.api.model;

import java.util.Date;

import com.xnk.service.entity.Entity;

public class UserTotal extends Entity<Long, UserTotal>{
    /**
	 * 
	 */
	private static final long serialVersionUID = -912906829143620822L;

	private Long id;

    private Long userId;//用户表示id

    private Integer evaluationReleaseCount;//测评数量

    private Integer evaluationAlreadyBuyCount;//已购

    private Integer evaluationSellCount;//卖出

    private Integer evaluationLikeCount;//想买

    private Integer myFollowUserCount;//我关注的人数

    private Integer myFollowFanCount;//我的粉丝

    private Integer myFollowShoeCount;//关注的鞋数量

    private Integer myFollowColorCount;//关注的配色

	private Integer getEvaRecomCount= 0;//测评获得的推荐

	private Integer getEvaGoodCount= 0;//测评获得的赞

	private Integer getEvaItemGoodCount= 0;//测评项获得的赞

	private Integer getEvaShareCount= 0;//测评被分享数

	//新增
    private Date createTime;

    private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getGetEvaRecomCount() {
		return getEvaRecomCount;
	}

	public void setGetEvaRecomCount(Integer getEvaRecomCount) {
		this.getEvaRecomCount = getEvaRecomCount;
	}

	public Integer getGetEvaGoodCount() {
		return getEvaGoodCount;
	}

	public void setGetEvaGoodCount(Integer getEvaGoodCount) {
		this.getEvaGoodCount = getEvaGoodCount;
	}

	public Integer getGetEvaItemGoodCount() {
		return getEvaItemGoodCount;
	}

	public void setGetEvaItemGoodCount(Integer getEvaItemGoodCount) {
		this.getEvaItemGoodCount = getEvaItemGoodCount;
	}

	public Integer getGetEvaShareCount() {
		return getEvaShareCount;
	}

	public void setGetEvaShareCount(Integer getEvaShareCount) {
		this.getEvaShareCount = getEvaShareCount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getEvaluationReleaseCount() {
		return evaluationReleaseCount;
	}

	public void setEvaluationReleaseCount(Integer evaluationReleaseCount) {
		this.evaluationReleaseCount = evaluationReleaseCount;
	}

	public Integer getEvaluationAlreadyBuyCount() {
		return evaluationAlreadyBuyCount;
	}

	public void setEvaluationAlreadyBuyCount(Integer evaluationAlreadyBuyCount) {
		this.evaluationAlreadyBuyCount = evaluationAlreadyBuyCount;
	}

	public Integer getEvaluationSellCount() {
		return evaluationSellCount;
	}

	public void setEvaluationSellCount(Integer evaluationSellCount) {
		this.evaluationSellCount = evaluationSellCount;
	}

	public Integer getEvaluationLikeCount() {
		return evaluationLikeCount;
	}

	public void setEvaluationLikeCount(Integer evaluationLikeCount) {
		this.evaluationLikeCount = evaluationLikeCount;
	}

	public Integer getMyFollowUserCount() {
		return myFollowUserCount;
	}

	public void setMyFollowUserCount(Integer myFollowUserCount) {
		this.myFollowUserCount = myFollowUserCount;
	}

	public Integer getMyFollowFanCount() {
		return myFollowFanCount;
	}

	public void setMyFollowFanCount(Integer myFollowFanCount) {
		this.myFollowFanCount = myFollowFanCount;
	}

	public Integer getMyFollowShoeCount() {
		return myFollowShoeCount;
	}

	public void setMyFollowShoeCount(Integer myFollowShoeCount) {
		this.myFollowShoeCount = myFollowShoeCount;
	}

	public Integer getMyFollowColorCount() {
		return myFollowColorCount;
	}

	public void setMyFollowColorCount(Integer myFollowColorCount) {
		this.myFollowColorCount = myFollowColorCount;
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