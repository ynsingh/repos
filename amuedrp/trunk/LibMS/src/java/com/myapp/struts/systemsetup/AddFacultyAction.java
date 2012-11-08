/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;
import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.FacultyDAO;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 *
 * @author edrp02
 */
public class AddFacultyAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String faculty_id;
    String faculty_name;
    String library_id;
    String button;
    boolean result;
    List control_list;
    int max_id;
    String max_new_id;
    Faculty f=new Faculty();
    FacultyId fid=new FacultyId();
      Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";

   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
FacultyDAO facdao=new FacultyDAO();
        HttpSession session=request.getSession();
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
        AddFacultyActionForm afaf=(AddFacultyActionForm)form;
        faculty_id=afaf.getFaculty_id();
        button=afaf.getButton();
        faculty_name=afaf.getFaculty_name();
        
        library_id=(String)session.getAttribute("library_id");
        
            Faculty fcheck=facdao.getFacultyId(library_id, faculty_name);
        System.out.println("faculty"+fcheck);
            if(fcheck==null)
            {
        fid.setFacultyId(faculty_id);
        fid.setLibraryId(library_id);
        f.setId(fid);
       
        f.setFacultyName(faculty_name);
        result=facdao.insert(f);
        if(result==true)
        {
           //request.setAttribute("msg", "Record Inserted Successfully");
            request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recinsesucc"));
            return mapping.findForward("success");

        }
        else
        {
           // request.setAttribute("msg1", "Record Not Inserted");
            request.setAttribute("msg1", resource.getString("systemsetup.manage_notice.recnotinsertedsuccess"));
            return mapping.findForward("success");
        }
            }else{

                // request.setAttribute("msg1", "Duplicate Faculty Name");
                request.setAttribute("msg1", resource.getString("systemsetup.addfaultyaction.duplifacname"));
            return mapping.findForward("success");
            }
       

        
        
    }
}
