package com.pizza;

public class PRequest {

    private long id;
    private String customerWish;

    public PRequest()
    {
        //empty constructor as always
    }

    public PRequest(long id, String wish)
    {
        this.id = id;
        this.customerWish = wish;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerWish() {
        return customerWish;
    }

    public void setCustomerWish(String wish) {
        this.customerWish = wish;
    }
}
