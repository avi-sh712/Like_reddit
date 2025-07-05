//package com.avi.reddit_like;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.util.Log;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class LoginActivity extends AppCompatActivity {
//
//    private static final String CLIENT_ID = "I-IMvoZFa5SwG8_WzuK0Ew\n"; // Replace this
//    private static final String REDIRECT_URI = "com.avi.reddit_like://oauth2redirect";
//    private static final String AUTH_URL = "https://www.reddit.com/api/v1/authorize.compact?client_id="
//            + CLIENT_ID
//            + "&response_type=code"
//            + "&state=random123"
//            + "&redirect_uri=" + REDIRECT_URI
//            + "&duration=permanent"
//            + "&scope=identity";
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // Open the Reddit login page
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AUTH_URL));
//        startActivity(intent);
//        finish(); // Optional
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        Uri uri = intent.getData();
//
//        if (uri != null && uri.toString().startsWith(REDIRECT_URI)) {
//            String code = uri.getQueryParameter("code");
//
//            if (code != null) {
//                Log.d("LoginActivity", "Authorization Code: " + code);
//
//                // TODO: Exchange code for token here, for now just launch main
//                Intent mainIntent = new Intent(this, MainActivity.class);
//                startActivity(mainIntent);
//                finish();
//            } else {
//                Log.e("LoginActivity", "Login failed or canceled");
//            }
//        }
//    }
//}
