package com.example.bao.model;

public class Restaurant {
    private int bId;
    private  String bAccount;
    private  String bPas;
    private  int bImage;
    private  String bName;
    private String bAddress;
    private Double bStar;
    private  Integer bSales;//销量
    private  Integer bSend_cost;//起送金额

    public Integer getbSales() {
        return bSales;
    }

    public void setbSales(Integer bSales) {
        this.bSales = bSales;
    }

    public Integer getbSend_cost() {
        return bSend_cost;
    }

    public void setbSend_cost(Integer bSend_cost) {
        this.bSend_cost = bSend_cost;
    }

    public Restaurant(){};

    public Restaurant(int bId, String bAccount, String bPas, int bImage, String bName, String bAddress, Double bStar) {
        this.bId = bId;
        this.bAccount = bAccount;
        this.bPas = bPas;
        this.bImage = bImage;
        this.bName = bName;
        this.bAddress = bAddress;
        this.bStar = bStar;
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public String getbAccount() {
        return bAccount;
    }

    public void setbAccount(String bAccount) {
        this.bAccount = bAccount;
    }

    public String getbPas() {
        return bPas;
    }

    public void setbPas(String bPas) {
        this.bPas = bPas;
    }

    public int getbImage() {
        return bImage;
    }

    public void setbImage(int bImage) {
        this.bImage = bImage;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getbAddress() {
        return bAddress;
    }

    public void setbAddress(String bAddress) {
        this.bAddress = bAddress;
    }

    public Double getbStar() {
        return bStar;
    }

    public void setbStar(Double bStar) {
        this.bStar = bStar;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "bId=" + bId +
                ", bAccount='" + bAccount + '\'' +
                ", bPas='" + bPas + '\'' +
                ", bImage=" + bImage +
                ", bName='" + bName + '\'' +
                ", bAddress='" + bAddress + '\'' +
                ", bStar=" + bStar +
                '}';
    }
}
