/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.annualreport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.BaseBean;
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
public class TdsReport {


    private ArrayList<BaseBean> data = new ArrayList<BaseBean>();

    private PreparedStatement ps;
    private ResultSet rs;
    private float netTotal;

    public float getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(float netTotal) {
        this.netTotal = netTotal;
    }



    private String empCode;

    public ArrayList<BaseBean> getData() {
        return data;
    }

    public void setData(ArrayList<BaseBean> data) {
        this.data = data;
    }

    public String getEmpCode() {
        LoggedEmployee bean = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        if(bean!=null)
        {
            empCode = bean.getProfile().getCode();
            loadReport();
        }
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }





    public void loadReport()
    {
        new MonthManager().init();
        
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select date_format(mm_month,'%M-%y'),sd_amount from "
                    + "month_master left join salary_data on month(sd_date) = month(mm_month) "
                    + "and year(sd_date) = year(mm_month) and sd_head_code=13 and sd_emp_code=? ");
            ps.setString(1, empCode);
            rs = ps.executeQuery();
            while(rs.next())
            {
                BaseBean bb = new BaseBean();
                bb.setName(rs.getString(1));
                bb.setBaseAmount(rs.getFloat(2));
                netTotal+=bb.getBaseAmount();
                data.add(bb);
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}
