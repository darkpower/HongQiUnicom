package com.hongqiunicom.crm.services.impl;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.dao.*;
import com.hongqiunicom.crm.entity.*;
import com.hongqiunicom.crm.services.UnicomOrderService;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    @Resource(type = UnicomOrderTagDao.class)
    private UnicomOrderTagDao unicomOrderTagDao;

    @Resource(type = StaffDao.class)
    private StaffDao staffDao;

    @Resource(type = CustomerDao.class)
    private CustomerDao customerDao;


    @Override
    public UnicomOrder createUnicomOrder(UnicomOrder unicomOrder) {
        UnicomOrder newUnicomOrder = new UnicomOrder();
        newUnicomOrder.setUnicomOrderDate(new Date());
        newUnicomOrder.setUnicomOrderState(1);
        newUnicomOrder.setUnicomOrderVerify(1);
        newUnicomOrder.setUnicomOrderSaveData(1);
        if (unicomOrder.getUnicomOrderTag() != null)
            newUnicomOrder.setUnicomOrderTag(unicomOrderTagDao.get(unicomOrder.getUnicomOrderTag().getUnicomOrderTagId()));
        if (unicomOrder.getUnicomOrderType() != null)
            newUnicomOrder.setUnicomOrderType(unicomOrderTypeDao.get(unicomOrder.getUnicomOrderType().getUnicomOrderTypeId()));
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
            e.printStackTrace();
        }

        return newUnicomOrder;
    }

    @Override
    public UnicomOrder updateUnicomOrderJoinBusinesses(UnicomOrder unicomOrder) {

        UnicomOrder pUnicomOrder = unicomOrderDao.get(unicomOrder.getUnicomOrderId());
        Iterator<Business> iteratorBusinesses = unicomOrder.getBusinesses().iterator();
        while (iteratorBusinesses.hasNext()) {
            Business business = businessDao.get(iteratorBusinesses.next().getBusinessId());
            if (business.getBusinessDate().before(pUnicomOrder.getUnicomOrderDate()))
                pUnicomOrder.setUnicomOrderDate(business.getBusinessDate());
            business.setBusinessState(2);
            pUnicomOrder.getBusinesses().add(business);
        }
        return pUnicomOrder;
    }

    @Override
    public UnicomOrder updateUnicomOrderJoinCustomer(UnicomOrder unicomOrder) {

        UnicomOrder pUnicomOrder = unicomOrderDao.get(unicomOrder.getUnicomOrderId());
        Customer customer = customerDao.get(unicomOrder.getCustomer().getCustomerId());
        pUnicomOrder.setCustomer(customer);
        return pUnicomOrder;
    }

    @Override
    public Integer getCountsWithOptions(String state, String verify, String savedata, String search) {
        return businessDao.getCount(this.getCriteriaWithOptions(state, verify, savedata, search));
    }


    @Override
    public Page<UnicomOrder> getUnicomOrderPageWithOptions(int pageSize, int nowPage, String state, String verify, String saveData, String search) {

        Page<UnicomOrder> page = new Page<>();
        page.setOrderBy("unicomOrderDate");
        page.setPageSize(pageSize);
        page.setNowPage(nowPage);
        return unicomOrderDao.getPage(this.getCriteriaWithOptions(state, verify, saveData, search), page);
    }

    @Override
    public UnicomOrder updateUnicomOrder(UnicomOrder unicomOrder) {
        Staff staff = staffDao.get(unicomOrder.getStaff().getStaffId());
        UnicomOrderType unicomOrderType = unicomOrderTypeDao.get(unicomOrder.getUnicomOrderType().getUnicomOrderTypeId());
        UnicomOrderTag unicomOrderTag = unicomOrderTagDao.get(unicomOrder.getUnicomOrderTag().getUnicomOrderTagId());
        UnicomOrder pUnicomOrder = unicomOrderDao.get(unicomOrder.getUnicomOrderId());
        pUnicomOrder.setUnicomOrderVerify(unicomOrder.getUnicomOrderVerify());
        pUnicomOrder.setUnicomOrderState(unicomOrder.getUnicomOrderState());
        pUnicomOrder.setUnicomOrderSaveData(unicomOrder.getUnicomOrderSaveData());
        pUnicomOrder.setUnicomOrderMistakeDescription(unicomOrder.getUnicomOrderMistakeDescription());
        pUnicomOrder.setUnicomOrderTag(unicomOrderTag);
        pUnicomOrder.setStaff(staff);
        pUnicomOrder.setUnicomOrderType(unicomOrderType);
        return pUnicomOrder;
    }

    private DetachedCriteria getCriteriaWithOptions(String state, String verify, String savedata, String search) {
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

        switch (verify) {
            case "全部":
                break;
            case "尚未验收":
                criteria.add(Restrictions.eq("unicomOrderVerify", 1));
                break;
            case "验收合格":
                criteria.add(Restrictions.eq("unicomOrderVerify", 2));
                break;
            case "业务差错":
                criteria.add(Restrictions.eq("unicomOrderVerify", 3));
                break;
        }

        switch (savedata) {
            case "全部":
                break;
            case "未存档":
                criteria.add(Restrictions.eq("unicomOrderSaveData", 1));
                break;
            case "已存档":
                criteria.add(Restrictions.eq("unicomOrderSaveData", 2));
                break;
        }

        switch (search) {
            case "全部":
                break;
            default:
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyyMMdd").parse(search);
                } catch (ParseException pe) {
                    date = null;
                }

                if (date != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),0, 0, 0);
                    Date startDate = calendar.getTime();
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),23, 59, 59);
                    Date endDate = calendar.getTime();
                    criteria.add(Restrictions.between("unicomOrderDate", startDate, endDate));
                } else {
                    criteria.createAlias("customer", "c");
                    criteria.createAlias("staff", "s");
                    Disjunction disjunction = Restrictions.disjunction();
                    disjunction.add(Restrictions.eq("c.customerName", search));
                    disjunction.add(Restrictions.eq("c.customerCardId", search));
                    disjunction.add(Restrictions.eq("s.staffName", search));
                    criteria.add(disjunction);
                }
                break;
        }


        return criteria;
    }
}
