package com.project.alcoholmembership.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("qrid")
    @Expose
    private String qrid;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("pushToken")
    @Expose
    private String pushToken;

    public String getQrid() {
        return qrid;
    }

    public void setQrid(String qrid) {
        this.qrid = qrid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }
}
