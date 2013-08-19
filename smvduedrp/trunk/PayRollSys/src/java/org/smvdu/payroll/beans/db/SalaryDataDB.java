/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.SalaryData;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.composite.SessionController;
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
public class SalaryDataDB {

    private PreparedStatement ps;
    private ResultSet rs;
    private UserInfo user;
    private SessionController sessionId = new SessionController();
    private int orgCode;
    public SalaryDataDB()  {
        //user = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        try {
            LoggedEmployee le = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
            if (le == null) {
                UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
                orgCode = uf.getUserOrgCode();
                System.out.println("DAta Should Be Write Here : 3214 : --" + orgCode);
            } else {
                orgCode = le.getUserOrgCode();
                System.out.println("DAta Should Be Write Here : 32142323 : " + orgCode);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public void autoLoad()
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from salary_data where sd_head_code=1 and "
                    + "month(sd_date)=? and year(sd_date)=?");
            ps.setInt(1, user.getCurrentMonth());
            ps.setInt(2, user.getCurrentYear());
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("insert into salary_data select emp_code,1,?,emp_basic,? from "
                    + "employee_master");
            ps.setString(1, user.getCurrentYear()+"-"+user.getCurrentMonth()+"-1");
            ps.setInt(2,1);
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("commit");
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }



    public void saveSummary(int income,int deduct,int net,String empCode)
    {
        try
        {
            UserInfo user = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            String date = user.getCurrentDate();
            String[] ds = date.split("-");
            int year = Integer.parseInt(ds[0]);
            int month = Integer.parseInt(ds[1]);
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from employee_salary_summery where es_code=? and es_month=? and es_year=? and es_org_id='"+orgCode+"' and es_sess_id = '"+sessionId.getCurrentSession()+"'");
            ps.setString(1, empCode);
            ps.setInt(2, month);
            ps.setInt(3, year);
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("insert into employee_salary_summery(es_code,es_month,es_year,"
                    + "es_total_income,es_total_deduct,es_gross,es_last_update_date,es_org_id,es_sess_id) values(?,?,?,?,?,?,date(now()),'"+orgCode+"','"+sessionId.getCurrentSession()+"')");
           
            ps.setString(1, empCode);
            ps.setInt(2, month);
            ps.setInt(3, year);
            ps.setInt(4, income);
            ps.setInt(5, deduct);
            ps.setInt(6, net);
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("commit");
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public boolean copyLastMonthData(String empCode,String date)   {
        String[] ds = date.split("-");
        int month = Integer.parseInt(ds[1]);
        int year=  Integer.parseInt(ds[0]);
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into salary_data (select sd_emp_code,sd_head_code,"
                    + "?,sd_amount from salary_data where sd_emp_code=? and month(sd_date)=? and year(sd_date)=? )");
            ps.setString(1, user.getCurrentDate());
            ps.setString(2, empCode);
            ps.setInt(3, month);
            ps.setInt(4, year);
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("commit");
            ps.executeUpdate();
            ps.close();
            c.close();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }


    
public ArrayList<SalaryData> loadInit(Employee empCode)    {
        try
        {
            CommonDB cdb = new CommonDB();
            Connection c = cdb.getConnection();
            ps=c.prepareStatement("select sh_id,sh_name,sh_type,0,sf_sal_formula,"
                    + "sh_alias,sh_scalable from emp_salary_head_master left join "
                    + "salary_head_master on sh_id = st_sal_code left join "
                    + "salary_formula  on sf_sal_id = sh_id  where  st_code = "
                    + "(select emp_type_code from employee_master where emp_code=?)");
            ps.setString(1,empCode.getCode());
            rs=ps.executeQuery();
            ArrayList<SalaryData> data = new ArrayList<SalaryData>();
            while(rs.next())
            {
                SalaryData sd = new SalaryData();
                sd.setHeadCode(rs.getInt(1));
                sd.setHeadName(rs.getString(2));
                sd.setCatagory(rs.getBoolean(3));
                sd.setHeadValue(rs.getInt(4));
                sd.setFormula(rs.getString(5));
                sd.setAlias(rs.getString(6));
                sd.setScalable(rs.getBoolean(7));
                if(sd.getHeadCode()==1)
                {
                    sd.setHeadValue(empCode.getCurrentBasic());
                }
                if(sd.getHeadCode()==2)
                {
                    sd.setHeadValue(empCode.getGradePay());
                }
                System.err.println(sd.getHeadName()+", "+sd.isScalable()+sd.getDefaultValue());
                data.add(sd);
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


    




    public ArrayList<SalaryData> loadCurrentSalaryData(Employee empCode)    {
        try
        {
            CommonDB cdb = new CommonDB();
            //System.out.println("Month Name : "+user.getCurrentMonth());
            UserInfo user = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            int month = user.getCurrentMonth();
            int year = user.getCurrentYear();
            Connection c = cdb.getConnection();
            ps=c.prepareStatement("select sh_id,sh_name,sh_type,sd_amount,sf_sal_formula,"
                    + "sh_alias,sh_scalable from emp_salary_head_master left join salary_head_master "
                    + "on sh_id = st_sal_code left join salary_data  on sh_id = sd_head_code"
                    + " left join salary_formula  on sf_sal_id = sh_id "
                    + " where sd_emp_code=? and month(sd_date)=? and year(sd_date)=?"
                    + " and st_code = (select emp_type_code from employee_master where emp_code=?)");
            ps.setString(1,empCode.getCode());
            ps.setInt(2, month);
            ps.setInt(3, year);
            ps.setString(4,empCode.getCode());
            rs=ps.executeQuery();
            ArrayList<SalaryData> data = new ArrayList<SalaryData>();
            while(rs.next())
            {
                SalaryData sd = new SalaryData();
                sd.setHeadCode(rs.getInt(1));
                sd.setHeadName(rs.getString(2));
                sd.setCatagory(rs.getBoolean(3));
                sd.setHeadValue(rs.getInt(4));
                sd.setFormula(rs.getString(5));
                sd.setAlias(rs.getString(6));
                sd.setScalable(rs.getBoolean(7));
                System.err.println(sd.getHeadName()+", "+sd.isScalable());
                data.add(sd);
            }
            rs.close();
            ps.close();
            c.close();
            if(data==null||data.isEmpty())
            {
                data=loadInit(empCode);
            }
            return data;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return loadInit(empCode);
        }
    }
    public ArrayList<SalaryData> loadSalaryData(String date)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sh_id,sh_name from salary_head_master ");
            rs=ps.executeQuery();
            ArrayList<SalaryData> data = new ArrayList<SalaryData>();
            while(rs.next())
            {
                SalaryData sd = new SalaryData();
                sd.setHeadCode(rs.getInt(1));
                sd.setHeadName(rs.getString(2));
                sd.setHeadValue(1000);
                data.add(sd);
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
    public ArrayList<SalaryData> loadRawData()    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sh_id,sh_name from salary_head_master ");
            rs=ps.executeQuery();
            ArrayList<SalaryData> data = new ArrayList<SalaryData>();
            while(rs.next())
            {
                SalaryData sd = new SalaryData();
                sd.setHeadCode(rs.getInt(1));
                sd.setHeadName(rs.getString(2));
                sd.setHeadValue(1000);
                data.add(sd);
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
    public boolean save(ArrayList<SalaryData> data,String empCode) {
        try
        {
            UserInfo user = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            SessionController ss = (SessionController) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("SessionController");
            SessionController session = new SessionController();
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from salary_data where sd_emp_code=? and "
                    + "month(sd_date)=? and year(sd_date)=?");
            ps.setString(1, empCode);
            String[] dd = user.getCurrentDate().split("-");
            System.out.println("Data is Here : "+user.getCurrentDate());
            ps.setInt(2, Integer.parseInt(dd[1]));
            ps.setInt(3, Integer.parseInt(dd[0]));
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("insert into salary_data values(?,?,?,?,?,?)");
            for(SalaryData sd : data)
            {
                ps.setString(1, empCode);
                ps.setInt(2, sd.getHeadCode());
                ps.setString(3,user.getCurrentDate());
                ps.setInt(4, sd.getHeadValue());
                ps.setInt(5, session.getCurrentSession());
                ps.setInt(6, orgCode);
                ps.executeUpdate();
                ps.clearParameters();
            }
            ps.close();
            ps=c.prepareStatement("commit");
            ps.executeUpdate();
            ps.close();
            c.close();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

}
