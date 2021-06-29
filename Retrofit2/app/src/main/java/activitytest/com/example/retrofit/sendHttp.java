package activitytest.com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface sendHttp {
    @GET("china/20")
    Call<List<Data>> getData();
}
