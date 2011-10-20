/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import com.myapp.struts.utility.pathConversion;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
 import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;




/**
 *
 * @author edrp02
 */
public class LogReportAction extends org.apache.struts.action.Action {

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


        try
        {
            HttpSession session1 = request.getSession();
        
 
                  System.out.println("Compiling report...");
          if(os.startsWith("Linux"))
   {
                  JasperCompileManager.compileReportToFile(path + "/log.jrxml");
          }else{
                  JasperCompileManager.compileReportToFile(path + "\\log.jrxml");
          }
             

         

           

          
          OutputStream ouputStream = response.getOutputStream();
           response.setContentType("application/pdf");
System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        
HashMap map = new HashMap();


      List<Item> log=(List<Item>)session1.getAttribute("search_institute_resultset1");
 


dataSource = new JRBeanCollectionDataSource(log);
   if(os.startsWith("Linux"))
   {
JasperFillManager.fillReportToFile(path+"/log.jasper", map,dataSource );
   }else{
   JasperFillManager.fillReportToFile(path+"\\log.jasper", map,dataSource );
   }


System.out.println("End");




   System.out.println("Filling report..."+dataSource);
File file1;
  if(os.startsWith("Linux"))
   {
 file1 = new File(path + "/" +
                                            "log.jrprint");
  }else{
 file1 = new File(path + "\\" +
                                            "log.jrprint");

  }
         System.out.println("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");
      JasperPrint jasperPrint1 = (JasperPrint)JRLoader.loadObject(file1);
     
       JRPdfExporter pdfExporter = new JRPdfExporter();
   pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint1);

  if(os.startsWith("Linux"))
   {

            pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                     path + "/" + "log.pdf");
  }else{
            pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                     path + "\\" + "log.pdf");

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
