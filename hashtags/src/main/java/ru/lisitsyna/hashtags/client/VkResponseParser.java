package ru.lisitsyna.hashtags.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class VkResponseParser {
    public List<PostInfo> parseResponse(String response) {

        JsonObject entries = (JsonObject) new JsonParser().parse(response);
        JsonObject responseJson = entries.getAsJsonObject("response");
        JsonArray items = responseJson.getAsJsonArray("items");
        List<PostInfo> infos = new ArrayList<>(entries.size());
        for (JsonElement e : items) {
            JsonObject d = (JsonObject) e;
            infos.add(new PostInfo(Instant.ofEpochSecond(d.get("date").getAsLong())));
        }
        return infos;
    }
}