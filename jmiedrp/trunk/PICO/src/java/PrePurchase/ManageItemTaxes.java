/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PrePurchase;
/**
 *
 * @author sknaqvi
 */

import utils.DevelopmentSupport;

import pojo.hibernate.ErpmItemRateTaxes;
import pojo.hibernate.ErpmItemRateTaxesDAO;


public class ManageItemTaxes extends DevelopmentSupport {
     private ErpmItemRateTaxes itemRateTax;
     private ErpmItemRateTaxesDAO itemRateTaxDAO = new ErpmItemRateTaxesDAO();

     private String message;
     private Integer irItemRateId;

     
     //Gettet Setter Starts Here
     public void setitemRateTax(ErpmItemRateTaxes itemRateTax) {
        this.itemRateTax = itemRateTax;
    }

    public ErpmItemRateTaxes getitemRateTax() {
        return this.itemRateTax;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }

    public void setirItemRateId(Integer irItemRateId) {
                   this.irItemRateId = irItemRateId;
    }

    public Integer getirItemRateId() {
                   return irItemRateId;
    }



    @Override
    public String execute() throws Exception {
    try {
            message = "Here " + getirItemRateId();
        return SUCCESS;
            }
    catch (Exception e) {
            message = "Exception in -> Manage ItemRateAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
            }
  }


    public String SaveItemRateTaxes() throws Exception {
    try {
            message = "Here " + getirItemRateId();
            return SUCCESS;
            }
    catch (Exception e) {
            message = "Exception in -> Manage ItemRateAxn"  + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
            }
  }

}
