package org.mobiletrain.food.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by 王松 on 2016/8/4.
 */
public class GuideVpAdapter extends PagerAdapter {
    private List<ImageView> list;

    public GuideVpAdapter(List<ImageView> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        Log.d("google_lenve_fb", "instantiateItem: " + position);
        ImageView imageView = list.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        Log.d("google_lenve_fb", "destroyItem: " + position);
        container.removeView((View) object);
    }
}
