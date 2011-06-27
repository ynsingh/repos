/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;


import com.myapp.struts.AdminDAO.AdminRegistrationDAO;
import com.myapp.struts.hbm.*;
import com.myapp.struts.AdminDAO.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.*;

/**
 *
 * @author Dushyant
 */
public class ChangeWorkingStatusAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private String working_status;
   // private String library_name;
    private String institute_id;
    private int registration_id;

    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ChangeWorkingStatusActionForm statusForm=(ChangeWorkingStatusActionForm)form;
        AdminReg_Institute adminreg = new AdminReg_Institute();
        AdminRegistrationDAO admindao =new AdminRegistrationDAO();
        LibraryDAO institutedao = new LibraryDAO();

        working_status=statusForm.getWorking_status();
        registration_id=statusForm.getRegistration_request_id();
        institute_id=statusForm.getInstitute_id();


        try{
         System.out.println(working_status+registration_id+institute_id+"*******************");
adminreg.setAdminRegistration((AdminRegistration)admindao.getAdminDeatilsById(registration_id).get(0));
adminreg.getAdminRegistration().setWorkingStatus(working_status);
admindao.update(adminreg.getAdminRegistration());
        
adminreg.setLibrary((Library)institutedao.getInstituteDetailsByRegistrationId(registration_id));
adminreg.getLibrary().setWorkingStatus(working_status);
institutedao.update(adminreg.getLibrary());

List rst = admindao.getAdminDetailsByStatus("Registered");
HttpSession session= request.getSession();
                    session.setAttribute("resultset1", rst);

         try{
              locale1=(String)session.getAttribute("locale");

              if(session.getAttribute("locale")!=null)
              {
                locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
              }
              else locale1="en";
            }catch(Exception e){locale1="en";}
             locale = new Locale(locale1);
             if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
             else{ rtl="RTL";page=false;align="right";}
              ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

         String msg=resource.getString("admin.adminblock.msg");
         String msg1=institute_id;
         String msg2=adminreg.getAdminRegistration().getInstituteName();
         String msg3=working_status;
         request.setAttribute("msg", msg);
         request.setAttribute("msg1", msg1);
         request.setAttribute("msg2", msg2);
         request.setAttribute("msg3", msg3);
         return mapping.findForward("success");
         
        }catch(Exception e)
        {
            System.out.println(e+"****************************************************");
            return mapping.findForward("failure");

        }
    }
}
