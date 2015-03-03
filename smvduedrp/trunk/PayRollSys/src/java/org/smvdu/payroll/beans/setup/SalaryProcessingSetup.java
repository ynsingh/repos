/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.setup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.smvdu.payroll.Hibernate.HibernateUtil;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.SalaryProcessingSetupDB;


/**
 *
 *  *  Copyright (c) 2010 - 2011,2013,2014 SMVDU, Katra, IIT Kanpur.
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
 *  Contributors: Members of ERP Team @ SMVDU, Katra, IIT Kanpur.
 *  @author <a href="mailto:kishore.shukla@gmail.com">Kishore Kumar Shukla</a>
 *  @author <a href="mailto:palseema@rediffmail.com">Manorama Pal</a>
 *
 */
public class SalaryProcessingSetup  implements Serializable{
    
    private int orgcode;

    public int getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(int orgcode) {
        this.orgcode = orgcode;
    }
    private String imgUrl;
    private int status;
    private String salarypromode;
    private int Id;
    private boolean active;
    private boolean inactive;
    PreparedStatement pst;
    ResultSet rst;
    private HibernateUtil helper;
    private Session session;
    
    public SalaryProcessingSetup()
    {
        UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        orgcode = uf.getUserOrgCode();
                
    }
    
         
    public boolean getActive() {
        activeStatusfirst();
        //System.out.println("is status=get===flag=======: "+active);
        return active ;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public boolean getInactive() {
        activeStatussecond();
        //System.out.println("is status=get====inactive======: "+inactive);
        return inactive;
    }
    
    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }
    
    public int getStatus() {
        //System.out.println("is status=get===status=======: "+status);
        return status;
    }
    public void setStatus(int status) {
       // System.out.println("is status=set===status=======: "+status);
        this.status = status;
    }
    
     public String getImgUrl() {
        return imgUrl;
    }
     
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    private String salaryprocessmode;
    
    public String getSalaryprocessmode() {
        //System.out.println("salpromode ----in new check: "+salaryprocessmode);
        return salaryprocessmode;
    }

    public void setSalaryprocessmode(String salaryprocessmode) {
        //System.out.println("salpromode ----in new check: "+salaryprocessmode);
        
        this.salaryprocessmode = salaryprocessmode;
    }
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    private ArrayList<SalaryProcessingSetup> salpromode;
    
    public ArrayList<SalaryProcessingSetup> getsalpromode()
    {        
	return new SalaryProcessingSetupDB().activeSalaryprocessmode();
                   
    }
    
    public void setsalpromode(ArrayList<SalaryProcessingSetup> salpromode) {
        this.salpromode = salpromode;
    }

    public void activeStatussecond()
    {
        try {
          boolean b = false;
          String spb = "Salary Processing with Budget";
          session = helper.getSessionFactory().openSession();
          session.beginTransaction();
          Query query = session.createQuery("select status from SalaryProcessingSetup where salaryprocessmode = '"+spb+"' and orgcode = '"+orgcode+"' ");
          ArrayList<Integer> flag =  (ArrayList<Integer>)query.list();
          session.getTransaction().commit();
               for(Integer f : flag)
                if(f==1) {
                    b=false;               
                } 
                else {
                    b=true;
                }
          
            setInactive(b);
      } 
      catch(Exception e) {
          session.getTransaction().rollback();
          e.printStackTrace();

      }     
      finally{
          session.close();
      }     
                
   /*     
            try
        {
                boolean b=false;
                String spb = "Salary Processing with Budget";
                Connection connection = new CommonDB().getConnection(); 
                pst = connection.prepareStatement("select flag from Salary_processing_setup"
                       +" where salary_process_mode='"+spb+"' and org_id='"+orgcode+"' ");
                rst = pst.executeQuery();
                rst.next();
                //System.out.println("second=====stat=============="+rst.getInt(1));
                if(rst.getInt(1)== 1){
                    b=false;
                                
                } 
                else{
                     b=true;
                }
                setInactive(b);
                                        
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
                    //return false;
        }              */
    }
      
    public void  activeStatusfirst()
    {
     
        try {
          boolean b = false;
          String spb = "Salary Processing";
          session = helper.getSessionFactory().openSession();
          session.beginTransaction();
          Query query = session.createQuery("select status from SalaryProcessingSetup where salaryprocessmode = '"+spb+"' and orgcode = '"+orgcode+"' ");
          ArrayList<Integer> flag =  (ArrayList<Integer>)query.list();
          session.getTransaction().commit();
          for(Integer f : flag) {
             if(f==1) {
                 b=false;               
             } 
             else {
                 b=true;
             }
          }      
          setActive(b);

      } 
      catch(Exception e) {
          session.getTransaction().rollback();
          e.printStackTrace();
      }     
      finally{
          session.close();
      }         
    /*    
          try
        {
                boolean b=false;
                String spb = "Salary Processing";
                Connection connection = new CommonDB().getConnection(); 
                pst = connection.prepareStatement("select flag from Salary_processing_setup"
                         +" where salary_process_mode='"+spb+"' and org_id='"+orgcode+"' ");
                rst = pst.executeQuery();
                rst.next();
                //System.out.println("second=====stat=============="+rst.getInt(1));
                if(rst.getInt(1)== 1){
                    b=false;
                               
                } 
                else{
                    b=true;
                }
                setActive(b);
                                    
        } 
        catch(Exception ex)
        {
            ex.printStackTrace();
        }       */               
    }
}
