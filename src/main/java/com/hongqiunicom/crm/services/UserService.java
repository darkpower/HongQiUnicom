package com.hongqiunicom.crm.services;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.entity.User;

/**
 * 用户的业务层
 *
 * @author ydq
 */
public interface UserService extends BaseService<User, Integer> {

    /**
     * 获取用户的分页对象
     *
     * @param pageSize
     * @param nowPage
     * @return
     */
    public Page<User> getUserPage(Integer pageSize, Integer nowPage);
}