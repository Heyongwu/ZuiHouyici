package com.example.administrator.zuihouyici;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 何永武 on 2017/9/21.
 */

public class NewWork {
    public String getsssss(String ss){
        URL url = null;
        String  pp = "";
        HttpURLConnection httpURLConnection = null;
        try {
            url = new URL(ss);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            int code = httpURLConnection.getResponseCode();
            if (code == 200){
                InputStream inputStream = httpURLConnection.getInputStream();
                byte [] b = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(b))!=-1){
                    pp+= new String(b,0,len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return  pp;
    }
}
