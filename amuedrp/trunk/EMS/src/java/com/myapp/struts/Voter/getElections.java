/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voter;

import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.ElectionDAO;
import com.myapp.struts.hbm.Electionrule;
import com.myapp.struts.hbm.Position1;
import com.myapp.struts.hbm.PositionDAO;
import com.myapp.struts.hbm.PositionWithCandidature;
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

/**
 *
 * @author Edrp-04
 */
public class getElections extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        StringBuffer emp_ids = new StringBuffer();
       
       
HttpSession session=request.getSession();
        //String searchText = request.getParameter("getSubLibrary_Id");
        String instituteId=(String)session.getAttribute("institute_id");
        List<Election> election =ElectionDAO.Elections(instituteId);

        Iterator it = election.iterator();
        int tcount=0;
        emp_ids.append("<elections>");
        Calendar c1 = Calendar.getInstance();
        Date d1 = c1.getTime();
        while (it.hasNext())
        {

//construct the xml string.
            //SubLibrary sublibpojo = (SubLibrary)sublibrary.get(tcount);
Election election1 =(Election)election.get(tcount);
               if(election1.getNstart().before(d1) && election1.getNend().after(d1))
               {
                emp_ids.append("<election>");
                emp_ids.append("<election_id>"+election1.getId().getElectionId()+"</election_id>");
                emp_ids.append("<electionname>"+election1.getElectionName()+"</electionname>");
                emp_ids.append("<description>"+election1.getDescription()+"</description>");
                emp_ids.append("<Nstart>"+election1.getNstart()+"</Nstart>");
                emp_ids.append("<Nend>"+election1.getNend()+"</Nend>");
                emp_ids.append("<Sstart>"+election1.getScrutnyDate()+"</Sstart>");
                emp_ids.append("<Send>"+election1.getScrutnyEndDate()+"</Send>");
                emp_ids.append("<Wstart>"+election1.getWithdrawlDate()+"</Wstart>");
                emp_ids.append("<Wend>"+election1.getWithdrawlEndDate()+"</Wend>");
                emp_ids.append("<Estart>"+election1.getStartDate()+"</Estart>");
                emp_ids.append("<Eend>"+election1.getEndDate()+"</Eend>");
                emp_ids.append("<Posts>");
                PositionDAO posdao = new PositionDAO();
                List<PositionWithCandidature> lstPos = posdao.getPositionWithCandidature(election1.getId().getElectionId(), election1.getId().getInstituteId());
                Iterator itlstPos=null;
                if(lstPos!=null) itlstPos = lstPos.iterator();
                while(itlstPos!=null && itlstPos.hasNext())
                {
                    PositionWithCandidature p = (PositionWithCandidature)itlstPos.next();
                    emp_ids.append("<post>");
                    emp_ids.append("<positionId>"+p.getPosition_id()+"</positionId>");
                    emp_ids.append("<position>"+p.getPosition_name()+"</position>");
                    emp_ids.append("<candidature>"+p.getCandidature()+"</candidature>");
                    emp_ids.append("</post>");
                }
                emp_ids.append("</Posts>");

                   emp_ids.append("<Rules>");
                PositionDAO posdao1 = new PositionDAO();
                List<Electionrule> lstPos1 = posdao1.getRules(election1.getId().getInstituteId(),election1.getId().getElectionId());
                Iterator itlstPos1=null;
                if(lstPos1!=null) itlstPos1 = lstPos1.iterator();
               while(itlstPos1!=null && itlstPos1.hasNext())
               {
                    Electionrule p = (Electionrule)itlstPos1.next();
                  emp_ids.append("<rule>");
                    emp_ids.append("<ruleId>"+p.getId().getRuleId()+"</ruleId>");
                    emp_ids.append("<rulevalue>"+p.getCriteria()+"</rulevalue>");

                   emp_ids.append("</rule>");
               }
                emp_ids.append("</Rules>");

                emp_ids.append("</election>");
               }
                tcount++;
                it.next();
        }
        emp_ids.append("</elections>");


        if(emp_ids!=null)
        {response.setContentType("application/xml");
        response.getWriter().write(emp_ids.toString());
        }


         return null;
    }
}
