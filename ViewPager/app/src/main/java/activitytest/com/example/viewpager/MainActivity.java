package activitytest.com.example.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<View> viewList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private MyAdapter adapter;
    private ViewPager viewPager;
    private TextView textView;
    private Boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        textView = (TextView)findViewById(R.id.text_item);
        adapter = new MyAdapter(titleList,viewList,MainActivity.this);
        viewPager = (ViewPager)findViewById(R.id.pager_item);
        viewPager.setCurrentItem(5000);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                textView.setText(String.valueOf(position+1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setPageTransformer(true,new MyTransformer());
        new Thread(){                                           //实现轮播
            public void run(){
                isRunning = true;
                while(isRunning){
                    try{
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //下一条
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                        }
                    });
                }
            }
        }.start();
    }

    private void initData() {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.pager1,null);
        View view1 = inflater.inflate(R.layout.pager2,null);
        View view2 = inflater.inflate(R.layout.pager3,null);
        View view3 = inflater.inflate(R.layout.pager4,null);
        viewList.add(view);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        titleList.add("第一张");
        titleList.add("第二张");
        titleList.add("第三张");
        titleList.add("第四张");
    }
}