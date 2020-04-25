package ru.lisitsyna.softwaredesign.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lisitsyna.softwaredesign.app.dao.UserDao;
import ru.lisitsyna.softwaredesign.app.dao.UserDaoInMemory;

import java.util.List;
import java.util.stream.Stream;

@RestController
public class UserApp {
    private UserDao dao = new UserDaoInMemory();

    @RequestMapping("/add-user")
    public String addUser(int id) {
        if (dao.addUser(new User(id))) return "Ok";
        return "Problems with adding user with id = " + id;
    }

    @RequestMapping("/add-money")
    public String addMoney(int id, double money) {
        if (dao.changeBalance(id, money)) return "Ok";
        return "Can't add money to user with id = " + id;
    }

    @RequestMapping("/buy-stocks")
    public String buyStocks(int id, String company, int count, double price) {
        if (dao.buyStocks(id, company, new Stocks(company, count, price))
                && dao.changeBalance(id, -price * count)) return "" + count * price;
        return "Can't buy stocks to user with id = " + id;
    }

    @RequestMapping("/sell-stocks")
    public String sellStocks(int id, String company, int count, double price) {
        if (dao.sellStocks(id, company, new Stocks(company, count, price))
                && dao.changeBalance(id, price * count)) return "" + count * price;
        return "Can't buy stocks to user with id = " + id;
    }

    @RequestMapping("/get-totally-balance")
    public String totallyBalance(int id) {
        return "" + dao.totallyBalance(id);
    }

    @RequestMapping("/get_stocks")
    public Stream<String> getStocks(int id) {
        return dao.getStocks(id).stream().map(s -> s.toString());
    }
}
