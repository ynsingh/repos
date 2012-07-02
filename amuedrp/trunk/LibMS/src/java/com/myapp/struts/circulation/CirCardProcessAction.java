/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import java.awt.Color;
import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;
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
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignRectangle;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.PositionTypeEnum;
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
public class CirCardProcessAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String checkbox1,checkbox2,checkbox3,checkbox4,checkbox5,checkbox6,checkbox7,checkbox8,checkbox9,checkbox10,
            checkbox11,checkbox12,memid,library_id,sublibrary_id,course_id,dept_id,faculty_id,course_name ;
    ArrayList list=new ArrayList();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CirCardProcessActionForm myform=(CirCardProcessActionForm)form;
        checkbox1=myform.getLibname();
        checkbox2=myform.getLibaddress();
        checkbox3=myform.getCardtype();
        checkbox4=myform.getCardgroup();
        checkbox5=myform.getLabname();
        checkbox6=myform.getLabid();
        checkbox7=myform.getLabclass();
        checkbox8=myform.getLabsession();
        checkbox9=myform.getLabaddress();
        checkbox10=myform.getHoldersign();
        checkbox11=myform.getDob();
        checkbox12=myform.getAuthoritysign();
        memid=myform.getMemid();
        HttpSession session=request.getSession();
        List<MemberList> memberlist=(List<MemberList>) session.getAttribute("memberlist");
        System.out.println("aqaqaqaqaqaqaqaqq ="+memberlist.size());
        String path = servlet.getServletContext().getRealPath("/");
        //Report Page Setting
        JasperDesign jasperDesign = new JasperDesign();
        jasperDesign.setName("membercard2");
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
        normalStyle.setName("Garuda1");
        normalStyle.setDefault(true);
