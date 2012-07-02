/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;
import java.util.List;
import com.myapp.struts.CirDAO.CirculationDAO;
import com.myapp.struts.hbm.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.myapp.struts.utility.Email;

/**
 *
 * @author edrp-03
 */
public class ReminderMailAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private final ExecutorService executor=Executors.newFixedThreadPool(1);
  Email obj;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        HttpSession session=request.getSession();
        CirculationDAO cirdao=new CirculationDAO();
        String library_id=(String)session.getAttribute("library_id");
        String sublibrary_id=(String)session.getAttribute("sublibrary_id");
        String memid=request.getParameter("memid");
        CirMemberDetail CM=cirdao.getMemberDetail(library_id,memid);
        String msg="Reminder for Book Return from Librarian" ;
        obj=new Email(CM.getEmail(),"",msg,"\n\nDear "+CM.getFname()+" "+CM.getLname()+",\n Your Check out date for the book is over.\n You are requested to return book as soon as possible\nThanks\nAdmin\nLibMS");
        obj.send();
        request.setAttribute("msg", "Mail Sent Successfully");
        return mapping.findForward(SUCCESS);
    }
}
