/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class CreateAccountActionForm extends org.apache.struts.action.ActionForm {
    
    private String user_name;
    private String staff_id;
    private String password;
    private String login_id;
    private String sublibrary_id;
    private String role;
    private String button;
    private String question;
    private String ans;

   

    /**
     *
     */
    public CreateAccountActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

  
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
   
            // TODO: add 'error.name.required' key to your resources

    return null;
    }
    

    /**
     * @return the first_name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * @param first_name the first_name to set
     */
    public void setUser_name(String first_name) {
        this.user_name = first_name;
    }

    /**
     * @return the last_name
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the login_id
     */
    public String getLogin_id() {
        return login_id;
    }

    /**
     * @param login_id the login_id to set
     */
    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    /**
     * @return the sublibrary_id
     */
    public String getSublibrary_id() {
        return sublibrary_id;
    }

    /**
     * @param sublibrary_id the sublibrary_id to set
     */
    public void setSublibrary_id(String sublibrary_id) {
        this.sublibrary_id = sublibrary_id;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the ans
     */
    public String getAns() {
        return ans;
    }

    /**
     * @param ans the ans to set
     */
    public void setAns(String ans) {
        this.ans = ans;
    }
}
