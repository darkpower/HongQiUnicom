package com.hongqiunicom.crm.web.springmvc.view;

import com.hongqiunicom.crm.common.Common;
import com.hongqiunicom.crm.common.ExcelTitle;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Darkpower on 2016/12/17.
 */
public class CardTempletExcelView extends AbstractXlsView {
    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("微软雅黑");
        cellStyle.setFont(font);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);


        // change the file name
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"CardTemplet.xls\"");
        Sheet sheet = workbook.createSheet("号卡上传模板");
        Row titleRow = sheet.createRow(0);

        Iterator<ExcelTitle> iExcelTitleList = Common.getCardTempletExcelTitleList().iterator();
        while (iExcelTitleList.hasNext()) {
            ExcelTitle excelTitle = iExcelTitleList.next();
            sheet.setColumnWidth(excelTitle.getTitleId(), excelTitle.getTitleWidth());
            sheet.setDefaultColumnStyle(excelTitle.getTitleId(), cellStyle);
            titleRow.createCell(excelTitle.getTitleId()).setCellValue(excelTitle.getTitleString());
        }

    }
}
