package com.example.lusen.bihuplus.Adapt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lusen.bihuplus.Data.First_Pager_Data;
import com.example.lusen.bihuplus.HttpUtils.ImageUtils;
import com.example.lusen.bihuplus.R;
import com.example.lusen.bihuplus.base.WebViewActivity;

import java.util.ArrayList;

/**
 * Created by lusen on 2017/2/23.
 */

public class Recylcle_theme_Adapt extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_TEXT = 2;
    private View mHeaderView;
    private ArrayList<First_Pager_Data> news;
    private Context context;
    private int number;
    private String [] ImageUrl_Img;
    private String [] title_Img;
    private Activity activity;

    public Recylcle_theme_Adapt(Activity activity,Context context,ArrayList<First_Pager_Data> first_news){
        this.news = first_news;
        this.context = context;
        this.activity = activity;
    }

    public void setNews(ArrayList<First_Pager_Data> first_news){
        this.news = first_news;
        Log.d("RecycleViewAdapt",this.news.get(0).getTitle());
    }
    public void setImageUrl_Img(String [] imageUrl_img){this.ImageUrl_Img = imageUrl_img;}     //设置图片加载路径
    public void setTitle_Img(String [] title_img){this.title_Img = title_img;}     //设置标题
    public void setNumber(int number){this.number = number;}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(activity);
        RecyclerView.ViewHolder holder = null;
        if(TYPE_NORMAL == viewType){
            View v = mInflater.inflate(R.layout.item,parent,false);
            holder = new Recylcle_theme_Adapt.MyHolder(v);
        }else if (TYPE_HEADER == viewType) {
            View v = mInflater.inflate(R.layout.activity_theme_img, parent, false);
            holder = new Recylcle_theme_Adapt.ImageViewHolder(v);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof Recylcle_theme_Adapt.MyHolder){
            //设置标题
            ((Recylcle_theme_Adapt.MyHolder) holder).tv.setText(news.get(position).getTitle());
//            ((Recylcle_theme_Adapt.MyHolder) holder).tv.setText("aaaa");
            //设置新闻小图片
            ImageUtils.get(news.get(position).getImagId(), new ImageUtils.Callback_img() {
                @Override
                public void onResponse(Bitmap response) {
                    ((Recylcle_theme_Adapt.MyHolder) holder).image.setImageBitmap(response);
                }
            });
            //设置点击事件
            ((Recylcle_theme_Adapt.MyHolder) holder).layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, WebViewActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            .putExtra(WebViewActivity.WEB_URL, news.get(position).getImageHref()));
                }
            });
        }else if (holder instanceof Recylcle_theme_Adapt.ImageViewHolder){
            //加载头部图片
            ImageUtils.get(ImageUrl_Img[number], new ImageUtils.Callback_img() {
                @Override
                public void onResponse(Bitmap response) {

                    ((Recylcle_theme_Adapt.ImageViewHolder) holder).imageheader.setImageBitmap(response);
                }
            });
            ((ImageViewHolder) holder).tvtheme.setText(title_Img[number]);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) return TYPE_HEADER;

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
    class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView imageheader;
        TextView tvtheme;
        public ImageViewHolder(View itemView) {
            super(itemView);
            tvtheme = (TextView) itemView.findViewById(R.id.theme_tv);
            imageheader = (ImageView) itemView.findViewById(R.id.theme_img);
        }
    }


}