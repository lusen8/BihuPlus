package com.example.lusen.bihuplus.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lusen.bihuplus.Data.WelcomeData;
import com.example.lusen.bihuplus.HttpUtils.ImageUtils;
import com.example.lusen.bihuplus.HttpUtils.MyGsonUtil;
import com.example.lusen.bihuplus.HttpUtils.MyHttpURL;
import com.example.lusen.bihuplus.R;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.lusen.bihuplus.R.layout.activity_welcome;


/**
 * Created by lusen on 2017/2/6.
 */

public class WelcomeView extends AppCompatActivity{
    private static String localVersionName;
    private String imageID;
    private ImageView view;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_welcome);
        //欢迎页面定时跳转
        final Intent it = new Intent(this, MainActivity.class); //转向MainActivity
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(it); //执行
            }
        };
        timer.schedule(task, 1000 * 3); //3秒后

        //欢迎界面的imageView，和textView
        view = (ImageView) findViewById(R.id.welcome);
        textView = (TextView) findViewById(R.id.author);
        //从知乎日报接口获取图片资源和作者
        MyHttpURL.get("https://news-at.zhihu.com/api/7/prefetch-launch-images/1080*1668", new MyHttpURL.Callback() {
            @Override
            public void onResponse(String response) {
                WelcomeData welcome= MyGsonUtil.parseJsonWithGson(response,WelcomeData.class);
                textView.setText(welcome.getCreatives().get(0).getText()+" ");
                //加载欢迎界面图片，由于知乎欢迎接口已炸，暂时用一下代替
                ImageUtils.get(welcome.getCreatives().get(0).getUrl(), new ImageUtils.Callback_img(){

                    @Override
                    public void onResponse(Bitmap response) {
                        view.setImageBitmap(response);
                    }
                });
//                textView.setText("© Fido Dido");    //知乎界面已炸，临时信息
            }
        });

        
    }
}
