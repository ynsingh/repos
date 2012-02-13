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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


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



   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

       DepActionForm lf=(DepActionForm)form;
    
        HttpSession session = request.getSession();
      String institue_id=(String)session.getAttribute("institute_id");
     // System.out.println(institute_id);
         String button=lf.getButton();
         String id=lf.getElectionId();
       if(CheckDate(lf.getNominationStart(),lf.getNominationEnd()))
           if(CheckDate(lf.getScrutnyDate(),lf.getScrutnyEndDate()))
               if(CheckDate(lf.getWithdrawlDate(),lf.getWithdrawlEndDate()))
                   if(CheckDate(lf.getStartdate(),lf.getEnddate()))
                       if(CheckDate(lf.getNominationEnd(),lf.getScrutnyDate()))
                           if(CheckDate(lf.getScrutnyEndDate(),lf.getWithdrawlDate()))
                               if(CheckDate(lf.getWithdrawlEndDate(),lf.getStartdate()))
                               {

                               }
                               else
                               {
                                    request.setAttribute("msg1", "Election Start Date Should be greater than Withdrawal Date");
                                    return mapping.findForward("fail");
                               }
                           else
                               {
                                    request.setAttribute("msg1", "Withdrawal Start Date Should be greater than Scrutny End Date");
                                    return mapping.findForward("fail");
                               }
                       else
                               {
                                    request.setAttribute("msg1", "Scrutny Start Date Should be greater than Nomination End Date");
                                    return mapping.findForward("fail");
                               }
                   else
                               {
                                    request.setAttribute("msg1", "Election Start Date Should be lesser than Election End Date");
                                    return mapping.findForward("fail");
                               }
               else
                               {
                                    request.setAttribute("msg1", "Withdrawal Start Date Should be lesser than Withdrawal End Date");
                                    return mapping.findForward("fail");
                               }
            else
                               {
                                    request.setAttribute("msg1", "Scrutny Start Date Should be lesser than Scrutny End Date");
                                    return mapping.findForward("fail");
                               }
         else
                               {
                                    request.setAttribute("msg1", "Nomination Start Date Should be lesser than Nomination End Date");
                                    return mapping.findForward("fail");
                               }

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
            election.setResultDeclarationDate(lf.getResultDeclarationDate());
            
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

            ArrayList underprocessList=new ArrayList();
  Calendar cal1 = Calendar.getInstance();
    Date d = cal1.getTime();
        List<Election> election1 = ElectionDAO.Elections(institute_id);
        Iterator ite = election1.iterator();
        while(ite.hasNext())
        {

             cal1 = Calendar.getInstance();
            d = cal1.getTime();
            Election elec = (Election)ite.next();

             if(elec.getNstart().before(d) && elec.getWithdrawlEndDate().after(d))
            {
               underprocessList.add(elec);

            }



        }
  session.removeAttribute("underprocessList");
System.out.println(underprocessList.size());
        session.setAttribute("underprocessList", underprocessList);

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
            election.setNstart(lf.getNominationStart());
            election.setNend(lf.getNominationEnd());
            election.setResultDeclarationDate(lf.getResultDeclarationDate());


               Calendar cal1 = Calendar.getInstance();
           Date d = cal1.getTime();
           if(lf.getResultDeclarationDate().before(d))
            {
                election.setStatus("closed");
           }else if(lf.getStartdate().before(d) && lf.getEnddate().after(d))
              election.setStatus("started");
           else
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
      
        ArrayList underprocessList=new ArrayList();

        List<Election> election1 = ElectionDAO.Elections(institute_id);
        Iterator ite = election1.iterator();
        while(ite.hasNext())
        {

             cal1 = Calendar.getInstance();
            d = cal1.getTime();
            Election elec = (Election)ite.next();

             if(elec.getNstart().before(d) && elec.getWithdrawlEndDate().after(d))
            {
               underprocessList.add(elec);

            }



        }
  session.removeAttribute("underprocessList");
System.out.println(underprocessList.size());
        session.setAttribute("underprocessList", underprocessList);
         return mapping.findForward("add");

       }

         if(button.equals("Block"))
       {
             System.out.println(lf.getStatus());
       Election ele=ElectionDAO.searchElection(lf.getElectionId(), institue_id);
       ele.setStatus(lf.getStatus());
       ElectionDAO.update(ele);
         request.setAttribute("msg1", "record has deleted  successfully");
        return mapping.findForward("add1");
       }
         return mapping.findForward("add");
    }
    private boolean CheckDate(Timestamp d1,Timestamp d2)
    {
       if(d1.after(d2))
        return false;
       else
           return true;
    }
}
