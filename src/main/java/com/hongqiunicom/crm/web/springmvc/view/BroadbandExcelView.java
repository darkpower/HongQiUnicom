package com.hongqiunicom.crm.web.springmvc.view;

import com.hongqiunicom.crm.common.Common;
import com.hongqiunicom.crm.common.ExcelTitle;
import com.hongqiunicom.crm.entity.Broadband;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Darkpower on 2016/12/7.
 * <p>
 * 继承AbstractXlsView
 */
public class BroadbandExcelView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        List<Broadband> broadbandList = (List<Broadband>) map.get("list");
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("微软雅黑");
        cellStyle.setFont(font);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);


        // change the file name
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + new SimpleDateFormat("yyMMddHHmmss").format(new Date()) + ".xls\"");
        Sheet sheet = workbook.createSheet("宽带续费率");
        Row titleRow = sheet.createRow(0);


        Iterator<ExcelTitle> iExcelTitleList = Common.getBroadbandExcelTitleList().iterator();
        while (iExcelTitleList.hasNext()) {
            ExcelTitle excelTitle = iExcelTitleList.next();
            sheet.setColumnWidth(excelTitle.getTitleId(), excelTitle.getTitleWidth());
            sheet.setDefaultColumnStyle(excelTitle.getTitleId(), cellStyle);
            titleRow.createCell(excelTitle.getTitleId()).setCellValue(excelTitle.getTitleString());
        }

        Iterator<Broadband> iBroadbandList = broadbandList.iterator();
        int lineNum = 1;

        while (iBroadbandList.hasNext()) {
            Broadband broadband = iBroadbandList.next();
            Row broadbandRow = sheet.createRow(lineNum);
            broadbandRow.createCell(0).setCellValue(broadband.getBroadbandAccount());
            broadbandRow.createCell(1).setCellValue(broadband.getBroadbandXuFeiState());
            broadbandRow.createCell(2).setCellValue(broadband.getCustomer() == null ? "" : broadband.getCustomer().getCustomerName());
            broadbandRow.createCell(3).setCellValue(broadband.getCustomer() == null ? "" : broadband.getCustomer().getCustomerTelphone());
            broadbandRow.createCell(4).setCellValue(broadband.getBroadbandSystemType());
            if (broadband.getCustomer() == null ? false : (broadband.getCustomer().getCustomerQualityVoice() > 200 || broadband.getCustomer().getCustomerQualityData() > 500))
                broadbandRow.createCell(5).setCellValue("高质用户");
            lineNum++;


        }
    }
}