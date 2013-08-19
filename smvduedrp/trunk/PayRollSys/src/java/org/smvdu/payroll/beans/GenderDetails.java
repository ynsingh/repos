/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

/**
 *
 * @author EDRP
 */
public class GenderDetails {
     public boolean gen(String dateOfBirth,String dateOfJoining)
    {
        try
        {
            int gen = 0;
            String[] dobs = dateOfBirth.split("-");
            String[] dojs = dateOfJoining.split("-");
            int citizenYear;
            int dateDiff = Integer.parseInt(dojs[0]) - Integer.parseInt(dobs[0]);
            int monthCounter;
            if(dateDiff >=62)
            {
                citizenYear = dateDiff;
                return true;
            }
            else
            {
                citizenYear = dateDiff;
                int month = 0;
                int monthDiff = Integer.parseInt(dobs[1]) - Integer.parseInt(dojs[1]);
                monthCounter = Integer.parseInt(dobs[1]);
                if(monthDiff <= 0)
                {
                    while(monthCounter !=12)
                    {
                        month++;
                        monthCounter++;
                    }
                    monthCounter = Integer.parseInt(dojs[1]);
                    while(monthCounter !=0)
                    {
                        month++;
                        monthCounter--;
                    }
                    month++;
                    System.out.println(""+month);
                }
                if(monthDiff >= 0)
                {
                    while(monthCounter !=12)
                    {
                        month++;
                        monthCounter++;
                    }
                    monthCounter = Integer.parseInt(dojs[1]);
                    while(monthCounter !=0)
                    {
                        month++;
                        monthCounter--;
                    }
                    month++;
                    System.out.println(""+month);
                }
                if(month >=12)
                {
                    citizenYear++;
                }
            }
            if(citizenYear >=62)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
}
