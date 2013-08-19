/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.UserInfo;
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

    private PreparedStatement ps,ps1;
    private ResultSet rs,rs1;
    private UserInfo userBean;

    public SalaryCopierDB() {
        userBean = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
    }
    
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
            DateFormat dateFormat;
            dateFormat = new SimpleDateFormat("yy-MM-dd");
            java.util.Date date;
            date =(java.util.Date) dateFormat.parse(to);
            String[] bits = from.split("-");
            ps = c.prepareStatement("delete from salary_data where MONTH(sd_date) = MONTH('"+to+"') and year(sd_date)=year('"+to+"') and org_code='"+userBean.getUserOrgCode()+"'");
            int i=ps.executeUpdate();
            if(i>0)
            {
                System.out.println("Data Deleted");
            }
            ps=c.prepareStatement("select *from salary_data where month(sd_date) = '"+bits[1]+"' and year(sd_date) = '"+bits[0]+"' and org_code='"+userBean.getUserOrgCode()+"'");
            rs=ps.executeQuery();
            while(rs.next())
            {
                ps1=c.prepareStatement("insert into salary_data values('"+rs.getInt(1)+"','"+rs.getInt(2)+"','"+ new java.sql.Date(date.getTime())+"','"+rs.getInt(4)+"','"+sessionController.getCurrentSession()+"','"+userBean.getUserOrgCode()+"')");
                ps1.executeUpdate();
                ps1.close();
            }
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
