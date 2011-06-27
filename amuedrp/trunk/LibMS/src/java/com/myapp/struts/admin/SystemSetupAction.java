/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import com.myapp.struts.opac.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.List;
import com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.*;
import  com.myapp.struts.systemsetupDAO.*;
import com.myapp.struts.opacDAO.OpacSearchDAO;
import com.myapp.struts.systemsetupDAO.*;

/**
 *
 * @author Faraz
 */
public class SystemSetupAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        HttpSession session = request.getSession();
        
    String library_id=(String)session.getAttribute("library_id");
    String sublibrary_id=(String)session.getAttribute("sublibrary_id");
    String login_id=(String) request.getAttribute("login_id");
    String user_name=(String) request.getAttribute("user_name");
    String staff_id=(String) request.getAttribute("staff_id");
    String staff_name=(String) request.getAttribute("staff_name");



List<Location> locobj=(List<Location>)LocationDAO.getLocation(library_id, sublibrary_id);
List<EmployeeType> empobj=(List<EmployeeType>)MemberDAO.searchEmployeeType(library_id);
List<SubEmployeeType> subempobj=(List<SubEmployeeType>)SubMemberDAO.searchSubEmployeeType(library_id);
List<DocumentCategory> docobj=(List<DocumentCategory>)DocumentCategoryDAO.searchDocumentCategory(library_id, sublibrary_id);
   request.setAttribute("login_id", login_id);
            request.setAttribute("user_name", user_name);
            request.setAttribute("library_id", library_id);
            request.setAttribute("staff_id", staff_id);
            request.setAttribute("staff_name", user_name);

   /*    if(locobj.isEmpty())
            return mapping.findForward("location");
        if(empobj.isEmpty())
            return mapping.findForward("member");
        if(subempobj.isEmpty())
            return mapping.findForward("submember");
        if(docobj.isEmpty())
            return mapping.findForward("document");*/
            if(locobj.isEmpty())
            {
            session.setAttribute("location","Manage Location");
            }

            if(empobj.isEmpty())
            {
           session.setAttribute("member","Manage Member Type");
            }
            if(subempobj.isEmpty())
            {
            session.setAttribute("submember","Mamage SubMember Type");
            }
            if(docobj.isEmpty())
            {
            session.setAttribute("document","Manage Document Category");
            }

        if(locobj.isEmpty() || empobj.isEmpty() || subempobj.isEmpty() || docobj.isEmpty())
        {
            System.out.println("here");
            return mapping.findForward("skip");
        }else{
            return mapping.findForward("success");
        }


    }
}
