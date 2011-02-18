/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.UserGroup;

/**
 *
 * @author Algox
 */
public class UserGroupDB {

    private PreparedStatement ps;
    private ResultSet rs;
    public ArrayList<UserGroup> load()  {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from user_group_master");
            rs =ps.executeQuery();
            ArrayList<UserGroup> data = new ArrayList<UserGroup>();
            while(rs.next())
            {

            }
            rs.close();
            ps.close();
            c.close();
            return data;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public Exception save(String ug)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into user_group_master(grp_name) values(?)");
            ps.setString(1, ug);
            ps.executeUpdate();
            ps.close();
            c.close();
            return null;
        }
        catch(Exception e)
        {
            return e;

        }
    }

}
