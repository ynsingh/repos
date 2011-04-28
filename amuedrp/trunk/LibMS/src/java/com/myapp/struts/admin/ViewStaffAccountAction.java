/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import  com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.*;
import java.util.List;
/**
 *
 * @author Dushyant
 */
public class ViewStaffAccountAction extends org.apache.struts.action.Action {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();
     
        String sublibrary_id=(String)session.getAttribute("sublibrary_id");

        String library_id=(String)session.getAttribute("library_id");

        String staff_id=request.getParameter("id")  ;

         System.out.println(staff_id+library_id);

        Login loginobj=LoginDAO.searchStaffId(staff_id, library_id);
        if(loginobj!=null){
             
             // request.setAttribute("button", button);
             List<SubLibrary>  sublib=SubLibraryDAO.searchSubLib(library_id);
             if(!sublib.isEmpty())
             {
                request.setAttribute("data",loginobj);
                request.setAttribute("sublib",sublib);
               
               
                return mapping.findForward("success");

             }
             else{
             session.setAttribute("msg", "Some Error encounrted");
            
            return mapping.findForward("success");

             }

                

        }
        else
        {
            session.setAttribute("msg", "Some Error encounrted");
             
            return mapping.findForward("success");
        }



        }
}




        
     






