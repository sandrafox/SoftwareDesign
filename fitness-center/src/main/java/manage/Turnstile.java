package manage;

import exceptions.CollectionException;
import exceptions.EntryException;
import exceptions.ExitException;
import storage.*;
import utils.Utils;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Turnstile {
    private EventStorage storage;
    private Clock clock;

    public Turnstile(EventStorage storage, Clock clock) {
        this.storage = storage;
        this.clock = clock;
    }

    public void comeIn(int id) throws EntryException, CollectionException {
        if (!mayComeIn(id)) throw new EntryException("This id = " + id + " can't come in");
        storage.addEvent(id, new Entry(Utils.localDateTime(clock)));
    }

    public void goOut(int id) throws ExitException, CollectionException {
        if (!mayGoOut(id)) throw new ExitException("This id = " + id + " can't go out");
        storage.addEvent(id, new Exit(Utils.localDateTime(clock)));
    }

    private boolean mayComeIn(int id) throws CollectionException {
        Map.Entry<LocalDate, Boolean> p = getExpirationAndInside(id);
        return !p.getValue() && p.getKey().isAfter(Utils.localDate(clock));
    }

    private boolean mayGoOut(int id) throws CollectionException {
        Map.Entry<LocalDate, Boolean> p = getExpirationAndInside(id);
        return p.getValue();
    }

    private Map.Entry<LocalDate, Boolean> getExpirationAndInside(int id) throws CollectionException {
        List<Event> events = storage.getEvents(id);
        if (events == null) throw new CollectionException("This id = " + id + " doesn't exist");
        if (!(events.get(0) instanceof CreationAccount)) throw new CollectionException("This id = " + id + " wasn't created");
        Object e = events.get(0).get(Event.DATE);
        if (e == null)  throw new CollectionException("");
        LocalDate expiration = (LocalDate) e ;
        e = events.get(0).get(Event.NAME);
        if (e == null)  throw new CollectionException("");
        String name = (String) e;
        boolean inside = false;
        for (int i = 1; i < events.size(); ++i) {
            Event event = events.get(i);
            if (event instanceof ExtensionAccount) {
                e = event.get(Event.DATE);
                if (e == null)  throw new CollectionException("");
                LocalDate date = (LocalDate) e;
                e = event.get(Event.PERIOD);
                if (e == null)  throw new CollectionException("");
                Long period = (Long) e;
                expiration = date.plusDays(period);
            } else if (event instanceof Entry) {
                inside = true;
            } else if (event instanceof Exit) {
                inside = false;
            }
        }
        return new AbstractMap.SimpleEntry<>(expiration, inside);
    }
}
