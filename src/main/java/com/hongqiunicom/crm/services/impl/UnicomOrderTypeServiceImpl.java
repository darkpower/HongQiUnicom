package com.hongqiunicom.crm.services.impl;

import com.hongqiunicom.crm.dao.UnicomOrderTypeDao;
import com.hongqiunicom.crm.entity.UnicomOrderType;
import com.hongqiunicom.crm.services.UnicomOrderTypeService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Darkpower on 2016/12/18.
 */
@Service
public class UnicomOrderTypeServiceImpl extends BaseServiceImpl<UnicomOrderType, Integer> implements UnicomOrderTypeService {


    @Resource(type = UnicomOrderTypeDao.class)
    private UnicomOrderTypeDao unicomOrderTypeDao;

    @Resource(type = UnicomOrderTypeDao.class)
    public void setBaseDao(UnicomOrderTypeDao unicomOrderTypeDao) {
        super.setBaseDao(unicomOrderTypeDao);
    }

    @Override
    public List<UnicomOrderType> getSelectList() {
        DetachedCriteria criteria = DetachedCriteria.forClass(UnicomOrderType.class);
        criteria.add(Restrictions.eq("unicomOrderTypeState", 1));
        return unicomOrderTypeDao.list(criteria);
    }
}
