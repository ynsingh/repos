/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author edrp02
 */
public class CourseActionForm extends org.apache.struts.action.ActionForm {
    
    private String course_id;
    private String faculty_id;
    private String dept_id;
    private String button;

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }
    

    

    /**
     * @return
     */
  

    /**
     *
     */
    public CourseActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        
        return null;
    }

    /**
     * @return the faculty_id
     */
    public String getFaculty_id() {
        return faculty_id;
    }

    /**
     * @param faculty_id the faculty_id to set
     */
    public void setFaculty_id(String faculty_id) {
        this.faculty_id = faculty_id;
    }

    /**
     * @return the dept_id
     */
    public String getDept_id() {
        return dept_id;
    }

    /**
     * @param dept_id the dept_id to set
     */
    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }
}
