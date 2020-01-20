package sample;

/**
 * ModelSales.java
 * Author: Spencer Graham
 * Date: January 17 2020
 * School: Ancaster High School
 * Purpose: This document is a class for the ModelSales object which stores manufacturer name and model name, and associated vehicle sales values
 */

public class ModelSales {
    private String manufacturer;
    private String model;
    private int count;
    public ModelSales(String manufacturer,String model,int count) {
        this.count = count;
        this.manufacturer =  manufacturer;
        this.model = model;
    }

    /**
     * Return the number of vehicles sold with the same manufacturer and model
     * @return - The count of vehicles sold
     */
    public int getCount() {
        return count;
    }

    /**
     * Set the value of the number of vehicles with the same model and manufacturer
     * @param count - The number of vehicles that are the same model and manufacturer
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Return the manufacturer name
     * @return - The name of the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Set the name of the manufacturer
     * @param manufacturer - The new name of the manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Return the name of the vehicle model
     * @return - The model name of the vehicle
     */
    public String getModel() {
        return model;
    }

    /**
     * Set the model name of the current instance
     * @param model - The name of the vehicle model
     */
    public void setModel(String model) {
        this.model = model;
    }
}
