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
import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.DeptDAO;
import com.myapp.struts.systemsetupDAO.FacultyDAO;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 *
 * @author edrp02
 */
public class FacultyUpdateViewAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String faculty_id;
    String faculty_name;
    String button;
    String library_id;
    boolean result;
    Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         HttpSession session = request.getSession();
         FacultyDAO facdao=new FacultyDAO();
         DeptDAO deptdao=new DeptDAO();
     
         try{

        locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
        FacultyUpdateViewActionForm fuvaf=(FacultyUpdateViewActionForm)form;
        faculty_id=fuvaf.getFaculty_id();
        faculty_name=fuvaf.getFaculty_name();
        button=fuvaf.getButton();
       
        library_id=(String)session.getAttribute("library_id");
       // Faculty fac=(Faculty)session.getAttribute("faculty");
       
        Faculty faculty=facdao.searchFacultyName(library_id, faculty_id);
        if(button.equals("Update"))
        {
             faculty.getId().setFacultyId(faculty_id);
             faculty.setFacultyName(faculty_name);
             result=facdao.update(faculty,library_id);
             if(result==true)
             {
               // request.setAttribute("msg", "Record Update Successfully");
                 request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recupdatesucc"));
              return mapping.findForward("success");
             }
             else
             {
               // request.setAttribute("msg", "Record Not Update");
                 request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recupdatenotsecc"));
              return mapping.findForward("success");
             }

        }
        if(button.equals("Delete"))
        {

               List<Department> dept=(List<Department>)deptdao.getDept(library_id, faculty_id);
               if(!dept.isEmpty())
               {

                   // request.setAttribute("msg1", "Dept Already there This faculty,Cannot Deleted");
                   request.setAttribute("msg1",  resource.getString("systemsetup.facultyupdateviewAction.deptalredythere"));
              return mapping.findForward("success");
               }





            List<CirMemberAccount> cir=   (List<CirMemberAccount>)facdao.searchAccount(library_id,faculty_id);
           if(!cir.isEmpty()){

             //   request.setAttribute("msg1", "Account Created With This faculty,Cannot Deleted");
            request.setAttribute("msg1", resource.getString("systemsetup.facultyupdateviewAction.acccanotdel"));
              return mapping.findForward("success");
           }

            result=facdao.Delete(faculty);
             if(result==true)
             {
                //request.setAttribute("msg", "Record Deleted Successfully");
              request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recdelsucc"));
              return mapping.findForward("success");
             }
             else
             {

                // request.setAttribute("msg", "Record Not Deleted");
              request.setAttribute("msg",  resource.getString("circulation.circulationnewmemberregAction.memnotdelsucc"));
              return mapping.findForward("success");
             }


        }


        return mapping.findForward("suc");
    }
}
