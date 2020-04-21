import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.lisitsyna.softwaredesign.actorsearch.AgregateActor;
import ru.lisitsyna.softwaredesign.actorsearch.Answer;
import ru.lisitsyna.softwaredesign.actorsearch.StubServer;

import static java.lang.Thread.sleep;

public class AgregateActorTest {
    private ActorSystem system;
    private ActorRef agregator;

    @BeforeEach
    void init() {
        system = ActorSystem.create("MySystem");
        agregator = system.actorOf(Props.create(AgregateActor.class), "agragator");
    }

    @AfterEach
    void terminate() {
        system.terminate();
    }

    @Test
    void simpleTest() {
        StubServer.TIMEOUT = 0;
        agregator.tell("dogs", ActorRef.noSender());
        try {
            sleep(1000);
            Assertions.assertEquals(3, Answer.responses.keySet().size());
        } catch (InterruptedException e) {
            Assertions.fail();
        }
    }

    @Test
    void emptyAnswer() {
        StubServer.TIMEOUT = 100000;
        agregator.tell("dogs", ActorRef.noSender());
        try {
            sleep(30000);
            Assertions.assertTrue(Answer.responses.isEmpty());
        } catch (InterruptedException e) {
            Assertions.fail();
        }
    }
}
