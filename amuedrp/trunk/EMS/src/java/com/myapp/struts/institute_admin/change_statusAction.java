/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.institute_admin;

import com.myapp.struts.hbm.ElectionManagerDAO;
import com.myapp.struts.hbm.Election_Manager_StaffDetail;
import com.myapp.struts.hbm.StaffManagerDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import  com.myapp.struts.utility.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.*;
/**
 *
 * @author Edrp-04
 */
public class change_statusAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private String status;
    private String manager_id;
    private String institute_id;
    private final ExecutorService executor=Executors.newFixedThreadPool(1);
    Email obj;
    private String admin_email;
     Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";

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
         Election_Manager_RegistrationActionForm electionManagerForm=(Election_Manager_RegistrationActionForm)form;
        try{
HttpSession session=request.getSession();





        try{
locale1=(String)session.getAttribute("locale");

    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");

    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);


            Election_Manager_StaffDetail ems= new Election_Manager_StaffDetail();
            StaffManagerDAO staffmanagerdao=new StaffManagerDAO();
             ElectionManagerDAO managerdao=new ElectionManagerDAO();
String instituteId = (String)session.getAttribute("institute_id");
              manager_id=electionManagerForm.getManager_id();
              //institute_id=electionManagerForm.getInstitute_id();
            status=electionManagerForm.getStatus();

            System.out.println(status);

 List<Election_Manager_StaffDetail> lstManager= (List<Election_Manager_StaffDetail>)managerdao.GetManagerDetails(manager_id,instituteId);
                if(!lstManager.isEmpty()){ems = (Election_Manager_StaffDetail)lstManager.get(0);
                admin_email=ems.getStaffDetail().getEmailId();
                }
            ems.getElectionManager().setStatus(status);

            staffmanagerdao.update(ems);

            String msg=resource.getString("status_changed_succeccfully");
             request.setAttribute("msg", msg);

        }
        catch(Exception e){
        }

           obj=new Email(admin_email,admin_email,"Manager Status is Changed from admin of Institute in EMS","Status"+status);
         executor.submit(new Runnable() {

                public void run() {
                    obj.send();
                }
            });


        return mapping.findForward(SUCCESS);
    }
}
