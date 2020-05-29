package com.xnk.service.provider.service;

import com.xnk.service.api.model.SysRegionPo;
import com.xnk.service.dao.sys.SysRegionDao;
import com.xnk.service.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class SysRegionService extends CrudService<SysRegionDao,SysRegionPo> {

    @Autowired
    private SysRegionDao sysRegionDao;

    List<SysRegionPo> getAreaCode(SysRegionPo po){
        return this.sysRegionDao.getAreaCode(po);
    }
    
}
