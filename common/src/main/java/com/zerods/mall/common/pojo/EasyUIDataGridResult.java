package com.zerods.mall.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author zerods
 * @version 1.0 03/09/2017
 */
public class EasyUIDataGridResult<T> implements Serializable {
    private long total;
    private List<T> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
