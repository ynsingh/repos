/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.actions;

import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

/**
 *
 * @author Algox
 */
public class EmpCodeListener implements ValueChangeListener{

    

    @Override
    public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {
        String code = (String) event.getNewValue();
        System.err.println(" >> Code : "+code);
    }

}
