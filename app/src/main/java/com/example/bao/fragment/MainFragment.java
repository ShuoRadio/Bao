package com.example.bao.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.bao.R;
import com.example.bao.activity.BuyMenuActivity;
import com.example.bao.adapter.CagegoryViewPagerAdapter;
import com.example.bao.adapter.EntranceAdapter;
import com.example.bao.adapter.ShopListViewAdapter;
import com.example.bao.model.ModelHomeEntrance;
import com.example.bao.model.Restaurant;
import com.example.bao.utils.MyDBHelper;
import com.example.bao.utils.ScreenUtil;
import com.example.bao.widget.IndicatorView;
import com.example.bao.widget.MyListView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainFragment extends Fragment{
    MyDBHelper myDBHelper;
    private  static MainFragment mainfragment;
    List<Restaurant> restaurantList;
    public static final int HOME_ENTRANCE_PAGE_SIZE = 10;//首页菜单单页显示数量
    private ViewPager entranceViewPager;
    private LinearLayout homeEntranceLayout;
    private List<ModelHomeEntrance> homeEntrances;
    private IndicatorView entranceIndicatorView;
    ShopListViewAdapter shopListViewAdapter;
    MyListView myListView;
    Button btn_jl;
    Button btn_pj;

    View mainView;
    Banner banner;
    //单例模式
    public static MainFragment getMainFragment(){
        if(mainfragment == null){
            mainfragment = new MainFragment();
            Log.i("test", "MainFragment被创建");
        }
        return mainfragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_main,container,false);
        mainView=inflater.inflate(R.layout.fragment_main,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView();
        init();

    }

    public void initView(){
        homeEntranceLayout = (LinearLayout)getActivity().findViewById(R.id.main_home_entrance);
        entranceViewPager=(ViewPager)getActivity().findViewById(R.id.main_home_entrance_vp);
        entranceIndicatorView=(IndicatorView)getActivity().findViewById(R.id.main_home_entrance_indicator);
        banner=getActivity().findViewById(R.id.main_banner1);
        btn_jl=getActivity().findViewById(R.id.btn_sort_jl);
        btn_pj=getActivity().findViewById(R.id.btn_sort_pj);

    }

    public void initData(){
        homeEntrances = new ArrayList<>();
        homeEntrances.add(new ModelHomeEntrance("美食", R.mipmap.ic_category_01));
        homeEntrances.add(new ModelHomeEntrance("饱饱超市", R.mipmap.ic_category_02));
        homeEntrances.add(new ModelHomeEntrance("生鲜果蔬", R.mipmap.ic_category_03));
        homeEntrances.add(new ModelHomeEntrance("饱了专送", R.mipmap.ic_category_04));
        homeEntrances.add(new ModelHomeEntrance("快食简餐", R.mipmap.ic_category_05));
        homeEntrances.add(new ModelHomeEntrance("下午茶", R.mipmap.ic_category_06));
        homeEntrances.add(new ModelHomeEntrance("披萨汉堡", R.mipmap.ic_category_07));
        homeEntrances.add(new ModelHomeEntrance("披萨汉堡", R.mipmap.ic_category_07));
        homeEntrances.add(new ModelHomeEntrance("家常菜", R.mipmap.ic_category_08));
        homeEntrances.add(new ModelHomeEntrance("小吃馆", R.mipmap.ic_category_09));
        homeEntrances.add(new ModelHomeEntrance("鲜花蛋糕", R.mipmap.ic_category_010));


//        homeEntrances.add(new ModelHomeEntrance("美食", R.mipmap.ic_category_0));
//        homeEntrances.add(new ModelHomeEntrance("电影", R.mipmap.ic_category_1));
//        homeEntrances.add(new ModelHomeEntrance("酒店住宿", R.mipmap.ic_category_2));
//        homeEntrances.add(new ModelHomeEntrance("生活服务", R.mipmap.ic_category_3));
//        homeEntrances.add(new ModelHomeEntrance("KTV", R.mipmap.ic_category_4));
//        homeEntrances.add(new ModelHomeEntrance("旅游", R.mipmap.ic_category_5));
//        homeEntrances.add(new ModelHomeEntrance("学习培训", R.mipmap.ic_category_6));
//        homeEntrances.add(new ModelHomeEntrance("汽车服务", R.mipmap.ic_category_7));
//        homeEntrances.add(new ModelHomeEntrance("摄影写真", R.mipmap.ic_category_8));
//        homeEntrances.add(new ModelHomeEntrance("休闲娱乐", R.mipmap.ic_category_10));
        homeEntrances.add(new ModelHomeEntrance("丽人", R.mipmap.ic_category_11));
        homeEntrances.add(new ModelHomeEntrance("运动健身", R.mipmap.ic_category_12));
        homeEntrances.add(new ModelHomeEntrance("大保健", R.mipmap.ic_category_13));
        homeEntrances.add(new ModelHomeEntrance("团购", R.mipmap.ic_category_14));
        homeEntrances.add(new ModelHomeEntrance("景点", R.mipmap.ic_category_16));
        homeEntrances.add(new ModelHomeEntrance("全部分类", R.mipmap.ic_category_15));
    }

    public void init() {
        ScreenUtil.init(getContext());
        LinearLayout.LayoutParams layoutParams12 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int)((float)ScreenUtil.getScreenWidth() / 2.0f));
        //首页菜单分页
        LinearLayout.LayoutParams entrancelayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int)((float)ScreenUtil.getScreenWidth() / 2.0f + 70));
        homeEntranceLayout.setLayoutParams(entrancelayoutParams);
        entranceViewPager.setLayoutParams(layoutParams12);
