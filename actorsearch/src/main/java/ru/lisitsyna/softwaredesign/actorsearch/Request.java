package ru.lisitsyna.softwaredesign.actorsearch;

public class Request {
    private Engine engine;
    private String request;

    public Request(Engine engine, String request) {
        this.engine = engine;
        this.request = request;
    }

    public Engine getEngine() {
        return engine;
    }

    public String getRequest() {
        return request;
    }
}
