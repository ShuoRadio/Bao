package com.example.bao.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bao.R;
import com.example.bao.model.Customer;
import com.example.bao.utils.MyDBHelper;


public class User_LoginActivity extends Activity {
    EditText et_useraccount;
    EditText et_userpassword;
    ImageView btn_login;
    TextView tv_restrant;
    TextView tv_userregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);


//        MyDBHelper myDBHelper=new MyDBHelper(User_LoginActivity.this,"Bao.db",null,1);
//        myDBHelper.DataInit();
        et_useraccount=findViewById(R.id.et_login_useraccount);
        et_userpassword=findViewById(R.id.et_login_userpassword);
        tv_restrant=findViewById(R.id.tv_login_restrant);
        tv_userregister=findViewById(R.id.tv_login_register);

        //跳转到商家页面
        tv_restrant.findViewById(R.id.tv_login_restrant);
        tv_restrant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(User_LoginActivity.this, Restrant_LoginActivity.class);
                startActivity(intent);
                User_LoginActivity.this.finish();
            }
        });


        //跳转到用户注册页面
        tv_userregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(User_LoginActivity.this, User_RegisterActivity.class);
                startActivity(intent);
                User_LoginActivity.this.finish();
            }
        });
        //登陆判断
        btn_login=findViewById(R.id.iv_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useraccount=et_useraccount.getText().toString();
                String userpassword=et_userpassword.getText().toString();

                MyDBHelper myDBHelper=new MyDBHelper(User_LoginActivity.this,"Bao.db",null,1);
                Customer customer = myDBHelper.selectCustomer(useraccount);

                //Log.i("thb", customer+"");
                //Log.i("thb", userpassword);
                if (customer==null){
                    Toast.makeText(User_LoginActivity.this,"该用户未注册,请注册",Toast.LENGTH_SHORT).show();
                }else if(userpassword!=null&&customer.getuPassword().equals(userpassword)){

                    Intent intent=new Intent(User_LoginActivity.this,MainActivity.class);
                    //根据账号account获得数据库记录，在获得id，传值id;

                    String login_id = customer.getuId()+"";

                    shared_write(login_id);

                    //intent.putExtra("login_id",login_id);

                    startActivity(intent);
                    User_LoginActivity.this.finish();

                }else{
                    Toast.makeText(User_LoginActivity.this,"账号/密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //SharedPreferences写
    private void shared_write(String content){
        SharedPreferences sharedPreferences = getSharedPreferences("my_id", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("my_id_key",content);
        editor.commit();
    }
}
