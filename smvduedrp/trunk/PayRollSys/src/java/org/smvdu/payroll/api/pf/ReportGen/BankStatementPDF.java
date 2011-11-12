/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.pf.ReportGen;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperRunManager;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;
/**
 *
 * @author ERP
 */
public class BankStatementPDF {

    /** Creates a new instance of BankStatementPDF */
    public BankStatementPDF()
    {
    }
    public void BankStatement()
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            UserInfo ub = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            HashMap map = new HashMap();
            map.put("org_name", ub.getOrgName());
            map.put("title", "Bank Statement for the Month of " +ub.getCurrentMonthName());
            map.put("month", ub.getCurrentMonth());
            map.put("year", ub.getCurrentYear());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            InputStream reportStream = facesContext.getExternalContext().getResourceAsStream("JasperFile/bankReport.jasper");
            ServletOutputStream servletOutputStream = response.getOutputStream();
            facesContext.responseComplete();
            response.setContentType("application/pdf");
            //response.setContentLength(("JasperFile/AnnualPFReport.jasper").length());
            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, map, cn);
            servletOutputStream.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
