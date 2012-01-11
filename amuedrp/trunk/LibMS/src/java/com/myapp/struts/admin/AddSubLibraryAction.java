/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;
import com.myapp.struts.hbm.*;
import com.myapp.struts.AdminDAO.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author edrp02
 */
public class AddSubLibraryAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String faculty;
    String faculty1;
    String sublib_name;
    String sublib_name1;
    String sublibrary_id;
    private boolean result;
   
    String department_address;
  
    String library_id;
    SubLibrary sl=new SubLibrary();
    SubLibraryId slid=new SubLibraryId();
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

        AddSubLibraryActionForm aslaf=(AddSubLibraryActionForm)form;
        sublibrary_id=aslaf.getSublibrary_id();
        sublib_name=aslaf.getSublib_name();
        faculty=aslaf.getFaculty();
        department_address=aslaf.getDepartment_address();
        faculty1=aslaf.getFaculty1();
        sublib_name1=aslaf.getSublib_name1();

        System.out.println(sublib_name1+"........................."+sublib_name+faculty+faculty1+sublibrary_id);



       
        library_id=(String)session.getAttribute("library_id");



        Library lib=LibraryDAO.searchLibraryID(library_id);

        //if sublibrary name is already used return false


        SubLibrary searchsubobj;

         if(sublib_name1.equals("Select")==true)
        {

       searchsubobj=SubLibraryDAO.getSubLibraryId(library_id, sublib_name);
        }
        else
        {

            searchsubobj=SubLibraryDAO.getSubLibraryId(library_id, sublib_name1);
        }




        if(searchsubobj==null){


        SubLibraryId subid=new SubLibraryId(library_id, sublibrary_id);
        SubLibrary subobj;

        if(sublib_name1.equals("Select")==true)
        {
        subobj=new SubLibrary(subid, lib, sublib_name);
    
        }
        else
        {
            subobj=new SubLibrary(subid, lib, sublib_name1);
        }




        
        if(faculty1.equals("Select")==false)
            subobj.setFacultyName(faculty1);
        else
            subobj.setFacultyName(faculty);

        subobj.setDeptAddress(department_address);
        result=SubLibraryDAO.insert(subobj);

        if(result==true)
        {
            request.setAttribute("msg", resource.getString("circulation.circulationnewmemberregAction.recinsesucc"));
            return mapping.findForward("success");

        }
        }else{
               // request.setAttribute("msg1", "Duplicate SubLibrary Name");
               request.setAttribute("msg1", resource.getString("admin.addsublibAction.duplisublibname"));
            return mapping.findForward("success");
            }

        return null;
 
         
    }
}
