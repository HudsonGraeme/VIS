package sample;

/**
 * VehicleData.java
 * Author: Spencer Graham
 * Date: January 17 2020
 * School: Ancaster High School
 * Purpose: This document is a class for the VehicleData object which stores all values loaded from the CSV file
 */

public class VehicleData {
    private String firstName;
    private String lastName;
    private int id;
    private String email;
    private String VIN;
    private String make;
    private String model;
    private int modelYear;
    private String color;
    private String plateNumber;

    private int year1;
    private int odometer1;
    private int year2;
    private int odometer2;
    private int year3;
    private int odometer3;
    private int year4;
    private int odometer4;
    private int year5;
    private int odometer5;
    private int mileage;
    private int count;
    private String stickerValid;

    /**
     * Initialize a new instance of VehicleData with the following parameters
     * @param id - The integer representing the ID of the data object
     * @param firstName - The first name of the vehicle owner
     * @param lastName - The last name of the vehicle owner
     * @param email - The email address of the vehicle owner
     * @param VIN - The Vehicle Identification Number of the vehicle
     * @param make - The manufacturer of the vehicle
     * @param model - The model of the vehicle
     * @param modelYear - The year the vehicle was produced
     * @param color - The color of the vehicle
     * @param plateNumber - The vehicle's plate number
     * @param year1 - Year one of registration
     * @param odometer1 - The vehicle's odometer reading for year one
     * @param year2 - Year two of registration
     * @param odometer2 - The vehicle's odometer reading for year two
     * @param year3 - Year three of registration
     * @param odometer3 - The vehicle's odometer reading for year three
     * @param year4 - Year four of registration
     * @param odometer4 - The vehicle's odometer reading for year four
     * @param year5 - Year five of registration
     * @param odometer5 - The vehicle's odometer reading for year five
     */
    public VehicleData(int id,String firstName,String lastName,String email,String VIN,String make,String model,int modelYear,String color,String plateNumber,int year1,int odometer1,int year2,int odometer2,int year3,int odometer3, int year4,int odometer4,int year5, int odometer5) {

        this.id = id;
        this.count = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.VIN = VIN;
        this.make = make;
        this.model = model;
        this.modelYear = modelYear;
        this.color = color;
        this.plateNumber = plateNumber;
        this.year1 = year1;
        this.odometer1 = odometer1;
        this.year2 = year2;
        this.odometer2 = odometer2;
        this.year3 = year3;
        this.odometer3 = odometer3;
        this.year4 = year4;
        this.odometer4 = odometer4;
        this.year5 = year5;
        this.odometer5 = odometer5;
        this.mileage = this.odometer1 + this.odometer2 + this.odometer3 + this.odometer4 + this.odometer5;
        if(this.year5 == 2017) {
            this.stickerValid = "Valid";
        } else {
            this.stickerValid = "Invalid";
        }
    }

    /**
     * Get the fifth year's odometer reading
     * @return - the year 5 odometer reading
     */
    public int getOdometer5() {
        return odometer5;
    }

    /**
     * Set the fifth year's odometer reading
     * @param odometer5 - the year 5 odometer reading
     */
    public void setOdometer5(int odometer5) {
        this.odometer5 = odometer5;
    }

    /**
     * Get the fifth year's value
     * @return - the value of year 5
     */
    public int getYear5() {
        return year5;
    }

    /**
     * Set the fifth year's value
     * @param year5  - the value of year 5
     */
    public void setYear5(int year5) {
        this.year5 = year5;
    }

    /**
     * Get the fourth year's odometer reading
     * @return - the year 4 odometer reading
     */
    public int getOdometer4() {
        return odometer4;
    }

    /**
     * Set the fourth year's odometer reading
     * @param odometer4 - the year 4 odometer reading
     */
    public void setOdometer4(int odometer4) {
        this.odometer4 = odometer4;
    }

    /**
     * Get the fourth year's value
     * @return - the value of year 4
     */
    public int getYear4() {
        return year4;
    }

    /**
     * Set the fourth year's value
     * @param year4  - the value of year 4
     */
    public void setYear4(int year4) {
        this.year4 = year4;
    }

    /**
     * Get the third year's odometer reading
     * @return - the year 3 odometer reading
     */
    public int getOdometer3() {
        return odometer3;
    }

    /**
     * Set the third year's odometer reading
     * @param odometer3 - the year 3 odometer reading
     */
    public void setOdometer3(int odometer3) {
        this.odometer3 = odometer3;
    }

