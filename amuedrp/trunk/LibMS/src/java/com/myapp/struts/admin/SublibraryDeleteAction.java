/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.myapp.struts.hbm.HibernateUtil;
import com.myapp.struts.hbm.SubLibrary;
/**
 *
 * @author edrp02
 */
public class SublibraryDeleteAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String sublibrary_id;
    String button;
    String library_id;
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
            SublibraryDeleteActionForm slvaf=(SublibraryDeleteActionForm)form;
            sublibrary_id=slvaf.getSublibrary_id();
            System.out.println("############################################3"+sublibrary_id);
            button=slvaf.getButton();
            HttpSession session=request.getSession();
            library_id=(String)session.getAttribute("library_id");
            Session hsession=HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx=null;
            try
            {
              tx=hsession.beginTransaction();
              Query query = hsession.createQuery("FROM SubLibrary WHERE id.sublibraryId = :sublibraryId and id.libraryId = :libraryId");
              query.setString("sublibraryId",sublibrary_id);
              query.setString("libraryId", library_id);
              SubLibrary sublibrary= (SubLibrary)query.uniqueResult();


              if (sublibrary==null)
              {
                  request.setAttribute("msg", "Sublibrary not exist");
                  return mapping.findForward("success");
              }
              else
              {

               request.setAttribute("button", button);
               request.setAttribute("sublibrary", sublibrary);
               return  mapping.findForward("updatesub");
              }

            }
            catch(Exception e)
            {
             System.out.println("MEG4="+e.toString());
             tx.rollback();
             throw e;
            }

        
        
    }
}
