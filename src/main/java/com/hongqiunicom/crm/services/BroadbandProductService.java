package com.hongqiunicom.crm.services;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.entity.BroadbandProduct;

/**
 * Created by Darkpower on 2016/11/17.
 *
 * 系统设置 - 宽带产品设置
 */
public interface BroadbandProductService extends BaseService<BroadbandProduct, Integer> {

    Page<BroadbandProduct> getBroadbandProductPage(Integer pageSize, Integer nowPage);

    Integer getAllCounts();

    Integer addBroadbandProduct(BroadbandProduct broadbandProduct);
}
