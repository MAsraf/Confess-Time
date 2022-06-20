/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author asraf
 */
public class DateValidatorFormat implements DateValidator {
    private String dateFormat;
    
    public DateValidatorFormat(String dateFormat){
        this.dateFormat = dateFormat;
    }

    @Override
    public Boolean isValid(String date) {
        DateFormat df = new SimpleDateFormat(this.dateFormat);
        df.setLenient(false);
        try{
            df.parse(date);
        }catch(ParseException e){
            return false;
        }
        return true;
    }
    
   
}   

