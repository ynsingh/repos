/*
 * To VIEW ALL CANIDATE LIST in PDF
 */

package PrintAction;

import com.myapp.struts.Candidate.CandidateRegistrationDAO;
import com.myapp.struts.hbm.ElectionManagerDAO;
import com.myapp.struts.utility.AppPath;
import com.myapp.struts.utility.LoggerUtils;
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
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Edrp-04
 */
public class AllCandiList extends org.apache.struts.action.Action {
     private static Logger log4j=LoggerUtils.getLogger();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try{
            HttpSession session = request.getSession();
            CandidateRegistrationDAO candidatedao= new CandidateRegistrationDAO();
            ElectionManagerDAO dao=new ElectionManagerDAO();
            JasperCompileManager.compileReportToFile(AppPath.getReportPath()+"AllCandidate.jrxml");
            String institute_id=(String)session.getAttribute("institute_id");
            String election_id=request.getParameter("election");
            List list=dao.AllCandiReport(institute_id,election_id);
            System.out.println("222222222222222222"+list.size());
            JRBeanCollectionDataSource data=new  JRBeanCollectionDataSource(list);
            ServletOutputStream ouputStream = response.getOutputStream();
            response.setContentType("application/pdf");
            HashMap hash= new HashMap();
            JasperFillManager.fillReportToFile(AppPath.getReportPath()+"AllCandidate.jasper",hash,data);
            File file= new File(AppPath.getReportPath()+"AllCandidate.jrprint");
            JasperPrint print =(JasperPrint)JRLoader.loadObject(file);
            JRPdfExporter pdf=new JRPdfExporter();
            pdf.setParameter(JRExporterParameter.JASPER_PRINT, print);
            pdf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, AppPath.getReportPath()+"AllCandidate.pdf");
            pdf.exportReport();
            JRExporter exporter = null;
            exporter = new JRHtmlExporter();
            JasperExportManager.exportReportToPdfStream(print, ouputStream);
            return mapping.findForward("success");
        }
        catch(Exception e)
        {
        log4j.error(e.toString());
        }
        return null;

    }
}
