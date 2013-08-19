/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.smvdu.payroll.beans.Employee;

/**
 *
 * @author ERP
 */
public class AllValidator implements Validator {

    /** Creates a new instance of AllValidator */
    public AllValidator() {
    }
    private boolean un;

    public boolean isUn() {
        return un;
    }

    public void setUn(boolean un) {
        this.un = un;
    }
    private  String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private String USER_NAME_PATTERN = "^[a-zA-Z\\s]*$";
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        try {
            //String message = new String();
            System.out.println("Hi.........................");
            Pattern pattern;
            Matcher matcher;
            String inputValue = (String) o;
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(inputValue);
            Employee emp = new Employee();
            if (matcher.find() == false) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Email is not valid.");
                message.setDetail("Email is not valid.");
                fc.addMessage("profileForm:Email", message);
                emp.setEmailIdValid(false);
                throw new ValidatorException(message);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void validateUserName(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        String userName=(String)o;
        if(userName.matches(USER_NAME_PATTERN) == false)
        {
            System.out.println("Validated : ");
            Employee emp = new Employee();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Plz Enter Valid First Name");
            //message.setDetail("First Name Must Be At Least Three Charecter ");
            fc.addMessage("", message);
            this.setUn(false); 
            throw new ValidatorException(message);
        }
    }
}
