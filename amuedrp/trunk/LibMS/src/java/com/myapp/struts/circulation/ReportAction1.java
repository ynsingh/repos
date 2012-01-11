/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;
import com.myapp.struts.CirDAO.CirculationDAO;
import com.myapp.struts.circulation.CirCheckOutViewAllActionForm;
import com.myapp.struts.hbm.HibernateUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


import java.sql.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.data.ListOfArrayDataSource;
import net.sf.jasperreports.engine.data.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRDataSource;
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
    private JRBeanCollectionDataSource dataSource1;
    private CirculationList cir_checkout_report1;
     private CirculationList image1;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            List list=null;
             String library_id,sublibrary_id;
        String path = servlet.getServletContext().getRealPath("/");
String os=(String)System.getProperty("os.name");
   System.out.println("OS----------->"+os);
   if(os.startsWith("Linux"))
   {
path=path+"/JasperReport";
   }else{
   path=path+"\\JasperReport";
   }
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
            if(os.startsWith("Linux"))
   {
          JasperCompileManager.compileReportToFile(path + "/CirCheckOut.jrxml");
            }else{
            JasperCompileManager.compileReportToFile(path + "\\CirCheckOut.jrxml");
            }
        //   JasperCompileManager.compileReportToFile(path + "/CirCheckoutSubReport.jrxml");
          System.out.println("Done!");
          OutputStream ouputStream = response.getOutputStream();
           response.setContentType("application/pdf");


 JRBeanCollectionDataSource dataSource;
 JRHibernateListDataSource ds;
// System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+cir_checkout_report.get(j).toString());
 HashMap map = new HashMap();
 if(!memid.isEmpty())
 map.put("memid1",memid);
 else
     map.put("memid1", "Not Specified");
 if(!starting_date.isEmpty())
 map.put("StartingDate",starting_date);
 else
     map.put("StartingDate", "Not Specified");
 if(!end_date.isEmpty())
 map.put("EndDate",end_date);
 else
     map.put("EndDate", "Not Specified");
 map.put("library_name",library_id);
 map.put("username",(String)session1.getAttribute("username"));
     Calendar currentDate = Calendar.getInstance();
  SimpleDateFormat formatter=   new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
  String dateNow = formatter.format(currentDate.getTime());

     map.put("systemdate", dateNow);
 

 //dataSource = new JRBeanCollectionDataSource(cir_checkout_report);
 //to fill sub report
 dataSource1 = new JRBeanCollectionDataSource(cir_checkout_report);
 HashMap  SIMPLE_DATA = new HashMap();
  SIMPLE_DATA.put("datasource1", dataSource1);
 // below codes are commmented

// SIMPLE_DATA.put("datasource", dataSource);
 //Map simpleMasterMap = new HashMap();
//simpleMasterMap.put("id3", "datasource");

// List simpleMasterList = new ArrayList();
// simpleMasterList.add(simpleMasterMap);

//JRDataSource simpleDS = new JRBeanCollectionDataSource(simpleMasterList);

 // map.put("SIMPLE_DATA", SIMPLE_DATA);

  // Below code is not comemted

   List simpleMasterList = new ArrayList();
     simpleMasterList.add(dataSource1);


//   Iterator it = cir_checkout_report.iterator();
//   int i=cir_checkout_report.size();
//   for(int ii=0;ii<i;ii++)
//   {
//         CirculationList  doc = (CirculationList)cir_checkout_report.get(ii);
//          if(doc.getMemid().equals(memid))
//          {
//                 cir_checkout_report1  =doc;
//                  map.put("cir_checkout_report1", cir_checkout_report1);
//                  // map.put("image1", image1);
//          }
//         it.next();
//
//   }
  map.put("cir_checkout_report", cir_checkout_report);
  if(os.equals("Linux")){
            JasperFillManager.fillReportToFile(path+"/CirCheckOut.jasper",map, dataSource1);
  }else{
            JasperFillManager.fillReportToFile(path+"\\CirCheckOut.jasper",map, dataSource1);
  }
           // JasperFillManager.fillReportToFile(path+"/CirCheckoutSubReport.jasper",map, dataSource1);
         // JasperFillManager.fillReportToFile(path+"/CirCheckOut.jasper",map, simpleDS);
           System.out.println("Filling report...");

          System.out.println("Done!");

          File file;
          if(os.equals("Linux")){
          file= new File(path + "/" +
                                              "CirCheckOut.jrprint");
          }else{
          file= new File(path + "\\" +
                                              "CirCheckOut.jrprint");
          }
          JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(file);
          JRPdfExporter pdfExporter = new JRPdfExporter();
          pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
          if(os.equals("Linux")){
	  pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                     path + "/" + "CirCheckOut.pdf");
          }else{
          pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                     path + "\\" + "CirCheckOut.pdf");

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
          e.printStackTrace();
        }
        return null;
    }
}
