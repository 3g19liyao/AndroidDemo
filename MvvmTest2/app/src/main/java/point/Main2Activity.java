package point;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ViewModel.MyViewModel;
import activitytest.com.example.mvvmtest2.R;
import activitytest.com.example.mvvmtest2.databinding.ActivityMain2Binding;

public class Main2Activity extends AppCompatActivity {
    MyViewModel VM;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMain2Binding binding = DataBindingUtil.setContentView(this,R.layout.activity_main2);
        VM = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MyViewModel.class);
        binding.setVM(VM);

        VM.getCode().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.textview.setText(String.valueOf(integer));
            }
        });
        //binding.setLifecycleOwner(this);
        /*button = (Button)findViewById(R.id.send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0;i < 5;i++){
                    Log.d("11","11");
                    VM.change();
                }
            }
        });*/
    }
}