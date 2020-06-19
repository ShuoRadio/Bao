package com.example.bao.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bao.R;
import com.example.bao.utils.MyDBHelper;

public class Restrant_RegisterActivity extends Activity {

    TextView tv_user;
    TextView tv_restrant;
    EditText et_bAccount;
    EditText et_bPas;
    ImageView btn_register;
    EditText et_bName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restrant_register);
        tv_user=findViewById(R.id.tv_register_restrant);
        tv_restrant=findViewById(R.id.tv_register_register);
        //输入内容
        et_bAccount=findViewById(R.id.et_register_restrantaccount);
        et_bPas=findViewById(R.id.et_register_restrantpassword);
        et_bName=findViewById(R.id.et_register_restrantname);

        btn_register=findViewById(R.id.iv_restrant_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bAccount=et_bAccount.getText().toString();
                String bPas=et_bPas.getText().toString();
                String bName=et_bName.getText().toString();

                if (bAccount.equals("")||bName.equals("")||bPas.equals("")){
                    Toast.makeText(Restrant_RegisterActivity.this,"注册内容不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    MyDBHelper myDBHelper=new MyDBHelper(Restrant_RegisterActivity.this,"Bao.db",null,1);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("bAccount",bAccount);
                    contentValues.put("bPas",bPas);
                    contentValues.put("bName",bName);
                    myDBHelper.insertRestaurant(contentValues);
                    Intent intent = new Intent(Restrant_RegisterActivity.this, Restrant_LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(Restrant_RegisterActivity.this,"商家"+bName+"  你好,请登陆",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //跳转到用户登陆界面
        tv_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restrant_RegisterActivity.this,User_LoginActivity.class);
                startActivity(intent);
            }
        });
        //跳转到商家登陆界面
        tv_restrant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restrant_RegisterActivity.this,Restrant_LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
