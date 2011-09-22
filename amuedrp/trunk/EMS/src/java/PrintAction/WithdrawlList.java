/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PrintAction;

import com.myapp.struts.Candidate.CandidateRegistrationDAO;
import com.myapp.struts.hbm.ElectionManagerDAO;
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
 * @author Edrp-04
 */
public class WithdrawlList extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
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
         HttpSession session = request.getSession();
 CandidateRegistrationDAO candidatedao= new CandidateRegistrationDAO();
 ElectionManagerDAO dao=new ElectionManagerDAO();
        String path = servlet.getServletContext().getRealPath("/");
         JasperCompileManager.compileReportToFile(path+"/reports/Withdrawl.jrxml");
 String institute_id=(String)session.getAttribute("institute_id");
 String status="Withdraw";
         List     list=dao.WithdrawlReport(institute_id);

         System.out.println(list.size());
         JRBeanCollectionDataSource data=new  JRBeanCollectionDataSource(list);

          ServletOutputStream ouputStream = response.getOutputStream();
           response.setContentType("application/pdf");

         HashMap hash= new HashMap();
//         hash.put("image",list.get(11));




         JasperFillManager.fillReportToFile(path+"/reports/Withdrawl.jasper",hash,data);

         File file= new File(path+"/reports/Withdrawl.jrprint");

         JasperPrint print =(JasperPrint)JRLoader.loadObject(file);

         JRPdfExporter pdf=new JRPdfExporter();

         pdf.setParameter(JRExporterParameter.JASPER_PRINT, print);
         pdf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path+"/reports/Withdrawl.pdf");

         pdf.exportReport();
JRExporter exporter = null;
                exporter = new JRHtmlExporter();
JasperExportManager.exportReportToPdfStream(print, ouputStream);
        return mapping.findForward(SUCCESS);
    }
}
