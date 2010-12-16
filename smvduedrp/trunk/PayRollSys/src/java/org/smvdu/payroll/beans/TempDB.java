/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import org.smvdu.payroll.beans.db.CommonDB;

/**
 *
 * @author Algox
 */
public class TempDB {

    private PreparedStatement ps;

    public void save(int[] ids)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into temp values(?)");
            for(int id : ids)
            {
                ps.setInt(1, id);
                ps.executeUpdate();
                ps.clearParameters();
            }
            ps.close();
            c.close();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
