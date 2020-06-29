import dao.ReactiveMongoDriverGoods;
import org.junit.jupiter.api.Assertions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.fail;

public class Test {
    private String sendRequest(String requestStr) {
        try {
            URL url = new URL("http://localhost:8080/" + requestStr);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            Assertions.assertEquals(connection.getResponseCode(), HttpURLConnection.HTTP_OK);
            StringBuilder responseStr = new StringBuilder();
            try (BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = input.readLine()) != null) {
                    responseStr.append(line);
                }
            }
            return responseStr.toString();
        } catch () {
            fail("Request isn't sended: " + ex.getMessage());
        }
        return "";
    }

    @org.junit.jupiter.api.Test
    void correctRequests() {
        String respStr;

        respStr = sendRequest("register?id=1&name=anya&currency=RUB");
        Assertions.assertTrue(respStr.contains("is added"));
        respStr = sendRequest("register?id=2&name=usd-user&currency=USD");
        Assertions.assertTrue(respStr.contains("is added"));
        respStr = sendRequest("register-user?id=3&name=eur-user&currency=EUR");
        Assertions.assertTrue(respStr.contains("is added"));
        ReactiveMongoDriverGoods.getUsers().toList().forEach(list -> Assertions.assertEquals(list.size(), 3));

        respStr = sendRequest("add?id=1&name=cat&price=4745.0&currency=RUB");
        Assertions.assertTrue(respStr.contains("is added"));
        respStr = sendRequest("add?id=2&name=dog&price=73.0&currency=USD");
        Assertions.assertTrue(respStr.contains("is added"));
        respStr = sendRequest("add?id=3&name=parrot&price=65.0&currency=EUR");
        Assertions.assertTrue(respStr.contains("is added"));
        ReactiveMongoDriverGoods.getGoods().toList().forEach(list -> Assertions.assertEquals(list.size(), 3));

        respStr = sendRequest("show?id=1");
        Assertions.assertTrue(respStr.contains("cat") && respStr.contains("dog") && respStr.contains("parrot") &&
                respStr.contains("RUB") && !respStr.contains("USD") && !respStr.contains("EUR"));
        respStr = sendRequest("show?id=2");
        Assertions.assertTrue(respStr.contains("cat") && respStr.contains("dog") && respStr.contains("parrot") &&
                !respStr.contains("RUB") && respStr.contains("USD") && !respStr.contains("EUR"));
        respStr = sendRequest("show?id=3");
        Assertions.assertTrue(respStr.contains("cat") && respStr.contains("dog") && respStr.contains("parrot") &&
                !respStr.contains("RUB") && !respStr.contains("USD") && respStr.contains("EUR"));
        ReactiveMongoDriverGoods.getUsers().toList().forEach(list -> Assertions.assertEquals(list.size(), 3));
        ReactiveMongoDriverGoods.getGoods().toList().forEach(list -> Assertions.assertEquals(list.size(), 3));

    }
}
