package com.sunsr.android.shareapp.util;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

import com.sunsr.android.shareapp.list.bean.ImageItem;
import com.sunsr.android.shareapp.list.bean.ShareItem;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * 异步加载图片类
 * 内有缓存
 * 首先生成一个实例，并调用loadDrawableByTag方法来获取一个Drawable对象
 * Created by Administrator on 2015-10-15.
 */
public class AsyncImageLoader {

    /**
     * 使用软引用SoftReference，可以由系统在恰当的时候更容易的回收
     */
    private HashMap<String,SoftReference<Drawable>> imgCache;

    public AsyncImageLoader(){
        imgCache = new HashMap<String, SoftReference<Drawable>>();
    }

    public Drawable loadDrawable(final ShareItem shareItem,final ImageCallBack callBack){
        ImageItem imageItem = shareItem.getImageItem();
        Drawable drawable;      //返回结果

        //先从缓存中查询图片
        if(imgCache.containsKey(imageItem.getUrl())){
            drawable = imgCache.get(imageItem.getUrl()).get();
            if(drawable != null){
                return drawable;
            }
        }

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                ShareItem receiveItem = (ShareItem)msg.obj;
                imgCache.put(receiveItem.getImageItem().getUrl(),new SoftReference<Drawable>(receiveItem.getImageItem().getDrawable()));
                callBack.obtainImage(shareItem);
                super.handleMessage(msg);
            }
        };

        //实例化线程获得图片
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShareItem receiveItem = getImageItem(shareItem);
                Message msg = new Message();
                msg.what = 0;
                msg.obj = receiveItem;
                handler.sendMessage(msg);
            }
        }).start();

        return null;
    }

    /**
     * 根据url获得图片对象，并存到iamgeItem中
     * @param shareItem
     * @return
     */
    public ShareItem getImageItem(ShareItem shareItem){
        ImageItem imageItem = shareItem.getImageItem();
        URL request;
        InputStream input;
        Drawable drawable = null;
        try {
            request = new URL(imageItem.getUrl());
            input = (InputStream)request.getContent();
            drawable = Drawable.createFromStream(input,"src");
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageItem.setDrawable(drawable);
        return shareItem;
    }

    /**
     * 获得图片的接口
     * 获得到图片之后调用obtainImage方法
     */
    public interface ImageCallBack{

        /**
         * 获取到图片后再调用需要实现执行具体的细节
         * @param shareItem 增加imageItem中的Drawable属性
         */
        void obtainImage(ShareItem shareItem);
    }
}
