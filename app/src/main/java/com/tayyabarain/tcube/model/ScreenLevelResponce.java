package com.tayyabarain.tcube.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tayyab Arain on 17/05/2017.
 */
public class ScreenLevelResponce {

    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("screenLevels")
    @Expose
    private List<ScreenLevel> screenLevels = null;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public List<ScreenLevel> getScreenLevels() {
        return screenLevels;
    }

    public void setScreenLevels(List<ScreenLevel> screenLevels) {
        this.screenLevels = screenLevels;
    }
}
