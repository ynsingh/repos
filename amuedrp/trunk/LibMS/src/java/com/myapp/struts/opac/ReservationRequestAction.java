/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.*;
import java.util.*;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import org.apache.catalina.Session;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Faraz
 */
public class ReservationRequestAction extends org.apache.struts.action.Action {
    
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
            HttpSession session = request.getSession();
            ResultSet rs;
            ReservationRequetActionForm myForm = (ReservationRequetActionForm)form;
            
            String library_id,memid,accessionno,card_id,title,author,publ,edition;
            String req_date,callno,isbn,vol,remark,category,issn,lang,pub_year;

            Calendar cal = new GregorianCalendar();
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            req_date= year+"-"+(month+1)+"-"+day;

            library_id=(String)session.getAttribute("library_id");
            memid=(String)session.getAttribute("mem_id");
            accessionno=myForm.getAccessionno();
            card_id=(String)session.getAttribute("card_id");
            title=myForm.getTXTTITLE();
            category=myForm.getCMBCAT();
            author=myForm.getTXTAUTHOR();
            isbn=myForm.getTXTISBN();
            callno=myForm.getTXTCALLNO();
            edition=myForm.getTXTEDITION();
            vol=myForm.getTXTVOL();
            publ=myForm.getTXTPUBL();
            remark=myForm.getTXTREMARKS();
            issn=myForm.getIssn();
            lang=myForm.getLang();
            pub_year=myForm.getPub_year();




//System.out.println(library_id +"','"+ memid+"','"+accessionno+"','"+card_id +"','"+ title +"','"+ category +"','"+ author +"','"+ isbn +"','"+ callno +"',"+ edition +"','"+ vol +"','"+ publ +"','"+ remark +"','"+ req_date +"','"+issn+"','"+lang);


String queryString = "INSERT INTO reservationlist(library_id,memid,accessionno,card_id,title,category,author,isbn,callno,edition,volume,publication,remark,request_date,issn,language,pub_year) VALUES ('"+
            library_id +"','"+ memid+"','"+accessionno+"','"+card_id +"','"+ title +"','"+ category +"','"+ author +"','"+ isbn +"','"+ callno +"','"+ edition  + "','"+ vol +"','"+ publ +"','"+ remark +"','"+ req_date+"','"+issn+"','"+lang+"','"+pub_year+"')";

int no = MyQueryResult.getMyExecuteUpdate(queryString);
if(no>0){

 queryString = "select count(*) from reservationlist where library_id='"+library_id+"' and memid='"+memid+"'";



rs = MyQueryResult.getMyExecuteQuery(queryString);
if(rs.next())
{
   queryString="update member_accounthistory set reservation_made='"+rs.getInt(1)+"' where library_id='"+library_id+"' and memid='"+memid+"'" ;
   no=MyQueryResult.getMyExecuteUpdate(queryString);
   if(no>0)
   {
       
        String msg="Your reservation request is sent to Library..";
        request.setAttribute("msg", msg);
        return mapping.findForward(SUCCESS);

    }
}
else
{
       String msg="Error in resending request";
       request.setAttribute("msg", msg);
       return mapping.findForward("error");
}
 
    }
else
{
       String msg="Error in resending request";
       request.setAttribute("msg", msg);
       return mapping.findForward("error");
}
return mapping.findForward("error");
}
}
