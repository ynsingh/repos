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

    if (inDate == null)
      return false;

    //set the format to use as a constructor argument
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    if (inDate.trim().length() != dateFormat.toPattern().length())
      return false;

    dateFormat.setLenient(false);

    try {
      //parse the inDate parameter
      dateFormat.parse(inDate.trim());
    }
    catch (ParseException pe) {
      return false;
    }
    return true;
  }

  public Date convertStringToDate(String inDate) throws Exception {
       try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
          return dateFormat.parse(inDate);
      }
    catch (Exception e) {
      throw e;
    }
  }

  public String convertDateToString(Date inDate, String format) throws Exception {
       try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(inDate);
      }
    catch (Exception e) {
      throw e;
    }
  }

  public boolean isFirstDateBeforeSecond(Date firstDate,Date secondDate) throws Exception {
      try {
            if(firstDate.before(secondDate))
                return true;
            else
                return false;
    }
        catch (Exception e) {
            throw e;
    }
  }
}
