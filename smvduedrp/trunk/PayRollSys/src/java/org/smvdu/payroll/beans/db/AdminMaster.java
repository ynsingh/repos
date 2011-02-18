/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Algox
 */
public class AdminMaster {

    private String password = "";
    private PreparedStatement ps;
    private ResultSet rs;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void validate()
    {
        try
        {
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
