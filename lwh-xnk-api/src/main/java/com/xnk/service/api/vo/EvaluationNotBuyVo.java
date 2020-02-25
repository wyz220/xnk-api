package com.xnk.service.api.vo;

import java.util.Date;

import com.xnk.service.entity.Entity;

/**
 * 测评详细信息
 * @author msht
 *
 */
public class EvaluationNotBuyVo extends Entity<Long, EvaluationNotBuyVo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8319208287159835265L;

	private Long id;

	private Long userId;

	private String userName;

	private String evaluationImgUrl;

	private String title;

	private String introduction;

	private Date evaluationDate;

	private Integer orderNum;//订阅数

	private Integer recommNum;//推荐人数  点击人数
	
	private Integer recommWriteSeqNum;//写推荐序人数  相当于点击了推荐人数

	private Integer hotNum;

	private String remark;

	private Date createDate;

	private Date modifyDate;

	private Integer isBuy;//1已购买 0未购买
	
	private Integer isNeed;//是否想买 1想买 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIsBuy() {
		return isBuy;
	}

	public void setIsBuy(Integer isBuy) {
		this.isBuy = isBuy;
	}

	public Integer getIsNeed() {
		return isNeed;
	}

	public void setIsNeed(Integer isNeed) {
		this.isNeed = isNeed;
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
		this.userName = userName;
	}

	public String getEvaluationImgUrl() {
		return evaluationImgUrl;
	}

	public void setEvaluationImgUrl(String evaluationImgUrl) {
		this.evaluationImgUrl = evaluationImgUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Date getEvaluationDate() {
		return evaluationDate;
	}

	public void setEvaluationDate(Date evaluationDate) {
		this.evaluationDate = evaluationDate;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getRecommNum() {
		return recommNum;
	}

	public void setRecommNum(Integer recommNum) {
		this.recommNum = recommNum;
	}

	public Integer getRecommWriteSeqNum() {
		return recommWriteSeqNum;
	}

	public void setRecommWriteSeqNum(Integer recommWriteSeqNum) {
		this.recommWriteSeqNum = recommWriteSeqNum;
	}

	public Integer getHotNum() {
		return hotNum;
	}

	public void setHotNum(Integer hotNum) {
		this.hotNum = hotNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	
}