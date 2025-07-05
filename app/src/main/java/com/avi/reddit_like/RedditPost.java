package com.avi.reddit_like;

public class RedditPost {
    String title;
    String imageUrl;
    String postUrl;
    String author;  // ✅ Add this line

    // ✅ Update constructor to include author
    public RedditPost(String title, String imageUrl, String postUrl, String author) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.postUrl = postUrl;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public String getAuthor() {
        return author;
    }
}
