/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import com.myapp.struts.MyQueryResult;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class PrivilegeAction extends org.apache.struts.action.Action {
    String privilege_list;
    String staff_id,library_id,button;
    String sql1,sql2,sql3,sql4,sql5;
    ResultSet privilege_backup_resultset[];
    String staff_name;
    ResultSet privilege_resultset,acq_privilege_resultset,cat_privilege_resultset,cir_privilege_resultset,ser_privilege_resultset;
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";


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
        HttpSession session=request.getSession();
        PrivilegeActionForm privilege=(PrivilegeActionForm)form;
        library_id=(String)session.getAttribute("library_id");
        staff_id=privilege.getStaff_id().trim();
        staff_name=privilege.getStaff_name();
        button=privilege.getButton();
        System.out.println("....button="+button);


        if(button.equals("Assign Privilege")||button.equals("Change Privilege"))
        {
        privilege_list=privilege.getPrivilege_list();
        System.out.println(privilege_list);
        if (privilege_list!="")
        {


        Privilege priv=new Privilege();

        privilege_backup_resultset=priv.backupPrivilege(staff_id,library_id);
        session.setAttribute("privilege_backup",privilege_backup_resultset);

        





        priv.AssignPrivilege(privilege_list,staff_id, library_id);
        privilege_backup_resultset[0].next();
        System.out.println("****************************"+privilege_backup_resultset[0].getString(4)+staff_id+library_id);
        privilege_backup_resultset[0].beforeFirst();
        request.setAttribute("staff_id", staff_id);
        request.setAttribute("staff_name", staff_name);
        request.setAttribute("privilege_list", privilege_list);


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
      
        request.setAttribute("privilege_resultset", privilege_resultset);

        request.setAttribute("acq_privilege_resultset", acq_privilege_resultset);
        request.setAttribute("cat_privilege_resultset", cat_privilege_resultset);
        request.setAttribute("cir_privilege_resultset", cir_privilege_resultset);
        request.setAttribute("ser_privilege_resultset", ser_privilege_resultset);

         //check the privilege of admin role user and degrade it to staff role if administrator privielege
         //not selected
         ResultSet temp=MyQueryResult.getMyExecuteQuery("select * from privilege where staff_id='"+staff_id+"' and library_id='"+library_id+"'");
         if(temp.next())
         {
         if(temp.getString("administrator").equals("true")){
             int i=MyQueryResult.getMyExecuteUpdate("update login set role='staff' where staff_id='"+staff_id+"' and library_id='"+library_id+"'");
             if(i==0){
                request.setAttribute("staff_id", staff_id);
                request.setAttribute("staff_name", staff_name);
                request.setAttribute("msg", "Exception occured");
                System.out.println(temp.getString("administrator"));
                return mapping.findForward("failure");
             }

         }

         }




        return mapping.findForward("assign_privilege");

        }
        request.setAttribute("staff_id", staff_id);
        request.setAttribute("staff_name", staff_name);
        request.setAttribute("msg", "No Specific Privilege assigned as yet");
        return mapping.findForward("failure");
        }

        else      //if(button.equals("View Privilge"))
        {
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



    /*    session.setAttribute("privilege", privilege_resultset);

        session.setAttribute("acq_privilege", acq_privilege_resultset);
        session.setAttribute("cat_privilege", cat_privilege_resultset);
        session.setAttribute("cir_privilege", cir_privilege_resultset);
        session.setAttribute("ser_privilege", ser_privilege_resultset);
*/
        request.setAttribute("privilege_resultset", privilege_resultset);

        request.setAttribute("acq_privilege_resultset", acq_privilege_resultset);
        request.setAttribute("cat_privilege_resultset", cat_privilege_resultset);
        request.setAttribute("cir_privilege_resultset", cir_privilege_resultset);
        request.setAttribute("ser_privilege_resultset", ser_privilege_resultset);
        request.setAttribute("staff_id", staff_id);
        request.setAttribute("staff_name", staff_name);
            return mapping.findForward("view_privilege");
        }

    }
}
