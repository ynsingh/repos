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
import com.myapp.struts.utility.AppPath;
import com.myapp.struts.utility.UserLog;
import com.myapp.struts.utility.LoggerUtils;
import java.io.*;
import jxl.*;
import java.util.*;
import jxl.Workbook;
import jxl.write.*;
import org.apache.log4j.Logger;
import java.util.List;
import javax.servlet.http.HttpSession;
import com.myapp.struts.cataloguingDAO.BibliographicEntryDAO;
import com.myapp.struts.hbm.DocumentDetails;

/**
 *
 * @author edrp-03
 */
public class AccExportAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
     String library_id,sub_library_id,user_id,tableName;

    private static Logger log4j =LoggerUtils.getLogger();
    
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
        

         HttpSession session=request.getSession();
         BibliographicDetailEntryActionForm1 bd = (BibliographicDetailEntryActionForm1) form;
         BibliographicEntryDAO dao=new BibliographicEntryDAO();
         ArrayList columns=new ArrayList();
        library_id=(String)session.getAttribute("library_id");
        sub_library_id=(String)session.getAttribute("sublibrary_id");
        user_id=(String)session.getAttribute("login_id");
	String doc_type=bd.getDocument_type();
        tableName="accession_details";
        String filename = "";
        columns.add(0, "Accession Number");
         columns.add(1, "Title");
        columns.add(2, "Main Entry");
        columns.add(3, "Call No");
        columns.add(4, "Publisher Name");
        columns.add(5, "Publishing Place");
        String search_by = bd.getSearch_by();
        String sort_by = bd.getSort_by();
        String search_keyword = bd.getSearch_keyword();
           int pageno=Integer.parseInt((String)(request.getParameter("page")==null || request.getParameter("page").isEmpty() ?"0":request.getParameter("page")));


         try
    {

        if (bd.getButton().equals("Export in XLS"))
        {
                filename=AppPath.getPropertiesFilePath()+tableName+user_id+".xls";

                    WorkbookSettings ws = new WorkbookSettings();
                    ws.setLocale(new Locale("en", "EN"));
                    WritableWorkbook workbook =
                    Workbook.createWorkbook(new File(filename), ws);
                    WritableSheet s = workbook.createSheet("Sheet1", 0);
                   // writeDataSheet1(s, tableName,library_id);





                     WritableFont wf = new WritableFont(WritableFont.TIMES,
                10, WritableFont.NO_BOLD);
        WritableCellFormat cf = new WritableCellFormat(wf);
        cf.setWrap(true);
        WritableFont wf1 = new WritableFont(WritableFont.ARIAL,
                10, WritableFont.BOLD);
        WritableCellFormat cf1 = new WritableCellFormat(wf1);
        cf.setWrap(true);
        Label l;


        for (int k = 0; k < columns.size(); k++) {
            l = new Label(k, 0, columns.get(k).toString(), cf1);
            s.addCell(l);
            System.out.println("this is table columns ::::::::::::::::" + columns.get(k).toString());

        }



        //List<Export> lst = (ArrayList<Export>)daoobj.ViewAllTable(table_name,library_id,sublibrary_id) ;

        List<DocumentDetails> lst = dao.getAccession(library_id, sub_library_id,search_by,search_keyword, sort_by,pageno,doc_type);


        System.out.println(tableName+lst.size());
        for (int row = 0; row < lst.size(); row++)
        {






               DocumentDetails  rowdata = (DocumentDetails) lst.get(row);

               l = new Label(0, row +1, String.valueOf(rowdata.getAccessionNo()), cf);
                    s.addCell(l);
               
               
               l = new Label(1, row +1, String.valueOf(rowdata.getTitle()), cf);
                    s.addCell(l);


                l = new Label(2, row +1, String.valueOf(rowdata.getMainEntry()), cf);
                    s.addCell(l);

                l = new Label(3, row +1, String.valueOf(rowdata.getCallNo()), cf);
                    s.addCell(l);

                l = new Label(4, row +1, String.valueOf(rowdata.getPublisherName()), cf);
                    s.addCell(l);

                l = new Label(5, row +1, String.valueOf(rowdata.getPublicationPlace()), cf);
                    s.addCell(l);
        }






                    workbook.write();
                    workbook.close();
                    request.setAttribute("msg", "The Data has been successfully exported and saved");
                   session.setAttribute("filename", tableName+user_id+".xls");
		log4j.error("Write:"+filename);
        }






        if (bd.getButton().equalsIgnoreCase("Export in FLAT"))
        {
             filename=AppPath.getPropertiesFilePath()+tableName+user_id+".txt";


               StringBuffer line=new StringBuffer();
               for (int k = 0; k < columns.size(); k++)
               {
                    line.append(columns.get(k).toString());
                    line.append("|");
                }
               line.deleteCharAt(line.length()-1);
               line.append("\n");
                List<DocumentDetails> lst = dao.getAccession(library_id, sub_library_id,search_by,search_keyword, sort_by,pageno,doc_type);
               //List<Export> lst = (ArrayList<Export>)daoobj.ViewAllTable(tableName,library_id,sublibrary_id) ;
               for (int row = 0; row < lst.size(); row++)
	       {


                DocumentDetails rowdata=(DocumentDetails)lst.get(row);




                line.append(rowdata.getAccessionNo());
                line.append("|");


                line.append(rowdata.getTitle());
                line.append("|");

                line.append(rowdata.getMainEntry());
                line.append("|");

                line.append(rowdata.getCallNo());
                line.append("|");

                line.append(rowdata.getPublisherName());
                    line.append("|");

                line.append(rowdata.getPublicationPlace());
                    line.append("|");




            line.append("\n");



        }
        //write data in the file

               boolean res=UserLog.WriteTextFile(line.toString(), filename);
		if(res==false){
		               request.setAttribute("msg1", "Export in Flat file has error");
		}else{
               session.setAttribute("type", "text");
               request.setAttribute("msg", "The Data has been successfully exported and saved");
               session.setAttribute("file", tableName+user_id+".txt");
		}

        }
        if (bd.getButton().equals("Back"))
        {
            return mapping.findForward("Back");
        }
        return mapping.findForward("success");
        }
        catch (IOException e)
        {
                e.printStackTrace();
		log4j.error(e.getMessage());
                request.setAttribute("msg1", "Unable to read Data from Database11"+e);
                return mapping.findForward("success");
        }
        catch (WriteException e)
        {
                e.printStackTrace();
		log4j.error(e.getMessage());
                request.setAttribute("msg1", "Unable to read Data from Database"+e);
                return mapping.findForward("success");
        }
        catch (Exception e)
        {
                e.printStackTrace();
		log4j.error(e.getMessage());
                request.setAttribute("msg1", "Unable to read Data from Database either the table is empty or corrupted");
                return mapping.findForward("success");
        }
    }
}
