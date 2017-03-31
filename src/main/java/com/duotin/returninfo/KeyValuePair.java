package com.duotin.returninfo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: guoqi
 * Date: 15/6/27
 * Time: 下午3:56
 */
public class KeyValuePair<TKey,TValue> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private TKey key;
    private TValue value;

    public KeyValuePair(TKey key, TValue value) {
        this.key = key;
        this.value = value;
    }

    public TKey getKey() {
        return key;
    }

    public void setKey(TKey key) {
        this.key = key;
    }

    public TValue getValue() {
        return value;
    }

    public void setValue(TValue value) {
        this.value = value;
    }

}

