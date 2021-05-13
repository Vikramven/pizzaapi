package com.pizza;

public class Pizza
{
    private long id;
    private String name;
    private boolean vegeterian;
    private double price;
    private String[] toppings;
    private String customerWish;

    public Pizza()
    {

    }

    public Pizza(long id, String name, boolean vegeterian, double price, String[] toppings)
    {
        this.id = id;
        this.name = name;
        this.vegeterian = vegeterian;
        this.price = price;
        this.toppings = toppings;
        customerWish = null;
    }

    public Pizza(long id, String name, boolean vegeterian, double price, String[] toppings, String wish)
    {
        this.id = id;
        this.name = name;
        this.vegeterian = vegeterian;
        this.price = price;
        this.toppings = toppings;
        this.customerWish = wish;
    }

    @Override
    public String toString() {
        return "Pizza [id=" + id +
                ", name=" + name +
                ", vegeterian=" +
                vegeterian +
                ", price=" + price +
                "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegeterian() {
        return vegeterian;
    }

    public void setVegeterian(boolean vegeterian) {
        this.vegeterian = vegeterian;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String[] getToppings() {
        return toppings;
    }

    public void setToppings(String[] toppings) {
        this.toppings = toppings;
    }

    public String getCustomerWish() {
        return customerWish;
    }

    public void setCustomerWish(String wish) {
        this.customerWish = wish;
    }
}


