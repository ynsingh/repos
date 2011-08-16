/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.Acquisition.CurrencyForm2;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mca53amu
 */
public class CurrencyAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private String button,ConId;
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CurrencyForm cf=(CurrencyForm)form;
        button=cf.getButton();
        ConId=cf.getConid();
        if(button.equals("New")){
           
            
            request.setAttribute("ConId",ConId);

        return mapping.findForward("New");
        }

        else
        {
        return mapping.findForward(SUCCESS);
        }

    }
}
