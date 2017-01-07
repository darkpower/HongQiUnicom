package com.hongqiunicom.crm.services.impl;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.dao.CustomerDao;
import com.hongqiunicom.crm.entity.Customer;
import com.hongqiunicom.crm.services.CustomerService;
import org.hibernate.criterion.DetachedCriteria;
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
    public Integer getCountsWithOptions() {
        return customerDao.getCount(this.getCriteriaWithOptions());
    }


    @Override
    public Page<Customer> getCustomerPageWithOptions(int pageSize, int nowPage) {

        Page<Customer> page = new Page<>();
        page.setOrderBy("customerId");
        page.setPageSize(pageSize);
        page.setNowPage(nowPage);
        return customerDao.getPage(this.getCriteriaWithOptions(), page);
    }


    private DetachedCriteria getCriteriaWithOptions() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
        return criteria;
    }
}
