package com.hongqiunicom.crm.services.impl;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.common.Common;
import com.hongqiunicom.crm.common.Excel;
import com.hongqiunicom.crm.dao.BroadbandDao;
import com.hongqiunicom.crm.dao.CustomerDao;
import com.hongqiunicom.crm.entity.Broadband;
import com.hongqiunicom.crm.entity.Customer;
import com.hongqiunicom.crm.services.BroadbandService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BroadbandServiceImpl extends BaseServiceImpl<Broadband, Integer> implements BroadbandService {

    @Resource(type = BroadbandDao.class)
    private BroadbandDao broadbandDao;

    @Resource(type = BroadbandDao.class)
    public void setBaseDao(BroadbandDao broadbandDao) {
        super.setBaseDao(broadbandDao);
    }

    @Resource(type = CustomerDao.class)
    private CustomerDao customerDao;

    public boolean batchUpdateByExcel(File excelFile) {

        try {
            List<Broadband> broadbandList = Excel.getListByExcel("宽带到期续费清单", excelFile);
            Iterator<Broadband> iterator = broadbandList.iterator();
            while (iterator.hasNext()) {
                Broadband excelBroadband = iterator.next();

                Broadband broadband = broadbandDao.get("broadbandAccount", excelBroadband.getBroadbandAccount());
                if (broadband != null) {
                    System.out.println("|xuhao|" + broadband.getBroadbandId() + "|accout|" + broadband.getBroadbandAccount() + "|state|" + broadband.getBroadbandXuFeiState());
                    broadband.setBroadbandState(excelBroadband.getBroadbandState());
                    broadband.setBroadbandSystemType(excelBroadband.getBroadbandSystemType());
                    System.out.println("当前系统状态为：" + broadband.getBroadbandState() + ", 判断contains（销号）为" + broadband.getBroadbandState().contains("销号"));
                    if (broadband.getBroadbandState().contains("销号")) {
                        if ("已续费".equals(excelBroadband.getBroadbandXuFeiState())) {
                            broadband.setBroadbandXuFeiState("已续费");
                        } else {
                            broadband.setBroadbandXuFeiState("已销号");
                        }
                    } else {
                        switch (excelBroadband.getBroadbandXuFeiState()) {
                            case "已续费":
                                broadband.setBroadbandXuFeiState(excelBroadband.getBroadbandXuFeiState());
                                break;
                            case "未续费":
                                switch (broadband.getBroadbandXuFeiState()) {
                                    case "已续费":
                                        broadband.setBroadbandXuFeiState("有问题");
                                        break;
                                    case "未续费":
                                        break;
                                    case "有问题":
                                        break;
                                }
                                break;
                        }
                    }


                    System.out.println("|xuhao|" + broadband.getBroadbandId() + "|accout|" + broadband.getBroadbandAccount() + "|state|" + broadband.getBroadbandXuFeiState());
                    broadbandDao.update(broadband);
                } else {

                    if (excelBroadband.getCustomer() != null)
                        customerDao.save(excelBroadband.getCustomer());
                    broadbandDao.save(excelBroadband);
                }


            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    @Override
    public Page<Broadband> getBroadbandPage(Integer pageSize, Integer nowPage) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Broadband.class);
        Page<Broadband> page = new Page<Broadband>();
        page.setOrderBy("broadbandSystemType");
        page.setPageSize(pageSize);
        page.setNowPage(nowPage);
        return broadbandDao.getPage(criteria, page);
    }

    @Override
    public Page<Broadband> getBroadbandPage(Integer pageSize, Integer nowPage, String switchOption) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Broadband.class);
        if (!"全部".equals(switchOption))
            criteria.add(Restrictions.eq("broadbandXuFeiState", switchOption));
        Page<Broadband> page = new Page<Broadband>();
        page.setOrderBy("broadbandSystemType");
        page.setPageSize(pageSize);
        page.setNowPage(nowPage);
        return broadbandDao.getPage(criteria, page);


    }

    /**
     * @参数：Broadband
     * @返回值：Broadband
     * @用途：修改手工调整的宽带数据
     **/
    @Override
    public Broadband manualUpdate(Broadband broadband) {
        Broadband oBroadband = broadbandDao.get(broadband.getBroadbandId());
        if (oBroadband.getCustomer() == null) {
            Customer customer = new Customer();
            customer.setCustomerCardId(broadband.getCustomer().getCustomerCardId());
            customer.setCustomerName(broadband.getCustomer().getCustomerName());
            customer.setCustomerTelphone(broadband.getCustomer().getCustomerTelphone());
            customer.setCustomerQualityVoice(broadband.getCustomer().getCustomerQualityVoice());
            customer.setCustomerQualityData(broadband.getCustomer().getCustomerQualityData());
            customerDao.save(customer);
            oBroadband.setCustomer(customer);
        } else {
            oBroadband.getCustomer().setCustomerCardId(broadband.getCustomer().getCustomerCardId());
            oBroadband.getCustomer().setCustomerName(broadband.getCustomer().getCustomerName());
            oBroadband.getCustomer().setCustomerTelphone(broadband.getCustomer().getCustomerTelphone());
            oBroadband.getCustomer().setCustomerQualityVoice(broadband.getCustomer().getCustomerQualityVoice());
            oBroadband.getCustomer().setCustomerQualityData(broadband.getCustomer().getCustomerQualityData());
        }

        oBroadband.setBroadbandXuFeiState(broadband.getBroadbandXuFeiState());
        broadbandDao.update(oBroadband);
        return oBroadband;
    }

    @Override
    public Integer getCountsWithOptions(String list, String xuFeiType, String systemType) {
        return broadbandDao.getCount(this.getCriteriaWithOption(list, xuFeiType, systemType));
    }

    @Override
    public Integer getCountsWithOptions(String list, String xuFeiType, String systemType, String date) {
        return broadbandDao.getCount(this.getCriteriaWithOption(list, xuFeiType, systemType, date));
    }

    @Override
    public Page<Broadband> getBroadbandPageWithOptions(Integer pageSize, Integer nowPage, String list, String xuFeiType, String systemType) {


        Page<Broadband> page = new Page<Broadband>();
        page.setOrderBy("broadbandExpireDate");
        page.setPageSize(pageSize);
        page.setNowPage(nowPage);
        return broadbandDao.getPage(this.getCriteriaWithOption(list, xuFeiType, systemType), page);
    }

    @Override
    public Page<Broadband> getBroadbandPageWithOptions(Integer pageSize, Integer nowPage, String list, String xuFeiType, String systemType, String date) {
        Page<Broadband> page = new Page<Broadband>();
        page.setOrderBy("broadbandExpireDate");
        page.setPageSize(pageSize);
        page.setNowPage(nowPage);
        return broadbandDao.getPage(this.getCriteriaWithOption(list, xuFeiType, systemType, date), page);
    }

    @Override
    public List<Broadband> getBroadbandsWithOptions(String list, String xuFeiType, String systemType) {
        return broadbandDao.list(this.getCriteriaWithOption(list, xuFeiType, systemType));
    }

    @Override
    public Broadband retentionUpdate(Broadband broadband) {
        Broadband oBroadband = broadbandDao.get(broadband.getBroadbandId());
        oBroadband.setBroadbandRetentionDate(new Date());
        oBroadband.setBroadbandRetentionContent(broadband.getBroadbandRetentionContent());
        broadbandDao.update(oBroadband);
        return oBroadband;
    }

    private DetachedCriteria getCriteriaWithOption(String list, String xuFeiType, String systemType) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Broadband.class);
        switch (list) {
            case "全部宽带续费清单":
                break;
            case "次月宽带续费清单":
                criteria.add(Restrictions.ge("broadbandExpireDate", Common.getLastMonthFirstDay()));
                criteria.add(Restrictions.le("broadbandExpireDate", Common.getLastMonthLastDay()));
                break;
            case "当月宽带续费清单":
                criteria.add(Restrictions.ge("broadbandExpireDate", Common.getThisMonthFirstDay()));
                criteria.add(Restrictions.le("broadbandExpireDate", Common.getThisMonthLastDay()));
                break;
        }

        switch (xuFeiType) {
            case "全部":
                break;
            case "已续费":
                criteria.add(Restrictions.eq("broadbandXuFeiState", xuFeiType));
                break;
            case "未续费":
                criteria.add(Restrictions.eq("broadbandXuFeiState", xuFeiType));
                break;
            case "已销号":
                criteria.add(Restrictions.eq("broadbandXuFeiState", xuFeiType));
                break;
            case "有问题":
                criteria.add(Restrictions.eq("broadbandXuFeiState", xuFeiType));
                break;
        }

        switch (systemType) {
            case "全部":
                break;
            case "BSS":
                criteria.add(Restrictions.eq("broadbandSystemType", systemType));
                break;
            case "CBSS":
                criteria.add(Restrictions.eq("broadbandSystemType", systemType));
                break;
        }
        return criteria;
    }


    private DetachedCriteria getCriteriaWithOption(String list, String xuFeiType, String systemType, String date) {
        DetachedCriteria criteria = this.getCriteriaWithOption(list, xuFeiType, systemType);
        if (!("".equals(date) || date == null)) {
            criteria.add(Restrictions.ge("broadbandRenewalDate", Common.getMonthFirstDayWithString(date)));
            criteria.add(Restrictions.le("broadbandRenewalDate", Common.getMonthLastDayWithString(date)));
        }
        return criteria;
    }

}