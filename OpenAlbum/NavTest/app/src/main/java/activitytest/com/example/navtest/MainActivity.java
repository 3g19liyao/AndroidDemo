package activitytest.com.example.navtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleRegistry;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Binder;
import android.os.Bundle;

//import com.squareup.leakcanary.LeakCanary;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Flow;

import leakcanary.LeakCanary;
import leakcanary.ObjectWatcher;
import leakcanary.internal.AppWatcherInstaller;

//import leakcanary.LeakCanary;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getLifecycle().addObserver();
        LifecycleRegistry
        ObjectWatcher

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment3);
        assert navHostFragment != null;
        NavController controller = navHostFragment.getNavController();

        EventBus.getDefault().post();

    }
}

