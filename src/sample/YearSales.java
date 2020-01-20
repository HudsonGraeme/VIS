package sample;

/**
 * YearSales.java
 * Author: Spencer Graham
 * Date: January 17 2020
 * School: Ancaster High School
 * Purpose: This document is a class for the YearSales object which stores the year and associated vehicle sales value
 */

public class YearSales {

    private int year;
    private int count;

    /**
     * Initialize a new YearSales instance with the following parameters
     * @param year - The year of the recorded data
     * @param count - The number of vehicles sold
     */
    public YearSales(int year,int count) {
            this.count = count;
            this.year =  year;
    }

    /**
     * Get the number of vehicle sold
     * @return - The number of vehicles sold
     */
    public int getCount() {
            return count;
    }

    /**
     * Set the number of vehicles sold
     * @param count -  The count of vehicles sold
     */
    public void setCount(int count) {
            this.count = count;
    }

    /**
     * Return the year the count of vehicles sold was recorded
     * @return The year of the data
     */
    public int getYear() {
        return year;
    }

    /**
     * Set the year the number of vehicles sold was recorded
     * @param year - An integer representing the year of sale
     */
    public void setYear(int year) {
        this.year = year;
    }
}
