import exceptions.*;
import manage.MangerService;
import manage.Turnstile;
import org.junit.Assert;
import org.junit.Test;
import storage.Account;
import storage.EventStorage;

import java.time.LocalDateTime;

public class TurnstileTest {
    @Test
    public void baseTest() throws CreationException, CollectionException, EntryException, ExtensionException, ExitException {
        LocalDateTime now = LocalDateTime.now();
        TestClock clock = new TestClock(now);
        EventStorage es = new EventStorage();
        MangerService m = new MangerService(es, clock);
        Turnstile t = new Turnstile(es, clock);
        m.createAccount(1, "Anya");
        m.extendAccount(1, 2);
        t.comeIn(1);
        clock.plusHours(25);
        t.goOut(1);
        Account a = m.collectInfo(1);
        Assert.assertEquals(now, a.getLastVisit());
    }
}
