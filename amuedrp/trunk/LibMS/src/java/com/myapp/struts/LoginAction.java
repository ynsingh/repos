

package com.myapp.struts;


import  com.myapp.struts.AdminDAO.*;
import com.myapp.struts.hbm.AcqPrivilege;
import com.myapp.struts.hbm.AdminRegistration;
import com.myapp.struts.hbm.CatPrivilege;
import com.myapp.struts.hbm.CirPrivilege;
import com.myapp.struts.hbm.Department;
import com.myapp.struts.hbm.Library;
import com.myapp.struts.hbm.Login;
import com.myapp.struts.hbm.Privilege;
import com.myapp.struts.hbm.SerPrivilege;
import com.myapp.struts.hbm.StaffDetail;
import com.myapp.struts.hbm.SubLibrary;
import com.myapp.struts.systemsetupDAO.DeptDAO;
import com.myapp.struts.systemsetupDAO.FacultyDAO;
import com.myapp.struts.systemsetupDAO.MemberCategoryDAO;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.*;
import  com.myapp.struts.utility.PasswordEncruptionUtility;
import  com.myapp.struts.utility.DateCalculation;
import java.sql.Connection;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Developed By : Kedar Kumar
 * Modified By  : 18-Feb-2011
 * Login Action to Check the Login User and Password
 */
public class LoginAction extends org.apache.struts.action.Action {


    String login_id;
    String username;
    String password;
   
 
    List ls1,ls2,ls3,ls4;
   
    String staff_id;
    String library_id;
    String button;
 Connection con;
 LoginDAO logindao=new LoginDAO();
    String sublibrary_id;
    String main_library;
List list1,list2;
    private List list;
 Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";

