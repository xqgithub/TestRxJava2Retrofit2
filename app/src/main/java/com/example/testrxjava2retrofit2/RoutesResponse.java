package com.example.testrxjava2retrofit2;

/**
 * Created by Z on 2017/5/3.
 */

public class RoutesResponse {


    /**
     * updated_at : 1493785206
     * d2o : routes/androidd2o
     * o2d : routes/androido2d
     * is_domestic : true
     */

    private int updated_at;
    private int china_ip_update_at;
    private String d2o;             // 国内翻国外的路由表
    private String o2d;             // 国外翻国内的路由表
    private String china_ip_list;   // ip表
    private boolean is_domestic;    // 是否是国内

    private String foreign_ip_list;//国外IP表
    private int foreign_ip_update_at;


    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public String getD2o() {
        return d2o;
    }

    public void setD2o(String d2o) {
        this.d2o = d2o;
    }

    public String getO2d() {
        return o2d;
    }

    public void setO2d(String o2d) {
        this.o2d = o2d;
    }

    public boolean isIs_domestic() {
        return is_domestic;
    }

    public void setIs_domestic(boolean is_domestic) {
        this.is_domestic = is_domestic;
    }

    public int getChina_ip_update_at() {
        return china_ip_update_at;
    }

    public void setChina_ip_update_at(int china_ip_update_at) {
        this.china_ip_update_at = china_ip_update_at;
    }

    public String getChina_ip_list() {
        return china_ip_list;
    }

    public void setChina_ip_list(String china_ip_list) {
        this.china_ip_list = china_ip_list;
    }

    public String getForeign_ip_list() {
        return foreign_ip_list;
    }

    public void setForeign_ip_list(String foreign_ip_list) {
        this.foreign_ip_list = foreign_ip_list;
    }

    public int getForeign_ip_update_at() {
        return foreign_ip_update_at;
    }

    public void setForeign_ip_update_at(int foreign_ip_update_at) {
        this.foreign_ip_update_at = foreign_ip_update_at;
    }

    public boolean is_domestic() {
        return is_domestic;
    }
}