//        LayoutInflater inflater = LayoutInflater.from(getActivity());
        //将RecyclerView放至ViewPager中：
        int pageSize = HOME_ENTRANCE_PAGE_SIZE;
        //一共的页数等于 总数/每页数量，并取整。
        int pageCount = (int) Math.ceil(homeEntrances.size() * 1.0 / pageSize);
        List<View> viewList = new ArrayList<View>();
        for (int index = 0; index < pageCount; index++) {
            //每个页面都是inflate出一个新实例

            RecyclerView recyclerView = (RecyclerView)LayoutInflater.from(getContext()).inflate(R.layout.item_main_home_entrance_vp,null);
            recyclerView.setLayoutParams(layoutParams12);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
            EntranceAdapter entranceAdapter = new EntranceAdapter(getContext(), homeEntrances, index, HOME_ENTRANCE_PAGE_SIZE);
            recyclerView.setAdapter(entranceAdapter);
            viewList.add(recyclerView);
        }
        CagegoryViewPagerAdapter adapter = new CagegoryViewPagerAdapter(viewList,entranceViewPager);
        entranceViewPager.setAdapter(adapter);
        entranceIndicatorView.setIndicatorCount(entranceViewPager.getAdapter().getCount());
        entranceIndicatorView.setCurrentIndicator(entranceViewPager.getCurrentItem());
        entranceViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                entranceIndicatorView.setCurrentIndicator(position);
            }
        });

        //设置图片轮播
        setBanner();
        //设置商家列表
        setShopList();
        //设置排序
        setSort();

    }


    private  void setBanner(){
        List<Integer>imags=new ArrayList();
        imags.add(R.drawable.main_banner_1);
//        imags.add(R.drawable.main_banner_4);
        imags.add(R.drawable.main_banner_5);
//        imags.add(R.drawable.main_banner_6);
        imags.add(R.drawable.main_banner_2);
        imags.add(R.drawable.main_banner_3);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context.getApplicationContext())
                        .load(path)
                        .into(imageView);
            }
        });
        banner.setImages(imags);
        banner.setBannerAnimation(Transformer.Default);
        banner.setDelayTime(3000);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
    }


    private  void setShopList(){
        myDBHelper=new MyDBHelper(getContext(),"Bao.db",null,1);
        restaurantList=myDBHelper.selectRestaurant(null);
        myListView =getActivity().findViewById(R.id.lv_main_shopListView);
        shopListViewAdapter=new ShopListViewAdapter(restaurantList,getActivity().getBaseContext());
        myListView.setAdapter(shopListViewAdapter);

        //点击进入店家菜单
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv_shopName=view.findViewById(R.id.tv_item_Menuname);
                String menuName= restaurantList.get(i).getbName();
                Bundle bundle=new Bundle();
                bundle.putSerializable("menuName",menuName);
                Intent intent= new Intent(getContext(), BuyMenuActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,0000);
            }
        });


    }
    //设置排序
    private void setSort(){

        //根据评价排序
        btn_pj.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                btn_pj.setTextColor(getResources().getColor(R.color.theme));
                btn_jl.setTextColor(getResources().getColor(R.color.balck));

                Comparator<Restaurant> comparator1= new Comparator<Restaurant>() {
                    @Override
                    public int compare(Restaurant restaurant, Restaurant t1) {
                        if (restaurant.getbStar()> t1.getbStar())
                            return  -1;
                        else if(restaurant.getbStar()< t1.getbStar())
                            return  1;
                        else return 0;
                    }
                };
//                restaurantList.sort(comparator1);  //版本过低不支持
                Collections.sort(restaurantList,comparator1);
                shopListViewAdapter.notifyDataSetChanged();
            }
        });


        //根据距离排序
        btn_jl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_jl.setTextColor(getResources().getColor(R.color.theme));
                btn_pj.setTextColor(getResources().getColor(R.color.balck));
                Comparator<Restaurant> comparator1= new Comparator<Restaurant>() {
                    @Override
                    public int compare(Restaurant restaurant, Restaurant t1) {
                        if (Double.parseDouble(restaurant.getbAddress())>Double.parseDouble(t1.getbAddress()))
                            return  1;
                        else if(Double.parseDouble(restaurant.getbAddress())<Double.parseDouble(t1.getbAddress()))
                            return  -1;
                        else return 0;
                    }
                };
//                restaurantList.sort(comparator1);  //版本过低不支持
                Collections.sort(restaurantList,comparator1);
                shopListViewAdapter.notifyDataSetChanged();


            }
        });

    }
}
