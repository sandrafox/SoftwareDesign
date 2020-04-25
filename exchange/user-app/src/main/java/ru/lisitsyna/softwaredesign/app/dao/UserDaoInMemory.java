package ru.lisitsyna.softwaredesign.app.dao;

import ru.lisitsyna.softwaredesign.app.Stocks;
import ru.lisitsyna.softwaredesign.app.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoInMemory implements UserDao {
    private Map<Integer, User> users = new HashMap<>();

    @Override
    public boolean addUser(User user) {
        if (users.containsKey(user.getId())) return false;
        users.put(user.getId(), user);
        return true;
    }

    @Override
    public boolean changeBalance(int id, double money) {
        if (!users.containsKey(id)) return false;
        User user = users.get(id);
        user.setBalance(user.getBalance() + money);
        return true;
    }

    @Override
    public boolean buyStocks(int id, String company, Stocks stocks) {
        if (!users.containsKey(id)) return false;
        User user = users.get(id);
        user.addStocks(company, stocks);
        return true;
    }

    @Override
    public boolean sellStocks(int id, String company, Stocks stocks) {
        if (!users.containsKey(id)) return false;
        User user = users.get(id);
        user.delStocks(company, stocks);
        return true;
    }

    @Override
    public double totallyBalance(int id) {
        if (!users.containsKey(id)) throw new IllegalStateException("This user doesn't exists");
        User user = users.get(id);
        double balance = user.getBalance();
        for (Stocks s : user.getStocks().values()) {
            balance += s.getPrice() * s.getCount();
        }
        return balance;
    }

    @Override
    public List<Stocks> getStocks(int id) {
        if (!users.containsKey(id)) throw new IllegalStateException("This user doesn't exists");
        User user = users.get(id);
        return new ArrayList<>(user.getStocks().values());
    }
}
