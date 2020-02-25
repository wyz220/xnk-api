package com.xnk.service.api.vo;

import java.util.Date;

public class UserVo {

	private Long id;

	private Integer source;//注册来源
	
    private Double balance;//钱包余额

    private Date birthday;

    private Integer sex;

    private Integer status;

    private String address;

    private String name;

    private String nickname;

    private String password;

    private String icon;

    private String introduction;

    private String phone;

    private String email;

    private String openid;

    private String unionid;

    private String wxappid;

    private String provinceId;

    private String cityId;

    private String areaId;

    private Date createTime;
    
    private Integer evaluationReleaseCount;//测评数量

    private Integer evaluationAlreadyBuyCount;//已购

    private Integer evaluationSellCount;//卖出

    private Integer evaluationLikeCount;//想买

    private Integer myFollowUserCount;//我关注的人数

    private Integer myFollowFanCount;//我的粉丝

    private Integer myFollowShoeCount;//关注的鞋数量

    private Integer myFollowColorCount;//关注的配色
    
    public UserVo(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getWxappid() {
		return wxappid;
	}

	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
    
}
