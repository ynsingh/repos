/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.pf.ReportGen;
  
import java.io.File; 
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;

/**
 *
 *  Copyright (c) 2010, 2011, 2014 SMVDU Katra.
 *  Copyright (c) 2014 - 2016 ETRG, IITK.
 *  All Rights Reserved.
 ** Redistribution and use in source and binary forms, with or 
 *  without modification, are permitted provided that the following 
 *  conditions are met: 
 ** Redistributions of source code must retain the above copyright 
 *  notice, this  list of conditions and the following disclaimer. 
 * 
 *  Redistribution in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in 
 *  the documentation and/or other materials provided with the 
 *  distribution. 
 * 
 * 
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 *  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 * 
 *
 * @author ERP
 * Modification date 14 July 2016,  Om Prakash<omprakashkgp@gmail.com> , IITK  
 
 */
public class BankStatementPDF {

    /** Creates a new instance of BankStatementPDF */
    public BankStatementPDF() {
    }

    public void BankStatement() {
        try
        {
            Connection cn = new CommonDB().getConnection();
            UserInfo ub = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            HashMap map = new HashMap();
            map.put("org_name", ub.getOrgName());
            map.put("title", "Bank Statement for the Month of " + ub.getCurrentMonthName());
            map.put("month", ub.getCurrentMonth());
            map.put("year", ub.getCurrentYear());
            map.put("emp_bank_name",ub.getBankName());
            map.put("org_id", ub.getUserOrgCode());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            //HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            JasperPrint jasperPrint = JasperFillManager.fillReport(path + File.separator + "JasperFile/bankReport.jasper", map, cn);
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void bankExcelSheet() {
        try {
            Connection cn = new CommonDB().getConnection();
            UserInfo ub = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            HashMap map = new HashMap();
            map.put("org_name", ub.getOrgName());
            map.put("title", "Bank Statement for the Month of " + ub.getCurrentMonthName());
            map.put("month", ub.getCurrentMonth());
            map.put("year", ub.getCurrentYear());
            map.put("emp_bank_name",ub.getBankName());
            map.put("org_id", ub.getUserOrgCode());
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            JasperPrint jasperPrint = JasperFillManager.fillReport(path + File.separator + "JasperFile/bankReport.jasper", map, cn);
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
