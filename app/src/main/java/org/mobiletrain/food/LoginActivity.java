package org.mobiletrain.food;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.mobiletrain.food.bean.UserBean;
import org.mobiletrain.food.util.AppConfig;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LoginActivity extends BaseActivity {

    private EditText password;
    private EditText username;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        sp = getSharedPreferences(AppConfig.USERINFO, MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        username.setText(sp.getString("username", ""));
        password.setText(sp.getString("password", ""));
    }

    public void login(View view) {
        BmobQuery<UserBean> query = new BmobQuery<>();
        query.addWhereEqualTo("password", password.getText().toString());
        query.addWhereEqualTo("username", username.getText().toString());
        query.findObjects(new FindListener<UserBean>() {
            @Override
            public void done(List<UserBean> list, BmobException e) {
                if (e == null) {
                    if (list != null && list.size() > 0) {
                        Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                        sp.edit().putString("username", username.getText().toString())
                                .putString("password", password.getText().toString())
                                .putBoolean("isLogin", true).commit();
                        LoginActivity.this.finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void reg(View view) {
        startActivity(new Intent(this, RegActivity.class));
    }
}
