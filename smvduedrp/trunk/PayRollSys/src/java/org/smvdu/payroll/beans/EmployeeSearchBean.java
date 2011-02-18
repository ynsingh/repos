/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import org.smvdu.payroll.beans.db.EmployeeDB;

/**
 *
 * @author Algox
 */
public class EmployeeSearchBean {

    private int typeCode = -1;
    private int deptCode = -1;
    private int desigCode = -1;
    private String name = "";

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
        return  new EmployeeDB().loadProfiles("where emp_name like '"+obj.toString()+"%'");
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
        cri+=" emp_name like '"+name+"%'";
        return cri;
    }

    public ArrayList<Employee> getAll()   {
       results = new EmployeeDB().loadProfiles(buildString());
       //FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, results.size()+" results.", ""));
       return results;
   }
    
}
