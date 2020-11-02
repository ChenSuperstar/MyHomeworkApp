package com.example.myhomeworkapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class VoteActivity extends AppCompatActivity {

    private Button agreeBtn;
    private Button disagreeBtn;
    private  Button abstationBtn;

    private class VoteTask extends AsyncTask<String, Void, String> {
        private  String TAG = "VoteTask";
        protected String doInBackground(String... params) {
            for (String p: params ) {
                Log.i(TAG, "doInBackground: " + p);
            }
            String ret = doVote(params[0]);
            return ret;
        }
        protected void onPostExecute(String s) {
            Toast.makeText(VoteActivity.this, s, Toast.LENGTH_SHORT).show();
        }}
    private String doVote(String voteStr){
        String retStr = "";
        Log.i("vote", "doVote() voteStr:" + voteStr);
        try {
            StringBuffer stringBuffer = new StringBuffer(); //存储封装好的请求体信息
            stringBuffer.append("r=").append(URLEncoder.encode(voteStr, "utf-8"));
            byte[] data = stringBuffer.toString().getBytes();
            String urlPath = "http://192.168.1.102:8080/vote/GetVote";
            URL url = new URL(urlPath);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setConnectTimeout(3000); //设置连接超时时间
            httpURLConnection.setDoInput(true); //打开输入流，以便从服务器获取数据
            httpURLConnection.setDoOutput(true); //打开输出流，以便向服务器提交数据
            httpURLConnection.setRequestMethod("POST"); //设置以Post方式提交数据
            httpURLConnection.setUseCaches(false); //使用Post方式不能使用缓存
            //设置请求体的类型是文本类型
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //设置请求体的长度
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
            //获得输出流，向服务器写入数据
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(data);
            int response = httpURLConnection.getResponseCode(); //获得服务器的响应码
            if(response == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                retStr = inputStreamToString(inputStream); //处理服务器的响应结果
                Log.i("vote", "retStr:" + retStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retStr;
    }

    private String inputStreamToString(InputStream inputStream) throws IOException {
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

    public String executeHttpGet() {
        String result = null;
        URL url = null;
        HttpURLConnection connection = null;
        InputStreamReader in = null;
        try {
            url = new URL("http://10.0.2.2:8888/data/get/?token=alexzhou");
            connection = (HttpURLConnection) url.openConnection();
            in = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(in);
            StringBuffer strBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                strBuffer.append(line);
            }
            result = strBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } } }
        return result;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        agreeBtn = (Button)findViewById(R.id.agreeBtn);
        disagreeBtn = (Button)findViewById(R.id.disagreeBtn);
        abstationBtn = (Button)findViewById(R.id.abstentionBtn);
    }


    public void onClick(View btn) {
        switch(btn.getId()){
            case R.id.agreeBtn:
                new VoteTask().execute("赞成");
                break;
            case R.id.disagreeBtn:
                new VoteTask().execute("反对");
                break;
            case R.id.abstentionBtn:
                new VoteTask().execute("弃权");
                break;
        }
    }
}