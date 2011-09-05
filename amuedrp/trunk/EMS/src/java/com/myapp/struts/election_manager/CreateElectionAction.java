/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;


/**
 *
 * @author akhtar
 */
public class CreateElectionAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
     ElectionRuleEligiblity1 ob= new ElectionRuleEligiblity1();
      private  String institute_id;
      private ElectionruleeligibilityDAO ere= new ElectionruleeligibilityDAO();
      

ElectionId empid=new ElectionId ();



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

       DepActionForm lf=(DepActionForm)form;
    
        HttpSession session = request.getSession();
      String institue_id=(String)session.getAttribute("institute_id");
      System.out.println(institute_id);
         String button=lf.getButton();
         String id=lf.getElectionId();
       if(button.equals("Submit"))
       {
         empid.setElectionId(id);
       // empid.setElectionId(lf.getElectionid());
        empid.setInstituteId(lf.getInstituteId());
Election election = new Election();
Electionrule electionrule = new Electionrule();
Eligibility eligibility = new Eligibility();
ElectionruleId electionruleid = new ElectionruleId();
EligibilityId eligibilityid = new EligibilityId();
Ballot ballot=new Ballot();
BallotId ballotid=new BallotId();
        election.setCreatedBy(lf.getCreatedby());
         election.setDescription(lf.getDescription());
          election.setElectionName(lf.getElectionname());
          System.out.println(lf.getStartdate());
           election.setEndDate(lf.getEnddate());
            election.setStartDate(lf.getStartdate());
            election.setNstart(lf.getNominationStart());
            election.setNend(lf.getNominationEnd());
            election.setScrutnyDate(lf.getScrutnyDate());
            election.setScrutnyEndDate(lf.getScrutnyEndDate());
            election.setWithdrawlDate(lf.getWithdrawlDate());
            election.setWithdrawlEndDate(lf.getWithdrawlEndDate());
             election.setStatus("under-process");
            
        election.setId(empid);
        ballot.setBallotName(lf.getElectionname());
        ballotid.setBallotId(lf.getElectionId());
        ballotid.setElectionId(lf.getElectionId());
        ballotid.setInstituteId(lf.getInstituteId());
        ballot.setId(ballotid);
electionruleid.setElectionId(lf.getElectionId());
electionruleid.setInstituteId(lf.getInstituteId());
electionruleid.setRuleId(lf.getElectionId());
electionrule.setId(electionruleid);
electionrule.setCriteria(lf.getCritaria());


eligibilityid.setElectionId(lf.getElectionId());
eligibilityid.setInstituteId(lf.getInstituteId());
eligibilityid.setEligibilityId(lf.getElectionId());
eligibilityid.setRuleId(lf.getElectionId());
eligibility.setId(eligibilityid);
eligibility.setAttendence(lf.getAttendence());
eligibility.setBacklog(lf.getBacklog());
eligibility.setCriminallog(lf.getCriminal());
eligibility.setDepartment(lf.getDeaprtment());
eligibility.setIndiscipline(lf.getIndiscipline());
eligibility.setMarks(lf.getMarks());
ob.setElection(election);
ob.setEligibility(eligibility);
ob.setElectionrule(electionrule);
ob.setBallot(ballot);
session.setAttribute("election_id",lf.getElectionId());

        ere.insert(ob);
         request.setAttribute("msg1", "record inserted succsessfully");
        return mapping.findForward("add");
       }
       if(button.equals("Update"))
       {
       empid.setElectionId(id);
       // empid.setElectionId(lf.getElectionid());
        empid.setInstituteId(lf.getInstituteId());
Election election = new Election();
Electionrule electionrule = new Electionrule();
Eligibility eligibility = new Eligibility();
ElectionruleId electionruleid = new ElectionruleId();
EligibilityId eligibilityid = new EligibilityId();
Ballot ballot=new Ballot();
BallotId ballotid=new BallotId();
        election.setCreatedBy(lf.getCreatedby());
         election.setDescription(lf.getDescription());
          election.setElectionName(lf.getElectionname());
          System.out.println(lf.getStartdate());
           election.setEndDate(lf.getEnddate());
            election.setStartDate(lf.getStartdate());
             election.setScrutnyDate(lf.getScrutnyDate());
            election.setScrutnyEndDate(lf.getScrutnyEndDate());
            election.setWithdrawlDate(lf.getWithdrawlDate());
            election.setWithdrawlEndDate(lf.getWithdrawlEndDate());
             election.setStatus("under-process");

        election.setId(empid);
electionruleid.setElectionId(lf.getElectionId());
electionruleid.setInstituteId(lf.getInstituteId());
electionruleid.setRuleId(lf.getElectionId());
electionrule.setId(electionruleid);
electionrule.setCriteria(lf.getCritaria());


ballot.setBallotName(lf.getElectionname());
        ballotid.setBallotId(lf.getElectionId());
        ballotid.setElectionId(lf.getElectionId());
        ballotid.setInstituteId(lf.getInstituteId());
        ballot.setId(ballotid);

eligibilityid.setElectionId(lf.getElectionId());
eligibilityid.setInstituteId(lf.getInstituteId());
eligibilityid.setEligibilityId(lf.getElectionId());
eligibilityid.setRuleId(lf.getElectionId());
eligibility.setId(eligibilityid);
eligibility.setAttendence(lf.getAttendence());
eligibility.setBacklog(lf.getBacklog());
eligibility.setCriminallog(lf.getCriminal());
eligibility.setDepartment(lf.getDeaprtment());
eligibility.setIndiscipline(lf.getIndiscipline());
eligibility.setMarks(lf.getMarks());
ob.setElection(election);
ob.setEligibility(eligibility);
ob.setElectionrule(electionrule);
ob.setBallot(ballot);
        ere.update(ob);
        session.setAttribute("election_id",lf.getElectionId());
         request.setAttribute("msg1", "record updated successfully");
        return mapping.findForward("add");
       }

         if(button.equals("Delete"))
       {
        ElectionDAO.delete(id,lf.getInstituteId());
         request.setAttribute("msg1", "record has deleted  successfully");
        return mapping.findForward("add");
       }
         return mapping.findForward("add");
    }
}
