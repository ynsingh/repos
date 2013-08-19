/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.composite;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.faces.bean.*;

/**
 *
 * @author smvduerp
 */
@ManagedBean
@SessionScoped
public class ActivationDeactivationMessage {

    /** Creates a new instance of ActivationDeactivationMessage */
    public ActivationDeactivationMessage() {
    }
    public String salaryMessage(String re)
    {
        try
        {
            //String re = "2012-04-17";
            String message = null;
            Calendar currentDate = new GregorianCalendar();
            int month = currentDate.get(Calendar.MONTH);
            int month1 = currentDate.get(Calendar.YEAR);
            int month11 = currentDate.get(Calendar.DAY_OF_MONTH);
            String[] s1 = re.split("-");
            //System.out.println("Hello World : "+(month1+"-"+(month+1)+"-"+month11));
            String s3 = month1+"-0"+(month+1)+"-"+month11;
            String[] s2 = s3.split("-");
            if((s1[0].equals(s2[0]) == true))
            {
                if(s1[1].equals(s2[1]) == true)
                {
                    if(s1[2].equals(s2[2]) == true)
                    {
                        message = "Sorry....";
                    }
                }
            }
            else
            {
                message = "ok......";
            }
            return message;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
