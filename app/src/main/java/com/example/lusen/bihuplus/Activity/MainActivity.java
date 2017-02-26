package com.example.lusen.bihuplus.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.example.lusen.bihuplus.Adapt.Recycle_Adapt;
import com.example.lusen.bihuplus.Data.First_Pager_Data;
import com.example.lusen.bihuplus.Data.News;
import com.example.lusen.bihuplus.Data.News_before;
import com.example.lusen.bihuplus.Data.News_content;
import com.example.lusen.bihuplus.HttpUtils.Date_split;
import com.example.lusen.bihuplus.HttpUtils.MyGsonUtil;
import com.example.lusen.bihuplus.HttpUtils.MyHttpURL;
import com.example.lusen.bihuplus.HttpUtils.Viewpager_lunbo;
import com.example.lusen.bihuplus.R;
import com.example.lusen.bihuplus.widget.ADViewPager;
import com.example.lusen.bihuplus.widget.RefreshRecyclerView;

import java.util.ArrayList;

import static com.example.lusen.bihuplus.R.id.recycle;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //viewpager一套
    private String[] imageUrls = new String[5];
    private String[] imageHref = new String[5];
    private Date_split data;
    private TextView textView;
    private int number;
    private RecyclerViewHeader recyclerViewHeader;
    private Recycle_Adapt myAdapter;
    private Viewpager_lunbo viewpagerLunbo;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<String> titleList = new ArrayList<String>();

    private RefreshRecyclerView recyclerView;
    public ArrayList<News> list = new ArrayList<News>();
    private ArrayList<First_Pager_Data> arrylist = new ArrayList<First_Pager_Data>();

//    @BindView(R.id.viewPager_main_ad)
    ADViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.title);
        recyclerView = (RefreshRecyclerView) findViewById(recycle);    //实例化RecycleView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));    //决定布局
        viewpagerLunbo = new Viewpager_lunbo(this,viewPager);

