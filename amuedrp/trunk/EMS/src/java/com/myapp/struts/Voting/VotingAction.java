/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voting;

import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.ElectionDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.Voting.Result;
import com.myapp.struts.hbm.VotingDAO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Edrp-04
 */
public class VotingAction extends org.apache.struts.action.Action {

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
        String election =(String) request.getParameter("election");
         String institute_id=(String)session.getAttribute("institute_id");
        String positions="";
        session.removeAttribute("election_id");
        session.removeAttribute("electionName");
        if(election!=null)
        {

            Election elec = ElectionDAO.searchElection(election,institute_id);
            VotingDAO votingdao= new VotingDAO();

            session.setAttribute("election_id", election);
            session.setAttribute("electionName", elec.getElectionName());
            System.out.println("electionid="+election+"institute_id="+institute_id);
            List result=votingdao.GetResult(institute_id, election);

            System.out.println(result);

session.setAttribute("resultset", result);

            if(result!=null)
            {
                positions+="<positions>";
                positions+="<election>"+elec.getElectionName()+"</election>";
                System.out.println("Position count="+result.size());
            Iterator itpos = result.listIterator();
            LinkedHashMap<String,ArrayList> m = new LinkedHashMap<String, ArrayList>();
            ArrayList<String> lsPos = new ArrayList<String>();
            while(itpos.hasNext())
            {
                Result rs = (Result)itpos.next();
                if(m.containsKey(rs.getPosition_name()))
                {
//                    m.get(rs.getPosition_name()).add(rs.getNumber_of_choice());
                    m.get(rs.getPosition_name()).add(rs.getCandidate_name());
                    m.get(rs.getPosition_name()).add(rs.getVotes());
                }
                else
                {
                    lsPos.add(rs.getPosition_name().toString());
                    lsPos.add(rs.getNumber_of_choice().toString());
                    m.put(rs.getPosition_name(),new ArrayList<String>());
//                    m.get(rs.getPosition_name()).add(rs.getNumber_of_choice());
                    m.get(rs.getPosition_name()).add(rs.getCandidate_name());
                    m.get(rs.getPosition_name()).add(rs.getVotes());
                }
            }




            itpos = result.iterator();

                for (int i=0;i<m.size();i++)
                {
                    List ls = (List)m.get((String)lsPos.get(2*i));
                    positions+="<position>";
        //            positions+="<positionId>"+lsPos.get(i)+"</positionId>";
                    positions+="<positionname>"+lsPos.get(2*i)+"</positionname>";
                    positions+="<noofchoice>"+lsPos.get(2*i+1)+"</noofchoice>";

                    Iterator it = ls.iterator();
                    while(it.hasNext())
                    {
                        positions+="<candidate>";
                        positions+="<candidatename>"+it.next().toString()+"</candidatename>";
                        positions+="<votes>"+it.next().toString()+"</votes>";
                        positions+="</candidate>";
                    }
                    positions+="</position>";
                }
                positions+="</positions>";

            }


        }
         System.out.println("XML ="+positions);
                response.setContentType("application/xml");
                response.getWriter().write(positions);
                return null;
       // return mapping.findForward(SUCCESS);

    }
}
