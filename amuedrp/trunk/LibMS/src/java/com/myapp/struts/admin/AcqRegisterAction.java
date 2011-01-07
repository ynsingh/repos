/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import com.myapp.struts.DuplicateEntry;
import com.myapp.struts.admin.AcqRegisterActionForm;
import com.myapp.struts.opac.MyQueryResult;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class AcqRegisterAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private String staff_id;
    private String button;
    private String library_id;
    Connection con;
    PreparedStatement stmt;
    String sql;
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
        AcqRegisterActionForm acqRegisterActionForm =(AcqRegisterActionForm)form;
        staff_id=acqRegisterActionForm.getStaff_id();
        button=acqRegisterActionForm.getButton();
        //library_id=acqRegisterActionForm.getLibrary_id();
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        //System.out.println("....................."+library_id);
        if(button.equals("Register"))
        {
         boolean test=DuplicateEntry.checkDuplicateEntry("staff_detail","staff_id" , staff_id,library_id);
         if(test)
         {
            request.setAttribute("msg1", "Staff Id : "+staff_id+" already exists");
            return mapping.findForward("duplicate");
         }
         else
         {

            request.setAttribute("staff_id", staff_id);
             return mapping.findForward("register");
         }
        }

        if(button.equals("Update")||button.equals("View")||button.equals("Delete"))
        {
            
         boolean test=DuplicateEntry.checkDuplicateEntry("staff_detail","staff_id" , staff_id,library_id);
         if(!test)
         {
            request.setAttribute("msg1", "Staff Id: "+staff_id+" doesn't exists");
            return mapping.findForward("duplicate");
         }
         else
         {
            sql="select * from staff_detail where staff_id='"+staff_id+"'  and library_id='"+ library_id +"'" ;
            ResultSet rst=MyQueryResult.getMyExecuteQuery(sql);
            if(rst.next())
            request.setAttribute("updateresultset",rst);
            request.setAttribute("button", button);
                    System.out.println(button+"...............");;
             return mapping.findForward("update/view/delete");
         }
        }

        return mapping.findForward("success");
    }
}
