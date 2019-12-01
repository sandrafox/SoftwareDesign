package ru.lisitsyna.hashtags.counter;

import ru.lisitsyna.hashtags.client.ApiClient;
import ru.lisitsyna.hashtags.client.PostInfo;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class HashtagsCounterImpl implements HashtagsCounter {
    private ApiClient apiClient;

    public HashtagsCounterImpl(ApiClient client) {
        apiClient = client;
    }

    public int[] count(String hashtag, int hours) {
        if (hours < 1 || hours > MAX_NUM_OF_HOURS) {
            throw new IllegalArgumentException("Expected number of hours between 1 and " + MAX_NUM_OF_HOURS);
        }
        if (hashtag.charAt(0) != '#') {
            throw new IllegalArgumentException("Expected hashtag started with '#'");
        }
        Instant now = Instant.now();
        Instant startTime = now.minusSeconds(3600 * hours);
        long time = startTime.getEpochSecond();
        List<PostInfo> posts = apiClient.getPosts(hashtag, time);
        int[] statistics = new int[hours];
        posts.stream().map(PostInfo::getTime).map(publishedTime -> (int) ChronoUnit.HOURS.between(startTime, publishedTime)).
                forEach(hoursBetween -> statistics[hoursBetween]++);
        return statistics;
    }
}
