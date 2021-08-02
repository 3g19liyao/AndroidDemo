package activitytest.com.example.chatclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;

public class SelectPhoto extends AppCompatActivity implements View.OnClickListener{

    private Socket socket=null;
    private String name;
    private String ip;
    private String port;
    private int imageId;
    private Button[] buttons=new Button[9];
    private int[] Id_button=new int[]{R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_photo);

        for(int i=0;i<9;i++){
            buttons[i]=(Button) findViewById(Id_button[i]);
            buttons[i].setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:imageId=1;goOn();break;
            case R.id.button2:imageId=2;goOn();break;
            case R.id.button3:imageId=3;goOn();break;
            case R.id.button4:imageId=4;goOn();break;
            case R.id.button5:imageId=5;goOn();break;
            case R.id.button6:imageId=6;goOn();break;
            case R.id.button7:imageId=7;goOn();break;
            case R.id.button8:imageId=8;goOn();break;
            case R.id.button9:imageId=9;goOn();break;
            default:break;
        }
    }

    public void goOn(){
        Intent intent = getIntent();                                //获取用户信息
        name = intent.getStringExtra("name");
        ip = intent.getStringExtra("ip");
        port = intent.getStringExtra("port");

        Intent intent1=new Intent(SelectPhoto.this,CharRoom.class);
        intent1.putExtra("name",name);
        intent1.putExtra("ip",ip);
        intent1.putExtra("port",port);
        intent1.putExtra("imageId",imageId);
        startActivity(intent1);
    }
}