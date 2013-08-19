/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.pf.ReportGen;
import java.awt.Image;
import net.sf.jasperreports.engine.JasperRunManager;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
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
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.user.OrgLogoDB;
/**
 *
 * @author Dhruv Mahajan
 */
public class TaxReportGen {

    /** Creates a new instance of TaxReportGen */
    public TaxReportGen() {
    }

    public String taxReport()
    {
        try
        {
            return "TaxReport.jsf";
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    public String taxReportAsHtml()
    {
        try
        {
            return "TaxReportAsHtml.jsf";
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    public String employeeWiseAsHtml()
    {
        try
        {
            return "TaxReportAsHtml.jsf";
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
 public void employeeWiseAsPdf()
    {

  try
        {
            Connection cn = new CommonDB().getConnection();
            UserInfo ub = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            HashMap map = new HashMap();
            map.put("org_name", ub.getOrgName());
            map.put("org_title", "Tax Report");
            map.put("year","" + ub.getCurrentYear());
            Image img = new OrgLogoDB().loadLogoImage();
            map.put("org_logo",img);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
             String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            JasperPrint jasperPrint = JasperFillManager.fillReport(path+File.separator+"JasperFile/investment.jasper", map, cn);
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
 public void employeeWiseExcel()
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            UserInfo ub = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            HashMap map = new HashMap();
            map.put("org_name", ub.getOrgName());
            map.put("title", "Employee Wise Investments");
            map.put("year",""+ ub.getCurrentYear());
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            JasperPrint jasperPrint = JasperFillManager.fillReport(path+File.separator+"JasperFile/investment.jasper", map, cn);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            ServletOutputStream servletOutputStream = response.getOutputStream();
            facesContext.responseComplete();
            response.setContentType("application/ms-excel");
            JRXlsExporter exporterXLS = new JRXlsExporter();
            exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, servletOutputStream);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, Boolean.TRUE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporterXLS.exportReport();
            servletOutputStream.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }}}



