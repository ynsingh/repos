/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;
import com.myapp.struts.hbm.*;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author iqubal
 */
public class UpdateElectionRule extends org.apache.struts.action.Action {
    
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

        
        String positions="";
        PositionDAO emailDAO = new PositionDAO();
        HttpSession session = request.getSession();
        String electionid = (String)request.getParameter("setElectionId");
        String ruleid = (String)request.getParameter("ruleId");
        String setRule = (String)request.getParameter("setRule");
        String action = (String)request.getParameter("action");
        System.out.println((String)request.getParameter("position"));
        int positionid = Integer.parseInt((String)request.getParameter("position"));
        String instituteId = (String)session.getAttribute("institute_id");
        System.out.println("election_iddddddddddddd!!!="+electionid+"rule_id="+ruleid+"setrule"+setRule+"insitute_id="+instituteId+"action="+action+"positionid="+positionid);
       // if(electionid==null)
           // electionid = (String)session.getAttribute("election_id");
        Electionrule position = (Electionrule)emailDAO.getRulesbyId(instituteId,electionid,ruleid,positionid);
        //System.out.println("position=========="+position);
      position.setCriteria(setRule);
        if(position!=null)
        {
              if(action!=null)
              {   emailDAO.deleteRule(position);
                  System.out.println("action==========d="+action);
                 positions+="<email_ids><message>Rule Deleted Successfully</message></email_ids>";
                 response.setContentType("application/xml");
                 response.getWriter().write(positions);
                 return null;
              }
else
{
            emailDAO.updateRule(position);
            System.out.println("action==========u="+action);
             positions+="<email_ids><message>Rule Updated Successfully</message></email_ids>";
            response.setContentType("application/xml");
            response.getWriter().write(positions);
            return null;
}

        
            }else{

          if(action!=null)
{  positions+="<email_ids><message>Sorry Rule Cannot deleted becouse election is started </message></email_ids>";
            response.setContentType("application/xml");
            response.getWriter().write(positions);
            return null;
          }else{

          positions+="<email_ids><message>Sorry Rule Cannot updated becouse election is started </message></email_ids>";
            response.setContentType("application/xml");
            response.getWriter().write(positions);
            return null;
          }

            }
           
        
        
    }
}
