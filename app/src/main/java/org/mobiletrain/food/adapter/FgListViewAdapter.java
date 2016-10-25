package org.mobiletrain.food.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.mobiletrain.food.R;
import org.mobiletrain.food.bean.FoodListBean;

import java.util.List;

/**
 * Created by 王松 on 2016/8/1.
 */
public class FgListViewAdapter extends BaseAdapter {
    private List<FoodListBean> list;
    private Context context;
    private LayoutInflater inflater;

    public FgListViewAdapter(Context context, List<FoodListBean> foodListBeen) {
        this.context = context;
        this.list = foodListBeen;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
            holder = new ViewHolder();
            holder.descrpition = (TextView) convertView.findViewById(R.id.description);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.keywords = (TextView) convertView.findViewById(R.id.keywords);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        FoodListBean foodListBean = list.get(position);
        //初始化Picasso
        Picasso.with(context)
                //设置要加载的图片路径
                .load(foodListBean.getImg())
                //设置默认图片
                .placeholder(R.mipmap.ic_launcher)
                //加载出错时显示的图片
                .error(R.mipmap.ic_launcher)
                //设置图片的缩放模式
//                .centerCrop()
                //设置图片的宽高
//                .resize()
                .into(holder.iv);
        holder.descrpition.setText(foodListBean.getDescription());
        holder.keywords.setText(foodListBean.getKeywords());
        return convertView;
    }

    class ViewHolder {
        ImageView iv;
        TextView descrpition, keywords;
    }
}
