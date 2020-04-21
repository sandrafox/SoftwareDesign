package ru.lisitsyna.softwaredesign.actorsearch;

import akka.actor.AbstractActorWithTimers;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.ReceiveTimeout;

import java.time.Duration;
import java.util.*;

public class AgregateActor extends AbstractActorWithTimers {
    static final long DURATION = 20;
    static final Duration TIMEOUT = Duration.ofSeconds(DURATION);
    static final Duration PRETIMEOUT = Duration.ofSeconds((long) Math.ceil(0.95 * DURATION));
    private Map<Engine, Response> responses = new HashMap<>();
    private ActorRef parent;
    private List<ActorRef> searchers = new ArrayList<>();
    static final Set<Engine> engines = Set.of(new Engine[]{Engine.BING, Engine.GOOGLE, Engine.YANDEX});
    private String request;

    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, this::getRequest)
                .match(Response.class, this::getResponse)
                .match(Timeout.class, this::getPreTimeout)
                .match(ReceiveTimeout.class, this::getTotallyTimeout)
                .build();
    }

    private void getRequest(String request) {
        parent = getSender();
        this.request = request;
        for (Engine e : engines) {
            ActorRef a = getContext().actorOf(Props.create(SearcherActor.class), e.toString());
            a.tell(new Request(e, request), getSelf());
            searchers.add(a);
        }

        getTimers().startSingleTimer("PRETIMEOUT", new Timeout(), PRETIMEOUT);
        getContext().setReceiveTimeout(TIMEOUT);
    }

    private void getResponse(Response response) {
        responses.put(response.getEngine(), response);
        searchers.remove(getSender());
        if (responses.size() == 3) stopSearch();
    }

    private void getPreTimeout(Timeout timeout) {
        for (Engine e : engines) {
            if (!responses.containsKey(e)) {
                ActorRef a = getContext().actorOf(Props.create(SearcherActor.class), e.toString() + "1");
                a.tell(new Request(e, request), getSelf());
                searchers.add(a);
            }
        }
    }

    private void getTotallyTimeout(ReceiveTimeout timeout) {
        stopSearch();
    }

    private void stopSearch() {
        for (ActorRef searcher : searchers) {
            getContext().stop(searcher);
        }
        Answer.responses.putAll(responses);
        getContext().stop(getSelf());
    }

    protected static class Timeout {}


}
