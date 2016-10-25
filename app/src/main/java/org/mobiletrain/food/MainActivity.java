package org.mobiletrain.food;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.mobiletrain.food.adapter.MyFgAdapter;
import org.mobiletrain.food.bean.ClassfyBean;
import org.mobiletrain.food.fragment.BlankFragment;
import org.mobiletrain.food.net.HttpUtil;
import org.mobiletrain.food.util.AppConfig;
import org.mobiletrain.food.util.JsonParse;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.完善ColActivity
 * 2.点击收藏时如果用户没有登录则跳转到登录页面
 * 3.收藏按钮的隐藏与显示
 * 4.添加退出登录功能
 * 5.添加后退按钮
 * 6.点赞、评论
 */
public class MainActivity extends BaseActivity {

    private DrawerLayout drawerLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case AppConfig.DOWNLOADSUCCESS:
                    initView(msg);
                    break;
                case AppConfig.DOWNLOADFAILED:
                    break;
            }
        }
    };
    private TextView loginTv;
    private SharedPreferences sp;

    private void initView(Message msg) {
        List<ClassfyBean> beanList = (List<ClassfyBean>) msg.obj;
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < beanList.size(); i++) {
            ClassfyBean classfyBean = beanList.get(i);
            fragments.add(BlankFragment.getInstance(classfyBean.getId(), i));
        }
        MyFgAdapter adapter = new MyFgAdapter(getSupportFragmentManager(), fragments, beanList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences(AppConfig.USERINFO, MODE_PRIVATE);
        sp.edit().putBoolean("isFirstLogin", false).commit();
        initView();
        initData();
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.my_col:
//                        Toast.makeText(MainActivity.this, "我的收藏", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,ColActivity.class));
                        break;
                }
                return false;
            }
        });
        loginTv = (TextView) navigationView.getHeaderView(0).findViewById(R.id.login_tv);
//        loginTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        String username = sp.getString("username", "请登录");
        loginTv.setText(username);
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = HttpUtil.getJson(AppConfig.CLASSFYURL);
                if (json != null && !"".equals(json)) {
                    List<ClassfyBean> classfyList = JsonParse.json2ClassfyList(json);
                    Message message = mHandler.obtainMessage();
                    message.obj = classfyList;
                    message.what = AppConfig.DOWNLOADSUCCESS;
                    mHandler.sendMessage(message);
                } else {
                    mHandler.sendEmptyMessage(AppConfig.DOWNLOADFAILED);
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    public void login(View view) {
        if (sp.getBoolean("isLogin", false)) {
            Toast.makeText(MainActivity.this, "跳转到个人中心页面！", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
