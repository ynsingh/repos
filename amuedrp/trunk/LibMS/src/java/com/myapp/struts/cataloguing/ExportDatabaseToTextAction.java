/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.cataloguing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.io.*;
import jxl.*;
import java.util.*;
import jxl.Workbook;

import jxl.write.*;
import com.myapp.struts.hbm.*;
import java.util.List;
import org.hibernate.Session;
import com.myapp.struts.cataloguingDAO.DAO;

/**
 *
 * @author khushnood
 */
public class ExportDatabaseToTextAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    NewJFrame newjframe = new NewJFrame();
    static String TextFileName="",filename = "/";
    String fileType = "excell";
    static String delimiter = " , ";
  public static String library_id="jmi";
    public static String sublibrary_id="jmi";

  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        StrutsUploadForm uploadForm = (StrutsUploadForm) form;


     
        
         //   delimiter = (uploadForm.getDelimiter()).concat(uploadForm.getCombo_delimiter()+" ");
      //  System.out.println("Delimiter:::::::::::::::::::::::::::::"+delimiter.toString());
       // fileType = uploadForm.getFileType();
             if (uploadForm.getButton().equals("Back")){
                 return mapping.findForward("Back");
             }

        if (uploadForm.getButton().equals("Export Data")) {
            try {

                java.awt.EventQueue.invokeLater(new Runnable() {

                    public void run() {
                        newjframe.setVisible(true);
                    }
                });
                if (NewJFrame.button.equalsIgnoreCase("CancelSelection")) {
                    newjframe.setVisible(false);
                    return mapping.findForward("success");

                }
                while (!NewJFrame.button.equalsIgnoreCase("ApproveSelection")) {
                    if (newjframe.button.equalsIgnoreCase("CancelSelection")) {
                        newjframe.setVisible(false);
                        NewJFrame.button = "";
                        NewJFrame.filename = "";
                        break;
                    }

                }
               // filename = (String) newjframe.jFileChooser1ActionPerformed1(newjframe) + uploadForm.getCombo_table_name() + ".xls";
                TextFileName = (String) newjframe.jFileChooser1ActionPerformed1(newjframe) + uploadForm.getCombo_table_name() + ".txt";





                //filename =  save.jFileChooser1ActionPerformed1(null)+"/"+uploadForm.getCombo_table_name()+".xls";

                //filename=(String)request.getAttribute("path");
                if (NewJFrame.button.equalsIgnoreCase("ApproveSelection")) {
                    NewJFrame.button = "";
                    NewJFrame.filename = "";

                    FileWriter fstream = null;
                    BufferedWriter out = null;

                    try {
                        String table_name = uploadForm.getCombo_table_name();
                        fstream = new FileWriter(TextFileName);
                        out = new BufferedWriter(fstream);
                        out.write("");
                        for (int k = 0; k < DAO.columnname1(table_name).size(); k++) {
                            //l = new Label(k, 0, DAO.columnname1(table_name).get(k).toString(), cf1);
                            out.write(DAO.columnname1(table_name).get(k).toString() + delimiter);

                            System.out.println("this is table column ::::::::::::::::" + DAO.columnname1(table_name).get(k).toString());

                        }

                        List lst = DAO.ViewAllTable(table_name, library_id);
                        List rowdata = null;
                        out.newLine();
                        /// fstream = new FileWriter(TextFileName,true);

                        for (int row = 0; row < lst.size(); row++) {
                            rowdata = (List) lst.get(row);
                            System.out.println("no of column"+DAO.columnname1(table_name).size());
                            for (int column = 0; column < DAO.columnname1(table_name).size(); column++) {
                                // System.out.println("************this is size of list column :::::::::::::" + rowdata.get(column));
//rowdata.size()

                                System.out.println("Column "+DAO.columnname1(table_name).get(column).toString()+" : value="+rowdata.get(column));
                                
                                if (rowdata.get(column) == null || rowdata.get(column).equals("")) {
                                String data="<NULL>";
                                
                                out.write(data+delimiter+" ");
                                }

                                else{
                                    // Create file

                                    out.write(rowdata.get(column).toString() + delimiter+ " ");
                                    //out.append(rowdata.get(column).toString() +delimiter);
                                    System.out.println("delimiter ::::::::::::::::" + rowdata.get(column).toString());
                                }

                                    //Close the output stream
                                    // out.close();
                                //}

                            }
                           // 
                            out.newLine();
                        }
                        out.close();
                        request.setAttribute("msgt", "The Data has been successfully exported and saved <br>" + TextFileName + "<br> with file name :" + uploadForm.getCombo_table_name() + ".txt");
//request.setAttribute("msg","FILE TYPE::::::::::::::::::::::"+uploadForm.getFileType().toString() );

                    } catch (Exception e) {//Catch exception if any
                        request.setAttribute("msg", e.getMessage());

                        System.err.println("Error: " + e.getMessage());
                    }

                }

                return mapping.findForward(SUCCESS);
            } catch (Exception e) {//Catch exception if any
                request.setAttribute("msg", e.getMessage());

                System.err.println("Error: " + e.getMessage());
            }
            //return mapping.findForward(SUCCESS);
        }
         return mapping.findForward(SUCCESS);
    }
}
