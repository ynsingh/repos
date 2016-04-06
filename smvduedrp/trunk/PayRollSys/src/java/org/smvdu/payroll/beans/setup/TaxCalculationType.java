/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.setup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.composite.SessionController;

/**
 *
 * @author shikhar
 */
public class TaxCalculationType implements Serializable{
    private String calcType;
    //private List<SelectItem> radios = null;
    //private String selectedItemValueSelectOneRadio = "";
    private int orgCode;
    private int session;
    SessionController sessionId = new SessionController();
    
    public TaxCalculationType()
    {
        UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        orgCode = uf.getUserOrgCode();
        session=sessionId.getCurrentSession();
        
         
        
       /* radios = new ArrayList<SelectItem>();
        SelectItem item1 = new SelectItem("Yearly","Yearly");
        SelectItem item2 = new SelectItem("Quartly","Quartly");
        SelectItem item3 = new SelectItem("Monthly","Monthly");
        
        radios.add(item1);
        radios.add(item2);
        radios.add(item3);*/
        
    }
    
     /**
     * @return the radios
     */
   /* public List<SelectItem> getRadios() {
        return radios;
    }*/

    /**
     * @param radios the radios to set
     */
   // public void setRadios(List<SelectItem> radios) {
    //    this.radios = radios;
    //}
    
    
    /**
    * @return the selectedItemValuesSelectOneRadio
    */
       // public String getSelectedItemValueSelectOneRadio() {
       // return selectedItemValueSelectOneRadio;
       // }

    /**
     * @param selectedItemValuesSelectOneRadio the selectedItemValuesSelectOneRadio to set
     */
     //   public void setSelectedItemValueSelectOneRadio(String selectedItemValueSelectOneRadio) {
      //  this.selectedItemValueSelectOneRadio = selectedItemValueSelectOneRadio;
        //}
    
    public String getCalcType() {
        return calcType;
    }

    public void setCalcType(String calcType) {
        this.calcType = calcType;
    }

    public int getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(int orgCode) {
        this.orgCode = orgCode;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }
    
    private boolean radiobuttonActive = true;//getter
       
    public boolean isActiveRadio(){
        return radiobuttonActive;
        }

    public void setActiveRadio(boolean radiobuttonActive) {
        this.radiobuttonActive = radiobuttonActive;
    }
   
  
}
