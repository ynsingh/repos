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
import com.myapp.struts.utility.AppPath;
import java.io.File;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRExporterParameter;

import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Edrp-04
 */
public class VotingAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         HttpSession session = request.getSession();
        String election =(String) request.getParameter("election");
        String report =(String) request.getParameter("report");
         String institute_id=(String)session.getAttribute("institute_id");
         String institute_name =(String)session.getAttribute("institute_name");
         String path = AppPath.getProjectImagePath();
         System.out.println("pathImageeeeeeeeeeeee"+path);
          String postal =(String) request.getParameter("postal");
              String agm =(String) request.getParameter("agm");

        String positions="";
        session.removeAttribute("election_id");
        session.removeAttribute("electionName");
        Election elec11 = ElectionDAO.searchElection(election,institute_id);
        System.out.println("election status is "+elec11.getPublish()+"     "+report);
        if(report!=null && elec11.getPublish()!=null && elec11.getPublish().equalsIgnoreCase("yes"))
        {
               List list;
              Election elec = ElectionDAO.searchElection(election,institute_id);
            VotingDAO votingdao= new VotingDAO();

            session.setAttribute("election_id", election);
            session.setAttribute("electionName", elec.getElectionName());
            System.out.println("electionid="+election+"institute_id="+institute_id);
            List result=votingdao.GetResult(institute_id, election);
            Calendar currentDate = Calendar.getInstance();
            SimpleDateFormat formatter=  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
            String dateNow = formatter.format(currentDate.getTime());
//compute total no of votes

            Iterator it=result.iterator();
            List finalResult=new ArrayList();
            int i=0;
            while(i<result.size()){
                Result rs=(Result)result.get(i);
                long r=0;
                if(rs.getAgm()!=null)
                   r+=Integer.parseInt(rs.getAgm());
                if(rs.getOffline_vote()!=null)
                   r+=Integer.parseInt(rs.getOffline_vote());
                if(rs.getVotes()!=null)
                   r+=Integer.parseInt(rs.getVotes());
                System.out.println("totalvotes######"+r);
                rs.setTotal(String.valueOf(r));
                finalResult.add(rs);
            i++;
            }




        // String path = servlet.getServletContext().getRealPath("/");
         JasperCompileManager.compileReportToFile(AppPath.getReportPath()+"ResultReport.jrxml");
        // String enroll=lf.getEnrollment();
 System.out.println("enroll");
        list=finalResult;
if(!list.isEmpty()){
        // System.out.println(list.get(0)+""+enroll);
         JRBeanCollectionDataSource data=new  JRBeanCollectionDataSource(list);

          OutputStream ouputStream = response.getOutputStream();
           response.setContentType("application/pdf");

         HashMap hash= new HashMap();
         hash.put("systemdate",dateNow);
         hash.put("institute_name",institute_name);
         hash.put("path",path);


         JasperFillManager.fillReportToFile(AppPath.getReportPath()+"ResultReport.jasper",hash,data);

         File file= new File(AppPath.getReportPath()+"ResultReport.jrprint");

         JasperPrint print =(JasperPrint)JRLoader.loadObject(file);

         JRPdfExporter pdf=new JRPdfExporter();

         pdf.setParameter(JRExporterParameter.JASPER_PRINT, print);
         pdf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, AppPath.getReportPath()+"ResultReport.pdf");

         pdf.exportReport();
         JRExporter exporter = null;
                exporter = new JRHtmlExporter();
            JasperExportManager.exportReportToPdfStream(print, ouputStream);





       path=path+"/src/java/com/myapp/struts/circulation/JasperReport";
        }

        }
        else if( report!=null )
        {
            if(session.getAttribute("login_role").toString().equalsIgnoreCase("Voter"))
            return mapping.findForward("redirect");
        }


        if(election!=null)
        {

            Election elec = ElectionDAO.searchElection(election,institute_id);
            if(elec!=null && elec.getStatus().equalsIgnoreCase("closed") && elec.getPublish()!=null)
            {
            VotingDAO votingdao= new VotingDAO();

            session.setAttribute("election_id", election);
            session.setAttribute("electionName", elec.getElectionName());
            System.out.println("electionid="+election+"institute_id="+institute_id);
            List result=null;
            System.out.println(postal+"................fghfghfghfg.");

            if(postal!=null)
            {   result=result=votingdao.GetResultPostal(institute_id, election);
            
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
                    m.get(rs.getPosition_name()).add(rs.getEnrolment());
                    m.get(rs.getPosition_name()).add(rs.getCandidate_name());
//                      m.get(rs.getPosition_name()).add(rs.getOffline_vote());
//                    m.get(rs.getPosition_name()).add(rs.getAgm());
                    m.get(rs.getPosition_name()).add(rs.getVotes());
                }
                else
                {
                     lsPos.add(rs.getPosition_name().toString());
                    lsPos.add(rs.getNumber_of_choice().toString());
                    m.put(rs.getPosition_name(),new ArrayList<String>());
                    m.get(rs.getPosition_name()).add(rs.getEnrolment());
                    m.get(rs.getPosition_name()).add(rs.getCandidate_name());
//                      m.get(rs.getPosition_name()).add(rs.getOffline_vote());
//                    m.get(rs.getPosition_name()).add(rs.getAgm());
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
                        positions+="<candidateenroll>"+it.next().toString()+"</candidateenroll>";
                        positions+="<candidatename>"+it.next().toString()+"</candidatename>";
//                     positions+="<postalvotes>"+it.next().toString()+"</postalvotes>";
//                     positions+="<agmvotes>"+it.next().toString()+"</agmvotes>";
                        positions+="<votes>"+it.next().toString()+"</votes>";
                        positions+="</candidate>";
                    }
                    positions+="</position>";
                }
                positions+="</positions>";

            }
            
             System.out.println("XML ="+positions);
                response.setContentType("application/xml");
                response.getWriter().write(positions);
                return null;
            
            }
            else if(agm!=null)
            {
                result=result=votingdao.GetResultAGM(institute_id, election);

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
                    m.get(rs.getPosition_name()).add(rs.getEnrolment());
                    m.get(rs.getPosition_name()).add(rs.getCandidate_name());
//                      m.get(rs.getPosition_name()).add(rs.getOffline_vote());
//                    m.get(rs.getPosition_name()).add(rs.getAgm());
                    m.get(rs.getPosition_name()).add(rs.getVotes());
                }
                else
                {
                     lsPos.add(rs.getPosition_name().toString());
                    lsPos.add(rs.getNumber_of_choice().toString());
                    m.put(rs.getPosition_name(),new ArrayList<String>());
                    m.get(rs.getPosition_name()).add(rs.getEnrolment());
                    m.get(rs.getPosition_name()).add(rs.getCandidate_name());
//                      m.get(rs.getPosition_name()).add(rs.getOffline_vote());
//                    m.get(rs.getPosition_name()).add(rs.getAgm());
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
                        positions+="<candidateenroll>"+it.next().toString()+"</candidateenroll>";
                        positions+="<candidatename>"+it.next().toString()+"</candidatename>";
//                     positions+="<postalvotes>"+it.next().toString()+"</postalvotes>";
//                     positions+="<agmvotes>"+it.next().toString()+"</agmvotes>";
                        positions+="<votes>"+it.next().toString()+"</votes>";
                        positions+="</candidate>";
                    }
                    positions+="</position>";
                }
                positions+="</positions>";

            }

             System.out.println("XML ="+positions);
                response.setContentType("application/xml");
                response.getWriter().write(positions);
                return null;





            }
            else
                 result=votingdao.GetResult(institute_id, election);

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
                        positions+="<candidateenroll>"+it.next().toString()+"</candidateenroll>";
                        positions+="<candidatename>"+it.next().toString()+"</candidatename>";
                        positions+="<postalvotes>"+it.next().toString()+"</postalvotes>";
                        positions+="<agmvotes>"+it.next().toString()+"</agmvotes>";
                        positions+="<votes>"+it.next().toString()+"</votes>";
                        positions+="</candidate>";
                    }
                    positions+="</position>";
                }
                positions+="</positions>";

            }
        }else{
         
                request.setAttribute("elec", "Sorry Election is not Closed");
                response.setContentType("application/xml");
                response.getWriter().write(positions);
        }

        }
         System.out.println("XML ="+positions);
                response.setContentType("application/xml");
                response.getWriter().write(positions);
                return null;
       // return mapping.findForward(SUCCESS);

    }
}
