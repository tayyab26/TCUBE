package com.tayyabarain.tcube.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class TableResponce {

    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("Tables")
    @Expose
    private List<Table> tables = null;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
