/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.smvdu.payroll.beans.BaseBean;

/**
 *
 * @author Algox
 */
public class BaseConverter implements Converter{
    private BaseBean bean = null;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        System.err.println("Got value : "+bean.getName());
        return bean;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        bean = (BaseBean)o;
        return String.valueOf(bean.getCode());
    }

}
