package activitytest.com.example.viewpager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class MyAdapter extends PagerAdapter {

    private List<String> list;
    private List<View> viewList;
    private Context mContext;

    public MyAdapter(List<String> list, List<View> viewList, Context mContext) {
        this.list = list;
        this.viewList = viewList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        position = position % list.size();
        if(position < 0){
            position = position+list.size();
        }
        container.removeView(viewList.get(position));
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        position = position % list.size();
        if(position < 0){
            position = position+list.size();
        }
        return list.get(position);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        position = position%viewList.size();
        if(position < 0){
            position = position + viewList.size();
        }
        //ImageView view = (ImageView) viewList.get(position);
        /*ViewParent vp = view.getParent();
        if(vp != null){                             //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }*/
        container.addView(viewList.get(position));
        return viewList.get(position);
    }
}
