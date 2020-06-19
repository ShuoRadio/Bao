package com.example.bao.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bao.R;
import com.example.bao.adapter.MyAdapter_detailedOrder;
import com.example.bao.model.Customer;
import com.example.bao.model.Menu;
import com.example.bao.model.Order;
import com.example.bao.model.Restaurant;
import com.example.bao.utils.MyDBHelper;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderOkActivity extends Activity {
    TextView tv_ok_pay_price;
    ImageView btn_back;
    EditText address;
    TextView uname;
    TextView phone;
    TextView time;
    TextView bname;
    TextView price;
    MyAdapter_detailedOrder myAdapter_detailedOrder;
    ListView listView;
    MyDBHelper myDBHelper;
    List<Menu> menuList;
    Button btn_pay;
    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ok);
        address=findViewById(R.id.et_ok_address);
        uname=findViewById(R.id.tv_ok_uname);
        phone=findViewById(R.id.tv_ok_phone);
        time=findViewById(R.id.tv_ok_sendtime);
        bname=findViewById(R.id.tv_ok_bname);
        price=findViewById(R.id.tv_ok_price);
        btn_back=findViewById(R.id.iv_ok_back);
        btn_pay=findViewById(R.id.btn_ok_pay);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(OrderOkActivity.this,BuyMenuActivity.class);
//                startActivity(intent);
                finish();
            }
        });
        order = (Order)getIntent().getSerializableExtra("order");
        Log.i("OrderOkActivity", "onCreate: "+order);
        double oprice = order.getoPrice();
        DecimalFormat dFormat = new DecimalFormat("#.00");
        String str = dFormat.format(oprice);
        order.setoPrice(Double.parseDouble(str));
        price.setText("￥"+str);
        final int uid = order.getuId();
        MyDBHelper myDBHelper=new MyDBHelper(OrderOkActivity.this,"Bao.db",null,1);
        Customer customer = myDBHelper.selectCustomerbyId(uid);
        String u_address = customer.getuAddress();
        //地址
        address.setText(u_address);
        tv_ok_pay_price=findViewById(R.id.tv_ok_pay_price);
        tv_ok_pay_price.setText("￥"+order.getoPrice());
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String time_current = simpleDateFormat.format(date);
        int hour=Integer.parseInt(time_current.substring(0,2));
        int minute = Integer.parseInt(time_current.substring(3));
        minute+=30;
        if (minute>=60){
            minute-=60;
            hour++;
        }
        String time_hour;
        String time_minute;
        if (minute<10){
            time_minute="0"+Integer.toString(minute);
        }else{
            time_minute=Integer.toString(minute);
        }

        if (hour<10){
            time_hour="0"+Integer.toString(hour);
        }else{
            time_hour=Integer.toString(hour);
        }
        String time_send="约"+time_hour+":"+time_minute+"送达";
        //显示预计送达时间
        time.setText(time_send);
        //显示姓名
        uname.setText(customer.getuName());
        //显示联系电话
        phone.setText(customer.getuPhone());
        int bid = order.getbId();
        Restaurant restaurant = myDBHelper.selectRestaurantbybId(bid);
        bname.setText(restaurant.getbName());
        ArrayList<Integer> mids = order.getmIds();
        listView=findViewById(R.id.lv_order_ok);
        int size=mids.size();
        List<Menu> menuList=new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Menu menu = myDBHelper.selectMenuBymId(mids.get(i));
            Log.i("OrderFragment",mids.get(i)+"" );
            menuList.add(menu);
        }
        myAdapter_detailedOrder=new MyAdapter_detailedOrder(menuList,this);
        listView.setAdapter(myAdapter_detailedOrder);

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = simpleDateFormat.format(date);
                order.setoDate(time);
                order.setoAddress(address.getText().toString());
                Log.i("thb1", "onClick: "+order);
                MyDBHelper myDBHelper=new MyDBHelper(OrderOkActivity.this,"Bao.db",null,1);
                ContentValues contentValues = new ContentValues();
                contentValues.put("uId",order.getuId());
                contentValues.put("bId",order.getbId());
                ArrayList<Integer> mids = order.getmIds();
                StringBuffer str = new StringBuffer();
                for (int i = 0; i < mids.size(); i++) {
                    if (i<mids.size()-1){
                        str.append(Integer.toString(mids.get(i))+",");
                    }else{
                        str.append(Integer.toString(mids.get(i)));
                    }
                }
                contentValues.put("mIds",str.toString());
                contentValues.put("oDate",order.getoDate());
                contentValues.put("oAddress",order.getoAddress());
                contentValues.put("oPrice",order.getoPrice());
                myDBHelper.insertOrder(contentValues);

                Toast.makeText(OrderOkActivity.this,"成功下单",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OrderOkActivity.this,MainActivity.class);
                intent.putExtra("modi_fragment","打开我的订单");
                startActivity(intent);
            }
        });
    }
}
