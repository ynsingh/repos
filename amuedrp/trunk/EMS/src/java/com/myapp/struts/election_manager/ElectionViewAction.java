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
public class ElectionViewAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";


    private Election ob=new Election();

private ElectionId elid=new ElectionId();
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
                 {
         String button="Update";
        HttpSession session = request.getSession();
        DepActionForm employeeform=(DepActionForm)form;
          Election ere=new Election();
        Election l;
        // System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDD"+button);
          String Eid=employeeform.getInstituteId();
          if (Eid==null) Eid = (String)session.getAttribute("institute_id");
        String req=(String)request.getParameter("id");
         String req1=(String)request.getParameter("st");
        if(req!=null && req1==null){
        session.setAttribute("ele", req);
        return mapping.findForward("preview");


        }
       
        if(req!=null && req1!=null){

 button="View";
    List<Electionrule> obj= (List<Electionrule>)CandidateRegistrationDAO.GetRuleDetails1(Eid,req);
    session.setAttribute("elerule", obj);
       l=ElectionDAO.searchElection(req,Eid);
          System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG"+l);
             if(l!=null){

   ere= (Election)l;

             employeeform.setCreatedby(ere.getCreatedBy());
              employeeform.setEnddate(ere.getEndDate());
               employeeform.setStartdate(ere.getStartDate());
               employeeform.setNominationStart(ere.getNstart());
               employeeform.setNominationEnd(ere.getNend());
               employeeform.setScrutnyDate(ere.getScrutnyDate());
               employeeform.setScrutnyEndDate(ere.getScrutnyEndDate());
               employeeform.setWithdrawlDate(ere.getWithdrawlDate());
               employeeform.setWithdrawlEndDate(ere.getWithdrawlEndDate());
               employeeform.setResultDeclarationDate(ere.getResultDeclarationDate());
                employeeform.setElectionname(ere.getElectionName());
                 employeeform.setStatus(ere.getStatus());
                 employeeform.setDescription(ere.getDescription());
            employeeform.setElectionId(ere.getId().getElectionId());
            employeeform.setInstituteId(ere.getId().getInstituteId());


           // employeeform.setCritaria(ere.getElectionrule().getCriteria());
           // employeeform.setAttendence(ere.getEligibility().getAttendence());
           // employeeform.setBacklog(ere.getEligibility().getBacklog());
           // employeeform.setCriminal(ere.getEligibility().getCriminallog());
           // employeeform.setDeaprtment(ere.getEligibility().getDepartment());
          //  employeeform.setMarks(ere.getEligibility().getMarks());
           // employeeform.setIndiscipline(ere.getEligibility().getIndiscipline());

            request.setAttribute("button", button);

             }







          request.setAttribute("back", "back");
          return mapping.findForward("add");

        }
        
          String id=employeeform.getElectionId();
        
          //System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR  "+id + " EID= " + Eid);
       // Election l=ElectionDAO.searchElection(id);
        
       //List<ElectionRuleEligiblity1> l;
       l=ElectionDAO.searchElection(id,Eid);
          System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG"+l);
        if(button.equals("Add"))
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
        }

        if(button.equals("Update"))
        {
           
            if(l!=null){
                 ere= (Election)l;
             employeeform.setCreatedby(ere.getCreatedBy());
              employeeform.setEnddate(ere.getEndDate());
               employeeform.setStartdate(ere.getStartDate());
               employeeform.setNominationStart(ere.getNstart());
               employeeform.setNominationEnd(ere.getNend());
               employeeform.setScrutnyDate(ere.getScrutnyDate());
               employeeform.setScrutnyEndDate(ere.getScrutnyEndDate());
               employeeform.setWithdrawlDate(ere.getWithdrawlDate());
               employeeform.setWithdrawlEndDate(ere.getWithdrawlEndDate());
               employeeform.setResultDeclarationDate(ere.getResultDeclarationDate());
                employeeform.setElectionname(ere.getElectionName());
                 employeeform.setStatus(ere.getStatus());
                 employeeform.setDescription(ere.getDescription());
            employeeform.setElectionId(ere.getId().getElectionId());
            employeeform.setInstituteId(ere.getId().getInstituteId());


           // employeeform.setCritaria(ere.getElectionrule().getCriteria());
           // employeeform.setAttendence(ere.getEligibility().getAttendence());
            //employeeform.setBacklog(ere.getEligibility().getBacklog());
           // employeeform.setCriminal(ere.getEligibility().getCriminallog());
           // employeeform.setDeaprtment(ere.getEligibility().getDepartment());
          //  employeeform.setMarks(ere.getEligibility().getMarks());
          //  employeeform.setIndiscipline(ere.getEligibility().getIndiscipline());
           
            request.setAttribute("button", button);
            return mapping.findForward("add");
                       }
            else{
            request.setAttribute("msg1", "electionid doesn't exists");
            return mapping.findForward("duplicate");
                }
        }

        if(button.equals("View") || button.equals("Block"))
        {
            

            if(l!=null){
               
                ere= (Election)l;
    

             employeeform.setCreatedby(ere.getCreatedBy());
              employeeform.setEnddate(ere.getEndDate());
               employeeform.setStartdate(ere.getStartDate());
               employeeform.setNominationStart(ere.getNstart());
               employeeform.setNominationEnd(ere.getNend());
               employeeform.setScrutnyDate(ere.getScrutnyDate());
               employeeform.setScrutnyEndDate(ere.getScrutnyEndDate());
               employeeform.setWithdrawlDate(ere.getWithdrawlDate());
               employeeform.setWithdrawlEndDate(ere.getWithdrawlEndDate());
               employeeform.setResultDeclarationDate(ere.getResultDeclarationDate());
                employeeform.setElectionname(ere.getElectionName());
                 employeeform.setStatus(ere.getStatus());
                 employeeform.setDescription(ere.getDescription());
            employeeform.setElectionId(ere.getId().getElectionId());
            employeeform.setInstituteId(ere.getId().getInstituteId());


           // employeeform.setCritaria(ere.getElectionrule().getCriteria());
          //  employeeform.setAttendence(ere.getEligibility().getAttendence());
            //employeeform.setBacklog(ere.getEligibility().getBacklog());
            //employeeform.setCriminal(ere.getEligibility().getCriminallog());
           // employeeform.setDeaprtment(ere.getEligibility().getDepartment());
          //  employeeform.setMarks(ere.getEligibility().getMarks());
          //  employeeform.setIndiscipline(ere.getEligibility().getIndiscipline());

            request.setAttribute("button", button);
            return mapping.findForward("add");
                       }
            
            else{
            request.setAttribute("msg1", "electionid doesn't exists");
            return mapping.findForward("duplicate");
                }
        }
