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

/**
 *
 * @author Algox
 */
public class EMailValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
       String enteredEmail = (String)value;
       Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
       Matcher m = p.matcher(enteredEmail);
       boolean matchFound = m.matches();

       if (!matchFound) {
             FacesMessage message = new FacesMessage();
             message.setSummary("Invalid Email ID.");
             throw new ValidatorException(message);
       }
   
    }

}
