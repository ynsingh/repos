/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import com.myapp.struts.hbm.DocumentDetails;
import java.awt.Color;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.components.barcode4j.Code39Component;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.krysalis.barcode4j.ChecksumMode;
import org.krysalis.barcode4j.HumanReadablePlacement;

/**
 *
 * @author edrp02
 */
public class BarcodeEntryAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    int i;
    String delimiter= ",";
    String items[];
    DocumentDetails dd[]=new DocumentDetails[100];
    String msg=" ";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BarcodeEntryActionForm beaf=(BarcodeEntryActionForm)form;
        String accession_no=beaf.getAccession_no();
        String list=beaf.getList();
        System.out.println("!!!!!!!!"+accession_no+"@@@@@@@@"+"list");
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        items= accession_no.split(delimiter);
        List<BooksBarcode> list2 = new ArrayList();

        for(i=0;i<items.length;i++)
        {
          String acc_no=items[i];
          System.out.println("aqeellllllll"+acc_no);
          dd[i]=BibliopgraphicEntryDAO.searchBook(acc_no, library_id, sub_library_id) ;
         // System.out.println("aqeellllllll"+dd[i].getAccessionNo());
          if(dd[i]==null)
          {
             if(msg==" ")
               msg=items[i];
             else
               msg=msg+","+items[i] ;

          }
        }

        if(msg!=" ")
        {
          request.setAttribute("msg",msg);
          request.setAttribute("list", list);
          request.setAttribute("accession_no", accession_no);
          msg=" ";
          return mapping.findForward(SUCCESS);
        }

        for(i=0;i<items.length;i=i+4)
        {
          BooksBarcode bb=new BooksBarcode();
          for(int j=i;j<4+i;j++)
          {
              int k=j%4;
              if(items.length>j){
              if(k==0)
              {  bb.setAccessionNo1(dd[j].getAccessionNo());
                 bb.setCallNo1(dd[j].getCallNo());
              }
              if(k==1)
              { bb.setAccessionNo2(dd[j].getAccessionNo());
                bb.setCallNo2(dd[j].getCallNo());
              }
              if(k==2)
              { bb.setAccessionNo3(dd[j].getAccessionNo());
                bb.setCallNo3(dd[j].getCallNo());
              }
              if(k==3)
              {  bb.setAccessionNo4(dd[j].getAccessionNo());
                 bb.setCallNo4(dd[j].getCallNo());
              }

              }
          }

          
          list2.add(bb);
        }


        String path = servlet.getServletContext().getRealPath("/");


        //Report Page Setting
        JasperDesign jasperDesign = new JasperDesign();
        jasperDesign.setName("bookbarcode");
        jasperDesign.setPageWidth(595);
        jasperDesign.setPageHeight(842);
        jasperDesign.setColumnWidth(515);
        jasperDesign.setColumnSpacing(0);
        jasperDesign.setLeftMargin(40);
        jasperDesign.setRightMargin(40);
        jasperDesign.setTopMargin(50);
        jasperDesign.setBottomMargin(50);


        //Fonts
        JRDesignStyle normalStyle = new JRDesignStyle();
        normalStyle.setName("Sans_Normal");
        normalStyle.setDefault(true);
      //  normalStyle.setFontName("DejaVu Sans");
        normalStyle.setFontSize(12);
     //   normalStyle.setPdfFontName("Helvetica");
        normalStyle.setPdfEncoding("Cp1252");
        normalStyle.setPdfEmbedded(false);
        jasperDesign.addStyle(normalStyle);

        JRDesignStyle boldStyle = new JRDesignStyle();
        boldStyle.setName("Sans_Bold");
    //    boldStyle.setFontName("DejaVu Sans");
        boldStyle.setFontSize(12);
        boldStyle.setBold(true);
     //   boldStyle.setPdfFontName("Helvetica-Bold");
        boldStyle.setPdfEncoding("Cp1252");
        boldStyle.setPdfEmbedded(false);
        jasperDesign.addStyle(boldStyle);

        JRDesignStyle italicStyle = new JRDesignStyle();
        italicStyle.setName("Sans_Italic");
    //    italicStyle.setFontName("DejaVu Sans");
        italicStyle.setFontSize(12);
        italicStyle.setItalic(true);
    //    italicStyle.setPdfFontName("Helvetica-Oblique");
        italicStyle.setPdfEncoding("Cp1252");
        italicStyle.setPdfEmbedded(false);
        jasperDesign.addStyle(italicStyle);

        //Fields
        JRDesignField  field = new JRDesignField();
        field.setName("title1");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("mainEntry1");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("isbn101");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("accessionNo1");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("callNo1");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("title2");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("mainEntry2");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("isbn102");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("accessionNo2");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("callNo2");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

         field = new JRDesignField();
        field.setName("title3");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("mainEntry3");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("isbn103");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("accessionNo3");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("callNo3");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

         field = new JRDesignField();
        field.setName("title4");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("mainEntry4");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("isbn104");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("accessionNo4");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("callNo4");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        //Detail Section
        JRDesignBand band = new JRDesignBand();
        band.setHeight(75);

        JRDesignComponentElement cdesign=new JRDesignComponentElement();
        JRDesignComponentElement cdesign1=new JRDesignComponentElement();
        JRDesignComponentElement cdesign2=new JRDesignComponentElement();
        JRDesignComponentElement cdesign3=new JRDesignComponentElement();

        JRDesignTextField    textField1 = new JRDesignTextField();
        textField1.setX(10);
        textField1.setY(0);
        textField1.setWidth(100);
        textField1.setHeight(10);
        textField1.setFontSize(5);
        textField1.setForecolor(new Color(0, 0, 0));
        textField1.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        textField1.setStyle(normalStyle);
        textField1.setBlankWhenNull(Boolean.TRUE);
        JRDesignExpression expression5 = new JRDesignExpression();
        expression5.setValueClass(java.lang.String.class);
        expression5.setText("$F{accessionNo1}");
        textField1.setExpression(expression5);
        band.addElement(textField1);

        textField1 = new JRDesignTextField();
        textField1.setX(10);
        textField1.setY(35);
        textField1.setWidth(100);
        textField1.setHeight(10);
        textField1.setFontSize(5);
        textField1.setForecolor(new Color(0, 0, 0));
        textField1.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        textField1.setStyle(normalStyle);
        textField1.setBlankWhenNull(Boolean.TRUE);
        expression5 = new JRDesignExpression();
        expression5.setValueClass(java.lang.String.class);
        expression5.setText("$F{callNo1}");
        textField1.setExpression(expression5);
        band.addElement(textField1);

        Code39Component barcode39=new Code39Component();
        barcode39.setExtendedCharSetEnabled(Boolean.TRUE);
        barcode39.setDisplayChecksum(Boolean.FALSE);
        barcode39.setDisplayStartStop(Boolean.TRUE);
        barcode39.setChecksumMode(ChecksumMode.CP_AUTO);
        barcode39.setTextPosition(HumanReadablePlacement.HRP_BOTTOM);
        barcode39.setEvaluationTimeValue(EvaluationTimeEnum.NOW);
        barcode39.setExtendedCharSetEnabled(Boolean.TRUE);
        JRDesignExpression expression=new JRDesignExpression();
        expression.setText("$F{accessionNo1}");
        expression.setValueClass(java.lang.String.class);
        barcode39.setCodeExpression(expression);
        cdesign.setComponent(barcode39);
        cdesign.setComponentKey(new ComponentKey(
                                        "http://jasperreports.sourceforge.net/jasperreports/components",
                                        "jr", "Code39"));
        cdesign.setX(0);
        cdesign.setY(5);
        cdesign.setWidth(220);
        cdesign.setHeight(40);

      
          
        JRDesignTextField    textField2 = new JRDesignTextField();
        textField2.setX(150);
        textField2.setY(0);
        textField2.setWidth(100);
        textField2.setHeight(10);
        textField2.setFontSize(5);
        textField2.setForecolor(new Color(0, 0, 0));
        textField2.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        textField2.setStyle(normalStyle);
        textField2.setBlankWhenNull(Boolean.TRUE);
        JRDesignExpression expression6 = new JRDesignExpression();
        expression6.setText("$F{accessionNo2}");
        expression6.setValueClass(java.lang.String.class);
        textField2.setExpression(expression6);
        band.addElement(textField2);

        textField2 = new JRDesignTextField();
        textField2.setX(150);
        textField2.setY(35);
        textField2.setWidth(100);
        textField2.setHeight(10);
        textField2.setFontSize(5);
        textField2.setForecolor(new Color(0, 0, 0));
        textField2.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        textField2.setStyle(normalStyle);
        textField2.setBlankWhenNull(Boolean.TRUE);
        expression6 = new JRDesignExpression();
        expression6.setText("$F{callNo2}");
        expression6.setValueClass(java.lang.String.class);
        textField2.setExpression(expression6);
        band.addElement(textField2);

        Code39Component barcode391=new Code39Component();
        barcode391.setExtendedCharSetEnabled(Boolean.TRUE);
        barcode391.setDisplayChecksum(Boolean.FALSE);
        barcode391.setDisplayStartStop(Boolean.TRUE);
        barcode391.setChecksumMode(ChecksumMode.CP_AUTO);
        barcode391.setTextPosition(HumanReadablePlacement.HRP_BOTTOM);
        barcode391.setEvaluationTimeValue(EvaluationTimeEnum.NOW);
        barcode391.setExtendedCharSetEnabled(Boolean.TRUE);
        JRDesignExpression expression1=new JRDesignExpression();
        expression1.setText("$F{accessionNo2}");
        expression1.setValueClass(java.lang.String.class);
        barcode391.setCodeExpression(expression1);
        cdesign1.setComponent(barcode391);
        cdesign1.setComponentKey(new ComponentKey(
                                        "http://jasperreports.sourceforge.net/jasperreports/components",
                                        "jr", "Code39"));
        cdesign1.setX(140);
        cdesign1.setY(5);
        cdesign1.setWidth(220);
        cdesign1.setHeight(40);

        JRDesignTextField    textField3 = new JRDesignTextField();
        textField3.setX(290);
        textField3.setY(0);
        textField3.setWidth(100);
        textField3.setHeight(10);
        textField3.setFontSize(5);
        textField3.setForecolor(new Color(0, 0, 0));
        textField3.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        textField3.setStyle(normalStyle);
        textField3.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{accessionNo3}");
        textField3.setExpression(expression);
        band.addElement(textField3);

        textField3 = new JRDesignTextField();
        textField3.setX(290);
        textField3.setY(35);
        textField3.setWidth(100);
        textField3.setHeight(10);
        textField3.setFontSize(5);
        textField3.setForecolor(new Color(0, 0, 0));
        textField3.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        textField3.setStyle(normalStyle);
        textField3.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{callNo3}");
        textField3.setExpression(expression);
        band.addElement(textField3);


        Code39Component barcode392=new Code39Component();
        barcode392.setExtendedCharSetEnabled(Boolean.TRUE);
        barcode392.setDisplayChecksum(Boolean.FALSE);
        barcode392.setDisplayStartStop(Boolean.TRUE);
        barcode392.setChecksumMode(ChecksumMode.CP_AUTO);
        barcode392.setTextPosition(HumanReadablePlacement.HRP_BOTTOM);
        barcode392.setEvaluationTimeValue(EvaluationTimeEnum.NOW);
        barcode392.setExtendedCharSetEnabled(Boolean.TRUE);
        JRDesignExpression expression2=new JRDesignExpression();
        expression2.setText("$F{accessionNo3}");
        expression2.setValueClass(java.lang.String.class);
        barcode392.setCodeExpression(expression2);
        cdesign2.setComponent(barcode392);
        cdesign2.setComponentKey(new ComponentKey(
                                        "http://jasperreports.sourceforge.net/jasperreports/components",
                                        "jr", "Code39"));
        cdesign2.setX(280);
        cdesign2.setY(5);
        cdesign2.setWidth(220);
        cdesign2.setHeight(40);

        JRDesignTextField    textField4 = new JRDesignTextField();
        textField4.setX(430);
        textField4.setY(0);
        textField4.setWidth(100);
        textField4.setHeight(10);
        textField4.setFontSize(5);
        textField4.setForecolor(new Color(0, 0, 0));
        textField4.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        textField4.setStyle(normalStyle);
        textField4.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{accessionNo4}");
        textField4.setExpression(expression);
        band.addElement(textField4);


        textField4 = new JRDesignTextField();
        textField4.setX(430);
        textField4.setY(35);
        textField4.setWidth(100);
        textField4.setHeight(10);
        textField4.setFontSize(5);
        textField4.setForecolor(new Color(0, 0, 0));
        textField4.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        textField4.setStyle(normalStyle);
        textField4.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{callNo4}");
        textField4.setExpression(expression);
        band.addElement(textField4);

        Code39Component barcode393=new Code39Component();
        barcode393.setExtendedCharSetEnabled(Boolean.TRUE);
        barcode393.setDisplayChecksum(Boolean.FALSE);
        barcode393.setDisplayStartStop(Boolean.TRUE);
        barcode393.setChecksumMode(ChecksumMode.CP_AUTO);
        barcode393.setTextPosition(HumanReadablePlacement.HRP_BOTTOM);
        barcode393.setEvaluationTimeValue(EvaluationTimeEnum.NOW);
        barcode393.setExtendedCharSetEnabled(Boolean.TRUE);
        JRDesignExpression expression3=new JRDesignExpression();
        expression3.setText("$F{accessionNo4}");
        expression3.setValueClass(java.lang.String.class);
        barcode393.setCodeExpression(expression3);
        cdesign3.setComponent(barcode393);
        cdesign3.setComponentKey(new ComponentKey(
                                        "http://jasperreports.sourceforge.net/jasperreports/components",
                                        "jr", "Code39"));
        cdesign3.setX(420);
        cdesign3.setY(5);
        cdesign3.setWidth(220);
        cdesign3.setHeight(40);

        band.addElement(cdesign);
        band.addElement(cdesign1);
        band.addElement(cdesign2);
        band.addElement(cdesign3);
        ((JRDesignSection)jasperDesign.getDetailSection()).addBand(band);


         try
        {
          System.out.println("Compiling report...");
          JasperCompileManager.compileReportToFile(jasperDesign,path + "/bookbarcode.jasper");
          System.out.println("Done!");

          OutputStream ouputStream = response.getOutputStream();
          response.setContentType("application/pdf");
          JRBeanCollectionDataSource dataSource;
          dataSource = new JRBeanCollectionDataSource(list2);

          HashMap map = new HashMap();

          System.out.println("Filling report...");
          JasperFillManager.fillReportToFile(path+"/bookbarcode.jasper",map, dataSource);
          System.out.println("Done!");


          File file = new File(path + "/" +"/bookbarcode.jrprint");
          JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(file);
          JRPdfExporter pdfExporter = new JRPdfExporter();
          pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	  pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,path + "/" + "bookbarcode.pdf");
	  System.out.println("Exporting report...");
          pdfExporter.exportReport();
          System.out.println("Done!");
         
          JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);
         
        }
        catch (JRException e)
        {
          e.printStackTrace();
        }


        return mapping.findForward(SUCCESS);
    }
}
