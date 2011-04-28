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
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
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
        // System.out.println(working_status+registration_id+institute_id+library_name+"*******************");
adminreg.setAdminRegistration((AdminRegistration)admindao.getAdminDeatilsById(registration_id).get(0));
adminreg.getAdminRegistration().setWorkingStatus(working_status);
admindao.update(adminreg.getAdminRegistration());
        
adminreg.setLibrary((Library)institutedao.getInstituteDetailsByRegistrationId(registration_id));
adminreg.getLibrary().setWorkingStatus(working_status);
institutedao.update(adminreg.getLibrary());

List rst = admindao.getAdminDetailsByStatus("Registered");
HttpSession session= request.getSession();
                    session.setAttribute("resultset1", rst);
     
         String msg="Working Status of Institute Successfully Updated ";
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
            return mapping.findForward("success");

        }
    }
}
