/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcquisitionDao;
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
//Library library=null;


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            List list=null;
            ResultSet rs=null;
            JRBeanCollectionDataSource dataSource=null;
           String path = servlet.getServletContext().getRealPath("/");
 String os=(String)System.getProperty("os.name");
   System.out.println("OS----------->"+os);
   if(os.startsWith("Linux"))
   {
path=path+"/JasperReport";
   }else{
   path=path+"\\JasperReport";
   }
Connection con=null;
ResultSet rs1=null;
HashMap SIMPLE_DATA;
int sum=0;
 AcquisitionDao acqdao=new AcquisitionDao();

        try
        {
            HttpSession session1 = request.getSession();
        String library_id = (String) session1.getAttribute("library_id");
        String sub_library_id = (String) session1.getAttribute("sublibrary_id");
 
                  System.out.println("Compiling report...");

                  if(os.startsWith("Linux"))
   {
          JasperCompileManager.compileReportToFile(path + "/onapproval.jrxml");
                  }else{
                  JasperCompileManager.compileReportToFile(path + "\\onapproval.jrxml");
                  }
             

           System.out.println("a******************************************************");

           

          
          OutputStream ouputStream = response.getOutputStream();
           response.setContentType("application/pdf");
System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        
HashMap map = new HashMap();


              

  List<ApprovalList> aa=acqdao.searchOnApprovalList(library_id, sub_library_id);


dataSource = new JRBeanCollectionDataSource(aa);
                  if(os.startsWith("Linux"))
   {

JasperFillManager.fillReportToFile(path+"/onapproval.jasper", map,dataSource );
                  }else{
   JasperFillManager.fillReportToFile(path+"\\onapproval.jasper", map,dataSource );
                  }


System.out.println("End");


File file1;

   System.out.println("Filling report..."+dataSource);
         if(os.startsWith("Linux"))
   {
       
 file1 = new File(path + "/" +"onapproval.jrprint");
         }else{
 file1 = new File(path + "\\" +"onapproval.jrprint");
         }
         System.out.println("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");
      JasperPrint jasperPrint1 = (JasperPrint)JRLoader.loadObject(file1);
     
       JRPdfExporter pdfExporter = new JRPdfExporter();
   pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint1);

         if(os.startsWith("Linux"))
   {

            pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                     path + "/" + "onapproval.pdf");
         }else{
            pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                     path + "\\" + "onapproval.pdf");

         }
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
}
