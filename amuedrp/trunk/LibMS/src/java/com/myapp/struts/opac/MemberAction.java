/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import com.myapp.struts.opac.MyQueryResult;
import com.myapp.struts.opac.MyResultSetAction;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Faraz
 */
public class MemberAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
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
        String ID;
        ResultSet rs=null,rs1=null,rs2=null,rs3=null;
        HttpSession session = request.getSession();
        MemberActionForm myForm =(MemberActionForm)form; 
        
        ID = myForm.getTXTMEMID().toUpperCase().trim();
        session.setAttribute("id", ID);
        System.out.println("ID"+ID);
        String query= "select * from member_accounthistory where memid='"+ID+"'";
        String query1= "select sum(fine) from fine_details where memid='"+ID+"'";
        String query2= "select distinct fine_details.fine,fine_details.date,document.title,document.author,document.callno,document.publisher  from fine_details inner join document on fine_details.accessionno=document.accessionno where fine_details.memid='"+ID+"'";
        String query3= "select * from reservationlist where card_id='"+ID+"'";
        rs = MyQueryResult.getMyExecuteQuery(query);
        rs1 = MyQueryResult.getMyExecuteQuery(query1);
        rs2 = MyQueryResult.getMyExecuteQuery(query2);
        rs3= MyQueryResult.getMyExecuteQuery(query3);
        if(rs!=null && rs1!=null){
        request.setAttribute("rs", rs);
        request.setAttribute("rs1", rs1);}
        session.setAttribute("finedetails_resultset", rs2);
        session.setAttribute("reservationdetails_resultset", rs3);
        if (rs.next())
        {
          String  status=rs.getString(7);
            if(!status.equals("Y")){
                String msg = "Sorry, your Membership is cancelled" +
                           " for somehow reason, please contact to your Library..";
                request.setAttribute("msg", msg);
                return mapping.findForward("member");
            }
        }
 else
        {
            String msg="Invalid Member,try again..";
            request.setAttribute("msg", msg);
            return mapping.findForward("member");
        }
        return mapping.findForward(SUCCESS);
    }
}
