package model;

import androidx.databinding.ObservableField;

import java.util.Random;

import db.Account;
import viewModel.LoadDataCallback;

public class Model {

    Account account = new Account("第一个","1111111111");
    public String name = account.getName();
    public String password = account.getPassword();

    public void Load(LoadDataCallback callback){
        boolean is = new Random().nextBoolean();
        if(is){
            int a = new Random().nextInt(20);
            String builder = name+a;
            account.setName(builder);
            account.setPassword(builder);
            name = account.getName();
            password = account.getPassword();
            callback.onSuccess();
        }else{
            callback.onFailure();
        }
    }

}
