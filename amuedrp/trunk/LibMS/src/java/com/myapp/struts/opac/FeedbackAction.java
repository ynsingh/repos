/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.*;
import com.myapp.struts.MyExecuteQueryAction;
import java.sql.ResultSet;
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
public class FeedbackAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
        String name, email, comments,cardno, date ,library_id;
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

        FeedbackActionForm myForm = (FeedbackActionForm)form;

HttpSession session=request.getSession();

    Calendar cal = new GregorianCalendar();
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    date=day+ "/"+ (month+1) + "/"+year;

    cardno=myForm.getCardno();
    name=myForm.getName();
    email=myForm.getEmail();
    comments=myForm.getComments();
library_id=myForm.getCMBLib();
System.out.println(library_id);

    String queryString = "INSERT INTO feedback" +
        "(cardno,name,email,comments,date,library_id) VALUES ('"+ cardno +"','"+ name +"','"+ email +"','"+ comments +"','"+ date +"','"+library_id+"')";

    int no = MyQueryResult.getMyExecuteUpdate(queryString);
        if(no>0)
        {
            request.setAttribute("msg", "Your Feedback is sent Successfully to Library..");
        }
            return mapping.findForward(SUCCESS);
        
        
    }
}
