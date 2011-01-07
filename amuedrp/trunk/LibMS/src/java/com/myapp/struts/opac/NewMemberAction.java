/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Faraz
 */
public class NewMemberAction extends org.apache.struts.action.Action {
    
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

        NewMemberActionForm myForm = (NewMemberActionForm)form;

String lib_name,lib_id,category,fname,mname,lname,subject="",add1,add2,city,state,pin,country;
String email,fax,ph1,ph2,req_date,faculty,dept,memid,rollno,course;
String date;

Calendar cal = new GregorianCalendar();
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    date=day+"/"+(month+1)+"/"+year;

    lib_name=myForm.getTXTLIBNAME();
lib_id=myForm.getTXTLIBID();
category=myForm.getCMBCAT();
fname=myForm.getTXTFNAME();
mname=myForm.getTXTMNAME();
lname=myForm.getTXTLNAME();
add1=myForm.getTXTADD1();
add2=myForm.getTXTADD2();
city=myForm.getTXTCITY();
state=myForm.getTXTSTATE();
pin=myForm.getTXTPIN();
country=myForm.getTXTCOUNTRY();
email=myForm.getTXTEMAIL();
fax=myForm.getTXTFAX();
ph1=myForm.getTXTPH1();
ph2=myForm.getTXTPH2();
req_date=date;
faculty=myForm.getTXTFACULTY();
dept=myForm.getTXTDEPT();
memid=request.getParameter("TXTID");
rollno=myForm.getTXTROLL();
course=myForm.getTXTCOURSE();


String queryString = "INSERT INTO member VALUES ('"+ lib_name +"','"+ lib_id +"','"+ category +"','"+ fname +"','"+ mname +"','"+ lname +"'," +
       "'"+ subject +"','"+ add1 +"','"+ add2 +"','"+ city +"','"+ state +"','"+ pin +"','"+ country +"','"+ email +"','"+ fax +"','"+ ph1 +"','"+ ph2 +"','"+ req_date +"','"+ faculty +"','"+ dept +"','"+ memid +"','"+ rollno +"','"+ course +"')";

int no = MyQueryResult.getMyExecuteUpdate(queryString);
if (no>0){
String msg="Your Membership Request is sent to Library..";
request.setAttribute("msg", msg);
        return mapping.findForward(SUCCESS);
        }
return mapping.findForward("failure");
    }
}
