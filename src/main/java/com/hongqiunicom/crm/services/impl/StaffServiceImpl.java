package com.hongqiunicom.crm.services.impl;

import com.hongqiunicom.crm.dao.StaffDao;
import com.hongqiunicom.crm.entity.Staff;
import com.hongqiunicom.crm.services.StaffService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Darkpower on 2016/12/18.
 */
@Service
public class StaffServiceImpl extends BaseServiceImpl<Staff, Integer> implements StaffService {


    @Resource(type = StaffDao.class)
    private StaffDao staffDao;

    @Resource(type = StaffDao.class)
    public void setBaseDao(StaffDao staffDao) {
        super.setBaseDao(staffDao);
    }

    @Override
    public List<Staff> getSelectList() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Staff.class);
        criteria.add(Restrictions.eq("staffState", 1));
        return staffDao.list(criteria
        );
    }
}
