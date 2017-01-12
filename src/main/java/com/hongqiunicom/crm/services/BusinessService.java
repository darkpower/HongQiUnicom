package com.hongqiunicom.crm.services;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.entity.Business;

import java.io.File;
import java.util.List;

/**
 * 业务受理Service
 */
public interface BusinessService extends BaseService<Business, Integer> {

    Page<Business> getBusinessPageWithOptions(Integer pageSize, Integer nowPage, String state);

    Integer getCountsWithOptions(String state);

    Business manualUpdate(Business business);

    Boolean batchUpdateByExcel(File excelFile);

    Boolean batchUpdate(List<Business> businesses, String option);
}
