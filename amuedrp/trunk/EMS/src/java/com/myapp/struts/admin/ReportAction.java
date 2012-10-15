/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;


import com.myapp.struts.hbm.Election;
import com.myapp.struts.hbm.ElectionDAO;
import com.myapp.struts.hbm.HibernateUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
 import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.Query;
import org.hibernate.Session;




/**
 *
 * @author edrp02
 */
public class ReportAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    public static final String REPORT_DIRECTORY = "reports";
    Connection connection=null;
    Statement statement=null;
    ResultSet resultSet=null;
    private Query query;
//Library library=null;


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();
            List list=null;
            ResultSet rs=null;
            JRBeanCollectionDataSource dataSource=null;

            String election_id=(String)request.getParameter("election");
            String institute_name=(String)session.getAttribute("institute_name");
            String institute_id=(String)session.getAttribute("institute_id");
            System.out.println("Election Name+++++++++++++"+election_id+""+institute_id);
             Election elec = ElectionDAO.searchElection(election_id,institute_id);
             String election_name=elec.getElectionName();
             System.out.println("Election Name++++++++++++++++++++"+election_name);
           String path = servlet.getServletContext().getRealPath("/");
//path=path.substring(0,path.lastIndexOf("/"));
//path=path.substring(0,path.lastIndexOf("/"));
//path=path.substring(0,path.lastIndexOf("/"));
//path=path+"/src/java/com/myapp/struts/Acquisition/JasperReport";
path=path+"/reports";
Connection con=null;
ResultSet rs1=null;
HashMap SIMPLE_DATA;
int sum=0;


        try
        {
            HttpSession session1 = request.getSession();
        String library_id = (String) session1.getAttribute("library_id");
        String sub_library_id = (String) session1.getAttribute("sublibrary_id");
 
                  System.out.println("Compiling report...");
          JasperCompileManager.compileReportToFile(path + "/logs.jrxml");
             

           System.out.println("a******************************************************");

           

          
          OutputStream ouputStream = response.getOutputStream();
           response.setContentType("application/pdf");
System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        
HashMap map = new HashMap();
 map.put("election_name",election_name);
 map.put("institute_name",institute_name);

  // List<Logs> aa=new ArrayList<Logs>();
 //  aa.add(new Logs("amu",12));
  // aa.add(new Logs("iitk",10));


  List<Logs> aa=searchLog(election_id);


System.out.println(aa.size()+";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");
dataSource = new JRBeanCollectionDataSource(aa);
JasperFillManager.fillReportToFile(path+"/logs.jasper", map,dataSource );


System.out.println("End");




   System.out.println("Filling report..."+dataSource);

         
 File file1 = new File(path + "/" +"logs.jrprint");
        
      JasperPrint jasperPrint1 = (JasperPrint)JRLoader.loadObject(file1);
     
       JRPdfExporter pdfExporter = new JRPdfExporter();
   pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint1);

   pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,path + "/" + "logs.pdf");
       System.out.println("Exporting report...");
          pdfExporter.exportReport();
          System.out.println("Done!");
          JRExporter exporter = null;


          exporter = new JRHtmlExporter();
          JasperExportManager.exportReportToPdfStream(jasperPrint1, ouputStream);






    }
        catch (JRException e)
        {
          e.printStackTrace();
        }




        return null;
    }
    public   ArrayList<Logs> searchLog(String election_id)
{
        Session session = HibernateUtil.getSessionFactory().openSession();
         ArrayList<Logs> demo=new ArrayList<Logs>();
        try
        {
            session.beginTransaction();
           String SQL_QUERY = "select a.gender,count(a.gender) as voted from VoterRegistration as a,VotingProcess as b where a.email=b.id.voterId and a.id.instituteId=b.id.instituteId and b.id.electionId=:election_id   group by a.gender";

  query = session.createQuery(SQL_QUERY);
  query.setString("election_id", election_id);
 for (Iterator it = query.iterate(); it.hasNext(); )
 {
     Object[] row = (Object[]) it.next();
     demo.add(new Logs(row[0].toString(), new Integer(row[1].toString())));
 }
  session.getTransaction().commit();
        }
         catch(Exception e){
        e.printStackTrace();

        }
        finally
        {
           session.close();
        }
    return demo;

}
}
