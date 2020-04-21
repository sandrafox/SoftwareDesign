package ru.lisitsyna.softwaredesign.actorsearch;

import akka.actor.UntypedAbstractActor;

public class SearcherActor extends UntypedAbstractActor {
    private Request request;

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Request) {
            Request request = (Request) message;
            Response response = new Response(request.getEngine(),
                    StubServer.search(request));
            getSender().tell(response, getSelf());
        }
    }

    public Request getRequest() {
        return request;
    }
}
