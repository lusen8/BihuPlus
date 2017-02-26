package com.example.lusen.bihuplus.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.lusen.bihuplus.Adapt.Recylcle_theme_Adapt;
import com.example.lusen.bihuplus.Data.First_Pager_Data;
import com.example.lusen.bihuplus.Data.News_content;
import com.example.lusen.bihuplus.Data.Themes_Data;
import com.example.lusen.bihuplus.Data.Themes_content;
import com.example.lusen.bihuplus.HttpUtils.MyGsonUtil;
import com.example.lusen.bihuplus.HttpUtils.MyHttpURL;
import com.example.lusen.bihuplus.R;
import com.example.lusen.bihuplus.widget.RefreshRecyclerView;

import java.util.ArrayList;

import static com.example.lusen.bihuplus.R.id.recycle;

/**
 * Created by lusen on 2017/2/23.
 */

public class ThemeActivity extends AppCompatActivity {

    //第几个页面
    private int number;
    //顶部图片加载路径
    private String [] imageUrls_theme = new String[12];
    //顶部标题
    private String [] titlt_theme = new String[12];
    //适配器
    private Recylcle_theme_Adapt myAdapter;
    //下拉刷新控件
    private SwipeRefreshLayout swipeRefreshLayout;
    //recycleView实例
    private RefreshRecyclerView recyclerView;
    //rencycle中的内容（图片和标题）
    private ArrayList<First_Pager_Data> arrylist = new ArrayList<First_Pager_Data>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Intent intent = getIntent();
        intent.getIntExtra("number",number);
        number = (int) intent.getSerializableExtra("number");
        Log.d("Themeeeee",number+"nnnnn ");
        initView();
        hot_news();
        initListener();
    }


    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.demo_swiperefreshlayout);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_blue_light, android.R.color.holo_green_light);
        recyclerView = (RefreshRecyclerView) findViewById(recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLoadMoreEnable(true);//允许加载更多
        recyclerView.setFooterResource(R.layout.foot);//设置脚布局
        myAdapter = new Recylcle_theme_Adapt(this,ThemeActivity.this,arrylist);

    }
    private Handler handler = new Handler();
    private void initListener() {       //加载过往消息

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

    public void hot_news(){

        //解析主题列表header
        MyHttpURL.get("http://news-at.zhihu.com/api/4/themes", new MyHttpURL.Callback() {
            @Override
            public void onResponse(String response) {
                response = "["+response+"]";
                final ArrayList <Themes_Data> list= (ArrayList<Themes_Data>) MyGsonUtil.getObjectList(response,Themes_Data.class);
                for(int j = 0;j < list.get(0).getOthers().size();j++){    //准备好数据

                    imageUrls_theme[j] = list.get(0).getOthers().get(j).getThumbnail(); //所有的header图片路径
                    titlt_theme[j] = list.get(0).getOthers().get(j).getDescription();   //所有的header标题

                }
                //获取到专题列表的内容
                for(int i = 0;i < list.get(0).getOthers().size();i++){
                    final int finalI = i;
                    Log.d("Theeeee",list.get(0).getOthers().get(0).getId()+" ");
                    MyHttpURL.get("http://news-at.zhihu.com/api/4/theme/" + list.get(0).getOthers().get(number).getId(), new MyHttpURL.Callback() {
                        @Override
                        public void onResponse(String response) {
                            response = "["+response+"]";
//                            Log.d("ThemeActivity",response);
                            ArrayList<Themes_content> list_2 = (ArrayList<Themes_content>) MyGsonUtil.getObjectList(response,Themes_content.class);
                            Log.d("ThemeActivity",list_2.get(0).getStories().get(2).getImages()[0]+" ");
                            for (int j = 0; j < list_2.get(0).getStories().size();j++){
//                                Log.d("ThemeActivity",list_2.get(0).getStories().get(1).getTitle());
                                First_Pager_Data data1 = new First_Pager_Data(list_2.get(0).getStories().get(j).getTitle());
//                                if (j != 0 && j!= 1){
//                                    Log.d("ThemeActivityyyyyyyy",list_2.get(0).getStories().get(3).toString()+" ");
                                    data1.setImage_theme(list_2.get(0).getStories().get(number).getImages()[0]);
//                                data1.setImage_theme("http://pic1.zhimg.com/56d1d1202077c7b5b0e48e3b7d3ebb60_t.jpg");
//                                }
                                arrylist.add(data1);     //RecycleView在首页显示的内容
                            }

                            MyHttpURL.get("http://news-at.zhihu.com/api/4/news/"+list_2.get(0).getStories().get(finalI).getId(), new MyHttpURL.Callback() {
                                @Override
                                public void onResponse(String response) {
                                    response = "["+response+"]";
                                    ArrayList<News_content> list_3 = (ArrayList<News_content>) MyGsonUtil.getObjectList(response,News_content.class);
                                    //设置跳转路径
                                    arrylist.get(finalI).setImageHref(list_3.get(0).getShare_url());

                                    myAdapter.setNews(arrylist);
                                    myAdapter.setNumber(number);
                                    myAdapter.setImageUrl_Img(imageUrls_theme);
                                    myAdapter.setTitle_Img(titlt_theme);
                                    recyclerView.setAdapter(myAdapter);  //设置好适配器
                                }
                            });
                        }
                    });
                }

//                //获取到viewpager中的消息内容 ，即点击跳转的路径
//                for(int i = 0;i < list.get(0).getTop_stories().size();i++){
//                    final int finalI = i;
//                    MyHttpURL.get("http://news-at.zhihu.com/api/4/news/" + list.get(0).getTop_stories().get(i).getId(), new MyHttpURL.Callback() {
//                        @Override
//                        public void onResponse(String response) {
//                            response = "["+response+"]";
//                            ArrayList<News_content> list_1 = (ArrayList<News_content>) MyGsonUtil.getObjectList(response,News_content.class);
//                        }
//                    });
//                }

     //获取到viewPager要显示图片URL
     //获取到viewPage要显示的标题



            }
        });
    }

}
