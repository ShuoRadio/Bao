package com.example.bao.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bao.R;
import com.example.bao.activity.BuyMenuActivity;
import com.example.bao.model.Menu;

import java.util.List;

public class BuyMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Menu> menuList1;
    private BuyMenuActivity mbuyMenuActivity;

    private int mPriceLeft;
    private int mReduceLeft;
    private int mAddLeft;
    public int[] counts;


    public BuyMenuAdapter(List<Menu> menuList, BuyMenuActivity buyMenuActivity) {
        this.menuList1 = menuList;
        this.mbuyMenuActivity = buyMenuActivity;
        counts=new int[menuList1.size()];
        for(int i=0;i<counts.length;i++)
            counts[i]=0;
    }

    public List<Menu> getMenuList() {
        return menuList1;
    }

    //创建ViewHolder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buy,parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }
    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final MyHolder myHolder=(MyHolder)holder;
        myHolder.setItem(position);

        //点击加号
        myHolder.imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Menu menu=mbuyMenuActivity.menuList.get(position);
                //若商品数量为0就进行此动画
                if(menu.getInBuyCount()==0){
                    myHolder.imageViewReduce.setVisibility(View.VISIBLE);
                    myHolder.menuCount.setVisibility(View.VISIBLE);
                    AnimatorSet set= new AnimatorSet(); //组合动画设置

                    //减号
                    ObjectAnimator ta1 = ObjectAnimator.ofFloat(myHolder.imageViewReduce, "translationX", mAddLeft - mReduceLeft, 0);
                    ObjectAnimator ra1 = ObjectAnimator.ofFloat(myHolder.imageViewReduce, "rotation", 0, 360);
                    ObjectAnimator aa1 = ObjectAnimator.ofFloat(myHolder.imageViewReduce, "alpha", 0, 1);

                    //数字
                    ObjectAnimator ta2 = ObjectAnimator.ofFloat(myHolder.menuCount, "translationX", mAddLeft - mPriceLeft, 0);
                    ObjectAnimator ra2 = ObjectAnimator.ofFloat(myHolder.menuCount, "rotation", 0, 360);
                    ObjectAnimator aa2 = ObjectAnimator.ofFloat(myHolder.menuCount, "alpha", 0, 1);
                    set.play(ta1).with(ra1).with(ta2).with(ra2).with(aa1).with(aa2);
                    set.setDuration(500).start();

                }
                //得到加号在屏幕的坐标
                int[] addLocation=new int[2];
                view.getLocationInWindow(addLocation);
                //得到购物车的坐标
                int[]carLocation=mbuyMenuActivity.getCarLocation();
                //添加一个ImageView
                final ImageView iv= new ImageView(view.getContext());
                iv.setBackgroundResource(R.drawable.icon_buy_add);
                RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(view.getWidth(),view.getHeight());
                lp.leftMargin = addLocation[0];
                lp.topMargin = addLocation[1] - view.getHeight();

                mbuyMenuActivity.getmContainer().addView(iv,lp);
                //横向移动
                ObjectAnimator oaX = ObjectAnimator.ofFloat(iv, "translationX", carLocation[0] - addLocation[0] + view.getWidth() / 2);
                //纵向
                ObjectAnimator oaY = ObjectAnimator.ofFloat(iv, "translationY", carLocation[1] - addLocation[1]);
                oaX.setInterpolator(new LinearInterpolator());

                oaY.setInterpolator(new AccelerateInterpolator());
                AnimatorSet set = new AnimatorSet();
                set.play(oaX).with(oaY);
                set.setDuration(500).start();
                set.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        //移除这个view
                        mbuyMenuActivity.getmContainer().removeView(iv);
                        //更新购物车
//                        mbuyMenuActivity.AddMenuCount(position);
                        menu.setInBuyCount(menu.getInBuyCount()+1);
                        mbuyMenuActivity.menuList.set(position,menu);
//                        menuList1.set(position,menu);
                        ((MyHolder)myHolder).menuCount.setText(String.valueOf(mbuyMenuActivity.menuList.get(position).getInBuyCount()));
                        mbuyMenuActivity.mCount++;
                        mbuyMenuActivity.setMtvNum();
                        ImageView iconCar =mbuyMenuActivity.findViewById(R.id.iv_shop_cart);
                        Drawable noemptycar= mbuyMenuActivity.getDrawable(R.drawable.icon_buy_shopcar_noempty);
                        iconCar.setImageDrawable(noemptycar);
                        //购买界面颜色功能变更预留

