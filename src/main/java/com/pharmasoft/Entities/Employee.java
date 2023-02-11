package com.pharmasoft.Entities;

public class Employee {

    private String first_name;
    private String last_name;
    private String employee_id;
    private boolean root_access;

    public Employee(String first_name, String last_name, String employee_id){
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Employee(String first_name, String last_name){
        this.first_name = first_name;
        this.last_name = last_name;
    }

    private void AddEmployee(){
        // store to db here
        System.out.println("[APP] Employee Created");
    }

    public static void RemoveEmployee(){
        // check if allowed to do that
        // remove from database
    }

}
