package ru.lisitsyna.hashtags.counter;

public interface HashtagsCounter {
    int MAX_NUM_OF_HOURS = 24;

    int[] count(String hashtag, int hours);
}
