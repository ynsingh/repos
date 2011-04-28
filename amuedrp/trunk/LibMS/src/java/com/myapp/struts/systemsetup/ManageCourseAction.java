/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.systemsetupDAO.*;

/**
 *
 * @author edrp01
 */
public class ManageCourseAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String library_id,sublibrary_id;
    List list1,list2,list3;
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");

   list1=(List)FacultyDAO.searchFaculty(library_id);
   
   
  if(list1.isEmpty()){
  String msg="You need to set Faculty";
  request.setAttribute("msg1", msg);
    return mapping.findForward("success"); 
  }
System.out.println("In Faculty ");

  list2=(List)DeptDAO.searchDept(library_id);


  if(list2.isEmpty()){
  String msg="You need to set Department";
  request.setAttribute("msg1", msg);
    return mapping.findForward("success");
  }
System.out.println("In Dept ");

session.setAttribute("faculty", list1);
   session.setAttribute("department", list2);
        
        return mapping.findForward("success");
    }
}
