package activitytest.com.example.navtest;

import android.app.Activity;
import android.app.Application;

import leakcanary.LeakCanary;
import leakcanary.internal.ActivityDestroyWatcher;
import leakcanary.internal.AndroidHeapDumper;
import leakcanary.internal.AndroidXFragmentDestroyWatcher;
import leakcanary.internal.AppWatcherInstaller;
import leakcanary.internal.InternalAppWatcher;


public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidXFragmentDestroyWatcher
        ActivityDestroyWatcher
        AppWatcherInstaller
        InternalAppWatcher


        LeakCanary

    }
}
