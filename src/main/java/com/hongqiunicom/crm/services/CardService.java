package com.hongqiunicom.crm.services;

import com.hongqiunicom.crm.entity.Card;

import java.io.File;

/**
 * Created by Darkpower on 2016/12/18.
 */
public interface CardService extends BaseService<Card, Integer> {
    boolean batchUpdateByExcel(File excelFile);
}
