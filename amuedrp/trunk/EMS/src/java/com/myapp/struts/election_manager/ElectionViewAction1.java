/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.Candidate.CandidateRegistrationDAO;
import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.ElectionId;
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
 * @author akhtar
 */
public class ElectionViewAction1 extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";


    private Election ob=new Election();

private ElectionId elid=new ElectionId();
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
                 {
        HttpSession session = request.getSession();
        DepActionForm employeeform=(DepActionForm)form;
        // String button=employeeform.getButton();
         String button="Update";
         System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDD"+button);
         // String id=employeeform.getElectionId();
         // String Eid=employeeform.getInstituteId();
          String Eid = (String)session.getAttribute("institute_id");
          String id="id";
          id=request.getParameter(id);
 session.setAttribute("electionid",id);

 String publish=(String)request.getParameter("publish");

          System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR  "+id + " EID= " + Eid+publish);
       // Election l=ElectionDAO.searchElection(id);
          Election ere=new Election();
       Election l;
       l=(Election)ElectionDAO.Electionname(Eid,id);
          System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG"+l);
      /*  if(button.equals("Add"))
        {
            List rs=ElectionDAO.ElectionID(id,Eid);
            Iterator it =rs.iterator();
            if(it.hasNext())
            {

            request.setAttribute("msg1", "electionid: "+id+" already exists");
            return mapping.findForward("duplicate");
                       }
            else{
                employeeform.setElectionId(id);
            request.setAttribute("button", button);
            return mapping.findForward("add");
                }
        }*/


        if(publish!=null){
              Election ele=ElectionDAO.searchElection(id,Eid);
              System.out.println(ele.getStatus());
              if(ele!=null){

              if(ele.getStatus().equalsIgnoreCase("closed")){

              ele.setPublish("yes");
              ElectionDAO.update(ele);

                request.setAttribute("msg", "Result Published for Public Access");
                return mapping.findForward("fail");
              }
                 else{
                request.setAttribute("msg", "Sorry Election is under-process");
                return mapping.findForward("fail");

              }
              }
             else{
            request.setAttribute("msg", "electionid doesn't exists");
            return mapping.findForward("fail");
                }



        }





        if(button.equals("Update"))
        {

            if(l!=null){

                 ere= (Election)l;
             System.out.println(ere.getStatus());
                if(ere.getStatus().equalsIgnoreCase("started")||ere.getStatus().equalsIgnoreCase("closed") )
                {   request.setAttribute("msg2", "Sorry Cannot Update Election already started/closed");
                    return mapping.findForward("duplicate");
                }
             employeeform.setCreatedby(ere.getCreatedBy());
              employeeform.setEnddate(ere.getEndDate());
               employeeform.setStartdate(ere.getStartDate());
               employeeform.setNominationStart(ere.getNstart());
               employeeform.setNominationEnd(ere.getNend());
                employeeform.setScrutnyDate(ere.getScrutnyDate());
               employeeform.setScrutnyEndDate(ere.getScrutnyEndDate());
               employeeform.setWithdrawlDate(ere.getWithdrawlDate());
               employeeform.setWithdrawlEndDate(ere.getWithdrawlEndDate());
                employeeform.setElectionname(ere.getElectionName());
                 employeeform.setStatus(ere.getStatus());
                 employeeform.setDescription(ere.getDescription());
            employeeform.setElectionId(ere.getId().getElectionId());
            employeeform.setInstituteId(ere.getId().getInstituteId());

employeeform.setResultDeclarationDate(ere.getResultDeclarationDate());
           // employeeform.setCritaria(ere.getElectionrule().getCriteria());
          //  employeeform.setAttendence(ere.getEligibility().getAttendence());
          //  employeeform.setBacklog(ere.getEligibility().getBacklog());
          //  employeeform.setCriminal(ere.getEligibility().getCriminallog());
         //   employeeform.setDeaprtment(ere.getEligibility().getDepartment());
         //   employeeform.setMarks(ere.getEligibility().getMarks());
        //    employeeform.setIndiscipline(ere.getEligibility().getIndiscipline());
               Election election   =(Election)CandidateRegistrationDAO.getElectionName(Eid,id);
    session.setAttribute("electionname",election.getElectionName());
            request.setAttribute("button", button);
            return mapping.findForward("add");
                       }
            else{
            request.setAttribute("msg1", "electionid doesn't exists");
            return mapping.findForward("duplicate");
                }
        }

        /*if(button.equals("View"))
            System.out.println("View Page");
        {


            if(!l.isEmpty()){

   ere= (ElectionRuleEligiblity1)l.get(0);


             employeeform.setCreatedby(ere.getElection().getCreatedBy());
              employeeform.setEnddate(ere.getElection().getEndDate());
               employeeform.setStartdate(ere.getElection().getStartDate());
                employeeform.setElectionname(ere.getElection().getElectionName());
                 employeeform.setStatus(ere.getElection().getStatus());
                 employeeform.setDescription(ere.getElection().getDescription());
            employeeform.setElectionId(ere.getElection().getId().getElectionId());
            employeeform.setInstituteId(ere.getElection().getId().getInstituteId());


            employeeform.setCritaria(ere.getElectionrule().getCriteria());
            employeeform.setAttendence(ere.getEligibility().getAttendence());
            employeeform.setBacklog(ere.getEligibility().getBacklog());
            employeeform.setCriminal(ere.getEligibility().getCriminallog());
            employeeform.setDeaprtment(ere.getEligibility().getDepartment());
            employeeform.setMarks(ere.getEligibility().getMarks());
            employeeform.setIndiscipline(ere.getEligibility().getIndiscipline());

            request.setAttribute("button", button);
            return mapping.findForward("add");
                       }

            else{
            //request.setAttribute("msg1", "electionid doesn't exists");
            return mapping.findForward("duplicate");
                }
        }*/
           /*if(button.equals("Delete"))
        {
            if(l!=null){
            employeeform.setCreatedby(l.getDescription());
             employeeform.setCreatedby(l.getCreatedBy());
              employeeform.setEnddate(l.getEndDate());
               employeeform.setStartdate(l.getStartDate());
                employeeform.setElectionname(l.getElectionName());
                 employeeform.setStatus(l.getStatus());
                 employeeform.setDescription(l.getDescription());

            employeeform.setElectionId(l.getId().getElectionId());
            employeeform.setInstituteId(l.getId().getInstituteId());
            request.setAttribute("button", button);
            return mapping.findForward("add");
                       }
            else{
            request.setAttribute("msg1", "electionid doesn't exists");
            return mapping.findForward("duplicate");
                }
        }*/





           return mapping.findForward("failure");
    }
}
