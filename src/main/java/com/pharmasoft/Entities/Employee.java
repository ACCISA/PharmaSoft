package com.pharmasoft.Entities;

import com.pharmasoft.Utils.Api;

import java.io.IOException;

public class Employee {

    private String first_name;
    private String last_name;
    private String employee_id;
    private boolean root_access;

    public Employee(){

    }

    public Employee(String first_name, String last_name, String employee_id){
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Employee(String first_name, String last_name){
        this.first_name = first_name;
        this.last_name = last_name;
    }

    private void AddEmployee() throws Exception {
        // store to db here
        System.out.println("[APP] Employee Created");
        Api sendToServer = new Api();
        sendToServer.addEmployee(new String[]{first_name,last_name,employee_id},Session.cur_session.getToken(),this);
    }

    public static void RemoveEmployee(){
        // check if allowed to do that
        // remove from database
    }

    public boolean equals(Object obj){
        if (obj == null) return false;
        if (obj == this) return true;
        if (getClass() == obj.getClass()) return true;
        return false;
    }

}
