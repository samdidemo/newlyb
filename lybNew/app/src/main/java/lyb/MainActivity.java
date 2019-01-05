package lyb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lyb.com.example.BottomBar;
import lyb.com.example.Fragment1;
import lyb.com.example.Fragment2;
import lyb.com.example.Fragment3;

import lyb.com.example.LoginActivity1;
import lyb.gson.News;
import lyb.gson.NewsList;
import lyb.util.HttpUtil;
import lyb.util.Utility;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static lyb.R.*;

public class MainActivity extends AppCompatActivity {
    private static  final int ITEM_ONLINE=20;
    private static final int  ITEM_SOCIETY= 1;
    private static final int  ITEM_COUNTY= 2;
    private static final int  ITEM_INTERNATION= 3;
    private static final int  ITEM_FUN= 4;
    private static final int  ITEM_SPORT= 5;
    private static final int  ITEM_NBA= 6;
    private static final int  ITEM_FOOTBALL= 7;
    private static final int  ITEM_TECHNOLOGY= 8;
    private static final int  ITEM_WORK= 9;
    private static final int  ITEM_APPLE= 10;
    private static final int  ITEM_WAR= 11;
    private static final int  ITEM_INTERNET= 12;
    private static final int  ITEM_TREVAL= 13;
    private static final int  ITEM_HEALTH= 14;
    private static final int  ITEM_STRANGE= 15;
    private static final int  ITEM_LOOKER= 16;
    private static final int  ITEM_VR= 17;
    private static final int  ITEM_IT= 18;


    private List<Title> titleList = new ArrayList<Title>();
    private ListView listView;
    private TitleAdapter adapter;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private SwipeRefreshLayout refreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(id.tool_bar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(drawable.ic_menu);
        }
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("社会新闻");

