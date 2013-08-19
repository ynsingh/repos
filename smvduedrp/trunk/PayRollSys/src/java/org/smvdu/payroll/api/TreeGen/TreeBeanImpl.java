/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.TreeGen;

import org.richfaces.model.TreeNodeImpl;


/**
 *
 * @author ERP
 */
public class TreeBeanImpl extends TreeNodeImpl{

    /** Creates a new instance of TreeBeanImpl */
    private String data;
    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
    public TreeBeanImpl(String data) {
        super();
        this.data = data;
    }
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    @Override
    public String toString()
    {
        return data;
    }

}
