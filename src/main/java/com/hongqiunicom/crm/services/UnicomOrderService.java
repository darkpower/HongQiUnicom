package com.hongqiunicom.crm.services;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.entity.UnicomOrder;

/**
 * 业务受理Service
 */
public interface UnicomOrderService extends BaseService<UnicomOrder, Integer> {

    UnicomOrder createUnicomOrder(UnicomOrder unicomOrder);

    UnicomOrder updateUnicomOrderJoinBusiness(Integer unicomOrderId, Integer businessId);

    Integer getCountsWithOptions(String state);

    Page<UnicomOrder> getUnicomOrderPageWithOptions(int pageSize, int nowPage, String state);

    UnicomOrder updateUnicomOrder(UnicomOrder unicomOrder);
}
