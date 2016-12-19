package com.hongqiunicom.crm.services;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.entity.Broadband;
import com.hongqiunicom.crm.entity.User;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Darkpower on 2016/11/17.
 */
public interface BroadbandService extends BaseService<Broadband, Integer> {
    boolean batchUpdateByExcel(File excelFile);

    Page<Broadband> getBroadbandPage(Integer pageSize, Integer nowPage);

    Page<Broadband> getBroadbandPage(Integer pageSize, Integer nowPage, String switchOption);

    Broadband manualUpdate(Broadband broadband);

    Integer getCountsWithOptions(String list, String xuFeiType, String systemType);

    Integer getCountsWithOptions(String list, String xuFeiType, String systemType, String date);

    Page<Broadband> getBroadbandPageWithOption(Integer pageSize, Integer nowPage, String list, String xuFeiType, String systemType);

    Page<Broadband> getBroadbandPageWithOption(Integer pageSize, Integer nowPage, String list, String xuFeiType, String systemType, String date);

    List<Broadband> getBroadbandsWithOptions(String list, String xuFeiType, String systemType);

}
