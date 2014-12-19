 /* Document : AttendanceDB
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import java.io.BufferedWriter;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.context.FacesContext;

import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.setup.Attendance;
import org.smvdu.payroll.user.ActiveProfile;
import org.smvdu.payroll.beans.upload.UploadFile;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.smvdu.payroll.beans.Employee;

/**
 * Manages Attendance in database.
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
*   Creation Date: 21 OCT 2014, IITK , Om Prakash(omprakashkgp@gmail.com), Manorama Pal (palseema30@gmail.com)
*/
public class AttendanceDB {
    

    private ActiveProfile info;
    private final UserInfo userBean;
      
    
    public AttendanceDB()   {
        info = (ActiveProfile)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ActiveProfile");

        userBean = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
              
    }

    private PreparedStatement ps;
    private ResultSet rs;
    
/**
    public Attendance convert(String code)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select att_emp_code,emp_name from employee_attendance_master where att_emp_code=?");
            ps.setString(1, code);
            rs =ps.executeQuery();
            rs.next();
            Attendance a = new Attendance();
            a.setCode(rs.getString(1));
            a.setName(rs.getString(2));
            rs.close();
            ps.close();
            c.close();
            return a;

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    * 
    
    */
    
    /*
    Update Employee Attendance 
    */
    public void update(ArrayList<Attendance> atts)    {
        
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update employee_attendance_master set emp_present=?, emp_leave=?"
                    + " where id=? and att_emp_code=? and org_code=? ");
            for(Attendance dp : atts)
            {
  
                    ps.setInt(1, dp.getPresent());
                    ps.setInt(2, dp.getLeave());
                    ps.setInt(3, dp.getId());
                    ps.setString(4, dp.getCode());
                    ps.setInt(5, userBean.getUserOrgCode());
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
    
    /**
    public ArrayList<Attendance> loadAttendances()   {
        ArrayList<Attendance> data = new ArrayList<Attendance>();
        try
        {
            
            Connection c = new CommonDB().getConnection();
           // ps=c.prepareStatement("select * from employee_attendance_master where org_code = '"+userBean.getUserOrgCode()+"'");
            ps=c.prepareStatement("select id, att_emp_code, emp_name, emp_present, emp_leave, month, year from employee_attendance_master"
                     +" left join employee_master on emp_code=att_emp_code where org_code='"+userBean.getUserOrgCode()+"'");
            
            rs=ps.executeQuery();
            while(rs.next())
            {
                Attendance att = new Attendance();
                att.setId(rs.getInt(1));
                att.setCode(rs.getString(2));
                Employee emp = new Employee();
                emp.setName(rs.getString(3));
                att.setName(emp.getName());
                //att.setName(rs.getString(3));
                att.setPresent(rs.getString(4));
                att.setLeave(rs.getString(5));
                att.setMonth(rs.getString(6));
                att.setYear(rs.getString(7));
                data.add(att);
               // System.out.println("first======"+rs.getInt(1)+"2==="+rs.getString(2)+"3==="+rs.getString(3)+"4===="+rs.getString(4)+"5==="+rs.getString(5)+"6==="+rs.getString(6)+"7==="+rs.getString(7));
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
    **/
    /*
    Use for display the selected attendance 
    */
    
    public ArrayList<Attendance> loadAttendances(int month,int year)   {
       ArrayList<Attendance> data = new ArrayList<Attendance>();
        try
        {
               
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select id, att_emp_code, emp_name, emp_present, emp_leave, month, year from employee_attendance_master "
                     +"left join employee_master on emp_code=att_emp_code where month='"+month+"' and year='"+year+"' and org_code='"+userBean.getUserOrgCode()+"'");
            
            rs=ps.executeQuery();
            int k=1;
            while(rs.next())
            {
                Attendance att = new Attendance();
                att.setId(rs.getInt(1));
                att.setCode(rs.getString(2));
                Employee emp = new Employee();
                emp.setName(rs.getString(3));
                att.setName(emp.getName());
                att.setPresent(rs.getInt(4));
                att.setLeave(rs.getInt(5));
                //att.setMonth(rs.getString(6));
                //att.setYear(rs.getString(7));
                att.setSrNo(k);
                data.add(att);
                k++;
                                
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
    
   /*
    Use for save attendance in databases 
    */
    public Exception save(Attendance attName)   {
        try
        {
            Connection c = new CommonDB().getConnection();
                ps=c.prepareStatement("insert into employee_attendance_master(att_emp_code, emp_present, emp_leave, month, year, org_code) values(?,?,?,?,?,?)");
           
                ps.setString(1, attName.getCode());
                //ps.setString(2, attName.getName());
                ps.setInt(2, attName.getPresent());
                ps.setInt(3, attName.getLeave());
                ps.setInt(4, attName.getMonth());
                ps.setInt(5, attName.getYear());
                ps.setInt(6, userBean.getUserOrgCode());            
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
   /*
    Use for upload csv file and save into databases
    */
    
    public Exception saveFile(UploadFile file)   {
        
        try
        {
            Connection c = new CommonDB().getConnection();
            
            ps=c.prepareStatement("insert into employee_attendance_master(att_emp_code,emp_present, emp_leave, month, year, org_code) values(?,?,?,?,?,?)");
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/attend");
            CSVReader reader = new CSVReader(new FileReader(path+"/"+file.getName()), ',', '\"', 1);
            ColumnPositionMappingStrategy<Attendance> mappingStrategy 
                                 = new ColumnPositionMappingStrategy<Attendance>();
            mappingStrategy.setType(Attendance.class);
            String[] columns = new String[] {"Code","Present","Leave", "Month", "Year"};
            mappingStrategy.setColumnMapping(columns);
        
            CsvToBean<Attendance> csv = new CsvToBean<Attendance>();
            List<Attendance> AttendanceList = csv.parse(mappingStrategy, reader);
               
            for (int i = 0; i < AttendanceList.size(); i++) 
            {
                
               Attendance Detail = AttendanceList.get(i);
               int mth = Detail.getMonth(); 
               //int dmonth = Calendar.(Detail.getMonth();
               int year = Detail.getYear();
               int days= NumberofDays(mth,year);  
               int sum=(Detail.getLeave())+(Detail.getPresent());
               if (sum <= days){
                                              
                    ps.setString(1,Detail.getCode());
                    //ps.setString(2,Detail.getName());
                    ps.setInt(2, Detail.getPresent());
                    ps.setInt(3, Detail.getLeave());
                    ps.setInt(4, Detail.getMonth());
                    ps.setInt(5, Detail.getYear());
                    ps.setInt(6, userBean.getUserOrgCode());
                    ps.executeUpdate();
               }
                else{
                    String path2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
                    File filecsv =new File(path2+"csvfile-UploadError.txt");                            
                    filecsv.createNewFile();
                    FileWriter fileWritter = new FileWriter(filecsv,true);
                    BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                    bufferWritter.write(" "+Detail.getCode()+" "+Detail.getPresent()+" "+Detail.getLeave()+"  "+Detail.getMonth()+"  "+Detail.getYear());
                    bufferWritter.newLine();
                    bufferWritter.close();
                                   
                 }
            }
            reader.close();
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
    
    /*Count the number of days in a selected month */
    
    public  int NumberofDays(int month, int year){
        try{
            Calendar calendar = Calendar.getInstance();
             //calendar.set(Calendar.YEAR, year);
            int date = 1;
            calendar.set(year, month, date);
            int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
           // System.out.println("Number of Days: " + days);
            return days;
             }
        catch(Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }
    
    /*Display the list of years*/
    
    public ArrayList<Attendance>ListofYears()
    {
        ArrayList<Attendance> listofyears = new ArrayList<Attendance>();
        try{
            Calendar calendar = Calendar.getInstance();
            int currentyear =calendar.get(Calendar.YEAR);
                   for(int yearlist=currentyear-10 ; yearlist<currentyear+10; yearlist++)
                    {
                    // System.out.println("Number of year: " +listofyears );
                    Attendance att = new Attendance();
                    att.setYear(yearlist);
                    listofyears.add(att);
                   
                }
                return listofyears;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    
        
    }
    
    /* 
    Check attendance of employee if left attendance
    */
    
     public ArrayList<Attendance> checkAttendances(int month, int year)   {
       ArrayList<Attendance> checkdata = new ArrayList<Attendance>();
      try
        {
            
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select emp_code from employee_master left join employee_attendance_master on att_emp_code=emp_code where att_emp_code is null");
            ps=c.prepareStatement(" select emp_code from employee_master where emp_code not in ( select att_emp_code from employee_attendance_master"
                  + " where month=? and year=? and org_code='"+userBean.getUserOrgCode()+"')");
           
            ps.setInt(1, month);
            ps.setInt(2, year);
            
            rs=ps.executeQuery();
           
            while(rs.next())
            {                
              Attendance check = new Attendance();
              check.setCode(rs.getString(1));
              //check.setName(rs.getString(2));
              checkdata.add(check);
                             
            }
            
            rs.close();
            ps.close();
            c.close();
            return checkdata;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        
    }
       /*
    Use for save check attendance in databases 
    */
    public Exception saveCheckAt(ArrayList<Attendance> checkAt)   {
        
        try
        {
            Connection c = new CommonDB().getConnection();
            //ArrayList<Attendance> checkboxdata = new ArrayList<Attendance>();
            ps=c.prepareStatement("insert into employee_attendance_master(att_emp_code, emp_present, emp_leave, month, year, org_code) values(?,?,?,?,?,?) ");
                for(Attendance check : checkAt)
                {
                    ps.setString(1, check.getCode());
                    //ps.setString(2, attName.getName());
                    ps.setInt(2, check.getPresent());
                    ps.setInt(3, check.getLeave());
                    ps.setInt(4, check.getMonth());
                    ps.setInt(5, check.getYear());
                    ps.setInt(6, userBean.getUserOrgCode()); 
                    ps.executeUpdate();
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

