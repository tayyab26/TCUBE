package com.tayyabarain.tcube.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clerk {

    @SerializedName("Autologoff")
    @Expose
    private Boolean autologoff;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Istrainingmode")
    @Expose
    private Boolean istrainingmode;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Nosale")
    @Expose
    private Boolean nosale;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Passwordprotected")
    @Expose
    private boolean passwordprotected;
    @SerializedName("Screen")
    @Expose
    private String screen;
    @SerializedName("ShowLogscreen")
    @Expose
    private Boolean showLogscreen;
    @SerializedName("Type")
    @Expose
    private String type;

    public Boolean getAutologoff() {
        return autologoff;
    }

    public void setAutologoff(Boolean autologoff) {
        this.autologoff = autologoff;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIstrainingmode() {
        return istrainingmode;
    }

    public void setIstrainingmode(Boolean istrainingmode) {
        this.istrainingmode = istrainingmode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getNosale() {
        return nosale;
    }

    public void setNosale(Boolean nosale) {
        this.nosale = nosale;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getPasswordprotected() {
        return passwordprotected;
    }

    public void setPasswordprotected(boolean passwordprotected) {
        this.passwordprotected = passwordprotected;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public Boolean getShowLogscreen() {
        return showLogscreen;
    }

    public void setShowLogscreen(Boolean showLogscreen) {
        this.showLogscreen = showLogscreen;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}