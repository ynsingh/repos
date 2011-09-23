/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author zeeshan
 */
public class CatDeleteAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    NewMARCDAO marcdao = new NewMARCDAO();
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
        NewMarcActionForm nmaf= (NewMarcActionForm)form;
       int tag=nmaf.getTagnumber();

       String btn=nmaf.getBtn();
        if(btn.equals("Delete")){

        boolean m=marcdao.deleteMARC(String.valueOf(tag));
        System.out.println("s###################### "+m);
        request.setAttribute("msg", "record is deleted !");
        return mapping.findForward(SUCCESS);
        }
        return mapping.findForward("failure");
}
}
