/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.Voting;

import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.ElectionDAO;
import com.myapp.struts.hbm.VotingDAO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.utility.AppPath;


/**
 *
 * @author Iqubal
 */
public class PreferentialFinalResultAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     *
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
        String institute_name =(String)session.getAttribute("institute_name");
         
        VotingDAO votingdao= new VotingDAO();
            String positions="";
          if(election!=null)
        {

            Election elec = ElectionDAO.searchElection(election,institute_id);
            if(elec!=null && elec.getStatus().equalsIgnoreCase("closed") && elec.getPublish()!=null)
            {
           
            session.setAttribute("election_id", election);
            session.setAttribute("electionName", elec.getElectionName());
            System.out.println("electionid="+election+"institute_id="+institute_id);
            List result=null;
       
            List<Result>   noofpos=(List<Result>)votingdao.GetNoOfPos(institute_id, election);
             
              result=votingdao.GetPreferencialResult1(institute_id, election,noofpos);
              Result   noofcand=(Result)votingdao.GetNoOfCand(institute_id, election);
               
           

              session.setAttribute("resultset", result);

            if(result!=null)
            {
               // final int ss=result.size();
               // System.out.println("Size of result:-----------------------------"+ss);
               // ComputePreferencial[] obj=new ComputePreferencial[ss];
                
        //   for(int i=0;i<result.size();i++){
      //   Result r=(Result)result.get(i); 
       //  System.out.println("value of Result @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+r.getPosition_name()+r.getPref());
        //  obj[i]=new ComputePreferencial(Integer.parseInt(r.getPosition_id()), Integer.parseInt(r.getCandidate_id()), r.getPref());
                
             //   }
                
                
                System.out.println(result+"kkkkkkkkkkkkkkk"+noofcand.getCand());
                positions+="<positions>";
                positions+="<election>"+elec.getElectionName()+"</election>";
                System.out.println("Position count!!!!!!!!!!!!!!!!!!!="+result.size());
            Iterator itpos = result.listIterator();
            LinkedHashMap<String,ArrayList> m = new LinkedHashMap<String, ArrayList>();
            ArrayList<String> lsPos = new ArrayList<String>();
            while(itpos.hasNext())
            {
                Result rs = (Result)itpos.next();
              
                if(m.containsKey(rs.getPosition_name()))
                {
                    m.get(rs.getPosition_name()).add(rs.getEnrolment());
                   // m.get(rs.getPosition_name()).add(rs.getCandidate_name());
                    m.get(rs.getPosition_name()).add(rs.getCandidateName());
                    System.out.println("jhgggggg5677757gggggg"+noofcand.getCand());      
                          
               for(int i=0;i<rs.getPref().length();i++){
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
                    //m.get(rs.getPosition_name()).add(rs.getCandidate_name());
                     m.get(rs.getPosition_name()).add(rs.getCandidateName());
                    for(int i=0;i<rs.getPref().length();i++){
                    String t=Character.toString(rs.getPref().charAt(i));                          
                    m.get(rs.getPosition_name()).add(t==null?0:t);
                      }
                    

                }
            }




            itpos = result.iterator();

                for (int i=0;i<m.size();i++)
                {
                    List ls = (List)m.get((String)lsPos.get(2*i));
                    positions+="<position>";
                    positions+="<positionname>"+lsPos.get(2*i)+"</positionname>";
                    positions+="<noofchoice>"+lsPos.get(2*i+1)+"</noofchoice>";

                    Iterator it = ls.iterator();
                    //code on 17 Jan
                    int noc=Integer.parseInt(lsPos.get(2*i+1));
                    for(int k=0;k<noc;k++)
                    //code on 17 Jan
                    //while(it.hasNext())
                    {
                        System.out.println(it.next()+"###################");
                        positions+="<candidate>";
                        
                        
                        
                        positions+="<candidatename>"+it.next().toString()+"</candidatename>";
                        System.out.println("Positionnnnnnnnnnnnn"+positions);
                        System.out.println("no of candidate issss "+lsPos.get(2*i+1));
                       // int noc=Integer.parseInt(lsPos.get(2*i+1));
                       // for(int k=0;k<noc;k++)
                        for(int j=0;j<noofcand.getCand();j++){ 
                        
                        positions+="<pref"+(j+1)+">"+it.next().toString()+"</pref"+(j+1)+">";
                        System.out.println("Position Test "+positions);
                   
                    }
                        positions+="</candidate>";
                      
                    }
                    positions+="</position>";
                }
                positions+="</positions>";
                System.out.println("XMLLLLLLLLLLLLLLLLLLLLLLLLLLL 1111="+positions);
                // response.setContentType("application/xml");
               // response.getWriter().write(positions);
            }
        }else{
         
                request.setAttribute("elec", "Sorry Election is not Closed");
                response.setContentType("application/xml");
                response.getWriter().write(positions);
                
        }
   }
                response.setContentType("application/xml");
                response.getWriter().write(positions);
              
               return null;
             
     
        
        
      
    }
}
