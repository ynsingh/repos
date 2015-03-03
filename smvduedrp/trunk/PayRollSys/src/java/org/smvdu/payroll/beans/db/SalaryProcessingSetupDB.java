/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.smvdu.payroll.Hibernate.HibernateUtil;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.setup.SalaryProcessingSetup;

/**
 *
 +  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
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
 
public class SalaryProcessingSetupDB {
        private int orgcode;
        private PreparedStatement ps;
        private ResultSet rs;
        private HibernateUtil helper;
        private Session session;
        
        
        
        public SalaryProcessingSetupDB() {
            try {
            
                    UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
                    orgcode = uf.getUserOrgCode();
            } 
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        public  ArrayList<SalaryProcessingSetup> activeSalaryprocessmode()
        {
            
         try {
            session =helper.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from SalaryProcessingSetup where status = 1 and orgcode = '"+orgcode+"'");
            ArrayList<SalaryProcessingSetup> data = (ArrayList<SalaryProcessingSetup>)query.list();
            ArrayList<SalaryProcessingSetup> activemode =  new ArrayList<SalaryProcessingSetup>();
                    SalaryProcessingSetup sps = new SalaryProcessingSetup();
            for(SalaryProcessingSetup s : data)
            {
                sps.setSalaryprocessmode(s.getSalaryprocessmode());
                activemode.add(sps);
            }
            session.getTransaction().commit();
            return activemode;
         }
         catch(Exception ex){
            session.getTransaction().rollback();
            ex.printStackTrace();
            return null;
         }
         finally{
            session.close();
        }           
            
            
        /*    
                try
                {
                    ArrayList<SalaryProcessingSetup> salmode = new ArrayList<SalaryProcessingSetup>();
                    Connection connection = new CommonDB().getConnection(); 
                    ps = connection.prepareStatement("select salary_process_mode  from Salary_processing_setup where flag=1 and org_id='"+orgcode+"' ");
                    rs = ps.executeQuery();
            
                    while(rs.next())
                    {
                            SalaryProcessingSetup salprosetup = new SalaryProcessingSetup();
                            salprosetup.setSalaryprocessmode(rs.getString(1));
                            salmode.add(salprosetup );
                    }

                    //System.out.println("activeSalaryprocessmode: "+salmode);
                    rs.close();
                    ps.close();
                    connection.close();
                    return salmode;
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                    return null;
                }              */
        }
        
        
        public ArrayList<SalaryProcessingSetup> AllDetailSalaryProcessMode()
        {
         
           try
            {
                session = helper.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("from SalaryProcessingSetup where orgcode = '"+orgcode+"'");
                ArrayList<SalaryProcessingSetup> data = (ArrayList<SalaryProcessingSetup>)query.list();
                session.getTransaction().commit();
                return data;           
            }
            catch(Exception e){
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
                        ArrayList<SalaryProcessingSetup> salmode = new ArrayList<SalaryProcessingSetup>();
                        Connection connection = new CommonDB().getConnection(); 
                        PreparedStatement pst;
                        ResultSet rst;
                        pst = connection.prepareStatement("select * from Salary_processing_setup where org_id='"+orgcode+"' ");
                        rst = pst.executeQuery();
                        while(rst.next())
                        {
                                SalaryProcessingSetup salprosetup = new SalaryProcessingSetup();
                                salprosetup.setId(rst.getInt(1));
                                salprosetup.setSalaryprocessmode(rst.getString(2));
                                salprosetup.setStatus(rst.getInt(3));
                                salmode.add(salprosetup ); 
                        }
                        rst.close();
                        pst.close();
                        connection.close();
                        return salmode;
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                    return null;
                }                */  
        }    
 
        public Exception update(ArrayList<SalaryProcessingSetup> salprosetup)    {
          
            try{
                int st=0;
                session = helper.getSessionFactory().openSession();
                
                for(SalaryProcessingSetup sps : salprosetup)
                {
                    if(sps.getStatus()==1){
                            st=0;   
                    }
                    else{
                            st=1;
                    }
                    session.beginTransaction();
                    SalaryProcessingSetup data = (SalaryProcessingSetup)session.get(SalaryProcessingSetup.class, sps.getId());
                    data.setStatus(st);
                    session.update(data);
                    session.getTransaction().commit();
                }
                return null;         
            }
            catch(Exception e){
                session.getTransaction().rollback();
                e.printStackTrace();
                return e;
            }
            finally{
                session.close();
            }      
                
           /* 
                try
                {
                        int st=0;
                        Connection c = new CommonDB().getConnection();
                        for(SalaryProcessingSetup sps : salprosetup)
                        {
                                if(sps.getStatus()==1){
                                        st=0;   
                                }
                                else{
                                        st=1;
                                }
                                ps=c.prepareStatement("update Salary_processing_setup set flag='"+st+"' where salary_process_mode ='"+sps.getSalaryprocessmode()+"' and org_id = '"+orgcode+"'");
                                //System.out.println(" updation===activeSalaryprocessmode:==== "+sps.getStatus());
                                //System.out.println("updation===activeSalaryprocessmode:===="+sps.getsalaryprocessmode());
                                ps.executeUpdate();
                                ps.clearParameters();
                        }
                        ps.close();
                        c.close();
                        return null;
                }
                catch(Exception e)
                {
                    return e;
           
                }          */
        }
        
    
}