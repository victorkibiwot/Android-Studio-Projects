package com.example.bmicalculator;

import java.text.DecimalFormat;

public class BMICalculator{
    int feet, inch, weight, age;
    double bmi;
    String gender;

    public BMICalculator(String gen, int ag, int ft, int in, int we){
        feet = ft;
        inch = in;
        weight = we;
        age = ag;
        gender = gen;
    }

    public void computeBMI(){
        //Convert feet to inches and get total inches
        int totalInches = (feet * 12) + inch;

        //Height in meters is the inches multiplied by 0.0254
        double heightInMeters = (totalInches * 0.0254);

        //BMI formula = weight in kg divided by height in meters squared
        bmi = weight / (heightInMeters * heightInMeters);
    }

    public String displayGuidance(double bmi) {

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String bmi_str = decimalFormat.format(bmi);

        if(gender == "Male"){
            //Display boy guidance
            return bmi_str + " - As you are under 18, please consult with yur doctor for healthy range for boys";
        }else{
            //Display girl guidance
            return bmi_str + " - As you are under 18, please consult with yur doctor for healthy range for girls";
        }

    }


    public String getFeedback(double bmi){

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String bmi_str = decimalFormat.format(bmi);
        String health;
        if(bmi<18.5){
            //Underweight
            health = "Underweight!";
            return bmi_str + " (" + health + ")";
        }
        else if(bmi > 25){
            //Overweight
            health = "Overweight!";
            return bmi_str + " (" + health + ")";
        }
        else{
            //Healthy
            health = "Healthy!";
            return bmi_str + " (" + health + ")";
        }
    }

    public String getFinalFeedback(){

        //Compute
        computeBMI();

        if(age>18){
            return getFeedback(bmi);
        }else{
            return displayGuidance(bmi);
        }
    }

    public String getStatus(){

        //Compute
        computeBMI();

        //Check if user is an adult or not
        if(age>18){
            if(bmi<18.5){
                //Underweight
                return "Underweight";
            }
            else if(bmi > 25){
                //Overweight
                return "Overweight";
            }
            else{
                //Healthy
                return "Healthy";
            }
        }else{
            return "Need Consultancy";
        }
    }

    //Getter
    public String getBMI(){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String bmi_str = decimalFormat.format(bmi);
        return bmi_str;
    }
}
