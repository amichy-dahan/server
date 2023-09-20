package com.dev.objects;

import javax.persistence.*;

@Entity
@Table(name = "salary")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String costumer;
    @Column
    private String location;
    @Column
    private String date;
    @Column
    private int hours;
    @Column
    private int kilometer;
    @Column
    private int employeeId;
    @Column
    private int drivePrice;
    @Column
    private int workPrice;
    @Column
    private int totalPrice;


    public Salary(String costumer, String location, String date, int hours, int kilometer, int employeeId, int drivePrice, int workPrice, int totalPrice) {
        this.costumer = costumer;
        this.location = location;
        this.date = date;
        this.hours = hours;
        this.kilometer = kilometer;
        this.employeeId = employeeId;
        this.drivePrice = drivePrice;
        this.workPrice = workPrice;
        this.totalPrice = totalPrice;
    }

    public Salary() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCostumer() {
        return costumer;
    }

    public void setCostumer(String costumer) {
        this.costumer = costumer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getKilometer() {
        return kilometer;
    }

    public void setKilometer(int kilometer) {
        this.kilometer = kilometer;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getDrivePrice() {
        return drivePrice;
    }

    public void setDrivePrice(int drivePrice) {
        this.drivePrice = drivePrice;
    }

    public int getWorkPrice() {
        return workPrice;
    }

    public void setWorkPrice(int workPrice) {
        this.workPrice = workPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
