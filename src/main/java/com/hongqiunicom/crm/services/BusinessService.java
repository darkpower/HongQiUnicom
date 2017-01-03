package com.hongqiunicom.crm.services;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.entity.Business;

import java.io.File;
import java.util.Set;

/**
 * 业务受理Service
 */
public interface BusinessService extends BaseService<Business, Integer> {

    Page<Business> getBusinessPageWithOptions(Integer pageSize, Integer nowPage, String list, String startDay, String endDay);

    Integer getCountsWithOptions(String list, String startDay, String endDay);

    Business manualUpdate(Business business);

    Boolean batchUpdateByExcel(File excelFile);

    Boolean invalidUpdate(Set<Business> businesses);
}
