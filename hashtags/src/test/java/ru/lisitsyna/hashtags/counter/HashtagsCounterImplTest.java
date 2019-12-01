package ru.lisitsyna.hashtags.counter;

import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.lisitsyna.hashtags.client.PostInfo;
import ru.lisitsyna.hashtags.client.VkClient;
import org.junit.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static ru.lisitsyna.hashtags.counter.HashtagsCounter.MAX_NUM_OF_HOURS;

public class HashtagsCounterImplTest {
    @Mock
    private VkClient client;

    private HashtagsCounterImpl counter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        counter = new HashtagsCounterImpl(client);
    }

    @Test
    public void invalidNumberOfHours() {
        String hashtag = "#a";
        int hours = 25;
        try {
            counter.count(hashtag, hours);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Expected number of hours between 1 and " + MAX_NUM_OF_HOURS, e.getMessage());
            return;
        }
        fail();
    }

    @Test
    public void invalidHashtag() {
        String hashtag = "a";
        int hours = 10;
        try {
            counter.count(hashtag, hours);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Expected hashtag started with '#'", e.getMessage());
            return;
        }
        fail();
    }

    @Test
    public void oneHourTest() {
        String hashtag = "#hashtag";
        int hours = 1;
        List<PostInfo> posts = Arrays.asList(new PostInfo(Instant.now().minusSeconds(3600 * hours)));
        when(client.getPosts(eq(hashtag), anyLong())).thenReturn(posts);
        int[] statistics = counter.count(hashtag, hours);
        int[] expected = {1};
        Assert.assertArrayEquals(expected, statistics);
    }

    @Test
    public void twentyFourHourTest() {
        String hashtag = "#hashtag";
        int hours = 24;
        List<PostInfo> posts = Arrays.asList(new PostInfo(Instant.now()),
                new PostInfo(Instant.now().minusSeconds(3600)),
                new PostInfo(Instant.now().minusSeconds(3600 * hours)),
                new PostInfo(Instant.now().minusSeconds(3600 * hours / 2)));
        when(client.getPosts(eq(hashtag), anyLong())).thenReturn(posts);
        int[] statistics = counter.count(hashtag, hours);
        int[] expected = {1};
        Assert.assertEquals(1, statistics[0]);
        Assert.assertEquals(1, statistics[23]);
        Assert.assertEquals(1, statistics[hours / 2 - 1]);
        Assert.assertEquals(1, statistics[22]);
    }

    @Test
    public void twelveHourTest() {
        String hashtag = "#hashtag";
        int hours = 12;
        List<PostInfo> posts = Arrays.asList(new PostInfo(Instant.now().minusSeconds(3600 * hours)));
        when(client.getPosts(eq(hashtag), anyLong())).thenReturn(posts);
        int[] statistics = counter.count(hashtag, hours);
        Assert.assertEquals(1, statistics[0]);
    }
}
