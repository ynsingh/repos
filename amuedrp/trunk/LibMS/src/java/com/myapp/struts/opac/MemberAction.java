/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.*;

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
        String ID,password,lib_id,library_id;
        ResultSet rs=null,rs1=null,rs2=null,rs3=null,rs5=null;
        HttpSession session = request.getSession();
        MemberActionForm myForm =(MemberActionForm)form; 
        
        ID = myForm.getTXTMEMID();
        password=myForm.getTXTPASS();
        lib_id=myForm.getCMBLib();
        System.out.println(lib_id);
        session.setAttribute("id", ID);
        library_id=(String)session.getAttribute("library_id");

        if(library_id==null){
             session.setAttribute("library_id", lib_id);
        }
       
        String query4= "select * from member where memid='"+ID+"' and library_id='"+lib_id+"' and password='"+password+"' and status='Y'";
          rs5 = MyQueryResult.getMyExecuteQuery(query4);
       if(rs5.next())
       {


        String query= "select * from member_accounthistory where memid='"+ID+"' and library_id='"+lib_id+"'";
        String query1= "select sum(fine) from fine_details where memid='"+ID+"' and library_id='"+lib_id+"'";
       String query2= "select distinct fine_details.fine,fine_details.date,document.title,document.author,document.callno,document.publisher  from fine_details inner join document on fine_details.accessionno=document.accessionno and fine_details.library_id=document.library_id where fine_details.memid='"+ID+"' and fine_details.library_id='"+lib_id+"'";
        String query3= "select * from reservationlist where library_id='"+lib_id+"' and memid='"+ID+"'";
        rs = MyQueryResult.getMyExecuteQuery(query);
        rs1 = MyQueryResult.getMyExecuteQuery(query1);
        rs2 = MyQueryResult.getMyExecuteQuery(query2);
        rs3= MyQueryResult.getMyExecuteQuery(query3);
        if(rs!=null )
            {
                session.setAttribute("rs", rs);

                session.setAttribute("rs1", rs1);
                System.out.println("ok");
                  session.setAttribute("finedetails_resultset", rs2);
        session.setAttribute("reservationdetails_resultset", rs3);
        session.setAttribute("card_id",rs5.getString("card_id"));
        session.setAttribute("mem_id",rs5.getString("memid"));
        }
      
        if (rs.next())
        {
          String  status=rs.getString(8);
         
            if(!status.equals("Y")){
                String msg = "Sorry, your Membership is cancelled" +
                           " for somehow reason, please contact to your Library..";
                request.setAttribute("msg", msg);
                return mapping.findForward("notfound");
            }
            else
            {
              System.out.println("err12");
               return mapping.findForward("account");
            }
        }
  }
 else
        {
            String msg="Invalid Member";
            request.setAttribute("msg", msg);
            return mapping.findForward("notfound");
        }
      return mapping.findForward("notfound");
    }
}
