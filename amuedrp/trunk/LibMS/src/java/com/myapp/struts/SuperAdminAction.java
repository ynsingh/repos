/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;

import  com.myapp.struts.*;
import com.myapp.struts.AdminDAO.LoginDAO;
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
    boolean result;
    
  
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        SuperAdminActionForm admin=(SuperAdminActionForm)form;

        user_id1=admin.getUser_id1().trim();
        
        password1=admin.getPassword1().trim();
        password4=admin.getPassword2().trim();
        role="superadmin";//login.getRole();
        
if(password1.equals(password4)==false)
{
LoginDAO logindao= new LoginDAO();
System.out.println("user_id= "+user_id1+" Password="+password1);
String password2 = PasswordEncruptionUtility.password_encrupt(password1);
String password3 = PasswordEncruptionUtility.password_encrupt(password4);
//List rs = (List)logindao.getLoginDetails(user_id1, password1);
Login rs1 = (Login)logindao.getSuperAdminLoginDetails(user_id1, password2,role);
//rst1=MyQueryResult.getMyExecuteQuery("select * from login where user_id='"+user_id1+"' and password='"+password1+"' and staff_id='"+"admin.ems"+"'");

if(rs1!=null)
        {
           /*System.out.println("Staff_id"+ ((Login)rs.get(0)).getUserName());
        //    List rs2 = (List)logindao.getLoginDetailsbyRole(role);
         //   Login loginrole = new Login();
        //    if(!rs2.isEmpty())
        //    {
         //       loginrole = (Login) rs2.get(0);
                if(loginrole.getLoginId().equals(user_id1)==true){


                Login logindetails = new Login();
              //  if(rs.isEmpty())
                    logindetails = (Login)rs1.get(0);
               // else
               //     logindetails = (Login)rs.get(0);
                StaffDetail staffId=logindetails.getStaffDetail();
                System.out.println(staffId.getId().getStaffId()+" library_id="+staffId.getId().getLibraryId());
                Integer i= 0;
                if(logindetails.getPassword().equals(password4))
                {
                    System.out.println("superadmin change");
                    i=2;
                }
                /*if(user_id1.equals(user_id2)==false)
                {
                    List rst = logindao.getUser(user_id2);
                    if(!rst.isEmpty())
                    {
                        i=1;
                    }
                }
                
                
                login.setLoginId(user_id2);
                login.setPassword(password3);
                login.setAns(logindetails.getAns());
                login.setQuestion(logindetails.getQuestion());
                login.setRole(logindetails.getRole());
                login.setUserName(logindetails.getUserName());
                //login.setStaffDetail(staffId);
                LoginId loginId = new LoginId();
                loginId.setLibraryId(staffId.getId().getLibraryId());
                loginId.setStaffId(staffId.getId().getStaffId());
                login.setId(loginId);*/

rs1.setPassword(password3);
    result=logindao.updateadmin(rs1);

if(result==true)
{
       /*if(user_id1.equals(user_id2)==false && user_id2!=null){
                logindao.updateByStaffId(login);
                logindao.delete(user_id1);
       }
       else
       {*/
          
       
       request.setAttribute("msg","Record Successfully Updated");
       HttpSession session = request.getSession();
       session.removeAttribute("SuperAdminActionForm");
                return    mapping.findForward("success");
}
/*request.setAttribute("msg","Please Enter Password different from the previous one!");
HttpSession session = request.getSession();
       session.removeAttribute("SuperAdminActionForm");
            return mapping.findForward("fail");
            
        }
            }request.setAttribute("msg","Please enter SuperAdmin Id");
            HttpSession session = request.getSession();
       session.removeAttribute("SuperAdminActionForm");
            return mapping.findForward("fail");
      }
            request.setAttribute("msg","Invalid user Name or Password");
            HttpSession session = request.getSession();
       session.removeAttribute("SuperAdminActionForm");
            return mapping.findForward("fail");*/
}
else{
 request.setAttribute("msg","Password Incorrect!");
            return mapping.findForward("fail");
}
}
else{
            request.setAttribute("msg","Please Enter Password different from the previous one!");
            return mapping.findForward("fail");
}
return null;
    }
}
