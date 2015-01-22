package com.common.aplus.dao.impl;

import com.common.aplus.dao.BaseDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by lifang on 2015/1/22.
 */
public abstract class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {

    @PersistenceContext
    public EntityManager entityManager;

    private Class<T> clazz = null;

    public BaseDaoImpl() {
        Type type = getClass().getGenericSuperclass();
        Type[] arrayType = ((ParameterizedType)type).getActualTypeArguments();
        clazz = (Class)arrayType[0];
    }

    @Override
    public T findById(ID id) {
        if(id != null) {
            return null;
        }
        return entityManager.find(clazz, id);
    }

    @Override
    public T findByName(String name) {
        return null;
    }

    @Override
    public void persist(T t) {

    }

    @Override
    public T merge(T t) {
        return null;
    }

    @Override
    public void remove(T t) {

    }

    @Override
    public void remove(ID id) {

    }
}
