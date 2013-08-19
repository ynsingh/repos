/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.pf.ReportGen;

import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.smvdu.payroll.user.OrgLogoDB;
import java.sql.Connection;
import java.util.HashMap;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.composite.ReportController;
import org.smvdu.payroll.beans.db.CommonDB;

/**
 *
 * @author ERP
 */
public class MonthlyRoll {

    /** Creates a new instance of MonthlyRoll */
    public MonthlyRoll() {
    }
    Connection cn;
    private PreparedStatement pst;
    private ResultSet rst;

    public void annualPFExcel() {
        try {
            //int sessionID = 0;
            cn = new CommonDB().getConnection();
            HashMap map = new HashMap();
            UserInfo ui = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            map.put("org_name", ui.getOrgName());
            Image img = new OrgLogoDB().loadLogoImage();
            map.put("org_logo", img);
            map.put("month", ui.getCurrentMonth());
            map.put("year", ui.getCurrentYear());
            map.put("org_title", "Salary Slip for the Month of " + ui.getCurrentMonthName());
            JasperPrint jasperPrint = new ReportController().getMonthlyPyroll();
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
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
