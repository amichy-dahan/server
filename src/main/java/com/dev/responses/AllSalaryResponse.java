package com.dev.responses;

import com.dev.objects.Price;
import com.dev.objects.Salary;

import java.util.List;

public class AllSalaryResponse extends BasicResponse{


    private List<Salary> salaries;




    public AllSalaryResponse(boolean success, Integer errorCode, List<Salary> salaries) {
        super(success, errorCode);
        this.salaries = salaries;
    }

public AllSalaryResponse(){

}

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }
}
