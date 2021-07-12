package activitytest.com.example.view;

import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import activitytest.com.example.view.databinding.ActivityMainBinding;
import activitytest.com.example.viewModel.MyViewModel;



public class MainActivity extends AppCompatActivity {

    MyViewModel viewModel ;
    TextView name;
    TextView password;
    TextView code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MyViewModel.class);
        binding.setVM(viewModel);
        binding.setView(this);

        /*viewModel.getCode().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                code.setText(String.valueOf(integer));
            }
        });
        viewModel.getName().observe(this, new Observer<String>() {

            @Override
            public void onChanged(String integer) {
                name.setText(integer);
            }
        });
        viewModel.getPassword().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String integer) {
                password.setText(integer);
            }
        });*/

        binding.setLifecycleOwner(this);

    }
}