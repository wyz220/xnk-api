package com.xnk.service.provider.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnk.service.api.model.Evaluation;
import com.xnk.service.dao.evaluation.EvaluationDao;
import com.xnk.service.provider.dataSource.TargetDataSource;
import com.xnk.service.service.CrudService;

@Service
@TargetDataSource("master")
@Transactional(readOnly = false)
public class EvaluationService extends CrudService<EvaluationDao, Evaluation>{

	public List<Evaluation> getUserBuyList(Long userId) {
		return this.dao.getUserBuyList(userId);
	}

	public List<Evaluation> getUserSellList(Long userId){
		return this.dao.getUserSellList(userId);
	}
	
	public List<Evaluation> getUserLikeList(Long userId){
		return this.dao.getUserLikeList(userId);
	}
	
}
