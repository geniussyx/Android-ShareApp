package com.sunsr.android.shareapp.list;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sunsr.android.shareapp.R;
import com.sunsr.android.shareapp.list.bean.ImageItem;
import com.sunsr.android.shareapp.list.bean.ShareItem;
import com.sunsr.android.shareapp.util.AsyncImageLoader;

import java.util.List;

/**
 * 分享列表的渲染器
 * Created by Administrator on 2015-10-15.
 */
public class ShareListAdapter extends BaseAdapter {

    private List<ShareItem> shareItems;         //分享的所有项目
    private Context context;                     //上下文
    private AsyncImageLoader loader;                // 异步加载图片类

    public ShareListAdapter(Context context,List<ShareItem> shareItems){
        this.context = context;
        this.shareItems = shareItems;
        loader = new AsyncImageLoader();
    }

    @Override
    public int getCount() {
        return shareItems.size();
    }

    @Override
    public Object getItem(int position) {
        return shareItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.main_list_item,null);
        ImageItem imageItem = shareItems.get(position).getImageItem();
        Drawable drawable =null;
        final View itemView = convertView;
        drawable = loader.loadDrawable(shareItems.get(position),new AsyncImageLoader.ImageCallBack() {
            @Override
            public void obtainImage(ShareItem receiveItem) {
                ImageView imageView = (ImageView)itemView.findViewById(R.id.shareImg);
                imageView.setImageDrawable(receiveItem.getImageItem().getDrawable());
                TextView titleView = (TextView)itemView.findViewById(R.id.title);
                titleView.setText(receiveItem.getTitle());
            }
        });
        return convertView;
    }
}
