package com.hongqiunicom.crm.services;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.entity.UnicomOrder;

/**
 * 业务受理Service
 */
public interface UnicomOrderService extends BaseService<UnicomOrder, Integer> {

    UnicomOrder createUnicomOrder(UnicomOrder unicomOrder);

    UnicomOrder updateUnicomOrderJoinBusinesses(UnicomOrder unicomOrder);

    UnicomOrder updateUnicomOrderJoinCustomer(UnicomOrder unicomOrder);

    Integer getCountsWithOptions(String state, String verify, String savedata, String search);

    Page<UnicomOrder> getUnicomOrderPageWithOptions(int pageSize, int nowPage, String state, String verify, String savedata, String search);

    UnicomOrder updateUnicomOrder(UnicomOrder unicomOrder);
}
