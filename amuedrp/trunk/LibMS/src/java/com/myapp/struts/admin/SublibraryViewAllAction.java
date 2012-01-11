/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.AdminDAO.*;

import java.util.List;
/**
 *
 * @author edrp02
 */
public class SublibraryViewAllAction extends org.apache.struts.action.Action {
    
  
     String library_id;
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            HttpSession session=request.getSession();
            library_id=(String)session.getAttribute("library_id");
           
           
              List sublibrary=SubLibraryDAO.searchSubLib(library_id);

              
              session.setAttribute("sublibrary", sublibrary);
              return mapping.findForward("success");
          

        
        
    }
}
