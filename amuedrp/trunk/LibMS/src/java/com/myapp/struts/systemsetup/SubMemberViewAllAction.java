/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.systemsetupDAO.SubMemberDAO;
import java.util.List;
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
public class SubMemberViewAllAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
     String library_id;
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SubMemberDAO submemdao=new SubMemberDAO();
         HttpSession session=request.getSession();
         library_id=(String)session.getAttribute("library_id");
         List submember=submemdao.searchSubEmployeeType(library_id);

          if(submember.isEmpty() && submember==null){
  String msg="You need to Add Sub Members";
  request.setAttribute("msg1", msg);
    return mapping.findForward("success");
  }

         session.setAttribute("submember", submember);
        
        return mapping.findForward(SUCCESS);
    }
}
