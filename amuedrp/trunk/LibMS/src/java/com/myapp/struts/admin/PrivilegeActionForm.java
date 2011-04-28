/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

/**
 *
 * @author Dushyant
 */
public class PrivilegeActionForm extends org.apache.struts.action.ActionForm {
    
    private String privilege_list;
     private String staff_id;
private String button;
private String staff_name;


    
    public PrivilegeActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     
     */


    /**
     * @return the privilege_list
     */
    public String getPrivilege_list() {
        return privilege_list;
    }

    /**
     * @param privilege_list the privilege_list to set
     */
    public void setPrivilege_list(String privilege_list) {
        this.privilege_list = privilege_list;
    }

    /**
     * @return the staff_id
     */
    public String getStaff_id() {
        return staff_id;
    }

    /**
     * @param staff_id the staff_id to set
     */
    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
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
     * @return the staff_name
     */
    public String getStaff_name() {
        return staff_name;
    }

    /**
     * @param staff_name the staff_name to set
     */
    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }
}
