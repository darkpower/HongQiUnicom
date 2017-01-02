package com.hongqiunicom.crm.services;

import com.hongqiunicom.crm.entity.UnicomOrderType;

import java.util.List;

/**
 * Created by Darkpower on 2016/12/18.
 */
public interface UnicomOrderTypeService extends BaseService<UnicomOrderType, Integer> {
    List<UnicomOrderType> getSelectList();
}
