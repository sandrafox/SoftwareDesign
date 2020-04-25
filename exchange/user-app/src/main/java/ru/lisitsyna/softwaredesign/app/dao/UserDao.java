package ru.lisitsyna.softwaredesign.app.dao;

import ru.lisitsyna.softwaredesign.app.Stocks;
import ru.lisitsyna.softwaredesign.app.User;

import java.util.List;

public interface UserDao {
    boolean addUser(User user);

    boolean changeBalance(int id, double money);

    boolean buyStocks(int id, String company, Stocks stocks);

    boolean sellStocks(int id, String company, Stocks stocks);

    double totallyBalance(int id);

    List<Stocks> getStocks(int id);
}
