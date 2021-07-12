package activitytest.com.example.model;



import java.util.Random;

import activitytest.com.example.db.User;

public class MyModel {

    private User user = new User("-1","-1");

    public String requestCode(){
        return String.valueOf(new Random().nextInt(100));
    }

    public String requestName(){
        user.setName(String.valueOf(Integer.parseInt(user.getName())+1));
        return "第"+user.getName()+"个人";
    }

    public String requestPassword(){
        user.setPassword(String.valueOf(Integer.parseInt(user.getPassword())+1));
        return "第"+user.getPassword()+"个密码";
    }

}
