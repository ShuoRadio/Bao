package com.example.bao.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.bao.R;
import com.example.bao.model.Menu;
import com.example.bao.model.Order;
import com.example.bao.model.Restaurant;
import com.example.bao.utils.MyDBHelper;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter_order extends BaseAdapter {
    List<Order> orderList=new ArrayList<>();
    Context mcontext;

    public MyAdapter_order(List<Order> orderList, FragmentActivity mcontext) {
        this.orderList = orderList;
        this.mcontext = mcontext;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(mcontext).inflate(R.layout.item_order,null);
        ImageView order_icon=convertView.findViewById(R.id.tv_icon);
        TextView order_bname=convertView.findViewById(R.id.tv_bname);
        TextView order_date=convertView.findViewById(R.id.tv_odate);
        TextView order_mname=convertView.findViewById(R.id.tv_mname);
        TextView order_price=convertView.findViewById(R.id.tv_oprice);
        int bid = orderList.get(position).getbId();
        MyDBHelper myDBHelper=new MyDBHelper(mcontext,"Bao.db",null,1);
        Restaurant restaurant = myDBHelper.selectRestaurantbybId(bid);
        order_icon.setImageDrawable(convertView.getContext().getDrawable(restaurant.getbImage()));
        //商家名称
        order_bname.setText(restaurant.getbName());
        Log.i("thb1", restaurant.getbName());
        //日期
        order_date.setText(orderList.get(position).getoDate());
        //菜单
        ArrayList<Integer> mids = orderList.get(position).getmIds();
        StringBuilder sb = new StringBuilder();
        for (int mid:mids){
            Menu menu = myDBHelper.selectMenuBymId(mid);
            String mname=menu.getmName();
            sb.append(mname+"   ");
        }
        String mnames=sb.toString();
        order_mname.setText(mnames);
        order_price.setText("￥"+orderList.get(position).getoPrice());
        return convertView;
    }

}
