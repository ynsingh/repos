/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;

import com.myapp.struts.opac.MyQueryResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
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
    String locale,locale1;
    
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


 
           LoginActionForm loginActionForm=(LoginActionForm)form;
            HttpSession session = request.getSession();
            user_id=loginActionForm.getUsername();
            password=loginActionForm.getPassword();
            button=loginActionForm.getButton();
            locale = loginActionForm.getLocale();
            locale1 = (String)session.getAttribute("locale");    
           System.out.println("button="+button);
         if(button.equals("Log In"))
           {        
                    loginActionForm.setButton("");
                    con=MyConnection.getMyConnection();
                    stmt=con.prepareStatement("select * from login where user_id=? and password=?");
                    stmt.setString(1, user_id);
                    stmt.setString(2, password);
                    rst=stmt.executeQuery();
                if(rst.next())  //record found
                {
         
                          session.setAttribute("library_id", rst.getString(4));
                          session.setAttribute("username", rst.getString(2));
                          session.setAttribute("staff_id",rst.getString("staff_id"));

                           staff_id=rst.getString("staff_id");
                           library_id=rst.getString(4);

                         if(rst.getString(1).equals("superadmin@gmail.com"))  //superadmin
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
        else
        {     
                request.setAttribute("msg","Invalid user Name or Password");
                return mapping.findForward("failure");
        }



         }//login button



       else if(button.equals("Forget Password"))
            {
                loginActionForm.setButton("");
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
