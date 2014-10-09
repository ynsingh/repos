/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.setup.Department;
import org.smvdu.payroll.user.ActiveProfile;
import org.smvdu.payroll.beans.upload.UploadFile;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages Department in database.
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
*  Modified Date: 07 OCT 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
*
*/
public class DepartmentDB {
    

    private ActiveProfile info;
    private final UserInfo userBean;
    
    
    public DepartmentDB()   {
        info = (ActiveProfile)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ActiveProfile");

        userBean = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");

               
    }

    private PreparedStatement ps;
    private ResultSet rs;

    public Department convert(String code)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select dept_code,dept_name from department_master where dept_name=?");
            ps.setString(1, code);
            rs =ps.executeQuery();
            rs.next();
            Department d = new Department();
            d.setCode(rs.getInt(1));
            d.setName(rs.getString(2));
            rs.close();
            ps.close();
            c.close();
            return d;

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public void update(ArrayList<Department> depts)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update department_master set  dept_dcode=?, dept_name=?, dept_nickname=?"
                    + " where dept_code=? and org_code= ? ");
            for(Department dp : depts)
            {
                ps.setString(1, dp.getDCode());
                ps.setString(2, dp.getName().toUpperCase());
                ps.setString(3, dp.getNickName());
                ps.setInt(4, dp.getCode());
                ps.setInt(5, userBean.getUserOrgCode());
                ps.executeUpdate();
                ps.clearParameters();
                //System.out.println("departments===="+dp.getDCode()+":"+dp.getName()+":"+dp.getNickName()+":"+dp.getCode());
            }
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
           e.printStackTrace();
           //Logger.getAnonymousLogger().log(Log., e.getMessage());
        }
    }
    public ArrayList<Department> loadDepartments()   {
        ArrayList<Department> data = new ArrayList<Department>();
        try
        {
            
            Connection c = new CommonDB().getConnection();
            //ps=c.prepareStatement("select dept_code,dept_name from department_master where org_code = '"+userBean.getUserOrgCode()+"'");
            //modify 26 sept 2014 for dcode and nickname
            ps=c.prepareStatement("select * from department_master where org_code = '"+userBean.getUserOrgCode()+"'");
            rs=ps.executeQuery();
            while(rs.next())
            {
                Department dept = new Department();
                dept.setCode(rs.getInt(1));
                dept.setDCode(rs.getString(2));
                dept.setName(rs.getString(3));
                dept.setNickName(rs.getString(4));
                data.add(dept);
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
   
    public Exception save(Department dptName)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            //ps=c.prepareStatement("insert into department_master(dept_name, org_code) values(?,?)");
            ps=c.prepareStatement("insert into department_master(dept_dcode, dept_name, dept_nickname, org_code) values(?,?,?,?)");
            ps.setString(1, dptName.getDCode().toUpperCase());
            ps.setString(2, dptName.getName().toUpperCase());
            ps.setString(3, dptName.getNickName().toUpperCase());
            ps.setInt(4, userBean.getUserOrgCode());
            
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
    
    
    public Exception saveFile(UploadFile file)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            
            ps=c.prepareStatement("insert into department_master(dept_dcode, dept_name, dept_nickname, org_code) values(?,?,?,?)");
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("../../web/tmp");
            CSVReader reader = new CSVReader(new FileReader(path+"/"+file.getName()), ',', '\"', 1);
            ColumnPositionMappingStrategy<Department> mappingStrategy 
                                 = new ColumnPositionMappingStrategy<Department>();
            mappingStrategy.setType(Department.class);
            String[] columns = new String[] {"DCode","Name","NickName"};
            mappingStrategy.setColumnMapping(columns);
        
            CsvToBean<Department> csv = new CsvToBean<Department>();
            List<Department> DepartmentList = csv.parse(mappingStrategy, reader);

        
            for (int i = 0; i < DepartmentList.size(); i++) 
            {
                
                Department DDetail = DepartmentList.get(i);
                // display CSV values
                
                ps.setString(1,DDetail.getDCode());
                ps.setString(2,DDetail.getName());
                ps.setString(3, DDetail.getNickName());
                ps.setInt(4, userBean.getUserOrgCode());
                ps.executeUpdate();
                           

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
    
         

}
