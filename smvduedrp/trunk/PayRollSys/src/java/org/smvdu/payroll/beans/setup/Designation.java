/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.setup;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.BaseBean;
import org.smvdu.payroll.beans.db.DesignationDB;

/**
 *
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
 *  All Rights Reserved.
 **  Redistribution and use in source and binary forms, with or 
 *  without modification, are permitted provided that the following 
 *  conditions are met: 
 **  Redistributions of source code must retain the above copyright 
 *  notice, this  list of conditions and the following disclaimer. 
 * 
 *  Redistribution in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in 
 *  the documentation and/or other materials provided with the 
 *  distribution. 
 * 
 * 
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 *  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 * 
 * 
 *  Contributors: Members of ERP Team @ SMVDU, Katra
 *
 */
public class Designation extends BaseBean implements Converter, Serializable {

    public void save() {
        FacesContext fc = FacesContext.getCurrentInstance();
        
        Exception e = new DesignationDB().save(getName());
        if (e == null) {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Designation saved" + getName(), ""));
        } else {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Designation already Exist : " + getName(), ""));
        }
    }
    private SelectItem[] arrayAsItem;

    public SelectItem[] getArrayAsItem() {

        ArrayList<Designation> designations = new DesignationDB().loadDesignations();
        arrayAsItem = new SelectItem[designations.size()];
        Designation dp = null;
        for (int i = 0; i < designations.size(); i++) {
            dp = designations.get(i);
            SelectItem si = new SelectItem(dp.getCode(), dp.getName());
            arrayAsItem[i] = si;
        }
        return arrayAsItem;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return new DesignationDB().convert(string);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        System.out.println("Object class Desig : " + o.getClass().getSimpleName());
        return o.toString();
    }
}
