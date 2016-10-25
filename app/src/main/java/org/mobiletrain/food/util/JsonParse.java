package org.mobiletrain.food.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mobiletrain.food.bean.ClassfyBean;
import org.mobiletrain.food.bean.FoodDetailBean;
import org.mobiletrain.food.bean.FoodListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王松 on 2016/8/1.
 */
public class JsonParse {

    public static List<ClassfyBean> json2ClassfyList(String json) {
        List<ClassfyBean> list = new ArrayList<>();
        try {
            JSONObject jo = new JSONObject(json);
            JSONArray tngou = jo.getJSONArray("tngou");
            for (int i = 0; i < tngou.length(); i++) {
                JSONObject data = tngou.getJSONObject(i);
                String description = data.getString("description");
                String keywords = data.getString("keywords");
                String name = data.getString("name");
                String title = data.getString("title");
                int foodclass = data.getInt("foodclass");
                int id = data.getInt("id");
                int seq = data.getInt("seq");
                list.add(new ClassfyBean(description, foodclass, id, keywords, name, seq, title));
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<FoodListBean> json2FoodListBean(String json) {
        List<FoodListBean> list = new ArrayList<>();
        try {
            JSONObject jo = new JSONObject(json);
            JSONArray tngou = jo.getJSONArray("tngou");
            for (int i = 0; i < tngou.length(); i++) {
                JSONObject data = tngou.getJSONObject(i);
                int count = data.getInt("count");
                int fcount = data.getInt("fcount");
                int id = data.getInt("id");
                int rcount = data.getInt("rcount");
                String description = data.getString("description");
                String disease = data.getString("disease");
                String food = data.getString("food");
                String img = "http://tnfs.tngou.net/image" + data.getString("img");
                String keywords = data.getString("keywords");
                String name = data.getString("name");
                String summary = data.getString("summary");
                String symptom = data.getString("symptom");
                list.add(new FoodListBean(count, description, disease, fcount, food, id, img, keywords, name, rcount, summary, symptom));
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FoodDetailBean json2FoodDetailBean(String jsonStr) {
        FoodDetailBean foodDetailBean = new FoodDetailBean();
        try {
            JSONObject jo = new JSONObject(jsonStr);
            String img = "http://tnfs.tngou.net/image" + jo.getString("img");
            String message = jo.getString("message");
            String name = jo.getString("name");
            foodDetailBean.setImg(img);
            foodDetailBean.setMessage(message);
            foodDetailBean.setName(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return foodDetailBean;
    }
}
