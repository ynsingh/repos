// INSERT INSTITUTE REQUEST IN DB
package com.myapp.struts.admin;
import  com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.myapp.struts.utility.Email;
import com.myapp.struts.utility.LoggerUtils;
import org.apache.log4j.Logger;
/**
 * Developed By : Kedar Kumar
 * Modified By  : 18-Feb-2011
 * Use to Check the Admin Registration Activity
 */
public class AdminRegistrationAction extends org.apache.struts.action.Action {


    private final ExecutorService executor=Executors.newFixedThreadPool(1);
    Email obj;
    private String login_id;
    boolean result;
    AdminRegistration adminobj;
    LoginDAO logindao;
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    private static Logger log4j =LoggerUtils.getLogger();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
         
   try
   {
            logindao=new LoginDAO();
            HttpSession session1 =request.getSession();
            locale1=(String)session1.getAttribute("locale");
            if(session1.getAttribute("locale")!=null)
            {
                locale1 = (String)session1.getAttribute("locale");
            }
            else locale1="en";
            locale = new Locale(locale1);
            if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
            else{ rtl="RTL";align="right";}
            ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
            AdminRegistrationActionForm adminRegistrationActionForm=(AdminRegistrationActionForm)form;
            login_id=adminRegistrationActionForm.getAdmin_email();
            adminobj=new AdminRegistration();
            AdminRegistration tempobj=(AdminRegistration)AdminRegistrationDAO.searchLoginID(login_id);
            Login loginobj=(Login)logindao.searchLoginID(login_id);
              if(tempobj!=null || loginobj!=null)
                {
                   // request.setAttribute("msg", "User ID is Duplicate");
                     request.setAttribute("msg",resource.getString("admin.adminregistrationaction.error1"));
                    return mapping.findForward("duplicate");
                }
               else
                {
                        adminobj.setInstituteName(adminRegistrationActionForm.getInstitute_name());
                        adminobj.setAbbreviatedName(adminRegistrationActionForm.getAbbreviated_name());
                        adminobj.setInstituteAddress(adminRegistrationActionForm.getInstitute_address());
                        adminobj.setPin(adminRegistrationActionForm.getPin());
                        adminobj.setCity(adminRegistrationActionForm.getCity());
                        adminobj.setState(adminRegistrationActionForm.getState());
                        adminobj.setCountry(adminRegistrationActionForm.getCountry());
                        adminobj.setLandLineNo(adminRegistrationActionForm.getLand_line_no());
                        adminobj.setMobileNo(adminRegistrationActionForm.getMobile_no());
                        adminobj.setTypeOfInstitute(adminRegistrationActionForm.getType_of_institute());
                        adminobj.setWebsite(adminRegistrationActionForm.getInstitute_website());
                        adminobj.setAdminFname(adminRegistrationActionForm.getAdmin_fname());
                        adminobj.setAdminLname(adminRegistrationActionForm.getAdmin_lname());
                        adminobj.setAdminDesignation(adminRegistrationActionForm.getAdmin_designation());
                        adminobj.setAdminEmail(adminRegistrationActionForm.getAdmin_email());
                        adminobj.setLibraryName(adminRegistrationActionForm.getLibrary_name());
                        adminobj.setCourtesy(adminRegistrationActionForm.getCourtesy());
                        adminobj.setRegistrationId(AdminRegistrationDAO.maxRegistrationID());
                        adminobj.setGender(adminRegistrationActionForm.getGender());
                        adminobj.setLoginId(adminRegistrationActionForm.getAdmin_email());
                        adminobj.setWorkingStatus("OK");
                        adminobj.setInstiLogo("");
                        adminobj.setStatus("NotRegistered");
                         result=AdminRegistrationDAO.insert1(adminobj);
                        if(result==false)
                        {
                            String msg="Request for registration failure due to some error";
                            //request.setAttribute("msg1", msg);
                            request.setAttribute("msg1",resource.getString("admin.adminregistrationaction.error2"));
                            return mapping.findForward("failure");
                        }
                        String msg="Request for Library Registration accepted successfully" ;
                         obj=new Email(adminRegistrationActionForm.getAdmin_email(),"",msg,"\n\nDear "+adminRegistrationActionForm.getAdmin_fname()+" "+adminRegistrationActionForm.getAdmin_lname()+",\n Your request for Library registration has been accepted Successfully.\nShortly you will get another mail regrading approval of your request.\nThanks\nWebAdmin\nLibMS");
                        executor.submit(new Runnable() {
                                public void run() {
                                obj.send();
                                }
                                });
                        //request.setAttribute("registration_msg", msg);
                         request.setAttribute("registration_msg",resource.getString("admin.adminregistrationaction.error3"));
                        return mapping.findForward("success");
           }

        }
        catch(Exception e)
        {
                request.setAttribute("msg1","Database Connectivity is Closed");
                log4j.error(e);
                return mapping.findForward("failure");
        }

    }
}
