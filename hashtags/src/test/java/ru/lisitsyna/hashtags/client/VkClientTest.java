package ru.lisitsyna.hashtags.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.lisitsyna.hashtags.http.UrlReader;

import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class VkClientTest {
    @Mock
    private UrlReader reader;

    private VkClient vkClient;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        vkClient = new VkClient(reader, new VkResponseParser());
    }

    @Test
    public void baseGetPostsTest() {
        Instant startTime = Instant.now().minusSeconds(3600);
        Instant postTime = startTime.plusSeconds(1800);
        String url = "https://api.vk.com/method/newsfeed.search?q=a&count=200&v=5.103&start_time="
                + startTime.getEpochSecond() + "&access_token=" + VkClient.ACC_TOKEN;
        String response =
                "{\n" +
                        "  \"response\": {\n" +
                        "    \"items\": [\n" +
                        "      {\n" +
                        "        \"date\": " + postTime.getEpochSecond() + "\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }\n" +
                        "}";
        when(reader.readAsText(eq(url))).thenReturn(response);
        List<PostInfo> posts = vkClient.getPosts("a", startTime.getEpochSecond());
        Assert.assertEquals(1, posts.size());
        PostInfo post = posts.get(0);
        Assert.assertEquals(postTime.getEpochSecond(), post.getTime().getEpochSecond());
    }
}
