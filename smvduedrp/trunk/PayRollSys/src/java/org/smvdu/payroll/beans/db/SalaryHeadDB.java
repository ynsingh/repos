/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;                                 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.smvdu.payroll.Hibernate.HibernateUtil;
import org.smvdu.payroll.beans.DefaultSalary;
import org.smvdu.payroll.beans.SalaryTypeMaster;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.setup.SalaryGrade;
import org.smvdu.payroll.beans.setup.SalaryHead;
import org.smvdu.payroll.user.ActiveProfile;

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
* Modified Date: 13 Nov 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
*
*/
public class SalaryHeadDB {

    private PreparedStatement ps;
    private ResultSet rs;
    private HibernateUtil helper;
    private Session session;


    private final UserInfo userBean;
    private ActiveProfile info;

    public SalaryHeadDB()   {
        info = (ActiveProfile)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ActiveProfile");

        userBean = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");


    }


     public ArrayList<String> getAllHeadAsString()
    {
        
        try {
       
            session = helper.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("select name from SalaryHead ");
            ArrayList<String> data =  (ArrayList<String>)query.list();
            session.getTransaction().commit();
            return data;
        } 
        catch(Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;

        }     
        finally{
            session.close();
        }   
        
    /*    
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sh_name from salary_head_master ");
            rs=ps.executeQuery();
            ArrayList<String> data = new ArrayList<String>();
            while(rs.next())
            {
                data.add(rs.getString(1));
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
        }       */
    }


