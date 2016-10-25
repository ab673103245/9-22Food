package org.mobiletrain.food.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by 王松 on 2016/8/4.
 */
public class ColBean extends BmobObject {
    private String name;
    private String url;
    private int id;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
