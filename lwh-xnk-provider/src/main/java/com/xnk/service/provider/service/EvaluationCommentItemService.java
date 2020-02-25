package com.xnk.service.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnk.service.api.model.Evaluation;
import com.xnk.service.api.model.EvaluationCommentItem;
import com.xnk.service.dao.evaluation.EvaluationCommentItemMapper;
import com.xnk.service.dao.evaluation.EvaluationDao;
import com.xnk.service.provider.dataSource.TargetDataSource;
import com.xnk.service.service.CrudService;

@Service
@TargetDataSource("master")
@Transactional(readOnly = false)
public class EvaluationCommentItemService  extends CrudService<EvaluationCommentItemMapper, EvaluationCommentItem>{

	@Autowired
	protected EvaluationCommentItemMapper dao;

	@Autowired
	private EvaluationDao evaluationDao;
	
	/**
	 * 添加测评项内容  如果是追加，追加测评加1
	 * @param citem
	 * @param evaluation
	 */
	public void insertComment(EvaluationCommentItem citem, Evaluation e) {
		
		if(null != citem.getParentId()){
			e.setZhuijiaNum(e.getZhuijiaNum() + 1);
		}else{
			citem.setParentId(0L);
			switch(citem.getType()){
			case 1:
				e.setFootFeelCommentNum(e.getFootFeelCommentNum() + 1);
				break;
			case 2:
				e.setAppreanceCommentNum(e.getAppreanceCommentNum() + 1);
				break;
			case 3:
				e.setWorkCommentNum(e.getWorkCommentNum() + 1);
				break;
			case 4:
				e.setPackageCommentNum( e.getPackageCommentNum() + 1);
				break;
			case 5:
				e.setZhiChengCommentNum(e.getZhiChengCommentNum() + 1);
				break;
			case 6:
				e.setFlexCommentNum(e.getFlexCommentNum() + 1);
				break;
			case 7:
				e.setTouqiCommentNum(e.getTouqiCommentNum() + 1);
				break;
			case 8:
				e.setZhuadiliCommentNum(e.getZhuadiliCommentNum() + 1);
				break;
			case 9:
				e.setNaimoCommentNum(e.getNaimoCommentNum() + 1);
				break;
			case 10:
				e.setHuanzhenCommentNum(e.getHuanzhenCommentNum() + 1);
				break;
			default:
				break;
			}
		}
		
		this.dao.insert(citem);
		this.evaluationDao.update(e);
	}
}
