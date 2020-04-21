package ru.lisitsyna.softwaredesign.actorsearch;

import org.json.JSONObject;

import static java.lang.Thread.sleep;

public class StubServer {
    public static long TIMEOUT = 0;

    public static JSONObject search(Request request) {
        try {
            sleep(TIMEOUT);
            return new JSONObject("{ \"result\": for " + request.getRequest() + " in " + request.getEngine() + "}");
        } catch (InterruptedException e) {
            return new JSONObject("{\"result\": Can't get result}");
        }
    }
}
