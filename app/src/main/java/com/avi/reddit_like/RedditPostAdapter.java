package com.avi.reddit_like;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RedditPostAdapter extends RecyclerView.Adapter<RedditPostAdapter.PostViewHolder> {

    private Context context;
    private List<RedditPost> postList;

    public RedditPostAdapter(Context context, List<RedditPost> postList) {
        this.context = context;
        this.postList = postList;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reddit_post_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        RedditPost post = postList.get(position);
        holder.title.setText(post.getTitle());
        holder.author.setText("Posted by u/" + post.getAuthor());

        if (post.getImageUrl() != null && !post.getImageUrl().isEmpty()) {
            Glide.with(context)
                    .load(post.getImageUrl())
                    .into(holder.image);
        } else {
            holder.image.setImageResource(R.drawable.placeholder); // fallback image
        }

        // ✅ Handle click to open WebViewActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra("url", post.getPostUrl());  // KEY: "url", NOT "postUrl"
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView title, author;
        ImageView image;

        public PostViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.reddit_post_title);
            author = itemView.findViewById(R.id.reddit_post_author);
            image = itemView.findViewById(R.id.reddit_post_image);
        }
    }
}
