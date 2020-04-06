package storage;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Exit extends Event {
    public Exit(LocalDateTime date) {
        super();
        description.put(TIME, date);
    }
}
