/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;
import java.util.*;

import  com.myapp.struts.*;
import javax.servlet.http.*;
import javax.servlet.http.Cookie;
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
public class LoginAction extends org.apache.struts.action.Action {
    String user_id;
    String username;
    String password;
    Connection con;
    ResultSet rst,rst1,rst2,rst3,rst4,rst5,rst6;
    PreparedStatement stmt;
    String staff_id;
    String library_id;
    String button;
String session_id;
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    Hashtable hashtable;
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

HttpSession session=request.getSession();

session_id=session.getId();
System.out.println(session.getId());
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;}
    else{ rtl="RTL";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);



 
           LoginActionForm loginActionForm=(LoginActionForm)form;
           
            user_id=loginActionForm.getUsername();
            password=loginActionForm.getPassword();
            button=loginActionForm.getButton1();




 //System.out.println(text2+"...........");




         if(button.equals("Log In"))
           {



            
                    //loginActionForm.setButton("");
                    con=MyConnection.getMyConnection();
                    stmt=con.prepareStatement("select * from login where user_id=? and password=?");
                    stmt.setString(1, user_id);
                    stmt.setString(2, password);
                    rst=stmt.executeQuery();
                if(rst.next())  //record found
                {
                    session.setMaxInactiveInterval(60*40);

                  /*  Cookie cookie = new Cookie ("library_id",rst.getString("library_id"));
                    Cookie cookie1 = new Cookie ("staff_id",rst.getString("staff_id"));
                  

                    response.addCookie(cookie);
                    response.addCookie(cookie1);
                    */
                  
                          session.setAttribute("library_id", rst.getString(4));
                          session.setAttribute("username", rst.getString(2));
                          session.setAttribute("staff_id",rst.getString("staff_id"));
                          session.setAttribute("login_role",rst.getString("role"));

                           staff_id=rst.getString("staff_id");
                           library_id=rst.getString(4);

                         if(rst.getString(5).equals("admin.libms"))  //superadmin
                         {
                                //pending
                                con=MyConnection.getMyConnection();
                                stmt=con.prepareStatement("select * from admin_registration where status = 'NotRegistered'");
                                rst=stmt.executeQuery();
                                session.setAttribute("resultset", rst);

                                stmt=con.prepareStatement("select count(*) from admin_registration where status = 'NotRegistered'");
                                rst=stmt.executeQuery();
                                rst.next();
                                int count=rst.getInt(1);
                                
                                session.setAttribute("count", count);

                                //registered
                                con=MyConnection.getMyConnection();
                                stmt=con.prepareStatement("select * from admin_registration where status='Registered' ");
                                rst=stmt.executeQuery();
                                session.setAttribute("resultset1", rst);

                                stmt=con.prepareStatement("select count(*) from admin_registration where status ='Registered'");
                                rst=stmt.executeQuery();
                                rst.next();
                                count=rst.getInt(1);
                                session.setAttribute("count1", count);


                                //view All

                                con=MyConnection.getMyConnection();
                                stmt=con.prepareStatement("select * from admin_registration");
                                rst=stmt.executeQuery();
                                session.setAttribute("resultset2", rst);

                                stmt=con.prepareStatement("select count(*) from admin_registration");
                                rst=stmt.executeQuery();
                                rst.next();
                                count=rst.getInt(1);
                                session.setAttribute("count2", count);


                    //java mailing code to send it on Admin registration page



















                                return mapping.findForward("superadmin");
                         }
                             //  System.out.println(user_id+""+password+staff_id);

                            //check first login
                ResultSet block=MyQueryResult.getMyExecuteQuery("select working_status from library where library_id='"+rst.getString("library_id")+"' and working_status='Blocked'");
                 if(block.next())
                 {
                    request.setAttribute("msg1","Library is Blocked, Contact Admin.");
                    return mapping.findForward("failure");

                 }
                 else{
                            if(rst.getString(6)==null)
                            {
                            ResultSet rs1=MyQueryResult.getMyExecuteQuery("select library_name from library where library_id='"+library_id+"'");
                            if(rs1.next()){session.setAttribute("library_name", rs1.getString(1));}

                            String sql1=("select * from privilege where staff_id='"+staff_id+"' and library_id='"+ library_id +"'");
                            PreparedStatement st=con.prepareStatement(sql1);
                            rst1=st.executeQuery();
                            rst1.next();
                            session.setAttribute("privilege_resultset", rst1);

                            String sql2=("select * from acq_privilege where staff_id='"+staff_id+"' and library_id='"+ library_id +"'");
                            st=con.prepareStatement(sql2);
                            rst2=st.executeQuery();
                            rst2.next();
                            session.setAttribute("acq_privilege_resultset", rst2);

                            String sql3=("select * from cat_privilege where staff_id='"+staff_id+"' and library_id='"+ library_id +"'");
                            st=con.prepareStatement(sql3);
                            rst3=st.executeQuery();
                            rst3.next();
                            session.setAttribute("cat_privilege_resultset", rst3);

                            String sql4=("select * from cir_privilege where staff_id='"+staff_id+"' and library_id='"+ library_id +"'");
                            st=con.prepareStatement(sql4);
                            rst4=st.executeQuery();
                            rst4.next();
                            session.setAttribute("cir_privilege_resultset", rst4);

                            String sql5=("select * from ser_privilege where staff_id='"+staff_id+"' and library_id='"+ library_id +"'");
                            st=con.prepareStatement(sql5);
                            rst5=st.executeQuery();
                            rst5.next();
                            session.setAttribute("ser_privilege_resultset", rst5);

                              return mapping.findForward("firstlogin");
                     
                            }

                         
                          
                            ResultSet rs1=MyQueryResult.getMyExecuteQuery("select library_name from library where library_id='"+library_id+"'");
                            if(rs1.next()){session.setAttribute("library_name", rs1.getString(1));}

                            String sql1=("select * from privilege where staff_id='"+staff_id+"' and library_id='"+ library_id +"'");
                            PreparedStatement st=con.prepareStatement(sql1);
                            rst1=st.executeQuery();
                            rst1.next();
                            session.setAttribute("privilege_resultset", rst1);

                            String sql2=("select * from acq_privilege where staff_id='"+staff_id+"' and library_id='"+ library_id +"'");
                            st=con.prepareStatement(sql2);
                            rst2=st.executeQuery();
                            rst2.next();
                            session.setAttribute("acq_privilege_resultset", rst2);

                            String sql3=("select * from cat_privilege where staff_id='"+staff_id+"' and library_id='"+ library_id +"'");
                            st=con.prepareStatement(sql3);
                            rst3=st.executeQuery();
                            rst3.next();
                            session.setAttribute("cat_privilege_resultset", rst3);

                            String sql4=("select * from cir_privilege where staff_id='"+staff_id+"' and library_id='"+ library_id +"'");
                            st=con.prepareStatement(sql4);
                            rst4=st.executeQuery();
                            rst4.next();
                            session.setAttribute("cir_privilege_resultset", rst4);

                            String sql5=("select * from ser_privilege where staff_id='"+staff_id+"' and library_id='"+ library_id +"'");
                            st=con.prepareStatement(sql5);
                            rst5=st.executeQuery();
                            rst5.next();
                            session.setAttribute("ser_privilege_resultset", rst5);
                    
                            //System.out.println("ok1"+staff_id);
                           // System.out.println(user_id+""+password+"1");
                            return mapping.findForward("success");
                        

              //System.out.println("ok"+staff_id);
                 }
               

        }
        else
        {     
                request.setAttribute("msg1","Invalid user Name or Password");
                return mapping.findForward("failure");
        }



         }//login button



       else if(button.equals("Forget Password"))
            {
                //loginActionForm.setButton("");
                con=MyConnection.getMyConnection();
                stmt=con.prepareStatement("select * from login where user_id=? and question is not null");
                stmt.setString(1, user_id);
                rst=stmt.executeQuery();
                if(rst.next())
                {
                session.setAttribute("pass","t");
                session.setAttribute("user_id", rst.getString("user_id"));
                session.setAttribute("username", rst.getString("user_name"));
                request.setAttribute("question", rst.getString("question"));
                request.setAttribute("staff_id",rst.getString("staff_id"));
                
                return mapping.findForward("forgetpassword");
                }
                else
                {
                    
                    request.setAttribute("msg","Security question not assigned");
                    return mapping.findForward("failure");
                }

            }



          //  else//if(!locale.equals(locale1))
          //  {
                   // if(locale.equals("English")) session.setAttribute("locale", "en");
                   // else if(locale.equals("Hindi")) session.setAttribute("locale", "hi");
                  //  else if(locale.equals("Urdu")) session.setAttribute("locale", "ur");
                   // else if(locale.equals("Arabic")) session.setAttribute("locale", "ar");
                    //return mapping.findForward("login");
           // }
          
           
      return mapping.findForward("failure");
    }
}
