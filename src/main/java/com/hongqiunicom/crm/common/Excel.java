package com.hongqiunicom.crm.common;

import com.hongqiunicom.crm.entity.Broadband;
import com.hongqiunicom.crm.entity.Business;
import com.hongqiunicom.crm.entity.BusinessType;
import com.hongqiunicom.crm.entity.Customer;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Darkpower on 2016/12/30.
 */
public class Excel {


    public static List getListByExcel(String activeString, File excelFile) throws IOException, ParseException {
        List oList = new ArrayList();
        HashMap<Integer, String> excelTitle = new HashMap<Integer, String>();
        POIFSFileSystem poifsFileSystem = new POIFSFileSystem(excelFile);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        Boolean isContent = false;
        String startTitle = "";
        Iterator<Row> rowIterator = sheet.iterator();

        switch (activeString) {
            case "序号":
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    //获得行中的列，即单元格
                    if (isContent) {
                        Business business = new Business();
                        Iterator<Cell> iterator = row.cellIterator();
                        while (iterator.hasNext()) {
                            Cell cell = iterator.next();
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                            switch (excelTitle.get(cell.getColumnIndex())) {
                                case "流水号":
                                    business.setBusinessSerialNumber(cell.getStringCellValue());
                                    break;
                                case "设备号码":
                                    business.setBusinessAccount(cell.getStringCellValue());
                                    break;
                                case "服务类型":
                                    BusinessType businessType = new BusinessType();
                                    businessType.setBusinessTypeName(cell.getStringCellValue());
                                    business.setBusinessType(businessType);
                                    break;
                                case "备注":
                                    business.setBusinessDescription(cell.getStringCellValue());
                                    break;
                                case "业务受理时间":
                                    business.setBusinessDate(cell.getDateCellValue());
                                    break;
                                case "客户姓名":
                                    business.setBusinessUserName(cell.getStringCellValue());
                                    break;

                                default:
                                    break;
                            }

                        }
                        Boolean hasBusiness = false;
                        Iterator oIterator = oList.iterator();
                        while (oIterator.hasNext()) {
                            Business oBusiness = (Business) oIterator.next();
                            if (business.getBusinessSerialNumber().equals(oBusiness.getBusinessSerialNumber()))
                                hasBusiness = true;
                        }
                        if (!hasBusiness) {
                            business.setBusinessState(1);
                            oList.add(business);
                        }
                    }
                    if (row.getCell(0) != null && "序号".equals(row.getCell(0).getStringCellValue())) {
                        Iterator<Cell> iterator = row.cellIterator();
                        while (iterator.hasNext()) {
                            Cell cell = iterator.next();
                            excelTitle.put(cell.getColumnIndex(), cell.getStringCellValue());
                        }
                        isContent = true;
                    }
                }
                break;
            case "宽带到期续费清单":
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
                            oList.add(broadband);

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
                break;
        }


        return oList;
    }


}
