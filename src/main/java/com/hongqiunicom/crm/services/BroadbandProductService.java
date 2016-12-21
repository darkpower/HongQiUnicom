package com.hongqiunicom.crm.services;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.entity.Broadband;
import com.hongqiunicom.crm.entity.BroadbandProduct;

import java.io.File;
import java.util.List;

/**
 * Created by Darkpower on 2016/11/17.
 */
public interface BroadbandProductService extends BaseService<BroadbandProduct, Integer> {

    Page<BroadbandProduct> getBroadbandProductPage(Integer pageSize, Integer nowPage);

    Integer getAllCounts();

}
