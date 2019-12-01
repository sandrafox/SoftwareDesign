package ru.lisitsyna.hashtags.client;

import java.util.List;

public interface ApiClient {
    List<PostInfo> getPosts(String query, long unixTime);
}
