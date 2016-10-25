package org.mobiletrain.food.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by 王松 on 2016/8/3.
 */
public class UserBean extends BmobObject {
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
