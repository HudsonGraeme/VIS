package sample;

/**
 * ColourSales.java
 * Author: Spencer Graham
 * Date: January 17 2020
 * School: Ancaster High School
 * Purpose: This document is a class for the ColourSales object which stores the color and associated vehicle sales value
 */


public class ColourSales {

    private String colour;
    private int count;

    /**
     * Initialize a new ColourSales object given the following parameters
     * @param colour - The color of the vehicle
     * @param count - The number of vehicles with the same colour
     */
    public ColourSales(String colour,int count) {
        this.count = count;
        this.colour =  colour;
    }

    /**
     * Get the number of vehicles sold
     * @return - The number of vehicles
     */
    public int getCount() {
        return count;
    }

    /**
     * Set the number of vehicles sold
     * @param count - The number of vehicles
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Get the colour of the data
     * @return - The string representing the colour
     */
    public String getColour() {
        return colour;
    }

    /**
     * Set the colour property to a new value
     * @param colour - A string representing a new colour
     */
    public void setColour(String colour) {
        this.colour = colour;
    }
}
