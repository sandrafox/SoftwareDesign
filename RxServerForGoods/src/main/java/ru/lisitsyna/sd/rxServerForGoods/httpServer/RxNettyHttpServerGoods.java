package ru.lisitsyna.sd.rxServerForGoods.httpServer;

import io.netty.handler.codec.http.QueryStringDecoder;
import io.reactivex.netty.protocol.http.server.HttpServer;
import ru.lisitsyna.sd.rxServerForGoods.dao.Currency;
import ru.lisitsyna.sd.rxServerForGoods.dao.Good;
import ru.lisitsyna.sd.rxServerForGoods.dao.ReactiveMongoDriverGoods;
import ru.lisitsyna.sd.rxServerForGoods.dao.User;
import rx.Observable;

import java.util.List;
import java.util.Map;

//import static io.reactivex.netty.protocol.http.server.HttpServer.*;

public class RxNettyHttpServerGoods {
    public static void main(String[] args) {
        HttpServer
                .newServer(8080)
                .start((req, resp) -> {
                    String name = req.getDecodedPath().substring(1);
                    Map<String, List<String>> parameters = new QueryStringDecoder(req.getUri()).parameters();
                    Observable<String> response;
                    switch (name) {
                        case "register":
                            response = ReactiveMongoDriverGoods
                                    .getUsers()
                                    .exists(user -> parameters.get("id").get(0).equals(user.getId()))
                                    .flatMap(exists -> {
                                        if (exists) {
                                            return Observable.just("Sorry, can't register because he already exists\n");
                                        } else {
                                            String currency = parameters.get("currency").get(0);
                                            if (!Currency.exists(currency)) {
                                                return Observable.just("Sorry, currency " + currency + " is invalid\n");
                                            }
                                            User user = new User(Integer.parseInt(parameters.get("id").get(0)),
                                                    new Currency(currency), parameters.get("name").get(0));
                                            return ReactiveMongoDriverGoods.addUser(user)
                                                    .map(success -> user.toString() + " is registered\n");
                                        }
                                    });
                            break;
                        case "add":
                            response = ReactiveMongoDriverGoods
                                    .getGoods()
                                    .exists(good -> parameters.get("id").get(0).equals(good.getId()))
                                    .flatMap(exists -> {
                                        if (exists) {
                                            return Observable.just("Sorry, can't add the good because it already exists\n");
                                        } else {
                                            String currency = parameters.get("currency").get(0);
                                            if (!Currency.exists(currency)) {
                                                return Observable.just("Sorry, currency " + currency + " is invalid\n");
                                            }
                                            Good good = new Good(Integer.parseInt(parameters.get("id").get(0)),
                                                    parameters.get("name").get(0),
                                                    Double.parseDouble(parameters.get("price").get(0)),
                                                    new Currency(currency));
                                            return ReactiveMongoDriverGoods.addGood(good)
                                                    .map(success -> good.toString() + " is added\n");
                                        }
                                    });
                            break;
                        case "show":
                            response = ReactiveMongoDriverGoods
                                    .getUsers()
                                    .filter(user -> parameters.get("id").get(0).equals(user.getId()))
                                    .firstOrDefault(new User(0, new Currency("RUB"), ""))
                                    .flatMap(user -> ReactiveMongoDriverGoods.getGoods()
                                    .map(product -> "(" + product.getId() + ") " + product.getName() + " : " +
                                            product.getCurrency().convertTo(product.getPrice(), user.getCurrency()) +
                                            " " + user.getCurrency().getCurrency()));
                            break;
                        default:
                            response = null;
                    }
                    return resp.writeString(response);
                })
                .awaitShutdown();
    }
}
