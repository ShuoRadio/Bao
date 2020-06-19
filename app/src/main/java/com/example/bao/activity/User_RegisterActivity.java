package com.example.bao.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bao.R;
import com.example.bao.model.Customer;
import com.example.bao.utils.MyDBHelper;

public class User_RegisterActivity extends Activity {
    EditText et_useraccount;
    EditText et_userpassword;
    EditText et_username;
    EditText et_userphone;
    EditText rt_useraddress;
    ImageView btn_userregister;
    ImageView btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        et_useraccount=findViewById(R.id.et_regsiter_useraccount);
        et_userpassword=findViewById(R.id.et_regsiter_userpassword);
        et_username=findViewById(R.id.et_regsiter_username);
        et_userphone=findViewById(R.id.et_regsiter_userphone);
        rt_useraddress=findViewById(R.id.et_regsiter_useraddress);
        btn_userregister=findViewById(R.id.iv_userregister);
        btn_back=findViewById(R.id.iv_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_RegisterActivity.this,User_LoginActivity.class);
                startActivity(intent);
            }
        });
        btn_userregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(User_RegisterActivity.this);
                builder.setMessage("确认要注册吗").setTitle("提示");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String useraccount=et_useraccount.getText().toString();
                        String userpassword=et_userpassword.getText().toString();
                        String username=et_username.getText().toString();
                        String userphone=et_userphone.getText().toString();
                        String useraddress=rt_useraddress.getText().toString();
                        MyDBHelper myDBHelper=new MyDBHelper(User_RegisterActivity.this,"Bao.db",null,1);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("uAccount",useraccount);
                        contentValues.put("uName",username);
                        contentValues.put("uPas",userpassword);
                        contentValues.put("uPhone",userphone);
                        contentValues.put("uAddress",useraddress);
                        if (!useraccount.equals("")&&!userpassword.equals("")){
                            Customer customer = myDBHelper.selectCustomerbyuAccount(useraccount);
                            if (customer==null){
                                myDBHelper.insertCustomer(contentValues);
                                Intent intent=new Intent(User_RegisterActivity.this, User_LoginActivity.class);
                                Toast.makeText(User_RegisterActivity.this,"亲爱的"+username+" 你好,请您登陆",Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                User_RegisterActivity.this.finish();
                            }else{
                                Toast.makeText(User_RegisterActivity.this,"该用户已注册,请重新输入账户",Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(User_RegisterActivity.this,"用户名/密码不能为空",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();
            }
        });
    }
}
