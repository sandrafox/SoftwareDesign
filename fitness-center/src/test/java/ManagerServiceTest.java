import exceptions.CollectionException;
import exceptions.CreationException;
import exceptions.ExtensionException;
import manage.MangerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import storage.Account;
import storage.EventStorage;

import java.time.Clock;
import java.time.LocalDateTime;

public class ManagerServiceTest {


    @BeforeEach
    void init() {

    }

    @Test
    public void Test() throws CreationException, ExtensionException, CollectionException {
        LocalDateTime now = LocalDateTime.now();
        TestClock clock = new TestClock(now);
        EventStorage es = new EventStorage();
        MangerService m = new MangerService(es, clock);
        m.createAccount(1, "Anya");
        m.extendAccount(1, 30);
        clock.plusHours(25);
        Account a1 = m.collectInfo(1);
        Assert.assertEquals("Anya", a1.getName());
        Assert.assertEquals(now.toLocalDate(), a1.getCreated());
        Assert.assertEquals(now.toLocalDate().plusDays(30), a1.getExpiration());
    }
}
