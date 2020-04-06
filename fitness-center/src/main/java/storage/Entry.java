package storage;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Entry extends Event {
    public Entry(LocalDateTime date) {
        super();
        description.put(TIME, date);
    }
}
