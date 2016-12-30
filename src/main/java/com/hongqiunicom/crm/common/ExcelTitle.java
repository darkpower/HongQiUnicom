package com.hongqiunicom.crm.common;

import java.util.List;

/**
 * Created by Darkpower on 2016/12/7.
 */
public class ExcelTitle {
    private Integer titleId = 0;
    private String titleString = "";
    private Integer titleWidth = 0;

    public ExcelTitle() {
    }

    public ExcelTitle(Integer titleId, Integer titleWidth, String titleString) {
        this.titleId = titleId;
        this.titleString = titleString;
        this.titleWidth = titleWidth;
    }


    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getTitleString() {
        return titleString;
    }

    public void setTitleString(String titleString) {
        this.titleString = titleString;
    }

    public Integer getTitleWidth() {
        return titleWidth;
    }

    public void setTitleWidth(Integer titleWidth) {
        this.titleWidth = titleWidth;
    }

}
