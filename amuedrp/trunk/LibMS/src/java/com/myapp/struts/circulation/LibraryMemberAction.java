/*
      Design by Iqubal Ahmad
      Modified on 2011-02-02
      This Action Class is meant for Calling Ajax From Faculty table.
 */

package com.myapp.struts.circulation;
import com.myapp.struts.systemsetupDAO.*;
import javax.servlet.http.*;
import com.myapp.struts.hbm.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Iqubal ahmad
 */
public class LibraryMemberAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String library_id;
    /**
      Design by Iqubal Ahmad
      Modified on 2011-02-02
      This Action Class is meant for Calling Ajax From Faculty table.
    
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
       
        
        library_id = request.getParameter("getLibrary_Id");
      
       
      
         
       
        String depts = MemberDAO.getMemberByLibrary(library_id);
        if (!depts.equals(""))
        {
        response.setContentType("application/xml");
        response.getWriter().write(depts);

        }
        return null;
    }
}
