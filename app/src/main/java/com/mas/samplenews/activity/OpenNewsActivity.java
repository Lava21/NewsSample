package com.mas.samplenews.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.mas.samplenews.R;

public class OpenNewsActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private WebView mWebView;

    String url = "https://www.google.com";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_news);

        setupToolbar();

        mProgressBar = findViewById(R.id.pbOpenNews);
        mProgressBar.setMax(100);
        url = getIntent().getStringExtra("url");

        mWebView = findViewById(R.id.wvOpenNews);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
        mProgressBar.setProgress(0);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String newUrl) {
                view.loadUrl(newUrl);
                mProgressBar.setProgress(0);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String sUrl, Bitmap favicon) {
                url = sUrl;
                invalidateOptionsMenu();
            }

            @Override
            public void onPageFinished(WebView view, String pUrl) {
                mProgressBar.setVisibility(View.GONE);
                invalidateOptionsMenu();
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_open_news, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.share){
            shareUrl(url);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()){
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private void setupToolbar(){
        Toolbar toolbar = findViewById(R.id.tbOpenNews);
        setTitle("Berita");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebView.canGoBack()){
                    mWebView.goBack();
                } else {
                    finish();
                }
            }
        });

    }

    private void shareUrl(String url){
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        share.putExtra(Intent.EXTRA_TEXT, url);
        startActivity(Intent.createChooser(share, "Bagikan ke: "));
    }
}
