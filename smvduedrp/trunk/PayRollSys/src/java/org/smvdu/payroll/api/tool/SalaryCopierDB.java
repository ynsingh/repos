/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.composite.SessionController;
import org.smvdu.payroll.beans.db.CommonDB;

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
public class SalaryCopierDB {

    private PreparedStatement ps;
    private ResultSet rs;

    public ArrayList<SalaryCopy> loadDates(int year)  {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select distinct date_format(sd_date,'%M-%y')"
                    + ",month(sd_date),year(sd_date) from salary_data");
            rs=ps.executeQuery();
            ArrayList<SalaryCopy> data = new ArrayList<SalaryCopy>();
            while(rs.next())
            {
                SalaryCopy sc = new SalaryCopy();
                sc.setDate(rs.getString(1));
                sc.setRealDate(rs.getInt(3)+"-"+rs.getInt(2)+"-1");
                data.add(sc);
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
    public Exception copyData(String from,String to)  {
        SessionController sessionController = (SessionController) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("SessionController");
        try
        {
            Connection c = new CommonDB().getConnection();

            ps=c.prepareStatement("delete from salary_data where month(sd_date)=month('"+to+"') and "
                    + "year(sd_date) = year('"+to+"') and sd_sess_id = ?");
            ps.setInt(1, sessionController.getCurrentSession());
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("insert into salary_data (select sd_emp_code,"
                    + "sd_head_code,?,sd_amount,? from salary_data where "
                    + "month(sd_date)=? and year(sd_date)=?)");
            String[] bits = from.split("-");
            ps.setString(1, to);
            ps.setInt(2, Integer.parseInt(bits[1]));
            ps.setInt(3, Integer.parseInt(bits[0]));
            ps.setInt(4, sessionController.getCurrentSession());
            ps.executeUpdate();
            ps.close();
            c.close();
            return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return e;
        }
    }
}
