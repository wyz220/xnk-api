package com.xnk.service.provider.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnk.service.api.model.UserEvaluationBuy;
import com.xnk.service.dao.evaluation.UserEvaluationBuyMapper;
import com.xnk.service.provider.dataSource.TargetDataSource;
import com.xnk.service.service.CrudService;

@Service
@TargetDataSource("master")
@Transactional(readOnly = false)
public class UserEvaluationBuyService extends CrudService<UserEvaluationBuyMapper, UserEvaluationBuy>{

}
