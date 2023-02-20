package com.pharmasoft.Entities;

public class Pharmacist extends Employee{

    public Pharmacist(String first_name, String last_name, String employee_id, String password){
        super(first_name, last_name, employee_id, password);
    }

    public Pharmacist(){

    }

    public boolean equals(Object obj){//
        if (obj == null) return false;
        if (obj == this) return true;
        if (getClass() == obj.getClass()) return true;
        return false;
    }


}
