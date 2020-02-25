package com.xnk.service.api.model;

import java.util.Date;

import com.xnk.service.entity.Entity;
/**
 */
public class WeiXinPay  extends Entity<Long, WeiXinPay>{

	private static final long serialVersionUID = 1L;

	private Long id;//微信宝宝所有编码
	
	/** 必选，自增长 */
	private Integer payId;
	/** 随机字符串 */
	private String nonceStr;
	/** 商品描述 */
	private String body;
	/** 商品描述 */
	private String detail;
	/** 必选，本订单的交易标识字段 */
	private String outTradeNo;
	/** 订单金额(分) */
	private String totalFee;
	/** 终端IP */
	private String spbillCreateIp;
	/** 交易起始时间 */
	private Integer timeStart;
	/** 交易结束时间 */
	private Integer timeExpire;
	/** 通知地址 */
	private String notifyUrl;
	/** 支付状态，0-预下单未支付 1-已支付 2-支付失败(取消、超时等) 3-退款 */
	private Integer status;
	/** 预下单成功ID */
	private String prepayId;
	/** 交易通信是否成功标志 success/fail */
	private String returnCode;
	/** 交易通信fail的信息 */
	private String returnMsg;
	/** 用户在商户appid下的唯一标识 */
	private String openid;
	/** 银行缩写 */
	private String bankType;
	/** 微信支付成功后 返回的transaction */
	private String transactionId;
	/** 支付完成时间 */
	private String timeEnd;
	/** 商户系统内部的退款单号 */
	private String outRefundNo;
	/** 退款金额(分) */
	private String refundFee;
	/** 微信退款单号 */
	private String refundId;
	protected Date createDate; // 创建日期
	protected Date modifiedDate; // 更新日期
	/**
	 * 获取必选，自增长
	 */
	public Integer getPayId() {
		return payId;
	}
	/**
	 * 设置必选，自增长
	 */
	public void setPayId(Integer payId) {
		this.payId = payId;
	}
	/**
	 * 获取随机字符串
	 */
	public String getNonceStr() {
		return nonceStr;
	}
	/**
	 * 设置随机字符串
	 */
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	/**
	 * 获取商品描述
	 */
	public String getBody() {
		return body;
	}
	/**
	 * 设置商品描述
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * 获取商品描述
	 */
	public String getDetail() {
		return detail;
	}
	/**
	 * 设置商品描述
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	/**
	 * 获取必选，本订单的交易标识字段
	 */
	public String getOutTradeNo() {
		return outTradeNo;
	}
	/**
	 * 设置必选，本订单的交易标识字段
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	/**
	 * 获取订单金额(分)
	 */
	public String getTotalFee() {
		return totalFee;
	}
	/**
	 * 设置订单金额(分)
	 */
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	/**
	 * 获取终端IP
	 */
	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}
	/**
	 * 设置终端IP
	 */
	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}
	/**
	 * 获取交易起始时间
	 */
	public Integer getTimeStart() {
		return timeStart;
	}
	/**
	 * 设置交易起始时间
	 */
	public void setTimeStart(Integer timeStart) {
		this.timeStart = timeStart;
	}
	/**
	 * 获取交易结束时间
	 */
	public Integer getTimeExpire() {
		return timeExpire;
	}
	/**
	 * 设置交易结束时间
	 */
	public void setTimeExpire(Integer timeExpire) {
		this.timeExpire = timeExpire;
	}
	/**
	 * 获取通知地址
	 */
	public String getNotifyUrl() {
		return notifyUrl;
	}
	/**
	 * 设置通知地址
	 */
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	/**
	 * 获取支付状态，0-预下单未支付 1-已支付 2-支付失败(取消、超时等) 3-退款
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置支付状态，0-预下单未支付 1-已支付 2-支付失败(取消、超时等) 3-退款
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取预下单成功ID
	 */
	public String getPrepayId() {
		return prepayId;
	}
	/**
	 * 设置预下单成功ID
	 */
	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}
	/**
	 * 获取交易通信是否成功标志 success/fail
	 */
	public String getReturnCode() {
		return returnCode;
	}
	/**
	 * 设置交易通信是否成功标志 success/fail
	 */
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	/**
	 * 获取交易通信fail的信息
	 */
	public String getReturnMsg() {
		return returnMsg;
	}
	/**
	 * 设置交易通信fail的信息
	 */
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	/**
	 * 获取用户在商户appid下的唯一标识
	 */
	public String getOpenid() {
		return openid;
	}
	/**
	 * 设置用户在商户appid下的唯一标识
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * 获取银行缩写
	 */
	public String getBankType() {
		return bankType;
	}
	/**
	 * 设置银行缩写
	 */
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	/**
	 * 获取微信支付成功后 返回的transaction
	 */
	public String getTransactionId() {
		return transactionId;
	}
	/**
	 * 设置微信支付成功后 返回的transaction
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	/**
	 * 获取支付完成时间
	 */
	public String getTimeEnd() {
		return timeEnd;
	}
	/**
	 * 设置支付完成时间
	 */
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	/**
	 * 获取商户系统内部的退款单号
	 */
	public String getOutRefundNo() {
		return outRefundNo;
	}
	/**
	 * 设置商户系统内部的退款单号
	 */
	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}
	/**
	 * 获取退款金额(分)
	 */
	public String getRefundFee() {
		return refundFee;
	}
	/**
	 * 设置退款金额(分)
	 */
	public void setRefundFee(String refundFee) {
		this.refundFee = refundFee;
	}
	/**
	 * 获取微信退款单号
	 */
	public String getRefundId() {
		return refundId;
	}
	/**
	 * 设置微信退款单号
	 */
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
}