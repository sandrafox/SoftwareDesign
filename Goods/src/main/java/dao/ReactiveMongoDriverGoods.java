package dao;

//import com.mongodb.reactivestreams.client.*;
import com.mongodb.rx.client.*;
//import io.reactivex.Observable;
import org.bson.Document;
import rx.Observable;

public class ReactiveMongoDriverGoods {
    private static MongoClient client =  MongoClients.create("mongodb://localhost:27017");
    private static MongoDatabase database = client.getDatabase("shop");
    private static MongoCollection<Document> users = database.getCollection("users");
    private static MongoCollection<Document> products = database.getCollection("goods");

    public static Observable<Success> addUser(User user){
        return users.insertOne(user.toDocument());
    }

    public static Observable<Success> addProduct(Good good) {
        return products.insertOne(good.toDocument());
    }

    public static Observable<User> getUsers() {
        return users.find().toObservable().map(User::new);
    }

    public static Observable<Good> getProducts() {
        return products.find().toObservable().map(Good::new);
    }

    public static void close() {
        client.close();
    }
}
