package activitytest.com.example.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GetLogin {
    @POST("user/login")
    @FormUrlEncoded()
    Call<Login> getCall(@Field("username") String username,@Field("password") String password);
}
