/*
     Design by Iqubal Ahmad
     Modified on 2011-02-02
     This Action Class is meant for Sending List  Object for Displaying Grid when request from opac is Clicked.
     This Action Class also send faculty_resultset Object For Calling Ajax in Fac,Dept, & Course.
 */

package com.myapp.struts.circulation;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
//import com.myapp.struts.hbm.CirRequestfromOpac;
import com.myapp.struts.hbm.HibernateUtil;
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
public class CirRequestOpacAction extends org.apache.struts.action.Action {
    
   
    String library_id;
    String sublibrary_id;

   String login_role;
   
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {





         HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        
        sublibrary_id = (String)request.getParameter("sublibrary_id");
       if(sublibrary_id==null)
          sublibrary_id=(String)session.getAttribute("sublibrary_id");
      

        

        
        
        Session hsession=HibernateUtil.getSessionFactory().getCurrentSession();
          Transaction tx=null;

          try{

                tx=hsession.beginTransaction();



               
                Query query = hsession.createQuery("FROM CirRequestfromOpac where libraryId = :library_id and sublibraryId=:sublibrary_id and status=:status");
               
                query.setString("library_id", library_id);
                query.setString("sublibrary_id", sublibrary_id);
                query.setString("status", "UnApproved");
                List cirrequestfromopac= query.list();
                if (cirrequestfromopac!=null)
                {
                 session.setAttribute("cirrequestfromopac", cirrequestfromopac);
                
                 return mapping.findForward("viewgrid");
                }
                else
                {
                 request.setAttribute("msg", "Record not exist");
               
                }
             }catch(Exception e)
             {
                 tx.rollback();
                 throw e;
             }
       
        return null;
    }
}
