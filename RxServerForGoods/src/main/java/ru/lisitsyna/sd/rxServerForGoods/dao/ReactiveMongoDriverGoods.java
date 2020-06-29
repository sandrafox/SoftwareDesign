package ru.lisitsyna.sd.rxServerForGoods.dao;

import com.mongodb.rx.client.*;
import org.bson.Document;
import rx.Observable;

import java.util.List;

public class ReactiveMongoDriverGoods {
    private static MongoClient client = createMongoClient();
    private static MongoDatabase database = client.getDatabase("rxgoods");
    private static MongoCollection<Document> users = database.getCollection("user");
    private static MongoCollection<Document> goods = database.getCollection("good");

    public static Observable<User> getUsers() {
        return users.find().toObservable().map(User::new);
    }

    public static Observable<Good> getGoods() {
        return goods.find().toObservable().map(Good::new);
    }

    public static Observable<Success> addUser(User user) {
        return users.insertOne(user.toDocument());
    }

    public static Observable<Success> addGood(Good good) {
        return goods.insertOne(good.toDocument());
    }

    private static MongoClient createMongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }
}
