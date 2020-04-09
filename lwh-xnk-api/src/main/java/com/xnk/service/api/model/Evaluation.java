package com.xnk.service.api.model;

import java.util.Date;

import com.xnk.service.entity.Entity;

/**
 * 测评详细信息
 * @author msht
 *
 */
public class Evaluation extends Entity<Long, Evaluation> {

	/**age
	 * 
	 */
	private static final long serialVersionUID = -8319208287159835265L;

	private Long id;

	private Long userId;

	private String userName;

	private String evaluationUserImg;//发布测评用户头像
	
	private String evaluationShoeType;

	private String postion;

	private String halfCourt;

	private String stackNoBallState;

	private String dribbleUse;

	private String shangLanUse;

	private String touLanUse;

	private String attack;

	private String defend;

	private String nutCap;

	private String basketballPlay;

	private Integer gender;

	private Integer age;

	private Date birthday;

	private Double height;

	private Integer weight;

	private String footType;

	private String footImgUrl;

	private String boundImgUrl;

	private String boundType;

	private String instepType;

	private String instepImgUrl;

	private Long boundFeetFileId;

	private Long footTypeFileId;

	private Long instepFileId;

	private String physicalFunction;

	private Long physicalFunctionFileId;

	private String shoeType;

	private Long shoeTypeId;

	private String brandName;

	private Long brandId;

	private String shoeName;

	private Long shoeId;

	private String colorName;//配色名称
	
	private Long colorId;//配色
	
	private String size;//尺码
	
	private String isPartialCode;

	private Long isPartialId;

	private String shoeSizeName;

	private Long shoeSizeId;

	private String sockType;

	private Long sockTypeId;

	private String fieldType;

	private Long fieldTypeId;

	private String firstFootFeelContent;

	private String afterUseFootFeelContent;

	private String footFeelImgUrl;

	private Integer appreanceLevel;

	private String appreanceImgUrl;

	private String appreanceShoeEvaluate;

	private String appreanceColorEvaluate;

	private String appreanceInfluence;

	private Integer workLevel;

	private String workImgUrl;

	private String workFengXian;

	private String workJiaoShui;

	private Integer packageLevel;

	private String packageImgUrl;

	private String packageFirst;

	private String packageMiddle;

	private String packageAfter;

	private Integer zhiChengLevel;

	private String zhiChengImgUrl;

	private String zhiChengMianFirst;

	private String zhiChengMianMiddle;

	private String zhiChengMianAfter;

	private String zhiChengBottomFirst;

	private String zhiChengBottomMiddle;

	private String zhiChengBottomAfter;

	private Integer flexLevel;

	private String flexImgUrl;

	private String flexShoeMianBottom;

	private String flexShoeMian;

	private String flexShoeBottom;

	private Integer touqiLevel;

	private String touqiImgUrl;

	private String touqiContent;

	private Integer zhuadiliLevel;

	private String zhuadiliImgUrl;

	private String zhuadiliContent;

	private Integer naimoLevel;

	private String naimoImgUrl;

	private String naimoContent;

	private Integer huanzhenLevel;

	private String huanzhenImgUrl;

	private String huanzhenContent;

	private String huanzhenFirst;

	private String huanzhenAfter;

	private String summary;

	private String summaryImgUrl;

	private String evaluationImgUrl;

	private String title;

	private String introduction;

	private Date evaluationDate;

	private Integer status;// -1 待编辑  0待审核 1审核成功 2已下架

	private Integer orderNum;//订阅数

	private Integer recommNum;//推荐人数  点击人数
	
	private Integer recommWriteSeqNum;//写推荐序人数  相当于点击了推荐人数

	private Integer hotNum;

	private String remark;

	private Date createDate;

	private Date modifyDate;

	private Integer footFeelCommentNum;//脚感磨合评论数

    private Integer footFeelGoodNum;//脚感磨合点赞数

    private Integer appreanceCommentNum;//外观设计评论数

    private Integer appreanceGoodNum;//外观设计点赞数

