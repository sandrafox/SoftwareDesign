package ru.lisitsyna.softwaredesign.app;

public class Stocks {
    private String company;
    private int count;
    private double price;

    public Stocks(String company, int count, double price) {
        this.company = company;
        this.count = count;
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return company + ": " + count + ", " + price;
    }
}
