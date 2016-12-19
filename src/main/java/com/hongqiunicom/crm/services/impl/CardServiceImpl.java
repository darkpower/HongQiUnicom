package com.hongqiunicom.crm.services.impl;

import com.hongqiunicom.crm.dao.CardDao;
import com.hongqiunicom.crm.entity.Card;
import com.hongqiunicom.crm.services.CardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

/**
 * Created by Darkpower on 2016/12/18.
 */
@Service
public class CardServiceImpl extends BaseServiceImpl<Card, Integer> implements CardService {


    @Resource(type = CardDao.class)
    private CardDao cardDao;

    @Override
    public boolean batchUpdateByExcel(File excelFile) {
        return false;
    }



}
