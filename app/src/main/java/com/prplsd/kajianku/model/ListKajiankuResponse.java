package com.prplsd.kajianku.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListKajiankuResponse {
    @SerializedName("data")
    private List<KajiankuItem> data;
    @SerializedName("status")
    private String status;
    public void setData(List<KajiankuItem> data){
        this.data = data;
    }
    public List<KajiankuItem> getData(){
        return data;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return status;
    }
}
