package activitytest.com.example.viewpager;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class MyTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(@NonNull View page, float position) {
        if(position <= -1 || position >=1){
            page.setAlpha(0);
        }else if(position < 0){
            page.setAlpha(1+position);
            page.setScaleY((float) (1 - (0.35 * -position)));
        }else if(position > 0){
            page.setAlpha(1-position);
            page.setScaleY((float) (1 - (0.35 * position)));
        }
    }
}
