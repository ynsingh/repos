/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.*;
import java.sql.ResultSet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Faraz
 */
public class NoticeAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private String library_id;
    
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
HttpSession session=(HttpSession)request.getSession();
library_id=(String)session.getAttribute("library_id");
        ResultSet rs =null;
        System.out.println("here");
        if(library_id==null)
        {
            return mapping.findForward("fail");

        }
        else
        {String query = "select * from notice where library_id='"+library_id+"'";

        rs = MyQueryResult.getMyExecuteQuery(query);
        request.setAttribute("noticeRs", rs);
        return mapping.findForward(SUCCESS);
        }
        
    }
}
