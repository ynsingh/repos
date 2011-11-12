/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.EmployeeDB;

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
public class MonthlyGrossDB {
    private PreparedStatement ps;
    private ResultSet rs;

//    public int[][] getMonthlyRoll(int typeCode,String date)
//    {
//        String[] ds = date.split("-");
//        int year = Integer.parseInt(ds[0]);
//        int month = Integer.parseInt(ds[0]);
//        try
//        {
//            Connection c = new CommonDB().getConnection();
//            for(Employee emp : new EmployeeDB().loadProfiles(" where emp_type_code = "+typeCode))
//            {
//            ps=c.prepareStatement("select sd_amount from salary_head_master "
//                + " left join salary_data on sd_head_code =sh_id and sd_emp_code=?"
//                + " and month(sd_date)=? and year(sd_date) =? "
//                + " where sh_id in (select st_sal_code from emp_salary_head_master "
//                + "where st_code =  (select emp_type_code from employee_master "
//                + "where emp_code=?) ) order by sh_id ");
//
//            }
//
//        }
//        catch(Exception e)
//        {
//
//        }
//    }



}
