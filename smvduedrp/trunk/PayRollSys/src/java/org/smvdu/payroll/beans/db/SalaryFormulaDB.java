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
import org.smvdu.payroll.beans.SalaryFormula;
import org.smvdu.payroll.beans.UserInfo;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.smvdu.payroll.Hibernate.HibernateUtil;
import org.smvdu.payroll.beans.setup.SalaryHead;

/**
*  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
*  Redistribution and use in source and binary forms, with or 
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
*  Modified Date: 16 Sep 2013, IITK (palseema@rediffmail.com, kshuklak@rediffmail.com)
*
*/

public class SalaryFormulaDB {
    private PreparedStatement ps;
    private ResultSet rs;
    private final UserInfo userBean;
    private HibernateUtil helper;
    private Session session;
    

     public SalaryFormulaDB()
     {
         userBean = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
     }
    public String loadFormula(int code)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sf_sal_formula from salary_formula "
                    + "left join salary_head_master on sh_id = sf_sal_id where sf_sal_id=?");
            ps.setInt(1, code);
            rs=ps.executeQuery();
            rs.next();
            String formula = rs.getString(1);
            rs.close();
            ps.close();
            c.close();
            return formula;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }
    public ArrayList<SalaryFormula> loadFormula()    {
  
         try
        {
            ArrayList<SalaryFormula> data = new ArrayList<SalaryFormula>();
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("select sh.number, sh.name, sf.formula from SalaryHead  sh left join sh.salaryFormula sf where sh.calculationType=1");
            List result = query.list();
            
            for (Iterator it = result.iterator(); it.hasNext(); ) {
                Object[] myResult = (Object[]) it.next();
                SalaryFormula s = new SalaryFormula();
                
              //  SalaryHead sal = new SalaryHead();
                //sal.setNumber((Integer) myResult[0]);
                
                //s.setSalaryHead(sal);
                
                s.setSalCode((Integer) myResult[0]);
                s.setName((String) myResult[1]);
                s.setFormula((String) myResult[2]);
                //s.setId((Integer) myResult[3]);
                data.add(s);

            } 
            
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
        
        
        /*    try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sh_id,sh_name,sf_sal_formula from  salary_head_master "
                    + "left join  salary_formula on sf_sal_id = sh_id where sh_calc_type=1");
            rs=ps.executeQuery();
            ArrayList<SalaryFormula> data = new ArrayList<SalaryFormula>();
            while(rs.next())
            {
                SalaryFormula sf = new SalaryFormula();
                sf.setSalCode(rs.getInt(1));
                sf.setName(rs.getString(2));
                sf.setFormula(rs.getString(3));
                data.add(sf);
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
        }           */
    }
    /** This method is used to insert and update the formula
     * @param editedRecord (SalaryFormula object get from jsp file and setter getter method in SalaryFormula bean)
     * @return boolean 
     */
    public boolean save(SalaryFormula editedRecord)    {
        try{
            /*get fcode from the database to check that is exists or not
            * @param editedRecord (SalaryFomula) deatil of selected Head
            * @see dataExist method
            * @return int
            */
            int fcode= dataExist(editedRecord);
           // System.out.println("obj=fcode =="+fcode);
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //System.out.println("obj=1 =="+editedRecord.getFormula()+":"+editedRecord.getSalCode());
            /**update the formula of SalaryHead if exists
             * update formula according to salaryHead code and orgId 
             */
            if(fcode!=-1){
               //  Transaction tx=session.beginTransaction();  
                String hql = "update SalaryFormula set formula = :formula where salaryHead.number = :number and orgcode= :orgcode";
                Query query = session.createQuery(hql);
                query.setParameter("formula",editedRecord.getFormula());
                query.setParameter("number", editedRecord.getSalCode());
                query.setParameter("orgcode", userBean.getUserOrgCode());
                System.out.println(editedRecord.getFormula()+":"+editedRecord.getSalCode());
                query.executeUpdate();
                int rowsAffected = query.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Updated " + rowsAffected + " rows.");
                } 
            }     
            else{
                /**Insert the formula of SalaryHead if not exists
                *insert formula according to salaryHead code and orgId 
                */
                //System.out.println("obj=in ifcase =="+editedRecord.getFormula()+":"+editedRecord.getSalCode());
                SalaryFormula obj = new SalaryFormula();
               
                SalaryHead sal = new SalaryHead();
                sal.setNumber(editedRecord.getSalCode());
                obj.setSalaryHead(sal);
                
                obj.setFormula(editedRecord.getFormula());
                obj.setOrgcode(userBean.getUserOrgCode());
                session.save(obj);
            }
            session.getTransaction().commit();
            return true;
                      
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
        finally {
            Connection close = session.close();
        }
              
    }
    
    /** Select SalaryHead code  from SalaryFormula table for the update
     * if exists return the salary Head code if not exists return -1 
     * @param editedRecord (SalaryFromula )
     * @return integer (SalaryHead Code)
     */
    
    public int dataExist(SalaryFormula editedRecord){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String hql2 = "from SalaryFormula where salaryHead.number = :salCode and orgcode= :orgcode";
            Query query1 = session.createQuery(hql2);
            query1.setParameter("salCode", editedRecord.getSalCode());
            query1.setParameter("orgcode", userBean.getUserOrgCode());
            Object queryResult = query1.uniqueResult();
           // System.out.println("dataexists==query=result="+queryResult.toString());
            SalaryFormula sfrm = (SalaryFormula) queryResult;
            //System.out.println("dataexists==sfrm=="+sfrm.getSalaryHead().getNumber());
            int fcode=sfrm.getSalaryHead().getNumber();
            session.getTransaction().commit();
            return fcode;
        }
        catch (Exception e) {
            return -1;
        }
        
    }
        
}
