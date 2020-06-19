package com.example.bao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.bao.R;
import com.example.bao.model.Restaurant;
import com.example.bao.utils.MyDBHelper;

import java.util.List;

public class ShopListViewAdapter extends BaseAdapter {
    List<Restaurant> restaurantList;
    Context myContext;

    public ShopListViewAdapter(List<Restaurant> restaurantList, Context myContext) {
        this.restaurantList = restaurantList;
        this.myContext = myContext;
    }

    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(myContext).inflate(R.layout.item_main_shoplist,null);

        MyDBHelper myDBHelper=new MyDBHelper(myContext,"Bao.db",null,1);
        ImageView iv_shopimage=view.findViewById(R.id.iv_item_shoplistt);
        TextView tv_shopname=view.findViewById(R.id.tv_item_shopname);
        TextView tv_startmoney=view.findViewById(R.id.tv_shoplist_startmoney);
        TextView tv_monthsale=view.findViewById(R.id.tv_shoplist_mouthsale);
        RatingBar rb_item_shopStar=view.findViewById(R.id.rb_item_shopStar);
        TextView tv_sendmoney=view.findViewById(R.id.tv_shoplist_sendmoney);
        TextView tv_sendtime=view.findViewById(R.id.tv_shoplist_sendtime);
        TextView tv_sendmeter=view.findViewById(R.id.tv_shoplist_sendmeter);
        TextView tv_shoplist_rankNum=view.findViewById(R.id.tv_shoplist_rankNum);
        Restaurant restaurant=restaurantList.get(i);

        //星级评价
//        double rankStar=(Math.random()*2)+3;
        double rankStar=restaurant.getbStar();
        String strrankeStar=String.format("%.1f",rankStar);
        //随机起送费
        String startmoney = String.format("%.0f",Math.random()*21);
        //距离  并计算时间 配送费
//        double dmeter=(Math.random()*500+100);
        String strmeter=restaurant.getbAddress();
        String formatMeter=String.format(strmeter, "%.1f");
        int imeter=(int)(Double.parseDouble(formatMeter)*1000);
        String sendtime=getSendTime(imeter);
        String sendmoney=getsendMoney(imeter);
        //随机月售
        String mouthsale=String.format("%.0f",Math.random()*2000+300);

        //设置
        iv_shopimage.setImageDrawable(view.getContext().getDrawable(restaurant.getbImage()));
        tv_shopname.setText(restaurant.getbName());
        tv_shoplist_rankNum.setText(strrankeStar);
        rb_item_shopStar.setRating(Float.parseFloat(strrankeStar));
        tv_monthsale.setText(mouthsale);
        tv_startmoney.setText(startmoney);
        tv_sendmoney.setText(sendmoney);
        tv_sendtime.setText(sendtime);
        tv_sendmeter.setText(formatMeter);


        return view;
    }

    public String getSendTime(int meter){
        int x=meter/30;
        if(x>60) x=x/60+30;
        return x+"";

    }

    public String getsendMoney(int meter){
        if(meter<=2000) return "1.5";
        else  return "3";
    }
}



