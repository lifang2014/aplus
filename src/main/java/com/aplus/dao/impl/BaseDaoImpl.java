package com.aplus.dao.impl;

import com.aplus.dao.BaseDao;
import com.aplus.exception.ServiceException;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.PathBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lifang on 2015/1/22.
 */
public abstract class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {

    @PersistenceContext
    public EntityManager entityManager;

    private Class<T> clazz = null;

    public Map<String, Boolean> fieldNames = new HashMap<String, Boolean>();

    private static final String FIELD_NAME = "name";

    public BaseDaoImpl() {
        Type type = getClass().getGenericSuperclass();
        Type[] arrayType = ((ParameterizedType)type).getActualTypeArguments();
        clazz = (Class)arrayType[0];
        Field[] fields = clazz.getFields();
        for(Field field : fields){
            fieldNames.put(field.getName(), true);
        }
    }

    @Override
    public T findById(@NotNull ID id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public T findByName(String name) throws ServiceException {
        PathBuilder<T> pathBuilder = new PathBuilder<T>(clazz, "o");
        JPAQuery jpaQuery = new JPAQuery(entityManager);
        if(fieldNames.get(FIELD_NAME) == true){
            return jpaQuery.from(pathBuilder).where(pathBuilder.getString(FIELD_NAME).eq(name)).singleResult(pathBuilder);
        }
        return null;
    }

    @Override
    public void persist(@NotNull T t) {
        entityManager.persist(t);
    }

    @Override
    public T merge(@NotNull T t) {
        entityManager.merge(t);
        return t;
    }

    @Override
    public void remove(@NotNull T t) {
        entityManager.remove(t);
    }

}
