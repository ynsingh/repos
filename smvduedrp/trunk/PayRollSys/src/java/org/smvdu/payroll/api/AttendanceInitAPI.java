/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.module.attendance.LoggedEmployee;

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
public class AttendanceInitAPI {

private PreparedStatement ps;
private ResultSet rs;
private LoggedEmployee bean;

    private void populate()  {
        try
        {
            CommonDB cdb = new CommonDB();
            int month = cdb.getMonth();
            int year = cdb.getYear();
            String currentDate = cdb.getFirstDay(bean.getCurrentDate());
            int total = cdb.getDateDiff(cdb.getFirstDay(bean.getCurrentDate()), cdb.getLastDate(bean.getCurrentDate()));
            Connection c = new CommonDB().getConnection();
            System.out.println("Number of Days : "+total);
            ps=c.prepareStatement("insert into date_master (select date_add(?,INTERVAL ? DAY),?,?)");
            for(int x=0;x<=total;x++)
            {
                ps.setString(1, currentDate);
                ps.setInt(2, x);
                ps.setInt(3, month);
                ps.setInt(4, year);
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
    public void init()  {
        bean = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        try
        {
            Connection c = new CommonDB().getConnection();
            ps= c.prepareStatement("select t_date from date_master where "
                    + "t_month=? and t_year=?");
            ps.setInt(1, bean.getCurrentMonth());
            ps.setInt(2, bean.getCurrentYear());
            rs = ps.executeQuery();
            if(rs.next())
            {
                rs.close();
                ps.close();
                c.close();
                return;
            }
            System.out.println("Month not initialized. Starting now ...");
            populate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