//        ButterKnife.bind(this);

        initView();
        hot_news();     //加载viewpager 和recycleView 一条龙服务（加载图片url，获取）。

        initListener();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        MyHttpURL.get("http://news-at.zhihu.com/api/4/themes", new MyHttpURL.Callback() {
//            @Override
//            public void onResponse(String response) {
//
//            }
//        });

        if (id == R.id.日常心理学) {
            Log.d("maintivity","点击成功");
            Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
            number = 0;
            intent.putExtra("number",number);
            startActivity(intent);
        } else if (id == R.id.用户推荐日报) {
            Log.d("maintivity","点击成功");
            number = 1;
            Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
            intent.putExtra("number",number);
            startActivity(intent);
        } else if (id == R.id.电影日报) {
            Log.d("maintivity","点击成功");
            number = 2;
            Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
            intent.putExtra("number",number);
            startActivity(intent);
        } else if (id == R.id.不许无聊) {
            Log.d("maintivity","点击成功");
            number = 3;
            Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
            intent.putExtra("number",number);
            startActivity(intent);
        } else if (id == R.id.设计日报) {
            Log.d("maintivity","点击成功");
            number = 4;
            Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
            intent.putExtra("number",number);
            startActivity(intent);
        } else if (id == R.id.大公司日报) {
            Log.d("maintivity","点击成功");
            number = 5;
            Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }else if (id == R.id.财经日报) {
            Log.d("maintivity","点击成功");
            number = 6;
            Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
            intent.putExtra("number",number);
            startActivity(intent);
        } else if (id == R.id.互联网安全) {
            Log.d("maintivity","点击成功");
            number = 7;
            Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
            intent.putExtra("number",number);
            startActivity(intent);
        } else if (id == R.id.开始游戏) {
            Log.d("maintivity","点击成功");
            number = 8;
            Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
            intent.putExtra("number",number);
            startActivity(intent);
        } else if (id == R.id.音乐日报) {
            Log.d("maintivity","点击成功");
            number = 9;
            Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
            intent.putExtra("number",number);
            startActivity(intent);
        } else if (id == R.id.体育日报) {
            Log.d("maintivity","点击成功");
            number = 11;
            Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }else if (id == R.id.动漫日报) {
            Log.d("maintivity","点击成功");
            number = 10;
            Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
            intent.putExtra("number",number);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void hot_news(){

        //网络请求并解析，最新消息
        MyHttpURL.get("http://news-at.zhihu.com/api/4/news/latest", new MyHttpURL.Callback() {
            @Override
            public void onResponse(String response) {
                response = "["+response+"]";
                 list= (ArrayList<News>) MyGsonUtil.getObjectList(response,News.class);
                data = new Date_split(list.get(0).getDate());
                for(int j = 0;j < list.get(0).getStories().size();j++){    //准备好数据
                    if (list.get(0).getStories().get(j).getImages()[0] != null || list.get(0).getStories().get(j).getTitle() != null){
                        Log.d("Mainticity",list.get(0).getStories().get(j).getTitle());
                         First_Pager_Data data = new First_Pager_Data(list.get(0).getStories().get(j).getTitle());
                        data.setImagId(list.get(0).getStories().get(j).getImages()[0]);
                        arrylist.add(data);     //RecycleView在首页显示的内容
                    }

                }
                //获取到recycleview各个消息中的跳转路径
                for(int i = 0;i < list.get(0).getStories().size();i++){
                    final int finalI = i;
                    MyHttpURL.get("http://news-at.zhihu.com/api/4/news/" + list.get(0).getStories().get(i).getId(), new MyHttpURL.Callback() {
                        @Override
                        public void onResponse(String response) {
                            response = "["+response+"]";
                            ArrayList<News_content> list_2 = (ArrayList<News_content>) MyGsonUtil.getObjectList(response,News_content.class);
                           arrylist.get(finalI).setImageHref(list_2.get(0).getShare_url());       //获取到RecycleView点击后要跳转的页面URL
                        }
                    });
                }

                //获取到viewpager中的消息内容 ，即点击跳转的路径
                for(int i = 0;i < list.get(0).getTop_stories().size();i++){
                    final int finalI = i;
                    MyHttpURL.get("http://news-at.zhihu.com/api/4/news/" + list.get(0).getTop_stories().get(i).getId(), new MyHttpURL.Callback() {
                        @Override
                        public void onResponse(String response) {
                            response = "["+response+"]";
                            ArrayList<News_content> list_1 = (ArrayList<News_content>) MyGsonUtil.getObjectList(response,News_content.class);
                            imageHref[finalI] = list_1.get(0).getShare_url();       //获取到ViewPager点击后需要跳转的页面URL
                        }
                    });
                }

                for (int i=0; i < list.get(0).getTop_stories().size(); i++ ){
                    if (list.get(0).getTop_stories().get(i).getImages() != null ){
                        imageUrls[i] = list.get(0).getTop_stories().get(i).getImages();     //获取到viewPager要显示图片URL
                        titleList.add(list.get(0).getTop_stories().get(i).getTitle());      //获取到viewPage要显示的标题
                    }
                }

                //加载viewpager轮播图三部曲——已经封装到RecycleView的在适配器中
//                viewpagerLunbo.setImageHref(imageHref);
//                viewpagerLunbo.setImageUrls(imageUrls);
//                viewpagerLunbo.setTitle(titleList);
//                viewpagerLunbo.setSubView();    //最后调用
                myAdapter.setNews(arrylist);
                myAdapter.setImageHref_Pager(imageHref);    //设置viewpager图片跳转路径
                myAdapter.setImageUrl_Pager(imageUrls);     //设置ViewPager图片加载路径
                myAdapter.setTitle_Pager(titleList);        //设置Viewpager标题
                recyclerView.setAdapter(myAdapter);  //设置好适配器

            }
        });
    }

    private void initListener() {       //加载过往消息

        recyclerView.setOnLoadMoreListener(new RefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMoreListener() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                      MyHttpURL.get("http://news.at.zhihu.com/api/4/news/before/" + data.add(-1), new MyHttpURL.Callback() {
                          @Override
                          public void onResponse(String response) {

                              final ArrayList<First_Pager_Data> first_pager_datas = new ArrayList<First_Pager_Data>();
                              response = "["+response+"]";
                              ArrayList<News_before> news_befores = (ArrayList<News_before>) MyGsonUtil.getObjectList(response,News_before.class);

                              for(int j = 0;j < news_befores.get(0).getStories().size();j++){    //准备好数据
                                  Log.d("Mantivity before",news_befores.get(0).getStories().get(j).getImages()[0]+" ");
                                      First_Pager_Data data = new First_Pager_Data(news_befores.get(0).getStories().get(j).getTitle());
                                      data.setImagId(news_befores.get(0).getStories().get(j).getImages()[0]);
                                      first_pager_datas.add(data);
                              }
                              //加载过往消息的跳转路径
                              for(int i = 0;i < list.get(0).getStories().size();i++){
                                  final int finalI = i;
                                  MyHttpURL.get("http://news-at.zhihu.com/api/4/news/" + news_befores.get(0).getStories().get(i).getId(), new MyHttpURL.Callback() {
                                      @Override
                                      public void onResponse(String response) {
                                          response = "["+response+"]";
                                          ArrayList<News_content> list_2 = (ArrayList<News_content>) MyGsonUtil.getObjectList(response,News_content.class);
                                          first_pager_datas.get(finalI).setImageHref(list_2.get(0).getShare_url());       //获取到RecycleView点击后要跳转的页面URL
                                      }
                                  });
                              }
                              myAdapter.addData(first_pager_datas);
                          }
                      });
                        recyclerView.notifyData();//刷新数据
                    }
                }, 2000);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        hot_news();
                        recyclerView.notifyData();//刷新数据
                    }
                }, 2000);
            }
        });
    }

    private Handler handler = new Handler();

    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.demo_swiperefreshlayout);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_blue_light, android.R.color.holo_green_light);
        recyclerView = (RefreshRecyclerView) findViewById(recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLoadMoreEnable(true);//允许加载更多
        recyclerView.setFooterResource(R.layout.foot);//设置脚布局
        myAdapter = new Recycle_Adapt(this,MainActivity.this,arrylist);

    }

}