//                        List<Menu> nowMenus=menuList;
//                        //查看增加是否达到起送
//                        double oPrice=0;
//                        //获取购买的东西
//                        for(int i=0;i<nowMenus.size();i++){
//                            while(nowMenus.get(i).getInBuyCount()>0){
//                                Menu nowMenu=nowMenus.get(i);
//                                oPrice+=nowMenu.getmPrice();
//                                nowMenus.get(i).setInBuyCount(nowMenu.getInBuyCount()-1);
//                            }
//                        }
//                        String strPrice=String.format(".2lf",oPrice);
//                        TextView tv_price=item

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
            }
        });

        //点击减号
        myHolder.imageViewReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                final Menu menu=menuList1.get(position);
                final Menu menu=mbuyMenuActivity.menuList.get(position);

                //如果商品数量为1就进行这个动画
                if(menu.getInBuyCount()==1){
                    AnimatorSet set = new AnimatorSet();
                    //减号
                    ObjectAnimator ta1 = ObjectAnimator.ofFloat(myHolder.imageViewReduce, "translationX", 0, mAddLeft - mReduceLeft);
                    ObjectAnimator ra1 = ObjectAnimator.ofFloat(myHolder.imageViewReduce, "rotation", 0, 360);
                    ObjectAnimator aa1 = ObjectAnimator.ofFloat(myHolder.imageViewReduce, "alpha", 1, 0);

                    //数字
                    ObjectAnimator ta2 = ObjectAnimator.ofFloat(myHolder.menuCount, "translationX", 0, mAddLeft - mPriceLeft);
                    ObjectAnimator ra2 = ObjectAnimator.ofFloat(myHolder.menuCount, "rotation", 0, 360);
                    ObjectAnimator aa2 = ObjectAnimator.ofFloat(myHolder.menuCount, "alpha", 1, 0);
                    set.play(ta1).with(ra1).with(ta2).with(ra2).with(aa1).with(aa2);
                    set.setDuration(500).start();

                    set.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {}

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            //移除这个view
//                            mbuyMenuActivity.ReduceMenuCount(position);
                            menu.setInBuyCount(menu.getInBuyCount()-1);
                            mbuyMenuActivity.menuList.set(position,menu);
                            myHolder.menuCount.setText(String.valueOf(mbuyMenuActivity.menuList.get(position).getInBuyCount()));
                            if(mbuyMenuActivity.menuList.get(position).getInBuyCount()==0){
                                myHolder.menuCount.setVisibility(View.INVISIBLE);
                                myHolder.imageViewReduce.setVisibility(View.INVISIBLE);
                            }
                            mbuyMenuActivity.mCount--;
                            mbuyMenuActivity.setMtvNum();
                            ImageView iconCar =mbuyMenuActivity.findViewById(R.id.iv_shop_cart);
                            if(mbuyMenuActivity.mCount==0){
                                Drawable emptycar= mbuyMenuActivity.getDrawable(R.drawable.icon_buy_shopcar_empty);
                                iconCar.setImageDrawable(emptycar);
                            }

                            //购买界面颜色功能变更预留
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {}

                        @Override
                        public void onAnimationRepeat(Animator animation) {}
                    });

                } else {
                    menu.setInBuyCount(menu.getInBuyCount()-1);
                    mbuyMenuActivity.menuList.set(position,menu);
                    myHolder.menuCount.setText(String.valueOf(mbuyMenuActivity.menuList.get(position).getInBuyCount()));
                    mbuyMenuActivity.mCount--;
                    mbuyMenuActivity.setMtvNum();
                }

            }
        });
    }



    @Override
    public int getItemCount() {
        return menuList1.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
        TextView menuName;
        TextView menuIntroduce;
        TextView menuPrice;
        TextView menuCount;
        ImageView imageViewAdd;
        ImageView imageViewReduce;
        ImageView iv_menu_icon;
        TextView tv_price;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            menuName=itemView.findViewById(R.id.tv_item_Menuname);
            menuIntroduce=itemView.findViewById(R.id.tv_item_menuIntroduce);
            menuPrice=itemView.findViewById(R.id.tv_menu_price);
            menuCount=itemView.findViewById(R.id.tv_menu_count);
            imageViewAdd=itemView.findViewById(R.id.iv_menu_add);
            imageViewReduce=itemView.findViewById(R.id.iv_menu_reduce);
            iv_menu_icon=itemView.findViewById(R.id.iv_item_buylistt);
//            tv_price=itemView.findViewById(R.id.tv_buy_price);


            imageViewAdd.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    //得到加号的左边的位置
                    mAddLeft=imageViewAdd.getLeft();
                    imageViewAdd.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });

            imageViewReduce.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    //得到减号左边的位置
                    mReduceLeft=imageViewReduce.getLeft();
                    imageViewReduce.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });

            menuPrice.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    //得到价格左边的位置
                    mPriceLeft=menuPrice.getLeft();
                    menuPrice.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }

        /**
         * 初始化
         * 设置减号消失出现动画
         * @param position
         */
        public void setItem(int position){

            Menu menu=menuList1.get(position);
            menuName.setText(menu.getmName());
            menuIntroduce.setText(menu.getmIntroduction());
            menuPrice.setText(menu.getmPrice()+"");
            menuCount.setText(String.valueOf(menu.getInBuyCount()));
            iv_menu_icon.setImageDrawable(itemView.getContext().getDrawable(menu.getmImage()));
//            iv_shopimage.setImageDrawable(view.getContext().getDrawable(restaurant.getbImage()));

            if(menu.getInBuyCount()>0){//数目大于0就显示
                imageViewReduce.setVisibility(View.VISIBLE);
                menuCount.setVisibility(View.VISIBLE);
//                menuCount.setText(menu.getInBuyCount()+"");
            }else{//数目小于0就隐藏
                imageViewReduce.setVisibility(View.GONE);
                menuCount.setVisibility(View.GONE);
            }

        }


    }



}
