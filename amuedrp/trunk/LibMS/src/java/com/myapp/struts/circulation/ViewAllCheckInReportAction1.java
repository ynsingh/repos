/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;
import com.myapp.struts.CirDAO.CirculationDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.sql.*;
import java.io.*;
import java.util.*;

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
 * @author edrp02
 */
public class ViewAllCheckInReportAction1 extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
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
             String library_id;
        String path = servlet.getServletContext().getRealPath("/");
        String os=(String)System.getProperty("os.name");
   System.out.println("OS----------->"+os);
   if(os.startsWith("Linux"))
   {

path=path+"/JasperReport";
   }else{
       path=path+"\\JasperReport";
   }
        try
        {
             CirCheckInReportActionForm ccra   =(CirCheckInReportActionForm)form;
           HttpSession session=request.getSession();
             library_id=(String)session.getAttribute("library_id");
            String  sub_lib=(String)session.getAttribute("sublibrary_id");
            String memid =ccra.getMemid();

           String year1=ccra.getStarting_date();
            String year2=ccra.getEnd_date();

//List circheckInlist1=(List)CirculationDAO.CheckoutReport1(library_id,sub_lib, year1,year2, memid);
        List circheckInlist1=(List)CirculationDAO.CheckInReport1(library_id, sub_lib, year1, year2, memid);
        // session.setAttribute("circheckInlist1", circheckInlist1);
       // System.out.println("@@@@@@@@@@@@+///////////////////////////////////////////////////////////////////////////////////////"+circheckInlist1.size());
        //return mapping.findForward(SUCCESS);
 System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
             
      
  
    System.out.println("Compiling report...");
     if(os.startsWith("Linux"))
   {
          JasperCompileManager.compileReportToFile(path + "/CirCheckIn.jrxml");
     }else{
          JasperCompileManager.compileReportToFile(path + "\\CirCheckIn.jrxml");
     }
          System.out.println("Done!");
          OutputStream ouputStream = response.getOutputStream();
           response.setContentType("application/pdf");


 JRBeanCollectionDataSource dataSource;

// System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+cir_checkout_report.get(j).toString());
 HashMap map = new HashMap();
  map.put("library_name",(String)session.getAttribute("library_name"));

     map.put("user_id",(String)session.getAttribute("username"));
     dataSource = new JRBeanCollectionDataSource(circheckInlist1);
    

 
     if(os.startsWith("Linux"))
   {
           JasperFillManager.fillReportToFile(path+"/CirCheckIn.jasper",map, dataSource);
     }else{
           JasperFillManager.fillReportToFile(path+"\\CirCheckIn.jasper",map, dataSource);
     }
           System.out.println("Filling report...");

          System.out.println("Done!");
          File file;

    if(os.startsWith("Linux"))
   {
          file= new File(path + "/" +
                                              "CirCheckIn.jrprint");
    }else{
          file= new File(path + "\\" +
                                              "CirCheckIn.jrprint");

    }
          JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(file);
          JRPdfExporter pdfExporter = new JRPdfExporter();
          pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
    if(os.startsWith("Linux"))
   {
	  pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                     path + "/" + "CirCheckIn.pdf");
    }else{
    	  pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                     path + "\\" + "CirCheckIn.pdf");
    }
	  System.out.println("Exporting report...");
          pdfExporter.exportReport();
          System.out.println("Done!");
 JRExporter exporter = null;
                exporter = new JRHtmlExporter();
JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);

            //  System.out.println(dataSource.getData().getClass());


        }
        catch (JRException e)
        {
            System.out.println("asssssssssssssssssssssssssaaaaaaaaaaaaaaaaaaaaaaaaaaassssssssssssssssssssss");
          e.printStackTrace();
        }
        return null;
    }
}
