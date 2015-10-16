package com.sunsr.android.shareapp.list;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.sunsr.android.shareapp.R;
import com.sunsr.android.shareapp.list.bean.ImageItem;
import com.sunsr.android.shareapp.list.bean.ShareItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 分享列表主页
 * Created by Administrator on 2015-10-15.
 */
public class ShareListActivity extends Activity {

    private Context context;                //上下文
    private ListView shareListView;         //分享列表
    private List<ShareItem> shareList = new ArrayList<ShareItem>();      //获得的分享数据


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);

        //设置列表Adapter
        context = this;
        shareListView = (ListView) findViewById(R.id.shareList);
        for(int i=0;i<10;i++){
            ShareItem shareItem = new ShareItem();
            shareItem.setTitle("这是标题");
            ImageItem imageItem = new ImageItem();
            imageItem.setUrl("http://e.hiphotos.baidu.com/image/h%3D200/sign=1d9e96bac25c10383b7ec9c28210931c/e1fe9925bc315c60c066fd4f89b1cb134854779b.jpg");
            shareItem.setImageItem(imageItem);
            shareList.add(shareItem);
        }
        ShareListAdapter adapter = new ShareListAdapter(context,shareList);
        shareListView.setAdapter(adapter);


    }
}
