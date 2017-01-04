package com.hongqiunicom.crm.services;

import com.hongqiunicom.crm.entity.UnicomOrderTag;

import java.util.List;

/**
 * Created by Darkpower on 2016/12/18.
 */
public interface UnicomOrderTagService extends BaseService<UnicomOrderTag, Integer> {
    List<UnicomOrderTag> getSelectList();
}
