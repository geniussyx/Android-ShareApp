package com.sunsr.android.shareapp.list.bean;

import android.graphics.drawable.Drawable;

/**
 * 代表一个图片对象，含有url
 * Created by Administrator on 2015-10-15.
 */
public class ImageItem {
    private String url;             //图片url
    private Drawable drawable;      //图片实例

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
