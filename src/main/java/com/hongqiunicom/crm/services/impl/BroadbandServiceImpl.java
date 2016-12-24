package com.hongqiunicom.crm.services.impl;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.common.Common;
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
            List<Broadband> broadbandList = this.getBroadbandByExcel(excelFile);
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

    private List<Broadband> getBroadbandByExcel(File excelFile) {
        List<Broadband> broadbandList = new ArrayList<Broadband>();

        HashMap<Integer, String> excelTitle = new HashMap<Integer, String>();
        HSSFWorkbook wb = null;
        POIFSFileSystem fs = null;
        try {
            //设置要读取的文件路径
            fs = new POIFSFileSystem(excelFile);
            //HSSFWorkbook相当于一个excel文件，HSSFWorkbook是解析excel2007之前的版本（xls）
            //之后版本使用XSSFWorkbook（xlsx）
            wb = new HSSFWorkbook(fs);
            //获得sheet工作簿
            HSSFSheet sheet = wb.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            boolean isContent = false;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //获得行中的列，即单元格
                if (isContent) {
                    Broadband broadband = new Broadband();
                    Customer customer = new Customer();
                    Iterator<Cell> iterator = row.cellIterator();
                    while (iterator.hasNext()) {
                        Cell cell = iterator.next();
                        SimpleDateFormat simpleDateFormat8 = new SimpleDateFormat("yyyyMMdd");
                        SimpleDateFormat simpleDateFormat14 = new SimpleDateFormat("yyyyMMddHHmmss");

                        if ("服务号码".equals(excelTitle.get(cell.getColumnIndex()))) {
                            broadband.setBroadbandAccount(cell.getStringCellValue());
                        }
                        if ("宽带到期时间".equals(excelTitle.get(cell.getColumnIndex()))) {
                            if (cell.getStringCellValue().length() == 8)
                                broadband.setBroadbandExpireDate(simpleDateFormat8.parse(cell.getStringCellValue()));
                            else if (cell.getStringCellValue().length() == 14)
                                broadband.setBroadbandExpireDate(simpleDateFormat14.parse(cell.getStringCellValue()));
                        }
                        if ("宽带续费时间".equals(excelTitle.get(cell.getColumnIndex())) && !"".equals(cell.getStringCellValue())) {
                            if (cell.getStringCellValue().length() == 8)
                                broadband.setBroadbandRenewalDate(simpleDateFormat8.parse(cell.getStringCellValue()));
                            else if (cell.getStringCellValue().length() == 14)
                                broadband.setBroadbandRenewalDate(simpleDateFormat14.parse(cell.getStringCellValue()));
                        }
                        if ("用户状态".equals(excelTitle.get(cell.getColumnIndex()))) {
                            broadband.setBroadbandState(cell.getStringCellValue());
                        }
                        if ("系统标识".equals(excelTitle.get(cell.getColumnIndex()))) {
                            broadband.setBroadbandSystemType(cell.getStringCellValue());
                        }

                        if ("续费类型名称".equals(excelTitle.get(cell.getColumnIndex()))) {
                            broadband.setBroadbandXuFeiState(cell.getStringCellValue());
                        }

                        if ("客户名称".equals(excelTitle.get(cell.getColumnIndex()))) {
                            customer.setCustomerName(cell.getStringCellValue());
                        }
                        if ("身份证号".equals(excelTitle.get(cell.getColumnIndex()))) {
                            customer.setCustomerCardId(cell.getStringCellValue());
                        }
                        if ("联系电话".equals(excelTitle.get(cell.getColumnIndex()))) {
                            customer.setCustomerTelphone(cell.getStringCellValue());
                        }

                    }
                    if (!"".equals(customer.getCustomerName()))
                        broadband.setCustomer(customer);
                    if (broadband.getBroadbandAccount() != null && !"".equals(broadband.getBroadbandAccount()))
                        broadbandList.add(broadband);

                }
                if (row.getCell(0) != null && "归属地市".equals(row.getCell(0).getStringCellValue())) {
                    Iterator<Cell> iterator = row.cellIterator();
                    while (iterator.hasNext()) {
                        Cell cell = iterator.next();
                        excelTitle.put(cell.getColumnIndex(), cell.getStringCellValue());
                    }
                    isContent = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Iterator<Broadband> ii = broadbandList.iterator();
        int iii = 0;
        while (ii.hasNext()) {
            Broadband b = ii.next();
            iii++;
            System.out.println("|序号|" + iii + "----" + "|宽带账号|" + b.getBroadbandAccount() + "|续费状态|" + b.getBroadbandXuFeiState());
        }
        return broadbandList;
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
    public Page<Broadband> getBroadbandPageWithOption(Integer pageSize, Integer nowPage, String list, String xuFeiType, String systemType) {


        Page<Broadband> page = new Page<Broadband>();
        page.setOrderBy("broadbandExpireDate");
        page.setPageSize(pageSize);
        page.setNowPage(nowPage);
        return broadbandDao.getPage(this.getCriteriaWithOption(list, xuFeiType, systemType), page);
    }

    @Override
    public Page<Broadband> getBroadbandPageWithOption(Integer pageSize, Integer nowPage, String list, String xuFeiType, String systemType, String date) {
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