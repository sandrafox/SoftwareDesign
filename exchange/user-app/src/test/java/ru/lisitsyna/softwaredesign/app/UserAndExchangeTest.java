package ru.lisitsyna.softwaredesign.app;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.containers.GenericContainer;
import ru.lisitsyna.softwaredesign.app.dao.UserDao;
import ru.lisitsyna.softwaredesign.app.dao.UserDaoInMemory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class UserAndExchangeTest {
    @ClassRule
    public static GenericContainer simpleWebServer
            = new FixedHostPortGenericContainer("exchange-app:1.0-SNAPSHOT")
            .withFixedExposedPort(8080, 8080)
            .withExposedPorts(8080);

    private UserApp users;

    @BeforeEach
    public void init() {
        users = new UserApp();
    }

    @Test
    public void simpleTest() throws Exception {
        users = new UserApp();
        Assert.assertEquals("Ok", users.addUser(1));
        Assert.assertEquals("Ok", users.addMoney(1, 1000.));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/add-company?company=a&count=10&price=9.5"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Assert.assertEquals("Ok", response.body());
        //test buy stocks
        request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/update"))
                .GET()
                .build();
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Assert.assertEquals("Ok", response.body());
        request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/get-info"))
                .GET()
                .build();
        HttpResponse<Stream<String>> responses =
                HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofLines());
        String[] stock = responses.body().filter(s -> s.startsWith("[\"a: ")).collect(Collectors.toList()).get(0).split(" ");
        request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/buy-stocks?company=a&count=2"))
                .GET()
                .build();
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        double buy = Double.parseDouble(users.buyStocks(1, "a", 2,
                Double.parseDouble(stock[2].substring(0, stock[2].length() - 2))));
        Assert.assertEquals("" + buy, response.body());
        //test sell stocks
        request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/update"))
                .GET()
                .build();
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        Assert.assertEquals("Ok", response.body());
        request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/get-info"))
                .GET()
                .build();
        responses =
                HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofLines());
        String stockString = responses.body().filter(s -> s.startsWith("[\"a: ")).collect(Collectors.toList()).get(0);
        stock = stockString.split(" ");
        request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/sell-stocks?company=a&count=1"))
                .GET()
                .build();
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        double sell = Double.parseDouble(users.sellStocks(1, "a", 1,
                Double.parseDouble(stock[2].substring(0, stock[2].length() - 2))));
        Assert.assertEquals("" + sell, response.body());

        String[] stockUser = users.getStocks(1).collect(Collectors.toList()).get(0).split(" ");
        Assert.assertEquals(stock[0].substring(2), stockUser[0]);
        Assert.assertEquals("1,", stockUser[1]);
        Assert.assertEquals(stock[2].substring(0, stock[2].length() - 2), stockUser[2]);
        Assert.assertEquals("" + (1000. - buy + sell + Double.parseDouble(stockUser[2])), users.totallyBalance(1));
    }
}
