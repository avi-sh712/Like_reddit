package com.avi.reddit_like;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RedditPostAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar custom views
        ImageView backButton = findViewById(R.id.backButton);
        EditText searchBar = findViewById(R.id.searchBar);
        ImageView logoView = findViewById(R.id.logoView); // in case you want to update logo dynamically

        // Back button listener
        backButton.setOnClickListener(v -> onBackPressed());

        // RecyclerView and SwipeRefresh
        recyclerView = findViewById(R.id.redditRecyclerView);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch posts initially
        loadRedditPosts();

        // Pull to refresh logic
        swipeRefresh.setOnRefreshListener(this::loadRedditPosts);
    }

    private void loadRedditPosts() {
        swipeRefresh.setRefreshing(true);

        RedditApiFetcher.fetchTopPosts("technology", new RedditApiFetcher.RedditPostCallback() {
            @Override
            public void onPostsFetched(List<RedditPost> posts) {
                adapter = new RedditPostAdapter(MainActivity.this, posts);
                recyclerView.setAdapter(adapter);
                swipeRefresh.setRefreshing(false);
            }
        });
    }
}
