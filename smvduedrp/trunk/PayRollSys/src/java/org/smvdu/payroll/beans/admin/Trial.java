/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.admin;

import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.smvdu.payroll.beans.db.CommonDB;

/**
 *
 * @author Algox
 */
public class Trial {

    JasperPrint jasperPrint;
    public void dataOut()
{
try
{

Connection con1=new CommonDB().getConnection();
JasperReport jasperReport = JasperCompileManager.compileReport("report1.jrxml");
jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(),con1);
JasperExportManager.exportReportToPdfFile(jasperPrint,"./salary.pdf");
}catch(Exception e){
    e.printStackTrace();
    System.out.println(System.getProperty("user.dir"));
}
}

}
