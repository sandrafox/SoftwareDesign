package manage;

import exceptions.CollectionException;
import storage.Entry;
import storage.Event;
import storage.Exit;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class ReportService {
    private Map<LocalDate, Map.Entry<Integer, Long>> daily = new HashMap<>();
    private Map<Integer, LocalDateTime> visit = new HashMap<>();
    Clock clock;
    double visits = 0.;
    long duration = 0;

    public void handle(Map.Entry<Integer, Event> pair) throws CollectionException {
        Integer id = pair.getKey();
        Event event = pair.getValue();
        if (event instanceof Entry) {
            Object e = event.get(Event.TIME);
            if (e == null)  throw new CollectionException("");
            LocalDateTime time = (LocalDateTime) e;
            visit.put(id, time);
        } else if (event instanceof Exit) {
            Object e = event.get(Event.TIME);
            if (e == null)  throw new CollectionException("");
            LocalDateTime time = (LocalDateTime) e;
            LocalDate date = time.toLocalDate();
            Map.Entry<Integer, Long> p = daily.getOrDefault(date, new AbstractMap.SimpleEntry<Integer, Long>(0, 0L));
            long d = p.getValue() + ChronoUnit.HOURS.between(visit.get(id), time);
            daily.put(date, new AbstractMap.SimpleEntry<>(p.getKey() + 1, d));
            visits++;
            duration += d;
        }
    }

    public double meanFrequency() {
        double days = daily.keySet().size();
        double res = 0.;
        for (LocalDate d : daily.keySet()) {
            res += daily.get(d).getKey();
        }
        return res / days;
    }

    public double meanDuration() {
        return (double) duration / visits;
    }

    public Map<LocalDate, Map.Entry<Integer, Long>> dailyStatistics() {
        return daily;
    }
}
