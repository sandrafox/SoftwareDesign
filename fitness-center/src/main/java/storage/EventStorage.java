package storage;

import exceptions.CollectionException;
import manage.ReportService;

import java.util.*;
import java.util.function.Function;

public class EventStorage {
    private Map<Integer, List<Event>> events = new HashMap<>();
    private List<ReportService> subscribers = new ArrayList<>();

    public boolean isExist(int id) {
        return events.containsKey(id);
    }

    public void addEvent(int id, Event event) {
        events.computeIfAbsent(id, n -> new ArrayList<Event>());
        events.get(id).add(event);
        subscribers.forEach(s -> {
            try {
                s.handle(new AbstractMap.SimpleEntry<>(id, event));
            } catch (CollectionException e) {
                e.printStackTrace();
            }
        });
    }

    public List<Event> getEvents(int id) {
        return events.get(id);
    }

    public void subscribe(ReportService subscriber) {
        events.forEach((key, value) -> value.forEach(v -> {
            try {
                subscriber.handle(new AbstractMap.SimpleEntry(key, v));
            } catch (CollectionException e) {
                e.printStackTrace();
            }
        }));
        subscribers.add(subscriber);
    }
}
