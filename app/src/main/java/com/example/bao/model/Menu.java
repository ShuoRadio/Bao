package com.example.bao.model;

/**
 * 菜品类
 */
public class Menu {
    private  int mId;
    private  String mName;
    private  double mPrice;
    private  String mIntroduction;
    private  int bId;
    private int mImage;
    private Double mStar;
    int inBuyCount=0;


    public Menu(){};

    public Menu(int mId, String mName, double mPrice, String mIntroduction, int bId, int mImage, Double mStar) {
        this.mId = mId;
        this.mName = mName;
        this.mPrice = mPrice;
        this.mIntroduction = mIntroduction;
        this.bId = bId;
        this.mImage = mImage;
        this.mStar = mStar;

    }

    public Menu(String mName, double mPrice, String mIntroduction, int bId) {
        this.mName = mName;
        this.mPrice = mPrice;
        this.mIntroduction = mIntroduction;
        this.bId = bId;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public Double getmStar() {
        return mStar;
    }

    public void setmStar(Double mStar) {
        this.mStar = mStar;
    }

    public String getmIntroduction() {
        return mIntroduction;
    }

    public void setmIntroduction(String mIntroduction) {
        this.mIntroduction = mIntroduction;
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public int getInBuyCount() {
        return inBuyCount;
    }

    public void setInBuyCount(int inBuyCount) {
        this.inBuyCount = inBuyCount;
    }
}
