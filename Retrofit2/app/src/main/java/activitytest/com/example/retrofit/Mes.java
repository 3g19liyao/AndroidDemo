package activitytest.com.example.retrofit;

import com.google.gson.Gson;

public class Mes {

    /**
     * status : 0
     * msg : 发送成功
     */

    private int status;
    private String msg;

    public static Mes objectFromData(String str) {

        return new Gson().fromJson(str, Mes.class);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
