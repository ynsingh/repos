/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.CirDAO.CirculationDAO;

public class ViewAllDeliquency extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            {
        CirculationDAO cirdao=new CirculationDAO();
        HttpSession session = request.getSession();
        String        library_id=(String)session.getAttribute("library_id");
        String sublibrary_id=(String)session.getAttribute("sublibrary_id");
     

      
    
      session.setAttribute("actlist", cirdao.searchDelReason(library_id, sublibrary_id));
      return mapping.findForward(SUCCESS);
    }
}
