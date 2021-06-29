package activitytest.com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button send ;
    Button send2;
    Button send3;
    Button send4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = (Button)findViewById(R.id.send);
        send2 = findViewById(R.id.send2);
        send3 = findViewById(R.id.send3);
        send4 = findViewById(R.id.button4);

        send3.setOnClickListener(this);
        send4.setOnClickListener(this);

        /*OkHttpClient okHttpClient1 = new OkHttpClient();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        Request request = new Request.Builder()
                .url("www.baidu.com")
                .build();
        try {
            //接收到回复Response
            okhttp3.Response response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull okhttp3.Response response) throws IOException {

            }
        });*/

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://guolin.tech/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                sendHttp sendHttp = retrofit.create(sendHttp.class);
                Call<List<Data>> call = sendHttp.getData();
                call.enqueue(new Callback<List<Data>>() {
                    @Override
                    public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                        Log.d("66666666666","66666666666666");
                        for(Data data : response.body()){
                            Log.d("666666",data.getName()+"..."+data.getId());
                        }
                        //Toast.makeText(MainActivity.this,response.body().getId()+"..."+response.body().getName(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<List<Data>> call, Throwable t) {
                        Log.d("77777777777","77777777777");
                        t.printStackTrace();
                    }
                });
            }
        });

        send2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.douban.com/v2/movie/")
                        .addConverterFactory(GsonConverterFactory.create())
                        //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                Map<String,Object> map = new HashMap<>();
                map.put("start",0);
                map.put("count",100);
                map.put("apikey","0b2bdeda43b5688921839c8ecb20399b");
                DouBan douBan = retrofit.create(DouBan.class);
                Call<Theaters> call = douBan.getCall(map);
                call.enqueue(new Callback<Theaters>() {
                    @Override
                    public void onResponse(Call<Theaters> call, Response<Theaters> response) {
                        Log.d("6666",response.body().toString());
                        response.body().show();
                    }

                    @Override
                    public void onFailure(Call<Theaters> call, Throwable t) {
                        Log.d("retrofit", "onFailure: "+t);
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    /*@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send3:
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.apishop.net/common/postcode/")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Map<String,Object> map = new HashMap<>();
                map.put("apiKey","ukw53jRd2e485466de53910151a53f49235007b25d0d15b");
                GetAddrs getAddrs = retrofit.create(GetAddrs.class);
                Call<Addrs> call = getAddrs.getCall(map);
                call.enqueue(new Callback<Addrs>() {
                    @Override
                    public void onResponse(Call<Addrs> call, Response<Addrs> response) {
                        //Log.d("请求地区", response.body().getResult().toString());
                        //response.body().getDesc();
                        //for (Addrs.ResultBean bean : response.body().getResult()){
                        //Log.d("Province",bean.getProvince());
                            /*List<Addrs.ResultBean> list = bean.getResult();
                            for(Addrs.ResultBean bean1 : list){
                                Log.d("Province",bean1.getProvince());
                            }
                    }

                    @Override
                    public void onFailure(Call<Addrs> call, Throwable t) {
                        Log.d("failure", t.toString());
                    }
                });
                break;
            case R.id.button4:
                Retrofit retrofit1 = new Retrofit.Builder()
                        .baseUrl("http://116.62.21.180:8086/")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                GetLogin getLogin = retrofit1.create(GetLogin.class);
                String name = "444";
                String password = "444";
                Call<Login> call1 = getLogin.getCall(name,password);
                call1.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        Log.d("登录接口成功",response.body().getData().getCreateTime());
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Log.d("登录接口","失败");
                    }
                });*/
                /*Log.d("1111111","222222222");
                Retrofit retrofit1 = new Retrofit.Builder()
                        .baseUrl("http://116.62.21.180:8088/")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                String tele = "15148297707";
                Message message = retrofit1.create(Message.class);
                Call<Mes> call1 = message.getCall(tele);
                call1.enqueue(new Callback<Mes>() {
                    @Override
                    public void onResponse(Call<Mes> call, Response<Mes> response) {
                        Log.d("短信发送接口",response.body().getMsg());
                    }

                    @Override
                    public void onFailure(Call<Mes> call, Throwable t) {
                        Log.d("短信发送接口","失败");
                    }
                });*/
        //}
    }