    /**
     * Get the third year's value
     * @return - the value of year 3
     */
    public int getYear3() {
        return year3;
    }

    /**
     * Set the third year's value
     * @param year3  - the value of year 3
     */
    public void setYear3(int year3) {
        this.year3 = year3;
    }

    /**
     * Get the second year's odometer reading
     * @return - the year 2 odometer reading
     */
    public int getOdometer2() {
        return odometer2;
    }

    /**
     * Set the second year's odometer reading
     * @param odometer2 - the year 2 odometer reading
     */
    public void setOdometer2(int odometer2) {
        this.odometer2 = odometer2;
    }

    /**
     * Get the second year's value
     * @return - the value of year 2
     */
    public int getYear2() {
        return year2;
    }

    /**
     * Set the second year's value
     * @param year2  - the value of year 2
     */
    public void setYear2(int year2) {
        this.year2 = year2;
    }
    /**
     * Get the first year's odometer reading
     * @return - the year 1 odometer reading
     */
    public int getOdometer1() {
        return odometer1;
    }
    /**
     * Set the first year's odometer reading
     * @param odometer1 - the year 1 odometer reading
     */
    public void setOdometer1(int odometer1) {
        this.odometer1 = odometer1;
    }
    /**
     * Get the first year's value
     * @return - the value of year 1
     */
    public int getYear1() {
        return year1;
    }

    /**
     * Set the first year's value
     * @param year1  - the value of year 1
     */
    public void setYear1(int year1) {
        this.year1 = year1;
    }

    /**
     * Get the plate number on the vehicle
     * @return - The vehicle's plate number
     */
    public String getPlateNumber() {
        return plateNumber;
    }

    /**
     * Set the new value of the plate number
     * @param plateNumber  - the value of the plate number
     */
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    /**
     * Get the vehicle color
     * @return - The color of the vehicle
     */
    public String getColor() {
        return color;
    }

    /**
     * Set the new value of the vehicle color
     * @param color  - the value of the vehicle color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Get the year the vehicle was produced
     * @return - The value of the year the model was produced
     */
    public int getModelYear() {
        return modelYear;
    }

    /**
     * Set the new value of the year of production of the vehicle
     * @param modelYear  - the value of the year the vehicle was produced
     */
    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    /**
     * Get the model of the vehicle
     * @return - The model of the vehicle
     */
    public String getModel() {
        return model;
    }

    /**
     * Set the new value of the vehicle model
     * @param model - the value of the vehicle model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Get the vehicle make
     * @return - The make of the vehicle
     */
    public String getMake() {
        return make;
    }

    /**
     * Set the new value of the vehicle make
     * @param make  - the value of the vehicle make
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Get the vehicle's VIN number
     * @return - The VIN number of the vehicle
     */
    public String getVIN() {
        return VIN;
    }

    /**
     * Set the new value of the VIN
     * @param VIN - the value of the VIN
     */
    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    /**
     * Get the email address of the person
     * @return - The person's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the new value of the person's email
     * @param email  - the value of the person's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of the ID property
     * @return - the integer value of the ID property
     */
    public int getId() {
        return id;
    }

    /**
     * Get the string value of the ID property
     * @return - A string representing the ID property's value
     */
    public String getIdString() {
        return Integer.toString(id);
    }

    /**
     * Set the new value of the ID
     * @param id  - the value of the ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the person's last name
     * @return - The last name of the person
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the new value of the last name property
     * @param lastName  - the value of the person's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the first name of the person
     * @return - The person's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the new value of the first name property
     * @param firstName  - the value of the person's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the mileage the person travelled over thr course of five years
     * @return - The mileage on the vehicle after five years
     */
    public int getMileage() {
        return mileage;
    }

    /**
     * Set the new value of the miles driven over 5 years
     * @param mileage  - the value of the mileage the person has driven over the course of 5 years
     */
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    /**
     * Get the number of vehicles the person owns
     * @return - The number of owned vehicles
     */
    public int getCount() {
        return count;
    }

    /**
     * Set the new value of the number of vehicles owned by the person
     * @param count  - the number of vehicles the person owns
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Return a string indicating if the vehicle's sticker is valid
     * @return - A string whether the vehicle sticker is valid or not
     */
    public String isStickerValid() {
        return stickerValid;
    }

    /**
     * Set the new value of the sticker valid property
     * @param stickerValid  - the value of the sticker valid property
     */
    public void setStickerValid(String stickerValid) {
        this.stickerValid = stickerValid;
    }
}
