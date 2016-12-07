package com.hongqiunicom.crm.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Hongten
 * @created 2014-5-21
 */
public class Common {

    public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";

    public static final String EMPTY = "";
    public static final String POINT = ".";
    public static final String LIB_PATH = "lib";
    public static final String STUDENT_INFO_XLS_PATH = LIB_PATH + "/student_info" + POINT + OFFICE_EXCEL_2003_POSTFIX;
    public static final String STUDENT_INFO_XLSX_PATH = LIB_PATH + "/student_info" + POINT + OFFICE_EXCEL_2010_POSTFIX;
    public static final String NOT_EXCEL_FILE = " : Not the Excel file!";
    public static final String PROCESSING = "Processing...";


    public static final List<ExcelTitle> getBroadbandExcelTitleList(){
        List<ExcelTitle> broadbandExcelTitleList = new ArrayList<ExcelTitle>();
        broadbandExcelTitleList.add(new ExcelTitle(0, 3800, "宽带账号"));
        broadbandExcelTitleList.add(new ExcelTitle(1, 2300, "续费状态"));
        broadbandExcelTitleList.add(new ExcelTitle(2, 2300, "机主姓名"));
        broadbandExcelTitleList.add(new ExcelTitle(3, 3800, "联系电话"));
        broadbandExcelTitleList.add(new ExcelTitle(4, 2300, "系统状态"));
        broadbandExcelTitleList.add(new ExcelTitle(5, 2300, "客户分类"));
        broadbandExcelTitleList.add(new ExcelTitle(6, 3000, "备注"));
        return broadbandExcelTitleList;
    }

    public static Date getLastMonthFirstDay() {
        Calendar lastMonthFirstDayCal = Calendar.getInstance();
        lastMonthFirstDayCal.add(Calendar.MONTH, -1);
        lastMonthFirstDayCal.set(Calendar.DAY_OF_MONTH, 1);
        return lastMonthFirstDayCal.getTime();
    }

    public static Date getLastMonthLastDay() {
        Calendar lastMonthLastDayCal = Calendar.getInstance();
        lastMonthLastDayCal.set(Calendar.DAY_OF_MONTH, 0);
        return lastMonthLastDayCal.getTime();
    }

    public static Date getThisMonthFirstDay() {
        Calendar lastMonthFirstDayCal = Calendar.getInstance();
        lastMonthFirstDayCal.set(Calendar.DAY_OF_MONTH, 1);
        return lastMonthFirstDayCal.getTime();
    }

    public static Date getThisMonthLastDay() {
        Calendar lastMonthLastDayCal = Calendar.getInstance();
        lastMonthLastDayCal.set(Calendar.DAY_OF_MONTH, lastMonthLastDayCal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return lastMonthLastDayCal.getTime();
    }


    public static Integer getTotalPageFromCount(Integer count) {
        System.out.print("!!!!----一共" + count + "条");
        int totalPage = count / 10;
        System.out. print("count / 10 = " + totalPage);
        System.out. print("count % 10 = " + totalPage % 10);

        if ((count % 10) != 0)
            totalPage++;
        System.out.print("!!!!----一共" + totalPage + "页");
        return totalPage;
    }
}