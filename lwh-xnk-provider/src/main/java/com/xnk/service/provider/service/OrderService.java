package com.xnk.service.provider.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnk.service.api.model.Color;
import com.xnk.service.api.model.Evaluation;
import com.xnk.service.api.model.Order;
import com.xnk.service.api.model.Shoe;
import com.xnk.service.api.model.UserEvaluationBuy;
import com.xnk.service.api.model.UserTotal;
import com.xnk.service.api.model.UserWalletHistory;
import com.xnk.service.dao.evaluation.EvaluationDao;
import com.xnk.service.dao.evaluation.OrderMapper;
import com.xnk.service.dao.evaluation.UserEvaluationBuyMapper;
import com.xnk.service.dao.shoe.ColorDao;
import com.xnk.service.dao.shoe.ShoeDao;
import com.xnk.service.dao.user.UserTotalMapper;
import com.xnk.service.dao.user.UserWalletHistoryMapper;
import com.xnk.service.provider.dataSource.TargetDataSource;
import com.xnk.service.service.CrudService;

@Service
@TargetDataSource("master")
@Transactional(readOnly = false)
public class OrderService extends CrudService<OrderMapper, Order>{

	@Autowired
	private EvaluationDao evaluationDao;
	
	@Autowired
	private UserEvaluationBuyMapper userEvaluationBuyDao;
	
	@Autowired
	private UserWalletHistoryMapper userWalletHistoryDao;
	
	@Autowired
	protected ShoeDao shoeDao;
	
	@Autowired
	protected ColorDao colorDao;
	
	@Autowired
	protected UserTotalMapper userTotalDao;
	
	public void paySuccess(String orderNo) throws Exception {
		Date curr = new Date();
		
		//更新订单状态成功
		Order order = new Order();
		order.setMchtOrderNo(orderNo);
		Order po = this.dao.get(order);
		po.setStatus(Order.OrderStatus.PAYED_SUCCESS.getStatus());
		po.setUpdateTime(curr);
		po.setPayTime(curr);
		this.dao.update(po);
		
		//更新测评订单数
		Evaluation ev = new Evaluation();
		ev.setId(po.getEvaluationId());
		Evaluation evaluation = this.evaluationDao.get(ev);
		evaluation.setOrderNum(evaluation.getOrderNum() + 1);
		this.evaluationDao.update(evaluation);
		
		//更新用户已购买测评
		UserEvaluationBuy entity = new UserEvaluationBuy();
		entity.setUserId(po.getUserId());
		entity.setEvaluationId(evaluation.getId());
		entity.setCreateTime(curr);
		userEvaluationBuyDao.insert(entity);
		
		//测评佣金划分记录
		UserWalletHistory his = new UserWalletHistory();
		his.setUserId(po.getUserId());
		his.setEvaluationId(po.getEvaluationId());
		his.setType(1);//推荐测评业务
		his.setChildType(0);
		his.setAmount(1.00);
		his.setStatus(0);//待审核
		his.setCreateTime(curr);
		this.userWalletHistoryDao.insert(his);
		
		//更新鞋款订单数
		Shoe shoe = new Shoe();
		shoe.setShoeId(evaluation.getShoeId());
		Shoe shoe2 = shoeDao.get(shoe);
		shoe2.setOrderNum(shoe2.getOrderNum() + 1);
		this.shoeDao.update(shoe2);
		
		//更新鞋款配色订单数
		Color color = new Color();
		color.setColorId(evaluation.getColorId());
		Color color2 = colorDao.get(color);
		color2.setOrderNum(color2.getOrderNum() + 1);
		this.colorDao.update(color2);
		
		//更新购买者已购数
		UserTotal ut = new UserTotal();
		ut.setUserId(po.getUserId());
		UserTotal userTotal = this.userTotalDao.get(ut);
		userTotal.setEvaluationAlreadyBuyCount(userTotal.getEvaluationAlreadyBuyCount() + 1);
		this.userTotalDao.update(userTotal);
		
		//更新测评作者卖出数
		ut.setUserId(order.getEvaluationId());
		UserTotal userTotal2 = this.userTotalDao.get(ut);
		userTotal2.setEvaluationSellCount(userTotal2.getEvaluationSellCount()+1);
		this.userTotalDao.update(userTotal2);
		
	}

	public void payFail(String orderNo) {
		Date curr = new Date();
		//更新失败
		Order order = new Order();
		order.setMchtOrderNo(orderNo);
		Order po = this.dao.get(order);
		po.setStatus(Order.OrderStatus.PAYED_FAIL.getStatus());
		po.setUpdateTime(curr);
		this.dao.update(po);
	}
}
