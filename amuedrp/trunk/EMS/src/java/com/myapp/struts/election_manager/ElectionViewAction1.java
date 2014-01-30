/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.election_manager;

import com.myapp.struts.Candidate.CandidateRegistrationDAO;
import com.myapp.struts.Voter.VoterRegistrationDAO;
import com.myapp.struts.Voting.Result;
import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.ElectionId;
import com.myapp.struts.utility.AppPath;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
        
         String btt= request.getParameter("bt");
         System.out.println("button is "+btt);
         if(btt!=null)
         {
            
             File file = new File(AppPath.getPropertiesFilePath1()+"election_delete.txt");
           if (!file.exists()) {
				file.createNewFile();
			}
                        FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
                        String elid1=request.getParameter("id").toString();
                        String institute_id=session.getAttribute("institute_id").toString();
                        Election el = ElectionDAO.searchElection(elid1,institute_id);
                        Calendar cal = Calendar.getInstance();
                         Date d = cal.getTime();
                         
               if( el.getStartDate().after(d) || (el.getResultDeclarationDate().before(d) && el.getPublish()!=null) )
               {
                    int i;
                        bw.write("**********************New Election Record**********************************");
                        bw.newLine();
                        bw.write("**********************Election*********************************************");
                        bw.newLine();
                        bw.write("Election Id             "+elid1);
                        bw.newLine();
                        bw.write("Election Name           "+el.getElectionName());
                        bw.newLine();
                        bw.write("Institute Id            "+institute_id);
                        bw.newLine();
                        bw.write("Description             "+el.getDescription());
                        bw.newLine();
                        bw.write("Start Date              "+el.getStartDate());
                        bw.newLine();
                        bw.write("End Date                "+el.getEndDate());
                        bw.newLine();
                        bw.write("Status                  "+el.getStatus());
                        bw.newLine();
                        bw.write("Nomination Start        "+el.getNstart());
                        bw.newLine();
                        bw.write("Nomination End          "+el.getNend());
                        bw.newLine();
                        bw.write("Scuritny Start          "+el.getScrutnyDate());
                        bw.newLine();
                        bw.write("Scuritny End            "+el.getScrutnyEndDate());
                        bw.newLine();
                        bw.write("Withdrawl Start         "+el.getWithdrawlDate());
                        bw.newLine();
                        bw.write("Withdrawl End           "+el.getWithdrawlEndDate());
                        bw.newLine();
                        bw.write("Result Declaration Date "+el.getResultDeclarationDate());
                        bw.newLine();
                        bw.write("Created By              "+el.getCreatedBy());
                        bw.newLine();
                        bw.write("Publish                 "+el.getPublish());
                        bw.newLine();
                        

                        System.out.println("iiiiiiiiiiiiii");

                        VotingDAO votingdao= new VotingDAO();
                        List<Result> result=votingdao.GetResult(institute_id, elid1);
                        System.out.println("Position count = "+result.size());
                        if(!result.isEmpty())
                        {


            Iterator itpos = result.listIterator();
            LinkedHashMap<String,ArrayList> m = new LinkedHashMap<String, ArrayList>();
            ArrayList<String> lsPos = new ArrayList<String>();
            while(itpos.hasNext())
            {
                Result rs = (Result)itpos.next();
                if(m.containsKey(rs.getPosition_name()))
                {
                    m.get(rs.getPosition_name()).add(rs.getEnrolment());
                    m.get(rs.getPosition_name()).add(rs.getCandidate_name());
                    m.get(rs.getPosition_name()).add(rs.getOffline_vote()==null?0:rs.getOffline_vote());
                    m.get(rs.getPosition_name()).add(rs.getAgm()==null?0:rs.getAgm());
                    m.get(rs.getPosition_name()).add(rs.getVotes());
                }
                else
                {
                    lsPos.add(rs.getPosition_name().toString());
                    lsPos.add(rs.getNumber_of_choice().toString());
                    m.put(rs.getPosition_name(),new ArrayList<String>());
                    m.get(rs.getPosition_name()).add(rs.getEnrolment());
                    m.get(rs.getPosition_name()).add(rs.getCandidate_name());
                    m.get(rs.getPosition_name()).add(rs.getOffline_vote()==null?0:rs.getOffline_vote());
                    m.get(rs.getPosition_name()).add(rs.getAgm()==null?0:rs.getAgm());
                    m.get(rs.getPosition_name()).add(rs.getVotes());
                }
            }




                itpos = result.iterator();

                for (i=0;i<m.size();i++)
                {
                    List ls = (List)m.get((String)lsPos.get(2*i));
                     bw.write("**************Result****************** ");
                     bw.newLine();
                     bw.write("Position Name "+lsPos.get(2*i));
                     bw.newLine();
                     bw.write("No of Position "+lsPos.get(2*i+1));
                     bw.newLine();
                     Iterator it = ls.iterator();
                     while(it.hasNext())
                     {
                       bw.write("Candidate Enrollment "+it.next().toString());
                       bw.newLine();
                       bw.write("Candidate Name "+it.next().toString());
                       bw.newLine();
                       bw.write("Postal Votes "+it.next().toString());
                       bw.newLine();
                       bw.write("Agm Votes "+it.next().toString());
                       bw.newLine();
                       bw.write("Votes "+it.next().toString());
                       bw.newLine();
                    }
                }

            }
                        else
                        {
                            bw.write("**************Result****************** ");
                            bw.newLine();
                            bw.write("Sorry No Result Found ");
                       bw.newLine();
                        }

                        
                        
                        
                        PositionDAO pos=new PositionDAO();
                        List <Position1> pos1=pos.getPosition(elid1,institute_id);
                        result=votingdao.GetPreferencialResult(institute_id, elid1);
                        Result   noofcand=(Result)votingdao.GetNoOfCand(institute_id, elid1);

                        if(result!=null)
                {
                 System.out.println(result+"  kkkkkkkkkkkkkkk  "+noofcand.getCand());

                System.out.println("Position count!!!!!!!!!!!!!!!!!!!="+result.size());
                Iterator itpos = result.listIterator();
                LinkedHashMap<String,ArrayList> m = new LinkedHashMap<String, ArrayList>();
                ArrayList<String> lsPos = new ArrayList<String>();
            while(itpos.hasNext())
            {
                System.out.println("Resulttttttt "+noofcand.getCand());
                Result rs = (Result)itpos.next();

                if(m.containsKey(rs.getPosition_name()))
                {
                    m.get(rs.getPosition_name()).add(rs.getEnrolment());
                    //m.get(rs.getPosition_name()).add(rs.getCandidate_name());
                    m.get(rs.getPosition_name()).add(rs.getCandidateName());
                 System.out.println("jhgggggggggggg"+noofcand.getCand());




                      for(i=0;i<rs.getPref().length();i++){
                          String t=Character.toString(rs.getPref().charAt(i));

                          m.get(rs.getPosition_name()).add(t==null?0:t);
                      }


                }
                else
                {


                     lsPos.add(rs.getPosition_name().toString());
                    lsPos.add(rs.getNumber_of_choice().toString());
                    m.put(rs.getPosition_name(),new ArrayList<String>());
                    m.get(rs.getPosition_name()).add(rs.getEnrolment());
                  //  m.get(rs.getPosition_name()).add(rs.getCandidate_name());
                    m.get(rs.getPosition_name()).add(rs.getCandidateName());
                    for(i=0;i<rs.getPref().length();i++){
                          String t=Character.toString(rs.getPref().charAt(i));

                          m.get(rs.getPosition_name()).add(t==null?0:t);
                      }
                    }
            }




            itpos = result.iterator();

                for (i=0;i<m.size();i++)
                {
                    List ls = (List)m.get((String)lsPos.get(2*i));
                    bw.write("**************Preferential Result****************** ");
                     bw.newLine();
                     bw.write("Position Name   "+lsPos.get(2*i));
                     bw.newLine();
                   bw.write("Number of Choice  "+lsPos.get(2*i+1));
                     bw.newLine();
                    

                    Iterator it = ls.iterator();
                    while(it.hasNext())
                    {
                        System.out.println(it.next()+"###################");
                        
                        bw.write("Candidate Name  "+it.next().toString());
                        bw.newLine();
                       


                        for(int j=0;j<noofcand.getCand();j++){
                        bw.write("Preference  "+(j+1)+" : "+it.next().toString());
                        bw.newLine();
                        

                    }


                    }

                }


            }
        else{

                 bw.write("**************Preferential Result****************** ");
                            bw.newLine();
                            bw.write("Sorry No Result Found ");
                       bw.newLine();
        }

                        if(!pos1.isEmpty())
                        {
                           for(i=0;i<=(pos1.size())-1;i++)
                           {
                               ElectionDAO.deletePreferentialVoting(Integer.toString(pos1.get(i).getId().getPositionId()),elid1, institute_id);
                           }
                        }

                        ElectionDAO.delete(elid1, institute_id);
                        
                        List<CandidateRegistration> cdr=CandidateRegistrationDAO.getCandidateDetailsByElectionId(institute_id,elid1);
                        int sz=cdr.size();
                        System.out.println("size is "+sz);
                       
                        for(i=0;i<=sz-1;i++){
                            System.out.println("i is = "+i);
                        bw.write("**********************candidate_registration**********************************");
                        bw.newLine();

                        bw.write("Enrollment              "+cdr.get(i).getId().getEnrollment());
                        bw.newLine();
                        bw.write("Institute Id            "+institute_id);
                        bw.newLine();
                        bw.write("Election Id             "+elid1);
                        bw.newLine();
                        bw.write("Enrolled In             "+cdr.get(i).getEnrolledIn());
                        bw.newLine();
                        bw.write("P Marks                 "+cdr.get(i).getPMarks());
                        bw.newLine();
                        bw.write("P Attendance            "+cdr.get(i).getPAttendence());
                        bw.newLine();
                        bw.write("Backlog                 "+cdr.get(i).getBacklog());
                        bw.newLine();
                        bw.write("Criminal                "+cdr.get(i).getCriminal());
                        bw.newLine();
                        bw.write("Indisc                  "+cdr.get(i).getIndisc());
                        bw.newLine();
                        bw.write("Position                "+cdr.get(i).getPosition());
                        bw.newLine();
                        bw.write("Status1                 "+cdr.get(i).getStatus());
                        bw.newLine();
                        bw.write("Rejected Reason         "+cdr.get(i).getRejectedReason());
                        bw.newLine();
                        bw.write("Request Date            "+cdr.get(i).getRequestDate());
                        bw.newLine();
                        bw.write("Accepted Date           "+cdr.get(i).getAcceptedDate());
                        bw.newLine();
                        bw.write("Proposed By             "+cdr.get(i).getProposedBy());
                        bw.newLine();
                        bw.write("Seconded By             "+cdr.get(i).getSecondedBy());
                        bw.newLine();
                        bw.write("Position Accepted       "+cdr.get(i).getPositionAccepted());
                        bw.newLine();
                           CandidateRegistrationDAO.deletecandidate(cdr.get(i));
                        }

                        List <SetVoter> vl=ElectionDAO.searchVoter1(elid1,institute_id);
                        System.out.println("hhhhhhhh "+vl.size());
                        int j;
                        VoterRegistration vr;
                        for(i=0;i<=(vl.size())-1;i++)
                        {
                            System.out.println("i is = "+i);
                            bw.write("**********************Set Voter**********************************");
                            bw.newLine();
                            bw.write("Enrollment              "+vl.get(i).getId().getEnrollment());
                            bw.newLine();
                            bw.write("Institute Id            "+institute_id);
                            bw.newLine();
                            bw.write("Election Id             "+elid1);
                            bw.newLine();
                            bw.write("Status                  "+vl.get(i).getStatus());
                            bw.newLine();
                            vr=VoterRegistrationDAO.searchVoterRegistration(institute_id, vl.get(i).getId().getEnrollment());
                            if(vr!=null)
                            {
                                bw.write("Voter Name              "+vr.getVoterName());
                                bw.newLine();
                                bw.write("Mobile No               "+vr.getMobileNumber());
                                bw.newLine();
                                bw.write("Email Id                "+vr.getEmail());
                                bw.newLine();
                            }
                        ElectionDAO.deleteSetVoter(vl.get(i));
                                                                                               
                        }
                        List <Electionrule> elr=ElectionDAO.searchElectionrule(elid1,institute_id);
                        if(!elr.isEmpty()){
                        for(i=0;i<=(elr.size())-1;i++)
                        {
                            System.out.println("i is = "+i);
                            bw.write("**********************Election Rule**********************************");
                            bw.newLine();
                            bw.write("Election Id              "+elr.get(i).getId().getElectionId());
                            bw.newLine();
                            bw.write("Institute Id            "+institute_id);
                            bw.newLine();
                            bw.write("Election Id             "+elr.get(i).getCriteria());
                            bw.newLine();
                            ElectionDAO.deleteElectionrule(elr.get(i));
                        }
                        }
                        else{
                            bw.write("**********************Election Rule**********************************");
                            bw.newLine();
                            bw.write("No Election Rules Found");
                            bw.newLine();
                        }
                        List <Eligibility> elg=ElectionDAO.searchElectionEligibility(elid1,institute_id);
                        if(!elg.isEmpty())
                        {
                        for(i=0;i<=(elg.size())-1;i++)
                        {
                            System.out.println("i is = "+i);
                            bw.write("**********************Eligibility************************************");
                            bw.newLine();
                            bw.write("Election Id              "+elg.get(i).getId().getElectionId());
                            bw.newLine();
                            bw.write("Institute                "+elg.get(i).getId().getInstituteId());
                            bw.newLine();
                            bw.write("Eligibility              "+elg.get(i).getEligibility());
                            bw.newLine();
                            bw.write("Department               "+elg.get(i).getDepartment());
                            bw.newLine();
                            bw.write("Marks                   "+elg.get(i).getMarks());
                            bw.newLine();
                            bw.write("Backlog                 "+elg.get(i).getBacklog());
                            bw.newLine();
                            bw.write("Criminal Log            "+elg.get(i).getCriminallog());
                            bw.newLine();
                            bw.write("Indiscipline            "+elg.get(i).getIndiscipline());
                            bw.newLine();
                            ElectionDAO.deleteEligibility(elg.get(i));
                        }
                        }
                        else{
                            
                            bw.write("**********************Eligibility**********************************");
                            bw.newLine();
                            bw.write("No Election Eligibility Found ");
                            bw.newLine();
                        }
                        ///////////////////////////
                        
                        
                        
                        
                       
                       String p;
                       if(!pos1.isEmpty())
                       {
                           for(i=0;i<=(pos1.size())-1;i++)
                           {
                               ElectionDAO.deleteVotingBallot(Integer.toString(pos1.get(i).getId().getPositionId()));
                           }
                       }
                       
                        BallotPositionCandiDAO.deleteBallot(elid1, institute_id);
                        CandidateRegistrationDAO.deleteCandi1(elid1, institute_id);
                        ElectionDAO.deleteElectionManagerMigrate(elid1, institute_id);
                        ElectionDAO.deletePosition(elid1, institute_id);
                        ElectionDAO.deleteVoting(elid1, institute_id);
                        ElectionDAO.deleteVotingProcess(elid1, institute_id);
                                                       
                        ///////////////////////
                        bw.close();

                        fw.close();
                        request.setAttribute("msg", "Election deleted successfully");

		  System.out.println("File existed");
                  return mapping.findForward("delete");
               }
               else
               {
                   request.setAttribute("msg", "We can not delete this Election ");

		  System.out.println("File existeddddd");
                  return mapping.findForward("delete");
               }
		//  fileOut.close();
             
         }

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
