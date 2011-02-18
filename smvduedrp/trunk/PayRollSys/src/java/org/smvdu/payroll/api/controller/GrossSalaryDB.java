/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.api.MonthlyIndividualGrossSalary;
import org.smvdu.payroll.beans.db.CommonDB;

/**
 *
 * @author Algox
 */
public class GrossSalaryDB {

    private PreparedStatement ps;
    private ResultSet rs;

//    public ArrayList<MonthlyIndividualGrossSalary> loadGross(String empCode) {
//        try
//        {
//            Connection c = new CommonDB().getConnection();
//            ps=c.prepareStatement("select sum()");
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//            return null;
//        }
//    }

}
