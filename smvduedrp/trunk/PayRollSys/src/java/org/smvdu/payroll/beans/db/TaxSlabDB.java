/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.db;

import java.sql.*;
import java.util.ArrayList;

import org.smvdu.payroll.taxslabs.TaxType;

/**
 *
 * @author smvdu
 */
public class TaxSlabDB {

    /** Creates a new instance of TaxSlabDB */
    private PreparedStatement ps;
    private ResultSet rs;

    public Exception update(ArrayList<TaxType> data) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("update taxslabs_master set ts_name=?, ts_start=?,"
                    + "ts_end=?,ts_percent=? where ts_id=?");
            for (TaxType tt : data) {
                System.out.println("code 1: " + tt.getName());
                System.out.println("code 1: " + tt.getStart());
                System.out.println("code 2 : " + tt.getEnd());
                System.out.println("code 3 : " + tt.getTaxpercent());
                System.out.println("code 4 : " + tt.getCode());
                ps.setString(1, tt.getName());
                ps.setString(2, tt.getStart());
                ps.setString(3, tt.getEnd());
                ps.setDouble(4, tt.getTaxpercent());
                ps.setInt(5, tt.getCode());
                ps.executeUpdate();
                ps.clearParameters();
            }
            ps.close();
            c.close();
            return null;
        } catch (Exception e) {
            return e;
        }
    }

    public ArrayList<TaxType> load() {
        try {

            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select * from taxslabs_master");
            rs = ps.executeQuery();
            ArrayList<TaxType> data = new ArrayList<TaxType>();

            while (rs.next()) {

                TaxType tt = new TaxType();


                tt.setCode(rs.getInt(1));
                tt.setStart(rs.getString(2));
                tt.setEnd(rs.getString(3));
                tt.setTaxpercent(rs.getInt(4));
                tt.setName(rs.getString(6));
                tt.setGender(rs.getString(5));
                data.add(tt);

            }
            return data;
        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }
    }

    public Exception save(TaxType tte) {
        try {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            int gen=0;
            //cn.clearWarnings();
            System.out.println(" ");
            if (tte.getGender().equals("Male") == true) {
                gen=0;
            }
            if (tte.getGender().equals("Female") == true) {
                gen=1;
            } else {
                gen=2;
            }
            pst = cn.prepareStatement("insert into taxslabs_master(ts_start,"
                    + "ts_end,ts_percent,ts_gender,ts_name) values('"+ tte.getStart().toUpperCase()+"','"+tte.getEnd()+"','"+tte.getTaxpercent()+"','"+gen+"','"+tte.getName()+"')");
            pst.executeUpdate();
            pst.close();
            cn.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
    }
}
