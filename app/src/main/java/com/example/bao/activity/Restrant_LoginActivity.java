package com.example.bao.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bao.R;
import com.example.bao.model.Restaurant;
import com.example.bao.utils.MyDBHelper;

public class Restrant_LoginActivity extends Activity {

    EditText et_restrantaccount;
    EditText et_restrantpassword;
    ImageView btn_restrant_login;
    TextView tv_user;
    TextView tv_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restrant_login);
        et_restrantaccount=findViewById(R.id.et_login_restrantaccount);
        et_restrantpassword=findViewById(R.id.et_login_restrantpassword);
        btn_restrant_login=findViewById(R.id.iv_restrant_login);
        tv_user=findViewById(R.id.tv_login_restrant);
        tv_register=findViewById(R.id.tv_login_register);
        //登陆
        btn_restrant_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bAccount=et_restrantaccount.getText().toString();
                String bPas=et_restrantpassword.getText().toString();
                if (bAccount.equals("")||bPas.equals("")){
                    Toast.makeText(Restrant_LoginActivity.this,"账号/密码不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    MyDBHelper myDBHelper=new MyDBHelper(Restrant_LoginActivity.this,"Bao.db",null,1);
                    Restaurant restaurant = myDBHelper.selectRestaurantbyAccount(bAccount);
                    if (restaurant.getbPas().equals(bPas)){
                        Toast.makeText(Restrant_LoginActivity.this,"欢迎 "+restaurant.getbName(),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Restrant_LoginActivity.this,"账户/密码错误",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        //跳转到用户登陆界面
        tv_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restrant_LoginActivity.this, User_LoginActivity.class);
                startActivity(intent);
            }
        });
        //跳转到商家注册界面
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restrant_LoginActivity.this, Restrant_RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
