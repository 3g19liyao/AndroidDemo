package activitytest.com.example.playvideotest;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button play;
    private Button pause;
    private Button rePlay;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button)findViewById(R.id.play);
        pause = (Button)findViewById(R.id.pause);
        rePlay = (Button)findViewById(R.id.replay);
        videoView = (VideoView)findViewById(R.id.video_item);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        rePlay.setOnClickListener(this);
        videoView.setVideoPath("android.resource://" + MainActivity.this.getPackageName() + "/raw/" + R.raw.);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.play:
                if(!videoView.isPlaying()){
                    videoView.start();
                }
                break;
            case R.id.pause:
                if(videoView.isPlaying()){
                    videoView.pause();
                }
                break;
            case R.id.replay:
                if(videoView.isPlaying()){
                    videoView.resume();
                }
                break;
            default:break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.suspend();
    }
}