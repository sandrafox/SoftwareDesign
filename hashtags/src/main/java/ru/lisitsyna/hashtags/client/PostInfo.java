package ru.lisitsyna.hashtags.client;

import java.time.Instant;

public class PostInfo {
    private Instant time;

    public PostInfo(Instant time) {
        this.time = time;
    }

    public Instant getTime() {
        return time;
    }
}
