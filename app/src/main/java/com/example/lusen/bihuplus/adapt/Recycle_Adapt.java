package com.example.lusen.bihuplus.adapt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lusen.bihuplus.data.First_Pager_Data;
import com.example.lusen.bihuplus.httputils.ImageUtils;
import com.example.lusen.bihuplus.R;
import com.example.lusen.bihuplus.base.WebViewActivity;
import com.example.lusen.bihuplus.widget.ADViewPager;

import java.util.ArrayList;

/**
 * Created by lusen on 2017/1/19.
 */

public class Recycle_Adapt extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_TEXT = 2;
    private View mHeaderView;
    private ArrayList<First_Pager_Data> news;
    private Context context;
    private String [] ImageHref_Pager;
    private String [] ImageUrl_Pager;
    private ArrayList<String> title_Pager;
    private Activity activity;

    public Recycle_Adapt(Activity activity,Context context,ArrayList<First_Pager_Data> first_news){
        this.news = first_news;
        this.context = context;
        this.activity = activity;
    }

    public void setImageUrl_Pager(String[] ImageUrl_Pager){
        this.ImageUrl_Pager = ImageUrl_Pager;
    }
    public void setImageHref_Pager(String[] ImageHref_Pager){
        this.ImageHref_Pager = ImageHref_Pager;
    }
    public void setTitle_Pager(ArrayList<String> title_Pager){
        this.title_Pager = title_Pager;
    }
    public void setNews(ArrayList<First_Pager_Data> first_news){
        this.news = first_news;
        Log.d("RecycleViewAdapt",this.news.get(0).getTitle());
    }
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }
    public View getHeaderView() {
        return mHeaderView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(activity);
        RecyclerView.ViewHolder holder = null;
        if(TYPE_NORMAL == viewType){
            View v = mInflater.inflate(R.layout.item,parent,false);
            holder = new MyHolder(v);
        }else if (TYPE_HEADER == viewType){
            View v = mInflater.inflate(R.layout.item_pager,parent,false);
            holder = new PagerViewHolder(v);
        }else if (TYPE_TEXT == viewType){
            View v = mInflater.inflate(R.layout.item_text,parent,false);
            holder = new TextViewHolder(v);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof MyHolder){
            //设置标题
            ((MyHolder) holder).tv.setText(news.get(position).getTitle());
            //设置新闻小图片
            ImageUtils.get(news.get(position).getImagId(), new ImageUtils.Callback_img() {
                @Override
                public void onResponse(Bitmap response) {
                    ((MyHolder) holder).image.setImageBitmap(response);
                }
            });
            //设置点击事件
            ((MyHolder) holder).layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, WebViewActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            .putExtra(WebViewActivity.WEB_URL, news.get(position).getImageHref()));
                }
            });
        }else if (holder instanceof PagerViewHolder){
            ((PagerViewHolder) holder).viewPager.setIndicatorDrawableChecked(R.drawable.shape_dot_selected) //当前指示点
                                            .setIndicatorDrawableUnchecked(R.drawable.shape_dot_unselected) //非当前指示点
                                            .setAutoPlay(true) //是否开启自动轮播
                                            .setDisplayIndicator(true) //是否显示指示器
                                            .setIndicatorGravity(Gravity.CENTER) //指示器位置
                                            .setImageUri(ImageUrl_Pager)  //图片路径
                                            .setBannerHref(ImageHref_Pager)  //点击图片跳转的路径
                                            .setTitle(title_Pager)        //设置标题
                                            .setTargetActivity(WebViewActivity.class)  //点击图片跳转的webView页面
                                            .startPlay();
        }else if (holder instanceof TextViewHolder){
            ((TextViewHolder) holder).title_lit.setText("今日要闻");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) return TYPE_HEADER;
        if (position == 1) return TYPE_TEXT;
        return TYPE_NORMAL;
    }

    public void addData(ArrayList<First_Pager_Data> newss){
        this.news.addAll(newss);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() { //获取数量
        return news.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        private ImageView image;
        private LinearLayout layout;
        public MyHolder(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.biaoti);
            image = (ImageView) itemView.findViewById(R.id.icon_news);
            layout = (LinearLayout) itemView.findViewById(R.id.item_a);

        }
    }
    class PagerViewHolder extends RecyclerView.ViewHolder{
        ADViewPager viewPager;
        public PagerViewHolder(View itemView) {
            super(itemView);
            viewPager = (ADViewPager) itemView.findViewById(R.id.viewPager_main);
        }
    }
    class TextViewHolder extends RecyclerView.ViewHolder{
        private TextView title_lit;
        public TextViewHolder(View itemView) {
            super(itemView);
            title_lit = (TextView) itemView.findViewById(R.id.title_day);
        }
    }

}
