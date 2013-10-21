/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;

/**
 *
 * @author sknaqvi
 */
public class DateUtilities {

    public boolean isValidDate(String inDate) {

        if (inDate == null) {
            return false;
        }

        //set the format to use as a constructor argument
       /* SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        if (inDate.trim().length() != dateFormat.toPattern().length()) {
            return false;
        }

        dateFormat.setLenient(false);

        try {
            //parse the inDate parameter
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;*/
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat dateFormat3 = new SimpleDateFormat("ddMMyyyy");

        //System.out.println(""+inDate.trim().length());
        //System.out.println(""+dateFormat.toPattern().length());
        //System.out.println(""+dateFormat1.toPattern().length());
       // System.out.println(""+dateFormat2.toPattern().length());
      if (inDate.trim().length() == dateFormat.toPattern().length()) {
        	 dateFormat.setLenient(false);
            // System.out.println("1");

             try {
                 //parse the inDate parameter
               

                 dateFormat.parse(inDate.trim());
                 return true;
             } catch (ParseException pe) {
                
             }
            
        }
        System.out.println(""+inDate.trim().length());

      if (inDate.trim().length() == dateFormat1.toPattern().length()) {
    	  dateFormat1.setLenient(false);
            // System.out.println("2");

             try {
                 //parse the inDate parameter
                 dateFormat1.parse(inDate.trim());
                 return true;
             } catch (ParseException pe) {
                
             }
            
        }
      if (inDate.trim().length() == dateFormat2.toPattern().length()) {
    	  dateFormat2.setLenient(false);
            // System.out.println("3");

             try {
                 //parse the inDate parameter
                 dateFormat2.parse(inDate.trim());
                 return true;
             } catch (ParseException pe) {
             }
           
        
        
        }
      if (inDate.trim().length() == dateFormat3.toPattern().length()) {
    	  dateFormat3.setLenient(false);
         // System.out.println("8");

          try {
              //parse the inDate parameter
        	  dateFormat3.parse(inDate.trim());
              return true;
          } catch (ParseException pe) {
          }
        
     
     
     }
        return false;
       
    }

    public Date convertStringToDate(String inDate) throws Exception {
        try {
            if (inDate.contains("/")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return dateFormat.parse(inDate);
            }
            if (inDate.contains("-")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                return dateFormat.parse(inDate);
            }
            if (inDate.contains(".")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                return dateFormat.parse(inDate);
            }
            if (inDate.length()==8) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
                return dateFormat.parse(inDate);
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String convertDateToString(Date inDate, String format) throws Exception {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(inDate);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean isFirstDateBeforeSecond(Date firstDate, Date secondDate) throws Exception {
        try {
            if (firstDate.before(secondDate)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
