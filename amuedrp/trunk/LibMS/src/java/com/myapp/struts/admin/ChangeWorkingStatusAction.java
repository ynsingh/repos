/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import com.myapp.struts.MyQueryResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class ChangeWorkingStatusAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private String working_status;
    private String library_name;
    private String library_id;
    private int registration_id;
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
    
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ChangeWorkingStatusActionForm statusForm=(ChangeWorkingStatusActionForm)form;
        working_status=statusForm.getWorking_status();
        registration_id=statusForm.getRegistration_request_id();
        library_id=statusForm.getLibrary_id();
        library_name=statusForm.getLibrary_name();
        try{
         System.out.println(working_status+registration_id+library_id+library_name+"*******************");
        int row=MyQueryResult.getMyExecuteUpdate("update admin_registration set working_status='"+working_status+"' where registration_id="+registration_id);
        if(row!=0)
        {

         row=MyQueryResult.getMyExecuteUpdate("update library set working_status='"+working_status+"' where registration_id='"+String.valueOf(registration_id)+"'");
         System.out.println(working_status+registration_id+library_id+library_name+row+"*******************");
         if(row!=0)
         {
         String msg="Working Status of Institute Successfully Updated ";
         String msg1=library_id;
         String msg2=library_name;
         String msg3=working_status;
         request.setAttribute("msg", msg);
         request.setAttribute("msg1", msg1);
         request.setAttribute("msg2", msg2);
         request.setAttribute("msg3", msg3);
         return mapping.findForward("success");
         }
         else
         {
         String msg="Working Status of Institute not Updated ";
         String msg1=library_name;
         String msg2=library_id;
         String msg3=library_name;
         request.setAttribute("msg", msg);
         request.setAttribute("msg1", msg1);
         request.setAttribute("msg2", msg2);
         request.setAttribute("msg3", msg3);
         return mapping.findForward("success");
         }
        }
        else
        {
         String msg="Working Status of Institute not Updated ";
         String msg1=library_name;
         String msg2=library_id;
         String msg3=library_name;
         request.setAttribute("msg", msg);
         request.setAttribute("msg1", msg1);
         request.setAttribute("msg2", msg2);
         request.setAttribute("msg3", msg3);
         return mapping.findForward("success");
        }
        }catch(Exception e)
        {
            System.out.println(e+"****************************************************");
            return mapping.findForward("success");

        }
    }
}
