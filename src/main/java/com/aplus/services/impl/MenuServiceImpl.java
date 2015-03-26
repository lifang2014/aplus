package com.aplus.services.impl;

import com.aplus.dao.BaseDao;
import com.aplus.entity.MenuEntity;
import com.aplus.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifang on 2015/1/31.
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuEntity, Long> implements MenuService{

    @Override
    @Autowired
    public void setBaseDao(BaseDao<MenuEntity, Long> baseDao) {
        super.setBaseDao(baseDao);
    }
}
