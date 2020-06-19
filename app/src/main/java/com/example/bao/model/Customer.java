package com.example.bao.model;

/**
 * 顾客类
 */
public class Customer {
    private int uId;
    private String  uAccount;
    private String uName;
    private String uPassword;
    private String uPhone;
    private String uAddress;
    private int uImage; //图片

    public Customer(){};

    public Customer(String uAccount, String uName, String uPassword, String uPhone, String uAddress) {
        this.uAccount = uAccount;
        this.uName = uName;
        this.uPassword = uPassword;
        this.uPhone = uPhone;
        this.uAddress = uAddress;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getuAccount() {
        return uAccount;
    }

    public void setuAccount(String uAccount) {
        this.uAccount = uAccount;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuAddress() {
        return uAddress;
    }

    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public int getuImage() {
        return uImage;
    }

    public void setuImage(int uImage) {
        this.uImage = uImage;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "uId=" + uId +
                ", uAccount='" + uAccount + '\'' +
                ", uName='" + uName + '\'' +
                ", uPassword='" + uPassword + '\'' +
                ", uPhone='" + uPhone + '\'' +
                ", uAddress='" + uAddress + '\'' +
                ", uImage=" + uImage +
                '}';
    }
}
