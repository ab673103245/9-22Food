package org.mobiletrain.food.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.mobiletrain.food.DetailActivity;
import org.mobiletrain.food.R;
import org.mobiletrain.food.adapter.AdsVpAdapter;
import org.mobiletrain.food.adapter.FgListViewAdapter;
import org.mobiletrain.food.bean.FoodListBean;
import org.mobiletrain.food.net.HttpUtil;
import org.mobiletrain.food.util.AppConfig;
import org.mobiletrain.food.util.JsonParse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王松 on 2016/8/1.
 */
public class BlankFragment extends BaseFragment {

    private ListView listView;
    private ProgressBar pb;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case AppConfig.DOWNLOADSUCCESS:
                    pb.setVisibility(View.GONE);
                    final List<FoodListBean> beanList = (List<FoodListBean>) msg.obj;
                    FgListViewAdapter adapter = new FgListViewAdapter(getActivity(), beanList);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getActivity(), DetailActivity.class);
                            if (getArguments().getInt("index", 0) == 0) {
                                if (position > 0) {
                                    intent.putExtra("id", beanList.get(position - 1).getId());
                                } else {
                                    //点击了ListView的头部
                                }
                            } else {
                                intent.putExtra("id", beanList.get(position).getId());
                            }
                            startActivity(intent);
                        }
                    });
                    break;
                case AppConfig.DOWNLOADFAILED:
                    pb.setVisibility(View.GONE);
                    break;
            }
        }
    };

    public static BlankFragment getInstance(int id, int index) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.blank_fg_layout, container, false);
        initView(view, inflater);
        return view;
    }

    private void initView(View view, LayoutInflater inflater) {
        listView = (ListView) view.findViewById(R.id.lv);
        pb = (ProgressBar) view.findViewById(R.id.pb);
        TextView noDataTv = (TextView) view.findViewById(R.id.no_data_tv);
        listView.setEmptyView(noDataTv);
        noDataTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });

        int index = getArguments().getInt("index");
        //如果当前初始化的是第一个Fragment
        if (index == 0) {
            View adsView = inflater.inflate(R.layout.ads_layout, null);
            ViewPager viewPager = (ViewPager) adsView.findViewById(R.id.viewpager);
            List<String> urls = new ArrayList<>();
            urls.add("http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg");
            urls.add("http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg");
            urls.add("http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg");
            urls.add("http://img.my.csdn.net/uploads/201309/01/1378037234_3539.jpg");
            urls.add("http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg");
            AdsVpAdapter adsVpAdapter = new AdsVpAdapter(getActivity(), urls);
            viewPager.setAdapter(adsVpAdapter);
            listView.addHeaderView(adsView);
        }
        initData();
    }

    private void initData() {
        pb.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = HttpUtil.getJson(String.format(AppConfig.LISTURL, getArguments().getInt("id")));
                if (json != null && !"".equals(json)) {
                    List<FoodListBean> foodListBeen = JsonParse.json2FoodListBean(json);
                    Message msg = mHandler.obtainMessage();
                    msg.what = AppConfig.DOWNLOADSUCCESS;
                    msg.obj = foodListBeen;
                    mHandler.sendMessage(msg);
                } else {
                    mHandler.sendEmptyMessage(AppConfig.DOWNLOADFAILED);
                }
            }
        }).start();
    }
}
