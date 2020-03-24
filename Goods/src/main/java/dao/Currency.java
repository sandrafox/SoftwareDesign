package dao;

import java.util.HashMap;
import java.util.Map;

public class Currency {
    private static final String RUBLES = "RUB", DOLLARS = "USD", EURO = "EUR";
    private String currency = "";
    private static final Map<String, Map<String, Double>> coefs = new HashMap<String, Map<String, Double>>() {{
        put(RUBLES, new HashMap<String, Double>() {{
            put(DOLLARS, 0.0127);
            put(EURO, 0.0117);
        }});
        put(DOLLARS, new HashMap<String, Double>() {{
            put(RUBLES, 78.74);
            put(EURO, 0.9186);
        }});
        put(EURO, new HashMap<String, Double>() {{
            put(RUBLES, 85.72);
            put(DOLLARS, 1.0029);
        }});
    }};

    public Currency(String currency) {
        if (currency.equals("RUB"))
                this.currency = RUBLES;
        if (currency.equals("USD"))
                this.currency = DOLLARS;
        if (currency.equals("EUR"))
                this.currency = EURO;
    }

    public String getCurrency() {
        return currency;
    }

    public double convertTo(double price, Currency c) {
        if (this.equals(c)) return price;
        return price * coefs.get(currency).get(c.currency);
    }

    public static boolean exists(String c) {
        return c.equals(RUBLES) || c.equals(DOLLARS) || c.equals(EURO);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Currency)) return false;
        Currency c = (Currency) obj;
        return currency.equals(c.currency);
    }
}
