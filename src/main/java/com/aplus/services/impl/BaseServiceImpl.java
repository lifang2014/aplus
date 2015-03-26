package com.aplus.services.impl;

import com.aplus.dao.BaseDao;
import com.aplus.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by lifang on 2015/1/22.
 */
public class BaseServiceImpl<T, ID extends Serializable> implements BaseDao<T, ID> {

    public Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    private BaseDao<T, ID> baseDao;

    public void setBaseDao(BaseDao<T, ID> baseDao){
        this.baseDao = baseDao;
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(@NotNull ID id) {
        if(id != null){
            return baseDao.findById(id);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public T findByName(String name){
        try {
            return baseDao.findByName(name);
        }catch (Exception e){
            logger.warn(e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return baseDao.findAll();
    }

    @Override
    @Transactional
    public void persist(@NotNull T t){
        if(t != null) {
            baseDao.persist(t);
        }
    }

    @Override
    @Transactional
    public T merge(@NotNull T t) {
        if(t != null) {
            return baseDao.merge(t);
        }
        return t;
    }

    @Override
    @Transactional
    public void remove(@NotNull T t) {
        if(t != null){
            baseDao.remove(t);
        }
    }

    @Transactional
    public void remove(@NotNull ID id) {
        if(id != null){
            T t = findById(id);
            if(t != null) {
                baseDao.remove(t);
            }
        }
    }
}
