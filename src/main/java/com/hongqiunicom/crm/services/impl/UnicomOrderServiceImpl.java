package com.hongqiunicom.crm.services.impl;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.dao.BusinessDao;
import com.hongqiunicom.crm.dao.StaffDao;
import com.hongqiunicom.crm.dao.UnicomOrderDao;
import com.hongqiunicom.crm.dao.UnicomOrderTypeDao;
import com.hongqiunicom.crm.entity.Business;
import com.hongqiunicom.crm.entity.Staff;
import com.hongqiunicom.crm.entity.UnicomOrder;
import com.hongqiunicom.crm.entity.UnicomOrderType;
import com.hongqiunicom.crm.services.UnicomOrderService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;

@Service
public class UnicomOrderServiceImpl extends BaseServiceImpl<UnicomOrder, Integer> implements UnicomOrderService {

    @Resource
    UnicomOrderDao unicomOrderDao;

    @Resource(type = UnicomOrderDao.class)
    public void setBaseDao(UnicomOrderDao unicomOrderDao) {
        super.setBaseDao(unicomOrderDao);
    }

    @Resource(type = BusinessDao.class)
    private BusinessDao businessDao;

    @Resource(type = UnicomOrderTypeDao.class)
    private UnicomOrderTypeDao unicomOrderTypeDao;

    @Resource(type = StaffDao.class)
    private StaffDao staffDao;


    @Override
    public UnicomOrder createUnicomOrder(UnicomOrder unicomOrder) {
        UnicomOrder newUnicomOrder = new UnicomOrder();
        newUnicomOrder.setUnicomOrderDate(new Date());
        newUnicomOrder.setUnicomOrderState(1);
        if (unicomOrder.getUnicomOrderType() != null) newUnicomOrder.setUnicomOrderType(unicomOrderTypeDao.get(unicomOrder.getUnicomOrderType().getUnicomOrderTypeId()));
        if (unicomOrder.getStaff() != null) newUnicomOrder.setStaff(staffDao.get(unicomOrder.getStaff().getStaffId()));
        unicomOrderDao.save(newUnicomOrder);
        try {
            Iterator<Business> businessIterator = unicomOrder.getBusinesses().iterator();
            while (businessIterator.hasNext()) {
                Business business = businessDao.get(businessIterator.next().getBusinessId());
                if (business.getBusinessDate().before(newUnicomOrder.getUnicomOrderDate())) {
                    newUnicomOrder.setUnicomOrderDate(business.getBusinessDate());
                }
                business.setBusinessState(2);
                newUnicomOrder.getBusinesses().add(business);
            }
        } catch (Exception e) {

        }

        return newUnicomOrder;
    }

    @Override
    public UnicomOrder updateUnicomOrderJoinBusiness(Integer unicomOrderId, Integer businessId) {
        Business business = businessDao.get(businessId);
        UnicomOrder unicomOrder = unicomOrderDao.get(unicomOrderId);
        if (business.getBusinessDate().before(unicomOrder.getUnicomOrderDate()))
            unicomOrder.setUnicomOrderDate(business.getBusinessDate());
        business.setBusinessState(2);
        unicomOrder.getBusinesses().add(business);
        return unicomOrder;
    }

    @Override
    public Integer getCountsWithOptions(String state) {
        return businessDao.getCount(this.getCriteriaWithOptions(state));
    }


    @Override
    public Page<UnicomOrder> getUnicomOrderPageWithOptions(int pageSize, int nowPage, String state) {

        Page<UnicomOrder> page = new Page<UnicomOrder>();
        page.setOrderBy("unicomOrderDate");
        page.setPageSize(pageSize);
        page.setNowPage(nowPage);
        return unicomOrderDao.getPage(this.getCriteriaWithOptions(state), page);
    }

    @Override
    public UnicomOrder updateUnicomOrder(UnicomOrder unicomOrder) {
        Staff staff = staffDao.get(unicomOrder.getStaff().getStaffId());
        UnicomOrderType unicomOrderType = unicomOrderTypeDao.get(unicomOrder.getUnicomOrderType().getUnicomOrderTypeId());
        UnicomOrder pUnicomOrder = unicomOrderDao.get(unicomOrder.getUnicomOrderId());
        pUnicomOrder.setStaff(staff);
        pUnicomOrder.setUnicomOrderType(unicomOrderType);
        return pUnicomOrder;
    }

    private DetachedCriteria getCriteriaWithOptions(String state) {
        DetachedCriteria criteria = DetachedCriteria.forClass(UnicomOrder.class);
        switch (state) {
            case "全部":
                break;
            case "未完工":
                criteria.add(Restrictions.eq("unicomOrderState", 1));
                break;
            case "已完工":
                criteria.add(Restrictions.eq("unicomOrderState", 2));
                break;
            case "留单":
                criteria.add(Restrictions.eq("unicomOrderState", 3));
                break;
        }
        return criteria;
    }
}
