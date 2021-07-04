package viewModel;

import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.ViewModel;


import activitytest.com.example.mvvmtest.BR;
import model.Model;

public class MainViewModel extends ViewModel implements LoadDataCallback{

    private Model model;

    public MainViewModel() {
        model = new Model();
        this.model = model;
    }


    @Bindable
    public String getName() {
        Log.d("111","111");
        return model.name;
    }

    @Bindable
    public String getPassword(){
        return model.password;
    }

    public void load(){
        model.Load(this);
    }

    @Override
    public void onSuccess() {
        notifyPropertyChanged(BR._all);
    }

    @Override
    public void onFailure() {

    }
}
