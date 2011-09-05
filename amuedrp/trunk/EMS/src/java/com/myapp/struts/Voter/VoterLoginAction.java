/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voter;

import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.ElectionDAO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author faraz
 */
public class VoterLoginAction extends org.apache.struts.action.Action {
    
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
        HttpSession session = request.getSession();
        
        String institute_Id = (String)session.getAttribute("institute_id");
       List<Election> election = ElectionDAO.Elections(institute_Id);
        Iterator it = election.iterator();
        System.out.println("Election List Size="+election.size());
        ArrayList electionList=new ArrayList();
        ArrayList currentelectionList=new ArrayList();
       ArrayList ClosedelectionList=new ArrayList();
       session.removeAttribute("electionList");
       session.removeAttribute("currentelectionList");
        session.removeAttribute("ClosedelectionList");
        while(it.hasNext())
        {

            Calendar cal1 = Calendar.getInstance();
           Date d = cal1.getTime();
            Election elec = (Election)it.next();
            if(elec.getStartDate().before(d) && elec.getEndDate().after(d))
            {
                elec.setStatus("started");
                    ElectionDAO.update(elec);
                    currentelectionList.add(elec);
                if(elec.getNstart().before(d) && elec.getNend().after(d))
                    electionList.add(elec);
                
            }
            else if(elec.getEndDate().before(d))
            {
                elec.setStatus("closed");
                 ElectionDAO.update(elec);
                  ClosedelectionList.add(elec);
            }

        }
        session.setAttribute("electionList", electionList);
        session.setAttribute("currentelectionList", currentelectionList);
        session.setAttribute("ClosedelectionList", ClosedelectionList);
        return mapping.findForward(SUCCESS);
    }
}
