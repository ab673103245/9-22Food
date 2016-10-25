package org.mobiletrain.food;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import org.mobiletrain.food.adapter.GuideVpAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    private int currentState;
    private int preState;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        List<ImageView> list = new ArrayList<>();
        int[] imgs = new int[]{R.drawable.p95, R.drawable.p96, R.drawable.p97, R.drawable.p98, R.drawable.p99};
        for (int i = 0; i < imgs.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(imgs[i]);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            list.add(iv);
        }
        GuideVpAdapter adapter = new GuideVpAdapter(list);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //0-》1  position一直为0，positionOffset一直在增大，state一开始为1，当手指松开页面，ViewPager开始自由滑动时，
            //又会调用onPageScrollStateChanged方法，然后调用onPageSelected，最后页面自由滑动，在滑动即将结束的时候，
            //position变为1，positionOffset变为0，state变为0

            //ViewPager在滑动的过程中，首先调用状态改变方法onPageScrollStateChanged，ViewPager的状态由0变为1，
            // 然后调用onPageScrolled方法，在onPageScrolled方法中，position表示一直表示左边页面的位置，
            //positionOffset表示右边页面的偏移量，positionOffsetPixels右边页面的偏移像素
            //当ViewPager滑动时调用该方法
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("google_lenve_fb", "onPageScrolled: position:" + position + ";positionOffset:"
                        + positionOffset + ";positionOffsetPixels:" + positionOffsetPixels);
                if (position == 4 && positionOffset == 0 && currentState == 1) {
                    Log.d("google_lenve_fb", "onPageScrolled: ======================================");
                }
            }

            //当ViewPager手指滑动结束时调用该方法
            @Override
            public void onPageSelected(int position) {
                Log.d("google_lenve_fb", "onPageSelected: " + position);
                currentPosition = position;
            }

            //当ViewPager的状态改变时调用该方法
            //0 静止状态
            //1 手指拖动页面
            //2 手指松开页面，ViewPager自由滑动时的状态
            //一个完整的ViewPager滑动过程，状态值的改变是：0-》1-》2-》0

            //只有在第一个页面向右滑动以及最后一个页面向左滑动时，state值的改变为0-》1-》0
            @Override
            public void onPageScrollStateChanged(int state) {
                currentState = state;
                Log.d("google_lenve_fb", "onPageScrollStateChanged: " + state);
                if (state == 0 && preState == 1 && currentPosition == 4) {
                    Log.d("google_lenve_fb", "onPageScrollStateChanged: +++++++++++++++++++++");
                    startActivity(new Intent(GuideActivity.this, MainActivity.class));
                    GuideActivity.this.finish();
                }
                preState = state;
            }
        });
    }
}
