/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import javax.servlet.http.HttpSession;
import com.myapp.struts.systemsetupDAO.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author edrp02
 */
public class AddDepartmentAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String dept_id;
    String dept_rec_id;
    String dept_name;
   
    String faculty_id;
    String library_id;
    String button;
    List control_list;
    int max_id;
    boolean result;
    Department d=new Department();
    DepartmentId did=new DepartmentId();
      Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

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
        AddDepartmentActionForm adaf=(AddDepartmentActionForm)form;
        dept_id=adaf.getDept_id();
        dept_name=adaf.getDept_name();
        faculty_id=adaf.getFaculty_name();

      
        library_id=(String)session.getAttribute("library_id");
         Department fcheck=DeptDAO.getDeptId(library_id, faculty_id, dept_name);
        System.out.println("Dept"+fcheck);
            if(fcheck==null)
            {





        d.setDeptName(dept_name);
        did.setDeptId(dept_id);
        did.setLibraryId(library_id);
        did.setFacultyId(faculty_id);
        d.setId(did);
        result=DeptDAO.insert(d);
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

            //request.setAttribute("msg1", "Duplicate Deptartment Name");
                request.setAttribute("msg1", resource.getString("systemsetup.adddepartment.duplideptname"));
            return mapping.findForward("success");
            }

        
       
    }
}
