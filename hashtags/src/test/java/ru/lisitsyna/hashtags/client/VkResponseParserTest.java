package ru.lisitsyna.hashtags.client;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class VkResponseParserTest {
    @Test
    public void baseParseResponseTest() {
        VkResponseParser parser = new VkResponseParser();
        String response =
                "{\n" +
                "  \"response\": {\n" +
                "    \"items\": [\n" +
                "      {\n" +
                "        \"date\": " + 50000010 + "\n" +
                "      }\n" +
                "      ,{\n" +
                "        \"date\": " + 50000012 + "\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        List<PostInfo> list = parser.parseResponse(response);
        Assert.assertEquals(2, list.size());
    }
}
