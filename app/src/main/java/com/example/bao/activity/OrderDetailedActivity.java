package com.example.bao.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bao.R;
import com.example.bao.model.Customer;
import com.example.bao.model.Menu;
import com.example.bao.model.Order;
import com.example.bao.model.Restaurant;
import com.example.bao.adapter.MyAdapter_detailedOrder;
import com.example.bao.utils.MyDBHelper;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailedActivity extends Activity {
    ImageView btn_back;
    TextView tv_bname;
    TextView order_price;
    TextView order_address;
    TextView order_phone;
    TextView order_id;
    TextView order_time;
    MyAdapter_detailedOrder myAdapter_detailedOrder;
    ListView listView;
    MyDBHelper myDBHelper;
    List<Menu> menuList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detailed);
        order_price=findViewById(R.id.order_money);
        order_address=findViewById(R.id.order_address);
        order_phone=findViewById(R.id.order_phone);
        order_id=findViewById(R.id.order_number);
        order_time=findViewById(R.id.order_otime);


        btn_back=findViewById(R.id.iv_order_back);
       btn_back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
//               Intent intent = new Intent(OrderDetailedActivity.this, ContainActivity.class);
//               intent.putExtra("id", "order");
//               startActivity(intent);
               finish();
           }
       });
        listView=findViewById(R.id.lv_detailedOrder);
        MyDBHelper myDBHelper=new MyDBHelper(OrderDetailedActivity.this,"Bao.db",null,1);
        Intent intent=getIntent();
        int oId = intent.getIntExtra("oId", 0);
        Order order = myDBHelper.selectOrderByoId(oId);
        ArrayList<Integer> mIds = order.getmIds();
        int size=mIds.size();
        List<Menu> menuList=new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Menu menu = myDBHelper.selectMenuBymId(mIds.get(i));
            Log.i("OrderFragment",mIds.get(i)+"" );
            menuList.add(menu);
        }
        myAdapter_detailedOrder=new MyAdapter_detailedOrder(menuList,this);
        listView.setAdapter(myAdapter_detailedOrder);

        tv_bname=findViewById(R.id.tv_order_bname);
        Restaurant restaurant = myDBHelper.selectRestaurantbybId(order.getbId());
        String bname = restaurant.getbName();
        tv_bname.setText(bname);

        order_price.setText("￥ "+order.getoPrice());
        int uid = order.getuId();
        Customer customer = myDBHelper.selectCustomerbyId(uid);
        order_address.setText(customer.getuAddress()+"");
        order_phone.setText(customer.getuPhone()+"");
        order_id.setText(order.getoId()+"");
        order_time.setText(order.getoDate());

    }
}
