/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.UserTask;

/**
 *
 * @author KESU
 */
public class UserStringBuilder {
     public String stringBuilder(String newString)
    {
        try
        {
            StringBuilder b = new StringBuilder();
            char c;
            for(int i=0;i<newString.length();i++)
            {
                c=newString.charAt(i);
                int j = c;
                if((j>=65 && j<=90) || (j>=97 && j<=122))
                {
                    b.append(c);
                }
            }
            return b.toString();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
