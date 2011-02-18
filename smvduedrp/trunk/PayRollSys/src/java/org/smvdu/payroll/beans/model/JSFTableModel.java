/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.model;

import javax.faces.model.ResultSetDataModel;

/**
 *
 * @author Algox
 */
public class JSFTableModel {

    private ResultSetDataModel rsdm;

    public void load()
    {
        rsdm = new ResultSetDataModel();
        
    }

}
