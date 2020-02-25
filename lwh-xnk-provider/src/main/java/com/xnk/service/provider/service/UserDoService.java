package com.xnk.service.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnk.service.api.model.Evaluation;
import com.xnk.service.api.model.UserDo;
import com.xnk.service.api.model.UserTotal;
import com.xnk.service.dao.evaluation.EvaluationDao;
import com.xnk.service.dao.user.UserDoMapper;
import com.xnk.service.dao.user.UserTotalMapper;
import com.xnk.service.provider.dataSource.TargetDataSource;
import com.xnk.service.service.CrudService;

@Service
@TargetDataSource("master")
@Transactional(readOnly = false)
public class UserDoService extends CrudService<UserDoMapper, UserDo>{
    @Autowired
	private EvaluationDao evaluationDao;
    
    @Autowired
    private UserTotalMapper userTotalDao;
	/**
	 * 测评项目点赞，测评点赞数相应累加
	 * commentSeqMap.put(1, "脚感磨合");
		commentSeqMap.put(2, "外观设计");
		commentSeqMap.put(3, "做工");
		commentSeqMap.put(4, "包裹性");
		commentSeqMap.put(5, "支撑性");
		commentSeqMap.put(6, "灵活性");
		commentSeqMap.put(7, "透气性");
		commentSeqMap.put(8, "抓地力");
		commentSeqMap.put(9, "耐磨性");
		commentSeqMap.put(10, "缓震性");
	 * @param used
	 * @param e
	 */
	public void insertCommentGood(UserDo used, Evaluation e) {
		
		switch(used.getChildType()){
		case 1:
			e.setFootFeelGoodNum(e.getFootFeelGoodNum() + 1);
			break;
		case 2:
			e.setAppreanceGoodNum(e.getAppreanceGoodNum() + 1);
			break;
		case 3:
			e.setWorkGoodNum(e.getWorkGoodNum() + 1);
			break;
		case 4:
			e.setPackageGoodNum( e.getPackageGoodNum() + 1);
			break;
		case 5:
			e.setZhiChengGoodNum(e.getZhiChengGoodNum() + 1);
			break;
		case 6:
			e.setFlexGoodNum(e.getFlexGoodNum() + 1);
			break;
		case 7:
			e.setTouqiGoodNum(e.getTouqiGoodNum() + 1);
			break;
		case 8:
			e.setZhuadiliGoodNum(e.getZhuadiliGoodNum() + 1);
			break;
		case 9:
			e.setNaimoGoodNum(e.getNaimoGoodNum() + 1);
			break;
		case 10:
			e.setHuanzhenGoodNum(e.getHuanzhenGoodNum() + 1);
			break;
		default:
			break;
		}
		
		evaluationDao.update(e);
		this.dao.insert(used);
	}
	
	public int insert(UserDo used){
		
		if(2==used.getType()){
			//用户想买
			//userTotal 更新想买数
			UserTotal ut = new UserTotal();
			ut.setUserId(used.getUserId());
			UserTotal userTotal = this.userTotalDao.get(ut);
			userTotal.setEvaluationLikeCount(userTotal.getEvaluationLikeCount() + 1);
			this.userTotalDao.update(userTotal);
		}
		
		return this.dao.insert(used);
	}
	
	public int update(UserDo used){
		
		if(used.getType() == 1 && used.getStatus() == 0){
			//用户取消想买
			UserTotal ut = new UserTotal();
			ut.setUserId(used.getUserId());
			UserTotal userTotal = this.userTotalDao.get(ut);
			userTotal.setEvaluationLikeCount(userTotal.getEvaluationLikeCount() - 1);
			this.userTotalDao.update(userTotal);
		}
		
		return this.dao.update(used);
	}

}
