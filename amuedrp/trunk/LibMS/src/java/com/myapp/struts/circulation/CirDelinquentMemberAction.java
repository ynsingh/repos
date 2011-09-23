/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.CirculationDAO.CirculationDAO;
import com.myapp.struts.hbm.CirMemberAccount;
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
public class CirDelinquentMemberAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    boolean result;
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
        String library_id =(String)session.getAttribute("library_id");
        String sublibrary_id=(String)session.getAttribute("sublibrary_id");
             CirDelinquentMemberActionForm   cdma =(CirDelinquentMemberActionForm)form;
  
        String changestatus = cdma.getChangestatus();

     String memid = cdma.getMemid();
   String button = cdma.getButton();
  String reason =cdma.getReason();
  CirMemberAccount cirmemac =  CirculationDAO.getAccount(library_id, sublibrary_id, memid);
  
  

 

  if(!changestatus.equalsIgnoreCase("active"))
  cirmemac.setRemark(reason);
  cirmemac.setStatus(changestatus);




 result = CirculationDAO.updateAccount(cirmemac);



  if(result==true)
  {
      request.setAttribute("msg","Member Record Sucessfully  "+cirmemac.getStatus()+" for Member id " +memid);
       return mapping.findForward(SUCCESS);
  }
  else
  {
     request.setAttribute("msg1","Record not Updated Due to Some Internal Reasons");
       return mapping.findForward(SUCCESS);
  }



       // return mapping.findForward(SUCCESS);
    }
}
