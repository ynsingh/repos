/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.TaxGender;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.smvdu.payroll.Hibernate.HibernateUtil;
import org.smvdu.payroll.beans.SessionMaster;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.module.attendance.LoggedEmployee;
/**
 *
 * @author ERP
 */
public class TaxSlabHeadDB {

    private int orgCode;
    private HibernateUtil helper;
    private Session session;
    
    public TaxSlabHeadDB()
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

    public Exception saveSlabHead(TaxSlabHeadBean taxHeadBean)
    {
     
        try
        {
            TaxSlabHeadBean data = new TaxSlabHeadBean();
            SessionMaster sm = new SessionMaster();
            sm.setCode(taxHeadBean.getFyearDropDown());
            data.setFyear(sm);
            data.setSlabName(taxHeadBean.getSlabName());
            data.setStartSlabValue(taxHeadBean.getStartSlabValue());
            data.setEndSlabValue(taxHeadBean.getEndSlabValue());
            data.setPercent(taxHeadBean.getPercent());
            data.setSurcharge(taxHeadBean.getSurcharge());
            data.setEduCess(taxHeadBean.getEduCess());
            data.setHeduCess(taxHeadBean.getHeduCess());
            data.setOrgcode(orgCode);
            
            session = helper.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(data);
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
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            pst = cn.prepareStatement("insert into slab_head(sl_session_id,slab_head_name,sl_start_value,sl_end_value,sl_percent,sl_surcharge,sl_edu_cess,sl_hedu_cess,sl_orgCode) values('"+taxHeadBean.getFyear()+"','"+taxHeadBean.getSlabName()+"','"+taxHeadBean.getStartSlabValue()+"','"+taxHeadBean.getEndSlabValue()+"','"+taxHeadBean.getPercent()+"','"+taxHeadBean.getSurcharge()+"','"+taxHeadBean.getEduCess()+"','"+taxHeadBean.getHeduCess()+"','"+orgCode+"')");
            pst.executeUpdate();
            pst.close();
            cn.close();
            return null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        }
        */
    }
    
    
    public ArrayList<TaxSlabHeadBean> loadSelectedSlab(int sess)   {
      
    
        try
        {
            session = helper.getSessionFactory().openSession();
            session.beginTransaction();
         //   Query query = session.createQuery("from TaxSlabHeadBean where fyear = '"+sess+"'");
            SessionMaster sm = new SessionMaster();
            sm.setCode(sess);
            Query query = session.createQuery("from TaxSlabHeadBean where fyear.code ='"+sess+"'"); 
            ArrayList<TaxSlabHeadBean> data = (ArrayList<TaxSlabHeadBean>) query.list();
            
     /*       Criteria criteria = session.createCriteria(TaxSlabHeadBean.class);
            criteria.createAlias("fyear","fy",CriteriaSpecification.LEFT_JOIN);
            criteria.add(Restrictions.eq("fy.code", sess));
            ArrayList<TaxSlabHeadBean> data = (ArrayList<TaxSlabHeadBean>) criteria.list(); */
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
            PreparedStatement ps;
            ResultSet rs;
            ps=c.prepareStatement("select sl_head_code,sl_session_id,slab_head_name,sl_start_value,sl_end_value,sl_percent,sl_surcharge,sl_edu_cess,sl_hedu_cess from "
                    + "slab_head where sl_session_id=?");
            ps.setInt(1, sess);
            //System.err.println(">>> Type Code : "+empType);
            rs=ps.executeQuery();
            ArrayList<TaxSlabHeadBean> data = new ArrayList<TaxSlabHeadBean>();
            while(rs.next())
            {
                TaxSlabHeadBean taxHeadBean = new TaxSlabHeadBean();
                taxHeadBean.setSlabHeadCode(rs.getInt(1));
                taxHeadBean.setFyearDropDown(rs.getInt(2));
                taxHeadBean.setSlabName(rs.getString(3));
                taxHeadBean.setStartSlabValue(rs.getInt(4));
                taxHeadBean.setEndSlabValue(rs.getInt(5));
                taxHeadBean.setPercent(rs.getFloat(6));
                taxHeadBean.setSurcharge(rs.getFloat(7));
                taxHeadBean.setEduCess(rs.getFloat(8));
                taxHeadBean.setHeduCess(rs.getFloat(9));
                data.add(taxHeadBean);
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
    
    
    
    public ArrayList<TaxSlabHeadBean> loadSlabInfo()
    {
    
        try
        {
            ArrayList<TaxSlabHeadBean> taxSlabInfo = new ArrayList<TaxSlabHeadBean>();
            session = helper.getSessionFactory().openSession();
            session.beginTransaction();
      //      Query query = session.createQuery("select new TaxSlabHeadBean(slabHeadCode, slabName) from TaxSlabHeadBean left join  TaxSlabHeadBean.fyear  where current = 1 and orgcode='"+orgCode+"'");
            Query query = session.createQuery("select ts.slabHeadCode, ts.slabName from TaxSlabHeadBean ts left join ts.fyear  where ts.fyear.current = 1 and ts.orgcode='"+orgCode+"' order by ts.slabHeadCode asc");
            List result = query.list();
            
          /*  for (int i = 0; i < result.size(); i++) {
                TaxSlabHeadBean ts = (TaxSlabHeadBean) result.get(i);
                System.out.println(ts.getSlabHeadCode());
                System.out.println(ts.getSlabName());
                taxSlabInfo.add(ts);
            }
            return taxSlabInfo; */
        
           for (Iterator it = result.iterator(); it.hasNext(); ) {
               Object[] myResult = (Object[]) it.next();
               TaxSlabHeadBean ts = new TaxSlabHeadBean();
               ts.setSlabHeadCode((Integer) myResult[0]);
               ts.setSlabName((String) myResult[1]);
               System.out.println("code : '"+ts.getSlabHeadCode()+"'");
               System.out.println("Name : '"+ts.getSlabName()+"'");
         //      System.out.println( "Found " + firstName + " " + lastName );
               taxSlabInfo.add(ts);
            } 
           session.getTransaction().commit();
           return taxSlabInfo;                  
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
            ArrayList<TaxSlabHeadBean> taxSlabInfo = new ArrayList<TaxSlabHeadBean>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select sl_head_code,slab_head_name from slab_head left join session_master on ss_id=sl_session_id where ss_current=1 and sl_orgCode='"+orgCode+"'");
            rst = pst.executeQuery();
            while(rst.next())
            {
                TaxSlabHeadBean taxHeadBean = new TaxSlabHeadBean();
                taxHeadBean.setSlabHeadCode(rst.getInt(1));
                taxHeadBean.setSlabName(rst.getString(2));
              
                System.out.println("Slab head Code In Load slab Info : "+taxHeadBean.getSlabHeadCode());
                System.out.println("Slab Head Name In Load Slab Info : "+taxHeadBean.getSlabName());
              
                taxSlabInfo.add(taxHeadBean); 
                
            }
            return taxSlabInfo;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
            */
    }
    public ArrayList<TaxSlabHeadBean> loadGenderSlabInfo(int genCode)
    {
        
         try
        {
            ArrayList<TaxSlabHeadBean> taxSlabInfo = new ArrayList<TaxSlabHeadBean>();
            session = helper.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("select ts.slabHeadCode, ts.slabName from EmployeeSlabDetail eshd left join eshd.slabCode ts  where eshd.gendercode = '"+genCode+"' and eshd.orgCode='"+orgCode+"'");
            List result = query.list();
            
            for (Iterator it = result.iterator(); it.hasNext(); ) {
               Object[] myResult = (Object[]) it.next();
               TaxSlabHeadBean ts = new TaxSlabHeadBean();
               ts.setSlabHeadCode((Integer) myResult[0]);
               ts.setSlabName((String) myResult[1]);
               System.out.println("code : '"+ts.getSlabHeadCode()+"'");
               System.out.println("Name : '"+ts.getSlabName()+"'");
         //      System.out.println( "Found " + firstName + " " + lastName );
               taxSlabInfo.add(ts);
            } 
            
            session.getTransaction().commit();   
            return taxSlabInfo;
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
            ArrayList<TaxSlabHeadBean> taxSlabInfo = new ArrayList<TaxSlabHeadBean>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            System.out.println("Gen code : "+genCode);
            pst = cn.prepareStatement("select sl_head_code,slab_head_name "
                    + "from emp_slab_head_master left join slab_head on "
                    + "sl_head_code = emp_slab_code where emp_gen_code = '"+genCode+"' and emp_slab_orgCode = '"+orgCode+"'");
            rst = pst.executeQuery();
            while(rst.next())
            {
                TaxSlabHeadBean taxHeadBean = new TaxSlabHeadBean();
                taxHeadBean.setSlabHeadCode(rst.getInt(1));
                taxHeadBean.setSlabName(rst.getString(2));
                
                //empsd.setSlabSelected(true);
                System.out.println("SLab Head Code in Gender Slab info : "+taxHeadBean.getSlabHeadCode());
                System.out.println("Slab Name in Gender Slab info : "+taxHeadBean.getSlabName());
               
                taxSlabInfo.add(taxHeadBean);
            }
            return taxSlabInfo;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
            
            */
    }
    
   /* public ArrayList<TaxSlabHeadBean> loadCurrentYearSlabs()
    {
        try
        {
            ArrayList<TaxSlabHeadBean> taxSlabInfo = new ArrayList<TaxSlabHeadBean>();
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            System.out.println("Gen code : "+genCode);
            pst = cn.prepareStatement("select sl_head_code,slab_head_name,sl_start_value,sl_end_value,sl_percent "
                    + "from emp_slab_head_master left join slab_head on "
                    + "sl_head_code = emp_slab_code where emp_gen_code = '"+genCode+"' and emp_slab_orgCode = '"+orgCode+"' order by emp_slab_code");
            rst = pst.executeQuery();
            while(rst.next())
            {
                TaxSlabHeadBean taxHeadBean = new TaxSlabHeadBean();
                taxHeadBean.setSlabHeadCode(rst.getInt(1));
                taxHeadBean.setSlabName(rst.getString(2));
                
                //empsd.setSlabSelected(true);
                System.out.println("SLab Head Code : "+taxHeadBean.getSlabHeadCode());
                System.out.println("Slab Name : "+taxHeadBean.getSlabName());
               
                taxSlabInfo.add(taxHeadBean);
            }
            return taxSlabInfo;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }*/
}
