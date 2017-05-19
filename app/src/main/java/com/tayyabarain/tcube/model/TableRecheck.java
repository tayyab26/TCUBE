package com.tayyabarain.tcube.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tayyab Arain on 16/05/2017.
 */
public class TableRecheck {

    @SerializedName("billPrinted")
    @Expose
    private Boolean billPrinted;
    @SerializedName("isFree")
    @Expose
    private Boolean isFree;
    @SerializedName("orderNumber")
    @Expose
    private Integer orderNumber;
    @SerializedName("tableId")
    @Expose
    private Integer tableId;
    @SerializedName("tableTitle")
    @Expose
    private String tableTitle;
    @SerializedName("terminalID")
    @Expose
    private String terminalID;
    @SerializedName("totalAmount")
    @Expose
    private Integer totalAmount;

    public Boolean getBillPrinted() {
        return billPrinted;
    }

    public void setBillPrinted(Boolean billPrinted) {
        this.billPrinted = billPrinted;
    }

    public Boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getTableTitle() {
        return tableTitle;
    }

    public void setTableTitle(String tableTitle) {
        this.tableTitle = tableTitle;
    }

    public String getTerminalID() {
        return terminalID;
    }

    public void setTerminalID(String terminalID) {
        this.terminalID = terminalID;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

}
