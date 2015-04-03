/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.TaxGender;
import java.sql.*;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.smvdu.payroll.Hibernate.HibernateUtil;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.module.attendance.LoggedEmployee;
/**
 *
 * @author ERP
 */
public class EmployeeSlabDetailDB {

    private int orgCode;
    private Session session;
    private HibernateUtil helper;
    public EmployeeSlabDetailDB()
    {
         LoggedEmployee le = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        if(le==null)
        {
            UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            orgCode = uf.getUserOrgCode();
        }
        else
        {
            orgCode = le.getUserOrgCode();
        }
    }
    public ArrayList<EmployeeSlabDetail> loadGenderDetail(){
    
        
         try
        {
            session = helper.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Gender where orgcode = '"+orgCode+"' order by genderCode asc");
            ArrayList<Gender> data = (ArrayList<Gender>) query.list();
            ArrayList<EmployeeSlabDetail> genDetails = new ArrayList<EmployeeSlabDetail>();
            
            for(Gender gn : data)
            {
                EmployeeSlabDetail esd = new EmployeeSlabDetail();
                esd.setCode(gn.getGenderCode());
                esd.setName(gn.getGenderName());
                genDetails.add(esd);
            }
            
            session.getTransaction().commit();
            return genDetails;   
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
            ArrayList<EmployeeSlabDetail> genDetail = new ArrayList<EmployeeSlabDetail>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst  = cn.prepareStatement("select ts_seq,gender_Name from ts_gender where orgCode = '"+orgCode+"' order by ts_seq asc");
            ResultSet rst;
            rst = pst.executeQuery();
            while(rst.next())
            {
                EmployeeSlabDetail gen = new EmployeeSlabDetail();
                gen.setCode(rst.getInt(1));
                gen.setName(rst.getString(2));
                genDetail.add(gen);
            }
            rst.close();
            pst.close();
            cn.close();
            return genDetail;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }           */
    }
    
    public Exception save(ArrayList<EmployeeSlabDetail> empSlabDetails, int genCode)
    {
    
        
        try
        {
            session = helper.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("delete from EmployeeSlabDetail where gendercode = '"+genCode+"' and orgCode = '"+orgCode+"'");
            query.executeUpdate();
            session.getTransaction().commit();
            
            for(EmployeeSlabDetail data : empSlabDetails)
            {
               session.beginTransaction();

              //  Department dept = (Department)session.get(Department.class, dp.getCode());
                EmployeeSlabDetail esd = new EmployeeSlabDetail();
                
                Gender gen = new Gender();
                gen.setGenderCode(genCode);
                
                TaxSlabHeadBean tshb = new TaxSlabHeadBean();
                tshb.setSlabHeadCode(data.getSlabCode().getSlabHeadCode());
                        
                esd.setGendercode(gen);
                esd.setSlabCode(tshb);
                esd.setOrgCode(orgCode);
                session.save(esd);
                session.getTransaction().commit();
            }
            return null;
                      
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }
        
        
        
        
        
        /*   try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = cn.prepareStatement("delete from emp_slab_head_master where emp_gen_code = '"+genCode+"' and emp_slab_orgCode = '"+orgCode+"'");
            pst.executeUpdate();
            pst.close();
            for(EmployeeSlabDetail empsd : empSlabDetails)
            {
                System.out.println("DAta Should Be Write Here : "+genCode+" : "+empsd.getSlabCode());
                pst = cn.prepareStatement("insert into emp_slab_head_master(emp_gen_code,emp_slab_code,emp_slab_orgCode) values('"+genCode+"','"+empsd.getSlabCode()+"','"+orgCode+"')");
                pst.executeUpdate();
                pst.clearParameters();
            }
            pst.close();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        }   */
    }
    
    public ArrayList<EmployeeSlabDetail> loadEmployeeSlabDetails(int genCode)
    {
         
            ArrayList<EmployeeSlabDetail> emplSlabDetails = new ArrayList<EmployeeSlabDetail>();
            ArrayList<TaxSlabHeadBean> selected = new TaxSlabHeadDB().loadGenderSlabInfo(genCode);
     /*      for(TaxSlabHeadBean ta : taxSlabHeadBean)
            {
                System.out.println("In Tax Slab Head bean : "+ta.getSlabHeadCode());
                System.out.println("In Tax Slab Head bean : "+ta.getSlabName());
                
            }       */
            for(TaxSlabHeadBean all : new TaxSlabHeadDB().loadSlabInfo())
            {
                EmployeeSlabDetail empsd = new EmployeeSlabDetail();
                
                System.out.println("code is : '"+ all.getSlabHeadCode()+"'");
                System.out.println("Slab Name is : '"+ all.getSlabName()+"'");
                
                TaxSlabHeadBean tshb = new TaxSlabHeadBean();
                tshb.setSlabHeadCode(all.getSlabHeadCode());
                
                empsd.setSlabCode(tshb);
                empsd.setSlabeName(all.getSlabName());
                
                System.out.println(selected.contains(all));
                
                if((selected.contains(all)))
                {
                    System.out.println("Data Should Be Write Here .. hi");
                    empsd.setSlabSelected(true);
                }
                else{
                System.out.println("Not matched");
                }
                emplSlabDetails.add(empsd);
            }

            return emplSlabDetails; 
            
       /*     ArrayList<TaxSlabHeadBean> all = new TaxSlabHeadDB().loadSlabInfo();
            for(TaxSlabHeadBean a : all)
            {
                EmployeeSlabDetail empsd = new EmployeeSlabDetail();
                empsd.setSlabHeadCode(a.getSlabHeadCode());
                empsd.setSlabName(a.getSlabName());
                              
                for(TaxSlabHeadBean item : selected)
                {
                    System.out.println(item);
                    System.out.println(a);
                    System.out.println(item==a);
                    if (item==a)
                    {
                        System.out.println("Data Should Be Write Here .. hi");
                        empsd.setSlabSelected(true);
                        break;
                    }
                    else
                    {
                        empsd.setSlabSelected(false);
                    }
                }
                emplSlabDetails.add(empsd);
            }

            return emplSlabDetails;     */
       
    }
}
