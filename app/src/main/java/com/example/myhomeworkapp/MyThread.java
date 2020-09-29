package com.example.myhomeworkapp;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class MyThread implements Runnable{

    private final String TAG = "Thread";
    private Handler handler;
    private static String rateUrl = "http://www.usd-cny.com/bankofchina.htm";

    public MyThread (Handler handler){
        this.handler = handler;
    }


    @Override
    public void run() {
        Log.i(TAG,"run:run()...");
        Message msg=handler.obtainMessage(5);
        //msg.obj="Hello from run()";
//        msg.obj=getHtmlString("https://www.swufe.edu.cn/");
        msg.obj = getExchangeRate(rateUrl);
        handler.sendMessage(msg);


    }

    public String getHtmlString (String http_url){
        String html=null;
        try {
            URL url=new URL(http_url);
            HttpURLConnection http=(HttpURLConnection)url.openConnection();
            InputStream in =http.getInputStream();
            html=inputStream2String(in);
            Log.i(TAG,"getHtmlString:html="+html);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return html;
    }
    private String inputStream2String(InputStream inputStream) throws IOException {
        final int buffersize=1024;
        final char[] buffer=new char[buffersize];
        final StringBuffer out=new StringBuffer();
        Reader in =new InputStreamReader(inputStream,"utf-8");
        while(true){
            int rsz =in.read(buffer,0,buffer.length);
            if(rsz<0)
                break;
            out.append(buffer,0,rsz);
        }
        return out.toString();
    }

    protected Map<String,Float> getExchangeRate(String url){
        Map<String,Float> map = new HashMap();
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "run"+doc.title());
        Elements tables = (Elements) doc.getElementsByTag("table");

        Element table0 = tables.get(0);

        Elements tds = table0.getElementsByTag("td");
        for(int i = 0; i < tds.size(); i += 6){
            int flag = 0;
            Element td1 = tds.get(i);
            Element td2 = tds.get(i+5);

            String country = td1.text();
            Float rate = Float.parseFloat(td2.text());

            map.put(country, rate);



//            Log.i(TAG, "run: "+ country + "==>"+ rate);
        }
        return map;
    }

}
