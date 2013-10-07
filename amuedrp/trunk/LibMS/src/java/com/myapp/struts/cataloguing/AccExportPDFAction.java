/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.BibliographicEntryDAO;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.DocumentDetails;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
 * @author edrp-03
 */
public class AccExportPDFAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String library_id,sub_library_id,user_id;
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
       HttpSession session=request.getSession();
         BibliographicDetailEntryActionForm1 bd = (BibliographicDetailEntryActionForm1) form;
         BibliographicEntryDAO dao=new BibliographicEntryDAO();

        library_id=(String)session.getAttribute("library_id");
        sub_library_id=(String)session.getAttribute("sublibrary_id");
        user_id=(String)session.getAttribute("login_id");
	String doc_type=bd.getDocument_type();
        String search_by = bd.getSearch_by();
        String sort_by = bd.getSort_by();
        String search_keyword = bd.getSearch_keyword();
           int pageno=Integer.parseInt((String)(request.getParameter("page")==null || request.getParameter("page").isEmpty() ?"0":request.getParameter("page")));
           List<DocumentDetails> rst = dao.getAccession(library_id, sub_library_id,search_by,search_keyword, sort_by,pageno,doc_type);


            String path = servlet.getServletContext().getRealPath("/");
         JasperCompileManager.compileReportToFile(path+"/JasperReport/AccDetail.jrxml");



         JRBeanCollectionDataSource data=new  JRBeanCollectionDataSource(rst);

          ServletOutputStream ouputStream = response.getOutputStream();

          response.setContentType("application/pdf");

         HashMap hash= new HashMap();





         JasperFillManager.fillReportToFile(path+"/JasperReport/AccDetail.jasper",hash,data);

         File file= new File(path+"/JasperReport/AccDetail.jrprint");

         JasperPrint print =(JasperPrint)JRLoader.loadObject(file);

         JRPdfExporter pdf=new JRPdfExporter();

         pdf.setParameter(JRExporterParameter.JASPER_PRINT, print);

         pdf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path+"/JasperReport/AccDetail.pdf");

         pdf.exportReport();
           JRExporter exporter = null;

           exporter = new JRHtmlExporter();

           JasperExportManager.exportReportToPdfStream(print, ouputStream);


        return mapping.findForward(SUCCESS);
    }
}
