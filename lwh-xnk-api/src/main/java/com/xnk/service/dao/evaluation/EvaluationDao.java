package com.xnk.service.dao.evaluation;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xnk.service.api.model.Evaluation;
import com.xnk.service.dao.CrudDao;

@Mapper
public interface EvaluationDao extends CrudDao<Evaluation>{

	/**
	 * 获取用户购买的测评
	 * @param userId
	 * @return
	 */
	List<Evaluation> getUserBuyList(Long userId);
	
	/**
	 * 获取用户卖出的测评
	 * @param userId
	 * @return
	 */
	List<Evaluation> getUserSellList(Long userId);
	
	/**
	 * 获取用户想买的测评
	 * @param userId
	 * @return
	 */
	List<Evaluation> getUserLikeList(Long userId);


    List<Evaluation> pageUserSell(Evaluation entity);

	int countUserSell(Evaluation entity);

	List<Evaluation> pageUserLike(Evaluation entity);

	int countUserLike(Evaluation entity);

}
