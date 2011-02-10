/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import com.myapp.struts.DuplicateEntry;
import com.myapp.struts.MyQueryResult;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
/**
 *
 * @author System Administrator
 */
public class AssignPrivilegeAction extends org.apache.struts.action.Action {

     /* forward name="success" path="" */
    private String staff_id;
    private String button;
    private String library_id;
    private String staff_name;
private String sql1,sql2,sql3,sql4,sql5;
private ResultSet privilege_resultset,acq_privilege_resultset,cat_privilege_resultset,cir_privilege_resultset,ser_privilege_resultset;

    String sql;
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
            throws Exception {
        AcqRegisterActionForm acqRegisterActionForm =(AcqRegisterActionForm)form;
        staff_id=acqRegisterActionForm.getStaff_id();

        button=acqRegisterActionForm.getButton();
        request.setAttribute("button", button);
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");



        if(button.equals("Assign Privilege")||button.equals("Change Privilege"))
        {
         boolean test=DuplicateEntry.checkDuplicateEntry("login","staff_id" , staff_id,library_id);

         if(!test)
         {
            request.setAttribute("msg1", "Staff Id: "+staff_id+" Account not exists");
                request.setAttribute("staff_name", staff_name);
            return mapping.findForward("notfound");
         }
         else
         {


         String id="admin."+library_id;   //get Institute admin ID
            if(staff_id.equals(id))
           {

                request.setAttribute("staff_name", staff_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                request.setAttribute("msg1", "You Cannot Modify Priviege of Institute Admin");
             return mapping.findForward("notfound");
           }

        id=(String)session.getAttribute("staff_id");  //cannot delete own account
            if(staff_id.equals(id))
            {


               request.setAttribute("staff_name", staff_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                request.setAttribute("msg1", "You Cannot Modify Priviliege of Own Account");
                  return mapping.findForward("notfound");

            }




               String login_role=(String)session.getAttribute("login_role");    //cannot create co-admin
         ResultSet rs1=MyQueryResult.getMyExecuteQuery("select * from login where staff_id='"+staff_id+"' and library_id='"+library_id+"'");

            if(rs1.next())
            {
                if(rs1.getString("role").equals(login_role))
                {
                   request.setAttribute("staff_name", staff_name);
                request.setAttribute("library_id", library_id);
                request.setAttribute("staff_id", staff_id);
                request.setAttribute("msg1", "You Cannot Update Priviliege of Admin");
                return mapping.findForward("notfound");
                }
            }





             ResultSet rs=MyQueryResult.getMyExecuteQuery("select user_name from login where staff_id='"+staff_id+"' and library_id='"+ library_id + "'");
             if(rs.next())
                 staff_name=rs.getString("user_name");

            request.setAttribute("button", button);
            request.setAttribute("staff_name", staff_name);
            request.setAttribute("staff_id", staff_id);

            sql1 ="select * from privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'" ;
        sql2="select * from acq_privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'" ;
        sql3="select * from cat_privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'" ;
        sql4="select * from cir_privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'" ;
        sql5="select * from ser_privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'" ;

        privilege_resultset=MyQueryResult.getMyExecuteQuery(sql1);
        acq_privilege_resultset=MyQueryResult.getMyExecuteQuery(sql2);
        cat_privilege_resultset=MyQueryResult.getMyExecuteQuery(sql3);
        cir_privilege_resultset=MyQueryResult.getMyExecuteQuery(sql4);
        ser_privilege_resultset=MyQueryResult.getMyExecuteQuery(sql5);

        privilege_resultset.next();
        acq_privilege_resultset.next();
        cat_privilege_resultset.next();
        cir_privilege_resultset.next();
        ser_privilege_resultset.next();
        //System.out.println(cir_privilege_resultset.getString(3));

        session.setAttribute("privilege", privilege_resultset);

        session.setAttribute("acq_privilege", acq_privilege_resultset);
        session.setAttribute("cat_privilege", cat_privilege_resultset);
        session.setAttribute("cir_privilege", cir_privilege_resultset);
        session.setAttribute("ser_privilege", ser_privilege_resultset);
             return mapping.findForward("assign");
         }
        }

        if(button.equals("View Privilege"))
        {

         boolean test=DuplicateEntry.checkDuplicateEntry("login","staff_id" , staff_id,library_id);
         if(!test)
         {
            request.setAttribute("msg1", "Record not found corresponding to staff Id:"+staff_id);
                request.setAttribute("staff_name", staff_name);
            return mapping.findForward("notfound");
         }
         else
         {
            sql="select * from staff_detail where staff_id='"+staff_id+"' and library_id='"+ library_id + "'" ;
            ResultSet rst=MyQueryResult.getMyExecuteQuery(sql);
            if(rst.next())
            request.setAttribute("updateresultset",rst);

            request.setAttribute("staff_name", staff_name);
             return mapping.findForward("update/view");
         }
        }

        return mapping.findForward("notfound");
    }
}
