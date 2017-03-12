package com.example.lusen.bihuplus.activity;

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

import com.example.lusen.bihuplus.R;
import com.example.lusen.bihuplus.adapt.RecycleAdapt;
import com.example.lusen.bihuplus.data.FirstPagerData;
import com.example.lusen.bihuplus.data.News;
import com.example.lusen.bihuplus.data.NewsBefore;
import com.example.lusen.bihuplus.data.NewsContent;
import com.example.lusen.bihuplus.httputils.DateSplit;
import com.example.lusen.bihuplus.httputils.MyGsonUtil;
import com.example.lusen.bihuplus.httputils.MyHttpURL;
import com.example.lusen.bihuplus.widget.ADViewPager;
import com.example.lusen.bihuplus.widget.RefreshRecyclerView;

import java.util.ArrayList;

import static com.example.lusen.bihuplus.R.id.recycle;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //viewpager一套
    private String[] imageUrls = new String[5];
    private String[] imageHref = new String[5];
    private DateSplit data;
    private TextView textView;
    private int number;
    private RecycleAdapt myAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<String> titleList = new ArrayList<String>();

    private RefreshRecyclerView recyclerView;
    public ArrayList<News> list = new ArrayList<News>();
    private ArrayList<FirstPagerData> arrylist = new ArrayList<FirstPagerData>();

    ADViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.title);
        recyclerView = (RefreshRecyclerView) findViewById(recycle);    //实例化RecycleView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));    //决定布局
//        viewpagerLunbo = new ViewpagerLunbo(this,viewPager);

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
                data = new DateSplit(list.get(0).getDate());
                for(int j = 0;j < list.get(0).getStories().size();j++){    //准备好数据
                    if (list.get(0).getStories().get(j).getImages()[0] != null || list.get(0).getStories().get(j).getTitle() != null){
                        Log.d("Mainticity",list.get(0).getStories().get(j).getTitle());
                         FirstPagerData data = new FirstPagerData(list.get(0).getStories().get(j).getTitle());
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
                            ArrayList<NewsContent> list2 = (ArrayList<NewsContent>) MyGsonUtil.getObjectList(response,NewsContent.class);
                           arrylist.get(finalI).setImageHref(list2.get(0).getShare_url());       //获取到RecycleView点击后要跳转的页面URL
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
                            ArrayList<NewsContent> list1 = (ArrayList<NewsContent>) MyGsonUtil.getObjectList(response,NewsContent.class);
                            imageHref[finalI] = list1.get(0).getShare_url();       //获取到ViewPager点击后需要跳转的页面URL
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
//                ViewpagerLunbo.setImageHref(imageHref);
//                ViewpagerLunbo.setImageUrls(imageUrls);
//                ViewpagerLunbo.setTitle(titleList);
//                ViewpagerLunbo.setSubView();    //最后调用
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

                              final ArrayList<FirstPagerData> firstpagerdatas = new ArrayList<FirstPagerData>();
                              response = "["+response+"]";
                              ArrayList<NewsBefore> news_befores = (ArrayList<NewsBefore>) MyGsonUtil.getObjectList(response,NewsBefore.class);

                              for(int j = 0;j < news_befores.get(0).getStories().size();j++){    //准备好数据
                                  Log.d("Mantivity before",news_befores.get(0).getStories().get(j).getImages()[0]+" ");
                                      FirstPagerData data = new FirstPagerData(news_befores.get(0).getStories().get(j).getTitle());
                                      data.setImagId(news_befores.get(0).getStories().get(j).getImages()[0]);
                                      firstpagerdatas.add(data);
                              }
                              //加载过往消息的跳转路径
                              for(int i = 0;i < list.get(0).getStories().size();i++){
                                  final int finalI = i;
                                  MyHttpURL.get("http://news-at.zhihu.com/api/4/news/" + news_befores.get(0).getStories().get(i).getId(), new MyHttpURL.Callback() {
                                      @Override
                                      public void onResponse(String response) {
                                          response = "["+response+"]";
                                          ArrayList<NewsContent> list2 = (ArrayList<NewsContent>) MyGsonUtil.getObjectList(response,NewsContent.class);
                                          firstpagerdatas.get(finalI).setImageHref(list2.get(0).getShare_url());       //获取到RecycleView点击后要跳转的页面URL
                                      }
                                  });
                              }
                              myAdapter.addData(firstpagerdatas);
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
        myAdapter = new RecycleAdapt(this,MainActivity.this,arrylist);

    }

}

