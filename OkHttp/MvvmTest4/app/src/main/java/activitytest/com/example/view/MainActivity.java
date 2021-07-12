package activitytest.com.example.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import activitytest.com.example.view.databinding.ActivityMainBinding;
import activitytest.com.example.viewModel.MyViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        myViewModel = new MyViewModel();
        activityMainBinding.setVm(myViewModel);
        activityMainBinding.setView(this);
    }
}