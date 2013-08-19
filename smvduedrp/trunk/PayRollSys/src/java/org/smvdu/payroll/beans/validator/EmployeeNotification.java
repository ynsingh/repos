/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.validator;


/**
 *
 * @author ERP
 */
public class EmployeeNotification {

    public String resignationNotification(String dateOfJoining,String dateOfResig)
    {
        try
        {
            //String s = new String();
            if(dateOfJoining.equals("") == true || dateOfResig.equals("") == true)
            {
                return "";
            }
            String[] doj1 = dateOfJoining.split("-");
            String[] dor1 = dateOfResig.split("-");
            int yearCountShow= Integer.parseInt(doj1[0]);
            int yearCountShow1 = 0;
            int monthCountShow = 0;
            int monthCountShow1 = 0;
            if(yearCountShow == Integer.parseInt(dor1[0]))
            {
                monthCountShow = Integer.parseInt(doj1[1]);
                while(monthCountShow != Integer.parseInt(dor1[1]))
                {
                    monthCountShow++;
                    monthCountShow1++;
                }
            }
            while(yearCountShow != Integer.parseInt(dor1[0]))
            {
                yearCountShow++;
                yearCountShow1++;
                if(yearCountShow == Integer.parseInt(dor1[0]))
                {
                    if(Integer.parseInt(doj1[1]) < Integer.parseInt(dor1[1]))
                    {
                        monthCountShow =0;
                        monthCountShow1 =0;
                        monthCountShow = Integer.parseInt(doj1[1]);
                        while(monthCountShow == Integer.parseInt(dor1[1]))
                        {
                            monthCountShow++;
                            monthCountShow1++;
                        }
                    }
                    if(Integer.parseInt(doj1[1]) > Integer.parseInt(dor1[1]))
                    {
                        monthCountShow =0;
                        monthCountShow1 =0;
                        monthCountShow = Integer.parseInt(dor1[1]);
                        while(monthCountShow > 1)
                        {
                            monthCountShow--;
                            monthCountShow1++;
                        }
                        monthCountShow1++;
                    }
                    else
                    {
                        monthCountShow1++;
                    }
                }
            }
            System.out.println("Total Number Of Year : "+yearCountShow1+" , Total Number Of Month : "+monthCountShow1);
            return "Total Number Of Year : "+yearCountShow1+" , Total Number Of Month : "+monthCountShow1;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
