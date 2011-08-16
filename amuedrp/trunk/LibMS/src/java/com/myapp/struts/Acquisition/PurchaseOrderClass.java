/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.hbm.*;

/**
 *
 * @author EdRP-05
 */
public class PurchaseOrderClass implements java.io.Serializable{
private AcqOrder1 acqOrder1;
    private AcqBibliography acqBibliography;
    private AcqBibliographyDetails acqBibliographyDetails;

    /**
     * @return the acqOrder1
     */
    public AcqOrder1 getAcqOrder1() {
        return acqOrder1;
    }

    /**
     * @param acqOrder1 the acqOrder1 to set
     */
    public void setAcqOrder1(AcqOrder1 acqOrder1) {
        this.acqOrder1 = acqOrder1;
    }

    /**
     * @return the acqBibliography
     */
    public AcqBibliography getAcqBibliography() {
        return acqBibliography;
    }

    /**
     * @param acqBibliography the acqBibliography to set
     */
    public void setAcqBibliography(AcqBibliography acqBibliography) {
        this.acqBibliography = acqBibliography;
    }

    /**
     * @return the acqBibliographyDetails
     */
    public AcqBibliographyDetails getAcqBibliographyDetails() {
        return acqBibliographyDetails;
    }

    /**
     * @param acqBibliographyDetails the acqBibliographyDetails to set
     */
    public void setAcqBibliographyDetails(AcqBibliographyDetails acqBibliographyDetails) {
        this.acqBibliographyDetails = acqBibliographyDetails;
    }

}
