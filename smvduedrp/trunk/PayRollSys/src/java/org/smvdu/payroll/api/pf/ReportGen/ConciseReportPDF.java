/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.pf.ReportGen;

import java.awt.Image;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class ConciseReportPDF {

    private ResultSet rst;
    private Connection cn;
    /** Creates a new instance of ConciseReportPDF */
    public ConciseReportPDF() {
    }
    public void AnnualConcise()
    {
       try
       {
           int sessionID = 0;
            
            cn = new CommonDB().getConnection();
            HashMap map = new HashMap();
            PreparedStatement pst = cn.prepareStatement("select ss_id from session_master where ss_current =1");
            rst = pst.executeQuery();
            if(rst.next())
                {
                    sessionID = rst.getInt(1);
                }





            UserInfo ub = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            map.put("org_name", ub.getOrgName());
            map.put("title", "Annual PF Report ");
            map.put("month", ub.getCurrentMonth());
            map.put("year", ub.getCurrentYear());
            Image img = new OrgLogoDB().loadLogoImage();
            map.put("org_logo", img);
            map.put("fin_session", sessionID);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            InputStream reportStream = facesContext.getExternalContext().getResourceAsStream("JasperFile/pfReport.jasper");
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
