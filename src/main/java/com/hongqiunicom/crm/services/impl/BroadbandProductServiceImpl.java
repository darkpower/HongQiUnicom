package com.hongqiunicom.crm.services.impl;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.dao.BroadbandProductDao;
import com.hongqiunicom.crm.entity.BroadbandProduct;
import com.hongqiunicom.crm.services.BroadbandProductService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Darkpower on 2016/12/20.
 * <p>
 * BroadbandProductServiceImpl
 */
@Service
public class BroadbandProductServiceImpl extends BaseServiceImpl<BroadbandProduct, Integer> implements BroadbandProductService {

    @Resource(type = BroadbandProductDao.class)
    private BroadbandProductDao broadbandProductDao;

    @Override
    public Page<BroadbandProduct> getBroadbandProductPage(Integer pageSize, Integer nowPage) {
        DetachedCriteria criteria = DetachedCriteria.forClass(BroadbandProduct.class);
        Page<BroadbandProduct> page = new Page<>();
        page.setOrderBy("broadbandProductId");
        page.setPageSize(pageSize);
        page.setNowPage(nowPage);
        return broadbandProductDao.getPage(criteria, page);
    }

    @Override
    public Integer getAllCounts() {
        return broadbandProductDao.getCount();
    }

    @Override
    public Integer addBroadbandProduct(BroadbandProduct broadbandProduct) {
        BroadbandProduct newBroadbandProduct = new BroadbandProduct();
        newBroadbandProduct.setBroadbandProductType(broadbandProduct.getBroadbandProductType());
        newBroadbandProduct.setBroadbandProductName(broadbandProduct.getBroadbandProductName());
        newBroadbandProduct.setBroadbandProductState(broadbandProduct.getBroadbandProductState());
        newBroadbandProduct.setBroadbandProductLength(broadbandProduct.getBroadbandProductLength());
        newBroadbandProduct.setBroadbandProductDeposit(broadbandProduct.getBroadbandProductDeposit());
        newBroadbandProduct.setBroadbandProductMonthly(broadbandProduct.getBroadbandProductMonthly());
        newBroadbandProduct.setBroadbandProductDownloadSpeed(broadbandProduct.getBroadbandProductDownloadSpeed());
        return broadbandProductDao.save(newBroadbandProduct);
    }

}
