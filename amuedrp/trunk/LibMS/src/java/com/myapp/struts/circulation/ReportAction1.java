/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;
import com.myapp.struts.CirculationDAO.CirculationDAO;
import com.myapp.struts.circulation.CirCheckOutViewAllActionForm;
import com.myapp.struts.hbm.HibernateUtil;
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
import net.sf.jasperreports.engine.data.ListOfArrayDataSource;

import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRHibernateListDataSource;




/**
 *
 * @author edrp02
 */
public class ReportAction1 extends org.apache.struts.action.Action {

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
             String library_id,sublibrary_id;
        String path = servlet.getServletContext().getRealPath("/");

path=path+"/JasperReport";
System.out.println(path+" .....");
        try
        {
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
              CirCheckOutViewAllActionForm ccvaaf = (CirCheckOutViewAllActionForm) form;
       String starting_date=ccvaaf.getStarting_date();
      String  end_date=ccvaaf.getEnd_date();
     String   memid=ccvaaf.getMemid();
 HttpSession session1=request.getSession();
  library_id=(String)session1.getAttribute("library_id");
        sublibrary_id=(String)session1.getAttribute("sublibrary_id");

   List cir_checkout_report=CirculationDAO.CheckoutReport1(library_id,sublibrary_id, starting_date,end_date, memid);
    //   System.out.println("@@@@@@@@"+cir_checkout_report.size());
        if(cir_checkout_report!=null)
        {
System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
          session1.setAttribute("cir_checkout_report",cir_checkout_report);
            }
          System.out.println("Compiling report...");
          JasperCompileManager.compileReportToFile(path + "/CirCheckOut.jrxml");
          System.out.println("Done!");
          OutputStream ouputStream = response.getOutputStream();
           response.setContentType("application/pdf");


 JRBeanCollectionDataSource dataSource;
 JRHibernateListDataSource ds;
// System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+cir_checkout_report.get(j).toString());
 HashMap map = new HashMap();
 dataSource = new JRBeanCollectionDataSource(cir_checkout_report);

          JasperFillManager.fillReportToFile(path+"/CirCheckOut.jasper",map, dataSource);
           System.out.println("Filling report...");

          System.out.println("Done!");
          File file = new File(path + "/" +
                                              "CirCheckOut.jrprint");
          JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(file);
          JRPdfExporter pdfExporter = new JRPdfExporter();
          pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	  pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                     path + "/" + "CirCheckOut.pdf");
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
          e.printStackTrace();
        }
        return null;
    }
}
