/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.InvestmentMaster;

/**
 *
 * @author Algox
 */
public class InvestmentMasterDB {

    private PreparedStatement ps;
    private ResultSet rs;


    public ArrayList<InvestmentMaster> load()  {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from investment_category_master");
            rs=ps.executeQuery();
            ArrayList<InvestmentMaster> data = new ArrayList<InvestmentMaster>();
            while(rs.next())
            {
                InvestmentMaster im = new InvestmentMaster();
                im.setId(rs.getInt(1));
                im.setName(rs.getString(2));
                data.add(im);
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

}
