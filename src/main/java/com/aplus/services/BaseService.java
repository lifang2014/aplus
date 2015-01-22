package com.aplus.services;

import com.aplus.exception.ServiceException;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by lifang on 2015/1/22.
 */
public abstract interface BaseService<T, ID extends Serializable>{

    public abstract T findById(@NotNull ID id);

    public abstract T findByName(String name) throws ServiceException;

    public abstract void persist(@NotNull T t);

    public abstract T merge(@NotNull T t);

    public abstract void remove(@NotNull T t);

    public abstract void remove(@NotNull ID id);

}
