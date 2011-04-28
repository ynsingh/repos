/*
      Design by Iqubal Ahmad
      Modified on 2011-02-02
      This action class is meant for sending List object for displaying ViewAllGrid .

 */

package com.myapp.struts.circulation;


import java.sql.ResultSet;
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
 *    Design by Iqubal Ahmad
      Modified on 2011-02-02
      This action class is meant for sending List object for displaying ViewAllGrid .

 */
public class CirViewAllAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
     String library_id;

    private ResultSet rst;
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

         HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        Session hsession=HibernateUtil.getSessionFactory().getCurrentSession();
          Transaction tx=null;

          try{

                tx=hsession.beginTransaction();

                Query query = hsession.createQuery("FROM CirRequestfromOpac where libraryId = :library_id");
                //query.setString("registrationId",registration_id);
                query.setString("library_id", library_id);
                List cirrequestfromopac= query.list();
                if (cirrequestfromopac!=null)
                {
                 session.setAttribute("cirrequestfromopac", cirrequestfromopac);
                 
                 return mapping.findForward("viewallgrid");
                }
                else
                {
                 request.setAttribute("msg", "Record not exist");
                 //return mapping.findForward("success");
                }
             }catch(Exception e)
             {
                 tx.rollback();
                 throw e;
             }

        return mapping.findForward(SUCCESS);
    }
}