//        normalStyle.setFontName("Garuda");
        normalStyle.setFontSize(12);
  //      normalStyle.setPdfFontName("Garuda");
        normalStyle.setPdfEncoding("Cp1252");
        normalStyle.setPdfEmbedded(false);
        jasperDesign.addStyle(normalStyle);

        JRDesignStyle boldStyle = new JRDesignStyle();
        boldStyle.setName("Garuda");
    //    boldStyle.setFontName("Garuda");
        boldStyle.setFontSize(12);
        boldStyle.setBold(true);
      //  boldStyle.setPdfFontName("Garuda");
        boldStyle.setPdfEncoding("Cp1252");
        boldStyle.setPdfEmbedded(false);
        jasperDesign.addStyle(boldStyle);

  /*      JRDesignStyle italicStyle = new JRDesignStyle();
        italicStyle.setName("Garuda");
        italicStyle.setFontName("Garuda");
        italicStyle.setFontSize(12);
        italicStyle.setItalic(true);
        italicStyle.setPdfFontName("Garuda");
        italicStyle.setPdfEncoding("Cp1252");
        italicStyle.setPdfEmbedded(false);
        jasperDesign.addStyle(italicStyle);

*/
        //Parameters
        JRDesignField field = new JRDesignField();
        field.setName("courseId");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);



        //Fields
        field = new JRDesignField();
        field.setName("address1");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("fname");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("mname");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);
        field = new JRDesignField();
        field.setName("image1");
        field.setValueClass(java.awt.Image.class);
        jasperDesign.addField(field);


         field = new JRDesignField();
        field.setName("address11");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        
        field = new JRDesignField();
        field.setName("mname11");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("image2");
        field.setValueClass(java.awt.Image.class);
        jasperDesign.addField(field);

        field = new JRDesignField();
        field.setName("fname11");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);


        //Detail Section
        JRDesignBand band = new JRDesignBand();
        band.setHeight(164);

        JRDesignRectangle rect=new JRDesignRectangle();
        rect.setX(0);
        rect.setY(0);
        rect.setWidth(190);
        rect.setHeight(110);
        rect.setPositionType(PositionTypeEnum.FLOAT);
        band.addElement(rect);

        JRDesignRectangle rect1=new JRDesignRectangle();
        rect1.setX(270);
        rect1.setY(0);
        rect1.setWidth(190);
        rect1.setHeight(110);
        rect1.setPositionType(PositionTypeEnum.FLOAT);
        band.addElement(rect1);

        JRDesignStaticText staticText = new JRDesignStaticText();
        staticText.setX(18);
        staticText.setY(3);
        staticText.setWidth(140);
        staticText.setHeight(18);
        staticText.setFontSize(9);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox1);
        band.addElement(staticText);

        
        staticText = new JRDesignStaticText();
        staticText.setX(288);
        staticText.setY(3);
        staticText.setWidth(140);
        staticText.setHeight(18);
        staticText.setFontSize(9);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox1);
        band.addElement(staticText);
        
        staticText = new JRDesignStaticText();
        staticText.setX(30);
        staticText.setY(13);
        staticText.setWidth(115);
        staticText.setHeight(15);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox2);
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(300);
        staticText.setY(13);
        staticText.setWidth(115);
        staticText.setHeight(15);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox2);
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(35);
        staticText.setY(21);
        staticText.setWidth(100);
        staticText.setHeight(15);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox3);
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(305);
        staticText.setY(21);
        staticText.setWidth(100);
        staticText.setHeight(15);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox3);
        band.addElement(staticText);


        staticText = new JRDesignStaticText();
        staticText.setX(36);
        staticText.setY(29);
        staticText.setWidth(100);
        staticText.setHeight(15);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox8);
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(306);
        staticText.setY(29);
        staticText.setWidth(100);
        staticText.setHeight(15);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox8);
        band.addElement(staticText);


        staticText = new JRDesignStaticText();
        staticText.setX(18);
        staticText.setY(38);
        staticText.setWidth(100);
        staticText.setHeight(20);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox5);
        
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(288);
        staticText.setY(38);
        staticText.setWidth(100);
        staticText.setHeight(20);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox5);
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(41);
        staticText.setY(38);
        staticText.setWidth(5);
        staticText.setHeight(10);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(":");
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(311);
        staticText.setY(38);
        staticText.setWidth(5);
        staticText.setHeight(10);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(":");
        band.addElement(staticText);

        JRDesignTextField textField = new JRDesignTextField();
        textField.setX(46);
        textField.setY(38);
        textField.setWidth(100);
        textField.setHeight(18);
        textField.setFontSize(6);
        textField.setForecolor(new Color(20,141,54));
        textField.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        textField.setStyle(boldStyle);
        textField.setBlankWhenNull(Boolean.TRUE);
        JRDesignExpression expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{fname}");
        textField.setExpression(expression);
        band.addElement(textField);

        textField = new JRDesignTextField();
        textField.setX(316);
        textField.setY(38);
        textField.setWidth(100);
        textField.setHeight(18);
        textField.setFontSize(6);
        textField.setForecolor(new Color(20,141,54));
        textField.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        textField.setStyle(boldStyle);
        textField.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{fname11}");
        textField.setExpression(expression);
        band.addElement(textField);

       
       
        staticText = new JRDesignStaticText();
        staticText.setX(18);
        staticText.setY(47);
        staticText.setWidth(70);
        staticText.setHeight(20);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox7);
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(288);
        staticText.setY(47);
        staticText.setWidth(70);
        staticText.setHeight(20);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox7);
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(41);
        staticText.setY(47);
        staticText.setWidth(5);
        staticText.setHeight(10);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(":");
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(311);
        staticText.setY(47);
        staticText.setWidth(5);
        staticText.setHeight(10);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(":");
        band.addElement(staticText);

        textField = new JRDesignTextField();
        textField.setStretchWithOverflow(true);
        textField.setX(46);
        textField.setY(47);
        textField.setWidth(132);
        textField.setHeight(30);
        textField.setFontSize(6);
        textField.setForecolor(new Color(20,141,54));
        textField.setPositionType(PositionTypeEnum.FLOAT);
        textField.setStyle(boldStyle);
        textField.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{courseId}");
        textField.setExpression(expression);
        band.addElement(textField);

        textField = new JRDesignTextField();
        textField.setStretchWithOverflow(true);
        textField.setX(316);
        textField.setY(47);
        textField.setWidth(132);
        textField.setHeight(30);
        textField.setFontSize(6);
        textField.setForecolor(new Color(20,141,54));
        textField.setPositionType(PositionTypeEnum.FLOAT);
        textField.setStyle(boldStyle);
        textField.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{courseId}");
        textField.setExpression(expression);
        band.addElement(textField);


        staticText = new JRDesignStaticText();
        staticText.setX(18);
        staticText.setY(56);
        staticText.setWidth(100);
        staticText.setHeight(30);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox9);
        
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(288);
        staticText.setY(56);
        staticText.setWidth(100);
        staticText.setHeight(30);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox9);
        band.addElement(staticText);


        staticText = new JRDesignStaticText();
        staticText.setX(41);
        staticText.setY(56);
        staticText.setWidth(5);
        staticText.setHeight(10);
        staticText.setFontSize(7);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(":");
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(311);
        staticText.setY(56);
        staticText.setWidth(5);
        staticText.setHeight(10);
        staticText.setFontSize(7);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(":");
        band.addElement(staticText);


        textField = new JRDesignTextField();
        textField.setStretchWithOverflow(true);
        textField.setX(46);
        textField.setY(56);
        textField.setWidth(132);
        textField.setHeight(30);
        textField.setFontSize(6);
        textField.setForecolor(new Color(20,141,54));
        textField.setPositionType(PositionTypeEnum.FLOAT);
        textField.setStyle(boldStyle);
        textField.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{address1}");
        textField.setExpression(expression);
        band.addElement(textField);

        textField = new JRDesignTextField();
        textField.setStretchWithOverflow(true);
        textField.setX(316);
        textField.setY(56);
        textField.setWidth(132);
        textField.setHeight(30);
        textField.setFontSize(6);
        textField.setForecolor(new Color(20,141,54));
        textField.setPositionType(PositionTypeEnum.FLOAT);
        textField.setStyle(boldStyle);
        textField.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{address11}");
        textField.setExpression(expression);
        band.addElement(textField);

        if(checkbox6!=null)
        {
        staticText = new JRDesignStaticText();
        staticText.setX(18);
        staticText.setY(70);
        staticText.setWidth(100);
        staticText.setHeight(12);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox6);
        //staticText.setText("aqeel");
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(288);
        staticText.setY(70);
        staticText.setWidth(100);
        staticText.setHeight(12);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox6);
        band.addElement(staticText);


        staticText = new JRDesignStaticText();
        staticText.setX(41);
        staticText.setY(70);
        staticText.setWidth(5);
        staticText.setHeight(10);
        staticText.setFontSize(7);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(":");
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(311);
        staticText.setY(70);
        staticText.setWidth(5);
        staticText.setHeight(10);
        staticText.setFontSize(7);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(":");
        band.addElement(staticText);


        textField = new JRDesignTextField();
        textField.setStretchWithOverflow(true);
        textField.setX(46);
        textField.setY(70);
        textField.setWidth(100);
        textField.setHeight(30);
        textField.setFontSize(6);
        textField.setForecolor(new Color(20,141,54));
        textField.setPositionType(PositionTypeEnum.FLOAT);
        textField.setStyle(boldStyle);
        textField.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{fname}");
        textField.setExpression(expression);
        band.addElement(textField);

        textField = new JRDesignTextField();
        textField.setStretchWithOverflow(true);
        textField.setX(316);
        textField.setY(70);
        textField.setWidth(100);
        textField.setHeight(30);
        textField.setFontSize(6);
        textField.setForecolor(new Color(20,141,54));
        textField.setPositionType(PositionTypeEnum.FLOAT);
        textField.setStyle(boldStyle);
        textField.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{fname11}");
        textField.setExpression(expression);
        band.addElement(textField);

        }

        if(checkbox6==null&&checkbox4!=null)
        {
          staticText = new JRDesignStaticText();
        staticText.setX(18);
        staticText.setY(70);
        staticText.setWidth(100);
        staticText.setHeight(12);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox4);
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(288);
        staticText.setY(70);
        staticText.setWidth(100);
        staticText.setHeight(12);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox4);
        band.addElement(staticText);


        staticText = new JRDesignStaticText();
        staticText.setX(41);
        staticText.setY(70);
        staticText.setWidth(5);
        staticText.setHeight(10);
        staticText.setFontSize(7);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(":");
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(311);
        staticText.setY(70);
        staticText.setWidth(5);
        staticText.setHeight(10);
        staticText.setFontSize(7);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(":");
        band.addElement(staticText);


        textField = new JRDesignTextField();
        textField.setStretchWithOverflow(true);
        textField.setX(46);
        textField.setY(70);
        textField.setWidth(100);
        textField.setHeight(30);
        textField.setFontSize(6);
        textField.setForecolor(new Color(20,141,54));
        textField.setPositionType(PositionTypeEnum.FLOAT);
        textField.setStyle(boldStyle);
        textField.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{fname}");
        textField.setExpression(expression);
        band.addElement(textField);

        textField = new JRDesignTextField();
        textField.setStretchWithOverflow(true);
        textField.setX(316);
        textField.setY(70);
        textField.setWidth(100);
        textField.setHeight(30);
        textField.setFontSize(6);
        textField.setForecolor(new Color(20,141,54));
        textField.setPositionType(PositionTypeEnum.FLOAT);
        textField.setStyle(boldStyle);
        textField.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{fname11}");
        textField.setExpression(expression);
        band.addElement(textField);

        }


        if(checkbox6!=null&&checkbox4!=null)
        {
          staticText = new JRDesignStaticText();
        staticText.setX(18);
        staticText.setY(78);
        staticText.setWidth(100);
        staticText.setHeight(12);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox4);
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(288);
        staticText.setY(78);
        staticText.setWidth(100);
        staticText.setHeight(12);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox4);
        band.addElement(staticText);


        staticText = new JRDesignStaticText();
        staticText.setX(41);
        staticText.setY(78);
        staticText.setWidth(5);
        staticText.setHeight(10);
        staticText.setFontSize(7);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(":");
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(311);
        staticText.setY(78);
        staticText.setWidth(5);
        staticText.setHeight(10);
        staticText.setFontSize(7);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(":");
        band.addElement(staticText);

        textField = new JRDesignTextField();
        textField.setStretchWithOverflow(true);
        textField.setX(46);
        textField.setY(78);
        textField.setWidth(100);
        textField.setHeight(30);
        textField.setFontSize(6);
        textField.setForecolor(new Color(20,141,54));
        textField.setPositionType(PositionTypeEnum.FLOAT);
        textField.setStyle(boldStyle);
        textField.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{fname}");
        textField.setExpression(expression);
        band.addElement(textField);

        textField = new JRDesignTextField();
        textField.setStretchWithOverflow(true);
        textField.setX(316);
        textField.setY(78);
        textField.setWidth(100);
        textField.setHeight(30);
        textField.setFontSize(6);
        textField.setForecolor(new Color(20,141,54));
        textField.setPositionType(PositionTypeEnum.FLOAT);
        textField.setStyle(boldStyle);
        textField.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{fname11}");
        textField.setExpression(expression);
        band.addElement(textField);
        }


        if(checkbox6==null&&checkbox4!=null&&checkbox11!=null)
        {
          staticText = new JRDesignStaticText();
        staticText.setX(18);
        staticText.setY(78);
        staticText.setWidth(100);
        staticText.setHeight(12);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox11);
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(288);
        staticText.setY(78);
        staticText.setWidth(100);
        staticText.setHeight(12);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox11);
        band.addElement(staticText);


        staticText = new JRDesignStaticText();
        staticText.setX(41);
        staticText.setY(78);
        staticText.setWidth(5);
        staticText.setHeight(10);
        staticText.setFontSize(7);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(":");
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(311);
        staticText.setY(78);
        staticText.setWidth(5);
        staticText.setHeight(10);
        staticText.setFontSize(7);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(":");
        band.addElement(staticText);


        textField = new JRDesignTextField();
        textField.setStretchWithOverflow(true);
        textField.setX(46);
        textField.setY(78);
        textField.setWidth(100);
        textField.setHeight(30);
        textField.setFontSize(6);
        textField.setForecolor(new Color(20,141,54));
        textField.setPositionType(PositionTypeEnum.FLOAT);
        textField.setStyle(boldStyle);
        textField.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{fname}");
        textField.setExpression(expression);
        band.addElement(textField);

        textField = new JRDesignTextField();
        textField.setStretchWithOverflow(true);
        textField.setX(316);
        textField.setY(78);
        textField.setWidth(100);
        textField.setHeight(30);
        textField.setFontSize(6);
        textField.setForecolor(new Color(20,141,54));
        textField.setPositionType(PositionTypeEnum.FLOAT);
        textField.setStyle(boldStyle);
        textField.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{fname11}");
        textField.setExpression(expression);
        band.addElement(textField);

        }

        if(checkbox6==null&&checkbox4==null&&checkbox11!=null)
        {
        
         staticText = new JRDesignStaticText();
        staticText.setX(18);
        staticText.setY(70);
        staticText.setWidth(100);
        staticText.setHeight(12);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox11);
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(288);
        staticText.setY(70);
        staticText.setWidth(100);
        staticText.setHeight(12);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox11);
        band.addElement(staticText);


        staticText = new JRDesignStaticText();
        staticText.setX(41);
        staticText.setY(70);
        staticText.setWidth(5);
        staticText.setHeight(10);
        staticText.setFontSize(7);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(":");
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(311);
        staticText.setY(70);
        staticText.setWidth(5);
        staticText.setHeight(10);
        staticText.setFontSize(7);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(":");
        band.addElement(staticText);


        }


        if(checkbox6!=null&&checkbox4==null&&checkbox11!=null)
        {

            staticText = new JRDesignStaticText();
        staticText.setX(18);
        staticText.setY(78);
        staticText.setWidth(100);
        staticText.setHeight(12);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox11);
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(288);
        staticText.setY(78);
        staticText.setWidth(100);
        staticText.setHeight(12);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox11);
        band.addElement(staticText);


        staticText = new JRDesignStaticText();
        staticText.setX(41);
        staticText.setY(78);
        staticText.setWidth(5);
        staticText.setHeight(10);
        staticText.setFontSize(7);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(":");
        band.addElement(staticText);

        staticText = new JRDesignStaticText();
        staticText.setX(311);
        staticText.setY(78);
        staticText.setWidth(5);
        staticText.setHeight(10);
        staticText.setFontSize(7);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(":");
        band.addElement(staticText);


        textField = new JRDesignTextField();
        textField.setStretchWithOverflow(true);
        textField.setX(46);
        textField.setY(78);
        textField.setWidth(100);
        textField.setHeight(30);
        textField.setFontSize(6);
        textField.setForecolor(new Color(20,141,54));
        textField.setPositionType(PositionTypeEnum.FLOAT);
        textField.setStyle(boldStyle);
        textField.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{fname}");
        textField.setExpression(expression);
        band.addElement(textField);

        textField = new JRDesignTextField();
        textField.setStretchWithOverflow(true);
        textField.setX(316);
        textField.setY(78);
        textField.setWidth(100);
        textField.setHeight(30);
        textField.setFontSize(6);
        textField.setForecolor(new Color(20,141,54));
        textField.setPositionType(PositionTypeEnum.FLOAT);
        textField.setStyle(boldStyle);
        textField.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{fname11}");
        textField.setExpression(expression);
        band.addElement(textField);


        }


        expression=new JRDesignExpression();
        expression.setText("$F{image1}");
        expression.setValueClass(java.awt.Image.class);
        JRDesignImage image=new JRDesignImage(null);
        image.setX(135);
        image.setY(35);
        image.setWidth(48);
        image.setHeight(50);
        image.setExpression(expression);
        band.addElement(image);

        expression=new JRDesignExpression();
        expression.setText("$F{image2}");
        expression.setValueClass(java.awt.Image.class);
        JRDesignImage image111=new JRDesignImage(null);
        image111.setX(405);
        image111.setY(35);
        image111.setWidth(48);
        image111.setHeight(50);
        image111.setExpression(expression);
        band.addElement(image111);


        staticText = new JRDesignStaticText();
        staticText.setX(132);
        staticText.setY(95);
        staticText.setWidth(52);
        staticText.setHeight(14);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox12);
        band.addElement(staticText);

        //if(memberlist.get(memberlist.size()-1).getFname11()!=null){
        staticText = new JRDesignStaticText();
        staticText.setX(402);
        staticText.setY(95);
        staticText.setWidth(52);
        staticText.setHeight(14);
        staticText.setFontSize(6);
        staticText.setForecolor(new Color(20,141,54));
        staticText.setBackcolor(Color.WHITE);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.CENTER);
        staticText.setStyle(boldStyle);
        staticText.setText(checkbox12);
        band.addElement(staticText);
      //  }

        textField = new JRDesignTextField();
        textField.setX(25);
        textField.setY(85);
        textField.setWidth(100);
        textField.setHeight(10);
        textField.setFontSize(5);
        textField.setForecolor(new Color(0, 0, 0));
        textField.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        textField.setStyle(normalStyle);
        textField.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{fname}");
        textField.setExpression(expression);
        band.addElement(textField);

        textField = new JRDesignTextField();
        textField.setX(295);
        textField.setY(85);
        textField.setWidth(100);
        textField.setHeight(10);
        textField.setFontSize(5);
        textField.setForecolor(new Color(0, 0, 0));
        textField.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        textField.setStyle(normalStyle);
        textField.setBlankWhenNull(Boolean.TRUE);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{fname11}");
        textField.setExpression(expression);
        band.addElement(textField);

        JRDesignComponentElement cdesign=new JRDesignComponentElement();
        Code39Component barcode39=new Code39Component();
        barcode39.setExtendedCharSetEnabled(Boolean.TRUE);
        barcode39.setDisplayChecksum(Boolean.FALSE);
        barcode39.setDisplayStartStop(Boolean.TRUE);
        barcode39.setChecksumMode(ChecksumMode.CP_AUTO);
        barcode39.setTextPosition(HumanReadablePlacement.HRP_BOTTOM);
        barcode39.setEvaluationTimeValue(EvaluationTimeEnum.NOW);
        barcode39.setExtendedCharSetEnabled(Boolean.TRUE);

        expression=new JRDesignExpression();
        expression.setText("$F{fname}");
        expression.setValueClass(java.lang.String.class);
        barcode39.setCodeExpression(expression);
        cdesign.setComponent(barcode39);
        cdesign.setComponentKey(new ComponentKey(
                                        "http://jasperreports.sourceforge.net/jasperreports/components",
                                        "jr", "Code39"));
        cdesign.setX(8);
        cdesign.setY(90);
        cdesign.setWidth(90);
        cdesign.setHeight(31);
        band.addElement(cdesign);

        JRDesignComponentElement cdesign1=new JRDesignComponentElement();
        Code39Component barcode391=new Code39Component();
        barcode391.setExtendedCharSetEnabled(Boolean.TRUE);
        barcode391.setDisplayChecksum(Boolean.FALSE);
        barcode391.setDisplayStartStop(Boolean.TRUE);
        barcode391.setChecksumMode(ChecksumMode.CP_AUTO);
        barcode391.setTextPosition(HumanReadablePlacement.HRP_BOTTOM);
        barcode391.setEvaluationTimeValue(EvaluationTimeEnum.NOW);
        barcode391.setExtendedCharSetEnabled(Boolean.TRUE);

        JRDesignExpression expression1=new JRDesignExpression();
        expression1.setText("$F{fname11}");
        expression1.setValueClass(java.lang.String.class);
        barcode391.setCodeExpression(expression1);
        cdesign1.setComponent(barcode391);
        cdesign1.setComponentKey(new ComponentKey(
                                        "http://jasperreports.sourceforge.net/jasperreports/components",
                                        "jr", "Code39"));
        cdesign1.setX(278);
        cdesign1.setY(90);
        cdesign1.setWidth(90);
        cdesign1.setHeight(31);
        band.addElement(cdesign1);

        ((JRDesignSection)jasperDesign.getDetailSection()).addBand(band);




        try
        {
          System.out.println("Compiling report...");
          JasperCompileManager.compileReportToFile(jasperDesign,path + "/membercard2.jasper");
          System.out.println("Done!");




          OutputStream ouputStream = response.getOutputStream();
          response.setContentType("application/pdf");
         // list.add(memobj);
          JRBeanCollectionDataSource dataSource;
          dataSource = new JRBeanCollectionDataSource(memberlist);

          HashMap map = new HashMap();
          JasperFillManager.fillReportToFile(path+"/membercard2.jasper",map, dataSource);
          File file = new File(path + "/" +"/membercard2.jrprint");
          JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(file);
          JRPdfExporter pdfExporter = new JRPdfExporter();
          pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	  pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,path + "/" + "membercard2.pdf");
	  System.out.println("Exporting report...");
          pdfExporter.exportReport();
          System.out.println("Done!");

            response.setContentType("application/pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);





        }
        catch (JRException e)
        {
          e.printStackTrace();
        }


        
        return null;
    }
}
