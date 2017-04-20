package com.gmail.mosoft521.gson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by root on 2017/4/20 0020.
 */
public class MethodContent implements Serializable {
    @SerializedName("Id")
    private String id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Code")
    private String code;
    @SerializedName("PWeight")
    private String pWeight;

    public MethodContent() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getpWeight() {
        return pWeight;
    }

    public void setpWeight(String pWeight) {
        this.pWeight = pWeight;
    }
}
