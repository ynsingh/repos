/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import java.sql.*;
import  com.myapp.struts.hbm.*;
import  com.myapp.struts.AdminDAO.*;

import com.myapp.struts.CirDAO.CirculationDAO;
import com.myapp.struts.admin.CreateAccountActionForm;
import com.myapp.struts.utility.Email;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import  com.myapp.struts.utility.PasswordEncruptionUtility;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 *
 * @author System Administrator
 */
public class ChangePasswordAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    
    private String user_name;
    
    private String password;
    private String library_id;
    
    private String mem_id;
    private boolean result;
    private String sub_library_id;
    int i;
    Email obj;
    private final ExecutorService executor=Executors.newFixedThreadPool(1);
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            CreateAccountActionForm caaction=(CreateAccountActionForm)form;
            CirculationDAO cirdao=new CirculationDAO();

            
            user_name=caaction.getUser_name();
            password=caaction.getPassword();
            mem_id=caaction.getMem_id();
            
           
            HttpSession session=request.getSession();
            library_id=(String)session.getAttribute("memlib");
           sub_library_id=(String)session.getAttribute("memsublib");

      

     CirMemberDetail staffobj=cirdao.getCirMemdtail(library_id, mem_id);
     
     CirMemberAccount cirmem=cirdao.cirMemdetail(mem_id, library_id,sub_library_id);


         password=PasswordEncruptionUtility.password_encrupt(password);
        cirmem.setPassword(password);
        result=cirdao.update1(cirmem);

        



       if(result==true)
       {

            request.setAttribute("mem_id", mem_id);
            request.setAttribute("user_name", user_name);
            request.setAttribute("library_id", library_id);
            request.setAttribute("msg","Password Changed Successfully");
            return mapping.findForward("success");
        }else{
            request.setAttribute("mem_id", mem_id);
            request.setAttribute("user_name", user_name);
            request.setAttribute("library_id", library_id);
            request.setAttribute("msg","Password not Changed Successfully");
            return mapping.findForward("success");

        }

            
    }
}
