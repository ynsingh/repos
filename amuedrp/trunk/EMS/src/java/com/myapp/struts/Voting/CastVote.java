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

/**
 *
 * @author faraz
 */
public class CastVote extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
     
        HttpSession session = request.getSession();
        String instituteId = (String)session.getAttribute("institute_id");
        String electionId = (String)session.getAttribute("election_id");
        if(electionId==null)
            electionId=(String)request.getParameter("id");
        String voterId = (String)session.getAttribute("user_id");
ArrayList casting = new ArrayList();
        List casting1 = new ArrayList<Candidate1>();
        voterDAO voterdao = new voterDAO();

            String compute=(String)request.getParameter("compute");
             String agm=(String)request.getParameter("agm");



            System.out.println(compute);



                        if(agm!=null)
            {

               String vote = request.getParameter("cast");
                String[] cast = vote.split(",;");
            System.out.println("Cast"+cast.length+cast[0]);
//        vp = new VotingProcess();
//        VotingProcessId vpId = new VotingProcessId(electionId, instituteId, voterId);
//            vp.setId(vpId);
//            vp.setStatus("Voted");



          //  casting.add(vp);
//
//            System.out.println("cast Length="+cast.length);
//
//            String maxVoterid = voterdao.getMaxVoterBallotId(electionId, instituteId);
//            Integer vbId;
//            if(maxVoterid!=null)
//                vbId= Integer.parseInt(maxVoterid) + 1;
//            else
//                vbId = 1;
//            Voting vot = new Voting();
//            VotingId votId = new VotingId();
//            votId.setElectionId(electionId);
//            votId.setInstituteId(instituteId);
//            votId.setVoterBallotId(vbId.toString());
//            vot.setId(votId);
//            casting.add(vot);

        for(int i=0;i<cast.length;i++)
        {
System.out.println(cast[i]);
            int flag = 0;
            String[] positions = cast[i].split(",");
            System.out.println("its working1"+positions);
            String positionName = positions[0];
            PositionDAO posDAO = new PositionDAO();
            System.out.println("position Name="+positions[0]);
            Position1 pos = posDAO.getPositionByName(positionName.trim(), electionId, instituteId);
            if(pos!=null)
            {
            for(int j=1;j<positions.length;j++)
            {
                String candidateName = positions[j].substring(0,positions[j].lastIndexOf("."));
                String votes = positions[j].substring(positions[j].lastIndexOf(".")+1,positions[j].length());
                System.out.println(candidateName+"  "+votes);
                if(candidateName!=null && !candidateName.isEmpty())
                {
                    Candidate1 cand = posDAO.getCandidateDetailById(candidateName,pos.getId().getPositionId(), electionId, instituteId);

                    if(cand!=null)
                    {

                        cand.setAgm(Integer.parseInt(votes));
                        casting1.add(cand);
                        flag=1;
                    }
                }
           }
            }
            else{
                System.out.println("Position not Exist"+cast[i]);
            }

       }

        String msg=new String();
        if(!casting1.isEmpty()|| casting1!=null)
        {
            msg = (String)voterdao.InsertVote1(casting1);
            request.setAttribute("msg", msg);
        }
        response.setContentType("application/xml");
        StringBuffer emails=new StringBuffer();
        emails.append("<cast>");
            emails.append("<message>"+msg+"</message></cast>");
            response.getWriter().write(emails.toString());
            return null;
        }






           
            if(compute!=null)
            {

               String vote = request.getParameter("cast");
                String[] cast = vote.split(",;");
            System.out.println("Cast"+cast.length+cast[0]);
//        vp = new VotingProcess();
//        VotingProcessId vpId = new VotingProcessId(electionId, instituteId, voterId);
//            vp.setId(vpId);
//            vp.setStatus("Voted");



          //  casting.add(vp);
//
//            System.out.println("cast Length="+cast.length);
//
//            String maxVoterid = voterdao.getMaxVoterBallotId(electionId, instituteId);
//            Integer vbId;
//            if(maxVoterid!=null)
//                vbId= Integer.parseInt(maxVoterid) + 1;
//            else
//                vbId = 1;
//            Voting vot = new Voting();
//            VotingId votId = new VotingId();
//            votId.setElectionId(electionId);
//            votId.setInstituteId(instituteId);
//            votId.setVoterBallotId(vbId.toString());
//            vot.setId(votId);
//            casting.add(vot);
           
        for(int i=0;i<cast.length;i++)
        {
System.out.println(cast[i]);
            int flag = 0;
            String[] positions = cast[i].split(",");
            System.out.println("its working1"+positions);
            String positionName = positions[0];
            PositionDAO posDAO = new PositionDAO();
            System.out.println("position Name="+positions[0]);
            Position1 pos = posDAO.getPositionByName(positionName.trim(), electionId, instituteId);
            if(pos!=null)
            {
            for(int j=1;j<positions.length;j++)
            {
                String candidateName = positions[j].substring(0,positions[j].lastIndexOf("."));
                String votes = positions[j].substring(positions[j].lastIndexOf(".")+1,positions[j].length());
                System.out.println(candidateName+"  "+votes);
                if(candidateName!=null && !candidateName.isEmpty())
                {
                    Candidate1 cand = posDAO.getCandidateDetailById(candidateName,pos.getId().getPositionId(), electionId, instituteId);
                        
                    if(cand!=null)
                    {
                   
                        cand.setOfflineVote(Integer.parseInt(votes));
                        casting1.add(cand);
                        flag=1;
                    }
                }
           }
            }
            else{
                System.out.println("Position not Exist"+cast[i]);
            }

       }
       
        String msg=new String();
        if(!casting1.isEmpty()|| casting1!=null)
        {
            msg = (String)voterdao.InsertVote1(casting1);
            request.setAttribute("msg", msg);
        }
        response.setContentType("application/xml");
        StringBuffer emails=new StringBuffer();
        emails.append("<cast>");
            emails.append("<message>"+msg+"</message></cast>");
            response.getWriter().write(emails.toString());
            return null;
        }
     





         

        VotingProcess vp = voterdao.getVoter(instituteId, electionId, voterId);
        System.out.println("sdvfgsdgsdgs"+electionId);
        if(request.getParameter("id")!=null)
        {
        if(vp!=null)
        {
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
        String[] cast = vote.split(",;");
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
        for(int i=0;i<cast.length;i++)
        {
            
            int flag = 0;
            String[] positions = cast[i].split(",");
            System.out.println("its working1"+positions);
            String positionName = positions[0];
            PositionDAO posDAO = new PositionDAO();
            System.out.println("position Name="+positions[0]);
            Position1 pos = posDAO.getPositionByName(positionName.trim(), electionId, instituteId);
            if(pos!=null)
            {
            for(int j=1;j<positions.length;j++)
            {
                String candidateName = positions[j];
                if(candidateName!=null && !candidateName.isEmpty())
                {
                    Candidate1 cand = posDAO.getCandidateDetailByName(candidateName,pos.getId().getPositionId(), electionId, instituteId);
                    if(cand!=null)
                    {
                        VotingBallotId votbalId = new VotingBallotId(vbId.toString(), String.valueOf(pos.getId().getPositionId()) , String.valueOf(cand.getId().getCandidateId()));
                        VotingBallot votBal = new VotingBallot(votbalId);
                        posCol.add(votBal);
                        flag=1;
                    }
                }
            }
            }
            else{
                System.out.println("Position not Exist"+cast[i]);
            }
            
        }
        casting.add(posCol);
        System.out.println(casting.size());
        String msg=new String();
        if(!casting.isEmpty()|| casting!=null)
        {
            msg = (String)voterdao.InsertVote(casting);
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
