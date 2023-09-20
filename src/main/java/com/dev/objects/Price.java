package com.dev.objects;

import javax.persistence.*;

@Entity
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private int employeeId;
    @Column
    private int priceOfHour;
    @Column
    private double priceOfKilo;


    public Price(int employeeId, int priceOfHour, double priceOfKilo) {
        this.employeeId = employeeId;
        this.priceOfHour = priceOfHour;
        this.priceOfKilo = priceOfKilo;
    }


    public Price(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getPriceOfHour() {
        return priceOfHour;
    }

    public void setPriceOfHour(int priceOfHour) {
        this.priceOfHour = priceOfHour;
    }

    public double getPriceOfKilo() {
        return priceOfKilo;
    }

    public void setPriceOfKilo(double priceOfKilo) {
        this.priceOfKilo = priceOfKilo;
    }
}