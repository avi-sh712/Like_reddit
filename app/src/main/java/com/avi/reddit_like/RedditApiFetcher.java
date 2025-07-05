package com.avi.reddit_like;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RedditApiFetcher {

    public interface RedditPostCallback {
        void onPostsFetched(List<RedditPost> posts);
    }

    public static void fetchTopPosts(String subreddit, RedditPostCallback callback) {
        new Thread(() -> {
            try {
                URL url = new URL("https://www.reddit.com/r/" + subreddit + "/top.json?limit=10");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("User-Agent", "RedditLikeApp");
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder responseBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }

                reader.close();

                JSONObject responseJson = new JSONObject(responseBuilder.toString());
                JSONArray posts = responseJson.getJSONObject("data").getJSONArray("children");

                List<RedditPost> postList = new ArrayList<>();

                for (int i = 0; i < posts.length(); i++) {
                    JSONObject postData = posts.getJSONObject(i).getJSONObject("data");

                    String title = postData.getString("title");
                    String imageUrl = "";
                    String author = postData.getString("author");

                    // Try to get image URL if preview is available
                    if (postData.has("preview")) {
                        imageUrl = postData.getJSONObject("preview")
                                .getJSONArray("images")
                                .getJSONObject(0)
                                .getJSONObject("source")
                                .getString("url")
                                .replaceAll("&amp;", "&");
                    }

                    // ✅ Add this line to get the full Reddit post URL
                    String postUrl = "https://www.reddit.com" + postData.getString("permalink");

                    postList.add(new RedditPost(title, imageUrl, postUrl, author));
                }

                // Call the callback on the main thread
                new Handler(Looper.getMainLooper()).post(() -> callback.onPostsFetched(postList));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

}
