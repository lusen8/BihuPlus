package com.example.lusen.bihuplus.httputils;

import android.graphics.Bitmap;
import android.os.Handler;

/**
 * Created by lusen on 2017/2/9.
 */

public class ImageUtils {
    public interface Callback_img{
        void onResponse(Bitmap response);
    }
    public static void get(final String url, final ImageUtils.Callback_img callback){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap response = ImageNet.get(url);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(response);
                    }
                });
            }
        }).start();
    }
}

