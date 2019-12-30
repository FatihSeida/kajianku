package com.prplsd.kajianku.model;


import com.google.gson.annotations.SerializedName;

public class KajiankuItem {
    @SerializedName("kkid")
    private String kkid;
    @SerializedName("nama kajian")
    private String nama_kajian;
    @SerializedName("foto")
    private String foto;
    @SerializedName("alamat kajian")
    private String alamat_kajian;
    @SerializedName("deskripsi")
    private String deskripsi_kk;
    @SerializedName("pembicara")
    private String pembicarakk;
    @SerializedName("waktu kajian")
    private String waktu_kk;
    @SerializedName("lama kajian")
    private String lama_kk;
    @SerializedName("tanggal kajian")
    private String tanggal_kk;
    public void setKkid(String kkid){
        this.kkid = kkid;
    }
    public String getKkidid(){
        return kkid;
    }
    public void setNama_kajian(String nama_kajian){
        this.nama_kajian = nama_kajian;
    }
    public String getNama_kajian(){
        return nama_kajian;
    }
    public void setFoto(String foto){ this.foto = foto; }
    public String getFoto(){
        return foto;
    }
    public void setAlamat_kajian(String alamat_kajian){
        this.alamat_kajian = alamat_kajian;
    }
    public String getAlamat_kajian(){
        return alamat_kajian;
    }
    public void setDeskripsi_kk(String deskripsi_kk){
        this.deskripsi_kk = deskripsi_kk;
    }
    public String getDeskripsi_kk(){
        return deskripsi_kk;
    }
    public void setPembicarakk(String pembicarakk){
        this.pembicarakk = pembicarakk;
    }
    public String getPembicarakk(){
        return pembicarakk;
    }
    public void setWaktu_kk(String waktu_kk){
        this.waktu_kk = waktu_kk;
    }
    public String getWaktu_kk(){
        return waktu_kk;
    }
    public void setLama_kk(String lama_kk){
        this.lama_kk = lama_kk;
    }
    public String getLama_kk(){
        return lama_kk;
    }
    public void setTanggal_kk(String tanggal_kk){
        this.tanggal_kk = tanggal_kk;
    }
    public String getTanggal_kk(){
        return tanggal_kk;
    }
}