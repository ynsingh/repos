

package com.myapp.struts;

import  com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.*;
import com.myapp.struts.systemsetupDAO.FacultyDAO;
import com.myapp.struts.systemsetupDAO.MemberCategoryDAO;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import java.util.*;
import javax.servlet.http.*;
import  com.myapp.struts.utility.PasswordEncruptionUtility;

/**
 * Developed By : Kedar Kumar
 * Modified By  : 18-Feb-2011
 * Login Action to Check the Login User and Password
 */
public class LoginAction extends org.apache.struts.action.Action {


    String login_id;
    String username;
    String password;
    Connection con;
  

    List ls1,ls2,ls3,ls4;
    PreparedStatement stmt;
    String staff_id;
    String library_id;
    String button;

    String sublibrary_id;
    String main_library;
List list1,list2;
    private List list;






    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {



        HttpSession session=request.getSession();



    try
    {





            LoginActionForm loginActionForm=(LoginActionForm)form;

            login_id=loginActionForm.getUsername();
            password=loginActionForm.getPassword();
            button=loginActionForm.getButton1();

System.out.println(password);
            password=PasswordEncruptionUtility.password_encrupt(password);


            con=MyConnection.getMyConnection();
            if(con==null)
            {
             request.setAttribute("msg1","Database Connectivity is Closed");
             return mapping.findForward("failure");
            }



            if(button.equals("Log In"))
           {



              Login tempobj=(Login)LoginDAO.searchUser(login_id,password);





              /*  If the Entered User and Password in Valid */
              if(tempobj!=null)
              {
                list=(List)SubLibraryDAO.getAllSubLibrary( tempobj.getId().getLibraryId());
                   list1=(List)MemberCategoryDAO.searchEmpType( tempobj.getId().getLibraryId());
          System.out.println(list1.size());
          list2=(List)FacultyDAO.searchFaculty( tempobj.getId().getLibraryId());

                session.setAttribute("sublibrary",list);
                System.out.println("sublibrary"+list.size());
            session.setAttribute("list1",list1);
            session.setAttribute("list2",list2);

               // rst=MyQueryResult.getMyExecuteQuery("select * from login where login_id='"+login_id+"' and password='"+password+"'");
               // rst.next();
                          session.setAttribute("library_id", tempobj.getId().getLibraryId());
                          session.setAttribute("username", tempobj.getUserName());
                          session.setAttribute("staff_id",tempobj.getId().getStaffId());
                          session.setAttribute("login_role",tempobj.getRole());
                        session.setAttribute("login_id",tempobj.getLoginId());



                          session.setAttribute("sublibrary_id",tempobj.getSublibraryId());
                          SubLibrary sub1=SubLibraryDAO.getMainSubLibraryId( tempobj.getId().getLibraryId());
                          if(sub1!=null)
                              session.setAttribute("mainsublibrary",sub1.getId().getSublibraryId());

                          System.out.println(sub1.getId().getSublibraryId()+"...........");

                          staff_id=tempobj.getId().getStaffId();
                          library_id=tempobj.getId().getLibraryId();
                          sublibrary_id=tempobj.getSublibraryId();
                          session.setAttribute("staff_id",staff_id);


                          SubLibrary sub=(SubLibrary)SubLibraryDAO.getLibName(library_id,sublibrary_id);
                          if(sub!=null)
                          {session.setAttribute("sublibrary_name",sub.getSublibName());
                          }








                         if(tempobj.getId().getStaffId().equals("admin.libms"))  //superadmin
                         {
                                /* Collect the List of Pending Institute 
                                stmt=con.prepareStatement("select * from admin_registration where status = 'NotRegistered'");
                                rst=stmt.executeQuery();
                                session.setAttribute("resultset", rst);
                                  Collect the Count of NotRegistered Institute 
                                stmt=con.prepareStatement("select count(*) from admin_registration where status = 'NotRegistered'");
                                rst=stmt.executeQuery();
                                rst.next();
                                int count=rst.getInt(1);

                                session.setAttribute("count", count);

                                  Collect the List of Registered Institute 
                                stmt=con.prepareStatement("select * from admin_registration where status='Registered' ");
                                rst=stmt.executeQuery();
                                session.setAttribute("resultset1", rst);
                                 Collect the Count of Registered Institute 
                                stmt=con.prepareStatement("select count(*) from admin_registration where status ='Registered'");
                                rst=stmt.executeQuery();
                                rst.next();
                                count=rst.getInt(1);
                                session.setAttribute("count1", count);


                                 Collect the List of All Institute 
                                stmt=con.prepareStatement("select * from admin_registration");
                                rst=stmt.executeQuery();
                                session.setAttribute("resultset2", rst);

                                stmt=con.prepareStatement("select count(*) from admin_registration");
                                rst=stmt.executeQuery();
                                rst.next();
                                count=rst.getInt(1);
                                session.setAttribute("count2", count);


                    //java mailing code to send it on Admin registration page*/
















                    session.setAttribute("user_id",login_id);
                    System.out.println("Admin---------------"+login_id);



                                return mapping.findForward("superadmin");
                         }//SuperAdmin Close if-else


                sublibrary_id=tempobj.getSublibraryId();
                library_id=tempobj.getId().getLibraryId();
                staff_id=tempobj.getId().getStaffId();
                login_id=tempobj.getLoginId();



        /* Check Whether The Library is Blocked or Not */

              Library libobj=(Library)LibraryDAO.searchBlockLibrary(library_id);
              if(libobj!=null)
              {    request.setAttribute("msg1","Library is Blocked, Contact Admin.");
                   return mapping.findForward("failure");
               }
            else
            {

                        sublibrary_id=tempobj.getSublibraryId();
                        libobj=(Library)LibraryDAO.getLibraryName(library_id);

     /* Check Whether Its First Time Login of the User
      *
      *
      *
      */
                            Login logobj=LoginDAO.searchFirstLogin(staff_id,library_id,sublibrary_id,login_id);

                            if(logobj!=null)
                            {
                                session.setAttribute("username", logobj.getUserName());
                                 session.setAttribute("staff_id",logobj.getId().getStaffId());

                            session.setAttribute("library_name", libobj.getLibraryName());

                            Privilege priv=PrivilegeDAO.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("privilege_resultset", priv);

                           AcqPrivilege acq_priv=AcqPrivilegeDAO.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("acq_privilege_resultset", acq_priv);

                            CatPrivilege cat_priv=CatPrivilegeDAO.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("cat_privilege_resultset", cat_priv);


                            CirPrivilege cir_priv=CirPrivilegeDAO.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("cir_privilege_resultset", cir_priv);

                            SerPrivilege ser_priv=SerPrivilegeDAO.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("ser_privilege_resultset", ser_priv);
                            System.out.println(".......................");

                              return mapping.findForward("firstlogin");

                            }
     /* Login With Privileges  of the User
      *
      *
      *
      *
      */
                            session.setAttribute("library_name", libobj.getLibraryName());

                            Privilege priv=PrivilegeDAO.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("privilege_resultset", priv);


                           AcqPrivilege acq_priv=AcqPrivilegeDAO.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("acq_privilege_resultset", acq_priv);

                            CatPrivilege cat_priv=CatPrivilegeDAO.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("cat_privilege_resultset", cat_priv);


                            CirPrivilege cir_priv=CirPrivilegeDAO.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("cir_privilege_resultset", cir_priv);

                            SerPrivilege ser_priv=SerPrivilegeDAO.getPrivilege(library_id,sublibrary_id,staff_id);
                            session.setAttribute("ser_privilege_resultset", ser_priv);

                            if(tempobj.getRole().contains("admin") || tempobj.getRole().contains("Admin"))
                            {
                            return mapping.findForward("success");
                            }
                            else
                                return mapping.findForward("success1");

              }/*End of Non-Block Library IF-ELSE*/








        }/*End of User Library IF-ELSE*/
        else
        {
                request.setAttribute("msg1","Invalid user Name or Password");
                return mapping.findForward("failure");
        }



         }//login button



       else if(button.equals("Forget Password"))
            {

            /* Check Weather the Question is Assigned for the User or Not */
               // stmt=con.prepareStatement("select * from login where login_id=? and question is not null");
               /// stmt.setString(1, login_id);
               Login obj=LoginDAO.searchForgetPassword(login_id);
                if(obj!=null)
                {
                session.setAttribute("pass","t");
                session.setAttribute("user_id", obj.getLoginId());
                session.setAttribute("library_id", obj.getId().getLibraryId());
                session.setAttribute("login_id",obj.getLoginId());
                session.setAttribute("username", obj.getUserName());
                session.setAttribute("question",obj.getQuestion());
                session.setAttribute("staff_id",obj.getId().getStaffId());


                return mapping.findForward("forgetpassword");
                }
                else
                {

                    request.setAttribute("msg","Security question not assigned");
                    return mapping.findForward("failure");
                }

            }


    }
    catch(Exception e)
    {
    System.out.println(e);

    }


      return null;
    }
}
