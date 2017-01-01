package com.hongqiunicom.crm.services;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.entity.Business;
import com.hongqiunicom.crm.entity.UnicomOrder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * 业务受理Service
 */
public interface UnicomOrderService extends BaseService<UnicomOrder, Integer> {

    UnicomOrder createUnicomOrder(UnicomOrder unicomOrder);

    UnicomOrder updateUnicomOrder(Integer unicomOrderId, Integer businessId);

    Integer getCountsWithOptions(String list, String startDay, String endDay);

    Page<UnicomOrder> getUnicomOrderPageWithOptions(int pageSize, int nowPage, String list, String startDay, String endDay);
}
