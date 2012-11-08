/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.CirDAO.*;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author edrp01
 */
public class CirStatistics extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
              HttpSession session=request.getSession();
            String library_id =(String)session.getAttribute("library_id");
           String sublibrary_id =(String)session.getAttribute("sublibrary_id");
CirculationDAO cirdao=new CirculationDAO();

  HashMap obj=cirdao.searchCirMemberStatistics(library_id,sublibrary_id);

        
         
         
                 session.setAttribute("statistics",obj );
//                 System.out.println(faculty.size());
         

        return mapping.findForward(SUCCESS);
    }
}
