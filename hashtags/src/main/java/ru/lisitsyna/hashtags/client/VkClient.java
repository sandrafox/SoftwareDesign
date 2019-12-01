package ru.lisitsyna.hashtags.client;

import ru.lisitsyna.hashtags.http.UrlReader;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class VkClient implements ApiClient {
    private UrlReader reader;
    private VkResponseParser parser;

    private final String VK_URL = "https://api.vk.com/method/";
    private final String METHOD = "newsfeed.search";
    private final String VERSION = "5.103";
    public static final String ACC_TOKEN = "370df5dcad68337bc0905d9d47a545d8d76205e18c4f461729b03ef676750f86916a9506f05e5eb5a2399";
    private final int MAX_COUNT = 200;

    public VkClient(UrlReader urlReader, VkResponseParser vkParser) {
        reader = urlReader;
        parser = vkParser;
    }

    public List<PostInfo> getPosts(String query, long unixTime) {
        String response = reader.readAsText(createURL(query, unixTime));
        return parser.parseResponse(response);
    }

    private String createURL(String query, long unixTime) {
        return VK_URL + METHOD + "?q=" + URLEncoder.encode(query, StandardCharsets.UTF_8)
                + "&count=" + MAX_COUNT + "&v=" + VERSION + "&start_time=" + unixTime
                + "&access_token=" + ACC_TOKEN;
    }
}
