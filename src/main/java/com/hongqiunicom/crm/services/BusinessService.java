package com.hongqiunicom.crm.services;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.entity.Business;

import java.io.File;
import java.util.Set;

/**
 * 业务受理Service
 */
public interface BusinessService extends BaseService<Business, Integer> {

    Page<Business> getBusinessPageWithOptions(Integer pageSize, Integer nowPage, String state);

    Integer getCountsWithOptions(String state);

    Business manualUpdate(Business business);

    Boolean batchUpdateByExcel(File excelFile);

    Boolean updateBusinessToHalt(Set<Business> businesses);

    Boolean updateBusinessToOther(Set<Business> businesses);

    Boolean updateBusinessToCard(Set<Business> businesses);

}
