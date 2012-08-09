/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import java.io.Serializable;

/**
 *
 * @author edrp02
 */
public class PaymentUpdateClass implements Serializable{

   String prn;

    public String getPrn() {
        return prn;
    }

    public void setPrn(String prn) {
        this.prn = prn;
    }
   

}
