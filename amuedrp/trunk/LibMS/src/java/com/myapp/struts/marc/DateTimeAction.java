/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author EdRP-05
 */
public class DateTimeAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        StringBuffer email_ids = new StringBuffer();
        Calendar now = Calendar.getInstance();
        int month = now.get(Calendar.MONTH) + 1;
        String bb= String.valueOf(now.get(Calendar.YEAR)) + String.valueOf(month) + String.valueOf(now.get(Calendar.DATE)) + String.valueOf(now.get(Calendar.HOUR_OF_DAY)) + String.valueOf(now.get(Calendar.MINUTE)) + String.valueOf(now.get(Calendar.SECOND)) + String.valueOf(now.get(Calendar.MILLISECOND));
        email_ids.append("<email_ids>");
email_ids.append("<email_id>"+bb+"</email_id>");
email_ids.append("</email_ids>");
        response.setContentType("application/xml");
        response.getWriter().write(email_ids.toString());
        return null;
    }
}
