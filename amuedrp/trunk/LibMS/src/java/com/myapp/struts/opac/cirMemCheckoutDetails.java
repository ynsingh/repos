/*
     Design by Iqubal Ahmad
     Modified on 2011-02-02
     This Action Class is meant for Sending List  Object for Displaying Grid when request from opac is Clicked.
     This Action Class also send faculty_resultset Object For Calling Ajax in Fac,Dept, & Course.
 */

package com.myapp.struts.opac;



import com.myapp.struts.circulation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
//import com.myapp.struts.hbm.CirRequestfromOpac;
import com.myapp.struts.hbm.HibernateUtil;
import com.myapp.struts.opacDAO.CheckoutDeocumentDetails;
import com.myapp.struts.opacDAO.CirRequestfromOpacDAO;
import com.sun.net.httpserver.Authenticator.Success;
import java.util.List;
import javax.servlet.http.HttpSession;
//import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Iqubal Ahmad
 */
public class cirMemCheckoutDetails extends org.apache.struts.action.Action {
    
   
    String library_id;
    String sublibrary_id;
CirRequestfromOpacDAO cirreqopac=new CirRequestfromOpacDAO();
   String login_role;
   
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {





         HttpSession session=request.getSession();
            library_id = (String)session.getAttribute("memlib");
           sublibrary_id = (String)session.getAttribute("memsublib");
           String memid = (String)session.getAttribute("mem_id");
      
 List<CheckoutDeocumentDetails>  requestList=null;


System.out.println("memid="+memid+" sublibraryId="+sublibrary_id+" libraryId="+library_id);
requestList = (List<CheckoutDeocumentDetails>)cirreqopac.getCheckOuts(library_id, sublibrary_id, memid);
System.out.println("size="+requestList.size());
        
session.setAttribute("membercheckoutDetail1", requestList);
       
        return mapping.findForward("success");
    }
}
