package com.emailwc.ytkeyplayerexact;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setContentView(webView);

        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("file:///")) {
                    try {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setDataAndType(Uri.parse(url), "video/*");
                        startActivity(i);
                        return true;
                    } catch (Exception e) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("file:///storage/emulated/0/")));
                        return true;
                    }
                }
                if (url.contains("youtube.com/watch")) {
                    String videoId = "";
                    try {
                        Uri uri = Uri.parse(url);
                        videoId = uri.getQueryParameter("v");
                    } catch (Exception e) {}
                    if (videoId != null && !videoId.isEmpty()) {
                        try {
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse("vnd.youtube:" + videoId));
                            startActivity(i);
                            return true;
                        } catch (Exception ex) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + videoId)));
                            return true;
                        }
                    }
                }
                view.loadUrl(url);
                return true;
            }
        });

        webView.loadUrl("https://m.youtube.com/");
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