    private Integer workCommentNum;

    private Integer workGoodNum;

    private Integer packageCommentNum;

    private Integer packageGoodNum;

    private Integer zhiChengCommentNum;//支撑

    private Integer zhiChengGoodNum;

    private Integer flexCommentNum;//灵活

    private Integer flexGoodNum;

    private Integer touqiCommentNum;//透气

    private Integer touqiGoodNum;

    private Integer zhuadiliCommentNum;//抓地力

    private Integer zhuadiliGoodNum;

    private Integer naimoCommentNum;

    private Integer naimoGoodNum;

    private Integer huanzhenCommentNum;

    private Integer huanzhenGoodNum;

    private Integer zhuijiaNum;
    
	private Integer isBuy;//1已购买 0未购买
	
	private Integer isNeed;//是否想买 1想买 

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEvaluationUserImg() {
		return evaluationUserImg;
	}

	public void setEvaluationUserImg(String evaluationUserImg) {
		this.evaluationUserImg = evaluationUserImg;
	}

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

	public Integer getRecommWriteSeqNum() {
		return recommWriteSeqNum;
	}

	public void setRecommWriteSeqNum(Integer recommWriteSeqNum) {
		this.recommWriteSeqNum = recommWriteSeqNum;
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

	public String getEvaluationShoeType() {
		return evaluationShoeType;
	}

	public void setEvaluationShoeType(String evaluationShoeType) {
		this.evaluationShoeType = evaluationShoeType == null ? null : evaluationShoeType.trim();
	}

	public String getPostion() {
		return postion;
	}

	public void setPostion(String postion) {
		this.postion = postion == null ? null : postion.trim();
	}

	public String getHalfCourt() {
		return halfCourt;
	}

	public void setHalfCourt(String halfCourt) {
		this.halfCourt = halfCourt == null ? null : halfCourt.trim();
	}

	
	public Integer getFootFeelCommentNum() {
		return footFeelCommentNum;
	}

	public void setFootFeelCommentNum(Integer footFeelCommentNum) {
		this.footFeelCommentNum = footFeelCommentNum;
	}

	public Integer getFootFeelGoodNum() {
		return footFeelGoodNum;
	}

	public void setFootFeelGoodNum(Integer footFeelGoodNum) {
		this.footFeelGoodNum = footFeelGoodNum;
	}

	public Integer getAppreanceCommentNum() {
		return appreanceCommentNum;
	}

	public void setAppreanceCommentNum(Integer appreanceCommentNum) {
		this.appreanceCommentNum = appreanceCommentNum;
	}

	public Integer getAppreanceGoodNum() {
		return appreanceGoodNum;
	}

	public void setAppreanceGoodNum(Integer appreanceGoodNum) {
		this.appreanceGoodNum = appreanceGoodNum;
	}

	public Integer getWorkCommentNum() {
		return workCommentNum;
	}

	public void setWorkCommentNum(Integer workCommentNum) {
		this.workCommentNum = workCommentNum;
	}

	public Integer getWorkGoodNum() {
		return workGoodNum;
	}

	public void setWorkGoodNum(Integer workGoodNum) {
		this.workGoodNum = workGoodNum;
	}

	public Integer getPackageCommentNum() {
		return packageCommentNum;
	}

	public void setPackageCommentNum(Integer packageCommentNum) {
		this.packageCommentNum = packageCommentNum;
	}

	public Integer getPackageGoodNum() {
		return packageGoodNum;
	}

	public void setPackageGoodNum(Integer packageGoodNum) {
		this.packageGoodNum = packageGoodNum;
	}

	public Integer getZhiChengCommentNum() {
		return zhiChengCommentNum;
	}

	public void setZhiChengCommentNum(Integer zhiChengCommentNum) {
		this.zhiChengCommentNum = zhiChengCommentNum;
	}

	public Integer getZhiChengGoodNum() {
		return zhiChengGoodNum;
	}

	public void setZhiChengGoodNum(Integer zhiChengGoodNum) {
		this.zhiChengGoodNum = zhiChengGoodNum;
	}

	public Integer getFlexCommentNum() {
		return flexCommentNum;
	}

	public void setFlexCommentNum(Integer flexCommentNum) {
		this.flexCommentNum = flexCommentNum;
	}

	public Integer getFlexGoodNum() {
		return flexGoodNum;
	}

	public void setFlexGoodNum(Integer flexGoodNum) {
		this.flexGoodNum = flexGoodNum;
	}

	public Integer getTouqiCommentNum() {
		return touqiCommentNum;
	}

	public void setTouqiCommentNum(Integer touqiCommentNum) {
		this.touqiCommentNum = touqiCommentNum;
	}

	public Integer getTouqiGoodNum() {
		return touqiGoodNum;
	}

	public void setTouqiGoodNum(Integer touqiGoodNum) {
		this.touqiGoodNum = touqiGoodNum;
	}

	public Integer getZhuadiliCommentNum() {
		return zhuadiliCommentNum;
	}

	public void setZhuadiliCommentNum(Integer zhuadiliCommentNum) {
		this.zhuadiliCommentNum = zhuadiliCommentNum;
	}

	public Integer getZhuadiliGoodNum() {
		return zhuadiliGoodNum;
	}

	public void setZhuadiliGoodNum(Integer zhuadiliGoodNum) {
		this.zhuadiliGoodNum = zhuadiliGoodNum;
	}

	public Integer getNaimoCommentNum() {
		return naimoCommentNum;
	}

	public void setNaimoCommentNum(Integer naimoCommentNum) {
		this.naimoCommentNum = naimoCommentNum;
	}

	public Integer getNaimoGoodNum() {
		return naimoGoodNum;
	}

	public void setNaimoGoodNum(Integer naimoGoodNum) {
		this.naimoGoodNum = naimoGoodNum;
	}

	public Integer getHuanzhenCommentNum() {
		return huanzhenCommentNum;
	}

	public void setHuanzhenCommentNum(Integer huanzhenCommentNum) {
		this.huanzhenCommentNum = huanzhenCommentNum;
	}

	public Integer getHuanzhenGoodNum() {
		return huanzhenGoodNum;
	}

	public void setHuanzhenGoodNum(Integer huanzhenGoodNum) {
		this.huanzhenGoodNum = huanzhenGoodNum;
	}

	public Integer getZhuijiaNum() {
		return zhuijiaNum;
	}

	public void setZhuijiaNum(Integer zhuijiaNum) {
		this.zhuijiaNum = zhuijiaNum;
	}

	public String getStackNoBallState() {
		return stackNoBallState;
	}

	public void setStackNoBallState(String stackNoBallState) {
		this.stackNoBallState = stackNoBallState == null ? null : stackNoBallState.trim();
	}

	public String getDribbleUse() {
		return dribbleUse;
	}

	public void setDribbleUse(String dribbleUse) {
		this.dribbleUse = dribbleUse == null ? null : dribbleUse.trim();
	}

	public String getShangLanUse() {
		return shangLanUse;
	}

	public void setShangLanUse(String shangLanUse) {
		this.shangLanUse = shangLanUse == null ? null : shangLanUse.trim();
	}

	public String getTouLanUse() {
		return touLanUse;
	}

	public void setTouLanUse(String touLanUse) {
		this.touLanUse = touLanUse == null ? null : touLanUse.trim();
	}

	public String getAttack() {
		return attack;
	}

	public void setAttack(String attack) {
		this.attack = attack == null ? null : attack.trim();
	}

	public String getDefend() {
		return defend;
	}

	public void setDefend(String defend) {
		this.defend = defend == null ? null : defend.trim();
	}

	public String getNutCap() {
		return nutCap;
	}

	public void setNutCap(String nutCap) {
		this.nutCap = nutCap == null ? null : nutCap.trim();
	}

	public String getBasketballPlay() {
		return basketballPlay;
	}

	public void setBasketballPlay(String basketballPlay) {
		this.basketballPlay = basketballPlay == null ? null : basketballPlay.trim();
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getFootType() {
		return footType;
	}

	public void setFootType(String footType) {
		this.footType = footType == null ? null : footType.trim();
	}

	public String getFootImgUrl() {
		return footImgUrl;
	}

	public void setFootImgUrl(String footImgUrl) {
		this.footImgUrl = footImgUrl == null ? null : footImgUrl.trim();
	}

	public String getBoundImgUrl() {
		return boundImgUrl;
	}

	public void setBoundImgUrl(String boundImgUrl) {
		this.boundImgUrl = boundImgUrl == null ? null : boundImgUrl.trim();
	}

	public String getBoundType() {
		return boundType;
	}

	public void setBoundType(String boundType) {
		this.boundType = boundType == null ? null : boundType.trim();
	}

	public String getInstepType() {
		return instepType;
	}

	public void setInstepType(String instepType) {
		this.instepType = instepType == null ? null : instepType.trim();
	}

	public String getInstepImgUrl() {
		return instepImgUrl;
	}

	public void setInstepImgUrl(String instepImgUrl) {
		this.instepImgUrl = instepImgUrl == null ? null : instepImgUrl.trim();
	}

	public Long getBoundFeetFileId() {
		return boundFeetFileId;
	}

	public void setBoundFeetFileId(Long boundFeetFileId) {
		this.boundFeetFileId = boundFeetFileId;
	}

	public Long getFootTypeFileId() {
		return footTypeFileId;
	}

	public void setFootTypeFileId(Long footTypeFileId) {
		this.footTypeFileId = footTypeFileId;
	}

	public Long getInstepFileId() {
		return instepFileId;
	}

	public void setInstepFileId(Long instepFileId) {
		this.instepFileId = instepFileId;
	}

	public String getPhysicalFunction() {
		return physicalFunction;
	}

	public void setPhysicalFunction(String physicalFunction) {
		this.physicalFunction = physicalFunction == null ? null : physicalFunction.trim();
	}

	public Long getPhysicalFunctionFileId() {
		return physicalFunctionFileId;
	}

	public void setPhysicalFunctionFileId(Long physicalFunctionFileId) {
		this.physicalFunctionFileId = physicalFunctionFileId;
	}

	public String getShoeType() {
		return shoeType;
	}

	public void setShoeType(String shoeType) {
		this.shoeType = shoeType == null ? null : shoeType.trim();
	}

	public Long getShoeTypeId() {
		return shoeTypeId;
	}

	public void setShoeTypeId(Long shoeTypeId) {
		this.shoeTypeId = shoeTypeId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName == null ? null : brandName.trim();
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getShoeName() {
		return shoeName;
	}

	public void setShoeName(String shoeName) {
		this.shoeName = shoeName == null ? null : shoeName.trim();
	}

	public Long getShoeId() {
		return shoeId;
	}

	public void setShoeId(Long shoeId) {
		this.shoeId = shoeId;
	}

	public String getIsPartialCode() {
		return isPartialCode;
	}

	public void setIsPartialCode(String isPartialCode) {
		this.isPartialCode = isPartialCode == null ? null : isPartialCode.trim();
	}

	public Long getIsPartialId() {
		return isPartialId;
	}

	public void setIsPartialId(Long isPartialId) {
		this.isPartialId = isPartialId;
	}

	public String getShoeSizeName() {
		return shoeSizeName;
	}

	public void setShoeSizeName(String shoeSizeName) {
		this.shoeSizeName = shoeSizeName == null ? null : shoeSizeName.trim();
	}

	public Long getShoeSizeId() {
		return shoeSizeId;
	}

	public void setShoeSizeId(Long shoeSizeId) {
		this.shoeSizeId = shoeSizeId;
	}

	public String getSockType() {
		return sockType;
	}

	public void setSockType(String sockType) {
		this.sockType = sockType == null ? null : sockType.trim();
	}

	public Long getSockTypeId() {
		return sockTypeId;
	}

	public void setSockTypeId(Long sockTypeId) {
		this.sockTypeId = sockTypeId;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType == null ? null : fieldType.trim();
	}

	public Long getFieldTypeId() {
		return fieldTypeId;
	}

	public void setFieldTypeId(Long fieldTypeId) {
		this.fieldTypeId = fieldTypeId;
	}

	public String getFirstFootFeelContent() {
		return firstFootFeelContent;
	}

	public void setFirstFootFeelContent(String firstFootFeelContent) {
		this.firstFootFeelContent = firstFootFeelContent == null ? null : firstFootFeelContent.trim();
	}

	public String getAfterUseFootFeelContent() {
		return afterUseFootFeelContent;
	}

	public void setAfterUseFootFeelContent(String afterUseFootFeelContent) {
		this.afterUseFootFeelContent = afterUseFootFeelContent == null ? null : afterUseFootFeelContent.trim();
	}

	public Integer getAppreanceLevel() {
		return appreanceLevel;
	}

	public void setAppreanceLevel(Integer appreanceLevel) {
		this.appreanceLevel = appreanceLevel;
	}

	public String getAppreanceImgUrl() {
		return appreanceImgUrl;
	}

	public void setAppreanceImgUrl(String appreanceImgUrl) {
		this.appreanceImgUrl = appreanceImgUrl == null ? null : appreanceImgUrl.trim();
	}

	public String getAppreanceShoeEvaluate() {
		return appreanceShoeEvaluate;
	}

	public void setAppreanceShoeEvaluate(String appreanceShoeEvaluate) {
		this.appreanceShoeEvaluate = appreanceShoeEvaluate == null ? null : appreanceShoeEvaluate.trim();
	}

	public String getAppreanceColorEvaluate() {
		return appreanceColorEvaluate;
	}

	public void setAppreanceColorEvaluate(String appreanceColorEvaluate) {
		this.appreanceColorEvaluate = appreanceColorEvaluate == null ? null : appreanceColorEvaluate.trim();
	}

	public String getAppreanceInfluence() {
		return appreanceInfluence;
	}

	public void setAppreanceInfluence(String appreanceInfluence) {
		this.appreanceInfluence = appreanceInfluence == null ? null : appreanceInfluence.trim();
	}

	public Integer getWorkLevel() {
		return workLevel;
	}

	public void setWorkLevel(Integer workLevel) {
		this.workLevel = workLevel;
	}

	public String getWorkImgUrl() {
		return workImgUrl;
	}

	public void setWorkImgUrl(String workImgUrl) {
		this.workImgUrl = workImgUrl == null ? null : workImgUrl.trim();
	}

	public String getWorkFengXian() {
		return workFengXian;
	}

	public void setWorkFengXian(String workFengXian) {
		this.workFengXian = workFengXian == null ? null : workFengXian.trim();
	}

	public String getWorkJiaoShui() {
		return workJiaoShui;
	}

	public void setWorkJiaoShui(String workJiaoShui) {
		this.workJiaoShui = workJiaoShui == null ? null : workJiaoShui.trim();
	}

	public Integer getPackageLevel() {
		return packageLevel;
	}

	public void setPackageLevel(Integer packageLevel) {
		this.packageLevel = packageLevel;
	}

	public String getPackageImgUrl() {
		return packageImgUrl;
	}

	public void setPackageImgUrl(String packageImgUrl) {
		this.packageImgUrl = packageImgUrl == null ? null : packageImgUrl.trim();
	}

	public String getPackageFirst() {
		return packageFirst;
	}

	public void setPackageFirst(String packageFirst) {
		this.packageFirst = packageFirst == null ? null : packageFirst.trim();
	}

	public String getPackageMiddle() {
		return packageMiddle;
	}

	public void setPackageMiddle(String packageMiddle) {
		this.packageMiddle = packageMiddle == null ? null : packageMiddle.trim();
	}

	public String getPackageAfter() {
		return packageAfter;
	}

	public void setPackageAfter(String packageAfter) {
		this.packageAfter = packageAfter == null ? null : packageAfter.trim();
	}

	public Integer getZhiChengLevel() {
		return zhiChengLevel;
	}

	public void setZhiChengLevel(Integer zhiChengLevel) {
		this.zhiChengLevel = zhiChengLevel;
	}

	public String getZhiChengImgUrl() {
		return zhiChengImgUrl;
	}

	public void setZhiChengImgUrl(String zhiChengImgUrl) {
		this.zhiChengImgUrl = zhiChengImgUrl == null ? null : zhiChengImgUrl.trim();
	}

	public String getZhiChengMianFirst() {
		return zhiChengMianFirst;
	}

	public void setZhiChengMianFirst(String zhiChengMianFirst) {
		this.zhiChengMianFirst = zhiChengMianFirst == null ? null : zhiChengMianFirst.trim();
	}

	public String getZhiChengMianMiddle() {
		return zhiChengMianMiddle;
	}

	public void setZhiChengMianMiddle(String zhiChengMianMiddle) {
		this.zhiChengMianMiddle = zhiChengMianMiddle == null ? null : zhiChengMianMiddle.trim();
	}

	public String getZhiChengMianAfter() {
		return zhiChengMianAfter;
	}

	public void setZhiChengMianAfter(String zhiChengMianAfter) {
		this.zhiChengMianAfter = zhiChengMianAfter == null ? null : zhiChengMianAfter.trim();
	}

	public String getZhiChengBottomFirst() {
		return zhiChengBottomFirst;
	}

	public void setZhiChengBottomFirst(String zhiChengBottomFirst) {
		this.zhiChengBottomFirst = zhiChengBottomFirst == null ? null : zhiChengBottomFirst.trim();
	}

	public String getZhiChengBottomMiddle() {
		return zhiChengBottomMiddle;
	}

	public void setZhiChengBottomMiddle(String zhiChengBottomMiddle) {
		this.zhiChengBottomMiddle = zhiChengBottomMiddle == null ? null : zhiChengBottomMiddle.trim();
	}

	public String getZhiChengBottomAfter() {
		return zhiChengBottomAfter;
	}

	public void setZhiChengBottomAfter(String zhiChengBottomAfter) {
		this.zhiChengBottomAfter = zhiChengBottomAfter == null ? null : zhiChengBottomAfter.trim();
	}

	public Integer getFlexLevel() {
		return flexLevel;
	}

	public void setFlexLevel(Integer flexLevel) {
		this.flexLevel = flexLevel;
	}

	public String getFlexImgUrl() {
		return flexImgUrl;
	}

	public void setFlexImgUrl(String flexImgUrl) {
		this.flexImgUrl = flexImgUrl == null ? null : flexImgUrl.trim();
	}

	public String getFlexShoeMianBottom() {
		return flexShoeMianBottom;
	}

	public void setFlexShoeMianBottom(String flexShoeMianBottom) {
		this.flexShoeMianBottom = flexShoeMianBottom == null ? null : flexShoeMianBottom.trim();
	}

	public String getFlexShoeMian() {
		return flexShoeMian;
	}

	public void setFlexShoeMian(String flexShoeMian) {
		this.flexShoeMian = flexShoeMian == null ? null : flexShoeMian.trim();
	}

	public String getFlexShoeBottom() {
		return flexShoeBottom;
	}

	public void setFlexShoeBottom(String flexShoeBottom) {
		this.flexShoeBottom = flexShoeBottom == null ? null : flexShoeBottom.trim();
	}

	public Integer getTouqiLevel() {
		return touqiLevel;
	}

	public void setTouqiLevel(Integer touqiLevel) {
		this.touqiLevel = touqiLevel;
	}

	public String getTouqiImgUrl() {
		return touqiImgUrl;
	}

	public void setTouqiImgUrl(String touqiImgUrl) {
		this.touqiImgUrl = touqiImgUrl == null ? null : touqiImgUrl.trim();
	}

	public String getTouqiContent() {
		return touqiContent;
	}

	public void setTouqiContent(String touqiContent) {
		this.touqiContent = touqiContent == null ? null : touqiContent.trim();
	}

	public Integer getZhuadiliLevel() {
		return zhuadiliLevel;
	}

	public void setZhuadiliLevel(Integer zhuadiliLevel) {
		this.zhuadiliLevel = zhuadiliLevel;
	}

	public String getZhuadiliImgUrl() {
		return zhuadiliImgUrl;
	}

	public void setZhuadiliImgUrl(String zhuadiliImgUrl) {
		this.zhuadiliImgUrl = zhuadiliImgUrl == null ? null : zhuadiliImgUrl.trim();
	}

	public String getZhuadiliContent() {
		return zhuadiliContent;
	}

	public void setZhuadiliContent(String zhuadiliContent) {
		this.zhuadiliContent = zhuadiliContent == null ? null : zhuadiliContent.trim();
	}

	public Integer getNaimoLevel() {
		return naimoLevel;
	}

	public void setNaimoLevel(Integer naimoLevel) {
		this.naimoLevel = naimoLevel;
	}

	public String getNaimoImgUrl() {
		return naimoImgUrl;
	}

	public void setNaimoImgUrl(String naimoImgUrl) {
		this.naimoImgUrl = naimoImgUrl == null ? null : naimoImgUrl.trim();
	}

	public String getNaimoContent() {
		return naimoContent;
	}

	public void setNaimoContent(String naimoContent) {
		this.naimoContent = naimoContent == null ? null : naimoContent.trim();
	}

	public Integer getHuanzhenLevel() {
		return huanzhenLevel;
	}

	public void setHuanzhenLevel(Integer huanzhenLevel) {
		this.huanzhenLevel = huanzhenLevel;
	}

	public String getHuanzhenImgUrl() {
		return huanzhenImgUrl;
	}

	public void setHuanzhenImgUrl(String huanzhenImgUrl) {
		this.huanzhenImgUrl = huanzhenImgUrl == null ? null : huanzhenImgUrl.trim();
	}

	public String getHuanzhenContent() {
		return huanzhenContent;
	}

	public void setHuanzhenContent(String huanzhenContent) {
		this.huanzhenContent = huanzhenContent == null ? null : huanzhenContent.trim();
	}

	public String getHuanzhenFirst() {
		return huanzhenFirst;
	}

	public void setHuanzhenFirst(String huanzhenFirst) {
		this.huanzhenFirst = huanzhenFirst == null ? null : huanzhenFirst.trim();
	}

	public String getHuanzhenAfter() {
		return huanzhenAfter;
	}

	public void setHuanzhenAfter(String huanzhenAfter) {
		this.huanzhenAfter = huanzhenAfter == null ? null : huanzhenAfter.trim();
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary == null ? null : summary.trim();
	}

	public String getSummaryImgUrl() {
		return summaryImgUrl;
	}

	public void setSummaryImgUrl(String summaryImgUrl) {
		this.summaryImgUrl = summaryImgUrl == null ? null : summaryImgUrl.trim();
	}

	public String getEvaluationImgUrl() {
		return evaluationImgUrl;
	}

	public void setEvaluationImgUrl(String evaluationImgUrl) {
		this.evaluationImgUrl = evaluationImgUrl == null ? null : evaluationImgUrl.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction == null ? null : introduction.trim();
	}

	public Date getEvaluationDate() {
		return evaluationDate;
	}

	public void setEvaluationDate(Date evaluationDate) {
		this.evaluationDate = evaluationDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
		this.remark = remark == null ? null : remark.trim();
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

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public Long getColorId() {
		return colorId;
	}

	public void setColorId(Long colorId) {
		this.colorId = colorId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFootFeelImgUrl() {
		return footFeelImgUrl;
	}

	public void setFootFeelImgUrl(String footFeelImgUrl) {
		this.footFeelImgUrl = footFeelImgUrl;
	}
}