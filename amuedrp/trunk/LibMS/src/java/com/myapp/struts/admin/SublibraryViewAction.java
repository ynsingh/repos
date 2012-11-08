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
import com.myapp.struts.hbm.*;
import com.myapp.struts.AdminDAO.*;

/**
 *
 * @author edrp02
 */
public class SublibraryViewAction extends org.apache.struts.action.Action {
    
 
 
    String sublibrary_id;
    String button;
    String library_id;
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         SubLibraryDAO sublibdao=new SubLibraryDAO();
         HttpSession session=request.getSession();
         library_id=(String)session.getAttribute("library_id");

    sublibrary_id=request.getParameter("id");
       
SubLibrary sublib=sublibdao.getLibName(library_id, sublibrary_id);
if(sublib!=null)
{request.setAttribute("button", "View");
               request.setAttribute("sublib", sublib);
               return  mapping.findForward("view");
}

     return null;
         
       
    }
}
