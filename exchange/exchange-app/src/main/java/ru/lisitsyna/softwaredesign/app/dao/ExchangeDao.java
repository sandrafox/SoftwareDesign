package ru.lisitsyna.softwaredesign.app.dao;

import ru.lisitsyna.softwaredesign.app.Stock;

import java.util.List;
import java.util.Set;

public interface ExchangeDao {
    boolean addCompany(String company);

    double addStocks(String company, int count);

    boolean addCompanyWthStocks(String company, Stock stock);

    boolean changePrice(String company, double delta);

    List<Stock> getAllStocks();

    Stock getCompanyStocks(String company);

    double buyStocks(String company, int count);

    Set<String> getCompanies();
}
