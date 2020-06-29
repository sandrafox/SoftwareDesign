package main.ru.lisitsyna.softwaredesign.eventstatistics.clock;

import java.time.Instant;

public interface Clock {
    Instant now();
}
