package com.xnk.service.api.model;

import java.util.Date;

import com.xnk.service.entity.Entity;

public class Shoe extends Entity<Long,Shoe> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3212816143022153051L;

	private Long shoeId;

    private String fileId;

    private Long brandId;
    
    private String name;//名称

    private Integer year;//发售年份

    private Double releasePrice;//发售价格

    private String sn;//货号

    private Integer state;//启用  0封停

    private Date modifyDate;

    private Date createDate;

    private Integer shoeType;//篮球鞋  跑鞋
    
    private String shoeTypeName;//篮球鞋  跑鞋

    
    private boolean isCheck;

    /**
     * 作者id
     */
    private Long authorId;

    private Integer hotNum = 0;//粉丝数量
    private Integer userNum = 0;//粉丝数量
    private Integer colorNum = 0;//配色数量 后台审核通过
    private Integer evaluationNum = 0;//测评数量  后台审核通过
    private Integer orderNum = 0;//订单数量
    
    private Integer status;//0用户未关注  1用户已关注
	public Long getShoeId() {
		return shoeId;
	}

	public void setShoeId(Long shoeId) {
		this.shoeId = shoeId;
	}
	
	
	public Integer getHotNum() {
		return hotNum;
	}

	public void setHotNum(Integer hotNum) {
		this.hotNum = hotNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}
	

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Integer getColorNum() {
		return colorNum;
	}

	public void setColorNum(Integer colorNum) {
		this.colorNum = colorNum;
	}

	public Integer getEvaluationNum() {
		return evaluationNum;
	}

	public void setEvaluationNum(Integer evaluationNum) {
		this.evaluationNum = evaluationNum;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public Integer getShoeType() {
		return shoeType;
	}

	public void setShoeType(Integer shoeType) {
		this.shoeType = shoeType;
	}

	public String getShoeTypeName() {
		return shoeTypeName;
	}

	public void setShoeTypeName(String shoeTypeName) {
		this.shoeTypeName = shoeTypeName;
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
   
}