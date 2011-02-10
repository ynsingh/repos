/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.*;

import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Faraz
 */
public class JournalAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
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
                String phrase,title,author,accno;
                String db,callno,publ,loc,place,sort,field,query="";
                ResultSet rs=null;
                HttpSession session = request.getSession();
                JournalActionForm myForm = (JournalActionForm)form;
                 String lib_id=myForm.getCMBLib();
                if(session.getAttribute("journalRs")!=null) session.removeAttribute("journalRs");
                 query="select * from document where category='journal'";
                if(!lib_id.equals("all")) query = query + " and library_id='" + lib_id + "'";
                
                    System.out.println("query="+query);
                rs = MyQueryResult.getMyExecuteQuery(query);
                
                session.setAttribute("journalRs", rs);
        return mapping.findForward(SUCCESS);
    }
}
