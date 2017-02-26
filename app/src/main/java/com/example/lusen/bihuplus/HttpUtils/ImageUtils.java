package com.example.lusen.bihuplus.HttpUtils;

import android.accounts.NetworkErrorException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

class ImageNet{
    public static Bitmap get(final String url) {
        HttpURLConnection conn = null;
        Bitmap bmp = null;
        try {
            // 利用string url构建URL对象
            URL mURL = new URL(url);
            conn = (HttpURLConnection) mURL.openConnection();
//            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(10000);

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                conn.connect();
                InputStream is = conn.getInputStream();
                bmp = BitmapFactory.decodeStream(is);
                is.close();

            } else {
                throw new NetworkErrorException("response status is "+responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (conn != null) {
                conn.disconnect();
            }
        }
        return bmp;
    }

}
