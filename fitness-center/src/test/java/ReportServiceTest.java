import exceptions.*;
import manage.MangerService;
import manage.ReportService;
import manage.Turnstile;
import org.junit.Assert;
import org.junit.Test;
import storage.EventStorage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.Map;

public class ReportServiceTest {
    @Test
    public void baseTest() throws CreationException, ExtensionException, CollectionException, EntryException, ExitException {
        LocalDateTime now = LocalDateTime.now();
        TestClock clock = new TestClock(now);
        EventStorage es = new EventStorage();
        MangerService m = new MangerService(es, clock);
        Turnstile t = new Turnstile(es, clock);
        m.createAccount(1, "Anya");
        m.extendAccount(1, 30);
        t.comeIn(1);
        clock.plusHours(2);
        t.goOut(1);
        ReportService r = new ReportService();
        es.subscribe(r);
        clock.plusHours(23);
        t.comeIn(1);
        clock.plusHours(1);
        t.goOut(1);
        Assert.assertEquals(1.5, r.meanDuration(), 1e-8);
        Assert.assertEquals(1., r.meanFrequency(), 1e-8);
        Map<LocalDate, Map.Entry<Integer, Long>> daily = r.dailyStatistics();
        Assert.assertTrue(daily.containsKey(now.toLocalDate()));
        Assert.assertTrue(daily.containsKey(now.plusDays(1).toLocalDate()));
        Assert.assertEquals(daily.get(now.toLocalDate()), (new AbstractMap.SimpleEntry<>(1, 2L)));
    }
}
