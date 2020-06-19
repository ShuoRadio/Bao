package com.example.bao.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bao.R;
import com.example.bao.fragment.MainFragment;
import com.example.bao.fragment.MineFragment;
import com.example.bao.fragment.OrderFragment;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    RadioGroup rg_main;
    //三个点击切换按钮
    RadioButton radioButton0 ;
    RadioButton radioButton1;
    RadioButton radioButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        try {
            String file="filename.txt";                           //定义文件名
            FileInputStream fileInputStream = openFileInput(file);//获取指定文件的文件输入流
            byte[] buffer = new byte[fileInputStream.available()];//定义一个字节缓存数组
            fileInputStream.read(buffer);                         //将数据读到缓存区
            fileInputStream.close();                              //关闭文件输入流

            FileOutputStream fileOutputStream= openFileOutput(file, Context.MODE_PRIVATE);
            String strContent="陈烁文牛逼";
            fileOutputStream.write(strContent.getBytes()); //将内容写入文件
            fileOutputStream.close();
        } catch (Exception e){

        }




        MainFragment mainFragment= MainFragment.getMainFragment();
        changeFragment(mainFragment);

        //从ModifyInfoActivity传值判断打开MineFragment界面
        Intent intent = getIntent();
        if(intent.getStringExtra("modi_fragment")!=null&&intent.getStringExtra("modi_fragment").equals("打开我的界面")){
            MineFragment mineFragment = new MineFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fg_layout,mineFragment);
            transaction.commit();
        }
        if(intent.getStringExtra("modi_fragment")!=null&&intent.getStringExtra("modi_fragment").equals("打开我的订单")){
            OrderFragment orderFragment = new OrderFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fg_layout,orderFragment);
            transaction.commit();
        }

        //点击按钮切换界面fragment
        rg_main=findViewById(R.id.rg_main);
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                for(int n=0;n<rg_main.getChildCount();n++){
                    RadioButton radioButton=(RadioButton) radioGroup.getChildAt(n);
                    if(radioButton.isChecked()){
//                        Toast.makeText(getBaseContext(),"第"+n+"个Fragment",Toas t.LENGTH_SHORT).show();
                        setIndexSected(n,rg_main);
                        break;
                    }
                }

            }
        });
    }
    //加载fragment布局方法
    private void changeFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fg_layout,fragment);
        transaction.commit();

    }



    //通过判断点击的radioButon 来选择加载fragment
    public void setIndexSected(int index,RadioGroup radioGroup){
        radioButton0=(RadioButton) radioGroup.getChildAt(0);
        radioButton1=(RadioButton) radioGroup.getChildAt(1);
        radioButton2=(RadioButton) radioGroup.getChildAt(2);

        Drawable top00= getResources().getDrawable(R.drawable.main_eat1);
        radioButton0.setCompoundDrawablesWithIntrinsicBounds(null,top00,null,null);
        radioButton0.setTextColor(getResources().getColor(R.color.main_icon));

        Drawable top11= getResources().getDrawable(R.drawable.main_order1);
        radioButton1.setCompoundDrawablesWithIntrinsicBounds(null,top11,null,null);
        radioButton1.setTextColor(getResources().getColor(R.color.main_icon));

        Drawable top22= getResources().getDrawable(R.drawable.main_mine1);
        radioButton2.setCompoundDrawablesWithIntrinsicBounds(null,top22,null,null);
        radioButton2.setTextColor(getResources().getColor(R.color.main_icon));

        switch (index)
        {
            case 0: //加载外卖界面
                changeFragment(MainFragment.getMainFragment());
                Drawable top0= getResources().getDrawable(R.drawable.main_eat2);
                radioButton0.setCompoundDrawablesWithIntrinsicBounds(null,top0,null,null);
                radioButton0.setTextColor(getResources().getColor(R.color.theme));
                break;
            case 1: //加载订单界面
                changeFragment(OrderFragment.getOrderFragment());
                Drawable top1= getResources().getDrawable(R.drawable.main_order2);
                radioButton1.setCompoundDrawablesWithIntrinsicBounds(null,top1,null,null);
                radioButton1.setTextColor(getResources().getColor(R.color.theme));
                break;
            case 2: //加载我的界面
                changeFragment(MineFragment.getMineFragment());
                Drawable top2= getResources().getDrawable(R.drawable.main_mine2);
                radioButton2.setCompoundDrawablesWithIntrinsicBounds(null,top2,null,null);
                radioButton2.setTextColor(getResources().getColor(R.color.theme));
                break;
        }
    }


}
