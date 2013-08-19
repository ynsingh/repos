/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.TaxGender;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
/**
 *
 * @author ERP
 */
public class TaxSlabHeadBean {

    /** Creates a new instance of TaxSlabHeadBean */
    public TaxSlabHeadBean() {
    }

    private String slabName = new String();
    private float startSlabValue ;
    private float endSlabValue;
    private float percent;
    private int slabHeadCode;
    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
    private ArrayList<TaxSlabHeadBean> taxHeadValue = new ArrayList<TaxSlabHeadBean>();
    public float getEndSlabValue() {
        return endSlabValue;
    }

    public void setEndSlabValue(float endSlabValue) {
        this.endSlabValue = endSlabValue;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public int getSlabHeadCode() {
        return slabHeadCode;
    }

    public void setSlabHeadCode(int slabHeadCode) {
        this.slabHeadCode = slabHeadCode;
    }

    public String getSlabName() {
        return slabName;
    }

    public void setSlabName(String slabName) {
        this.slabName = slabName;
    }

    public float getStartSlabValue() {
        return startSlabValue;
    }

    public void setStartSlabValue(float startSlabValue) {
        this.startSlabValue = startSlabValue;
    }

    public ArrayList<TaxSlabHeadBean> getTaxHeadValue() {
        taxHeadValue = new TaxSlabHeadConttroler().loadSlabHead();
        return taxHeadValue;
    }

    public void setTaxHeadValue(ArrayList<TaxSlabHeadBean> taxHeadValue) {
        this.taxHeadValue = taxHeadValue;
    }
    
    public void saveSlabHead()
    {
        try
        {
            Exception ex = new TaxSlabHeadDB().saveSlabHead(this);
            if(ex == null)
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Slab Name : "+ this.getSlabName()+" Added", ""));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, ""+ex, ""));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
