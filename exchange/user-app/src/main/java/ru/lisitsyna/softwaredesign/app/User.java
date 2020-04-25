package ru.lisitsyna.softwaredesign.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private int id;
    private double balance;
    private Map<String, Stocks> stocks = new HashMap<>();

    public User(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Map<String, Stocks> getStocks() {
        return stocks;
    }

    public void setStocks(Map<String, Stocks> stocks) {
        this.stocks = stocks;
    }

    public int getId() {
        return id;
    }

    public void addStocks(String company, Stocks stock) {
        Stocks stock1 = stocks.get(company);
        if (stock1 == null)
            stocks.put(company, stock);
        else {
            stock1.setPrice(stock.getPrice());
            stock1.setCount(stock1.getCount() + stock.getCount());
        }
    }

    public void delStocks(String company, Stocks stock) {
        Stocks stock1 = stocks.get(company);
        if (stock1 == null)
            throw new IllegalStateException("User = " + id + " doesn't have stocks of this comany " + company);
        else {
            stock1.setPrice(stock.getPrice());
            stock1.setCount(stock1.getCount() - stock.getCount());
        }
    }
}
