/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp02
 */
public class CirCheckoutAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String memid;
    int max_chkout;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CirCheckoutActionForm ccaf=(CirCheckoutActionForm)form;
        memid=ccaf.getMemid();
        max_chkout=ccaf.getMax_chkout();
        request.setAttribute("memid", memid);
        request.setAttribute("max_chkout",max_chkout );
        HttpSession session=request.getSession();

String expired=(String)session.getAttribute("expired");
System.out.println(expired+"           .....................");
if(expired!=null)
{
request.setAttribute("nocheckout","Cannot Check Out Book Because Account is expired");
session.removeAttribute("expired");
return mapping.findForward("failure");
}




        return mapping.findForward("success");
    }
}
