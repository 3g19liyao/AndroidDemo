package Model;

import java.util.Random;

import db.Account;

public class MyModel {

    private Account account;

    public Integer requestCode(){
        return new Random().nextInt(100);
    }

    public String requestName(){
        if(account == null){
            account = new Account("第一个","111111");
        }
        return account.getName()+"*";
    }

    public String requestPassword(){
        if(account == null){
            account = new Account("第一个","111111");
        }
        return account.getPassword()+"+";
    }
}
