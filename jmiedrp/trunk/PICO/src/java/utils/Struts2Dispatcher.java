/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

/**
 *
 * @author sknaqvi
 */

import javax.servlet.*;
import org.apache.struts2.dispatcher.FilterDispatcher;
import org.hibernate.HibernateException;

public class Struts2Dispatcher extends FilterDispatcher {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);

        try {
            HibernateUtil.createSessionFactory();
        } catch (HibernateException e) {
            throw new ServletException(e);
        }
    }
}
