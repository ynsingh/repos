/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.db;


/**
*  Copyright (c) 2010, 2011, 2014 SMVDU Katra.
*  Copyright (c) 2014 - 2017 ETRG, IITK.
*  All Rights Reserved.
** Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
** Redistributions of source code must retain the above copyright 
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
*  Contributors: Members of ERP Team @ SMVDU, Katra, IITKanpur
*  @author  Manorama Pal (palseema30@gmail.com)
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.FacesContext;
import static org.apache.commons.lang.StringUtils.substringAfterLast;
import static org.apache.commons.lang.StringUtils.substringBeforeLast;
import org.hibernate.Query;
import org.hibernate.Session;
import org.smvdu.payroll.Hibernate.HibernateUtil;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.setup.SalaryHead;

public class LedgerMapDB {
    private final UserInfo userBean;
    private HibernateUtil helper;
    private Session session;
    
    public LedgerMapDB(){
        userBean = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
    }
    
    /** Load Expenditure Ledgers and Ledgers Code from BGAS
     * @return ArrayList
     */
    public ArrayList<SalaryHead> loadExpLedgersfromBGAS()   {
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            Connection c = new CommonDB().getbgasConnection();
            ArrayList<SalaryHead> data = new ArrayList<SalaryHead>();
            ps = c.prepareStatement("select ledgers.code, ledgers.name from ledgers "
                 +" where ledgers.code like '"+4001+"%' or ledgers.code like '"+100401+"%' or ledgers.code  like '"+2004+"%' ");
            rs=ps.executeQuery();
            
            while(rs.next()){
                SalaryHead bgashead = new SalaryHead();
                bgashead.setLedgerCode(rs.getString(1));
                bgashead.setLedgerName(rs.getString(2));
                data.add(bgashead);
              
            }    
            rs.close();
            ps.close();
            c.close();
            return data;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
             
    }
    
    /**Method for creating xml file
     * writing the mapped heads with BGAS ledger name and code
     * @ see XMLWriterUtil
     * @param heads 
     * @return 
     */
    
       
    public boolean mapLedgers(ArrayList<SalaryHead> heads)  {
        boolean flage=false;
        try
        {
            session = helper.getSessionFactory().openSession();
            //session.beginTransaction();
            
            for(SalaryHead sh : heads)
            {
                //System.out.println("heads==="+sh.getBgasLedger()+"number==="+sh.getNumber());
                String bledger = sh.getBgasLedger();
                System.out.println("bledger===="+bledger);
               /* String[] parts = bledger.split("-");
                String ledgername = parts[0]; 
                String ledgercode = parts[1];*/
                String ledgername= substringBeforeLast(bledger, "-");
                String ledgercode= substringAfterLast(bledger, "-");
                System.out.println("bledger===="+bledger+"ledgername==="+ledgername+"ledgercode==="+ledgercode);
                session.beginTransaction();
                SalaryHead s = (SalaryHead)session.get(SalaryHead.class, sh.getNumber());
                System.out.println("headcode===="+sh.getNumber()+"ledgecode==="+ledgercode);
                //Query query1 = session.createQuery("update SalaryHead  set sh_ledger_code= '"+ledgercode+"' where id = '"+sh.getNumber()+"'");
                s.setNumber(sh.getNumber());
               // if(ledgercode.equals("Select Ledger"))
                //s.setLedgerCode(null);
                //else
                s.setLedgerCode(ledgercode); 
                session.update(s);
                System.out.println("headcode===="+sh.getNumber()+"ledgecode==="+ledgercode);
                //query1.executeUpdate();
                
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
        return flage;
       
    }
    
    /** Method for load all Mapped heads and ledgers according to chart of account
     * @param coaformat
     * @param heads
     * @return ArrayList of SalaryHeads
     */
   
    public ArrayList<SalaryHead> loadExpLedgers_Heads(String coaformat, ArrayList<SalaryHead> data){
        try
        {
            //System.out.println("coaformat===="+coaformat);  
            session = helper.getSessionFactory().openSession();
            session.beginTransaction();
         
            Query query = session.createQuery(" select sh.number, sh.shcode, sh.name, sh.alias, sh.under, sh.calculationType, sh.type, sh.ledgerCode  from SalaryHead sh where sh.orgcode = '"+userBean.getUserOrgCode()+"'");
        
            data = new ArrayList<SalaryHead>();  
            List result = query.list();
            for (Iterator it = result.iterator(); it.hasNext(); ) {
                Object[] myResult = (Object[]) it.next();
                SalaryHead s = new SalaryHead();
                s.setNumber((Integer) myResult[0]);
                s.setShcode((String) myResult[1]);
                s.setName((String) myResult[2]);
                s.setAlias((String) myResult[3]);
                s.setUnder((Boolean) myResult[4]);
                s.setCalculationType((Boolean) myResult[5]);
                s.setType((Boolean) myResult[6]);
                s.setLedgerCode((String)myResult[7]);
                if(coaformat!=null){
                s.setCoaFormat(coaformat);
                }
                String ledgername=getLedgerNamefromBGAS((String)myResult[7]);
                s.setBgasLedger(ledgername+"-"+(String)myResult[7]);
                //System.out.println("coaformat===="+coaformat+": "+s.getBgasLedger()+":"+s.getLedgerCode()+":"+s.getLedgerName());  
                      
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
        
    }    
    
    
    
    public String getLedgerNamefromBGAS(String ledgercode){
        PreparedStatement ps;
        ResultSet rs;
        String ledgername="";
        try
        {
            Connection c = new CommonDB().getbgasConnection();
            ArrayList<SalaryHead> data = new ArrayList<SalaryHead>();
            ps = c.prepareStatement("select  ledgers.name from ledgers "
                 + "where ledgers.code='"+ledgercode+"' ");
            
            rs=ps.executeQuery();
            
            while(rs.next()){
              ledgername=rs.getString(1);
            }    
            rs.close();
            ps.close();
            c.close();
            //System.out.println("ledgername==="+ledgername);
            return  ledgername;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
             
    }
    
    
   
}
