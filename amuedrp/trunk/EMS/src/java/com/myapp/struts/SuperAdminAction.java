/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;

import  com.myapp.struts.*;
import com.myapp.struts.hbm.*;
import com.myapp.struts.utility.PasswordEncruptionUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dushyant
 */
public class SuperAdminAction extends org.apache.struts.action.Action {
    String user_id1;
    String user_id2;
    String password1;
    String password4;
    ResultSet rst1,rst2;
    String role;
    String staff_id;
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
    
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        SuperAdminActionForm admin=(SuperAdminActionForm)form;
Login login= new Login();
        user_id1=admin.getUser_id1().trim();
        user_id2=null;//admin.getUser_id2().trim();
        password1=admin.getPassword1().trim();
        password4=admin.getPassword2().trim();
        role="Superadmin";//login.getRole();

        HttpSession session=request.getSession();
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



        
if(password1.equals(password4)==false)
{
LoginDAO logindao= new LoginDAO();
System.out.println("user_id= "+user_id1+" Password="+password1);
String password2 = PasswordEncruptionUtility.password_encrupt(password1);
String password3 = PasswordEncruptionUtility.password_encrupt(password4);
List rs = (List)logindao.getLoginDetails(user_id1, password1);
List rs1 = (List)logindao.getLoginDetails(user_id1, password2);
//rst1=MyQueryResult.getMyExecuteQuery("select * from login where user_id='"+user_id1+"' and password='"+password1+"' and staff_id='"+"admin.ems"+"'");

if(!rs.isEmpty()||!rs1.isEmpty())
        {
           //System.out.println("Staff_id"+ ((Login)rs.get(0)).getUserName());
            List rs2 = (List)logindao.getLoginDetailsbyRole(role);
            Login loginrole = new Login();
            if(!rs2.isEmpty())
            {
                loginrole = (Login) rs2.get(0);
                if(loginrole.getUserId().equals(user_id1)==true){


                Login logindetails = new Login();
                if(rs.isEmpty())
                    logindetails = (Login)rs1.get(0);
                else
                    logindetails = (Login)rs.get(0);
                StaffDetail staffId=logindetails.getStaffDetail();
                Integer i= 0;
                if(logindetails.getPassword().equals(password1))
                {
                    i=2;
                }
                if(user_id1.equals(user_id2)==false)
                {
                    List rst = logindao.getUser(user_id2);
                    if(!rst.isEmpty())
                    {
                        i=1;
                    }
                }
                
                login.setUserId(user_id2);
                login.setPassword(password3);
                login.setAns(logindetails.getAns());
                login.setQuestion(logindetails.getQuestion());
                login.setRole(logindetails.getRole());
                login.setUserName(logindetails.getUserName());
                login.setStaffDetail(staffId);

if(i==0)
{
       if(user_id1.equals(user_id2)==false && user_id2!=null){
                logindao.updateByStaffId(login);
                logindao.delete(user_id1);
       }
       else
       {
           login.setUserId(user_id1);
           logindao.update(login);
       }
      // String msg=resource.getString("record_updated_successfully");
       request.setAttribute("msg","Record Updated Successfully");
       session.removeAttribute("SuperAdminActionForm");
                return    mapping.findForward("success");
}
request.setAttribute("msg","User Name Already Exists");
       session.removeAttribute("SuperAdminActionForm");
            return mapping.findForward("fail");
            
        }
            }request.setAttribute("msg","Please enter SuperAdmin Id");
            
       session.removeAttribute("SuperAdminActionForm");
            return mapping.findForward("fail");
      }
String msg=resource.getString("invalid_user_pass");
            request.setAttribute("msg",msg);
            
       session.removeAttribute("SuperAdminActionForm");
            return mapping.findForward("fail");
}
else{
         String msg=resource.getString("different_password");
            request.setAttribute("msg",msg);
            return mapping.findForward("fail");
}

    }
}
