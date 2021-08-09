package activitytest.com.example.serverchat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editText;
    private String port;
    private ServerSocket serverSocket = null;
    private List<Socket> list=new ArrayList<>();
    private openServer openserver;


    class openServer extends Thread implements Runnable {
        public void run() {
            try {
                serverSocket = new ServerSocket(55555);             //设置指定端口
                Log.d("main","asdasdasdasd");
                while (true) {
                    Socket s = serverSocket.accept();                               //接收客户端请求

                    list.add(s);
                    new Thread(new openThread(s, list)).start();                        //为客户端开启线程
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText)findViewById(R.id.port_input);
        Button start=(Button)findViewById(R.id.start);
        Button end=(Button)findViewById(R.id.end);
        start.setOnClickListener(this);
        end.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                port = editText.getText().toString();
                if (isInteger(port) &&  transformation(port)<=65535) {
                    openserver = new openServer();
                    openserver.start();
                    Toast.makeText(this, "开启成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "输入端口错误,开启失败", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.end:
                makeText(this, "关闭成功", LENGTH_SHORT).show();
                try {
                    list.clear();
                    openserver.interrupt();
                    if(serverSocket!=null){
                        serverSocket.close();                       //关闭端口，断开所有客户端连接
                    }
                    Toast.makeText(this, "关闭成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this,"关闭失败", Toast.LENGTH_LONG).show();
                }
                    break;
            default:break;
        }
    }


    public boolean isInteger(String str){
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)<'0' || str.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }
    public int transformation(String str){
        int num=Integer.parseInt(str);
        return num;
    }
}