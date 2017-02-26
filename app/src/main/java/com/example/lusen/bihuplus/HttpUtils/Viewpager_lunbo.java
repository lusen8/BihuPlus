package com.example.lusen.bihuplus.HttpUtils;

import android.app.Activity;
import android.view.Gravity;

import com.example.lusen.bihuplus.R;
import com.example.lusen.bihuplus.base.WebViewActivity;
import com.example.lusen.bihuplus.widget.ADViewPager;

import java.util.ArrayList;

/**
 * Created by lusen on 2017/2/13.
 */

public class Viewpager_lunbo {

    private String[] imageUrls;
    private String[] imageHref;
//    @BindView(R.id.viewPager_main_ad)
    private ADViewPager viewPager;
    private Activity activity;
    private ArrayList<String> title;

    public Viewpager_lunbo(Activity activity, ADViewPager viewPager){
        this.viewPager = viewPager;
        this.activity = activity;
    }

    public void setImageUrls(String[] imageUrls){
        this.imageUrls = imageUrls;
    }
    public void setImageHref(String[] imageHref){
        this.imageHref = imageHref;
    }
    public void setTitle(ArrayList<String> title){
        this.title = title;
    }
    public String[] getImageUrls(){return imageUrls;}
    public String[] getImageHref(){return imageHref;}
    public ArrayList<String> getTitle(){return title;}
    public void setSubView() {

//        viewPager= (ADViewPager) this.activity.findViewById(R.id.viewPager_main_ad);
        viewPager.setIndicatorDrawableChecked(R.drawable.shape_dot_selected) //当前指示点
                .setIndicatorDrawableUnchecked(R.drawable.shape_dot_unselected) //非当前指示点
                .setAutoPlay(true) //是否开启自动轮播
                .setDisplayIndicator(true) //是否显示指示器
                .setIndicatorGravity(Gravity.CENTER) //指示器位置
                .setImageUri(imageUrls)  //图片路径
                .setBannerHref(imageHref)  //点击图片跳转的路径
                .setTitle(title)        //设置标题
                .setTargetActivity(WebViewActivity.class)  //点击图片跳转的webView页面
                .startPlay();

    }
}
