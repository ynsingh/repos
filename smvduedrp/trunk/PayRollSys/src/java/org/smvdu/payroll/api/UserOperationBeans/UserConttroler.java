/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.UserOperationBeans;

import java.util.ArrayList;

/**
 *
 * @author ERP
 */
public class UserConttroler {

    public ArrayList<UserBeans> getSuggestion(Object obj)
    {
        try
        {
            return new UserBeanDB().getUserId(obj);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
