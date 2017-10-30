package com.example.administrator.zuihouyici;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private ViewPager vp;
    private RadioGroup rg;
    private ArrayList<Fragment> list;
    private ConnectivityManager con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }
    private void initView() {
        vp = (ViewPager) findViewById(R.id.frag);
        rg = (RadioGroup) findViewById(R.id.rg);
        list = new ArrayList<>();
        list.add(new com.example.administrator.zuihouyici.Fragment());
        list.add(new com.example.administrator.zuihouyici.Fragment02());
        list.add(new com.example.administrator.zuihouyici.Fragment03());
        list.add(new com.example.administrator.zuihouyici.Fragment04());
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.bt1:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.bt2:
                        vp.setCurrentItem(1);
                        break;

                    case R.id.bt3:
                        vp.setCurrentItem(2);
                        break;
                    case R.id.bt4:
                        vp.setCurrentItem(3);
                        break;
                }
            }
        });


    }

}
