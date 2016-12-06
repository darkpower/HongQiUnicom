package com.hongqiunicom.crm.dao.impl;

import com.hongqiunicom.crm.dao.BaseDao;
import com.hongqiunicom.crm.dao.CustomerDao;
import com.hongqiunicom.crm.entity.Customer;
import org.springframework.stereotype.Repository;

/**
 * Created by Darkpower on 2016/11/19.
 */

@Repository
public class CustomerDaoImpl extends BaseDaoImpl<Customer, Integer> implements CustomerDao{
}
