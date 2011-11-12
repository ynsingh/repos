/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.admin;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
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
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
 */
public class ConsolidatedMonthlySalaryUtil{

    private FastReportBuilder drb = new FastReportBuilder();
    private DynamicReport report;
    private DefaultTableModel model;
    public  void buildReport(DefaultTableModel model,String mTitle) throws Exception {

/**
* Creates the DynamicReportBuilder and sets the basic options for
* the report
*/
this.model = model;
Style columDetail = new Style();
columDetail.setBorder(Border.THIN);
Style columDetailWhite = new Style();
columDetailWhite.setBorder(Border.THIN);
columDetailWhite.setBackgroundColor(Color.WHITE);
Style columDetailWhiteBold = new Style();
columDetailWhiteBold.setBorder(Border.THIN);
columDetailWhiteBold.setBackgroundColor(Color.WHITE);
Style titleStyle = new Style();
titleStyle.setFont(new Font(12,Font._FONT_VERDANA,true));
Style numberStyle = new Style();
numberStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
Style amountStyle = new Style();
amountStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
amountStyle.setBackgroundColor(Color.cyan);
amountStyle.setTransparency(Transparency.OPAQUE);
Style oddRowStyle = new Style();

oddRowStyle.setBorder(Border.NO_BORDER);
Color veryLightGrey = new Color(230,230,230);
oddRowStyle.setBackgroundColor(veryLightGrey);oddRowStyle.setTransparency(Transparency.OPAQUE);


// table name column
String[] headings;
int colCount = model.getColumnCount();
headings = new String[colCount];
for(int i=0;i<colCount;i++)
{
    
      headings[i]=model.getColumnName(i);
    
}

int colwidth = 2;
for(int i=0;i<headings.length;i++){
String key=headings[i];
if(key.length()==1||key.length()==2)
{
    colwidth =1;
}
else
{
    colwidth=key.length();
}
AbstractColumn column = ColumnBuilder.getInstance().setColumnProperty(key , String.class.getName())
.setTitle(key).setWidth(new Integer(colwidth))
.setStyle(columDetailWhite).build();
drb.addColumn(column);

}
drb.setTitle(mTitle)
.setTitleStyle(titleStyle).setTitleHeight(new Integer(30))
.setSubtitleHeight(new Integer(20))
.setDetailHeight(new Integer(15))
.setPageSizeAndOrientation(Page.Page_A4_Landscape())
//.setLeftMargin(margin)
//.setRightMargin(margin)
//.setTopMargin(margin)
// .setBottomMargin(margin)
.setPrintBackgroundOnOddRows(true)
.setOddRowBackgroundStyle(oddRowStyle)
.setColumnsPerPage(new Integer(1))

.setUseFullPageWidth(true)
.setColumnSpace(new Integer(5));
 report = drb.build();


//return dr;
}

    public void print()
    {
        try
        {
        JRDataSource ds = new JRTableModelDataSource(model);
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(report, new ClassicLayoutManager(), ds);
        JasperViewer jv = new JasperViewer(jp, false);
        jv.setTitle("Consolidated Monthly Salary Report");
        jv.setVisible(true);
        }
        catch(Exception e)
        {

        }
    }

}
