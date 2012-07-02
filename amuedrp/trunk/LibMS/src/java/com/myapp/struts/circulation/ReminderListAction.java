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
import com.myapp.struts.CirDAO.CirculationDAO;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author edrp-03
 */
public class ReminderListAction extends org.apache.struts.action.Action {
    
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
HttpSession session=request.getSession();
CirculationDAO cirdao=new CirculationDAO();
Calendar cal=Calendar.getInstance();
SimpleDateFormat formatter=
  new SimpleDateFormat("yyyy-MM-dd");
String dateNow = formatter.format(cal.getTime());
System.out.println("date==="+dateNow);
String library_id=(String)session.getAttribute("library_id");
String sublibrary_id=(String)session.getAttribute("sublibrary_id");


List reminderlist=cirdao.ReminderList(library_id,sublibrary_id,dateNow);
System.out.println("ReminderList==="+reminderlist.size());
session.setAttribute("reminderlist", reminderlist);





        return mapping.findForward(SUCCESS);
    }
}