        refreshLayout = (SwipeRefreshLayout)findViewById(id.swipe_layout);
        refreshLayout.setColorSchemeColors(getResources().getColor(color.colorPrimary));
        listView = (ListView)findViewById(id.list_view);
        adapter = new TitleAdapter(this,layout.list_view_item, titleList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent = new Intent(MainActivity.this,ContentActivity.class);
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Title title = titleList.get(position);
                intent.putExtra("title",actionBar.getTitle());
                intent.putExtra("uri",title.getUri());
                startActivity(intent);
            }
        });
        /**
         * lyb
         * 2019.1.2
         * 点击回到顶部按钮，回到顶部
         */
        Button button=findViewById(id.rollback);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.smoothScrollToPositionFromTop(0,0);   //可以滑动着到顶部,后边的第一个参数设置要滑动到哪一个item，第二个不用管
            }
        });


        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        bottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor("#999999", "#ff5d5e")
                .addItem(Fragment1.class,
                        "首页",
                        drawable.newbefore,
                        drawable.newafter)
                .addItem(Fragment2.class,
                        "订单",
                        R.drawable.item2_before,
                        R.drawable.item2_after)
                .addItem(Fragment3.class,
                        "我的",
                        R.drawable.item3_before,
                        R.drawable.item3_after)
                .build();

        drawerLayout = (DrawerLayout)findViewById(id.drawer_layout);
        navigationView = (NavigationView)findViewById(id.nav_view);
        navigationView.setCheckedItem(id.nav_society);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case id.nav_society:
                        handleCurrentPage("社会新闻",ITEM_SOCIETY);
                        break;
                    case id.nav_county:
                        handleCurrentPage("国内新闻",ITEM_COUNTY);
                        break;
                    case id.nav_internation:
                        handleCurrentPage("国际新闻",ITEM_INTERNATION);
                        break;
                    case id.nav_fun:
                        handleCurrentPage("娱乐新闻",ITEM_FUN);
                        break;
                    case id.nav_sport:
                        handleCurrentPage("体育新闻",ITEM_SPORT);
                        break;
                    case id.nav_nba:
                        handleCurrentPage("NBA新闻",ITEM_NBA);
                        break;
                    case id.nav_football:
                        handleCurrentPage("足球新闻",ITEM_FOOTBALL);
                        break;
                    case id.nav_technology:
                        handleCurrentPage("科技新闻",ITEM_TECHNOLOGY);
                        break;
                    case id.nav_work:
                        handleCurrentPage("创业新闻",ITEM_WORK);
                        break;
                    case id.nav_apple:
                        handleCurrentPage("苹果新闻",ITEM_APPLE);
                        break;
                    case id.nav_war:
                        handleCurrentPage("军事新闻",ITEM_WAR);
                        break;
                    case id.nav_internet:
                        handleCurrentPage("移动互联",ITEM_INTERNET);
                        break;
                    case id.nav_travel:
                        handleCurrentPage("旅游资讯",ITEM_TREVAL);
                        break;
                    case id.nav_health:
                        handleCurrentPage("健康知识",ITEM_HEALTH);
                        break;
                    case id.nav_strange:
                        handleCurrentPage("奇闻异事",ITEM_STRANGE);
                        break;
                    case id.nav_vr:
                        handleCurrentPage("VR科技",ITEM_VR);
                        break;
                    case id.nav_it:
                        handleCurrentPage("IT资讯",ITEM_IT);
                        break;

                    default:
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
        /**
         * lyb
         * 实现刷新功能
         * 2018.12.29
         */
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                int itemName = parseString((String)actionBar.getTitle());//得到当前的title，根据title来刷新对应的内容
                requestNew(itemName);
            }
        });

        requestNew(ITEM_SOCIETY);


    }

    /**
     *  判断是否是当前页面,如果不是则 请求处理数据
     */
    private void handleCurrentPage(String text, int item){
        ActionBar actionBar = getSupportActionBar();
        if (!text.equals(actionBar.getTitle().toString())){
            actionBar.setTitle(text);
            requestNew(item);
            refreshLayout.setRefreshing(true);
        }
    }


    /**6
     * 请求处理数据
     */
    public void requestNew(int itemName){

        // 根据返回到的 URL 链接进行申请和返回数据
        String address = response(itemName);
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "新闻加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                /**
                 * 将json数据解析为newlist对象
                 * lyb.2018.12.29
                 */
                final NewsList newlist = Utility.parseJsonWithGson(responseText);
                final int code = newlist.code;
                if (code == 200){
                        titleList.clear();
                        for (News news:newlist.newsList){
                            Title title = new Title(news.title,news.description,news.picUrl, news.url);
                            titleList.add(title);
                        }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                            listView.setSelection(0);
                            refreshLayout.setRefreshing(false);
                        };
                    });
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "数据错误返回",Toast.LENGTH_SHORT).show();
                            refreshLayout.setRefreshing(false);
                        }
                    });
                }



            }
        });


    }

    /**
     * 输入不同的类型选项，返回对应的 URL 链接
     */
    private String response(int itemName){
        String address = "https://api.tianapi.com/social/?key=" + "f2b905c998159e3f04633daa8aeebf88&num=50";
        switch(itemName){
            case ITEM_SOCIETY:
                break;
            case ITEM_COUNTY:
                address = address.replaceAll("social","guonei");
//                address="https://api.tianapi.com/guonei/?key=339a8b166f397f008236e596616a5f54&num=50&rand=1";
                break;
            case ITEM_INTERNATION:
                address = address.replaceAll("social","world");
//                address="https://api.tianapi.com/world/?key=339a8b166f397f008236e596616a5f54&num=50&rand=1";
                break;
            case ITEM_FUN:
                address = address.replaceAll("social","huabian");
//                address="https://api.tianapi.com/huabian/?key=339a8b166f397f008236e596616a5f54&num=50&rand=1";
                break;
            case ITEM_SPORT:
                address = address.replaceAll("social","tiyu");
//                address="https://api.tianapi.com/tiyu/?key=339a8b166f397f008236e596616a5f54&num=50&rand=1";
                break;
            case ITEM_NBA:
                address = address.replaceAll("social","nba");
//                address="https://api.tianapi.com/nba/?key=339a8b166f397f008236e596616a5f54&num=50&rand=1";
                break;
            case ITEM_FOOTBALL:
                address = address.replaceAll("social","football");
//                address="https://api.tianapi.com/football/?key=339a8b166f397f008236e596616a5f54&num=50&rand=1";
                break;
            case ITEM_TECHNOLOGY:
                address = address.replaceAll("social","keji");
//                address="https://api.tianapi.com/keji/?key=339a8b166f397f008236e596616a5f54&num=50&rand=1";
                break;
            case ITEM_WORK:
                address = address.replaceAll("social","startup");
//                address="https://api.tianapi.com/startup/?key=339a8b166f397f008236e596616a5f54&num=50&rand=1";
                break;
            case ITEM_APPLE:
                address = address.replaceAll("social","apple");
//                address="https://api.tianapi.com/apple/?key=339a8b166f397f008236e596616a5f54&num=50&rand=1";
                break;
            case ITEM_WAR:
                address = address.replaceAll("social","military");
//                address="https://api.tianapi.com/military/?key=339a8b166f397f008236e596616a5f54&num=50&rand=1";
                break;
            case ITEM_INTERNET:
                address = address.replaceAll("social","mobile");
//                address="https://api.tianapi.com/mobile/?key=339a8b166f397f008236e596616a5f54&num=50&rand=1";
                break;
            case ITEM_TREVAL:
                address = address.replaceAll("social","travel");
//                address="https://api.tianapi.com/travel/?key=339a8b166f397f008236e596616a5f54&num=50&rand=1";
                break;
            case ITEM_HEALTH:
                address = address.replaceAll("social","health");
//                address="https://api.tianapi.com/health/?key=339a8b166f397f008236e596616a5f54&num=50&rand=1";
                break;
            case ITEM_STRANGE:
                address = address.replaceAll("social","qiwen");
//                address="https://api.tianapi.com/qiwen/?key=339a8b166f397f008236e596616a5f54&num=50&rand=1";
                break;
            case ITEM_VR:
                address = address.replaceAll("social","vr");
//                address="https://api.tianapi.com/vr/?key=339a8b166f397f008236e596616a5f54&num=50&rand=1";
                break;
            case ITEM_IT:
                address = address.replaceAll("social","it");
//                address="https://api.tianapi.com/it/?key=339a8b166f397f008236e596616a5f54&num=50&rand=1";
                break;
            default:
        }
        return address;
    }

    /**
     * 通过 actionbar.getTitle() 的参数，返回对应的 ItemName
     */
    private int parseString(String text){
        if (text.equals("社会新闻")){
            return ITEM_SOCIETY;
        }
        if (text.equals("国内新闻")){
            return ITEM_COUNTY;
        }
        if (text.equals("国际新闻")){
            return ITEM_INTERNATION;
        }
        if (text.equals("娱乐新闻")){
            return ITEM_FUN;
        }
        if (text.equals("体育新闻")){
            return ITEM_SPORT;
        }
        if (text.equals("NBA新闻")){
            return ITEM_NBA;
        }
        if (text.equals("足球新闻")){
            return ITEM_FOOTBALL;
        }
        if (text.equals("科技新闻")){
            return ITEM_TECHNOLOGY;
        }
        if (text.equals("创业新闻")){
            return ITEM_WORK;
        }
        if (text.equals("苹果新闻")){
            return ITEM_APPLE;
        }
        if (text.equals("军事新闻")){
            return ITEM_WAR;
        }
        if (text.equals("移动互联")){
            return ITEM_INTERNET;
        }
        if (text.equals("旅游资讯")){
            return ITEM_TREVAL;
        }
        if (text.equals("健康知识")){
            return ITEM_HEALTH;
        }
        if (text.equals("奇闻异事")){
            return ITEM_STRANGE;
        }
        if (text.equals("VR科技")){
            return ITEM_VR;
        }
        if (text.equals("IT资讯")){
            return ITEM_IT;
        }
        return ITEM_SOCIETY;
    }

    /**
     * 对侧边栏按钮进行处理，打开侧边栏
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    /**
     * 对返回键进行处理，如果侧边栏打开则关闭侧边栏，否则关闭 activity
     */
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawers();
        }else{
            finish();
        }
    }
}
