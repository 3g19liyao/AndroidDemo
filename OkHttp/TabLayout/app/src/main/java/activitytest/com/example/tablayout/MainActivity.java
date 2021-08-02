package activitytest.com.example.tablayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        for(int i = 1;i <= 8;i++){
            TabFragment fragment = new TabFragment("第"+i+"个碎片");
            fragmentList.add(fragment);
        }
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "第"+(position+1)+"个";
            }
        });

        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("第一个"));
        tabLayout.addTab(tabLayout.newTab().setText("第二个"));
        tabLayout.addTab(tabLayout.newTab().setText("第三个"));
        tabLayout.addTab(tabLayout.newTab().setText("第四个"));
        tabLayout.addTab(tabLayout.newTab().setText("第五个"));
        tabLayout.addTab(tabLayout.newTab().setText("第六个"));
        tabLayout.addTab(tabLayout.newTab().setText("第七个"));
        tabLayout.addTab(tabLayout.newTab().setText("第八个"));

        tabLayout.setupWithViewPager(viewPager,false);
    }
}