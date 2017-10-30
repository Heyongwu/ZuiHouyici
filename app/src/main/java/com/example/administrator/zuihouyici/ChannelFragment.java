package com.example.administrator.zuihouyici;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * Created by 何永武 on 2017/9/21.
 */
public class ChannelFragment extends android.support.v4.app.Fragment{

    private String news_url;
    private View view;
    private PullToRefreshListView vp;
    private List<Psps> list;
    private MyAdapter2 adapter2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        news_url = (String) bundle.get("url");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item7,null);
        inis();
        inpp();
        return view;
    }
    private void inpp() {
        xiala();
    }

    private void inis() {
        vp = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);
        vp.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                //设置下拉时的时间显示
                String label= DateUtils.formatDateTime(getActivity().getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                //更新label显示
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                xiala();
            }


        });
        vp.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                shangti();
            }
        });
    }

    private void xiala() {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                String getpp = new NewWork().getsssss(s);
                return getpp;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Bean bena = new Gson().fromJson(s, Bean.class);
                list = bena.getNewslist();

                adapter2 = new MyAdapter2(list, getActivity());

                vp.setAdapter(adapter2);
                vp.onRefreshComplete();
            }
        }.execute(news_url);
    }
    private void shangti() {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                String getpp = new NewWork().getsssss(s);
                return getpp;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Bean bena = new Gson().fromJson(s, Bean.class);
                list.addAll(bena.getNewslist());
                adapter2.notifyDataSetChanged();
                vp.onRefreshComplete();
            }
        }.execute(news_url);
    }
}
