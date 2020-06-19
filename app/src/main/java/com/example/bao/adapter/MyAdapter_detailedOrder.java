package com.example.bao.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bao.R;
import com.example.bao.model.Menu;

import java.util.List;

public class MyAdapter_detailedOrder  extends BaseAdapter {
    List<Menu> menuList;
    Context mContext;


    public MyAdapter_detailedOrder(List<Menu> menuList, Context mContext) {
        this.menuList = menuList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return menuList.size();
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_order_menu, null);
        TextView mname = convertView.findViewById(R.id.tv_order_mname);
        TextView mnumber = convertView.findViewById(R.id.tv_order_mnumber);
        TextView mprice = convertView.findViewById(R.id.tv_order_mprice);

        mname.setText(menuList.get(position).getmName());
        mnumber.setText("x1");
        mprice.setText("￥" + menuList.get(position).getmPrice() + "");
        return convertView;
    }


}

