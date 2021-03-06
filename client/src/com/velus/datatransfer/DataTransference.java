package com.velus.datatransfer;

import java.io.Serializable;

/**
 * Упаковщик для Request-Response
 * Все объекты, передаваемые по Request-Response являются наследниками DataTransference
 * @param <T>
 */
public class DataTransference<T> implements Serializable {
    public final Class<T> type;
    T value;
    public DataTransference(Class<T> type, T value){
        this.type = type;
        this.value = value;

    }
    public T get(){
        return this.value;
    }

}
