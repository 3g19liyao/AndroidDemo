package activitytest.com.example.viewModel;

import android.util.Log;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingComponent;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.net.HttpCookie;

import activitytest.com.example.model.MyModel;

public class MyViewModel extends ViewModel {

    public MutableLiveData<String> code ;//= new MutableLiveData<>();
    MutableLiveData<String> name ;//= new MutableLiveData<>();
    MutableLiveData<String> password ;//= new MutableLiveData<>();

    private MyModel myModel = new MyModel();

    public MutableLiveData<String> getCode(){
        Log.d("111111","222222");
        if(code == null){
            code = new MutableLiveData<>();
        }
        //code.setValue(myModel.requestCode());
        return code;
    }

    public void changeCode(){
        code.setValue(myModel.requestCode());
    }

    public MutableLiveData<String> getName(){
        if(name == null){
            name = new MutableLiveData<>();
        }
        return name;
    }
    public MutableLiveData<String> getPassword(){
        if(password == null){
            password = new MutableLiveData<>();
        }
        return password;
    }

    public void load(){
        changePassword();
        changeName();
    }

    private void changeName() {
        name.setValue(myModel.requestName());
    }

    private void changePassword() {
        password.setValue(myModel.requestPassword());
    }

    public void change(){
        changeCode();
    }

}
