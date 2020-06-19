package com.example.bao.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bao.R;
import com.example.bao.model.Customer;
import com.example.bao.model.Menu;
import com.example.bao.model.Order;
import com.example.bao.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version){
        super(context,dbName,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建用户表
        String sql1="CREATE TABLE Customer (\n" +
                "    uId      INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                     NOT NULL,\n" +
                "    uAccount TEXT    NOT NULL\n" +
                "                     UNIQUE,\n" +
                "    uName    TEXT    NOT NULL,\n" +
                "    uPas     TEXT    NOT NULL,\n" +
                "    uPhone   TEXT    NOT NULL,\n" +
                "    uAddress TEXT    NOT NULL\n" +
                ");";
        db.execSQL(sql1);

        //创建商家表
        String sql2="CREATE TABLE Restaurant (\n" +
                "    bId      INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                     NOT NULL,\n" +
                "    bAccount TEXT    NOT NULL\n" +
                "                     UNIQUE,\n" +
                "    bPas     TEXT    NOT NULL,\n" +
                "    bName    TEXT    NOT NULL,\n" +
                "    bImage   TEXT,  \n" +
                "    bSend_cost   INTEGER,  \n" +
                "    bSales   INTEGER,  \n" +
                "    bAddress   TEXT,  \n" +
                "    bStar   DOUBLE  \n" +
                ");";
        db.execSQL(sql2);


        //创建菜品表
        String sql3="CREATE TABLE Menu (\n" +
                "    mId           INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    mName         TEXT    NOT NULL,\n" +
                "    mPrice        DOUBLE  NOT NULL,\n" +
                "    mIntroduction TEXT    NOT NULL,\n" +
                "    mImage   TEXT,  \n" +
                "    mStar   INTEGER,  \n" +
                "    bId           INTEGER REFERENCES Restaurant (bId) \n" +
                "                          NOT NULL\n" +
                ");";
        db.execSQL(sql3);

        //创建订单表
        String sql4="CREATE TABLE [Order] (\n" +
                "    oId    INTEGER NOT NULL\n" +
                "                   PRIMARY KEY AUTOINCREMENT,\n" +
                "    uId    INTEGER REFERENCES Customer (uId) \n" +
                "                   NOT NULL,\n" +
                "    bId    INTEGER REFERENCES Restaurant (bId) \n" +
                "                   NOT NULL,\n" +
                "    mIds   TEXT    NOT NULL,\n" +
                "    oDate  DATE    NOT NULL,\n" +
                "    oAddress  TEXT    NOT NULL,\n" +
                "    dId  INTEGER      ,\n" +
                "    oPrice DOUBLE     NOT NULL,\n" +
                "    oEvalute  TEXT    \n" +
                ");\n";
        db.execSQL(sql4);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //用户增删改查方法
    public void  insertCustomer(ContentValues contentValues){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert("Customer",null,contentValues);
        sqLiteDatabase.close();
    }

    public Customer selectCustomer(String uAccount){
        Customer customer=new Customer();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.query("Customer",null,"uAccount = ?",new String[]{uAccount},null,null,null);
        while (cursor.moveToNext()){
            customer.setuId(cursor.getInt(cursor.getColumnIndex("uId")));
            customer.setuAccount(cursor.getString(cursor.getColumnIndex("uAccount")));
            customer.setuName(cursor.getString(cursor.getColumnIndex("uName")));
            customer.setuPassword(cursor.getString(cursor.getColumnIndex("uPas")));
            customer.setuPhone(cursor.getString(cursor.getColumnIndex("uPhone")));
            customer.setuAddress(cursor.getString(cursor.getColumnIndex("uAddress")));
            sqLiteDatabase.close();
            return  customer;
        }return null;
    }
    //根据用户姓名查询数据
    public Customer selectCustomerbyName(String uName){
        Customer customer=new Customer();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.query("Customer",null,"uName = ?",new String[]{uName},null,null,null);
        while (cursor.moveToNext()){
            customer.setuId(cursor.getInt(cursor.getColumnIndex("uId")));
            customer.setuAccount(cursor.getString(cursor.getColumnIndex("uAccount")));
            customer.setuName(cursor.getString(cursor.getColumnIndex("uName")));
            customer.setuPassword(cursor.getString(cursor.getColumnIndex("uPas")));
            customer.setuPhone(cursor.getString(cursor.getColumnIndex("uPhone")));
            customer.setuAddress(cursor.getString(cursor.getColumnIndex("uAddress")));
            sqLiteDatabase.close();
            return  customer;
        }return null;
    }
    //根据用户id查找用户
    public Customer selectCustomerbyId(int uid){
        Customer customer=new Customer();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.query("Customer",null,"uId = ?",new String[]{String.valueOf(uid)},null,null,null);
        while (cursor.moveToNext()){
            customer.setuId(cursor.getInt(cursor.getColumnIndex("uId")));
            customer.setuAccount(cursor.getString(cursor.getColumnIndex("uAccount")));
            customer.setuName(cursor.getString(cursor.getColumnIndex("uName")));
            customer.setuPassword(cursor.getString(cursor.getColumnIndex("uPas")));
            customer.setuPhone(cursor.getString(cursor.getColumnIndex("uPhone")));
            customer.setuAddress(cursor.getString(cursor.getColumnIndex("uAddress")));
            sqLiteDatabase.close();
            return  customer;
        }return null;
    }

    public Customer selectCustomerbyuAccount(String uAccount){
        Customer customer=new Customer();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.query("Customer",null,"uAccount = ?",new String[]{String.valueOf(uAccount)},null,null,null);
        while (cursor.moveToNext()){
            customer.setuId(cursor.getInt(cursor.getColumnIndex("uId")));
            customer.setuAccount(cursor.getString(cursor.getColumnIndex("uAccount")));
            customer.setuName(cursor.getString(cursor.getColumnIndex("uName")));
            customer.setuPassword(cursor.getString(cursor.getColumnIndex("uPas")));
            customer.setuPhone(cursor.getString(cursor.getColumnIndex("uPhone")));
            customer.setuAddress(cursor.getString(cursor.getColumnIndex("uAddress")));
            sqLiteDatabase.close();
            return  customer;
        }return null;
    }

    public void  deleteCustomer(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("Customer", "uId=?", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }

    public void updateCustomer(ContentValues contentValues,int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.update("Customer",contentValues,"uId=?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }


    //商家
    //商家表增删改查
    public void  insertRestaurant(ContentValues contentValues){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert("Restaurant",null,contentValues);
        sqLiteDatabase.close();
    }

    public int selectResaurantId(String bname){
        Restaurant restaurant=new Restaurant();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.query("Restaurant",null,"bName=?",new String[]{bname},null,null,null);
        while (cursor.moveToNext()){
            return cursor.getColumnIndex("bId");

        }return 0;
    }


    public List<Restaurant> selectRestaurant(String name){
        List<Restaurant> restaurantList=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        if(name==null){
            cursor = sqLiteDatabase.query("Restaurant",null,null,null,null,null,null);
        }else{
            cursor = sqLiteDatabase.query("Restaurant",null,"bName like ?",new String[]{"%"+name+"%"},null,null,null);
        }
        if(cursor.getCount()==0){
            Log.i("find","没找到数据");
        }
        while (cursor.moveToNext()){
            Restaurant restaurant=new Restaurant();
            restaurant.setbId(cursor.getInt(cursor.getColumnIndex("bId")));
            restaurant.setbName(cursor.getString(cursor.getColumnIndex("bName")));
            restaurant.setbAccount(cursor.getString(cursor.getColumnIndex("bAccount")));
            restaurant.setbPas(cursor.getString(cursor.getColumnIndex("bPas")));
            restaurant.setbImage(Integer.parseInt(cursor.getString(cursor.getColumnIndex("bImage"))));
            restaurant.setbAddress(cursor.getString(cursor.getColumnIndex("bAddress")));
            restaurant.setbStar(cursor.getDouble(cursor.getColumnIndex("bStar")));
            restaurantList.add(restaurant);
        }
        sqLiteDatabase.close();
        return restaurantList;
    }


    public Restaurant selectRestaurantbyAccount(String bAccount){
        Restaurant restaurant=new Restaurant();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.query("Restaurant",null,"bAccount=?",new String[]{bAccount},null,null,null);
        while (cursor.moveToNext()){
            restaurant.setbId(cursor.getInt(cursor.getColumnIndex("bId")));
            restaurant.setbName(cursor.getString(cursor.getColumnIndex("bName")));
            restaurant.setbAccount(cursor.getString(cursor.getColumnIndex("bAccount")));
            restaurant.setbPas(cursor.getString(cursor.getColumnIndex("bPas")));
            restaurant.setbImage(Integer.parseInt(cursor.getString(cursor.getColumnIndex("bImage"))));
            restaurant.setbAddress(cursor.getString(cursor.getColumnIndex("bAddress")));
            restaurant.setbStar(cursor.getDouble(cursor.getColumnIndex("bStar")));
            sqLiteDatabase.close();
            return restaurant;
        }return null;
    }

    public Restaurant selectRestaurantbybId(int bId){
        Restaurant restaurant=new Restaurant();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.query("Restaurant",null,"bId=?",new String[]{String.valueOf(bId)},null,null,null);
        while (cursor.moveToNext()){
            restaurant.setbId(cursor.getInt(cursor.getColumnIndex("bId")));
            restaurant.setbName(cursor.getString(cursor.getColumnIndex("bName")));
            restaurant.setbAccount(cursor.getString(cursor.getColumnIndex("bAccount")));
            restaurant.setbPas(cursor.getString(cursor.getColumnIndex("bPas")));
            restaurant.setbImage(Integer.parseInt(cursor.getString(cursor.getColumnIndex("bImage"))));
            restaurant.setbAddress(cursor.getString(cursor.getColumnIndex("bAddress")));
            restaurant.setbStar(cursor.getDouble(cursor.getColumnIndex("bStar")));
            sqLiteDatabase.close();
            return restaurant;
        }return null;
    }



    public void  deleteRestaurant(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("Restaurant", "bId=?", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }

    public void updateRestaurant(ContentValues contentValues,int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.update("Restaurant",contentValues,"bId=?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }


    //菜品表增删改查
    /**
     * 增加菜品
     * @param contentValues
     */
    public void  insertMenu(ContentValues contentValues){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert("Menu",null,contentValues);
        sqLiteDatabase.close();
    }

    /**
     *
     * @param name 根据菜名查找菜品
     *             如果是null则为全部菜品
     * @return 返回menu的List
     */
    public List<Menu> selectMenu(String name){
        List<Menu> menuList=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        if(name==null){
            cursor = sqLiteDatabase.query("Menu",null,null,null,null,null,null);
        }else{
            cursor = sqLiteDatabase.query("Menu",null,"mName like ?",new String[]{"%"+name+"%"},null,null,null);
        }
        if(cursor.getCount()==0){
            Log.i("find","没找到数据");
        }
        while (cursor.moveToNext()){
            Menu menu=new Menu();
            menu.setmId(cursor.getInt(cursor.getColumnIndex("mId")));
            menu.setmName(cursor.getString(cursor.getColumnIndex("mName")));
            menu.setmPrice(cursor.getDouble(cursor.getColumnIndex("mPrice")));
            menu.setmIntroduction(cursor.getString(cursor.getColumnIndex("mIntroduction")));
            menu.setbId(cursor.getInt(cursor.getColumnIndex("bId")));
            menu.setmImage(Integer.parseInt(cursor.getString(cursor.getColumnIndex("mImage"))));
            menuList.add(menu);
        }
        sqLiteDatabase.close();
        return menuList;
    }

    /**
     *
     * @param bid 根据商家id讯号菜品
     * @return
     */
    public List<Menu> selectMenuBybId(int bid){
        List<Menu> menuList=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.query("Menu",null,"bId =?",new String[]{String.valueOf(bid)},null,null,null);

        if(cursor.getCount()==0){
            Log.i("find","没找到数据");
        }
        while (cursor.moveToNext()){
            Menu menu=new Menu();
            menu.setmId(cursor.getInt(cursor.getColumnIndex("mId")));
            menu.setmName(cursor.getString(cursor.getColumnIndex("mName")));
            menu.setmPrice(cursor.getDouble(cursor.getColumnIndex("mPrice")));
            menu.setmIntroduction(cursor.getString(cursor.getColumnIndex("mIntroduction")));
            menu.setbId(cursor.getInt(cursor.getColumnIndex("bId")));
            menu.setmImage(Integer.parseInt(cursor.getString(cursor.getColumnIndex("mImage"))));
            menuList.add(menu);
        }
        sqLiteDatabase.close();
        return menuList;
    }

    public Menu selectMenuBymId(int mid){
        Menu menu=new Menu();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.query("Menu",null,"mId =?",new String[]{String.valueOf(mid)},null,null,null);

        if(cursor.getCount()==0){
            Log.i("find","没找到数据");
        }
        while (cursor.moveToNext()){
            menu.setmId(cursor.getInt(cursor.getColumnIndex("mId")));
            menu.setmName(cursor.getString(cursor.getColumnIndex("mName")));
            menu.setmPrice(cursor.getDouble(cursor.getColumnIndex("mPrice")));
            menu.setmIntroduction(cursor.getString(cursor.getColumnIndex("mIntroduction")));
            menu.setbId(cursor.getInt(cursor.getColumnIndex("bId")));
            menu.setmImage(Integer.parseInt(cursor.getString(cursor.getColumnIndex("mImage"))));
            sqLiteDatabase.close();
            return  menu;
        }return null;

    }

    public void  deleteMenu(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("Menu", "mId=?", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }

    public void updateMenu(ContentValues contentValues,int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.update("Menu",contentValues,"mId=?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }

    //订单表增删改查

    /**
     * 增加订单
     * @param contentValues
     */
    public void  insertOrder(ContentValues contentValues){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert("[Order]",null,contentValues);
        sqLiteDatabase.close();
    }

    /**
     * 根据用户id查询订单
     * @param uid
     * @return
     */
    public List<Order> selectOrder(int uid){
        List<Order> orderList=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.query("[Order]",null,"uId =?",new String[]{String.valueOf(uid)},null,null,null);

        if(cursor.getCount()==0){
            Log.i("find","没找到数据");
        }
        while (cursor.moveToNext()){
            Order order = new Order();
            order.setoId(cursor.getInt(cursor.getColumnIndex("oId")));
            order.setuId(cursor.getInt(cursor.getColumnIndex("uId")));
            order.setbId(cursor.getInt(cursor.getColumnIndex("bId")));
            //将订单内菜品转化为int类型放入列表中
            String allmenu=cursor.getString(cursor.getColumnIndex("mIds"));
            Log.i("thb2", "selectOrder: "+allmenu);

            String[] strMenu=allmenu.split(",");
            ArrayList<Integer> intMenu=new ArrayList<>();
            for(int i=0;i<strMenu.length;i++){
                intMenu.add(Integer.parseInt(strMenu[i]));
            }
            order.setmIds(intMenu);

            //转化时间
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            order.setoDate(cursor.getString(cursor.getColumnIndex("oDate")));
            order.setoPrice(cursor.getDouble(cursor.getColumnIndex("oPrice")));
            order.setoAddress(cursor.getString(cursor.getColumnIndex("oAddress")));
            order.setoEvalute(cursor.getString(cursor.getColumnIndex("oEvalute")));
            order.setdId(cursor.getInt(cursor.getColumnIndex("dId")));
            orderList.add(order);
        }
        sqLiteDatabase.close();
        return orderList;
    }

    public int selectLastInsert_order(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "select last_insert_rowid() from " + "[Order]" ;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        int a = -1;
        if(cursor.moveToFirst()){
            a = cursor.getInt(0);
        }
        return a;
    }

    //根据oId查询订单,返回Order对象
    public Order selectOrderByoId(int oId){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.query("[Order]",null,"oId =?",new String[]{String.valueOf(oId)},null,null,null);
        Order order = new Order();
        while (cursor.moveToNext()){
            order.setoId(cursor.getInt(cursor.getColumnIndex("oId")));
            order.setuId(cursor.getInt(cursor.getColumnIndex("uId")));
            order.setbId(cursor.getInt(cursor.getColumnIndex("bId")));
            //将订单内菜品转化为int类型放入列表中
            String allmenu=cursor.getString(cursor.getColumnIndex("mIds"));
            String[] strMenu=allmenu.split(",");
            ArrayList<Integer> intMenu=new ArrayList<>();
            for(int i=0;i<strMenu.length;i++){
                intMenu.add(Integer.parseInt(strMenu[i]));
            }
            order.setmIds(intMenu);

            //转化时间
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            order.setoDate(cursor.getString(cursor.getColumnIndex("oDate")));
            order.setoPrice(cursor.getDouble(cursor.getColumnIndex("oPrice")));
        }
        sqLiteDatabase.close();
        return order;


    }

    public void  deleteOrder(int oid) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("Order", "oId=?", new String[]{String.valueOf(oid)});
        sqLiteDatabase.close();
    }

    public void updateOrder(ContentValues contentValues,int oid){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.update("Order",contentValues,"oId=?",new String[]{String.valueOf(oid)});
        sqLiteDatabase.close();
    }

    public void DataInit() {
        SQLiteDatabase db = getWritableDatabase();
        //添加customer
        String sql101="INSERT INTO Customer (uId, uAccount, uName, uPas, uPhone, uAddress) VALUES (1, 'boss', '老板', '123456', '15915919511', '江苏南京')" ;
        String sql102 = "INSERT INTO Customer (uId, uAccount, uName, uPas, uPhone, uAddress) VALUES (2, 'zrx', '张瑞祥', '123456', '15651769821', '江苏南京')";
        String sql103 = "INSERT INTO Customer (uId, uAccount, uName, uPas, uPhone, uAddress) VALUES (3, 'yhc', '袁汉超', '123456', '15965478520', '河北石家庄')";
        String sql104 = "INSERT INTO Customer (uId, uAccount, uName, uPas, uPhone, uAddress) VALUES (4, 'hyc', '韩运畅', '123456', '13845697410', '湖南长沙')";
        String sql105 = "INSERT INTO Customer (uId, uAccount, uName, uPas, uPhone, uAddress) VALUES (5, 'xhr', '薛浩然', '123456', '13865421230', '湖北武汉')";
        String sql106 = "INSERT INTO Customer (uId, uAccount, uName, uPas, uPhone, uAddress) VALUES (6, 'la', '李奥', '123456', '13825864562', '云南昆明')";
        String sql107 = "INSERT INTO Customer (uId, uAccount, uName, uPas, uPhone, uAddress) VALUES (7, 'zht', '朱昊天', '123456', '15956478520', '吉林长春')";
        String sql108 = "INSERT INTO Customer (uId, uAccount, uName, uPas, uPhone, uAddress) VALUES (8, 'lb', '李博', '123456', '15985230214', '内蒙古呼和浩特')";
        String sql109 = "INSERT INTO Customer (uId, uAccount, uName, uPas, uPhone, uAddress) VALUES (9, 'czp', '崔志鹏', '123456', '15965203245', '安徽合肥')";
        String sql110 = "INSERT INTO Customer (uId, uAccount, uName, uPas, uPhone, uAddress) VALUES (10, 'mm', '马明', '123456', '15678956541', '山西太原')";
        String sql111 = "INSERT INTO Customer (uId, uAccount, uName, uPas, uPhone, uAddress) VALUES (11, 'wt', '王涛', '123456', '15632102589', '辽宁沈阳')";
        String sql112 = "INSERT INTO Customer (uId, uAccount, uName, uPas, uPhone, uAddress) VALUES (12, 'nc', '倪晨', '123456', '13945896250', '黑龙江哈尔滨')";
        String sql113 = "INSERT INTO Customer (uId, uAccount, uName, uPas, uPhone, uAddress) VALUES (13, 'thb', '唐洪斌', '123456', '15689652145', '浙江杭州')";
        String sql114 = "INSERT INTO Customer (uId, uAccount, uName, uPas, uPhone, uAddress) VALUES (14, 'csw', '陈烁文', '123456', '15699510356', '福建福州')";
        db.execSQL(sql101);
        db.execSQL(sql102);
        db.execSQL(sql103);
        db.execSQL(sql104);
        db.execSQL(sql105);
        db.execSQL(sql106);
        db.execSQL(sql107);
        db.execSQL(sql108);
        db.execSQL(sql109);
        db.execSQL(sql110);
        db.execSQL(sql111);
        db.execSQL(sql112);
        db.execSQL(sql113);
        db.execSQL(sql114);


        //添加Restaurant
        String icon_1=R.drawable.shop_icon_1+"";
        String icon_2= R.drawable.shop_icon_2+"";
        String icon_3=R.drawable.shop_icon_3+"";
        String icon_4=R.drawable.shop_icon_4+"";
        String icon_5=R.drawable.shop_icon_5+"";
        String icon_6=R.drawable.shop_icon_6+"";
        String icon_7=R.drawable.shop_icon_7+"";
        String sql201=  "INSERT INTO Restaurant (bId, bAccount, bPas, bName ,bImage,bAddress,bStar,bSend_cost,bSales) VALUES (1, 'kzzc', '123123', '卤人甲','"+icon_1+"','0.8',4.9,15,300);";
        String sql202 = "INSERT INTO Restaurant (bId, bAccount, bPas, bName ,bImage,bAddress,bStar,bSend_cost,bSales) VALUES (2, 'bmxs', '123123', '八戒炒饭','"+icon_2+"','2.5',3.7,25,600);";
        String sql203 = "INSERT INTO Restaurant (bId, bAccount, bPas, bName ,bImage,bAddress,bStar,bSend_cost,bSales) VALUES (3, 'xdk', '123123', '叫了只炸鸡','"+icon_3+"','1.5',4.5,25,700);";
        String sql204 = "INSERT INTO Restaurant (bId, bAccount, bPas, bName ,bImage,bAddress,bStar,bSend_cost,bSales) VALUES (4, 'hnzj', '123123', '桥头排骨','"+icon_4+"','3.5',4.6,20,900);";
        String sql205 = "INSERT INTO Restaurant (bId, bAccount, bPas, bName ,bImage,bAddress,bStar,bSend_cost,bSales) VALUES (5, 'sds', '123123', '台资味','"+icon_5+"','1.2',4.9,35,500);";
        String sql206 = "INSERT INTO Restaurant (bId, bAccount, bPas, bName ,bImage,bAddress,bStar,bSend_cost,bSales) VALUES (6, 'hls', '123123', '肯德基','"+icon_6+"','7.0',4.5,25,400);";
        String sql207 = "INSERT INTO Restaurant (bId, bAccount, bPas, bName ,bImage,bAddress,bStar,bSend_cost,bSales) VALUES (7, 'hmj', '123123', '老乡鸡','"+icon_7+"','5.2',4.3,20,100);";
        db.execSQL(sql201);
        db.execSQL(sql202);
        db.execSQL(sql203);
        db.execSQL(sql204);
        db.execSQL(sql205);
        db.execSQL(sql206);
        db.execSQL(sql207);



        //添加Menu
        String shop_icon_1_menu_1=R.drawable.shop_icon_1_menu_1+"";
        String shop_icon_1_menu_2=R.drawable.shop_icon_1_menu_2+"";
        String shop_icon_1_menu_3=R.drawable.shop_icon_1_menu_3+"";
        String shop_icon_1_menu_4=R.drawable.shop_icon_1_menu_4+"";
        String shop_icon_1_menu_5=R.drawable.shop_icon_1_menu_5+"";
        String shop_icon_1_menu_6=R.drawable.shop_icon_1_menu_6+"";
        String shop_icon_1_menu_7=R.drawable.shop_icon_1_menu_7+"";

        String shop_icon_2_menu_1=R.drawable.shop_icon_2_menu_1+"";
        String shop_icon_2_menu_2=R.drawable.shop_icon_2_menu_2+"";
        String shop_icon_2_menu_3=R.drawable.shop_icon_2_menu_3+"";
        String shop_icon_2_menu_4=R.drawable.shop_icon_2_menu_4+"";
        String shop_icon_2_menu_5=R.drawable.shop_icon_2_menu_5+"";
        String shop_icon_2_menu_6=R.drawable.shop_icon_2_menu_6+"";
        String shop_icon_2_menu_7=R.drawable.shop_icon_2_menu_7+"";

        String shop_icon_3_menu_1=R.drawable.shop_icon_3_menu_1+"";
        String shop_icon_3_menu_2=R.drawable.shop_icon_3_menu_2+"";
        String shop_icon_3_menu_3=R.drawable.shop_icon_3_menu_3+"";
        String shop_icon_3_menu_4=R.drawable.shop_icon_3_menu_4+"";
        String shop_icon_3_menu_5=R.drawable.shop_icon_3_menu_5+"";
        String shop_icon_3_menu_6=R.drawable.shop_icon_3_menu_6+"";
        String shop_icon_3_menu_7=R.drawable.shop_icon_3_menu_7+"";

        String shop_icon_4_menu_1=R.drawable.shop_icon_4_menu_1+"";
        String shop_icon_4_menu_2=R.drawable.shop_icon_4_menu_2+"";
        String shop_icon_4_menu_3=R.drawable.shop_icon_4_menu_3+"";
        String shop_icon_4_menu_4=R.drawable.shop_icon_4_menu_4+"";
        String shop_icon_4_menu_5=R.drawable.shop_icon_4_menu_5+"";
        String shop_icon_4_menu_6=R.drawable.shop_icon_4_menu_6+"";
        String shop_icon_4_menu_7=R.drawable.shop_icon_4_menu_7+"";

        String shop_icon_5_menu_1=R.drawable.shop_icon_5_menu_1+"";
        String shop_icon_5_menu_2=R.drawable.shop_icon_5_menu_2+"";
        String shop_icon_5_menu_3=R.drawable.shop_icon_5_menu_3+"";
        String shop_icon_5_menu_4=R.drawable.shop_icon_5_menu_4+"";
        String shop_icon_5_menu_5=R.drawable.shop_icon_5_menu_5+"";
        String shop_icon_5_menu_6=R.drawable.shop_icon_5_menu_6+"";
        String shop_icon_5_menu_7=R.drawable.shop_icon_5_menu_7+"";

        String shop_icon_6_menu_1=R.drawable.shop_icon_6_menu_1+"";
        String shop_icon_6_menu_2=R.drawable.shop_icon_6_menu_2+"";
        String shop_icon_6_menu_3=R.drawable.shop_icon_6_menu_3+"";
        String shop_icon_6_menu_4=R.drawable.shop_icon_6_menu_4+"";
        String shop_icon_6_menu_5=R.drawable.shop_icon_6_menu_5+"";
        String shop_icon_6_menu_6=R.drawable.shop_icon_6_menu_6+"";
        String shop_icon_6_menu_7=R.drawable.shop_icon_6_menu_7+"";

        String shop_icon_7_menu_1=R.drawable.shop_icon_7_menu_1+"";
        String shop_icon_7_menu_2=R.drawable.shop_icon_7_menu_2+"";
        String shop_icon_7_menu_3=R.drawable.shop_icon_7_menu_3+"";
        String shop_icon_7_menu_4=R.drawable.shop_icon_7_menu_4+"";
        String shop_icon_7_menu_5=R.drawable.shop_icon_7_menu_5+"";
        String shop_icon_7_menu_6=R.drawable.shop_icon_7_menu_6+"";
        String shop_icon_7_menu_7=R.drawable.shop_icon_7_menu_7+"";
        String shop_icon_7_menu_8=R.drawable.shop_icon_7_menu_8+"";
        String shop_icon_7_menu_9=R.drawable.shop_icon_7_menu_9+"";
        String shop_icon_7_menu_10=R.drawable.shop_icon_7_menu_10+"";
        String shop_icon_7_menu_11=R.drawable.shop_icon_7_menu_11+"";
        String shop_icon_7_menu_12=R.drawable.shop_icon_7_menu_12+"";
        String shop_icon_7_menu_13=R.drawable.shop_icon_7_menu_13+"";
        String shop_icon_7_menu_14=R.drawable.shop_icon_7_menu_14+"";
        String shop_icon_7_menu_15=R.drawable.shop_icon_7_menu_15+"";
        String shop_icon_7_menu_16=R.drawable.shop_icon_7_menu_16+"";
        String shop_icon_7_menu_17=R.drawable.shop_icon_7_menu_17+"";
        String shop_icon_7_menu_18=R.drawable.shop_icon_7_menu_18+"";

        String sql301="INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('黄金炸鸡爪', 25.00, '香酥脆嫩举个爪，每天卖出10万加', 1,'"+shop_icon_1_menu_1+"',10);";
        String sql302 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('黄金掌中宝', 20.00, '珍贵的保留给珍贵的你', 1,'"+shop_icon_1_menu_2+"',12);";
        String sql303 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('黄金鸡翅尖', 20.00, '架子小肉多但脾气爆治', 1,'"+shop_icon_1_menu_3+"',13);";
        String sql304 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('黄金藕棒', 12.00, '藕棒一顿吃3根', 1,'"+shop_icon_1_menu_4+"',14);";
        String sql305 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('黄金鸡翅', 17.00, '鲜嫩多汁，碰', 1,'"+shop_icon_1_menu_5+"',15);";
        String sql306 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('脆皮炸鸭舌', 15.00, '舌尖上的美味', 1,'"+shop_icon_1_menu_6+"',17);";
        String sql307 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ( '秘制卤鸡爪', 25.00, '锁住你的胶原蛋白', 1,'"+shop_icon_1_menu_7+"',14);";


        String sql308="INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('皇帝炒饭', 18.88, '虾仁，香肠，火腿，培根，里脊，鸡蛋', 2,'"+shop_icon_2_menu_1+"',10);";
        String sql309 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('金陵烤鸭炒饭', 39.88, '主要原料：鸭肉', 2,'"+shop_icon_2_menu_2+"',12);";
        String sql310 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('三鲜炒饭',38.88, '主要原料：辣椒酱', 2,'"+shop_icon_2_menu_3+"',13);";
        String sql311 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('扬州八怪炒饭', 37.88, '主要原料：鸡蛋', 2,'"+shop_icon_2_menu_4+"',14);";
        String sql312 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('猪八戒炒饭', 35.88, '主要原料：猪肉', 2,'"+shop_icon_2_menu_5+"',15);";
        String sql313 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ( '牛魔王套餐',49.88, '牛肉炒饭+秘制牛肉酱', 2,'"+shop_icon_2_menu_6+"',17);";
        String sql314 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('手工狮子头炒饭', 38.00, '主要原料：大米', 2,'"+shop_icon_2_menu_7+"',14);";

        String sql315 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ( '原味叫香鸡', 35.90, '主要原料：鸡肉', 3,'"+shop_icon_3_menu_1+"',12);";
        String sql316 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('甘梅叫香鸡', 35.90, '主要原料：鸡肉', 3,'"+shop_icon_3_menu_2+"',13);";
        String sql317 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ( '孜然叫香鸡', 35.90, '主要原料：鸡肉', 3,'"+shop_icon_3_menu_3+"',15);";
        String sql318 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('叫香鸡', 35.90, '主要原料：鸡肉', 3,'"+shop_icon_3_menu_4+"',18);";
        String sql319 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('黑胡椒叫香鸡', 35.90, '主要原料：鸡肉', 3,'"+shop_icon_3_menu_5+"',12);";
        String sql320 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('叫香鸡翅', 12.00, '店长推荐', 3,'"+shop_icon_3_menu_6+"',14);";
        String sql321 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('叫香大鸡排', 12.00, '店长推荐', 3,'"+shop_icon_3_menu_7+"',13);";

        String sql322 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('香辣排骨', 17.00, '主要原料：猪肋排', 4,'"+shop_icon_4_menu_1+"',14);";
        String sql323 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ( '甘梅排骨', 17.00, '主要原料：猪肋排', 4,'"+shop_icon_4_menu_2+"',14);";
        String sql324 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ( '孜然排骨', 17.00, '主要原料：猪肋排', 4,'"+shop_icon_4_menu_3+"',15);";
        String sql325 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ( '椒盐排骨', 17.00, '主要原料：猪肋排', 4,'"+shop_icon_4_menu_4+"',18);";
        String sql326 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ( '梅子桥头炸肉', 15.00, '主要原料：猪肉', 4,'"+shop_icon_4_menu_5+"',14);";
        String sql327 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('孜然桥头炸肉', 15.00, '主要原料：猪肉', 4,'"+shop_icon_4_menu_6+"',13);";
        String sql328 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ( '椒盐桥头炸肉', 15.00, '主要原料：猪肉', 4,'"+shop_icon_4_menu_7+"',14);";

        String sql329 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ( '台湾大师1号卤肉饭', 17.80, '店长强烈推荐菜品', 5,'"+shop_icon_5_menu_1+"',18);";
        String sql330 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES (  '客家香笋扣肉便当', 18.88, '主要原料：猪肉', 5,'"+shop_icon_5_menu_2+"',15);";
        String sql331 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ( '花莲棒球鸡', 8.00, '金黄酥脆',5,'"+shop_icon_5_menu_3+"',15);";
        String sql332 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ( '泰雅奥尔良鸡排',15.88, '主要原料：奥尔良鸡排', 5,'"+shop_icon_5_menu_4+"',18);";
        String sql333 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ( '正味三杯鸡便当',18.88, '一杯米酒，一杯酱油，一杯幼砂糖', 5,'"+shop_icon_5_menu_5+"',12);";
        String sql334 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('梅干菜扣肉饭', 18.50, '主要原料：猪肉', 5,'"+shop_icon_5_menu_6+"',13);";
        String sql335 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES (  '炭烤鸭串', 12.00, '炭烤口味，外焦里嫩', 5,'"+shop_icon_5_menu_7+"',15);";

        String sql336 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ( '春风满心单人套餐', 27.5, '拿铁1杯，香辣鸡翅2块', 6,'"+shop_icon_6_menu_1+"',18);";
        String sql337 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES (   '甜蜜下午茶双人套餐', 49, '九龙金玉珍珠奶茶，香辣黄金鸡柳，蛋挞，红豆派，辣香骨鸡', 6,'"+shop_icon_6_menu_2+"',16);";
        String sql338 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ( '冲绳黑糖珍珠奶茶', 23, '九龙金玉珍珠奶茶',6,'"+shop_icon_6_menu_3+"',16);";
        String sql339 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ( '炸鸡分享桶',60, '鸡翅，烤翅，鸡块，鸡米花，果汁饮料', 6,'"+shop_icon_6_menu_4+"',18);";
        String sql340 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('厚切牛排明星餐',57.5, '牛排堡，黄金鸡柳，可乐，蛋挞', 6,'"+shop_icon_6_menu_5+"',12);";
        String sql341 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('手撕火鸡肉鸡腿堡',25.5, '鸡肉，火鸡肉，面包，生菜等', 6,'"+shop_icon_6_menu_6+"',13);";
        String sql342 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES (  '六味小吃桶', 84.5, '烤翅，辣翅，原味鸡，黄金鸡块，鸡米花，红豆派', 6,'"+shop_icon_6_menu_7+"',16);";

        String sql343 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ('农家蒸蛋',8, '鸡蛋，青葱，咸鸡油蚕豆酱', 7,'"+shop_icon_7_menu_1+"',18);";
        String sql344 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES (  '香肠蒸豆米', 14, '猪肉香肠，豆米，咸鸡油', 7,'"+shop_icon_7_menu_2+"',16);";
        String sql345 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ( '葱油鸡', 17, '鸡块，葱油，青葱，姜片，酱油', 7,'"+shop_icon_7_menu_3+"',16);";
        String sql346 = "INSERT INTO Menu (mName, mPrice, mIntroduction, bId,mImage,mStar) VALUES ( '香辣鸡杂',15, '鸡胗，鸡心，鸡肝，鸡肺，红辣油，干辣椒', 7,'"+shop_icon_7_menu_4+"',18);";



        db.execSQL(sql301);
        db.execSQL(sql302);
        db.execSQL(sql303);
        db.execSQL(sql304);
        db.execSQL(sql305);
        db.execSQL(sql306);
        db.execSQL(sql307);

        db.execSQL(sql308);
        db.execSQL(sql309);
        db.execSQL(sql310);
        db.execSQL(sql311);
        db.execSQL(sql312);
        db.execSQL(sql313);
        db.execSQL(sql314);

        db.execSQL(sql315);
        db.execSQL(sql316);
        db.execSQL(sql317);
        db.execSQL(sql318);
        db.execSQL(sql319);
        db.execSQL(sql320);
        db.execSQL(sql321);

        db.execSQL(sql322);
        db.execSQL(sql323);
        db.execSQL(sql324);
        db.execSQL(sql325);
        db.execSQL(sql326);
        db.execSQL(sql327);
        db.execSQL(sql328);

        db.execSQL(sql329);
        db.execSQL(sql330);
        db.execSQL(sql331);
        db.execSQL(sql332);
        db.execSQL(sql333);
        db.execSQL(sql334);
        db.execSQL(sql335);

        db.execSQL(sql336);
        db.execSQL(sql337);
        db.execSQL(sql338);
        db.execSQL(sql339);
        db.execSQL(sql340);
        db.execSQL(sql341);
        db.execSQL(sql342);

        db.execSQL(sql343);
        db.execSQL(sql344);
        db.execSQL(sql345);
        db.execSQL(sql346);




        //添加order
        String sql401 = "INSERT INTO \"Order\" ( uId, mIds, oDate, oPrice,bId,oAddress,oEvalute,dId) VALUES (14, '1,2,3', '2019-12-01', 45,1,'金陵科技学院','菜品很好1',1);\n";
        String sql402 = "INSERT INTO \"Order\" (uId, mIds, oDate, oPrice,bId,oAddress,oEvalute,dId) VALUES (13, '3,4', '2019-12-08', 32,1,'金陵科技学院','菜品很好1',1);\n";
        String sql403 = "INSERT INTO \"Order\" (uId, mIds, oDate, oPrice,bId,oAddress,oEvalute,dId) VALUES (2, '8,9,10', '2019-12-09', 97.64,2,'金陵科技学院','菜品很好1',1);\n";
        String sql404 = "INSERT INTO \"Order\" (uId, mIds, oDate, oPrice,bId,oAddress,oEvalute,dId) VALUES (14, '16,17,18', '2019-12-09', 107.7,3,'金陵科技学院','菜品很好1',1);\n";
        String sql405 = "INSERT INTO \"Order\" (uId, mIds, oDate, oPrice,bId,oAddress,oEvalute,dId) VALUES (14, '7', '2019-12-10', 25,1,'金陵科技学院','菜品很好1',1);\n";
        String sql406 = "INSERT INTO \"Order\" (uId, mIds, oDate, oPrice,bId,oAddress,oEvalute,dId) VALUES (14, '22,25', '2019-12-11', 34,4,'金陵科技学院','菜品很好1',1);\n";



        db.execSQL(sql401);
        db.execSQL(sql402);
        db.execSQL(sql403);
        db.execSQL(sql404);
        db.execSQL(sql405);
        db.execSQL(sql406);
    }



}
