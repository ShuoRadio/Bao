package com.example.bao.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bao.R;
import com.example.bao.adapter.BuyMenuAdapter;
import com.example.bao.holder.BuyMenuAdapterInterface;
import com.example.bao.model.Menu;
import com.example.bao.model.Order;
import com.example.bao.model.Restaurant;
import com.example.bao.utils.MyDBHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BuyMenuActivity extends AppCompatActivity implements BuyMenuAdapterInterface {
    private RecyclerView recycleView_buy;
    private ImageView mIvCar; //购物车
    private ImageView shopIcon;
    private RelativeLayout mContainer;
    private TextView mtvNum; //购物车上的数字
    public  int mCount = 0;
    private  TextView tv_shopname;
    private  TextView  tv_buy_price;
    public static  List<Menu> menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_menu);
        getSupportActionBar().hide();

        recycleView_buy=findViewById(R.id.rv_item_buy);
        shopIcon=findViewById(R.id.iv_buy_shopicon);
        mIvCar=findViewById(R.id.iv_shop_cart);
        mContainer= findViewById(R.id.ll_menu_continer);
        mtvNum=findViewById(R.id.tv_car_num);
        Button btn_buy_yes=findViewById(R.id.btn_buy_yes);
        tv_shopname=findViewById(R.id.tv_buy_shopname);
//        tv_buy_price=findViewById(R.id.tv_buy_price);



        //得到传入的数据
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String shopName=bundle.getSerializable("menuName")+"";
        MyDBHelper myDBHelper=new MyDBHelper(this,"Bao.db",null,1);
        final Restaurant restaurant=myDBHelper.selectRestaurant(shopName).get(0);
        final int shopId=restaurant.getbId();
        Log.i("find","找到的商家id为  "+shopId+"店名为"+shopName);
        menuList=myDBHelper.selectMenuBybId(shopId);
        final BuyMenuAdapter buyMenuAdapter =new BuyMenuAdapter(menuList,this);
        recycleView_buy.setLayoutManager(new LinearLayoutManager(this));
        recycleView_buy.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recycleView_buy.setAdapter(buyMenuAdapter);
        shopIcon.setImageDrawable(getDrawable(restaurant.getbImage()));
        tv_shopname.setText(restaurant.getbName());
        final ArrayList<Integer> mIds = new ArrayList<>();
        /**
         * 点击购买按钮
         */
        btn_buy_yes.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String struserId=share_read("my_id_key");
                int userId=Integer.parseInt(struserId);
                List<Menu> returnMenuList=new ArrayList<Menu>(buyMenuAdapter.getMenuList());

                double oPrice=0;
                //获取购买的东西
                for(int i=0;i<returnMenuList.size();i++){
                    while(returnMenuList.get(i).getInBuyCount()>0){
                        Menu nowMenu=returnMenuList.get(i);
                        mIds.add(nowMenu.getmId());
                        oPrice+=nowMenu.getmPrice();
                        returnMenuList.get(i).setInBuyCount(nowMenu.getInBuyCount()-1);
                    }
                }
                Date date= new Date();
                String time=date.toString();
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年-MM月-dd日 HH:mm:ss");
//                String ordertime= simpleDateFormat.format(time);
                Order order=new Order();
                order.setuId(userId);
                order.setbId(shopId);
                order.setmIds(mIds);
//                order.setoDate(ordertime);
                order.setoPrice(oPrice);
                Log.i("BuyMenuActivity", "onClick: "+order.getmIds());
                Intent intent = new Intent(BuyMenuActivity.this,OrderOkActivity.class);
                intent.putExtra("order",order);
                startActivity(intent);

            }
        });


    }

    /**
     *
     * @return 布局最外层
     */
    public RelativeLayout getmContainer() {
        return mContainer;
    }

    /**
     *
     * @return 得到购物车在窗体上的坐标
     */
    public int[] getCarLocation(){
        int[] carLocation = new int[2];
        mIvCar.getLocationInWindow(carLocation);
        return carLocation;
    }

    /**
     * 设置购物车数字显示
     */
    public void setMtvNum(){
        if(mCount>0){
            //若是大于0则图标显示
            mtvNum.setText(String.valueOf(mCount));
            mtvNum.setVisibility(View.VISIBLE);
        }else{
            mtvNum.setVisibility(View.GONE);
        }
    }

    //SharedPreferences读
    private String share_read(String key){
        SharedPreferences sharedPreferences = getSharedPreferences("my_id", Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key,"没有读取到对应值");
        return value;
    }


    @Override
    public void AddMenuCount(int poisition) {
        menuList.get(poisition).setInBuyCount(menuList.get(poisition).getInBuyCount()+1);
    }

    @Override
    public void ReduceMenuCount(int poisition) {
        menuList.get(poisition).setInBuyCount(menuList.get(poisition).getInBuyCount()-1);
    }

    @Override
    public void ReturnZeroMenuCount() {

    }
}
