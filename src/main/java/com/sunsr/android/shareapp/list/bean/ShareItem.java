package com.sunsr.android.shareapp.list.bean;

/**
 * 分享信息实体
 * Created by Administrator on 2015-10-15.
 */
public class ShareItem {
    private String title;               //标题
    private ImageItem imageItem;        //图片实体

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setImageItem(ImageItem imageItem) {
        this.imageItem = imageItem;
    }

    public ImageItem getImageItem() {
        return imageItem;
    }
}
