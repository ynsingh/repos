/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import com.myapp.struts.CirDAO.CirculationDAO;
import com.myapp.struts.hbm.CirMemberAccount;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.utility.DateCalculation;
/**
 *
 * @author edrp02
 */
public class CardInformationAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
     private String memid,library_id,sublibrary_id;
    private String button;
    CirculationDAO cirdao=new CirculationDAO();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();
        CardInformationActionForm myform=(CardInformationActionForm)form;

        memid=myform.getTXTMEMID();

button=myform.getButton();
library_id=(String)session.getAttribute("library_id");
sublibrary_id=(String)session.getAttribute("sublibrary_id");
if(button.equalsIgnoreCase("Generate Card")){
        System.out.println(memid+"...............");
        request.setAttribute("memid",memid);
        
        return mapping.findForward(SUCCESS);
}else if(button.equalsIgnoreCase("Lost Card")){
  CirMemberAccount cirmemberaccount=cirdao.getAccount2(library_id, sublibrary_id, memid);
  if(cirmemberaccount!=null){
      cirmemberaccount.setCardStatus("Losts");
      cirmemberaccount.setLostDate(DateCalculation.now());

  cirdao.update2(cirmemberaccount);
  request.setAttribute("msg", "Losts Card Status Updated Successfully");

   return mapping.findForward("success1");
  }


}else if(button.equalsIgnoreCase("Duplicate Card")){
  CirMemberAccount cirmemberaccount=cirdao.getAccount2(library_id, sublibrary_id, memid);
  if(cirmemberaccount!=null){
      cirmemberaccount.setCardStatus("DuplicateCard");
      cirmemberaccount.setDuplicateIssueDate(DateCalculation.now());

  cirdao.update2(cirmemberaccount);
  request.setAttribute("msg", "Duplicate Card Status Updated Successfully");

   return mapping.findForward("success");
  }

    }
return null;
    }
}
