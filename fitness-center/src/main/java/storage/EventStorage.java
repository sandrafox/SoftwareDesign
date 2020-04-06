package storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class EventStorage {
    private Map<Integer, List<Event>> events;

    public boolean isExist(int id) {
        return events.containsKey(id);
    }

    public void addEvent(int id, Event event) {
        events.computeIfAbsent(id, n -> new ArrayList<Event>());
        events.get(id).add(event);
    }

    public List<Event> getEvents(int id) {
        return events.get(id);
    }

    public void subscribe(Function<Map.Entry<Integer, List<Event>>>)
}
