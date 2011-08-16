/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;

import com.myapp.struts.AdminDAO.LogsDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import java.sql.*;
import java.io.*;
import java.util.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;




/**
 *
 * @author edrp02
 */
public class ReportAction4 extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    public static final String REPORT_DIRECTORY = "reports";
    Connection connection=null;
    Statement statement=null;
    ResultSet resultSet=null;
  


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            List list=null;
            ResultSet rs=null;
           

            JRDataSource simpleDS;
            String path = servlet.getServletContext().getRealPath("/");
            Connection con=null;
            ResultSet rs1=null;
            HashMap SIMPLE_DATA;
            int sum=0;
            try
            {
            ReportActionForm af = (ReportActionForm) form;
          
            System.out.println("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
            HashMap map = new HashMap();
List<Log1> log=LogsDAO.getUserLogChart();
for(int i=0;i<log.size();i++)
    System.out.println(log.get(i).getLibrary_id()+" "+log.get(i).getCount1());

         
JRBeanCollectionDataSource dataSource;

JasperCompileManager.compileReportToFile(path + "/logs.jrxml");
System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
 dataSource = new JRBeanCollectionDataSource(log);
          JasperFillManager.fillReportToFile(path+"/logs.jasper",map, dataSource);



            

            System.out.println("a******************************************************");

            
            System.out.println("4444444444444444444444444444444444444444444444444444444444444444444");


            
            System.out.println("Done!");

            OutputStream ouputStream = response.getOutputStream();
            response.setContentType("application/pdf");
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
              System.out.println("Done!");
           
            System.out.println("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");
            File file1 = new File(path + "/" + "logs.jrprint");
            System.out.println("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");
           
            System.out.println("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");
          
            JasperPrint jasperPrint1 = (JasperPrint)JRLoader.loadObject(file1);
         
            JRPdfExporter pdfExporter = new JRPdfExporter();
            System.out.println("ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
          
            pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint1);
           
            pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,path + "/" + "logs.pdf");
            System.out.println("crrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
           
            System.out.println("Exporting report...");
            pdfExporter.exportReport();
            System.out.println("Done!");
            JRExporter exporter = null;


            exporter = new JRHtmlExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint1);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
            JasperExportManager.exportReportToPdfStream(jasperPrint1, ouputStream);
          
            }
            catch (JRException e)
            {
            e.printStackTrace();
            }
            return null;
    }
}
