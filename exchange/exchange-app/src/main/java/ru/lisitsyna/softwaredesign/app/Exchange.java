package ru.lisitsyna.softwaredesign.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lisitsyna.softwaredesign.app.dao.ExchangeDao;
import ru.lisitsyna.softwaredesign.app.dao.ExchangeDaoInMemory;

import java.util.*;
import java.util.stream.Stream;

@RestController
public class Exchange {
    private ExchangeDao dao = new ExchangeDaoInMemory();

    @RequestMapping("/add-company")
    public String addCompany(String company, int count, double price) throws ExchangeException {
        if (dao.addCompanyWthStocks(company, new Stock(company, count, price))) return "Ok";
        throw new ExchangeException("Can't add stocks of this company " + company);
    }

    @RequestMapping("/add-stocks")
    public String  addStocks(String company, int count) throws ExchangeException {
        if (dao.addStocks(company, count) > 0.) return "Ok";
        throw new ExchangeException("Can't add stocks to this company " + company);
    }

    @RequestMapping("/buy-stocks")
    public String buyStocks(String company, int count) throws ExchangeException {
        return "" + dao.buyStocks(company, count);
    }

    @RequestMapping("/sell-stocks")
    public String sellStocks(String company, int count) {
        return "" + dao.addStocks(company, count);
    }

    @RequestMapping("/get-info")
    public Stream<String> getStocksInfo() {
        return dao.getAllStocks().stream().map(Stock::toString);
    }

    @RequestMapping("/update")
    public String updatePrices() throws ExchangeException {
        boolean flag = true;
        for (String compamy : dao.getCompanies()) {
            double delta = Math.random() * 5. * Math.signum(Math.random() - 0.5);
            flag &= dao.changePrice(compamy, delta);
        }
        if (flag) return "Ok";
        throw new ExchangeException("Can't update price");
    }
}
