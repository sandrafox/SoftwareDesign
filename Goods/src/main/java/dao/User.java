package dao;

import org.bson.Document;

public class User {
    private int id;
    private Currency currency;
    private String name;

    public User(int id, Currency currency, String name) {
        this.id = id;
        this.currency = currency;
        this.name = name;
    }

    public User(Document doc) {
        this(doc.getInteger("id"), new Currency(doc.getString("currency")),
                doc.getString("name"));
    }

    public Document toDocument() {
        return new Document("id", id)
                .append("name", name)
                .append("currency", currency.getCurrency());

    }

    public int getId() {
        return id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "(" + id + ") " + name + " - " + currency.getCurrency();
    }
}
