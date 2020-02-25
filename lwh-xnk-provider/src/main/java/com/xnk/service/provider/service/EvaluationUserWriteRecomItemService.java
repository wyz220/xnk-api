package com.xnk.service.provider.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.xnk.service.api.model.Evaluation;
import com.xnk.service.api.model.EvaluationUserWriteRecomItem;
import com.xnk.service.api.model.EvaluationWriteRecomTotal;
import com.xnk.service.dao.evaluation.EvaluationDao;
import com.xnk.service.dao.evaluation.EvaluationUserWriteRecomItemMapper;
import com.xnk.service.dao.evaluation.EvaluationWriteRecomTotalMapper;
import com.xnk.service.provider.config.SysConfig;
import com.xnk.service.provider.dataSource.TargetDataSource;
import com.xnk.service.provider.utils.CurrencyUtils;
import com.xnk.service.service.CrudService;

@Service
@TargetDataSource("master")
@Transactional(readOnly = false)
public class EvaluationUserWriteRecomItemService extends CrudService<EvaluationUserWriteRecomItemMapper, EvaluationUserWriteRecomItem>{

	@Autowired
	protected EvaluationUserWriteRecomItemMapper dao;

	@Autowired
	private EvaluationWriteRecomTotalMapper evaluationWriteRecomTotalMapper;
	
	@Autowired
	private EvaluationDao evaluationDao;
	
	
	public void insertBatch(List<EvaluationUserWriteRecomItem> list) {
		
		Evaluation eva = new Evaluation();
		eva.setId(list.get(0).getEvaluationId());
		eva = this.evaluationDao.get(eva);
		
		EvaluationWriteRecomTotal wr = new EvaluationWriteRecomTotal();
		wr.setEvaluationId(eva.getId());
		List<EvaluationWriteRecomTotal> totalList = this.evaluationWriteRecomTotalMapper.list(wr);
		Map<Integer,EvaluationWriteRecomTotal> exist = Maps.newHashMap();
		for(EvaluationWriteRecomTotal rec : totalList){
			exist.put(rec.getSeq().intValue(), rec);
		}
		
		List<EvaluationWriteRecomTotal> adds = new ArrayList<EvaluationWriteRecomTotal>();
		
		for(EvaluationUserWriteRecomItem rec : list){
			EvaluationWriteRecomTotal evaluationWriteRecomTotal = exist.get(rec.getType());
			
			if(null == evaluationWriteRecomTotal){//未增加过
				evaluationWriteRecomTotal = new EvaluationWriteRecomTotal();
				evaluationWriteRecomTotal.setEvaluationId(eva.getId());
				evaluationWriteRecomTotal.setSeq(Long.valueOf(rec.getType()));
				evaluationWriteRecomTotal.setName(SysConfig.writeEvaluationRecomMap.get(rec.getType()));
				evaluationWriteRecomTotal.setNum(1);
				Integer orderNum = eva.getOrderNum();
				Double percent = 0.0;
				if(orderNum > 0){
					double div = CurrencyUtils.div(1, orderNum);
					percent = CurrencyUtils.mul(div, 100);
				}
				evaluationWriteRecomTotal.setPercent(percent);
				evaluationWriteRecomTotal.setCreateTime(new Date());
				adds.add(evaluationWriteRecomTotal);
				//this.evaluationWriteRecomTotalMapper.insert(evaluationWriteRecomTotal);
			}else{
				int currNum = evaluationWriteRecomTotal.getNum() + 1;
				Integer orderNum = eva.getOrderNum();
				Double percent = 0.0;
				if(orderNum > 0){
					double div = CurrencyUtils.div(currNum, orderNum);
					percent = CurrencyUtils.mul(div, 100);
				}
				evaluationWriteRecomTotal.setNum(currNum);
				evaluationWriteRecomTotal.setPercent(percent);
				evaluationWriteRecomTotal.setUpdateTime(new Date());
				this.evaluationWriteRecomTotalMapper.update(evaluationWriteRecomTotal);
			}
			
		}
		
		if(adds.size() > 0)this.evaluationWriteRecomTotalMapper.insertBatch(adds);
		
		this.dao.insertBatch(list);
	}

	// // 测评项推荐总人数/购买测评总人数   就是测评项占比
	public void insertOne(EvaluationUserWriteRecomItem e) {

		this.dao.insert(e);
		
		Evaluation eva = new Evaluation();
		eva.setId(e.getEvaluationId());
		eva = this.evaluationDao.get(eva);
		
		EvaluationWriteRecomTotal wr = new EvaluationWriteRecomTotal();
		wr.setSeq(Long.valueOf(e.getType()));
		wr.setEvaluationId(eva.getId());
		EvaluationWriteRecomTotal evaluationWriteRecomTotal = this.evaluationWriteRecomTotalMapper.get(wr);
		
		if(null == evaluationWriteRecomTotal){//未增加过
			evaluationWriteRecomTotal = new EvaluationWriteRecomTotal();
			evaluationWriteRecomTotal.setEvaluationId(eva.getId());
			evaluationWriteRecomTotal.setSeq(wr.getSeq());
			evaluationWriteRecomTotal.setName(SysConfig.writeEvaluationRecomMap.get(wr.getSeq()));
			evaluationWriteRecomTotal.setNum(1);
			Integer orderNum = eva.getOrderNum();
			Double percent = 0.0;
			if(orderNum > 0){
				double div = CurrencyUtils.div(1, orderNum);
				percent = CurrencyUtils.mul(div, 100);
			}
			evaluationWriteRecomTotal.setPercent(percent);
			evaluationWriteRecomTotal.setCreateTime(new Date());
			this.evaluationWriteRecomTotalMapper.insert(evaluationWriteRecomTotal);
		}else{
			int currNum = evaluationWriteRecomTotal.getNum() + 1;
			Integer orderNum = eva.getOrderNum();
			Double percent = 0.0;
			if(orderNum > 0){
				double div = CurrencyUtils.div(currNum, orderNum);
				percent = CurrencyUtils.mul(div, 100);
			}
			
			evaluationWriteRecomTotal.setNum(currNum);
			evaluationWriteRecomTotal.setPercent(percent);
			evaluationWriteRecomTotal.setUpdateTime(new Date());
			this.evaluationWriteRecomTotalMapper.update(evaluationWriteRecomTotal);
		}
	}
	
	
}
