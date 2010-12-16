/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import org.smvdu.payroll.beans.db.EmpTypeHeadDB;

/**
 *
 * @author Algox
 */
public class SalaryMap implements ActionListener,ValueChangeListener{


    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    

    private int typeCode =2;
    private int[] salaryCode;

    public int[] getSalaryCode() {
        if(salaryCode==null)
        {
            salaryCode = new int[0];
        }
        return salaryCode;
    }

    public void setSalaryCode(int[] salaryCode) {
        if(salaryCode==null)
        {
            System.err.println(" >> No Selection");
            return;
        }
        for(int i: salaryCode)
        {
            System.err.println(" > Code : "+i);
        }
        this.salaryCode = salaryCode;

        
    }


    
    public void print()
    {
        boolean b = new EmpTypeHeadDB().save(this);
        if(b)
        {
            message = "Data Saved";
        }
 else
        {
            message = "Data NOT Saved";
 }
    }


    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {

        this.typeCode = typeCode;
    }

    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {

        PhaseId phaseId = event.getPhaseId();
        if (phaseId.equals(PhaseId.ANY_PHASE)) {
            event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
            event.queue();
        } else if (phaseId.equals(PhaseId.UPDATE_MODEL_VALUES)) {
// do you method here
        }
        int newValue = (Integer) event.getNewValue();
        typeCode = newValue;
        ArrayList<Integer> ints = new EmpTypeHeadDB().getHeadCodes(newValue);
        salaryCode = new int[ints.size()];
        for(int i=0;i<ints.size();i++)
        {
            salaryCode[i]= ints.get(i);
        }
        System.out.println(" New Value :"+newValue + " Selectest Length : "+ints.size());
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(),null,"test");
    }

}
