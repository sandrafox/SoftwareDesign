package ru.lisitsyna.softwaredesign.actorsearch;

import org.json.JSONObject;

import java.util.List;

public class Response {
    Engine engine;
    JSONObject links;

    public Response(Engine engine, JSONObject links) {
        this.engine = engine;
        this.links = links;
    }

    public Engine getEngine() {
        return engine;
    }

    public JSONObject getLinks() {
        return links;
    }
}
