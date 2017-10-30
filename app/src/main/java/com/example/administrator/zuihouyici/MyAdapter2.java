package com.example.administrator.zuihouyici;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import java.util.List;
/**
 * Created by 何永武 on 2017/9/21.
 */
public class MyAdapter2 extends BaseAdapter {
    private List<Psps> list;
    private Context context;
    public MyAdapter2(List<Psps> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DisplayImageOptions option = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)

                .imageScaleType(ImageScaleType.EXACTLY)
                .build();
        convertView = View.inflate(context,R.layout.item9,null);
        TextView tv = (TextView) convertView.findViewById(R.id.tvs);
        ImageView iv = (ImageView) convertView.findViewById(R.id.ivs);
        tv.setText(list.get(position).getTitle());
        ImageLoader.getInstance().displayImage(list.get(position).getPicUrl(),iv,option);
        return convertView;
    }
}
