/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.pf.ReportGen;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperRunManager;
import org.smvdu.payroll.api.pf.PFOpeningBalanceDB;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.user.OrgLogoDB;

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
        try
        {
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
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            InputStream reportStream = facesContext.getExternalContext().getResourceAsStream("JasperFile/salaryslip.jasper");
            InputStream reportStream1 = facesContext.getExternalContext().getResourceAsStream("JasperFile/salaryslip.jasper");
            ArrayList<InputStream> i =new ArrayList<InputStream>();

            ServletOutputStream servletOutputStream = response.getOutputStream();
            facesContext.responseComplete();
            response.setContentType("application/pdf");
            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, map, cn);
            servletOutputStream.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
