/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

/**
 *
 * @author Algox
 */
public class EmpTypeHead {

    private int typeCode=0;
    private boolean visible = false;

    public boolean isVisible() {
        if(typeCode>0)
        {
            visible = true;
        }
 else
        {
            visible = false;
 }
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public int getTypeCode() {
        return typeCode;
    }
    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }
    public EmpTypeHead()    {
        
    }
   
   
}
