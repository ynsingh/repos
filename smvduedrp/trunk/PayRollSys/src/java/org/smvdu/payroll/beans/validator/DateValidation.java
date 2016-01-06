/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.validator;

/**
 *
 * @author ERP
 */
public class DateValidation {

   public boolean dateOfBirthValidation(String dateOfBirth,String dateOfJoining,String dateOfReg)
    {
        try
        {
            boolean flag = false;
            String[] bit = dateOfBirth.split("-");
            String[] bit1 = dateOfJoining.split("-");
            int dateValue = Integer.parseInt(bit1[0]) - Integer.parseInt(bit[0]);
            System.out.println("Date Of Birth : "+dateOfBirth);
            //flag = new DateValidation().dateOfResiValidation(dateOfBirth, dateOfJoining, dateOfReg);
            if((((Integer.parseInt(bit1[0])-Integer.parseInt(bit[0]))<18) ||
                    ( Integer.parseInt(bit1[0]) == Integer.parseInt(bit[0]))))
            {
             return false;
            }
            else
            {
                return true;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean dateOfResiValidation(String dateOfBirth,String dateOfJoining,String dateOfResi)
    {
        try
        {
            String[] dor = dateOfResi.split("-");
            String[] dob = dateOfBirth.split("-");
            String[] doj = dateOfJoining.split("-");
            if((Integer.parseInt(dor[0]) == Integer.parseInt(dob[0])) ||
                    (Integer.parseInt(dor[2]) == Integer.parseInt(doj[2])) ||
                    ((Integer.parseInt(dor[0])-Integer.parseInt(dob[0]))<23) ||
                    (Integer.parseInt(doj[0])) > Integer.parseInt(dor[0]))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
}
