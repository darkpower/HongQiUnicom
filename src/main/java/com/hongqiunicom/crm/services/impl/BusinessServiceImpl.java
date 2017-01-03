package com.hongqiunicom.crm.services.impl;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.common.Common;
import com.hongqiunicom.crm.common.Excel;
import com.hongqiunicom.crm.dao.BusinessDao;
import com.hongqiunicom.crm.dao.BusinessTypeDao;
import com.hongqiunicom.crm.entity.Business;
import com.hongqiunicom.crm.entity.BusinessType;
import com.hongqiunicom.crm.services.BusinessService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class BusinessServiceImpl extends BaseServiceImpl<Business, Integer> implements BusinessService {

    @Resource(type = BusinessDao.class)
    private BusinessDao businessDao;

    @Resource(type = BusinessDao.class)
    public void setBaseDao(BusinessDao businessDao) {
        super.setBaseDao(businessDao);
    }

    @Resource(type = BusinessTypeDao.class)
    private BusinessTypeDao businessTypeDao;


    @Override
    public Page<Business> getBusinessPageWithOptions(Integer pageSize, Integer nowPage, String list, String startDay, String endDay) {
        Page<Business> page = new Page<Business>();
        page.setOrderBy("businessDate");
        page.setPageSize(pageSize);
        page.setNowPage(nowPage);
        return businessDao.getPage(this.getCriteriaWithOptions(list, startDay, endDay), page);
    }


    @Override
    public Integer getCountsWithOptions(String list, String startDay, String endDay) {
        return businessDao.getCount(this.getCriteriaWithOptions(list, startDay, endDay));
    }

    @Override
    public Business manualUpdate(Business business) {
        return null;
    }

    @Override
    public Boolean batchUpdateByExcel(File excelFile) {
        try {
            List<Business> businessList = Excel.getListByExcel("序号", excelFile);
            Iterator<Business> iterator = businessList.iterator();
            while (iterator.hasNext()) {
                Business excelBusiness = iterator.next();
                Business business = businessDao.get("businessSerialNumber", excelBusiness.getBusinessSerialNumber());
                if (business == null) {
                    BusinessType pBusinessType = businessTypeDao.get("businessTypeName", excelBusiness.getBusinessType().getBusinessTypeName());
                    if(pBusinessType == null){
                        businessTypeDao.save(excelBusiness.getBusinessType());
                    }else{
                        excelBusiness.setBusinessType(pBusinessType);
                    }
                    businessDao.save(excelBusiness);
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Boolean invalidUpdate(Set<Business> businesses) {
        Iterator<Business> iterator = businesses.iterator();
        while(iterator.hasNext()){
            Business pBusiness = businessDao.get(iterator.next().getBusinessId());
            pBusiness.setBusinessState(3);
        }
        return true;
    }


    private DetachedCriteria getCriteriaWithOptions(String list, String startDay, String endDay) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Business.class);
        switch (list) {
            case "全部":
                break;
            case "未分拣":
                criteria.add(Restrictions.eq("businessState", 1));
                break;
        }
//        if (!"".equals(startDay) && !"".equals(endDay)) {
//            criteria.add(Restrictions.ge("broadbandRenewalDate", Common.getDateWithString("yyyy-MM-dd", startDay)));
//            criteria.add(Restrictions.le("broadbandRenewalDate", Common.getDateWithString("yyyy-MM-dd", endDay)));
//        }
        return criteria;
    }

}
