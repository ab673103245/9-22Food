package org.mobiletrain.food;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.mobiletrain.food.bean.ColBean;
import org.mobiletrain.food.util.AppConfig;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ColActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_col);
        SharedPreferences sp = getSharedPreferences(AppConfig.USERINFO, MODE_PRIVATE);
        final ListView listView = (ListView) findViewById(R.id.lv);
        BmobQuery<ColBean> query = new BmobQuery<>();
        query.addWhereEqualTo("username", sp.getString("username", ""));
        query.findObjects(new FindListener<ColBean>() {
            @Override
            public void done(final List<ColBean> list, BmobException e) {
                if (e == null) {
                    if (list != null && list.size() > 0) {
                        List<String> data = new ArrayList<String>();
                        for (ColBean colBean : list) {
                            data.add(colBean.getName());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ColActivity.this, android.R.layout.simple_list_item_1, data);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(ColActivity.this, DetailActivity.class);
                                intent.putExtra("id", list.get(position).getId());
                                startActivity(intent);
                            }
                        });
                    }
                }
            }
        });
    }
}
