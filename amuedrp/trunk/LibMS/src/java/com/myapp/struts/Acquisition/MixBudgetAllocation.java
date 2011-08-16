/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;
import com.myapp.struts.hbm.AcqBudget;
import com.myapp.struts.hbm.AcqBudgetAllocation;
import java.io.Serializable;

/**
 *
 * @author System Administrator
 */
public class MixBudgetAllocation implements Serializable{
    private AcqBudget acqBudget;
    private AcqBudgetAllocation  acqBudgetAllocation;

    public AcqBudget getAcqBudget() {
        return acqBudget;
    }

    public void setAcqBudget(AcqBudget acqBudget) {
        this.acqBudget = acqBudget;
    }

    public AcqBudgetAllocation getAcqBudgetAllocation() {
        return acqBudgetAllocation;
    }

    public void setAcqBudgetAllocation(AcqBudgetAllocation acqBudgetAllocation) {
        this.acqBudgetAllocation = acqBudgetAllocation;
    }

   
    

}
