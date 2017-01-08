package com.hongqiunicom.crm.services.impl;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.dao.CustomerDao;
import com.hongqiunicom.crm.entity.Customer;
import com.hongqiunicom.crm.services.CustomerService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Integer> implements CustomerService {

    @Resource
    CustomerDao customerDao;

    @Resource(type = CustomerDao.class)
    public void setBaseDao(CustomerDao customerDao) {
        super.setBaseDao(customerDao);
    }


    @Override
    public Integer getCountsWithOptions(String search) {
        return customerDao.getCount(this.getCriteriaWithOptions(search));
    }


    @Override
    public Page<Customer> getCustomerPageWithOptions(int pageSize, int nowPage, String search) {

        Page<Customer> page = new Page<>();
        page.setOrderBy("customerId");
        page.setPageSize(pageSize);
        page.setNowPage(nowPage);
        return customerDao.getPage(this.getCriteriaWithOptions(search), page);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customerDao.save(customer);
        return customer;
    }


    private DetachedCriteria getCriteriaWithOptions(String search) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);

        switch(search){
            case "全部":
                break;
            default:
                Disjunction disjunction = Restrictions.disjunction();
                disjunction.add(Restrictions.eq("customerCardId", search));
                disjunction.add(Restrictions.eq("customerName", search));
                disjunction.add(Restrictions.eq("customerTelphone", search));
                criteria.add(disjunction);
                break;
        }
        return criteria;
    }
}
