package ViewModel;


import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import Model.MyModel;

public class MyViewModel extends ViewModel {

    MutableLiveData<Integer> code = new MutableLiveData<>();
    MutableLiveData<String> name = new MutableLiveData<>();
    MutableLiveData<String> password = new MutableLiveData<>();
    private MyModel myModel = new MyModel();

    public MutableLiveData<Integer> getCode(){
        if(code == null){
            code = new MutableLiveData<>();
        }
        Log.d("2222222","2222222222");
        code.setValue(myModel.requestCode());
        Log.d("666", String.valueOf(code.getValue()));
        return code;
    }

    public MutableLiveData<String> getName(){
        Log.d("111111111","11111111");
        if(name == null){
            name = new MutableLiveData<>();
        }
        name.setValue(myModel.requestName());
        return name;
    }

    public MutableLiveData<String> getPassword(){
        if(password == null){
            password = new MutableLiveData<>();
        }
        password.setValue(myModel.requestPassword());
        return password;
    }

    public void load(){
        //name.setValue(myModel.requestName());
        //password.setValue(myModel.requestPassword());
        getPassword();
        getName();
    }

    public void change(){
        //code.setValue(222);
        this.getCode();
    }
}
