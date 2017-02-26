package com.example.lusen.bihuplus.base;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.lusen.bihuplus.R;
import com.example.lusen.bihuplus.behavior.AnimatorBehavior;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lusen on 2017/2/13.
 */

public class WebViewActivity extends AppCompatActivity {
    public static final String WEB_URL = "url";

    @BindView(R.id.toolbar_web)
    Toolbar toolbar;
    @BindView(R.id.fab_web)
    FloatingActionButton fab;
    @BindView(R.id.web_base)
    WebView webView;
    @BindView(R.id.pbBar_web)
    ProgressBar pbBar;
    @BindView(R.id.nested_scrollView_web)
    NestedScrollView nestedScrollView;

    private String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        ButterKnife.bind(this);

        setSubView();
        initEvent();
    }

    /**
     * 设置视图
     */
    private void setSubView() {
        url = getIntent().getStringExtra(WEB_URL);
        //在此初始化fab按钮，避免第一次进入页面时上滑直接弹出按钮
        AnimatorBehavior.scaleHide(fab, null);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebSettings wSet = webView.getSettings();
        //设置是否支持JS交互，不设置页面显示不出来
        wSet.setJavaScriptEnabled(true);
        //设置适应屏幕
        wSet.setUseWideViewPort(true);
        wSet.setLoadWithOverviewMode(true);
        //支持缩放
        wSet.setSupportZoom(true);
        //设置数据格式，这样能在一定程度上节省资源
        wSet.setDefaultTextEncodingName("UTF-8");
        //隐藏原生缩放控件
        wSet.setDisplayZoomControls(false);
        wSet.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.loadUrl(url);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                toolbar.setTitle(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pbBar.setProgress(newProgress);
                if (100 == newProgress) {
                    pbBar.setVisibility(View.GONE);
                }
            }
        });

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pbBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pbBar.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        });
    }

    private void initEvent() {
        //回到页面顶部
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nestedScrollView.smoothScrollTo(0, 0);
                hideFAB();
            }
        });
    }

    /**
     * 当点击FAB按钮滑到顶部时，FAB按钮隐藏
     */
    private void hideFAB() {
        fab.postDelayed(new Runnable() {
            @Override
            public void run() {
                AnimatorBehavior.scaleHide(fab, new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        fab.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(View view) {
                    }
                });
            }
        }, 300);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        webView.getSettings().setJavaScriptEnabled(false);
    }

    private void webGoBack() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
//            android.os.Process.killProcess(android.os.Process.myPid());
//            System.exit(0);
        }
    }

    @Override
    public void onBackPressed() {
        webGoBack();
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.removeAllViews();
            webView.destroy();
        }
        super.onDestroy();
    }
}