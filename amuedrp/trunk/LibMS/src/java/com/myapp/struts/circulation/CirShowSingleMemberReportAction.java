/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;
import com.myapp.struts.CirDAO.*;
import com.myapp.struts.hbm.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp01
 */
public class CirShowSingleMemberReportAction extends org.apache.struts.action.Action {
    
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
         HttpSession session=request.getSession();
         String   library_id=(String)session.getAttribute("library_id");
       String sublibrary_id=(String)session.getAttribute("sublibrary_id");
      String  mem_id=(String)request.getParameter("id");
      String  checkIn=(String)request.getParameter("checkIn");

        CirCheckin checkindetail=CirculationDAO.searchCheckinMemDetails(library_id,sublibrary_id,mem_id,checkIn);
      //    System.out.println("@@@@@@@@@@"+cmemdetail.getMemberId());

        if(checkindetail!=null)
        {
           request.setAttribute("mem_id",mem_id);
           request.setAttribute("checkindetail",checkindetail);
           return mapping.findForward("success");
        }


        
        return mapping.findForward(SUCCESS);
    }
}