    public ArrayList<String> getApplicableHeads(String empCode)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sh_name from salary_head_master "
                + "where sh_id in (select st_sal_code from emp_salary_head_master "
                + "where st_code =  (select emp_type_code from employee_master "
                + "where emp_code=?) ) order by sh_id ");
            ps.setString(1, empCode);
            rs=ps.executeQuery();
            ArrayList<String> data = new ArrayList<String>();
            while(rs.next())
            {
                data.add(rs.getString(1));
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
    
    
    
    public ArrayList<SalaryTypeMaster> getTypes()  {
           
        try
        {     
          session = helper.getSessionFactory().openSession();
          session.beginTransaction();
          Query query = session.createQuery("from SalaryTypeMaster");
          ArrayList<SalaryTypeMaster> data = (ArrayList<SalaryTypeMaster>) query.list();       
          session.getTransaction().commit();
          return data;
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }
        
        
    /*    
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select st_id,st_name from salary_type");
            rs = ps.executeQuery();
            ArrayList<SalaryTypeMaster> data = new ArrayList<SalaryTypeMaster>();
            while(rs.next())
            {
                SalaryTypeMaster st = new SalaryTypeMaster();
                st.setId(rs.getInt(1));
                st.setName(rs.getString(2));
                data.add(st);
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
        }   */
    }
       
    public void updateDefaultvalue(ArrayList<SalaryHead> heads,int emptype)  {
      
        try
        {
            session = helper.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("delete from DefaultSalary where gradeType.code = '"+emptype+"' ");
            query.executeUpdate();
            session.getTransaction().commit();
            
            for(SalaryHead data : heads)
            {
                session.beginTransaction();
                
                DefaultSalary ds = new DefaultSalary();
                
                SalaryGrade sg = new SalaryGrade();
                sg.setCode(emptype);               
                ds.setGradeType(sg);
                
                SalaryHead sh = new SalaryHead();
                sh.setNumber(data.getNumber());               
                ds.setSalaryHead(sh);
                
                ds.setAmount(data.getDefaultValue());
                
                session.save(ds);
                session.getTransaction().commit();
 
            }
                      
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        
        
    /*    
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from default_salary_master where ds_emp_type=?");
            ps.setInt(1, emptype);
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("update default_salary_master set ds_amount=? "
                    + "where ds_sal_head=? and ds_emp_type=?");
            for(SalaryHead sh : heads)
            {
                ps.setInt(1, sh.getDefaultValue());
                ps.setInt(2, sh.getNumber());
                ps.setInt(3, emptype);
                ps.executeUpdate();
                ps.clearParameters();
            }
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }   */
        
    }
    
    public void update(ArrayList<SalaryHead> heads)  {
       
        try
        {
    
            session = helper.getSessionFactory().openSession();
            
            for(SalaryHead sh : heads)
            {
               session.beginTransaction();

                SalaryHead s = (SalaryHead)session.get(SalaryHead.class, sh.getNumber());
                
                s.setNumber(sh.getNumber());
                s.setShcode(sh.getShcode().toUpperCase());
                s.setName(sh.getName().toUpperCase());
                s.setAlias(sh.getAlias().toUpperCase());
                s.setCalculationType(sh.isCalculationType());
                s.setUnder(sh.isUnder());
                s.setType(sh.isType());
                s.setDisplay(sh.isDisplay());
                s.setProcessType(sh.isProcessType());
                s.setOrgcode(userBean.getUserOrgCode());
                
                session.update(s);
                session.getTransaction().commit();
            }
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
    /*    
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update salary_head_master set sh_code=?,sh_name=?,sh_alias=?,"
                    + "sh_calc_type=?,sh_type=?,sh_cat=?,sh_display=?,sh_process_type=? where sh_id=? and sh_org_id=?");
            for(SalaryHead sh : heads)
            {
                ps.setString(1, sh.getShcode().toUpperCase());
                ps.setString(2, sh.getName().toUpperCase());
                ps.setString(3, sh.getAlias().toUpperCase());
                ps.setBoolean(4, sh.isCalculationType());
                ps.setBoolean(5, sh.isUnder());
                ps.setBoolean(6, sh.isType());
                ps.setBoolean(7, sh.isDisplay());
                ps.setBoolean(8, sh.isProcessType());
                ps.setInt(9, sh.getNumber());
                ps.setInt(10, userBean.getUserOrgCode());
                ps.executeUpdate();try
        
                ps.clearParameters();
            }
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }       */
    }
        
        
    public void updateDefault(ArrayList<SalaryHead> heads,int code)    {
   
        
         try
        {
            session = helper.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("delete from DefaultSalary where gradeType.code = '"+code+"' ");
            query.executeUpdate();
            session.getTransaction().commit();
            
            for(SalaryHead data : heads)
            {
                session.beginTransaction();

                DefaultSalary ds = new DefaultSalary();
                
                SalaryGrade sg = new SalaryGrade();
                sg.setCode(code);               
                ds.setGradeType(sg);
                
                SalaryHead sh = new SalaryHead();
                sh.setNumber(data.getNumber());               
                ds.setSalaryHead(sh);
                
                ds.setAmount(data.getDefaultValue());
                
                session.save(ds);
                session.getTransaction().commit();
 
            }
                      
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        /*
        
        
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from default_salary_master where ds_emp_type=?");
            ps.setInt(1, code);
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("insert into default_salary_master (ds_emp_type, ds_sal_head, ds_amount) values(?,?,?)");
            for(SalaryHead sh : heads)
            {
                ps.setInt(1, code);
                ps.setInt(2, sh.getNumber());
                ps.setInt(3, sh.getDefaultValue());
                ps.executeUpdate();
                ps.clearParameters();
            }
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }       */
    }


    public ArrayList<SalaryHead> loadAppliedHeads(int empType)   {
       
        
        try
        {
            ArrayList<SalaryHead> sheads = new ArrayList<SalaryHead>();
            session = helper.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("select sh.number, sh.name, sh.calculationType, sh.under from EmployeeTypeSalaryHead et left join et.salaryHeadCode sh where et.empTypeCode.code ='"+empType+"' and et.orgcode='"+userBean.getUserOrgCode()+"' order by sh.under");
            List result = query.list();
            
            for (Iterator it = result.iterator(); it.hasNext(); ) {
               Object[] myResult = (Object[]) it.next();
               SalaryHead s = new SalaryHead();
               s.setNumber((Integer) myResult[0]);
               s.setName((String) myResult[1]);
               s.setCalculationType((Boolean) myResult[2]);
               s.setUnder(!(Boolean) myResult[3]);
               
               sheads.add(s);
            } 
            
            session.getTransaction().commit();   
            return sheads;
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }         
        
        
        
    /*    
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sh_id,sh_name,sh_calc_type,sh_type "
                    + "from emp_salary_head_master left join salary_head_master"
                    + " on sh_id = st_sal_code where st_code=? and st_org_code='"+userBean.getUserOrgCode()+"' order by sh_type");
            ps.setInt(1, empType);
            //System.err.println(">>> Type Code : "+empType);
            rs=ps.executeQuery();
            ArrayList<SalaryHead> data = new ArrayList<SalaryHead>();
            while(rs.next())
            {
                SalaryHead sal = new SalaryHead();
                sal.setNumber(rs.getInt(1));
                sal.setName(rs.getString(2));
                sal.setCalculationType(rs.getBoolean(3));
                sal.setUnder(!rs.getBoolean(4));
                data.add(sal);
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
        }       */
    }



    public ArrayList<SalaryHead> loadSelectedHeads(int empType)   {
        
        try
        {
            ArrayList<SalaryHead> sheads = new ArrayList<SalaryHead>();
            session = helper.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("select sh.number, sh.name, ds.amount, sh.under from SalaryHead sh left join sh.defaultSalary ds where sh.calculationType=0 and ds.gradeType.code='"+empType+"' ");
            List result = query.list();
            
            for (Iterator it = result.iterator(); it.hasNext(); ) {
               Object[] myResult = (Object[]) it.next();
               SalaryHead s = new SalaryHead();
               s.setNumber((Integer) myResult[0]);
               s.setName((String) myResult[1]);
               s.setDefaultValue((Integer) myResult[2]);
               s.setUnder((Boolean) myResult[3]);
               
               sheads.add(s);
            } 
            
            session.getTransaction().commit();   
            return sheads;
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }       
        
       
      /* 
        
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sh_id,sh_name,ds_amount,sh_type from "
                    + "salary_head_master left join default_salary_master "
                    + "on ds_sal_head = sh_id and ds_emp_type=? where sh_calc_type=0 ");
            ps.setInt(1, empType);
            //System.err.println(">>> Type Code : "+empType);
            rs=ps.executeQuery();
            ArrayList<SalaryHead> data = new ArrayList<SalaryHead>();
            while(rs.next())
            {
                SalaryHead sal = new SalaryHead();
                sal.setNumber(rs.getInt(1));
                sal.setName(rs.getString(2));
                sal.setDefaultValue(rs.getInt(3));
                sal.setUnder(rs.getBoolean(4));
                data.add(sal);
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
        }       */
    }
    
    public ArrayList<SalaryHead> loadSelectedDeductionHeads(int empType)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select st_sal_code,sh_name,ds_amount,sh_type from "
                    + "emp_salary_head_master left join salary_head_master "
                    + "on sh_id = st_sal_code left join default_salary_master "
                    + "on ds_emp_type = st_code and ds_sal_head=sh_id where st_code=? and "
                    + "sh_type=0   ");
            ps.setInt(1, empType);
            rs=ps.executeQuery();
            ArrayList<SalaryHead> data = new ArrayList<SalaryHead>();
            while(rs.next())
            {
                SalaryHead dept = new SalaryHead();
                dept.setNumber(rs.getInt(1));
                dept.setName(rs.getString(2));
                dept.setDefaultValue(rs.getInt(3));
                //System.out.println("\n loadSelectedDeductionHeadss======"+rs.getInt(3)); 
                dept.setCalculationType(rs.getBoolean(4));
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
    public ArrayList<SalaryHead> loadSelectedIncomeHeads(int empType)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select st_sal_code,sh_name,ds_amount,sh_type from "
                    + "emp_salary_head_master left join salary_head_master "
                    + "on sh_id = st_sal_code left join default_salary_master "
                    + "on ds_emp_type = st_code and ds_sal_head=sh_id where st_code=? "
                    + " and sh_type=1 ");
            ps.setInt(1, empType);
            rs=ps.executeQuery();
            ArrayList<SalaryHead> data = new ArrayList<SalaryHead>();
            while(rs.next())
            {
                SalaryHead dept = new SalaryHead();
                dept.setNumber(rs.getInt(1));
                dept.setName(rs.getString(2));
                dept.setDefaultValue(rs.getInt(3));
                //System.out.println("\n loadSelectedIncomeHeads======"+rs.getInt(3));               
                dept.setCalculationType(rs.getBoolean(4));
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
    public boolean updateFormula(int code,String formula)    {
      
        try
        {
            session = helper.getSessionFactory().openSession();
            session.beginTransaction();
            SalaryHead sh = (SalaryHead)session.get(SalaryHead.class, code);
            sh.setFormula(formula);
            session.update(sh);
            session.getTransaction().commit();

            return true;
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();
        }
        
        
    /*    try
        {
              Connection c = new CommonDB().getConnection();
              ps=c.prepareStatement("update salary_head_master set sh_formula=? where sh_id=?");
              ps.setString(1, formula);
              ps.setInt(2, code);
              ps.executeUpdate();
              ps.close();
              c.close();
              return true;
        }
        catch(Exception e)
        {
            return false;
        }   */
    }
    
    public ArrayList<SalaryHead> loadConsolidatedHeads()   {
       
        try
        {
            
          session = helper.getSessionFactory().openSession();
          session.beginTransaction();
          Query query = session.createQuery("from SalaryHead where calculationType=0");
          ArrayList<SalaryHead> data = (ArrayList<SalaryHead>) query.list();       
          session.getTransaction().commit();
          return data;
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }  
        
        
        
    /*    
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from salary_head_master where sh_calc_type=0");
            rs=ps.executeQuery();
            ArrayList<SalaryHead> data = new ArrayList<SalaryHead>();
            while(rs.next())
            {
                SalaryHead dept = new SalaryHead();
                dept.setNumber(rs.getInt(1));
                dept.setShcode(rs.getString(2));
                dept.setName(rs.getString(3));
                dept.setUnder(rs.getBoolean(4));
                dept.setAlias(rs.getString(5));
                dept.setCalculationType(rs.getBoolean(6));
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
        }   */
    }
    
    public ArrayList<SalaryHead> loadHeads(boolean type)   {
      
        try
        {
            
          session = helper.getSessionFactory().openSession();
          session.beginTransaction();
          Query query = session.createQuery("from SalaryHead where under = '"+type+"'");
          ArrayList<SalaryHead> data = (ArrayList<SalaryHead>) query.list();       
          session.getTransaction().commit();
          return data;
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }  
        
        
        
    /*    
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from salary_head_master where sh_type=?");
            ps.setBoolean(1, type);
            rs=ps.executeQuery();
            ArrayList<SalaryHead> data = new ArrayList<SalaryHead>();
            while(rs.next())
            {
                SalaryHead dept = new SalaryHead();
                dept.setNumber(rs.getInt(1));
                dept.setShcode(rs.getString(2));
                dept.setName(rs.getString(3));
                dept.setUnder(rs.getBoolean(4));
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
    */    
    }
    public ArrayList<SalaryHead> loadAllHeads()   {
      
         try
        {
            ArrayList<SalaryHead> sheads = new ArrayList<SalaryHead>();
            session = helper.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("select sh.number,sh.shcode, sh.name, sh.under, sh.alias, sh.calculationType, sh.scalable, sh.special, sh.type, sh.display, st.id, st.name, sh.processType from SalaryHead sh left join sh.typeCode st order by sh.number");
            List result = query.list();
            
            for (Iterator it = result.iterator(); it.hasNext(); ) {
               Object[] myResult = (Object[]) it.next();
               SalaryHead s = new SalaryHead();
               s.setNumber((Integer) myResult[0]);
               s.setShcode((String) myResult[1]);
               s.setName((String) myResult[2]);
               s.setUnder((Boolean) myResult[3]);
               s.setAlias((String) myResult[4]);
               s.setCalculationType((Boolean) myResult[5]);
               s.setScalable((Boolean) myResult[6]);
               s.setSpecial((Boolean) myResult[7]);
               s.setType((Boolean) myResult[8]);
               s.setDisplay((Boolean) myResult[9]);
               
               SalaryTypeMaster stm = new SalaryTypeMaster();
               stm.setId((Integer) myResult[10]);
               stm.setName((String) myResult[11]);
               
               s.setTypeCode(stm);
               s.setProcessType((Boolean) myResult[12]);
               
               sheads.add(s);
            } 
            
            session.getTransaction().commit();   
            return sheads;
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }       
        
        /*
        
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sh_id,sh_code,sh_name,sh_type,sh_alias,sh_calc_type,"
                    + "sh_scalable,sh_special,sh_cat,sh_display,"
                    + "sh_type_code,st_name,sh_process_type from salary_head_master left join salary_type "
                    + "on st_id = sh_type_code order by sh_id");  
            rs=ps.executeQuery();
            ArrayList<SalaryHead> data = new ArrayList<SalaryHead>();
            while(rs.next())
            {
                SalaryHead dept = new SalaryHead();
                dept.setNumber(rs.getInt(1));
                dept.setShcode(rs.getString(2));
                dept.setName(rs.getString(3));
                dept.setUnder(rs.getBoolean(4));
                dept.setAlias(rs.getString(5));
                dept.setCalculationType(rs.getBoolean(6));  
                dept.setScalable(rs.getBoolean(7));
                dept.setSpecial(rs.getBoolean(8));
                dept.setType(rs.getBoolean(9));
                dept.setDisplay(rs.getBoolean(10));
                SalaryTypeMaster st = new SalaryTypeMaster();
                st.setId(rs.getInt(11));
                st.setName(rs.getString(12));
                dept.setSalaryType(st);
                dept.setProcessType(rs.getBoolean(13));
                data.add(dept);
                //System.out.println("data===="+data);
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
        */
        
    }
    public Exception save(SalaryHead form)   {
    
       try
        {
          SalaryHead head = new SalaryHead();
          
          head.setShcode(form.getShcode().toUpperCase());
          head.setName(form.getName().toUpperCase());
          head.setUnder(!form.isUnder());
          head.setAlias(form.getAlias().toUpperCase());
          head.setCalculationType(form.isCalculationType());
          head.setScalable(form.isType());
          head.setDisplay(form.isDisplay());
          
          SalaryTypeMaster stm = new SalaryTypeMaster();
          stm.setId(form.getTypeCodeForDropDown());
          
          head.setTypeCode(stm);
          head.setProcessType(form.isProcessType());
          head.setOrgcode(userBean.getUserOrgCode());
          
          session = helper.getSessionFactory().openSession();
          session.beginTransaction();
          session.save(head);
          session.getTransaction().commit();
          return null;
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return e;
        }
        finally {
            session.close();
        }       
    
        
        
    /*    
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into salary_head_master(sh_code,sh_name,sh_type,"
                    + "sh_alias,sh_calc_type,sh_scalable,sh_display,sh_type_code,sh_process_type,sh_org_id) values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, form.getShcode().toUpperCase());
            ps.setString(2, form.getName().toUpperCase());
            ps.setBoolean(3, !form.isUnder());
            ps.setString(4, form.getAlias().toUpperCase());
            ps.setBoolean(5, form.isCalculationType());
            ps.setBoolean(6, form.isType());
            ps.setBoolean(7, form.isDisplay());
            ps.setInt(8, form.getTypeCode());
            ps.setBoolean(9, form.isProcessType());
            ps.setInt(10, userBean.getUserOrgCode());
            ps.executeUpdate();            
            ps.close();
            c.close();
            return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return e;
        }  */ 

    }   

}
