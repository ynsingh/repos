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
import javax.faces.application.FacesMessage;
import org.smvdu.payroll.beans.ConfigSalLiabilityXml;
import org.smvdu.payroll.beans.composite.NewSalaryProcessing;
import org.smvdu.payroll.beans.setup.SalaryGrade;

/**
 *
 *  Copyright (c) 2010 - 2011 SMVDU, Katra.
 *  Copyright (c) 2014 - 2017 ETRG, IITK.
*   All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*   without modification, are permitted provided that the following 
*   conditions are met: 
**  Redistributions of source code must retain the above copyright 
*   notice, this  list of conditions and the following disclaimer. 
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
*  Modified Date: 02 Dec 2013, IITK (palseema@rediffmail.com, kshuklak@rediffmail.com)
*  Last Modification :(Salary Processing with Budgets), January 2017, Manorama Pal (palseema30@gmail.com).
*  Last Modification :(Salary Processing), 14 April 2017, Manorama Pal (palseema30@gmail.com).
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
                
            } else {
                orgCode = le.getUserOrgCode();
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public void autoLoad()
    {
        user = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        SessionController sessionId = new SessionController();
        try
        {
            String filePath=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/salary/SalaryConfiguration.xml");
            String value= ConfigSalLiabilityXml.getKeyValue(filePath, "SalaryLiability", Integer.toString(orgCode));
            String date = user.getCurrentDate();
            if(value != null){
                if(value.equals(date)){
                    
                    Connection c = new CommonDB().getConnection();
                    ps=c.prepareStatement("delete from salary_data " 
                    + " where month(sd_date)=? and year(sd_date)=?");
                    ps.setInt(1, user.getCurrentMonth());
                    ps.setInt(2, user.getCurrentYear());
                    ps.executeUpdate();
                    ps.close();
                    //insert data of all employee in salarydata according to payband, salary head and salary liability
                    loadDefaultHeadValues();
                    FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Salary Generated successfully", ""));
                }
                else{
                    FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Date is over for Salary Generation for this month ... ", ""));
                }
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Please configure salary liability date ... ", ""));
            } 
            
          
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


    
    public ArrayList<SalaryData> loadInit(Employee emp){
        try
        {
        
            ArrayList<SalaryData> forvalues=LoadValuesWithFormula(emp);
            ArrayList<SalaryData> combinedData = new ArrayList<SalaryData>();
            for(SalaryData sdv : forvalues)
            {
                SalaryData sd = new SalaryData();
                sd.setHeadCode(sdv.getHeadCode());
                sd.setHeadName(sdv.getHeadName());
                sd.setCatagory(sdv.isCatagory());
                int value=loadDefaultHeadValues(emp,sdv.getHeadCode());
                //sd.setHeadValue(sdv.getHeadValue());
                sd.setHeadValue(value);
                sd.setFormula(sdv.getFormula());
                sd.setAlias(sdv.getAlias());
                sd.setScalable(sdv.isType());
                combinedData.add(sd);
            
            }
            //System.out.println("load init method seem4===="+combinedData.size());
            return combinedData;
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
            UserInfo user = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            int month = user.getCurrentMonth();
            int year = user.getCurrentYear();
            Connection c = cdb.getConnection();
                ps=c.prepareStatement("select sh_id,sh_name,sh_type,sd_amount,sf_sal_formula,"
                    + "sh_alias,sh_scalable from emp_salary_head_master left join salary_head_master "
                    + "on sh_id = st_sal_code left join salary_data  on sh_id = sd_head_code "
                    + "left join salary_formula  on sf_sal_id = sh_id "
                    + "where sd_emp_code=? and month(sd_date)=? and year(sd_date)=? "
                    + "and st_code = (select emp_type_code from employee_master where emp_code=?) order by sh_id");
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
                //System.err.println(sd.getHeadName()+", "+sd.isScalable());
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

public ArrayList<SalaryGrade> loadEmpgrdPay(int empcode){
	try
        {
            Connection c = new CommonDB().getConnection();
            //ps=c.prepareStatement("select grd_gp from salary_grade_master where grd_code=? ");
            ps=c.prepareStatement("select grd_gp from salary_grade_master "
            + " left join employee_master on emp_salary_grade=grd_code"
            +"and grd_code='(select emp_salary_grade from employee_master where emp_code=?)'");
            ps.setString(1, Integer.toString(empcode));
            rs=ps.executeQuery();
            ArrayList<SalaryGrade> grades = new ArrayList<SalaryGrade>();
            while(rs.next())
            {

                SalaryGrade sg = new SalaryGrade();
                //sg.setCode(rs.getInt(1));
                sg.setGradePay(rs.getInt(1));
                grades.add(sg);
            }
            rs.close();
            ps.close();
            c.close();
            //System.out.println("CopyData==up=emppay= "+grades);
            return grades;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


	public int loadEmpgrdcode(String empcode) {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select emp_salary_grade from employee_master where emp_code=?");
            ps.setString(1, empcode);
            rs=ps.executeQuery();
            rs.next();
            int GPcode= rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            
            return GPcode;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return -1;
            
        }
    }
        
    public void updateGradePay(){
        try
        {
            UserInfo user = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select emp_code,emp_salary_grade from "
            + "employee_master where emp_org_code ='"+user.getUserOrgCode()+"' ");
            rs=ps.executeQuery();   
            while(rs.next())
            {
                
                SalaryGradeDB sgdb=new SalaryGradeDB();
                ArrayList<SalaryGrade> salgpdata=sgdb.load();
                for(SalaryGrade sgp : salgpdata)
                {
                    ps=c.prepareStatement("update salary_data set sd_amount=?"
                    + " where sd_emp_code=? and sd_head_code=?");
                              
                    if(sgp.getCode()==rs.getInt(2)){
                        ps.setInt(1,sgp.getGradePay());
                        ps.setString(2,rs.getString(1));
                        ps.setInt(3,2);
                        ps.executeUpdate();
                        ps.clearParameters();
                    }
                }
            } 
            rs.close();
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
           
        }
    }
         //changes for salaryProccessing to bgas-------------------    
    public ArrayList<Employee> loadAllEmpCode(){
        try
        {
             Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select emp_code, emp_basic, grd_gp, emp_salary_grade  from employee_master "
                +"left join salary_grade_master on grd_code = emp_salary_grade where emp_org_code='"+orgCode+"'");
            rs=ps.executeQuery();
            //System.err.println("rs====="+rs);
            ArrayList<Employee> data = new ArrayList<Employee>();
            while(rs.next())
            {
                Employee emp = new Employee();
                emp.setCode(rs.getString(1));
                emp.setCurrentBasic(rs.getInt(2));
                emp.setGradePay(rs.getInt(3));
                data.add(emp);
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
    
    /**
     * Load and insert  data in table for generate salary of employees
     */
    public void loadDefaultHeadValues(){
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select emp_code,emp_basic,emp_type_code, emp_salary_grade from "
            + "employee_master where emp_org_code ='"+user.getUserOrgCode()+"' ");
            rs=ps.executeQuery();   
            while(rs.next())
            { 
                Employee emp=new Employee();
                emp.setCode(rs.getString(1)); 
                emp = new EmployeeDB().loadProfile(rs.getString(1),orgCode);
                              
                //System.out.println("detail=="+rs.getString(1)+"grade===="+rs.getInt(3)+","+emp.getCode()+","+emp+",empbasic=="+rs.getString(2));
                ArrayList<SalaryData> salhead = new SalaryHeadDB().CombineDataofselectHead(rs.getInt(3),rs.getInt(4),emp);
                  
                for(SalaryData sh : salhead)
                {   
                   
                    ps=c.prepareStatement("insert into salary_data values(?,?,?,?,?,?)");
                    //System.out.println("shcode=currentsalaryheadcode=="+sh.getHeadCode()+"ddcode==="+rs.getInt(2)+"ddvalue==="+rs.getString(1)+"default value"+sh.getHeadValue());
                    ps.setString(1,rs.getString(1));
                   // ps.setInt(2,sh.getNumber());
                    ps.setInt(2,sh.getHeadCode());
                    ps.setString(3, user.getCurrentDate());
                    if(sh.getHeadCode()== 1 ||sh.getHeadCode()== 2 ){
                        if(sh.getHeadCode()== 1){
                            if(rs.getInt(2)== 0)
                            ps.setInt(1,sh.getHeadValue());
                            else
                            ps.setInt(4,rs.getInt(2));
                        }  
                        if(sh.getHeadCode() == 2){
                            if(emp.getGradePay()== 0)
                                ps.setInt(4,sh.getHeadValue());
                            else
                                ps.setInt(4, emp.getGradePay());
                        }  
                   
                    } 
                    else{
                        ps.setInt(4,sh.getHeadValue());
                    } 
                    
                    ps.setInt(5, sessionId.getCurrentSession());
                    ps.setInt(6,user.getUserOrgCode());
                    ps.executeUpdate();
                    ps.clearParameters();
                    ps.close();  
                        
                }
                /** create liability of all employees */
                
                NewSalaryProcessing nsp=new NewSalaryProcessing();
                nsp.saveEmpLiability(rs.getString(1));
            }
            rs.close();
            ps.close();
            c.close();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
    }
    
    /**
     * Load Default values of employee according to salaryhead
     * @param emp
     * @param salhead
     * @return headvalue
     */
    public int loadDefaultHeadValues(Employee emp, int salhead){
        try
        {
            Connection c = new CommonDB().getConnection();
               
            ps=c.prepareStatement("select ds_amount from default_salary_master "
                + "where ds_emp_type= (select emp_salary_grade from employee_master where emp_code=?) and ds_sal_head=?  order by ds_sal_head");    
            ps.setString(1,emp.getCode());
            ps.setInt(2,salhead);
            rs=ps.executeQuery();
            int headvalue=0;
            if(rs.next())
            {
                if(salhead==1)
                {
                    headvalue=emp.getCurrentBasic();
                }
                else if(salhead==2)
                {
                    headvalue=emp.getGradePay();
                }
                else{
                    headvalue=rs.getInt(1);
                }
            // System.out.println("load init method=seem2==="+headvalue+":===="+ salhead);
            
            }
            rs.close();
            ps.close();
            c.close();
            return headvalue;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }
    
    /**
     * Load all salary head with formulas of employee
     * @param emp
     * @return data(arrayList)
     */
    public ArrayList<SalaryData> LoadValuesWithFormula(Employee emp)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sh_id,sh_name,sh_type,0,sf_sal_formula,sh_alias,sh_scalable from emp_salary_head_master "
                + "left join salary_head_master on sh_id = st_sal_code left join salary_formula on sh_id = sf_sal_id "
                +"where st_code=(select emp_type_code from employee_master where emp_code=?) order by sh_id");
            ps.setString(1,emp.getCode());
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
                    sd.setHeadValue(emp.getCurrentBasic());
                }
                if(sd.getHeadCode()==2)
                {
                    sd.setHeadValue(emp.getGradePay());
                }
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

}
