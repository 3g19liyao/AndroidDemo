package activitytest.com.example.chatclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText name_input;
    private EditText ip_input;
    private EditText port_input;
    Button enter;
    private boolean a=false;
    private int imageId;
    private String name;
    private String ip;
    private String port;
    private OutputStream os;
    private InputStream is;
    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initial();                                  //初始化

        Intent intent=getIntent();
        if(intent!=null){
            Toast.makeText(this, "请输入相应Ip和端口号！", Toast.LENGTH_SHORT).show();
        }
    }

    private void initial() {
        name_input=findViewById(R.id.name_input);
        ip_input=findViewById(R.id.ipname_input);
        port_input=findViewById(R.id.portname_input);
        enter=findViewById(R.id.enter);
        enter.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        name = name_input.getText().toString();
        port = port_input.getText().toString();
        ip = ip_input.getText().toString();
        final int p = Integer.parseInt(port);
        if (p > 1023 && p < 65536) {
            final Intent intent = new Intent(MainActivity.this, SelectPhoto.class);
            intent.putExtra("name", name);
            intent.putExtra("ip", ip);
            intent.putExtra("port", port);
            intent.putExtra("imageId", imageId);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"端口输入有误",Toast.LENGTH_LONG).show();
        }

    }
}