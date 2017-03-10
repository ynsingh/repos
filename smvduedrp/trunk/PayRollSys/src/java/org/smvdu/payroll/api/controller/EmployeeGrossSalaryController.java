/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.smvdu.payroll.Hibernate.HibernateUtil;
import org.smvdu.payroll.api.MatrixTransformer;
import org.smvdu.payroll.api.report.IndividualGrossDB;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.composite.SessionController;
import org.smvdu.payroll.module.attendance.LoggedEmployee;
import org.smvdu.payroll.user.ActiveProfile;

/**
*
*  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  Copyright (c) 2014 - 2017 IIT, Kanpur. 
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
*  @author : Manorama Pal (palseema30@gmail.com), IITK  
*
*/
public class EmployeeGrossSalaryController{

    private String dateFrom;
    private String dateTo;
    private UserInfo uf;

    private int orgCode;
    private HibernateUtil helper;
    private Session session;

    public EmployeeGrossSalaryController()
    {
        LoggedEmployee le = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        if(le==null)
        {
            uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            orgCode = uf.getUserOrgCode();
        }
        else
        {
            orgCode = le.getUserOrgCode();
        }
    }


    private Employee employee;

    public Employee getEmployee() {
        ActiveProfile ac = (ActiveProfile)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ActiveProfile");
        employee = ac.getProfile();
       //System.out.println("ac==="+ac);
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    private ArrayList<GrossData> salaryMatrix;

    public ArrayList<GrossData> getSalaryMatrix() {


        salaryMatrix = MatrixTransformer.transform(new IndividualGrossDB().fetchSalaryMatrix(employee.getCode()),employee.getCode());
        return salaryMatrix;
    }

    public void setSalaryMatrix(ArrayList<GrossData> salaryMatrix) {
        this.salaryMatrix = salaryMatrix;
    }
    
    private String financialyear;
    
    public String getFinancialYear(){
        
        SessionController currentsess = new SessionController();
        try{
            session = helper.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("select sm.startDate, sm.endDate from SessionMaster sm  where sm.code ='"+currentsess.getCurrentSession()+"'");
            //System.out.println("Query==="+query.getQueryString());
            List<Object[]>smaster = query.list();
            for(Object[] sms : smaster)
            {
                String[] stparts;
                stparts = sms[0].toString().split("-");
                String stpart1 = stparts[0];
                String[] endparts = sms[1].toString().split("-");
                String endpart1 = endparts[0];
               
                financialyear=stpart1+" - "+endpart1;
            }
            session.getTransaction().commit();
            return financialyear;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }
    }

    public void setFinancialYear(String financialyear){
        this.financialyear = financialyear;
    }
   
}
