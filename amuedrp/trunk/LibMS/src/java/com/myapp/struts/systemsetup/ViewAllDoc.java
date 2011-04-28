/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.systemsetupDAO.*;
import com.myapp.struts.hbm.*;

import java.util.List;
/**
 *
 * @author edrp02
 */
public class ViewAllDoc extends org.apache.struts.action.Action {
    
  
     String library_id;
    private String sublibrary_id;
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            HttpSession session=request.getSession();
            library_id=(String)session.getAttribute("library_id");
           sublibrary_id=(String)session.getAttribute("sublibrary_id");
           
              List<DocumentCategory> docList=(List<DocumentCategory>)DocumentCategoryDAO.listdoccategory(library_id,sublibrary_id);
              session.setAttribute("docList", docList);
              return mapping.findForward("success");
          

        
        
    }
}
