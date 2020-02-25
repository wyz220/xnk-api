package com.xnk.service.api.model;

import java.util.Date;

import com.xnk.service.entity.Entity;

public class Order extends Entity<Long,Order>{
    /**
	 * 
	 */
	private static final long serialVersionUID = -419499572366241300L;

	private Long id;

    private Long userId;

    private Long evaluationId;

    private String nickName;

    private Integer type;

    private Integer childType;

    private Integer source;

    private String orderNo;

    private Integer status;

    private Double amount;

    private Double actualPayAmount;

    private Double discountAmount;

    private Integer payType;

    private Date payTime;

    private String mchtOrderNo;

    private String remark;

    private String payNoticeInfo;

    private Date createTime;

    private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(Long evaluationId) {
		this.evaluationId = evaluationId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getChildType() {
		return childType;
	}

	public void setChildType(Integer childType) {
		this.childType = childType;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getActualPayAmount() {
		return actualPayAmount;
	}

	public void setActualPayAmount(Double actualPayAmount) {
		this.actualPayAmount = actualPayAmount;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getMchtOrderNo() {
		return mchtOrderNo;
	}

	public void setMchtOrderNo(String mchtOrderNo) {
		this.mchtOrderNo = mchtOrderNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPayNoticeInfo() {
		return payNoticeInfo;
	}

	public void setPayNoticeInfo(String payNoticeInfo) {
		this.payNoticeInfo = payNoticeInfo;
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

	public enum OrderStatus {
		WAIT_PAY(0), PAYED_SUCCESS(1), PAYED_FAIL(2), APPLY_REFUND(3), REFUNDED(4), WAITING_RECEIVE_ORDER(
				5), RECEIVED_ORDER(6), ORDER_DELIVERING(7), ORDER_FINISHED(8), ORDER_CLOSED(9);

		private int status;

		private OrderStatus(int status) {
			this.status = status;
		}

		public int getStatus() {
			return status;
		}

		public static OrderStatus getStatus(int status) {
			switch (status) {
			case 0:
				return WAIT_PAY;
			case 1:
				return PAYED_SUCCESS;
			case 2:
				return PAYED_FAIL;
			case 3:
				return APPLY_REFUND;
			case 4:
				return REFUNDED;
			case 5:
				return WAITING_RECEIVE_ORDER;
			case 6:
				return RECEIVED_ORDER;
			case 7:
				return ORDER_DELIVERING;
			case 8:
				return ORDER_FINISHED;
			case 9:
				return ORDER_CLOSED;
			}
			return WAIT_PAY;
		}
	}
}