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
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
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

          String postal =(String) request.getParameter("postal");
              String agm =(String) request.getParameter("agm");

        String positions="";
        session.removeAttribute("election_id");
        session.removeAttribute("electionName");

        if(report!=null)
        {
               List list;
              Election elec = ElectionDAO.searchElection(election,institute_id);
            VotingDAO votingdao= new VotingDAO();

            session.setAttribute("election_id", election);
            session.setAttribute("electionName", elec.getElectionName());
            System.out.println("electionid="+election+"institute_id="+institute_id);
            List result=votingdao.GetResult(institute_id, election);


         String path = servlet.getServletContext().getRealPath("/");
         JasperCompileManager.compileReportToFile(path+"/reports/ResultReport.jrxml");
        // String enroll=lf.getEnrollment();
 System.out.println("enroll");
        list=result;
if(!list.isEmpty()){
        // System.out.println(list.get(0)+""+enroll);
         JRBeanCollectionDataSource data=new  JRBeanCollectionDataSource(list);

          OutputStream ouputStream = response.getOutputStream();
           response.setContentType("application/pdf");

         HashMap hash= new HashMap();
//         hash.put("image",list.get(11));


         JasperFillManager.fillReportToFile(path+"/reports/ResultReport.jasper",hash,data);

         File file= new File(path+"/reports/ResultReport.jrprint");

         JasperPrint print =(JasperPrint)JRLoader.loadObject(file);

         JRPdfExporter pdf=new JRPdfExporter();

         pdf.setParameter(JRExporterParameter.JASPER_PRINT, print);
         pdf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path+"/reports/ResultReport.pdf");

         pdf.exportReport();
         JRExporter exporter = null;
                exporter = new JRHtmlExporter();
            JasperExportManager.exportReportToPdfStream(print, ouputStream);





 // path=path+"/src/java/com/myapp/struts/circulation/JasperReport";
        }

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
                result=result=votingdao.GetResultPostal(institute_id, election);
            else if(agm!=null)
                result=result=votingdao.GetResultAGM(institute_id, election);
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
                    m.get(rs.getPosition_name()).add(rs.getVotes());
                }
                else
                {
                    lsPos.add(rs.getPosition_name().toString());
                    lsPos.add(rs.getNumber_of_choice().toString());
                    m.put(rs.getPosition_name(),new ArrayList<String>());
                    m.get(rs.getPosition_name()).add(rs.getEnrolment());
                    m.get(rs.getPosition_name()).add(rs.getCandidate_name());
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
