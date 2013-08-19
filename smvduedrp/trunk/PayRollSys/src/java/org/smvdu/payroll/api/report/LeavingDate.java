/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.report;

/**
 *
 * @author ERP
 */
public class LeavingDate {

    public String leavingDate(String regdate,int notfica)
    {
        try
        {
            String notfic = String.valueOf(notfica);
            if(regdate.equals("") == true || notfic.equals("") == true)
            {
                return "";
            }
            String[] s=regdate.split("-");
            int rd = Integer.parseInt(s[2]);
            int rm = Integer.parseInt(s[1]);
            int ry = Integer.parseInt(s[0]);
            int rm1 = 0;
            int not =Integer.parseInt(notfic);
            String leaving = new String();
            int month = not % 30;
            int month1 = not / 30;
            int kl =21;
            int difrd=0;
            int crm = 0;
            int rm2;
            difrd = rd + month;
            if(difrd < 31 && difrd > 0)
            {
                rd = difrd;
            }
            else
            {
                rd = difrd -30;
                rm = rm+1;
            }
            for(int i=0; i <month1 ; i++)
            {
                rm++;
                if(rm > 12)
                {
                    rm1 =rm1+1;
                }
            }
            rm2 = rm;
            rm = rm1;
            crm = rm2 / 12;
            if(rm2 > 12)
            {
                for(int y = 0;y < crm; y++)
                {
                    ry++;
                }
            }
            else
            {
                rm =rm2;
            }
            leaving = ry+"-"+rm+"-"+rd;
            return leaving;
            //System.out.println("Year : "+ry+" Month : "+rm+" Date : "+rd);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
