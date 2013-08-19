/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.pf.ReportGen;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.*;
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
@ManagedBean
@RequestScoped

/**
 *
 * @author smvdu
 */
public class FormSixteen {

    /** Creates a new instance of FormSixteen */
    public FormSixteen() {
    }
public String formsixteentaxReport()
    {
        try
        {
            return "FinalTaxPayable.jsf";
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    public String formsixteentaxReportAsHtml()
    {
        try
        {
            System.out.println("abc");
            return "FinalTaxPayableAsHtml.jsf";
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
}

 public void formsixteentaxReportAsPdf()
    {
     try
     {
           Connection cn = (Connection) new CommonDB().getConnection();
            ResultSet rst;
            int ssCode = 0;
            PreparedStatement pst = cn.prepareStatement("select ss_id from session_master where ss_current =1");
            rst = pst.executeQuery();
            if (rst.next()) {
                ssCode = rst.getInt(1);
            }
            UserInfo ub = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            HashMap map = new HashMap();
            map.put("org_name", ub.getOrgName());
            map.put("title", "FORM 16");
            //map.put("month", ub.getCurrentMonth());
           // map.put("year", ub.getCurrentYear());
           map.put("session", String.valueOf(ssCode)); 
            FacesContext facesContext = FacesContext.getCurrentInstance();
            //HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            JasperPrint jasperPrint = JasperFillManager.fillReport(path + File.separator + "JasperFile/finaltax.jasper", map, cn);
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
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
            servletOutputStream.flush();
        }
 catch (Exception ex)
 {
            ex.printStackTrace();
        }
}


}
