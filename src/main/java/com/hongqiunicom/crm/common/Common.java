package com.hongqiunicom.crm.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        broadbandExcelTitleList.add(new ExcelTitle(2, 2300, "宽带状态"));
        broadbandExcelTitleList.add(new ExcelTitle(3, 2300, "机主姓名"));
        broadbandExcelTitleList.add(new ExcelTitle(4, 3800, "联系电话"));
        broadbandExcelTitleList.add(new ExcelTitle(5, 2300, "系统状态"));
        broadbandExcelTitleList.add(new ExcelTitle(6, 2300, "客户分类"));
        broadbandExcelTitleList.add(new ExcelTitle(7, 3000, "备注"));
        return broadbandExcelTitleList;
    }

    public static final List<ExcelTitle> getCardTempletExcelTitleList(){
        List<ExcelTitle> cardTempletExcelTitleList = new ArrayList<ExcelTitle>();
        cardTempletExcelTitleList.add(new ExcelTitle(0, 5500, "ICCID"));
        cardTempletExcelTitleList.add(new ExcelTitle(1, 5500, "类型"));
        cardTempletExcelTitleList.add(new ExcelTitle(2, 5500, "运营商"));
        cardTempletExcelTitleList.add(new ExcelTitle(3, 5500, "卡号"));
        cardTempletExcelTitleList.add(new ExcelTitle(4, 5500, "卡品"));
        cardTempletExcelTitleList.add(new ExcelTitle(5, 5500, "开卡日期"));
        cardTempletExcelTitleList.add(new ExcelTitle(6, 5500, "卡费"));
        return cardTempletExcelTitleList;
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

    public static Date getMonthFirstDayWithString(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getMonthLastDayWithString(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }
}