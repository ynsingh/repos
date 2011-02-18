/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import java.util.Date;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Algox
 */
public class DJTrial {

    public static void main(String args[])
    {
        try
        {
                FastReportBuilder drb = new FastReportBuilder();
                DynamicReport dr = drb.addColumn("State", "state", String.class.getName(),30)
                .addColumn("Branch", "branch", String.class.getName(),30)
                .addColumn("Product Line", "productLine", String.class.getName(),50)
                .addColumn("Item", "item", String.class.getName(),50)
                .addColumn("Item Code", "id", Long.class.getName(),30,true)
                .addColumn("Quantity", "quantity", Long.class.getName(),60,true)
                .addColumn("Amount", "amount", Float.class.getName(),70,true)
                .addGroups(2)
                .setTitle("November 2006 sales report")
                .setSubtitle("This report was generated at " + new Date())
                .setPrintBackgroundOnOddRows(true)
                .setUseFullPageWidth(true)
                .build();

                JRDataSource ds = new JREmptyDataSource();
                JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);
                JasperViewer.viewReport(jp);    //finally display the report report
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
