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
import com.myapp.struts.cataloguingDAO.DAO;

import java.io.*;
import jxl.*;
import java.util.*;
import jxl.Workbook;
import jxl.write.DateFormat;
import jxl.write.Number;
import jxl.write.*;
import java.text.SimpleDateFormat;
import com.myapp.struts.hbm.*;
import com.myapp.struts.hbm.HibernateUtil;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Query;
import java.util.List;
import com.myapp.struts.cataloguing.NewJFrame;
import javax.servlet.http.HttpSession;

/**
 *
 * @author khushnood
 */
public class ExportDatabaseAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    NewJFrame newjframe = new NewJFrame();
 String library_id;
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        StrutsUploadForm uploadForm = (StrutsUploadForm) form;
       HttpSession session=request.getSession();
 library_id=(String)session.getAttribute("library_id");
        DAO dataaccess = new DAO();
        String filename = "/";

        if (uploadForm.getButton().equals("Export Data")) {
            try {

                java.awt.EventQueue.invokeLater(new Runnable() {

                    public void run() {
                        newjframe.setVisible(true);
                    }
                });
if (NewJFrame.button.equalsIgnoreCase("CancelSelection")){
    newjframe.setVisible(false);
    return mapping.findForward("success");

}
                while (!NewJFrame.button.equalsIgnoreCase("ApproveSelection")) {
                      if(newjframe.button.equalsIgnoreCase("CancelSelection")){
                newjframe.setVisible(false);
                  NewJFrame.button = "";
                    NewJFrame.filename="";
                break;
            }

                }
                filename = (String) newjframe.jFileChooser1ActionPerformed1(newjframe) + uploadForm.getCombo_table_name() + ".xls";
                
                



                //filename =  save.jFileChooser1ActionPerformed1(null)+"/"+uploadForm.getCombo_table_name()+".xls";

                //filename=(String)request.getAttribute("path");
                if (NewJFrame.button.equalsIgnoreCase("ApproveSelection")) {
                    NewJFrame.button = "";
                    NewJFrame.filename="";

                    System.out.println("00000000000000000000000 this is the path of the :" + filename);
                    WorkbookSettings ws = new WorkbookSettings();
                    ws.setLocale(new Locale("en", "EN"));
                    WritableWorkbook workbook =
                            Workbook.createWorkbook(new File(filename), ws);
                    WritableSheet s = workbook.createSheet("Sheet1", 0);


                    writeDataSheet1(s, uploadForm.getCombo_table_name(),library_id);
                    workbook.write();
                    workbook.close();
                    request.setAttribute("msg", "The Data has been successfully exported and saved <br>" + filename + "<br> with file name :" + uploadForm.getCombo_table_name() + ".xls");
                    return mapping.findForward("success");
                }
            } catch (IOException e) {
                e.printStackTrace();
                request.setAttribute("msg1", "Unable to read Data from Database");
                return mapping.findForward("success");
            } catch (WriteException e) {
                e.printStackTrace();
                request.setAttribute("msg1", "Unable to read Data from Database");
                return mapping.findForward("success");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("msg1", "Unable to read Data from Database either the table is empty or corrupted");
                return mapping.findForward("success");
            }
            return mapping.findForward("success");
        }
        if (uploadForm.getButton().equals("Back")) {
            return mapping.findForward("Back");
        }
        return mapping.findForward("success");
    }

    private  void writeDataSheet1(WritableSheet s, String tableName,String library_id)
            throws WriteException {
        WritableFont wf = new WritableFont(WritableFont.TIMES,
                10, WritableFont.NO_BOLD);
        WritableCellFormat cf = new WritableCellFormat(wf);
        cf.setWrap(true);
        WritableFont wf1 = new WritableFont(WritableFont.ARIAL,
                10, WritableFont.BOLD);
        WritableCellFormat cf1 = new WritableCellFormat(wf1);
        cf.setWrap(true);
        Label l;

        String table_name = tableName;
        for (int k = 0; k < DAO.columnname1(table_name).size(); k++) {
            l = new Label(k, 0, DAO.columnname1(table_name).get(k).toString(), cf1);
            s.addCell(l);
            System.out.println("this is table column ::::::::::::::::" + DAO.columnname1(table_name).get(k).toString());

        }

        List lst = DAO.ViewAllTable(table_name,library_id) ;
        List rowdata = null;
        // System.out.println("************this is size of list :::::::::::::" + lst.size());
        // System.out.println("************this is size of list :::::::::::::" + lst.listIterator().next().toString());
        for (int row = 0; row < lst.size(); row++) {
            rowdata = (List) lst.get(row);
            for (int column = 0; column < rowdata.size(); column++) {
                // System.out.println("************this is size of list column :::::::::::::" + rowdata.get(column));
                if (rowdata.get(column) != null) {
                    l = new Label(column, row + 1, rowdata.get(column).toString(), cf);
                    s.addCell(l);
                }
            }
        }



    }
}
