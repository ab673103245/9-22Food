package org.mobiletrain.food;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.mobiletrain.food.bean.ColBean;
import org.mobiletrain.food.bean.FoodDetailBean;
import org.mobiletrain.food.net.HttpUtil;
import org.mobiletrain.food.util.AppConfig;
import org.mobiletrain.food.util.JsonParse;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class DetailActivity extends BaseActivity {

    private ImageView iv;
    private TextView summary;
    private boolean isCol;
    private SharedPreferences sp;
    private int id;
    private FoodDetailBean detailBean;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case AppConfig.DOWNLOADSUCCESS:
                    detailBean = (FoodDetailBean) msg.obj;
                    Picasso.with(DetailActivity.this).load(detailBean.getImg()).into(iv);
                    //Html.fromHtml()解析带有HTML标签的文本
                    summary.setText(Html.fromHtml(detailBean.getMessage()));
                    break;
            }
        }
    };
    private ImageView colIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        sp = getSharedPreferences(AppConfig.USERINFO, MODE_PRIVATE);
        id = getIntent().getIntExtra("id", 0);
        iv = (ImageView) findViewById(R.id.iv);
        summary = (TextView) findViewById(R.id.summary);
        colIv = (ImageView) findViewById(R.id.col_iv);
        BmobQuery<ColBean> query = new BmobQuery<>();
        query.addWhereEqualTo("url", String.format(AppConfig.DETAILURL, id));
        query.addWhereEqualTo("username", sp.getString("username", ""));
        query.findObjects(new FindListener<ColBean>() {
            @Override
            public void done(List<ColBean> list, BmobException e) {
                if (e == null) {
                    //该item已经被收藏
                    if (list != null && list.size() > 0) {
                        colIv.setImageResource(android.R.drawable.btn_star_big_on);
                        isCol = true;
                    } else {
                        colIv.setImageResource(android.R.drawable.btn_star_big_off);
                        isCol = false;
                    }
                }
            }
        });
        initData(id);
    }

    private void initData(final int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = HttpUtil.getJson(String.format(AppConfig.DETAILURL, id));
                if (json != null && !"".equals(json)) {
                    FoodDetailBean foodDetailBean = JsonParse.json2FoodDetailBean(json);
                    Message msg = mHandler.obtainMessage();
                    msg.what = AppConfig.DOWNLOADSUCCESS;
                    msg.obj = foodDetailBean;
                    mHandler.sendMessage(msg);
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

    public void colClick(View view) {
        //如果该食物已经被收藏，则取消收藏，否则收藏该食物
        if (isCol) {
            BmobQuery<ColBean> query = new BmobQuery<>();
            query.addWhereEqualTo("username", sp.getString("username", ""));
            query.addWhereEqualTo("url", String.format(AppConfig.DETAILURL, id));
            query.findObjects(new FindListener<ColBean>() {
                @Override
                public void done(List<ColBean> list, BmobException e) {
                    if (e == null) {
                        if (list != null && list.size() > 0) {
                            ColBean colBean = list.get(0);
                            colBean.delete(new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        colIv.setImageResource(android.R.drawable.star_big_off);
                                        Toast.makeText(DetailActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                                        isCol = !isCol;
                                    } else {
                                        Toast.makeText(DetailActivity.this, "取消收藏失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                }
            });

        } else {
            //如果收藏一个item，则向Bmob中存储一条数据
            final ColBean colBean = new ColBean();
            colBean.setId(id);
            colBean.setName(detailBean.getName());
            colBean.setUsername(sp.getString("username", ""));
            colBean.setUrl(String.format(AppConfig.DETAILURL, id));
            colBean.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    colIv.setImageResource(android.R.drawable.star_big_on);
                    isCol = !isCol;
                    Toast.makeText(DetailActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
