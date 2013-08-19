/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.pf.ReportGen;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.user.OrgLogoDB;
import net.sf.jasperreports.engine.JasperCompileManager;

/**
 *
 * @author ERP
 */
public class EMployeeMonthSlipPDF {

    private Connection cn;
    private PreparedStatement pst;
    private ResultSet rst;
    /** Creates a new instance of EMployeeMonthSlipPDF */
    public EMployeeMonthSlipPDF() {
    }
    public void MonthlySlipOfEmployee()
    {
        try{
            //JasperToXml abc=new JasperToXml();
            //abc.jasperfileresult();
            cn = new CommonDB().getConnection();
            HashMap map = new HashMap();
            UserInfo ub = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            map.put("org_name", ub.getOrgName());
            map.put("month", ub.getCurrentMonth());
            map.put("year", ub.getCurrentYear());
            Image img = new OrgLogoDB().loadLogoImage();
            map.put("org_logo", img);
            map.put("org_title", "Salary Slip for the Month of " + ub.getCurrentMonthName());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            JasperPrint jasperPrint = JasperFillManager.fillReport(path + File.separator + "JasperFile/salaryslip.jasper", map, cn);
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            //InputStream reportStream = facesContext.getExternalContext().getResourceAsStream("JasperFile/salaryslip.jasper");
            ServletOutputStream servletOutputStream = response.getOutputStream();
            facesContext.responseComplete();
            JRAbstractExporter exporter1 = new JRPdfExporter();
            Map<JRExporterParameter, Object> parameterExport = new HashMap<JRExporterParameter, Object>();
            parameterExport.put(JRExporterParameter.JASPER_PRINT, jasperPrint);
            parameterExport.put(JRPdfExporterParameter.OUTPUT_STREAM, servletOutputStream);
            response.setContentType("application/pdf");
            exporter1.setParameters(parameterExport);
            exporter1.exportReport();
            servletOutputStream.close();
           
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
