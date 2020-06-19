package com.example.bao.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bao.R;
import com.example.bao.activity.MainActivity;
import com.example.bao.activity.ModifyInfoActivity;
import com.example.bao.activity.User_LoginActivity;
import com.example.bao.model.Customer;
import com.example.bao.utils.MyDBHelper;

public class MineFragment extends Fragment {

    ImageView iv_mine_zhuxiao;
    ImageView iv_mine_Setting;
    ImageView iv_mine_Mseeage;


    TextView tv_mine_name;
    TextView tv_mine_hongbao;

    LinearLayout ll_mine_hbkq;
    LinearLayout ll_mine_jt;
    LinearLayout ll_mine_qb;

    RelativeLayout rl_mine_sc;
    RelativeLayout rl_mine_swhz;
    RelativeLayout rl_mine_kf;
    RelativeLayout rl_mine_gyhd;
    RelativeLayout rl_mine_bkyl;
    RelativeLayout rl_mine_tjyl;

    com.example.bao.widget.XCRoundImageView xcRoundImageView;
    com.example.bao.widget.RoundAngleImageView roundAngleImageView;

    static String login_id;

    private static MineFragment mineFragment;

    public static MineFragment getMineFragment(){
        if(mineFragment == null){
            mineFragment = new MineFragment();
        }
        return mineFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //登录成功后,传值获取id,连接数据库获得姓名，设置姓名
//        Intent intent = getActivity().getIntent();
//        login_id = intent.getStringExtra("login_id");


        login_id = share_read("my_id_key");

        tv_mine_name = getActivity().findViewById(R.id.tv_mine_name);

        MyDBHelper myDBHelper = new MyDBHelper(MineFragment.this.getActivity(),"Bao.db",null,1);
        Customer customer = myDBHelper.selectCustomerbyId(Integer.parseInt(login_id));
        tv_mine_name.setText(customer.getuName());


        //设置按钮
        iv_mine_Setting = getActivity().findViewById(R.id.iv_mine_setting);
        iv_mine_Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //传值，由tv_mine_account获取在数据库中的内容，传值到ModifyInfoActivity界面；
                //String value_mine_name= tv_mine_name.getText().toString();
                //Toast.makeText(MineFragment.this.getActivity(),"值"+value_mine_account,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), ModifyInfoActivity.class);
                intent.putExtra("value_id",login_id);
                startActivity(intent);

                MineFragment.this.getActivity().onBackPressed();
            }
        });

        //信息按钮
        iv_mine_Mseeage = getActivity().findViewById(R.id.iv_mine_message);
        iv_mine_Mseeage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MineFragment.this.getActivity());
                builder.setMessage("    本系统由陈烁文、唐洪斌、张瑞祥联手打造！").setTitle("信息");
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        //注销按钮
        iv_mine_zhuxiao = getActivity().findViewById(R.id.iv_mine_zhuxiao);
        iv_mine_zhuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MineFragment.this.getActivity());
                builder.setMessage("⚠确认退出？").setTitle("提示信息");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Intent intent = new Intent(view.getContext(), User_LoginActivity.class);
                        startActivity(intent);
                        MineFragment.this.getActivity().onBackPressed();
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
        });
        //点击名字，跳转修改界面
        tv_mine_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ModifyInfoActivity.class);
                intent.putExtra("value_id",login_id);
                startActivity(intent);

                MineFragment.this.getActivity().onBackPressed();
            }
        });

        //点击头像跳转修改界面
        xcRoundImageView = getActivity().findViewById(R.id.iv_mine_touxiang);
        xcRoundImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ModifyInfoActivity.class);
                intent.putExtra("value_id",login_id);
                startActivity(intent);

                MineFragment.this.getActivity().onBackPressed();
            }
        });

        //奖励金操作
        roundAngleImageView = getActivity().findViewById(R.id.iv_mine_jlj);
        roundAngleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MineFragment.this.getActivity());
                builder.setMessage("您已经是我们尊贵的vip用户，是否充值成为更加尊贵VVIP用户").setTitle("提示信息");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MineFragment.this.getActivity());
                        builder.setMessage("恭喜您已经成为我们最尊贵的VVIP用户").setTitle("提示");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MineFragment.this.getActivity());
                        builder.setMessage("不充钱点什么外卖").setTitle("提示");
                        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();
                    }
                });
                builder.create().show();
            }
        });

        //红包卡券
        ll_mine_hbkq = getActivity().findViewById(R.id.ll_mine_hbkq);
        ll_mine_hbkq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MineFragment.this.getActivity());
                builder.setMessage("您有8个红包未使用，赶紧下单吧").setTitle("提示信息");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        MineFragment.this.getActivity().onBackPressed();
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
        });

        //津贴
        ll_mine_jt = getActivity().findViewById(R.id.ll_mine_jt);
        ll_mine_jt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MineFragment.this.getActivity());
                builder.setMessage("您有$20购物津贴未使用，赶紧下单吧").setTitle("提示信息");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        MineFragment.this.getActivity().onBackPressed();
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
        });

        //钱包
        ll_mine_qb = getActivity().findViewById(R.id.ll_mine_qb);
        ll_mine_qb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"该功能正在开发，尽情谅解！",Toast.LENGTH_SHORT).show();
            }
        });


        //我的收藏
        rl_mine_sc = getActivity().findViewById(R.id.rl_mine_sc);
        rl_mine_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MineFragment.this.getActivity());
                builder.setMessage("亲！这边不允许您收藏呢！").setTitle("提示");
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        //我的客服
        rl_mine_kf = getActivity().findViewById(R.id.rl_mine_kf);
        rl_mine_kf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MineFragment.this.getActivity());
                builder.setMessage("客服小姐姐还没上班哦！").setTitle("提示");
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        //推荐有礼
        rl_mine_tjyl = getActivity().findViewById(R.id.rl_mine_tjyl);
        rl_mine_tjyl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MineFragment.this.getActivity());
                builder.setMessage("恭喜你获得福利，赶紧下单吧!").setTitle("提示信息");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        MineFragment.this.getActivity().onBackPressed();
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
        });

        //商务合作
        rl_mine_swhz = getActivity().findViewById(R.id.rl_mine_swhz);
        rl_mine_swhz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MineFragment.this.getActivity());
                builder.setMessage("元旦期间，商务活动取消！").setTitle("提示");
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        //办卡有礼
        rl_mine_bkyl = getActivity().findViewById(R.id.rl_mine_bkyl);
        rl_mine_bkyl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MineFragment.this.getActivity(),"没钱办啥卡呀！",Toast.LENGTH_SHORT).show();
            }
        });

        //公益活动
        rl_mine_gyhd = getActivity().findViewById(R.id.rl_mine_gyhd);
        rl_mine_gyhd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MineFragment.this.getActivity());
                builder.setMessage("确认捐款1元？").setTitle("提示信息");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(MineFragment.this.getActivity(),"感谢您为公益事业做出贡献!",Toast.LENGTH_SHORT).show();
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
        });

        //红包签到
        tv_mine_hongbao = getActivity().findViewById(R.id.tv_mine_hongbao);
        tv_mine_hongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MineFragment.this.getActivity(),"恭喜你，签到成功！",Toast.LENGTH_SHORT).show();
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
