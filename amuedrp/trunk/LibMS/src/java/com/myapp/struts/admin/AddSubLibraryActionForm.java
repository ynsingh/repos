/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author edrp02
 */
public class AddSubLibraryActionForm extends org.apache.struts.action.ActionForm {
    
    private String faculty;
    private String faculty1;
    private String sublib_name;
    private String sublib_name1;
    private String sublibrary_id;
 
    private String department_address;
    private String button;

   

    public String getDepartment_address() {
        return department_address;
    }

    public void setDepartment_address(String department_address) {
        this.department_address = department_address;
    }

   
   

  

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

  
  

    public String getSublib_name() {
        return sublib_name;
    }

    public void setSublib_name(String sublib_name) {
        this.sublib_name = sublib_name;
    }

    public String getSublibrary_id() {
        return sublibrary_id;
    }

    public void setSublibrary_id(String sublibrary_id) {
        this.sublibrary_id = sublibrary_id;
    }
   

    

    /**
     * @return
     */
   

    /**
     *
     */
    public AddSubLibraryActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the button
     */
    public String getButton() {
        return button;
    }

    /**
     * @param button the button to set
     */
    public void setButton(String button) {
        this.button = button;
    }

    /**
     * @return the faculty1
     */
    public String getFaculty1() {
        return faculty1;
    }

    /**
     * @param faculty1 the faculty1 to set
     */
    public void setFaculty1(String faculty1) {
        this.faculty1 = faculty1;
    }

    /**
     * @return the sublib_name1
     */
    public String getSublib_name1() {
        return sublib_name1;
    }

    /**
     * @param sublib_name1 the sublib_name1 to set
     */
    public void setSublib_name1(String sublib_name1) {
        this.sublib_name1 = sublib_name1;
    }

   
    
}
