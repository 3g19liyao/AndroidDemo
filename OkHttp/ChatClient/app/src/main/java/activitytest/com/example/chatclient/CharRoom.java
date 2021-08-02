package activitytest.com.example.chatclient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CharRoom extends AppCompatActivity implements View.OnClickListener {
    private EditText inputText;                 //输入信息
    Button send;                                //发送按键
    Button edit;                                //回退按键
    RecyclerView msgRecyclerView;             //聊天信息控件
    private boolean a=false;
    private Socket socket=null;
    private OutputStream os;
    private InputStream is;
    private String name;
    private String ip;
    private String port;
    private int imageId;
    private List<Msg> msgList;
    private MsgAdapter adapter;
    String sMsg;
    StringBuilder stringBuilder=new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_room);

        initialize();                           //信息初始化
        serverConnect();                        //与服务端取得连接

        update();                              //获取聊天记录
        openNewThread();                        //打开线程

    }

    public void serverConnect() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath().build());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return;
                        }
                    }).start();
                    socket = new Socket(ip, Integer.parseInt(port));
                    a = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (a) {
            Intent intent=new Intent(CharRoom.this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "链接失败,请重新输入！", Toast.LENGTH_SHORT).show();
            //finish();
        }
        /*try {
            socket = new Socket(ip,Integer.parseInt(port));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*if(socket!=null){
            Toast.makeText(this,"连接成功！",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"找不到服务器,请重新登录！",Toast.LENGTH_LONG).show();
            finish();
        }*/
        try {
            os=socket.getOutputStream();
            is=socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openNewThread() {

        new Thread(new Runnable() {                                 //开启线程,接收消息

            @Override
            public void run() {
                byte[] arr = new byte[1024 * 4];
                try {
                    while (true) {
                        int len;
                        while (-1 != (len = is.read(arr))) {
                            String rMsg = new String(arr, 0, len);
                            if(rMsg!=""){
                                final Msg msg = new Msg(rMsg.substring(9,rMsg.indexOf("来")),rMsg.substring(0,1),rMsg.substring(rMsg.indexOf("来")+3,rMsg.length()),rMsg.substring(1,9), Msg.TYPE_RECEIVED);
                                msg.save();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        msgList.add(msg);
                                        adapter.notifyItemInserted(msgList.size() - 1);     //有新消息时，刷新RecycleView中的显示
                                        msgRecyclerView.scrollToPosition(msgList.size() - 1);           //将RecyclerView定位到最后一行
                                    }
                                });
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void update() {
        LitePal.getDatabase();
        List<Msg> litepal= DataSupport.findAll(Msg.class);

        for(Msg msg:litepal){
            if(!msg.getName().equals(this.name)){
                msg.setType(0);
            }else{
                msg.setType(1);
            }
            msgList.add(msg);
            adapter.notifyItemInserted(msgList.size() - 1);     //有新消息时，刷新RecycleView中的显示
            msgRecyclerView.scrollToPosition(msgList.size() - 1);           //将RecyclerView定位到最后一行
        }
    }
    private void initialize() {


        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button)findViewById(R.id.send);
        edit = (Button)findViewById(R.id.edit_up);
        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycle_view);
        edit.setOnClickListener(this);
        send.setOnClickListener(this);

        msgList=new ArrayList<>();
        Intent intent = getIntent();                                //获取用户信息
        name = intent.getStringExtra("name");
        ip = intent.getStringExtra("ip");
        port = intent.getStringExtra("port");
        imageId=intent.getIntExtra("imageId",1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);      //指定Recyclerview布局为线性布局
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter=new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edit_up:
                AlertDialog.Builder dia = new AlertDialog.Builder(CharRoom.this);           //设置一个消息框
                dia.setTitle("退出");
                dia.setMessage("确定退出登录吗？");
                dia.setCancelable(true);
                dia.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();                                                       //退出
                    }
                });
                dia.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dia.show();
                break;
            case R.id.send:
                try {
                    sMsg = inputText.getText().toString();
                    if (!"".equals(sMsg)) {
                        String date = new SimpleDateFormat("hh:mm:ss").format(new Date());
                        stringBuilder.append(imageId).append(date).append(sMsg).append("来自：").append(name);
                        sMsg = stringBuilder.toString();
                        os.write(sMsg.getBytes());
                        stringBuilder=new StringBuilder();
                        Msg msg = new Msg(sMsg.substring(9,sMsg.indexOf("来")),sMsg.substring(0,1),sMsg.substring(sMsg.indexOf("来")+3,sMsg.length()),sMsg.substring(1,9), Msg.TYPE_SENT);                     //指定为发送
                        msg.save();
                        msgList.add(msg);
                        adapter.notifyItemInserted(msgList.size() - 1);
                        msgRecyclerView.scrollToPosition(msgList.size() - 1);
                        inputText.setText("");                                              //清空输入文本框
                        Toast.makeText(this,"已发送",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}