package com.example.bao.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bao.activity.OrderDetailedActivity;
import com.example.bao.adapter.MyAdapter_order;
import com.example.bao.utils.MyDBHelper;
import com.example.bao.R;
import com.example.bao.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {
    private  static OrderFragment orderFragment;

    List<Order> orderList;
    MyAdapter_order myAdapter;
    ListView listView;
    MyDBHelper myDBHelper;

    //单例模式
    public static OrderFragment getOrderFragment(){
        if(orderFragment == null){
            orderFragment = new OrderFragment();
            Log.i("test", "OrderFragment被创建");
        }
        return orderFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_order,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView=getActivity().findViewById(R.id.lv_mylist);

        myDBHelper=new MyDBHelper(this.getActivity(),"Bao.db",null,1);
        String my_id_key = share_read("my_id_key");
        int uid = Integer.parseInt(my_id_key);
        orderList= myDBHelper.selectOrder(uid);
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            ArrayList<Integer> mids = order.getmIds();
        }
        myAdapter=new MyAdapter_order(orderList,this.getActivity());
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), OrderDetailedActivity.class);
                intent.putExtra("oId",orderList.get(position).getoId());
                Log.i("OrderFragment", orderList.get(position).getoId()+"");
                startActivity(intent);
            }
        });
    }

    //SharedPreferences读
    private String share_read(String key){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_id", Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key,"没有读取到对应值");
        return value;
    }
}