//           if(button.equals("Block"))
//        {
//            if(l!=null){
//            employeeform.setCreatedby(l.getDescription());
//             employeeform.setCreatedby(l.getCreatedBy());
//              employeeform.setEnddate(l.getEndDate());
//               employeeform.setStartdate(l.getStartDate());
//                employeeform.setElectionname(l.getElectionName());
//                 employeeform.setStatus(l.getStatus());
//                 employeeform.setDescription(l.getDescription());
//
//            employeeform.setElectionId(l.getId().getElectionId());
//            employeeform.setInstituteId(l.getId().getInstituteId());
//            request.setAttribute("button", button);
//            return mapping.findForward("add");
//                       }
//            else{
//            request.setAttribute("msg1", "electionid doesn't exists");
//            return mapping.findForward("duplicate");
//                }
//        }

if(button.equals("Preview"))
return mapping.findForward("preview");
           String institute_id = (String)session.getAttribute("institute_id");
        String staff_id = (String)session.getAttribute("user_id");
List<CandidateRegLoginDetails> lstcandi = (List<CandidateRegLoginDetails>)CandidateRegistrationDAO.searchCandidate1(staff_id, institute_id);

        if(lstcandi!=null)
        {




             session.setAttribute("CandidateList", lstcandi);
        }

           return mapping.findForward("failure");
    }
    
}