  String date="";
  String time="";
 SubLibraryDAO sublibdao=new SubLibraryDAO();
 MemberCategoryDAO memdao=new MemberCategoryDAO();
 FacultyDAO facdao=new FacultyDAO();
 StaffDetailDAO staffdetaildao=new StaffDetailDAO();
 AdminRegistrationDAO admindao=new AdminRegistrationDAO();
 DeptDAO deptdao=new DeptDAO();
 LibraryDAO libdao=new LibraryDAO();
 PrivilegeDAO privdao=new PrivilegeDAO();
 AcqPrivilegeDAO acqprevdao=new AcqPrivilegeDAO();
 SerPrivilegeDAO serprevdao=new SerPrivilegeDAO();
 CirPrivilegeDAO cirprevdao=new CirPrivilegeDAO();
 CatPrivilegeDAO catprevdao=new CatPrivilegeDAO();
 


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {

     //   DOMConfigurator.configure("log4j.xml");
System.gc();


date=DateCalculation.now();
time=String.valueOf(System.currentTimeMillis());

        HttpSession session=request.getSession();
        session.setAttribute("pagename", "Welcome Page");
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
//    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
//    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);



  



 





            LoginActionForm loginActionForm=(LoginActionForm)form;

            login_id=loginActionForm.getUsername();
            password=loginActionForm.getPassword();
            button=loginActionForm.getButton1();


          if(password!=null)
            password=PasswordEncruptionUtility.password_encrupt(password);


       if(button==null)
           button="Log In";



       


try{



            if(button.equals("Log In"))
           {

String remote=(String)session.getAttribute("remoteauth");
Login tempobj;
if(remote!=null)
{
    login_id=(String)session.getAttribute("email_id");
    tempobj=(Login)logindao.searchLoginID(login_id);
}
else{
    tempobj=(Login)logindao.searchUser(login_id,password);
}

              /*  If the Entered User and Password in Valid */
              if(tempobj!=null)
              {

                list=(List)sublibdao.getAllSubLibrary( tempobj.getId().getLibraryId());
                   list1=(List)memdao.searchEmpType( tempobj.getId().getLibraryId());
         
          list2=(List)facdao.searchFaculty( tempobj.getId().getLibraryId());

                session.setAttribute("sublibrary",list);
               
            session.setAttribute("list1",list1);
            session.setAttribute("list2",list2);
            StaffDetail staff=staffdetaildao.searchStaffId(tempobj.getId().getStaffId(),  tempobj.getId().getLibraryId());
               
                          session.setAttribute("library_id", tempobj.getId().getLibraryId());
                          session.setAttribute("username",staff.getTitle()+" "+ tempobj.getUserName());
                          session.setAttribute("staff_id",tempobj.getId().getStaffId());
                          session.setAttribute("login_role",tempobj.getRole());
                        session.setAttribute("login_id",tempobj.getLoginId());


                         AdminRegistration admin=admindao.searchInstituteAdmin((String)session.getAttribute("login_id"));
                          session.setAttribute("AdminDetail",admin);

                    String lib_id=tempobj.getId().getLibraryId();
                    String sublib_id=tempobj.getSublibraryId();

                          session.setAttribute("sublibrary_id",tempobj.getSublibraryId());
                            System.out.println("before sublib ");
                          SubLibrary sub1=sublibdao.getMainSubLibraryId(lib_id,sublib_id);
                       System.out.println("After sublib "+sub1);

                          if(sub1!=null)
                              session.setAttribute("mainsublibrary",sub1.getId().getSublibraryId());

                        

                          staff_id=tempobj.getId().getStaffId();
                          library_id=tempobj.getId().getLibraryId();
                          sublibrary_id=tempobj.getSublibraryId();
                          session.setAttribute("staff_id",staff_id);


                          Department dept=deptdao.getDeptName(library_id, sublibrary_id);
                          if(dept!=null)
                          {
                        
                          session.setAttribute("sublibrary_name",dept.getDeptName());
                          }
                          else
                              session.setAttribute("sublibrary_name",sub1.getSublibName());
                        








                         if(tempobj.getId().getStaffId().equals("admin.libms"))  //superadmin
                         {
                              
                            session.setAttribute("user_id",login_id);

                         
                           

                                return mapping.findForward("superadmin");
                         }//SuperAdmin Close if-else


                sublibrary_id=tempobj.getSublibraryId();
                library_id=tempobj.getId().getLibraryId();
                staff_id=tempobj.getId().getStaffId();
                login_id=tempobj.getLoginId();



        /* Check Whether The Library is Blocked or Not */

              Library libobj=(Library)libdao.searchBlockLibrary(library_id);
              if(libobj!=null)
              {
            
                  
                  //  request.setAttribute("msg1","Library is Blocked, Contact Admin.");
                    request.setAttribute("msg1",resource.getString("admin.LoginAction.error1"));

                   return mapping.findForward("failure");
               }
            else
            {

                        sublibrary_id=tempobj.getSublibraryId();
                 Library  libobj1=(Library)libdao.getLibraryName(library_id);
                 AdminRegistration admin1=admindao.searchInstitute(library_id);

     /* Check Whether Its First Time Login of the User
      *
      *
      *
      */
                            Login logobj=logindao.searchFirstLogin(staff_id,library_id,sublibrary_id,login_id);

                            if(logobj!=null)
                            {
                                session.setAttribute("username", logobj.getUserName());
                                 session.setAttribute("staff_id",logobj.getId().getStaffId());

                            session.setAttribute("library_name", libobj1.getLibraryName());
                             session.setAttribute("library_add",admin1.getInstituteAddress() );

                            Privilege priv=privdao.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("privilege_resultset", priv);

                           AcqPrivilege acq_priv=acqprevdao.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("acq_privilege_resultset", acq_priv);

                            CatPrivilege cat_priv=catprevdao.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("cat_privilege_resultset", cat_priv);


                            CirPrivilege cir_priv=cirprevdao.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("cir_privilege_resultset", cir_priv);

                            SerPrivilege ser_priv=serprevdao.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("ser_privilege_resultset", ser_priv);
                           
                     
                             
                              return mapping.findForward("firstlogin");

                            }
     /* Login With Privileges  of the User
      *
      *
      *
      *
      */
                            session.setAttribute("library_name", libobj1.getLibraryName());
                            session.setAttribute("library_add",admin1.getInstituteAddress() );

                            Privilege priv=privdao.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("privilege_resultset", priv);


                           AcqPrivilege acq_priv=acqprevdao.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("acq_privilege_resultset", acq_priv);

                            CatPrivilege cat_priv=catprevdao.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("cat_privilege_resultset", cat_priv);


                            CirPrivilege cir_priv=cirprevdao.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("cir_privilege_resultset", cir_priv);

                            SerPrivilege ser_priv=serprevdao.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("ser_privilege_resultset", ser_priv);

                            if(tempobj.getRole().contains("admin") || tempobj.getRole().contains("Admin"))
                            {
                           
                                
                            return mapping.findForward("success");
                            }
                            else
                            {
                             
                                return mapping.findForward("success1");}

              }/*End of Non-Block Library IF-ELSE*/








        }/*End of User Library IF-ELSE*/
        else
        {
              
                 
                //request.setAttribute("msg1","Invalid user Name or Password");
                 request.setAttribute("msg1",resource.getString("admin.LoginAction.error2"));
                return mapping.findForward("failure");
        }



         }//login button



       else if(button.equals("Forget Password"))
            {

            /* Check Weather the Question is Assigned for the User or Not */
               // stmt=con.prepareStatement("select * from login where login_id=? and question is not null");
               /// stmt.setString(1, login_id);
               Login obj=logindao.searchForgetPassword(login_id);
                if(obj!=null)
                {
                session.setAttribute("pass","t");
                session.setAttribute("user_id", obj.getLoginId());
                session.setAttribute("library_id", obj.getId().getLibraryId());
                session.setAttribute("login_id",obj.getLoginId());
                session.setAttribute("username", obj.getUserName());
                session.setAttribute("question",obj.getQuestion());
                session.setAttribute("staff_id",obj.getId().getStaffId());
              //  systemlog.info(login_id+","+library_id+","+sublibrary_id);
 
                return mapping.findForward("forgetpassword");
                }
                else
                {
                 //  systemlog.info(login_id+","+library_id+","+sublibrary_id);
 
                   // request.setAttribute("msg","Security question not assigned");
                    request.setAttribute("msg",resource.getString("admin.LoginAction.error3"));
                    return mapping.findForward("failure");
                }

            }


    }
    catch(Exception e)
    {
    e.printStackTrace();
      request.setAttribute("msg","Data Connectivity Problem,Contact WebAdmin");
                    return mapping.findForward("failure");
    }


      return null;
    }
}
