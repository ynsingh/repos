/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.validator;

import java.sql.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.smvdu.payroll.beans.db.CommonDB;

/**
 *
 * @author Algox
 */
public class DateValidator implements Validator{


    public DateValidator()
    {
        System.err.println("Date Validator Active .");
    }
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
       String mydate = (String)value;
       try
       {
            Date dt = Date.valueOf(mydate);
            String[] ddd = mydate.split("-");
            int year = Integer.parseInt(ddd[0]);
            int month = Integer.parseInt(ddd[1]);
            int date = Integer.parseInt(ddd[2]);
            int od = new CommonDB().getYear();
            if(year<1900||year>od)
            {
                throw new Exception("Invalide Year. ");
            }
            if(month<1||month>12)
            {
                throw new Exception("Invalid Month. ");
            }
            if(date<1||date>31)
            {
                throw new Exception("Invalide Day. ");
            }
            System.err.println(dt);
       }
       catch(Exception e)
       {
            FacesMessage message = new FacesMessage();
            message.setSummary(e.getMessage() + ".Input Date as yyyy-mm-dd");
            throw new ValidatorException(message);
       }
   
    }

}
