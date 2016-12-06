package com.hongqiunicom.crm.services.impl;

import com.hongqiunicom.crm.bean.Page;
import com.hongqiunicom.crm.dao.UserDao;
import com.hongqiunicom.crm.entity.User;
import com.hongqiunicom.crm.services.UserService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {
    @Resource(type = UserDao.class)
    private UserDao userDao;

    @Resource(type = UserDao.class)
    public void setBaseDao(UserDao userDao) {
        super.setBaseDao(userDao);
    }

    /**
     * {@inheritDoc}
     */
    public Page<User> getUserPage(Integer pageSize, Integer nowPage) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        Page<User> page = new Page<User>();
        page.setOrderBy("id");
        page.setPageSize(pageSize);
        page.setNowPage(nowPage);
        return userDao.getPage(criteria, page);
    }
}