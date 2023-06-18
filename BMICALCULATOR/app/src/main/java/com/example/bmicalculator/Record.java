package com.example.bmicalculator;

public class Record {
    private String Status, Name, BMI, Age;

    public Record(String name, String age, String bmi, String status){
        this.Name = name;
        this.Age = age;
        this.BMI = bmi;
        this.Status = status;

    }

    //Getters
    public String getName(){
        return Name;
    }

    public String getAge(){
        return Age;
    }

    public String getBMI(){
        return BMI;
    }

    public String getStatus(){
        return Status;
    }

    public String toString(){
        return getName() + "  Age: " + getAge() + "  BMI: " + getBMI() + " (" + getStatus() + ")";
    }

}
