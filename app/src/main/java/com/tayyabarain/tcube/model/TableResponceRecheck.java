package com.tayyabarain.tcube.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tayyab Arain on 16/05/2017.
 */
public class TableResponceRecheck {

    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("table")
    @Expose
    private Table table;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    @Override
    public String toString() {
            return new Gson().toJson(this);
    }
}
