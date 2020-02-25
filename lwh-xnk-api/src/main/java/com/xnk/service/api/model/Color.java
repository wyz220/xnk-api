package com.xnk.service.api.model;

import java.util.Date;

import com.xnk.service.entity.Entity;

public class Color extends Entity<Long, Color> {

	/**
	 * 配色
	 */
	private static final long serialVersionUID = -6748647142509960080L;

	private Long colorId;

	private String fileId;

	private Long shoeId;

	private String name;

	private Double releasePrice;

	private String sn;

	private Integer year;

	private Integer state;

	private Integer evaluationNum;

	private Integer userNum;

	private Integer colorNum;

	private Integer hotNum;

	private Integer orderNum;
	
	private Date modifyDate;

	private Date createDate;

	private Long authorId;

    private Integer shoeType;//篮球鞋  跑鞋
	    
    private String shoeTypeName;//篮球鞋  跑鞋
    
    private Integer status;//0用户未关注  1用户已关注
    
	public Long getColorId() {
		return colorId;
	}

	public void setColorId(Long colorId) {
		this.colorId = colorId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Long getShoeId() {
		return shoeId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getShoeType() {
		return shoeType;
	}

	public void setShoeType(Integer shoeType) {
		this.shoeType = shoeType;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getShoeTypeName() {
		return shoeTypeName;
	}

	public void setShoeTypeName(String shoeTypeName) {
		this.shoeTypeName = shoeTypeName;
	}

	public void setShoeId(Long shoeId) {
		this.shoeId = shoeId;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

}
