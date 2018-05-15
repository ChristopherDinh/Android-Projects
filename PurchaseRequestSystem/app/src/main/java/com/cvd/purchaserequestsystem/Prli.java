package com.cvd.purchaserequestsystem;

public class Prli {
    private String product;
    private int quantity;
    private double price;
    private double lineTotal;
    int prliID;

    public Prli(String product, int quantity, double price, double lineTotal, int prliID) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.lineTotal = lineTotal;
        this.prliID = prliID;
    }

    public int getPrliID() {
        return prliID;
    }

    public void setPrliID(int prliID) {
        this.prliID = prliID;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(double lineTotal) {
        this.lineTotal = lineTotal;
    }
}
