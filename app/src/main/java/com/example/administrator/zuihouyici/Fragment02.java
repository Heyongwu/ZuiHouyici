package com.example.administrator.zuihouyici;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 何永武 on 2017/9/21.
 */

public class Fragment02 extends android.support.v4.app.Fragment {
    private static final String JSON_URL = "https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10";
    private PullToRefreshListView vp;
    private MyAdapter2 adapter2;
//    private List<Psps> list;
    private View view;



    private String [] channes = {"关注","推荐","视图","军事","团结","煞笔"};
    private String [] urls = {"https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10","https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10","https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10","https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10","https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10","https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10"};
    private List<ChannelFragment> list = new ArrayList<>();
    private ChannelFragment fragment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item02,container,false);
        pop();
        return view;
    }

    private void pop() {
        for (int i = 0 ; i>channes.length ; i++){
            fragment = new ChannelFragment();
            new Bundle()
        }

    }


}
