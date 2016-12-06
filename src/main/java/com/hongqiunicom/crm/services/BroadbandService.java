package com.hongqiunicom.crm.services;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.entity.Broadband;
import com.hongqiunicom.crm.entity.User;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Darkpower on 2016/11/17.
 */
public interface BroadbandService extends BaseService<Broadband, Integer> {
    boolean batchUpdateByExcel(File excelFile);

    Page<Broadband> getBroadbandPage(Integer pageSize, Integer nowPage);

    Page<Broadband> getBroadbandPage(Integer pageSize, Integer nowPage, String switchOption);

    Page<Broadband> getBroadbandPageWithExpireDate(Date firstDay, Date lastDay, Integer pageSize, Integer nowPage);

    Page<Broadband> getBroadbandPageWithExpireDate(Date firstDay, Date lastDay, String switchOption, Integer pageSize, Integer nowPage);

    Integer getAllCount(String switchOption);

    Integer getCountWithExpireDate(Date firstDay, Date lastDay);

    Integer getCountWithExpireDate(Date firstDay, Date lastDay, String switchOption);

    Broadband manualUpdate(Broadband broadband);
}
