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
import java.io.*;
import java.util.regex.Pattern;
import jxl.*;
import java.util.*;
import jxl.Workbook;
import jxl.write.*;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author khushnood
 */
public class TextUploadAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    NewJFrame newjframe = new NewJFrame();

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        StrutsUploadForm uploadForm = (StrutsUploadForm) form;
       // FormFile myFile = uploadForm.getMyfile();
        FileInputStream fstream = null;

         String path = null;
         String temppath="";

        if(uploadForm.getButton().equals("Brows file")){

         try {

            java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                    newjframe.setVisible(true);
                }
            });
            if (NewJFrame.button.equalsIgnoreCase("CancelSelection")) {
                newjframe.setVisible(false);
                return mapping.findForward("cancle");

            }
            while (!NewJFrame.button.equalsIgnoreCase("ApproveSelection")) {
                if (newjframe.button.equalsIgnoreCase("CancelSelection")) {
                    newjframe.setVisible(false);
                    NewJFrame.button = "";
                    break;
                }

            }

           path = (String) newjframe.jFileChooser1ActionPerformedFilepath(newjframe);
             if (NewJFrame.button.equalsIgnoreCase("ApproveSelection")) {
                    NewJFrame.button = "";
                    NewJFrame.fileabsolutepath = "";
             }
           //System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFPPPPPPPPAAAAAAAAATHHHHH::::" + temppath);
        } catch (Exception e) {
        }


        {
            WritableSheet s = null;
            WritableWorkbook workbook = null;

            int Totalrow = 0;
            String delimiter=uploadForm.getDelimiter();
            Pattern p = Pattern.compile("["+delimiter+"\\s]+");
            //count line no

            try {
                WorkbookSettings ws = new WorkbookSettings();
                temppath=path;
                temppath=path.concat(".xls");
                   System.out.println("pathe:::::::::after changes"+path);

                ws.setLocale(new Locale("en", "EN"));
                workbook =
                        Workbook.createWorkbook(new File(temppath), ws);
                s = workbook.createSheet("Sheet1", 0);




                System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFPPPPPPPPAAAAAAAAATHHHHH::::"+temppath);

            } catch (IOException e) {
                e.printStackTrace();
                // request.setAttribute("msg1", "Unable to read Data from Database");
                // return mapping.findForward("success");
            }
//create file object







          //  StringBuffer strContent = new StringBuffer("");






            {

                /*

                 * Create new FileInputStream object. Constructor of FileInputStream throws

                 * FileNotFoundException if the agrument File does not exist.

                 */

                /*

                 * To read bytes from stream use,

                 * int read() method of FileInputStream class.

                 *

                 * This method reads a byte from stream. This method returns next byte of data

                 * from file or -1 if the end of the file is reached.

                 *

                 * Read method throws IOException in case of any IO errors.

                 */








                // to write on excell sheet
                try {
                    WritableFont wf = new WritableFont(WritableFont.TIMES,
                            10, WritableFont.NO_BOLD);
                    WritableCellFormat cf = new WritableCellFormat(wf);
                    cf.setWrap(true);
                    WritableFont wf1 = new WritableFont(WritableFont.ARIAL,
                            10, WritableFont.BOLD);
                    WritableCellFormat cf1 = new WritableCellFormat(wf1);
                    cf.setWrap(true);
                    Label l;




                    {
                        try {


                            //fstream=(FileInputStream)myFile.getInputStream();


                            fstream = new FileInputStream(path);
                                 System.out.println("fstreanmmmmmmmmmmmmm::::" + fstream.toString());

                            // fstream=(FileInputStream)uploadForm.getMyfile().getInputStream();
                            // Open the file that is the first
                            // command line parameter
                            // fstream = myFile.getInputStream();
                            // Get the object of DataInputStream
                            DataInputStream in = new DataInputStream(fstream);
                            BufferedReader br = new BufferedReader(new InputStreamReader(in));
                            // BufferedReader br =myFile.getInputStream().read();

                            String strLine;

                            //Read File Line By Line
                            while ((strLine = br.readLine()) != null) {
                                // Print the content on the console

                                String[] result1 =
                                        p.split(strLine);
                                System.out.println("result:" + result1.length);
                                for (int i = 0; i < result1.length; i++) {
                                    System.out.println(result1[i]);
                                    l = new Label(i, Totalrow, result1[i], cf1);
                                    s.addCell(l);
                                    System.out.println("pathe:::::::::"+path);
                                    System.out.println("pathe:::::::::"+temppath);
                                }


                                System.out.println(strLine+"..........................");
                                Totalrow++;

                            }
                            workbook.write();
                            workbook.close();
                            //Close the input stream
                            in.close();
                        } catch (Exception e) {//Catch exception if any
                              workbook.write();
                            workbook.close();
                            //Close the input stream

                            System.err.println("Error: " + e.getMessage());
                            request.setAttribute("error", e);
                            return mapping.findForward(SUCCESS);
                        }
                    }

                } catch (WriteException e) {
                    e.printStackTrace();
                    // request.setAttribute("msg1", "Unable to read Data from Database");

                } catch (Exception e) {
                    e.printStackTrace();
                    //request.setAttribute("msg1", "Unable to read Data from Database either the table is empty or corrupted");

                }

                /*

                 * To close the FileInputStream, use

                 * void close() method of FileInputStream class.

                 *

                 * close method also throws IOException.

                 */





            }






           // System.out.println(strContent);




            /*

             * Please note that, FileInputStream SHOULD NOT BE USED to read

             * character data file.

             * It is meant for reading binary data such as an image file.

             *

             * To read character data, FileReader should be used.

             */

        }
         }

         /*
         try{
         System.out.println("File path  is :" +  temppath);
       //  File newfile=new File(getServlet().getServletContext().getRealPath("/")+"temp.xls");
         //FormFile newexlfile=new FormFile(getServlet().getServletContext().getRealPath("/")+"temp.xls");
         InputStream inputStream = null;
        File file=new File(temppath);


        FormFile newexlfile=null;
        //newexlfile.setFileName((FormFile)file);
        uploadForm.setExcelFile((FormFile)file);


      request.setAttribute("error", "hhhhhhhhhhhh");
       }
       catch(Exception e){
            request.setAttribute("error", e.getMessage());
            request.setAttribute("path", temppath);
           return mapping.findForward(SUCCESS);
       }*/
         request.setAttribute("testfileread", "System has generated a excel file on the same path <br>"+temppath);
         return mapping.findForward(SUCCESS);

    }
}
