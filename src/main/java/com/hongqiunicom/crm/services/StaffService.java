package com.hongqiunicom.crm.services;

import com.hongqiunicom.crm.entity.Staff;

import java.util.List;

/**
 * Created by Darkpower on 2016/12/18.
 */
public interface StaffService extends BaseService<Staff, Integer> {
    List<Staff> getSelectList();
}
