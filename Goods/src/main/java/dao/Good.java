package dao;

import org.bson.Document;

public class Good {
    private int id;
    private String name;
    private double price;
    private Currency currency;

    public Good(int id, String name, double price, Currency currency) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    public Good(Document doc) {
        this(doc.getInteger("id"), doc.getString("name"), doc.getDouble("price"),
                new Currency(doc.getString("currency")));
    }

    public Document toDocument() {
        return new Document("id", id)
                .append("name", name)
                .append("price", price)
                .append("currency", currency.getCurrency());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "(" + id + ") " + name + " : " + price + " " + currency.getCurrency();
    }
}
