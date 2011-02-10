/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import com.myapp.struts.MyQueryResult;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author System Administrator
 */
public class ShowCheckedPrivilegeAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private String staff_id;
    private String library_id;
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
        String sql1,sql2,sql3,sql4,sql5;
        ResultSet privilege_resultset,acq_privilege_resultset,cat_privilege_resultset,cir_privilege_resultset,ser_privilege_resultset;
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        staff_id = (String)request.getAttribute("staff_id");
        if (staff_id==null)staff_id = (String)request.getParameter("staff_id");
        System.out.println("staff_id="+staff_id);

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
        request.setAttribute("staff_id", staff_id);
        return mapping.findForward(SUCCESS);
    }
}
