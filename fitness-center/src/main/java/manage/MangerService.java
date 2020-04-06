package manage;

import exceptions.CollectionException;
import exceptions.CreationException;
import exceptions.ExtensionException;
import storage.*;
import utils.Utils;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MangerService {
    private EventStorage storage;
    private Clock clock;

    public MangerService(EventStorage storage, Clock clock) {
        this.storage = storage;
        this.clock = clock;
    }

    public void extendAccount(int id, long days) throws ExtensionException {
        if (!storage.isExist(id)) throw new ExtensionException("This id = " + id + " doesn't exist");
        storage.addEvent(id, new ExtensionAccount(Utils.localDate(clock), days));
    }

    public void createAccount(int id, String name) throws CreationException {
        if (storage.isExist(id)) throw new CreationException("This id = " + id + " already exists");
        storage.addEvent(id, new CreationAccount(Utils.localDate(clock), name));
    }

    public Account collectInfo(int id) throws CollectionException {
        List<Event> events = storage.getEvents(id);
        if (events == null) throw new CollectionException("This id = " + id + " doesn't exist");
        if (!(events.get(0) instanceof CreationAccount)) throw new CollectionException("This id = " + id + " wasn't created");
        Account account = new Account(id);
        Object e = events.get(0).get(Event.DATE);
        if (e == null)  throw new CollectionException("");
        LocalDate expiration = (LocalDate) e ;
        account.setCreated(expiration);
        account.setExpiration(expiration);
        e = events.get(0).get(Event.NAME);
        if (e == null)  throw new CollectionException("");
        String name = (String) e;
        account.setName(name);
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
                account.setExpiration(expiration);
            } else if (event instanceof Entry) {
                e = event.get(Event.TIME);
                if (e == null)  throw new CollectionException("");
                LocalDateTime time = (LocalDateTime) e;
                account.setLastVisit(time);
            }
        }
        return account;
    }
}
