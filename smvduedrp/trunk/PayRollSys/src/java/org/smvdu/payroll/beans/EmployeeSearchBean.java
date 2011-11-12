/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.db.EmployeeDB;
import org.smvdu.payroll.module.attendance.LoggedEmployee;

/**
 * Provides various searching criteria integration to Employee search records. It calls
 * EmployeeDB.search to fetch records. It is available as SearchBean as managed bean.
 * This class is responsible for providing popup results in almost all UI window
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
*
 */
public class EmployeeSearchBean {



    public EmployeeSearchBean()
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


    private int typeCode = -1;
    private int deptCode = -1;
    private int desigCode = -1;
    private String name = "";
    private String st;

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = new Employee().getStatusI();
    }
    private int orgCode=0;

    
    private ArrayList<Employee> emps;

    public ArrayList<Employee> getEmps() {
        emps = new EmployeeDB().loadProfiles(" where emp_org_code="+orgCode);
        return emps;
    }

    public void setEmps(ArrayList<Employee> emps) {
        this.emps = emps;
    }
    
    private ArrayList<Employee> results;

    public int getDeptCode() {
        return deptCode;
    }
    public void setDeptCode(int deptCode) {
        this.deptCode = deptCode;
    }
    public int getDesigCode() {
        return desigCode;
    }
    public void setDesigCode(int desigCode) {
        this.desigCode = desigCode;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getTypeCode() {
        return typeCode;
    }
    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }



    public void search()
    {
        getAll();
    }


    public ArrayList<Employee> getSuggestion(Object obj)
    {
        return  new EmployeeDB().loadProfiles("where emp_name like '"+obj.toString()+"%' and emp_org_code="+orgCode);
    }

    
    public String buildString()
    {
        String cri= " where ";
        if(deptCode>0)
        {
            cri+=" emp_dept_code="+deptCode +" and ";
        }
        if(desigCode>0)
        {
            cri+=" emp_desig_code="+desigCode +" and ";
        }
        if(typeCode>0)
        {
            cri+=" emp_type_code="+typeCode +" and ";
        }
        cri+=" emp_name like '"+name+"%' and emp_org_code="+orgCode;
        return cri;
    }



    private SelectItem[] asItem;
    private ArrayList<Employee> all;

    public void setAll(ArrayList<Employee> all) {
        this.all = all;
    }
    

    public SelectItem[] getAsItem() {
        ArrayList<Employee> empsx= new EmployeeDB().loadProfiles(buildString());
        asItem = new SelectItem[empsx.size()];
        Employee em = null;
        for(int i=0;i<empsx.size();i++)
        {
            em = empsx.get(i);
            SelectItem si = new SelectItem(em.getEmpId(), em.getName());
            asItem[i] = si;
        }
        return asItem;
    }

    public void setAsItem(SelectItem[] asItem) {
        this.asItem = asItem;
    }

    
    
    

    public ArrayList<Employee> getAll()   {
       results = new EmployeeDB().loadProfiles(buildString());
       //FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, results.size()+" results.", ""));
       return results;
   }
    
}
