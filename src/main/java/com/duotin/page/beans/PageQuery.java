package com.duotin.page.beans;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @param <T>
 * @author jared
 * @Description:分页对象
 * @date Nov 10, 2014 10:11:58 AM
 */
public class PageQuery<T> implements Serializable {

    private static final long serialVersionUID = -4068430693984766027L;

    private List<T> result;

    private Page page;

    public PageQuery(List<T> result, Page page) {
        this.result = result;
        this.page = page;
    }

    public PageQuery<T> page(Page value) {
        this.page = value;
        return this;
    }

    public PageQuery() {
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        if (result == null) {
            this.result = Collections.emptyList();
        } else {
            this.result = result;
        }
    }

    public PageQuery<T> result(List<T> result) {
        if (result == null) {
            this.result = Collections.emptyList();
        } else {
            this.result = result;
        }
        return this;
    }
}
