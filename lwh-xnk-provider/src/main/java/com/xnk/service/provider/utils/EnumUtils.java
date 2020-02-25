package com.xnk.service.provider.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public  class EnumUtils {
	
	private static Logger logger = LoggerFactory.getLogger(EnumUtils.class);

	/***
	 * 接口状态，正常 1、异常0
	 * @author CHB
	 *
	 */
	public static enum RUN_STATUS
	{
		NORMAL(1,"正常"),ABNORMAL(0,"异常");
		
		public final int value;

		private final String desc;

		private RUN_STATUS(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (RUN_STATUS enumType : RUN_STATUS.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	
	/***
	 * 主办或者协办，
	 * @author Administrator
	 *  HOST,主办；ASSISTING，协办
	 */
	public static enum B_JOIN_TYPE
	{
		HOST(0),ASSISTING(1);
		private int value;
		
		private B_JOIN_TYPE(int join_type)
		{
			this.value = join_type;
		}
		
		public int toValue()
		{
			return value;
		}

	}
	

	/***
	 * 是否删除
	 * @author Administrator
	 *DELETED,已删除
	 */
	public static enum C_IS_DEL
	{
		DELETED(1),NOT_DELETED(0);
		
		private int value;
		 
	    private C_IS_DEL(int is_del) {
	        this.value = is_del;
	    }
	 
	    public int toValue() {
	        return value;
	    }
	}
	
	/***
	 * 附件是否可多选，是1，否0
	 * @author CHB
	 *
	 */
	public static enum C_ATTACH_IS_MULTI
	{
		YES(1,"是"),NO(0,"否");
		
		public final int value;

		private final String desc;

		private C_ATTACH_IS_MULTI(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (C_ATTACH_IS_MULTI enumType : C_ATTACH_IS_MULTI.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
	}
	
	/***
	 * 附件储存方式，数据库1，文件2
	 * @author CHB
	 *
	 */
	public static enum C_ATTACH_SAVE_TYPE
	{
		DATABASE(1,"数据库"),FILE(2,"文件");
		
		public final int value;

		private final String desc;

		private C_ATTACH_SAVE_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (C_ATTACH_SAVE_TYPE enumType : C_ATTACH_SAVE_TYPE.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
		
		public static List<Map<Object,Object>> getMap(){
			List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
			Map<Object,Object> map = null;
			for (C_ATTACH_SAVE_TYPE enumType : C_ATTACH_SAVE_TYPE.values()) {
				map = new HashMap<Object,Object>();
				map.put("value",enumType.value);
				map.put("key", enumType.desc);
				list.add(map);
			}
			return list;
		}
	}
	
	/***
	 * 客户类型
	 * @author CHB
	 *
	 */
	public static enum B_CUSTOMER_TYPE
	{
		GERENN(0,"个人"),QIYE(1,"企业"),DANWEI(6,"单位");
		
		public final int value;

		private final String desc;

		private B_CUSTOMER_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_CUSTOMER_TYPE enumType : B_CUSTOMER_TYPE.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
		
		public static List<Map<Object,Object>> getMap(){
			List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
			Map<Object,Object> map = null;
			for (B_CUSTOMER_TYPE enumType : B_CUSTOMER_TYPE.values()) {
				map = new HashMap<Object,Object>();
				map.put("value",enumType.value);
				map.put("key", enumType.desc);
				list.add(map);
			}
			return list;
		}
	}
	
	/***
	 * 货币种类
	 * @author CHB
	 *
	 */
	public static enum B_APPROVE_CURRENCY
	{
		WANYUAN(1,"万元"),YUAN(2,"元"),OUYUAN(3,"欧元"),MEIYUAN(4,"美元"),GANGBI(5,"港币"),XJPY(6,"新加坡元");
		
		public final int value;

		private final String desc;

		private B_APPROVE_CURRENCY(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_APPROVE_CURRENCY enumType : B_APPROVE_CURRENCY.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
		
		public static List<Map<Object,Object>> getMap(){
			List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
			Map<Object,Object> map = null;
			for (B_APPROVE_CURRENCY enumType : B_APPROVE_CURRENCY.values()) {
				map = new HashMap<Object,Object>();
				map.put("value",enumType.value);
				map.put("key", enumType.desc);
				list.add(map);
			}
			return list;
		}
	}
	
	
	/***
	 * 货币种类
	 * @author CHB
	 *
	 */
	public static enum B_APPROVE_IS_G_CHANNEL
	{
		SHI(1,"是"),FOU(0,"否");
		
		public final int value;

		private final String desc;

		private B_APPROVE_IS_G_CHANNEL(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_APPROVE_IS_G_CHANNEL enumType : B_APPROVE_IS_G_CHANNEL.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
		
		public static List<Map<Object,Object>> getMap(){
			List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
			Map<Object,Object> map = null;
			for (B_APPROVE_IS_G_CHANNEL enumType : B_APPROVE_IS_G_CHANNEL.values()) {
				map = new HashMap<Object,Object>();
				map.put("value",enumType.value);
				map.put("key", enumType.desc);
				list.add(map);
			}
			return list;
		}
	}
	
	/***
	 * 结果类型，批准（1）不批准（2）默认是0
	 * @author chenh
	 *
	 */
	public static enum B_RESULT_TYPE
	{
		LICENSES(0,"证照"),APPROVAL(1,"批文");
		
		public final int value;

		private final String desc;

		private B_RESULT_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_RESULT_TYPE enumType : B_RESULT_TYPE.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	/***
	 * 是否批准，批准（1）不批准（2）默认是0
	 * @author CHENH
	 *
	 */
	public static enum B_APPROVE_RESULT
	{
		APPROVAL(1,"批准"),NO_APPROVAL(2,"不批准"),NO(0,"办理中");
		
		public final int value;

		private final String desc;

		private B_APPROVE_RESULT(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_APPROVE_RESULT enumType : B_APPROVE_RESULT.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	/***
	 * 办理结果，批准、不批准
	 * @author CHENH
	 *
	 */
	public static enum B_APPR_RESULT
	{
		APPROVAL(1,"批准"),NO_APPROVAL(2,"不批准");
		
		public final int value;

		private final String desc;

		private B_APPR_RESULT(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_APPR_RESULT enumType : B_APPR_RESULT.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	/***
	 * 补正标志   0:未结束 1：补正结束(材料齐全)
	 * @author CHENH
	 *
	 */
	public static enum B_ISRENEW
	{
		ONGOING(1,"补正中"),SUCCESS(0,"完成");
		
		public final int value;

		private final String desc;

		private B_ISRENEW(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_ISRENEW enumType : B_ISRENEW.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	/***
	 * 操作类型   1,新增2,修改3,撤回新增
	 * @author CHENH
	 *
	 */
	public static enum B_HANDLE_TYPE
	{
		ADD(1,"新增"),UPDATE(2,"修改"),CANCEL(3,"撤回新增");
		
		public final int value;

		private final String desc;

		private B_HANDLE_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_HANDLE_TYPE enumType : B_HANDLE_TYPE.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
		
	/***
	 * 补正状态   0:未结束 1：补正结束(材料齐全)
	 * @author CHENH
	 *
	 */
	public static enum B_RENEW_STATUS
	{
		NOTOVER(0,"未结束"),COMPLETE(1,"补正结束（材料齐全）");
		
		public final int value;

		private final String desc;

		private B_RENEW_STATUS(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_RENEW_STATUS enumType : B_RENEW_STATUS.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	
	
	/***
	 * 特别程序申请类型  
	 * @author CHENH
	 *
	 */
	public static enum B_DELAY_TYPE
	{
		ZJJD(1,"专家鉴定"),TZ(2,"听证"),ZB(3,"招标"),PM(4,"拍卖"),JC(5,"检测"),JY(6,"检疫"),QT(7,"其它");
		
		public final int value;

		private final String desc;

		private B_DELAY_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_DELAY_TYPE enumType : B_DELAY_TYPE.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	/***
	 * 是否需要年检  0:否,1:是
	 * @author CHENH
	 *
	 */
	public static enum B_IS_YEAR_CHECK
	{
		YES(1,"是"),NO(0,"否");
		
		public final int value;

		private final String desc;

		private B_IS_YEAR_CHECK(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_IS_YEAR_CHECK enumType : B_IS_YEAR_CHECK.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	/***
	 * 是否需要快递出件结果  0:否,1:是
	 * @author CHENH
	 *
	 */
	public static enum B_IS_COURIER
	{
		YES(1,"是"),NO(0,"否");
		
		public final int value;

		private final String desc;

		private B_IS_COURIER(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_IS_COURIER enumType : B_IS_COURIER.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	/***
	 * 审核结果 
	 * @author chenh
	 *
	 */
	public static enum B_APPROVE_RESULT_ALL
	{
		WJS(0,"未结束"),CLQQ(1,"材料齐全"),CLBQ(2,"材料不齐"),CG(-9999,"草稿"),YSL(3,"已受理"),TG(4,"通过"),
		BTG(5,"不通过"),PZ(6,"批准"),BPZ(7,"不批准"),CZBJ(8,"出证办结"),ZB(9,"转报"),BJ(10,"办结"),BYSL(8888,"不予受理");
		
		public final int value;

		private final String desc;

		private B_APPROVE_RESULT_ALL(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_APPROVE_RESULT_ALL enumType : B_APPROVE_RESULT_ALL.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	/***
	 * 审核环节状态 
	 * @author chenh
	 *
	 */
	public static enum B_APPROVE_STATUS
	{
		DJ(0,"登记"),BJCH(-99,"办件撤回"),CLSH(-2,"材料审核"),SL(2,"受理"),CB(3,"承办"),SH(4,"审核"),BZ(5,"批准"),
		LG(7,"拟稿"),HG(9,"核稿"),QF(11,"签发"),BJ(10,"办结"),QS(13,"签收"),CJ(14,"出件"),CLBZ(12,"材料补正"),
		TBCX(8,"特别程序"),SQYQ(6,"申请延期"),SQCX(17,"申请撤消"),QTDWEP(20,"牵头单位受理"),LHBL(21,"联合办理"),JS(9999,"结束");
		
		public final int value;

		private final String desc;

		private B_APPROVE_STATUS(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_APPROVE_STATUS enumType : B_APPROVE_STATUS.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	
	
	/***
	 * 特别环节审核状态  0:申请,1:审批，9999：审批完成
	 * @author chenh
	 *
	 */
	public static enum B_STATUS
	{
		APPLY(0,"申请"),APPROVAL(1,"审批"),OVER(9999,"审批完成");
		
		public final int value;

		private final String desc;

		private B_STATUS(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_STATUS enumType : B_STATUS.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	/***
	 * 办理状态，待办0、已办1
	 * @author CHENH
	 *
	 */
	public static enum B_WORK_STATUS
	{
		DEALTIN(0,"待办"),ALREADYS(1,"已办");
		
		public final int value;

		private final String desc;

		private B_WORK_STATUS(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_WORK_STATUS enumType : B_WORK_STATUS.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	/***
	 * 流程是否结束，0:未结束、1:结束
	 * @author CHENH
	 *
	 */
	public static enum B_IF_END
	{
		NO(0,"未结束"),YES(1,"结束");
		
		public final int value;

		private final String desc;

		private B_IF_END(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_IF_END enumType : B_IF_END.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	
	/***
	 * 组织架构状态，状态0=已经删除1=有效部门2=部门被禁用
	 * @author CHB
	 *
	 */
	public static enum C_FRAME_STATUS
	{
		DEL(0,"删除"),ENABLE(1,"启用"),DISABLE(2,"禁用");
		
		public final int value;

		private final String desc;

		private C_FRAME_STATUS(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (C_FRAME_STATUS enumType : C_FRAME_STATUS.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	/***
	 * 启用1、禁用0
	 * @author CHB
	 *
	 */
	public static enum C_STATUS
	{
		ENABLE(1,"启用"),DISABLE(0,"禁用");
		
		public final int value;

		private final String desc;

		private C_STATUS(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (C_STATUS enumType : C_STATUS.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	/***
	 * 角色类型
	 * @author CHB
	 *
	 */
	public static enum C_ROLE_TYPE {
		post("post","岗位"),position("position","职务"),group("group","用户组"),assignment("assignment","角色")
		,user("user","用户"),dept("dept","部门"),workgroup("workgroup","工作组");

		public final String value;

		private final String desc;

		private C_ROLE_TYPE(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(String type) {
			for (C_ROLE_TYPE enumType : C_ROLE_TYPE.values()) {
				if (enumType.value.equals(type)) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	/***
	 * 是否，是1，否0
	 * @author xiecy
	 */
	public static enum IS_MULTI
	{
		YES(1,"是"),NO(0,"否");
		
		public final int value;

		private final String desc;

		private IS_MULTI(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (IS_MULTI enumType : IS_MULTI.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
		
		public static List<Map<Object,Object>> getMap(){
			List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
			Map<Object,Object> map = null;
			for (IS_MULTI enumType : IS_MULTI.values()) {
				map = new HashMap<Object,Object>();
				map.put("value",enumType.value);
				map.put("key", enumType.desc);
				list.add(map);
			}
			return list;
		}
	}
	
	/***
	 * 发送状态
	 * @author CHB
	 *
	 */
	public static enum C_SEND_STATUS
	{
		WFS(0,"未发送"),FSCG(1,"发送成功"),FSSB(2,"发送失败");
		
		public final int value;

		private final String desc;

		private C_SEND_STATUS(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (C_SEND_STATUS enumType : C_SEND_STATUS.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
		
		public static List<Map<Object,Object>> getMap(){
			List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
			Map<Object,Object> map = null;
			for (C_SEND_STATUS enumType : C_SEND_STATUS.values()) {
				map = new HashMap<Object,Object>();
				map.put("value",enumType.value);
				map.put("key", enumType.desc);
				list.add(map);
			}
			return list;
		}
	}
	/***
	 * 申报控制的类型 @枚举 1-时间控制；2-数量控制；3-用户控制
	 * @author CHB
	 *
	 */
	public static enum DECLARE_CONTROL_TYPE
	{
		TIME(1,"时间控制"),AMOUNT(2,"数量控制"),USER(3,"用户控制");
		
		public final int value;
		
		private final String desc;
		
		private DECLARE_CONTROL_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		
		public String getDesc() {
			return desc;
		}
		
		public static String getDesc(int type) {
			for (DECLARE_CONTROL_TYPE enumType : DECLARE_CONTROL_TYPE.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
		
		public static List<Map<Object,Object>> getMap(){
			List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
			Map<Object,Object> map = null;
			for (DECLARE_CONTROL_TYPE enumType : DECLARE_CONTROL_TYPE.values()) {
				map = new HashMap<Object,Object>();
				map.put("value",enumType.value);
				map.put("key", enumType.desc);
				list.add(map);
			}
			return list;
		}
	}
	
	/***
	 * 事项，审批条件类型，1 - 予以批准的条件；2 - 不予以批准的条件
	 * @author xiecy
	 */
	public static enum B_ITEM_RULE_TYPE
	{
		YES(1,"予以批准的条件"),NO(2,"不予以批准的条件");
		
		public final int value;

		private final String desc;

		private B_ITEM_RULE_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_ITEM_RULE_TYPE enumType : B_ITEM_RULE_TYPE.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
		
		public static List<Map<Object,Object>> getMap(){
			List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
			Map<Object,Object> map = null;
			for (B_ITEM_RULE_TYPE enumType : B_ITEM_RULE_TYPE.values()) {
				map = new HashMap<Object,Object>();
				map.put("value",enumType.value);
				map.put("key", enumType.desc);
				list.add(map);
			}
			return list;
		}
	}
	
	/***
	 * 邮件类型
	 * @author CHB
	 */
	public static enum C_MESSAGE_EMAIL_TYPE
	{
		SMTP(1,"SMTP");//POP3(1,"POP3"),SMTP(2,"SMTP"),IMAP(3,"IMAP");
		
		public final int value;

		private final String desc;

		private C_MESSAGE_EMAIL_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (C_MESSAGE_EMAIL_TYPE enumType : C_MESSAGE_EMAIL_TYPE.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
		
		public static List<Map<Object,Object>> getMap(){
			List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
			Map<Object,Object> map = null;
			for (C_MESSAGE_EMAIL_TYPE enumType : C_MESSAGE_EMAIL_TYPE.values()) {
				map = new HashMap<Object,Object>();
				map.put("value",enumType.value);
				map.put("key", enumType.desc);
				list.add(map);
			}
			return list;
		}
	}
	
	/***
	 * 事项，申请人权利与义务，内容类型1 - 权利；2 - 义务
	 * @author xiecy
	 */
	public static enum B_ITEM_RIGHT_DUTY_TYPE
	{
		RIGHT(1,"权利"),DUTY(2,"义务");
		
		public final int value;

		private final String desc;

		private B_ITEM_RIGHT_DUTY_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_ITEM_RIGHT_DUTY_TYPE enumType : B_ITEM_RIGHT_DUTY_TYPE.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
		
		public static List<Map<Object,Object>> getMap(){
			List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
			Map<Object,Object> map = null;
			for (B_ITEM_RULE_TYPE enumType : B_ITEM_RULE_TYPE.values()) {
				map = new HashMap<Object,Object>();
				map.put("value",enumType.value);
				map.put("key", enumType.desc);
				list.add(map);
			}
			return list;
		}
	}
	
	/***
	 * 是否接收消息
	 * @author CHB
	 */
	public static enum C_MSC_SEND_USER_TYPE
	{
		XITONG(1,"业务系统"),YONGHU(2,"用户");
		
		public final int value;

		private final String desc;

		private C_MSC_SEND_USER_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (C_MSC_SEND_USER_TYPE enumType : C_MSC_SEND_USER_TYPE.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
		
		public static List<Map<Object,Object>> getMap(){
			List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
			Map<Object,Object> map = null;
			for (C_MSC_SEND_USER_TYPE enumType : C_MSC_SEND_USER_TYPE.values()) {
				map = new HashMap<Object,Object>();
				map.put("value",enumType.value);
				map.put("key", enumType.desc);
				list.add(map);
			}
			return list;
		}
	}
		
	/***
	 * 申请人权利与义务
	 * @author CHB
	 */
	public static enum C_RIGHT_DUTY
	{
		RIGHT(1,"权利"),DUTY(2,"义务");
		
		public final int value;

		private final String desc;

		private C_RIGHT_DUTY(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (C_RIGHT_DUTY enumType : C_RIGHT_DUTY.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
	}

	/***
	 * 表单设计，表单类型
	 * @author xiecy
	 */
	public static enum C_FORM_TYPE
	{
		EDIT(1,"编辑表单"),PRINT(2,"打印表单");
		
		public final int value;

		private final String desc;

		private C_FORM_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (C_FORM_TYPE enumType : C_FORM_TYPE.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
	}
	
	/***
	 * 文件转换状态
	 * @author 曹海兵
	 */
	public static enum C_ATTACH_CONVERT
	{
		DENGDAI(0,"等待转换"),SHIBAI(2,"转换失败");
		
		public final int value;

		private final String desc;

		private C_ATTACH_CONVERT(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (C_ATTACH_CONVERT enumType : C_ATTACH_CONVERT.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
	}	
	
	/***
	 * 部署环境
	 * @author 曹海兵
	 */
	public static enum C_ENVIRONMENT
	{
		WINDOW("windows","WINDOW"),LINUX("linux","LINUX");
		
		public final String value;

		private final String desc;

		private C_ENVIRONMENT(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(String type) {
			for (C_ENVIRONMENT enumType : C_ENVIRONMENT.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
	}	
	
	/***
	 * 文件类型
	 * @author 曹海兵
	 */
	public static enum C_SYS_ATTACH_TYPE
	{
		WENDANG("1","doc/docx/xls/xlsx/wps/txt/log/ppt/sql/jsp"),PNG("png2swf.exe","png/bmp"),JPG("jpeg2swf.exe","jpeg/jpg"),GIF("gif2swf.exe","gif"),PDF("pdf2swf.exe","pdf");
		
		public final String value;

		private final String desc;

		private C_SYS_ATTACH_TYPE(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(String type) {
			for (C_SYS_ATTACH_TYPE enumType : C_SYS_ATTACH_TYPE.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
	}
	
	/**
	* TODO 根据枚举名称获取对应的枚举对象并转成List<Map<String,Object>>对象
	* @param enumName 枚举对象名称
	* @return List<Map<String,Object>>
	* @author xiecy
	 */
	public static List<Map<String,Object>> getEnumList(String enumName){
		List<Map<String,Object>> enumList = new ArrayList<Map<String,Object>>();
		
		//获取默认枚举类数据
		String ClassName = EnumUtils.class.getName();
		if(StringUtils.isNotBlank(ClassName)) {
			enumList = getEnumList(ClassName, enumName);
		}
		if(enumList.isEmpty()) {
			//默认枚举未能找到数据，加载自定义枚举类数据
			ClassName = Global.getConfig("customEnumClass");
			enumList = getEnumList(ClassName, enumName);
		}
		
		return enumList;
	}
	
	/**
	* TODO 根据枚举名称获取对应的枚举对象并转成List<Map<String,Object>>对象
	* @param ClassName 枚举类
	* @param enumName 枚举对象名称
	* @return List<Map<String,Object>>
	* @author xiecy
	 */
	private static List<Map<String,Object>> getEnumList(String ClassName,String enumName){
		List<Map<String,Object>> enumList = new ArrayList<Map<String,Object>>();
		
		Class clazz;
		try {
			//反射获取枚举对象
			clazz = Class.forName(ClassName+"$"+enumName.toUpperCase());
			//获取当前枚举常量
			Object[] objectArray = clazz.getEnumConstants();
			//枚举遍历
			int sort = 0;
			for(Object obj : objectArray){
				Map<String,Object> map = new HashMap<String,Object>();
				//反射获取对应常量的值、描述
				map.put("value",ReflectionUtils.getFieldValue(obj, "value"));
				map.put("key", ReflectionUtils.getFieldValue(obj, "desc"));
				if(sort == 0) {
					map.put("isDefault", 1);
				}else {
					map.put("isDefault", 0);
				}
				sort++;
				
				enumList.add(map);
			}
		} catch (ClassNotFoundException e) {
			if(!ClassName.equals(EnumUtils.class.getName())) {
				logger.debug("枚举对象没找到！{}", e.getMessage());
			}
		}
		
		return enumList;
	}
	
	/**
	* TODO 根据类型和key获取对应文本
	* @param value
	* @param type
	* @param defaultValue
	* @return String
	* @author xiecy
	 */
	public static String getEnumLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (Map<String,Object> enu : getEnumList(type)){
				if (value.equals(enu.get("value")+"")){
					return enu.get("key")+"";
				}
			}
		}
		return defaultValue;
	}
	
	/**
	* TODO 多值比对，将key转文本，多值用逗号隔开
	* @param values
	* @param type
	* @param defaultValue
	* @return String
	* @author xiecy
	 */
	public static String getEnumLabels(String values, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getEnumLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}
	
	/**
	* TODO 判断是否存在该枚举对象
	* @param ClassName 枚举类
	* @param type 枚举对象类型
	* @return Boolean
	* @author xiecy
	 */
	private static Boolean isEnumObject(String ClassName,String type){
		try {
			//反射获取枚举对象
			Class.forName(ClassName+"$"+type.toUpperCase());
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	* TODO 判断是否存在该枚举对象
	* @param type 枚举对象类型
	* @return Boolean
	* @author xiecy
	 */
	public static Boolean isEnumObject(String type){
		boolean exist = false;
		String ClassName = EnumUtils.class.getName();
		if(StringUtils.isNotBlank(ClassName)) {
			exist = isEnumObject(ClassName, type);
		}
		if(!exist) {
			ClassName = Global.getConfig("customEnumClass");
			exist = isEnumObject(ClassName, type);
		}
		return exist;
	}
	
	public static void main(String[] args) {
		List<Map<String,Object>> list = getEnumList("c_role_type");
		for(Map<String,Object> map : list){
			System.out.println("key:"+map.get("key")+"  value:"+map.get("value"));
		}
		//System.out.println(C_ROLE_TYPE.group.value);
	}
	
	/**
	 * 栏目类型
	 * @author hewj
	 *
	 */
	public static enum CMS_CATEGORY_TYPE
	{
		INSITE(0,"对内"),OUTSITE(1,"对外");
		
		public final int value;

		private final String desc;

		private CMS_CATEGORY_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (CMS_CATEGORY_TYPE enumType : CMS_CATEGORY_TYPE.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
	}
	
	/**
	 * 接收终端类型
	 * @author hewj
	 *
	 */
	public static enum CMS_TERMINAL_TYPE
	{
		APP(0,"app"),WEB(1,"web端");
		
		public final int value;

		private final String desc;

		private CMS_TERMINAL_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (CMS_TERMINAL_TYPE enumType : CMS_TERMINAL_TYPE.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	/**
	 * 目标窗口
	 * @author hewj
	 *
	 */
	public static enum CMS_TARGET
	{
		SELF(0,"当前窗口"),NEW(1,"新窗口");
		
		public final int value;

		private final String desc;

		private CMS_TARGET(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (CMS_TARGET enumType : CMS_TARGET.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
	}
	
	/**
	 * 导入导出数据类型
	 * @author caohb
	 *
	 */
	public static enum DATA_TYPE
	{
		menu(0,"菜单"),role(1,"角色"),roleMenu(2,"角色菜单"),flow(3,"流程数据"),user(4,"用户数据");
		
		public final int value;

		private final String desc;

		private DATA_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (DATA_TYPE enumType : DATA_TYPE.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
	}
	
	/**
	 * 是否显示
	 * @author hewj
	 *
	 */
	public static enum IS_SHOW
	{
		SHOW(0,"显示"),HIDDEN(1,"隐藏");
		
		public final int value;

		private final String desc;

		private IS_SHOW(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (IS_SHOW enumType : IS_SHOW.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
	}
	
	
	/**
	 * 栏目展现方式
	 * @author hewj
	 *
	 */
	public static enum SHOW_TYPE
	{
		DEFAULT(0,"默认展现方式"),HOME_LIST(1,"首页栏目列表"),COLUMN_FIRST(2,"栏目第一条内容");
		
		public final int value;

		private final String desc;

		private SHOW_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (SHOW_TYPE enumType : SHOW_TYPE.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
	}
	
	/**
	 * 推荐位
	 * @author hewj
	 *
	 */
	public static enum POSID
	{
		HOME(0,"首页焦点图"),CATEGORY(1,"栏目页文章推荐");
		
		public final int value;

		private final String desc;

		private POSID(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (POSID enumType : POSID.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
	}
	
	/**
	 * 消息类型
	 * @author xiecy
	 */
	public static enum C_MSC_TEMPLATE_TYPE
	{
		SMS(0,"短信"),MAIL(2,"邮件"),WEB(4,"站内消息");
		
		public final int value;

		private final String desc;

		private C_MSC_TEMPLATE_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (C_MSC_TEMPLATE_TYPE enumType : C_MSC_TEMPLATE_TYPE.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
	}
	
	

	public static enum B_ITEM_TIME_LIMIT
	{
		WORKING_DAY(0,"工作日"),NATURE_DAY(1,"自然日");
		
		public final int value;

		private final String desc;

		private B_ITEM_TIME_LIMIT(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (B_ITEM_TIME_LIMIT enumType : B_ITEM_TIME_LIMIT.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
	}	
	
	/**
	* TODO 是与否，是1，否0
	* @author xiecy
	* @date 2015-12-14 下午3:02:15
	 */
	public static enum YES_NO
	{
		YES(1,"是"),NO(0,"否");
		
		public final int value;

		private final String desc;

		private YES_NO(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (YES_NO enumType : YES_NO.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
	}
	
	/**
	* TODO 性别，男1，女2
	* @author xiecy
	* @date 2015-12-14 下午3:02:15
	 */
	public static enum C_SEX
	{
		BOY(0,"男"),GIRL(1,"女");
		
		public final int value;

		private final String desc;

		private C_SEX(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (C_SEX enumType : C_SEX.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
	}
	
	/***
	 * 多级联办，1普通、2一表制、3建设工程
	 * @author HHL
	 *
	 */
	public static enum MORE_UNION
	{
		PT(1,"普通"),YBZ(2,"异常"),JSGC(3,"建设工程");
		
		public final int value;

		private final String desc;

		private MORE_UNION(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (MORE_UNION enumType : MORE_UNION.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	/***
	 * 索引类型  StringField -  索引不分词;  StoredField -  只存不索引 ; TextField - 索引并分词
	 * @author HHL
	 *
	 */
	public static enum LUCENE_INDEX_TYPE
	{
		SYBUFC("StringField","索引不分词"),ZCBSY("StoredField","只存不索引"),SYBFC("TextField","索引并分词");
		
		public final String value;

		private final String desc;

		private LUCENE_INDEX_TYPE(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(String type) {
			for (LUCENE_INDEX_TYPE enumType : LUCENE_INDEX_TYPE.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}

	}
	
	public static enum IS_PASS
	{
		PASS(2,"通过"),NO_PASS(3,"不通过");
		
		public final int value;

		private final String desc;

		private IS_PASS(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public static String getDesc(int type) {
			for (IS_PASS enumType : IS_PASS.values()) {
				if (enumType.value == type) {
					return enumType.getDesc();
				}
			}
			return "" + type;
		}
		
		public static List<Map<Object,Object>> getMap(){
			List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
			Map<Object,Object> map = null;
			for (IS_PASS enumType : IS_PASS.values()) {
				map = new HashMap<Object,Object>();
				map.put("value",enumType.value);
				map.put("key", enumType.desc);
				list.add(map);
			}
			return list;
		}
	}
}
