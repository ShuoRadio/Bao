package com.example.bao.activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bao.R;
import com.example.bao.model.Customer;
import com.example.bao.utils.MyDBHelper;

public class ModifyInfoActivity extends AppCompatActivity {

    ImageView iv_modify_back;

    EditText et_modify_account;
    EditText et_modify_name;
    EditText et_modify_password;
    EditText et_modify_phone;
    EditText et_modify_address;

    ImageView iv_modify_OK;
    ImageView iv_modify_Reset;

    String fragment_id = "打开我的界面";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_info);

        getSupportActionBar().hide();

        iv_modify_back = findViewById(R.id.iv_modify_back);

        et_modify_account = findViewById(R.id.et_modify_account);
        et_modify_name = findViewById(R.id.et_modify_name);
        et_modify_password = findViewById(R.id.et_modify_password);
        et_modify_phone = findViewById(R.id.et_modify_phone);
        et_modify_address = findViewById(R.id.et_modify_address);
        iv_modify_OK = findViewById(R.id.iv_modify_OK);
        iv_modify_Reset = findViewById(R.id.iv_modify_Reset);

        //接收MineFragment传值的account，根据账号获取数据库的内容，再将值set到5个edittext中+++
        final Intent intent = getIntent();
        final String value_modi_id = intent.getStringExtra("value_id");

        //根据account获取一条记录
        MyDBHelper myDBHelper = new MyDBHelper(ModifyInfoActivity.this,"Bao.db",null,1);
        Customer customer = myDBHelper.selectCustomerbyId(Integer.parseInt(value_modi_id));

        //String a = customer.toString();
        //从记录获取不同的属性值
        final String mo_id = customer.getuId()+"";
        final String mo_name = customer.getuName();
        final String mo_account = customer.getuAccount();
        final String mo_password = customer.getuPassword();
        final String mo_phone = customer.getuPhone();
        final String mo_address = customer.getuAddress();

        //设置界面编辑框的内容
        et_modify_account.setText(mo_account);
        et_modify_name.setText(mo_name);
        et_modify_phone.setText(mo_phone);
        et_modify_password.setText(mo_password);
        et_modify_address.setText(mo_address);

        //返回按钮
        iv_modify_back.setOnClickListener(new View.OnClickListener() {
            @Override
            //可以传值，返回界面，判断传值，选择显示的Fragment内容
            public void onClick(View view) {
                Intent intent = new Intent(ModifyInfoActivity.this, MainActivity.class);

                intent.putExtra("modi_fragment",fragment_id);
                startActivity(intent);
                finish();
            }
        });

        //确定按钮
        iv_modify_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String modiaccount = et_modify_account.getText().toString();
                final String modiname = et_modify_name.getText().toString();
                final String modipass = et_modify_password.getText().toString();
                final String modiphone = et_modify_phone.getText().toString();
                final String modiaddress = et_modify_address.getText().toString();

                if(modiaccount.length()==0 || modiname.length()==0 || modiphone.length() == 0 || modiaddress.length() == 0 || modipass.length() == 0) {
                    Toast.makeText(view.getContext(),"请输入有关内容！",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!isMobileNO(modiphone)){
                        Toast.makeText(view.getContext(),"请输入正确的手机号码！",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(ModifyInfoActivity.this);
                        builder.setMessage("确认修改？").setTitle("提示信息");
                        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                //根据id编号修改数据库中联系人的信息
                                MyDBHelper myDBHelper = new MyDBHelper(ModifyInfoActivity.this,"Bao.db",null,1);
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("uAccount",modiaccount);
                                contentValues.put("uName",modiname);
                                contentValues.put("uPas",modipass);
                                contentValues.put("uPhone",modiphone);
                                contentValues.put("uAddress",modiaddress);

                                //根据id修改信息
                                int id = Integer.parseInt(mo_id);
                                myDBHelper.updateCustomer(contentValues,id);

                                Intent intent = new Intent(ModifyInfoActivity.this, MainActivity.class);

                                intent.putExtra("modi_fragment",fragment_id);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();
                    }
                }
            }
        });

        //长按提示确定
        iv_modify_OK.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(view.getContext(),"确定",Toast.LENGTH_SHORT).show();;
                return false;
            }
        });

        //重置按钮
        iv_modify_Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_modify_account.setText(mo_account);
                et_modify_name.setText(mo_name);
                et_modify_phone.setText(mo_phone);
                et_modify_password.setText(mo_password);
                et_modify_address.setText(mo_address);
            }
        });

        //长按提示重置
        iv_modify_Reset.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(view.getContext(),"重置",Toast.LENGTH_SHORT).show();;
                return false;
            }
        });
    }

    //判断手机号是否正确
    public static boolean isMobileNO(String mobiles) {
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)){
            return false;
        }
        else return mobiles.matches(telRegex);
    }

}
