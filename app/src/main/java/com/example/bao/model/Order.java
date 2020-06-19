package com.example.bao.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private int oId;
    private  int uId;
    private  int bId;
    private ArrayList<Integer> mIds;
    private String oDate;
    private  double oPrice;
    private String oAddress;
    private int dId;//配送员id
    private String oEvalute;

    public Order(){};

    public Order(int oId, int uId, int bId, ArrayList<Integer> mIds, String oDate, double oPrice, String oAddress, int dId, String oEvalute) {
        this.oId = oId;
        this.uId = uId;
        this.bId = bId;
        this.mIds = mIds;
        this.oDate = oDate;
        this.oPrice = oPrice;
        this.oAddress = oAddress;
        this.dId = dId;
        this.oEvalute = oEvalute;
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public ArrayList<Integer> getmIds() {
        return mIds;
    }

    public void setmIds(ArrayList<Integer> mIds) {
        this.mIds = mIds;
    }

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }



    public String getoDate() {
        return oDate;
    }

    public void setoDate(String oDate) {
        this.oDate = oDate;
    }

    public double getoPrice() {
        return oPrice;
    }

    public void setoPrice(double oPrice) {
        this.oPrice = oPrice;
    }

    public String getoAddress() {
        return oAddress;
    }

    public void setoAddress(String oAddrss) {
        this.oAddress = oAddrss;
    }

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public String getoEvalute() {
        return oEvalute;
    }

    public void setoEvalute(String oEvalute) {
        this.oEvalute = oEvalute;
    }
}
