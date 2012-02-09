/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.institute_admin;

import com.myapp.struts.hbm.AdminRegistrationDAO;
import com.myapp.struts.hbm.Election;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Dushyant
 */
public class SearchElectionAction extends org.apache.struts.action.Action {
    
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
         AdminRegistrationDAO emailDAO = new AdminRegistrationDAO();
        String searchText = request.getParameter("user_id");
        
        String insti_admin=(String)session.getAttribute("institute_id");
        System.out.println("dsfsd");

        String emails = (String)getElection(searchText, insti_admin);
        if (!emails.equals(""))
        {
        response.setContentType("application/xml");
        response.getWriter().write(emails);

        }

             return null;




    }

public String getElection (String searchText,String insti_id) {
List<Election> rs = null;

StringBuffer email_ids = new StringBuffer();
//String sql = "select *  from login where user_id='"+searchText+"' and password='"+searchText+"'";
try {
AdminRegistrationDAO login = new AdminRegistrationDAO();
rs = login.searchElectionDetails(insti_id,searchText);


//construct the xml string.


if(!rs.isEmpty())
{
Iterator it = rs.iterator();
int tcount=0;
email_ids.append("<dept_ids>");
while (it.hasNext())
{

//construct the xml string.
Election subemppojo = rs.get(tcount);


   email_ids.append("<dept_id>"+subemppojo.getId().getElectionId()+"</dept_id>");
   email_ids.append("<dept_name>"+subemppojo.getElectionName()+"</dept_name>");
tcount++;
it.next();
}
email_ids.append("</dept_ids>");
}
else
{
email_ids.append("<dept_ids>");
email_ids.append("<dept_id></dept_id>");
email_ids.append("</dept_ids>");
}

}
catch(Exception se) {
    email_ids.append("<dept_ids>");
email_ids.append("<dept_id>Database not Connected! Please contact Web Admin</dept_id>");
email_ids.append("</dept_id>");
return email_ids.toString();
}
finally {
try {

}
catch(Exception e) {
return email_ids.toString();
}
}
return email_ids.toString();
}

   
}
