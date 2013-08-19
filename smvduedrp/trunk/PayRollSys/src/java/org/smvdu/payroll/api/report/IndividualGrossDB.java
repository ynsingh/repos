/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.SalaryHeadDB;
import org.smvdu.payroll.beans.setup.SalaryHead;

/**
 *
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
 */
public class IndividualGrossDB {

    private PreparedStatement ps;
    private ResultSet rs;

   
    public Integer[][] fetchSummary(String empCode)
    {
        Integer[][] dataset = new Integer[3][13];
        int month=4;
        int year=2010;
        int t1=0;
        int t2=0;
        int t3=0;

        try
        {
            Connection c = new CommonDB().getConnection();
            for(int i=0;i<12;i++)
            {

                ps=c.prepareStatement("select emp_name,es_total_income,es_total_deduct,es_gross from "
                        + "employee_master left join employee_salary_summery on es_code = emp_code "
                        + "and  es_month=? and es_year=? where emp_code=?");
                ps.setInt(1, month);
                ps.setInt(2, year);
                ps.setString(3, empCode);
                rs=ps.executeQuery();
                while(rs.next())
                {
                    dataset[0][i] = rs.getInt(2);
                    dataset[1][i] = rs.getInt(3);
                    dataset[2][i] = rs.getInt(4);
                    t1+=rs.getInt(2);
                    t2+=rs.getInt(3);
                    t3+=rs.getInt(4);
                }
                month++;
                if(month==13)
                {
                    month=1;
                    year++;
                }
                rs.close();
                ps.close();
            }
            dataset[0][12] = t1;
            dataset[1][12] = t2;
            dataset[2][12] = t3;
        }
        catch(Exception e)
        {
            
        }
        return dataset;
    }



    public Integer[][] fetchSalaryMatrix(String empCode) {
        ArrayList<String> sals = new SalaryHeadDB().getAllHeadAsString();
        Integer[][] dataset = new Integer[sals.size()][12];
        String startDate = "2011-4-1";
        int dateOffset = 0;
        try {
            Connection c = new CommonDB().getConnection();
            for (int i = 0; i < 12; i++) {
                ps = c.prepareStatement("select sd_amount,sd_date from salary_head_master "
                        + " left join salary_data on sd_head_code =sh_id and sd_emp_code=?"
                        + " and month(sd_date)=(select month(adddate(?,?)) ) and year(sd_date) = "
                        + "(select year(adddate(?,?))) order by sh_id");
                ps.setString(1, empCode);
                ps.setString(2, startDate);
                ps.setInt(3, dateOffset);
                ps.setString(4, startDate);
                ps.setInt(5, dateOffset);
                rs = ps.executeQuery();
                int k = 0;
                while (rs.next()) {
                    dataset[k][i] = rs.getInt(1);
                    k++;
                }
                rs.close();
                ps.close();
                dateOffset += 31;
            }
            c.close();
            System.out.println("Total Rows : " + dataset.length);
            return dataset;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
