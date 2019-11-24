package com.example.ecommerceappfinalproject.RecyclerView;

public class RecyclerViewItem {
    private String mText1;
    private String mText2;
    private int id;
    String imgeUrl;
    

    public RecyclerViewItem(String mImageResource, String mText1, String mText2, int id) {
        this.imgeUrl= mImageResource;
        this.mText1 = mText1;
        this.mText2 = mText2;
        this.id = id;

    }

    public void setImgeUrl(String imgeUrl) {
        this.imgeUrl = imgeUrl;
    }

    public String getImgeUrl() {
        return imgeUrl;
    }

    public int getId() {
        return id;
    }

    public String getmText1() {
        return mText1;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }
}
