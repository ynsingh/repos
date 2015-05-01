 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.TaxGender;

/**
 *
 *  Copyright (c) 2010 - 2011 SMVDU, Katra, 2015 IITKanpur.
 *  All Rights Reserved.
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *  Redistributions of source code must retain the above copyright
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
 *  Contributors: Members of ERP Team @ SMVDU, Katra, IITKanpur
 *  Modified Date: 30 April 2015, IITK (palseema30@gmail.com)
 */

import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.smvdu.payroll.Hibernate.HibernateUtil;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.module.attendance.LoggedEmployee;

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
    
    /**method for loading employee slab detail according to gender ex:(super senior, senior citizen male female(U60)) 
    * @param genCode
    * @return ArrayList of EmployeeSlabDetail
    */
    
    public ArrayList<EmployeeSlabDetail> loadEmployeeSlabDetails(int genCode){
        try
        {
            
            ArrayList<EmployeeSlabDetail> emplSlabDetails = new ArrayList<EmployeeSlabDetail>();
            ArrayList<TaxSlabHeadBean> allslab = new TaxSlabHeadDB().loadSlabInfo();
            ArrayList<TaxSlabHeadBean> selected = new TaxSlabHeadDB().loadGenderSlabInfo(genCode);
            ArrayList<TaxSlabHeadBean>allfileteredsalb=new ArrayList<TaxSlabHeadBean>(allslab);
            allfileteredsalb.removeAll(selected);
            for(TaxSlabHeadBean all : allfileteredsalb)
            {
                EmployeeSlabDetail empsd = new EmployeeSlabDetail();
                //System.out.println("code : '"+ all.getSlabHeadCode()+"'");
                //System.out.println("Slab Name is : '"+ all.getSlabName()+"'");
                TaxSlabHeadBean tshb = new TaxSlabHeadBean();
                tshb.setSlabHeadCode(all.getSlabHeadCode());
                
                empsd.setSlabCode(tshb);
                empsd.setSlabeName(all.getSlabName());
                emplSlabDetails.add(empsd);
            }   
            for(TaxSlabHeadBean ss : selected)
            {   
                EmployeeSlabDetail empsd = new EmployeeSlabDetail();
                TaxSlabHeadBean tshb = new TaxSlabHeadBean();
                tshb.setSlabHeadCode(ss.getSlabHeadCode());
                empsd.setSlabSelected(true);
                empsd.setSlabCode(tshb);
                empsd.setSlabeName(ss.getSlabName());
                emplSlabDetails.add(empsd);
            
            }
            return emplSlabDetails; 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }   
    
    }
}    
