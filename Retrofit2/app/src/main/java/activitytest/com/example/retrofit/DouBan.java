package activitytest.com.example.retrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface DouBan {
    @FormUrlEncoded()
    @POST("in_theaters")
    Call<Theaters> getCall(@FieldMap Map<String,Object> map);
}
