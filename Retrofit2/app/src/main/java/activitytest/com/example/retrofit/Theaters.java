package activitytest.com.example.retrofit;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Theaters {
        private int count; //返回数量
        private int start; //分页量
        private int total; //数据库总数量
        @SerializedName("subjects")
        private List<Subjects> data; //电影的具体信息


        private class Subjects {
            private String title;//电影名
            private String mainland_pubdate;//大陆上映时间
            private String alt;//网页连接
        }

        //定义 输出返回数据 的方法
        public void show() {
            Log.d("返回数量", ""+count);
            Log.d("分页量", ""+start);
            Log.d("数据库总数量", ""+total);

            for (Subjects subjects: data){
                Log.d("电影名", ""+subjects.title);
                Log.d("大陆上映时间", ""+subjects.mainland_pubdate);
                Log.d("网页连接", ""+subjects.alt);
            }
        }
}
