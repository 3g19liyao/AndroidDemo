package activitytest.com.example.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Message {

    @POST("user/getmsgcode.do")
    @FormUrlEncoded()
    Call<Mes> getCall(@Field("phone") String phone);

}
