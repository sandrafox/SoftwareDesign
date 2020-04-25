package ru.lisitsyna.softwaredesign.app.dao;

import ru.lisitsyna.softwaredesign.app.Stock;

import java.util.*;
import java.util.stream.Collectors;

public class ExchangeDaoInMemory implements ExchangeDao {
    private Map<String, Stock> stocks = new HashMap<>();

    @Override
    public boolean addCompany(String company) {
        if (stocks.containsKey(company)) return false;
        stocks.put(company, new Stock(company, 0, 0.));
        return true;
    }

    @Override
    public double addStocks(String company, int count) {
        if (!stocks.containsKey(company)) return -1.;
        Stock stock1 = stocks.get(company);
        stock1.setCount(stock1.getCount() + count);
        return count * stock1.getPrice();
    }

    @Override
    public boolean addCompanyWthStocks(String company, Stock stock) {
        if (stocks.containsKey(company)) return false;
        if (!company.equals(stock.getCompany())) return false;
        stocks.put(company, stock);
        return true;
    }

    @Override
    public boolean changePrice(String company, double delta) {
        if (!stocks.containsKey(company)) return false;
        Stock stock1 = stocks.get(company);
        stock1.setPrice(stock1.getPrice() + delta);
        return true;
    }

    @Override
    public List<Stock> getAllStocks() {
        return new ArrayList<>(stocks.values());
    }

    @Override
    public Stock getCompanyStocks(String company) {
        return stocks.get(company);
    }

    @Override
    public double buyStocks(String company, int count) {
        if (!stocks.containsKey(company)) return -1.;
        Stock stock = stocks.get(company);
        double sum = stock.getPrice() * count;
        stock.setCount(stock.getCount() - count);
        return sum;
    }

    @Override
    public Set<String> getCompanies() {
        return stocks.keySet();
    }
}
