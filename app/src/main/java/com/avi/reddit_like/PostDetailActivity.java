package com.avi.reddit_like;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class PostDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        WebView webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        String url = getIntent().getStringExtra("postUrl");
        if (url != null) {
            webView.loadUrl(url);
        }

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> onBackPressed());
    }
}
