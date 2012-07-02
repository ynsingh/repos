/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import com.myapp.struts.hbm.DocumentDetails;
import com.myapp.struts.utility.AppPath;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp02
 */
public class CatalogueCardEntryAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";


    String delimiter= ",";
    String items[];
    catalogue dd[]=new catalogue[100];
    
    String msg=" ";
    int i;
    BibliopgraphicEntryDAO dao=new BibliopgraphicEntryDAO();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BarcodeEntryActionForm beaf=(BarcodeEntryActionForm)form;
        String accession_no=beaf.getAccession_no();
        String list=beaf.getList();
        String cardName=beaf.getCard();
        List<catalogue> list2=new ArrayList();
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String path = AppPath.getReportPath();
        if(accession_no=="")
        {
          request.setAttribute("msgg","Please Enter List");
          return mapping.findForward(SUCCESS);
        }
        items= accession_no.split(delimiter);
        for(i=0;i<items.length;i++)
        {
          String acc_no=items[i];
          DocumentDetails docdetails=BibliopgraphicEntryDAO.searchBook(acc_no, library_id, sub_library_id) ;
          if(docdetails!=null)
          {
          int doc_id1=docdetails.getId().getDocumentId();
          System.out.println("aqeeeeeeeeeeeeeeel"+doc_id1);
          dd[i]=dao.searchDoc4(doc_id1, library_id, sub_library_id);
          System.out.println(dd[i]+String.valueOf(doc_id1)+library_id+sub_library_id);
          }
        
          if(docdetails==null)
          {
             if(msg==" ")
               msg=items[i];
             else
               msg=msg+","+items[i] ;

          }
        }

        if(msg!=" ")
        {
          request.setAttribute("msg",msg);
          request.setAttribute("list", list);
          request.setAttribute("accession_no", accession_no);
          msg=" ";
          return mapping.findForward(SUCCESS);
        }

         for(i=0;i<items.length;i=i+1)
         {   list2.add(dd[i]);
         System.out.println(dd[i]);
         }


        
     if(cardName==null)
     {
       request.setAttribute("msgg","Select Card Type");
        request.setAttribute("accession_no", accession_no);
       return mapping.findForward(SUCCESS);
     }

            
            JRBeanCollectionDataSource dataSource;
            HashMap map = new HashMap();
            map.put("bb",list2);
            dataSource = new JRBeanCollectionDataSource(list2);


      if(cardName.equalsIgnoreCase("maincard"))
      {
            JasperCompileManager.compileReportToFile(path+ "subreport1.jrxml");
            JasperFillManager.fillReportToFile(path+"subreport1.jasper",map,dataSource);
            File file = new File(path +"subreport1.jrprint");
            JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(file);
            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	    pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                     path +  "subreport1.pdf");
            pdfExporter.exportReport();
            JRExporter exporter = null;
            exporter = new JRHtmlExporter();
            OutputStream ouputStream = response.getOutputStream();
            response.setContentType("application/pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);
      }

      if(cardName.equalsIgnoreCase("titlecard"))
      {
            JasperCompileManager.compileReportToFile(path+ "subreport2.jrxml");
            JasperFillManager.fillReportToFile(path+"subreport2.jasper",map,dataSource);
            File file = new File(path + "subreport2.jrprint");
            JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(file);
            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	    pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                     path + "subreport2.pdf");
            pdfExporter.exportReport();
            System.out.println("Done!");
            JRExporter exporter = null;
            exporter = new JRHtmlExporter();
            OutputStream ouputStream = response.getOutputStream();
            response.setContentType("application/pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);
      }
      if(cardName.equalsIgnoreCase("subjectcard"))
      {
            JasperCompileManager.compileReportToFile(path + "subreport4.jrxml");
            JasperFillManager.fillReportToFile(path+"subreport4.jasper",map,dataSource);
            File file = new File(path +"subreport4.jrprint");
            JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(file);
            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	    pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                     path + "subreport4.pdf");
	    System.out.println("Exporting report...");
            pdfExporter.exportReport();
            System.out.println("Done!");
            JRExporter exporter = null;
            exporter = new JRHtmlExporter();
            OutputStream ouputStream = response.getOutputStream();
            response.setContentType("application/pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);
      }

       if(cardName.equalsIgnoreCase("scard"))
       {
            JasperCompileManager.compileReportToFile(path + "subreport5.jrxml");
            JasperFillManager.fillReportToFile(path+"subreport5.jasper",map,dataSource);


            System.out.println("Done!");
            File file = new File(path + "subreport5.jrprint");
            JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(file);
            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	    pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                     path + "subreport5.pdf");
            pdfExporter.exportReport();
            JRExporter exporter = null;
            exporter = new JRHtmlExporter();
            OutputStream ouputStream = response.getOutputStream();
            response.setContentType("application/pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);
      }


      if(cardName.equalsIgnoreCase("allcard"))
      {
            JasperCompileManager.compileReportToFile(path+ "mainreport1.jrxml");
            JasperCompileManager.compileReportToFile(path+ "subreport111.jrxml");
            JasperCompileManager.compileReportToFile(path+ "subreport211.jrxml");
            JasperCompileManager.compileReportToFile(path + "subreport41.jrxml");
            JasperCompileManager.compileReportToFile(path + "subreport51.jrxml");
            JasperFillManager.fillReportToFile(path+"mainreport1.jasper",map, new JREmptyDataSource());
            File file = new File(path + "mainreport1.jrprint");
            JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(file);
            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	    pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                     path +  "mainreport1.pdf");
            pdfExporter.exportReport();
            JRExporter exporter = null;
            exporter = new JRHtmlExporter();
            OutputStream ouputStream = response.getOutputStream();
            response.setContentType("application/pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);
      }
        request.setAttribute("msgg","Select Card Type");
        request.setAttribute("accession_no", accession_no);
        return mapping.findForward(SUCCESS);
    }
}
