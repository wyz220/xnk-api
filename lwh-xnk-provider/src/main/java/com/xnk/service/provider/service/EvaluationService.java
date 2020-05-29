package com.xnk.service.provider.service;

import com.xnk.service.api.model.Evaluation;
import com.xnk.service.dao.evaluation.EvaluationDao;
import com.xnk.service.entity.Page;
import com.xnk.service.provider.dataSource.TargetDataSource;
import com.xnk.service.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

	public Page<Evaluation> getUserSellList(Evaluation entity){
		entity.getPage().setCount(this.dao.countUserSell(entity));
		entity.getPage().setList(this.dao.pageUserSell(entity));
		return entity.getPage();
	}

	public Page<Evaluation> getUserLikeList(Evaluation entity){
		entity.getPage().setCount(this.dao.countUserLike(entity));
		entity.getPage().setList(this.dao.pageUserLike(entity));
		return entity.getPage();
	}

	public List<Evaluation> getUserLikeList(Long userId){
		return this.dao.getUserLikeList(userId);
	}


    public List<Evaluation> listByEvaIds(Long[] evIds) {
		return this.dao.listByEvaIds(evIds);
    }
}
