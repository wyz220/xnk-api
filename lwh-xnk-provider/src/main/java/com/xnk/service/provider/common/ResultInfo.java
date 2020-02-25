package com.xnk.service.provider.common;

public class ResultInfo {
	
	public static final String SUCCESS = "0000";

	private String code;

	private String message;

	public ResultInfo() {
		this.code = SUCCESS;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public enum ResultCode {
		SUCCESS("0000", "操作成功"), FAILED("0001", "操作失败"), NOT_LOGIN_ERROR("0002", "用户没有登录"), FILE_TYPE_ERROR("0003",
				"文件类型错误"), FILE_HEAD_FORMAT_ERROR("0004", "文件头部格式不正确"), INVALID_PARAM("0005",
						"参数无效"), INVALID_STATUS("0006", "无效状态"), INVALID_USERNAME("0007", "用户名或者密码错误")
		,INVALID_SIGN("0008", "无效签名"),INVALID_ACCOUNT("0009", "无效账号"),INVALID_EQUIPMENT("0010", "无效设备")
		,BALANCE_NOT_ENOUGH("0011", "余额不足"),INVALID_PHONE_NUMBER("0012","无效手机号码"),USER_ALREADY_EXISTS("0013","用户已经存在")
		,INVALID_PWD_FORMAT("0014", "密码格式不正确"),INVALID_VERIFY_CODE("0015","无效验证码"),INVALID_AMOUNT("0016","无效金额")
		,CONTENT_LENGTH_TOO_LONG("0017","内容长度超过限制"),REPEAT_SUBMITED("0018","重复提交"),CARD_IS_LOSTED("0019","该卡号已经挂失"),
		REPEAT_BIND_ACCOUNT("0020","重复绑定账号"),NO_BINDED_ACCOUNT("0021","没有绑定账号错误"),BIND_SAME_ACCOUNT("0022","被绑定账户不能和绑定账户相同")
		,IS_FLOW_CARD("0023","该会员已经是大流量用户"),IS_ALREADY_ACTIVE("0024","这个卡号已经激活，请使用新卡")
		
		//订单
		,INVALID_ORDER("1001","无效订单"),ORDER_AMOUNT_INCONSISTENT("1002","订单金额不一致"),PAY_ACCOUNT_IS_EMPTY("1003","支付账号为空")
		,REPEAT_PAID("1004","重复支付"),PROMOTION_ACTIVITY_END("1005","促销活动已经结束"),INVALID_ORDER_STATUS("1006","无效订单状态"),
		AMOUNT_EXCEEDING_LIMIT("1007","金额超过最大限制"),EQUIP_NETWORK_EXCEPTION("1008","设备网络异常"),NOT_SELL_WATER("1009","不可售水")
		,NOT_FOUND_ORDER("1010","未能找到相关订单"),INVALID_PACKAGE("1011","无效套餐"),INVALID_ACTIVITY("1012","无效活动"),
		INVALID_COUPON("1013","无效优惠券"),INVALID_FLOW_CARD_GIVE_MONEY("1014","无效的赠送金额")

		//物业
		,BANKCARD_NOT_AUDIT("2001","银行卡未审核"),AUDIT_RECORD_EXISTS("2002","审核记录已经存在")
		,GET_WATER_FEE_NOT_ENOUGH("2003","取水金额不足"), REPEAT_WITHDRAWAL("2004","您的提现正在申请中，请不要重复提现"),

        //活动
		HAS_GET_GIFTS("3001","已经领取过礼品"),EXCEEDING_BUY_QUANTITY_LIMIT("3002","超过购买次数限制"),
		
		//巡检
		INVALID_WATERMETER("4001","无效水表读数"),
		INVALID_AMMETER("4002","无效电表读数"),
		
		//优惠券
		INVALID_LINK_URL("5001","无效分享链接"),
		GET_COUPON_UPPER_LIMIT("5002","超过领取次数限制"),
		ALREADY_GET_COUPON("5003","已经领取过优惠券"),
		ALREADY_GET_FINISHED_COUPON("5004","优惠券已被领取完");
		private String code;
		private String message;

		private ResultCode(String code, String message) {
			this.code = code;
			this.message = message;
		}

		public String getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}

		public static ResultCode getResultCode(String code) {

			switch (code) {
			case "0000":
				return SUCCESS;
			case "0001":
				return FAILED;
			case "0002":
				return NOT_LOGIN_ERROR;
			case "0003":
				return FILE_TYPE_ERROR;
			case "0004":
				return FILE_HEAD_FORMAT_ERROR;
			case "0005":
				return INVALID_PARAM;
			case "0006":
				return INVALID_STATUS;
			case "0007":
				return INVALID_USERNAME;
			case "0008":
				return INVALID_SIGN;
			case "0009":
				return INVALID_ACCOUNT;
			case "0010":
				return INVALID_EQUIPMENT;
			case "0012":
				return INVALID_PHONE_NUMBER;
			case "0013":
				return USER_ALREADY_EXISTS;
			case "0014":
				return INVALID_PWD_FORMAT;
			case "0015":
				return INVALID_VERIFY_CODE;
			case "0016":
				return INVALID_AMOUNT;
			case "0017":
				return CONTENT_LENGTH_TOO_LONG;
			case "0018":
				return REPEAT_SUBMITED;
			case "0019":
				return CARD_IS_LOSTED;
			case "0020":
				return REPEAT_BIND_ACCOUNT;
			case "0021":
				return NO_BINDED_ACCOUNT;
			case "0022":
				return BIND_SAME_ACCOUNT;
				
			case "1001":
				return INVALID_ORDER;
			case "1002":
				return ORDER_AMOUNT_INCONSISTENT;
			case "1003":
				return PAY_ACCOUNT_IS_EMPTY;
			case "1004":
				return REPEAT_PAID;
			case "1005":
				return PROMOTION_ACTIVITY_END;
			case "1006":
				return INVALID_ORDER_STATUS;
			case "1007":
				return AMOUNT_EXCEEDING_LIMIT;
			case "1008":
				return EQUIP_NETWORK_EXCEPTION;
			case "1009":
				return NOT_SELL_WATER;
			case "1010":
				return NOT_FOUND_ORDER;
			case "1011":
				return INVALID_PACKAGE;
			case "1012":
				return INVALID_ACTIVITY;
			case "1013":
				return INVALID_COUPON;
				
			case "2001":
				return BANKCARD_NOT_AUDIT;
			case "2002":
				return AUDIT_RECORD_EXISTS;
			case "2003":
				return GET_WATER_FEE_NOT_ENOUGH;
			case "2004":
				return REPEAT_WITHDRAWAL;
				
			case "3001":
				return HAS_GET_GIFTS;
			case "3002":
				return EXCEEDING_BUY_QUANTITY_LIMIT;
				
			case "4001":
				return INVALID_WATERMETER;
			case "4002":
				return INVALID_AMMETER;
				
			case "5001":
				return INVALID_LINK_URL;
			case "5002":
				return GET_COUPON_UPPER_LIMIT;
			case "5003":
				return ALREADY_GET_COUPON;
			case "5004":
				return ALREADY_GET_FINISHED_COUPON;
				
			default:
				return FAILED;
			}
		}
	}
}
