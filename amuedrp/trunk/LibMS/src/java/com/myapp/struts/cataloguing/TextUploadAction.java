//TEXT FILE UPLOAD
package com.myapp.struts.cataloguing;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.io.*;
import java.util.regex.Pattern;
import jxl.*;
import java.util.*;
import jxl.Workbook;
import jxl.write.*;
import org.apache.struts.upload.FormFile;
public class TextUploadAction extends org.apache.struts.action.Action
{
    
    private static final String SUCCESS = "success";
    NewJFrame newjframe = new NewJFrame();
       String temppath="";
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        try
        {
             StrutsUploadForm uploadForm = (StrutsUploadForm) form;
             FileInputStream fstream = null;
             String path = null;
          

            if(uploadForm.getButton().equalsIgnoreCase("upload file"))
            {

                        java.awt.EventQueue.invokeLater(new Runnable() {
                            public void run()
                            {
                                newjframe.setVisible(true);
                            }
                        });


                        if (NewJFrame.button.equalsIgnoreCase("CancelSelection"))
                        {
                            newjframe.setVisible(false);
                            return mapping.findForward("cancel");
                        }
                        while (!NewJFrame.button.equalsIgnoreCase("ApproveSelection"))
                        {
                            if (newjframe.button.equalsIgnoreCase("CancelSelection"))
                            {
                                newjframe.setVisible(false);
                                NewJFrame.button = "";
                                break;
                            }
                        }
                        path = (String) newjframe.jFileChooser1ActionPerformed1(newjframe);
                        if (NewJFrame.button.equalsIgnoreCase("ApproveSelection"))
                        {
                                NewJFrame.button = "";
                                NewJFrame.filename = "";
                        }
                        FormFile myFile = uploadForm.getExcelFile();
                        path = (String) myFile.getFileName();
                        WritableSheet s = null;
                        WritableWorkbook workbook = null;
                        int Totalrow = 0;
                        String delimiter=uploadForm.getDelimiter();
                        Pattern p = Pattern.compile("["+delimiter+"\\s]+");
                        WorkbookSettings ws = new WorkbookSettings();
                        temppath=path;
                        temppath=path.concat(".xls");
                        System.out.println("pathe:::::::::after changes"+path);
                        ws.setLocale(new Locale("en", "EN"));
                        workbook = Workbook.createWorkbook(new File(temppath), ws);
                        s = workbook.createSheet("Sheet1", 0);
                        System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFPPPPPPPPAAAAAAAAATHHHHH::::"+temppath);
                        // to write on excell sheet
                        WritableFont wf = new WritableFont(WritableFont.TIMES,10, WritableFont.NO_BOLD);
                        WritableCellFormat cf = new WritableCellFormat(wf);
                        cf.setWrap(true);
                        WritableFont wf1 = new WritableFont(WritableFont.ARIAL,10, WritableFont.BOLD);
                        WritableCellFormat cf1 = new WritableCellFormat(wf1);
                        cf.setWrap(true);
                        Label l;
                        fstream = new FileInputStream(path);
                        DataInputStream in = new DataInputStream(fstream);
                        BufferedReader br = new BufferedReader(new InputStreamReader(in));
                        String strLine;

                            //Read File Line By Line
                            while ((strLine = br.readLine()) != null)
                            {
                                // Print the content on the console

                                String[] result1 =p.split(strLine);
                                for (int i = 0; i < result1.length; i++)
                                {
                                    l = new Label(i, Totalrow, result1[i], cf1);
                                    s.addCell(l);
                                }
                                System.out.println(strLine);
                                Totalrow++;
                            }
                            workbook.write();
                            workbook.close();
                            in.close();
                            System.out.println("File path  is :" +  temppath);
                            File file=new File(temppath);
                            uploadForm.setExcelFile((FormFile)file);

            }
       }
       catch(Exception e)
       {
               request.setAttribute("error", e.getMessage());
               return mapping.findForward(SUCCESS);
       }
             request.setAttribute("testfileread", "System has generated a excel file on the same path <br>"+temppath);
             return mapping.findForward(SUCCESS);

    }
}
