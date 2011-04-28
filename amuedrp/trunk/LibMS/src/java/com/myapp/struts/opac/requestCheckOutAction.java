/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import com.myapp.struts.CirculationDAO.CirculationDAO;
import com.myapp.struts.circulationDAO.cirDAO;
import com.myapp.struts.hbm.CirMemberAccount;
import com.myapp.struts.hbm.CirMemberDetail;
import com.myapp.struts.hbm.CirOpacRequest;
import com.myapp.struts.hbm.CirOpacRequestId;
import com.myapp.struts.hbm.DocumentDetails;
import com.myapp.struts.opacDAO.CirRequestfromOpacDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author faraz
 */
public class requestCheckOutAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
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

        HttpSession session = request.getSession();
        //DocumentDetails dd = (DocumentDetails)request.getAttribute("dd");
        String docId = (String)request.getParameter("docId");
        String libId = (String)request.getParameter("libId");
        String sublibId = (String)request.getParameter("sublibId");

        String memId = (String)session.getAttribute("mem_id");
        String memlibId = (String)session.getAttribute("memlib");

        if(memId==null || memlibId==null)
        {
            //send request to member login page

                session.setAttribute("checkoutRequestMemId", memId);
                session.setAttribute("checkoutRequestLibId", libId);
                session.setAttribute("checkoutRequestSubLibId", sublibId);
                session.setAttribute("checkoutRequestDocId", docId);
                request.setAttribute("msg", "Please login!");
                    return mapping.findForward("login");
         
        }
        else
        {
            CirMemberAccount memSubLib = (CirMemberAccount)CirculationDAO.searchCirMemAccountDetails(libId, sublibId, memId);
            if(memSubLib==null)
            {
                request.setAttribute("msg", "You do not have an account in library "+ libId +"-"+sublibId);
                return mapping.findForward("failure");
            }
            else
            {
                //insertion of data into cir_opac_request
                if(memSubLib.getStatus().equalsIgnoreCase("active"))
                {









                 List<CirOpacRequest> check1 = (List<CirOpacRequest>)CirRequestfromOpacDAO.checkDuplicateRequest(libId, sublibId, memId, docId);
                if(check1.isEmpty()){
                
                boolean flag = CirRequestfromOpacDAO.SendCheckOutRequest(memId, libId, sublibId, docId);
                if(flag){
                    request.setAttribute("msg", "Your Request is successfully send");
                    return mapping.findForward("success");
                }
                else
                {
                    request.setAttribute("msg", "Your Request not send due to some reason");
                    return mapping.findForward("failure");
                }
                }
                else{
                     session.removeAttribute("checkoutRequestMemId");
                session.removeAttribute("checkoutRequestLibId");
                session.removeAttribute("checkoutRequestSubLibId");
                session.removeAttribute("checkoutRequestDocId");
                session.removeAttribute("type");
                     request.setAttribute("msg", "Your Request is already pending....");
                    return mapping.findForward("failure");
                }
                }
                else
                {
                    request.setAttribute("msg", "Your Account is InActive");
                    return mapping.findForward("failure");
                }
            }
        }
        
    }
}
