/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.*;
import java.util.*;
import javax.servlet.http.*;
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
    
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        NewMemberActionForm myForm = (NewMemberActionForm)form;

String library_id,category,fname,mname,lname,password,add1,add2,city,state,pin,country;
String email,fax,ph1,ph2,req_date,faculty,dept,rollno,course;
String date;
HttpSession session=request.getSession();
//library_id=(String)session.getAttribute("library_id");
Calendar cal = new GregorianCalendar();
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    date=day+"/"+(month+1)+"/"+year;

    
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
password=myForm.getTXTPASS();
library_id=myForm.getCMBLib();

rollno=myForm.getTXTROLL();
course=myForm.getTXTCOURSE();

if(faculty.equals("Select"))
    faculty="";

if(dept.equals("Select"))
    dept="";
if(course.equals("Select"))
    course="";





String queryString = "INSERT INTO member(library_id,category,fname,lname,mname,address1,address2,city,state,pin,country,email,fax,phone1,phone2,requestdate,faculty_id,dept_id,rollno,course,password) VALUES ('"+ library_id+"','"+ category +"','"+ fname +"','"+ mname +"','"+ lname +"'," +
       "'"+  add1 +"','"+ add2 +"','"+ city +"','"+ state +"','"+ pin +"','"+ country +"','"+ email +"','"+ fax +"','"+ ph1 +"','"+ ph2 +"','"+ req_date +"','"+ faculty +"','"+ dept +"','"+ rollno +"','"+ course +"','"+password+"')";
System.out.println("here");
int no = MyQueryResult.getMyExecuteUpdate(queryString);
if (no>0){
String msg="Your Membership Request sent successfully to Library";
request.setAttribute("msg", msg);

        return mapping.findForward(SUCCESS);
        }

return mapping.findForward("failure");
    }
}
