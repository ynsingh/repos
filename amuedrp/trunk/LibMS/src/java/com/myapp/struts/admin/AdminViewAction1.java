/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import com.myapp.struts.DuplicateEntry;
import com.myapp.struts.admin.AdminViewActionForm;
import com.myapp.struts.opac.MyQueryResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;

/**
 *
 * @author Dushyant
 */
public class AdminViewAction1 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private  String user_id;
    private  String user_name;
    private  String password;
    private  String library_id;
    private  String library_name;
    private String f_name;
    private String l_name;

    private  String institute_name;
    private  String admin_login_id;
    private int registration_request_id;
    private String courtesy;
    private String gender;
private String staff_id;
private String query_id;
private String land_line_no;
private String mobile_no;
private String city1;
private String state1;
private String country1;
private String pin1;
    
    
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
            throws Exception
    {
        AdminViewActionForm admin=(AdminViewActionForm)form;
        registration_request_id=admin.getRegistration_request_id();
        institute_name=admin.getInstitute_name();
        user_id=admin.getAdmin_email();
        user_name =admin.getAdmin_fname()+" "+admin.getAdmin_lname();
        password=admin.getAdmin_password();
        library_id=admin.getLibrary_id();
        library_name=admin.getLibrary_name();
        f_name=admin.getAdmin_fname();
        l_name=admin.getAdmin_lname();
        courtesy=admin.getCourtesy();
        gender=admin.getGender();
        staff_id="admin."+library_id;
        query_id=admin.getQuery_id();
        land_line_no=admin.getLand_line_no();
        mobile_no=admin.getMobile_no();
        city1=admin.getCity();
        state1=admin.getState();
        country1=admin.getCountry();
        pin1=admin.getPin();
          //  System.out.println("..................."+institute_name);
      // System.out.println("user_id="+library_id+library_name);
  try
   {

        boolean b=DuplicateEntry.checkDuplicateEntry("library","library_id",library_id);
        boolean b1=DuplicateEntry.checkDuplicateEntry("login","staff_id",staff_id,library_id);
        if(b==true)
        {
            
         request.setAttribute("query_id",query_id);
      //   System.out.println(query_id);
             request.setAttribute("msg", "Library Already Registered with Library_ID: "+library_id);
             return mapping.findForward("failure");
        }
        if(b1==true)
        {
            request.setAttribute("msg", "Admin Already Registered with Staff_ID: "+staff_id);
           
              return mapping.findForward("failure");
        }
       //insert into library
            int i=MyQueryResult.getMyExecuteUpdate("insert into library(registration_id,library_id,library_name,staff_id)  values ('"+ registration_request_id+"','"+library_id+"','"+library_name+"','"+staff_id+"')");
            System.out.println(library_name);
            if(i!=0)
            {
                System.out.println(gender+""+courtesy);
                //insert into staff_detail
            int z=MyQueryResult.getMyExecuteUpdate("insert into staff_detail(staff_id,library_id,first_name,last_name,emai_id,date_joining,date_releaving,date_of_birth,title,gender,contact_no,mobile_no,city1,state1,country1,zip1)  values ('"+staff_id+"','"+library_id+"','"+f_name+"','"+l_name+"','"+user_id+"',null,null,null,'"+courtesy+"','"+gender+"','"+land_line_no+"','"+mobile_no+"','"+city1+"','"+state1+"','"+country1+"','"+pin1+"')");
             if(z!=0)
             {


                //insert into login

                int j=MyQueryResult.getMyExecuteUpdate("insert into login(user_id,user_name,password,library_id,staff_id) values ('"+user_id+"','"+user_name+"','"+password+"','"+library_id+"','"+staff_id+"')");
                /*java mailing code to send it */

                System.out.println(library_id);


                if(j!=0)
                {
                        Privilege.assignAdminPrivilege(staff_id,library_id);
                        int k=MyQueryResult.getMyExecuteUpdate("update admin_registration set status='Registered',library_id='"+library_id+"',staff_id='"+staff_id+"' where registration_id="+registration_request_id);
                        System.out.println(k);
                        if(k!=0)
                        {
                        request.setAttribute("accept_msg1", library_id);
                        request.setAttribute("accept_msg2",library_name );
                        request.setAttribute("accept_msg3",institute_name );
                        request.setAttribute("msg", "Request Accepted with library Id "+library_id);
                        return mapping.findForward("success");

                        }
                        else
                        {
                            int m=MyQueryResult.getMyExecuteUpdate("delete from library where staff_id='"+staff_id+"' and library_id='"+ library_id + "'");
                            if(m!=0)
                            {}
                            int l=MyQueryResult.getMyExecuteUpdate("delete from staff_detail where staff_id='"+staff_id+"' and library_id='"+ library_id + "'");
                            if(l!=0)
                            {}
                            int n=MyQueryResult.getMyExecuteUpdate("delete from login where staff_id='"+staff_id+"' and library_id='"+ library_id + "'");
                            if(n!=0)
                            {}


                        request.setAttribute("accept_msg1", library_id);
                        request.setAttribute("accept_msg2",library_name );
                        request.setAttribute("accept_msg3",institute_name );
                        request.setAttribute("msg", " Record not updated in admin_registration");
                        return mapping.findForward("failure");
            
                        }
                }
                else
                {
                     int k=MyQueryResult.getMyExecuteUpdate("delete from library where staff_id='"+staff_id+"' and library_id='"+ library_id + "'");
                    if(k!=0)
                    {}
                     int l=MyQueryResult.getMyExecuteUpdate("delete from staff_detail where staff_id='"+staff_id+"' and library_id='"+ library_id + "'");
                    if(l!=0)
                    {}
                    request.setAttribute("accept_msg1", library_id);
                    request.setAttribute("accept_msg2",library_name );
                    request.setAttribute("accept_msg3",institute_name );
                    request.setAttribute("msg", " Record not updated in login");
                   return mapping.findForward("failure");

                }
            }
             else
                {
                    int k=MyQueryResult.getMyExecuteUpdate("delete from library where staff_id='"+staff_id+"' and library_id='"+ library_id + "'");
                    if(k!=0)
                    {}

                    request.setAttribute("accept_msg1", library_id);
                    request.setAttribute("accept_msg2",library_name );
                    request.setAttribute("accept_msg3",institute_name );
                    request.setAttribute("msg", " Record not updated in staff_details");
                    return mapping.findForward("failure");

                }






            }
             else
                {
                    request.setAttribute("accept_msg1", library_id);
                    request.setAttribute("accept_msg2",library_name );
                    request.setAttribute("accept_msg3",institute_name );
                    request.setAttribute("msg", " Record not updated in library");
                   return mapping.findForward("failure");

                }
            
        
        

    }
        catch(Exception e2)
        {
      //  System.out.println(e);
       
        }

        return mapping.findForward("success");
    }
}
