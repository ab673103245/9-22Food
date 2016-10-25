package org.mobiletrain.food;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.mobiletrain.food.bean.UserBean;
import org.mobiletrain.food.util.AppConfig;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegActivity extends BaseActivity {

    private EditText email;
    private EditText password;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setVisibility(View.GONE);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        sp = getSharedPreferences(AppConfig.USERINFO, MODE_PRIVATE);
    }

    public void reg(View view) {
        UserBean userBean = new UserBean();
        userBean.setPassword(password.getText().toString());
        userBean.setUsername(email.getText().toString());
        userBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    sp.edit().putString("username", email.getText().toString())
                            .putString("password", password.getText().toString())
                            .putBoolean("isLogin", false).commit();
                    Toast.makeText(RegActivity.this, "数据添加成功！", Toast.LENGTH_SHORT).show();
                    RegActivity.this.finish();
                } else {
                    Toast.makeText(RegActivity.this, "数据添加失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}