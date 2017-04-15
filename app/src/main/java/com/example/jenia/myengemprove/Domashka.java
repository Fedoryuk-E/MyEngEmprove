package com.example.jenia.myengemprove;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Domashka extends AppCompatActivity {

    private WebView domaskaWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domashka);

        domaskaWebView = (WebView) findViewById(R.id.webViewDomashka);
        domaskaWebView.getSettings().setJavaScriptEnabled(true);
        domaskaWebView.loadUrl("https://my.greenforest.com.ua/");
        domaskaWebView.setWebViewClient(new MyWebViewClient());
    }
    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
    }
}
