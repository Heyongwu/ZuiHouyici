package com.example.administrator.zuihouyici;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 何永武 on 2017/9/21.
 */

public class Fragment extends android.support.v4.app.Fragment {
    private static final String JSON_URL = "https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10";
    private ArrayList<View> list = new ArrayList<View>();
    private ViewPager vps;
    private LinearLayout ll;
    private int current = 0 ;
    private int old_dot = 0;
    int p = 0 ;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            vps.setCurrentItem(msg.what);
            list.get(old_dot).setBackgroundResource(R.drawable.dot_normal);
            list.get(msg.what%list.size()).setBackgroundResource(R.drawable.dot_focused);
            old_dot = msg.what%list.size();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item01,container,false);
        vps = (ViewPager) view.findViewById(R.id.vp);
        ll = (LinearLayout) view.findViewById(R.id.ll);
        new AsyncTask<String, Integer, String>() {
            private String sp;

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];

                String getsssss = new NewWork().getsssss(s);

                return getsssss;
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Bean bean = new Gson().fromJson(s, Bean.class);
                final List<Psps> newslist = bean.getNewslist();

                vps.setAdapter(new PagerAdapter() {
                    @Override
                    public int getCount() {
                        return Integer.MAX_VALUE;
                    }

                    @Override
                    public boolean isViewFromObject(View view, Object object) {
                        return view == object;
                    }

                    @Override
                    public Object instantiateItem(ViewGroup container, int position) {
                        ImageView imageView = new ImageView(getActivity());
                        DisplayImageOptions options = new DisplayImageOptions.Builder()
                                .cacheInMemory(true)
                                .cacheOnDisk(true)
                                .bitmapConfig(Bitmap.Config.RGB_565)

                                .imageScaleType(ImageScaleType.EXACTLY)
                                .build();
                        ImageLoader.getInstance().displayImage(newslist.get(position% newslist.size()).getPicUrl(),imageView,options);
                        container.addView(imageView);
                        return imageView;
                    }

                    @Override
                    public void destroyItem(ViewGroup container, int position, Object object) {
                        container.removeView((View) object);
                    }
                });
                //

                vps.setCurrentItem(newslist.size()*100);
                for (int a = 0; a< newslist.size() ; a++){
                    View view = View.inflate(getActivity(),R.layout.xiaoyuandian,null);
                    View dot = view.findViewById(R.id.dot);
                    list.add(dot);
                    ll.addView(view);

                }
                list.get(0).setBackgroundResource(R.drawable.dot_focused);
                p = vps.getCurrentItem();
                new Thread(){
                    @Override
                    public void run() {
                        while(true){
                            try {
                                sleep(5000);
                                Message obtain = Message.obtain();
                                obtain.what = p++;
                                handler.sendMessage(obtain);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();

            }
        }.execute(JSON_URL);

        return view ;

    }
}
