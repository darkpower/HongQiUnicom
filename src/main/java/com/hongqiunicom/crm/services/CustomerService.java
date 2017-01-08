package com.hongqiunicom.crm.services;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.entity.Customer;

/**
 * 业务受理Service
 */
public interface CustomerService extends BaseService<Customer, Integer> {


    Integer getCountsWithOptions(String search);

    Page<Customer> getCustomerPageWithOptions(int pageSize, int nowPage,String search);

    Customer createCustomer(Customer customer);

}
