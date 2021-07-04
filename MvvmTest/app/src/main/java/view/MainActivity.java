package view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import activitytest.com.example.mvvmtest.R;
import activitytest.com.example.mvvmtest.databinding.ActivityMainBinding;
import db.Account;
import viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    MainViewModel VM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        //setContentView(R.layout.activity_main);

        MutableLiveData
        MainViewModel viewModel;
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        VM = new MainViewModel();
        binding.setVM(VM);
        binding.setView(this);
    }

    public void sendLoad(View view){
        Log.d("666","555");
        VM.load();
    }
}