package com.hongqiunicom.crm.services.impl;

import com.hongqiunicom.crm.dao.UnicomOrderTagDao;
import com.hongqiunicom.crm.entity.UnicomOrderTag;
import com.hongqiunicom.crm.services.UnicomOrderTagService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Darkpower on 2016/12/18.
 */
@Service
public class UnicomOrderTagServiceImpl extends BaseServiceImpl<UnicomOrderTag, Integer> implements UnicomOrderTagService {


    @Resource(type = UnicomOrderTagDao.class)
    private UnicomOrderTagDao unicomOrderTagDao;

    @Resource(type = UnicomOrderTagDao.class)
    public void setBaseDao(UnicomOrderTagDao unicomOrderTagDao) {
        super.setBaseDao(unicomOrderTagDao);
    }

    @Override
    public List<UnicomOrderTag> getSelectList() {
        DetachedCriteria criteria = DetachedCriteria.forClass(UnicomOrderTag.class);
        criteria.add(Restrictions.eq("unicomOrderTagState", 1));
        return unicomOrderTagDao.list(criteria);
    }
}
