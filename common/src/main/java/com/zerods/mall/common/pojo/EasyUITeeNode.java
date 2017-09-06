package com.zerods.mall.common.pojo;

import java.io.Serializable;

/**
 * @author zerods
 * @version 1.0 03/09/2017
 */
public class EasyUITeeNode implements Serializable {
    private long id;
    private String text;
    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
