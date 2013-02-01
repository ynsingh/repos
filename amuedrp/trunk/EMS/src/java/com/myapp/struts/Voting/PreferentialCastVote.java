/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Voting;

import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.Position1;
import com.myapp.struts.hbm.PositionDAO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.utility.UserLog;

/**
 *
 *
 */
public class PreferentialCastVote extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
	private static final String SUCCESS = "success";

    @Override
    	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
     System.out.println("Preferential cat vote Action Call ");
        	HttpSession session = request.getSession();
		String ClientIPAdd=(String)request.getRemoteAddr();
        	String instituteId = (String)session.getAttribute("institute_id");
        	String electionId = (String)session.getAttribute("election_id");
        	if(electionId==null)
            		electionId=(String)request.getParameter("id");
        		String voterId = (String)session.getAttribute("user_id");
			ArrayList casting = new ArrayList();
        		List casting1 = new ArrayList<Candidate1>();
        		voterDAO voterdao = new voterDAO();

        	VotingProcess vp = voterdao.getVoter(instituteId, electionId, voterId);
        	System.out.println("sdvfgsdgsdgs"+electionId);
        	if(request.getParameter("id")!=null){
        		if(vp!=null){
            			response.setContentType("application/xml");
            			StringBuffer emails=new StringBuffer();
            			emails.append("<cast>");
            			emails.append("<message>Voter Already voted for this election!</message></cast>");
            			response.getWriter().write(emails.toString());
            			return null;
        		}else{
        			response.setContentType("application/xml");
            			StringBuffer emails=new StringBuffer();
            			emails.append("<cast>");
            			emails.append("<message>Please Vote</message></cast>");
            			response.getWriter().write(emails.toString());
            			return null;
        		}

               	}else{
        		String vote = request.getParameter("cast");
			if(vote.startsWith(";"))
   				vote=vote.substring(1,vote.length());
				UserLog.ErrorLog("Voter IP Address"+ClientIPAdd+"\n"+vote,"UserLog.txt");
        			String[] cast = vote.split(";,");
                               
                                 System.out.println(cast[0].toString()+"BGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
                               



        			if(vp!=null)
        			{
            				response.setContentType("application/xml");
            				StringBuffer emails=new StringBuffer();
            				emails.append("<cast>");
            				emails.append("<message>Voter Already voted for this election!</message></cast>");
            				response.getWriter().write(emails.toString());
            				return null;
        			}
        			vp = new VotingProcess();
        			VotingProcessId vpId = new VotingProcessId(electionId, instituteId, voterId);
            			vp.setId(vpId);
            			vp.setStatus("Voted");
            			casting.add(vp);

            			System.out.println("cast Length="+cast.length+cast[0]);

            			String maxVoterid = voterdao.getMaxVoterBallotId(electionId, instituteId);
            			System.out.println(maxVoterid+"......................"+"Voting Result");
            			Integer vbId;

                		vbId= Integer.parseInt(maxVoterid);
            			Voting vot = new Voting();
            			VotingId votId = new VotingId();
            			votId.setElectionId(electionId);
            			votId.setInstituteId(instituteId);
            			votId.setVoterBallotId(vbId.toString());
            			vot.setId(votId);
            			casting.add(vot);
            			ArrayList posCol = new ArrayList();
                                ArrayList prefposCol = new ArrayList();
        			for(int i=0;i<cast.length;i++){
            		int flag = 0;
            		String[] positions = cast[i].split(",");
           	        System.out.println("its working1"+positions);
            		String positionName = positions[0];
            		PositionDAO posDAO = new PositionDAO();
            		System.out.println("position Name="+positions[0]+"||"+positions[1]+"||"+positions[2]);
			UserLog.ErrorLog(positions[0]+"||"+positions[1],"UserLog.txt");
            		Position1 pos = posDAO.getPositionByName(positionName.trim(), electionId, instituteId);
            		if(pos!=null){
            		for(int j=1;j<positions.length;j++){
                	String candidateName = positions[j];
                	if(candidateName!=null && !candidateName.isEmpty()){
                    	Candidate1 cand = posDAO.getCandidateDetailByName(candidateName,pos.getId().getPositionId(), electionId, instituteId);
                    	if(cand!=null){
                        VotingBallotId votbalId = new VotingBallotId(vbId.toString(), String.valueOf(pos.getId().getPositionId()) , String.valueOf(cand.getId().getCandidateId()));
                        VotingBallot votBal = new VotingBallot(votbalId);
                        posCol.add(votBal);
                       

                        PreferencialVoting pv=new PreferencialVoting();
                        PreferencialVotingId pvid=new PreferencialVotingId();
                        pvid.setCandidateId(votBal.getId().getCandidateId());
                        pvid.setVoterId(voterId);
                        pv.setVoterBallotId(votBal.getId().getVoterBallotId());
                        pvid.setElectionId(electionId);
                        pvid.setPositionId(votBal.getId().getPositionId());
                        pv.setInstituteId(instituteId);
                        if(positions[2].endsWith(";"))
                        pv.setPreference(positions[2].substring(0,positions[2].length()-1));
                        else
                        pv.setPreference(positions[2]);
                        pv.setId(pvid);
                        prefposCol.add(pv);
                                flag=1;
                    	      }
                		}
            			}
            			}else{
                		System.out.println("Position not Exist"+cast[i]);
            				}
                    		}
        			casting.add(posCol);
                                casting.add(prefposCol);
        			System.out.println(casting.size());
        			String msg=new String();
        			if(!casting.isEmpty()|| casting!=null){

            			msg = (String)voterdao.InsertPVote(casting,"UserLog.txt");

            			request.setAttribute("msg", msg);
        			}
        			response.setContentType("application/xml");
        			StringBuffer emails=new StringBuffer();
        			emails.append("<cast>");
            			emails.append("<message>"+msg+"</message></cast>");
            			response.getWriter().write(emails.toString());
        		}
        		return null;
    		}
	}
