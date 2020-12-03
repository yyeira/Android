package com.gcu.simple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.database.Observable;
import android.os.Bundle;
import android.util.Log;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.gcu.simple.adapter.ListAdapter;
import com.gcu.simple.model.ImageInfo;
import com.gcu.simple.model.MyModel;
import com.gcu.simple.network.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    MyModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        model = new ViewModelProvider(this).get(MyModel.class);

        ListView listView = findViewById(R.id.listView);
        final ListAdapter adapter = new ListAdapter(new ArrayList<ImageInfo>(), getApplicationContext());
        listView.setAdapter(adapter);

        model.getData().observe(this, new Observer<List<ImageInfo>>(){

            @Override
            public void onChanged(List<ImageInfo> imageInfos) {
                if (imageInfos == null)
                    return;
                adapter.update(imageInfos);
            }
        });

        //Glide.with(getApplicationContext()).load(dataList.get(position).imageUrl).into(ListAdapter.ViewHolder.imageView);
        getData();
    }

    void getData() {
        RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        String url = "https://bing.getlove.cn/latelyBingImageStory";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {


                    @Override
                    public void onResponse(JSONArray response) {
                        //textView.setText("Response: " + response.toString());
                        Log.v("awtt1", String.format("111:%s", response.toString()));
                        JSONArray ar = null;
                        try {
                            ar = response;
                            Log.v("awtt1", String.format("111:%d", ar.length()));
                            List<ImageInfo> rets = new ArrayList<>();
                            for (int i = 0; i < ar.length(); i++) {
                                JSONObject item = ar.getJSONObject(i);
                                String text = item.getString("copyright");
                                String url = item.getString("CDNUrl");
                                url = String.format("https:%s", url);
                                rets.add(new ImageInfo(url, text));
                                Log.v("awtt1", String.format("%s, %s", url, text));
                            }
                            model.getData().setValue(rets);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), String.format("网络错误：%s", error), Toast.LENGTH_SHORT).show();
                        Log.e("wtt", String.format("%s", error));
                    }

                });
        queue.add(jsonObjectRequest);



    }

    void getData2(){
        final TextView textView = (TextView) findViewById(R.id.text);
        // ...

        // 初始化 RequestQueue.
        RequestQueue queue2 = Volley.newRequestQueue(this);

        // 发起一个StringRequest 请求指定网址
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://www.gcu.edu.cn/main.htm",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // 在textview控件上显示最多500个字符的内容
                        textView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 有错误是，显示： 请求数据报错!
                textView.setText("请求数据报错!");
            }
        });

        // 放置到调度队列.
        queue2.add(stringRequest);
    }
}