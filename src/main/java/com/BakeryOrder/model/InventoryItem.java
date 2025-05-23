package com.BakeryOrder.model;

import java.io.Serializable;

public class InventoryItem implements Serializable {
    private int id;
    private String name;
    private String category;
    private double quantity;
    private String unit;
    private double threshold;
    private String supplier;

    public InventoryItem() {
    }

    public InventoryItem(int id, String name, String category, double quantity, String unit, double threshold, String supplier) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.unit = unit;
        this.threshold = threshold;
        this.supplier = supplier;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                ", threshold=" + threshold +
                ", supplier='" + supplier + '\'' +
                '}';
    }
}
