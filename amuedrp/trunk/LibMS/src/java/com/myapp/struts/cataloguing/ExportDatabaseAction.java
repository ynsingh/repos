//EXPORT FROM DATABASE TO XLS
package com.myapp.struts.cataloguing;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.cataloguingDAO.DAO;
import com.myapp.struts.hbm.AccessionRegister;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.DocumentDetails;
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

public class ExportDatabaseAction extends org.apache.struts.action.Action {



    //NewJFrame newjframe = new NewJFrame();
    String library_id,sublibrary_id,user_id;
      private static Logger log4j =LoggerUtils.getLogger();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        StrutsUploadForm uploadForm = (StrutsUploadForm) form;
        HttpSession session=request.getSession();
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");
        user_id=(String)session.getAttribute("login_id");
        String filename = "";
    try
    {

        if (uploadForm.getButton().equals("Export in XLS"))
        {
                filename=AppPath.getPropertiesFilePath()+uploadForm.getCombo_table_name()+user_id+".xls";
            
                    WorkbookSettings ws = new WorkbookSettings();
                    ws.setLocale(new Locale("en", "EN"));
                    WritableWorkbook workbook =
                    Workbook.createWorkbook(new File(filename), ws);
                    WritableSheet s = workbook.createSheet("Sheet1", 0);
                    writeDataSheet1(s, uploadForm.getCombo_table_name(),library_id);
                    workbook.write();
                    workbook.close();
                    request.setAttribute("msg", "The Data has been successfully exported and saved");
                   session.setAttribute("filename", uploadForm.getCombo_table_name()+user_id+".xls");
		log4j.error("Write:"+filename);
        }
        if (uploadForm.getButton().equalsIgnoreCase("Export in FLAT"))
        {
             filename=AppPath.getPropertiesFilePath()+uploadForm.getCombo_table_name()+user_id+".txt";

               String tableName=uploadForm.getCombo_table_name();
               StringBuffer line=new StringBuffer();
               for (int k = 0; k < DAO.columnname1(tableName).size(); k++)
               {
                    line.append(DAO.columnname1(tableName).get(k).toString());
                    line.append("|");
                }
               line.deleteCharAt(line.length()-1);
               line.append("\n");
               List<Export> lst = (ArrayList<Export>)DAO.ViewAllTable(uploadForm.getCombo_table_name(),library_id,sublibrary_id) ;
               for (int row = 0; row < lst.size(); row++)
	       {
            if(tableName.equalsIgnoreCase("bibliographic_details"))
            {
                BibliographicDetails  rowdata = (BibliographicDetails) lst.get(row).getBibliographicDetails();
                line.append(rowdata.getId().getBiblioId());
                line.append("|");

                line.append(rowdata.getId().getLibraryId());
                line.append("|");
                line.append(rowdata.getId().getSublibraryId());
                line.append("|");
                line.append(rowdata.getDocumentType());
                    line.append("|");
                line.append(rowdata.getBookType());
                    line.append("|");
                line.append(rowdata.getAccessionType());
                    line.append("|");
                line.append(rowdata.getTitle());
                    line.append("|");
                    line.append(rowdata.getSubtitle());
                    line.append("|");
                    line.append(rowdata.getAltTitle());
                    line.append("|");
                    line.append(rowdata.getStatementResponsibility());
                    line.append("|");
                    line.append(rowdata.getMainEntry());
                    line.append("|");
                    line.append(rowdata.getAddedEntry());
                    line.append("|");
                    line.append(rowdata.getAddedEntry1());
                    line.append("|");
                    line.append(rowdata.getAddedEntry2());
                    line.append("|");
                    line.append(rowdata.getAddedEntry3());
                    line.append("|");
                    line.append(rowdata.getPublisherName());
                    line.append("|");
                    line.append(rowdata.getPublicationPlace());
                    line.append("|");
                    line.append(rowdata.getPublishingYear());
                    line.append("|");
                    line.append(rowdata.getCallNo());
                    line.append("|");
                    line.append(rowdata.getPartsNo());
                    line.append("|");
                    line.append(rowdata.getSubject());
                    line.append("|");
                    line.append(rowdata.getEntryLanguage());
                    line.append("|");
                    line.append(rowdata.getIsbn10());
                    line.append("|");
                    line.append(rowdata.getIsbn13());
                    line.append("|");
                    line.append(rowdata.getLccNo());
                    line.append("|");
                    line.append(rowdata.getEdition());
                    line.append("|");
                    line.append(rowdata.getNoOfCopies());
                    line.append("|");
                    line.append(rowdata.getAuthorName());
                    line.append("|");
                    line.append(rowdata.getGuideName());
                    line.append("|");
                    line.append(rowdata.getUniversityFaculty());
                    line.append("|");
                    line.append(rowdata.getDegree());
                    line.append("|");
                    line.append(rowdata.getSubmittedOn());
                    line.append("|");
                    line.append(rowdata.getAcceptanceYear());
                    line.append("|");
                    line.append(rowdata.getCollation1());
                    line.append("|");
                    line.append(rowdata.getNotes());
                    line.append("|");
                    line.append(rowdata.getAbstract_());
                    line.append("|");
                    line.append(rowdata.getAddress());
                    line.append("|");
                    line.append(rowdata.getState1());
                    line.append("|");
                    line.append(rowdata.getCountry());
                    line.append("|");
                    line.append(rowdata.getEmail());
                    line.append("|");
                    line.append(rowdata.getFrmrFrq());
                    line.append("|");
                    line.append(rowdata.getFrqDate());
                   line.append("|");
                    line.append(rowdata.getIssn());
                    line.append("|");
                    line.append(rowdata.getVolumeLocation());
                    line.append("|");
                    line.append(rowdata.getProductionYear());
                    line.append("|");
                    line.append(rowdata.getSource1());
                    line.append("|");
                    line.append(rowdata.getDuration());
                    line.append("|");
                    line.append(rowdata.getSeries());
                    line.append("|");
                    line.append(rowdata.getTypeOfDisc());
                    line.append("|");
                    line.append(rowdata.getFileType());
                    line.append("|");
                    line.append(rowdata.getDateAcquired());
                    
            line.append("\n");
            }

            if(tableName.equalsIgnoreCase("accession_register"))
            {
                AccessionRegister  rowdata = (AccessionRegister) lst.get(row).getAccessionRegister();

                    line.append(rowdata.getId().getLibraryId());
                  line.append("|");
                line.append(rowdata.getId().getSublibraryId());
                    line.append("|");
                line.append(rowdata.getBiblioId());
                    line.append("|");
                line.append(rowdata.getAccessionNo());
                    line.append("|");
                line.append(rowdata.getId().getRecordNo());
                    line.append("|");
                line.append(rowdata.getVolumeNo());
                    line.append("|");
                line.append(rowdata.getLocation());
                    line.append("|");
                    line.append(rowdata.getShelvingLocation());
                    line.append("|");
                   line.append(rowdata.getIndexNo());
                    line.append("|");
                   line.append(rowdata.getNoOfPages());
                    line.append("|");
                    line.append(rowdata.getPhysicalWidth());
                    line.append("|");
                    line.append(rowdata.getBindType());
                    line.append("|");
                   line.append(rowdata.getPhysicalForm());
                    line.append("|");
                    line.append(rowdata.getPhysicalDescription());
                    line.append("|");
                    line.append(rowdata.getColour());
                    line.append("|");
                    line.append(rowdata.getDateAcquired());
                    line.append("|");

			line.append("\n");
            }
		
             if(tableName.equalsIgnoreCase("document_details"))
            {
                DocumentDetails  rowdata = (DocumentDetails) lst.get(row).getDocumentDetails();

                line.append(rowdata.getId().getDocumentId());
                    line.append("|");
                line.append(rowdata.getBiblioId());
                    line.append("|");
                line.append(rowdata.getId().getLibraryId());
                    line.append("|");
                line.append(rowdata.getId().getSublibraryId());
                    line.append("|");
                line.append(rowdata.getDocumentType());
                    line.append("|");
                 line.append(rowdata.getBookType());
                    line.append("|");
                line.append(rowdata.getAccessionType());
                    line.append("|");
                   line.append(rowdata.getDateAcquired());
                    line.append("|");
                line.append(rowdata.getTitle());
                    line.append("|");
                  line.append(rowdata.getSubtitle());
                    line.append("|");
                   line.append(rowdata.getAltTitle());
                    line.append("|");
                  line.append(rowdata.getStatementResponsibility());
                    line.append("|");
                   line.append(rowdata.getMainEntry());
                    line.append("|");
                  line.append(rowdata.getAddedEntry());
                    line.append("|");
                 line.append(rowdata.getAddedEntry1());
                    line.append("|");
                 line.append(rowdata.getAddedEntry2());
                    line.append("|");
                  line.append(rowdata.getAddedEntry3());
                    line.append("|");
                   line.append(rowdata.getPublisherName());
                    line.append("|");
                   line.append(rowdata.getPublicationPlace());
                    line.append("|");
                   line.append(rowdata.getPublishingYear());
                     line.append("|");
                   line.append(rowdata.getPartsNo());
                      line.append("|");
                   line.append(rowdata.getSubject());
                      line.append("|");

                line.append(rowdata.getEntryLanguage());
                      line.append("|");
                   line.append(rowdata.getIsbn10());
                      line.append("|");
                   line.append(rowdata.getIsbn13());
                      line.append("|");
                  line.append(rowdata.getLccNo());
                      line.append("|");
                  line.append(rowdata.getEdition());
                      line.append("|");
                 line.append(rowdata.getNoOfCopies());
                      line.append("|");
                  line.append(rowdata.getAuthorName());
                      line.append("|");
                  line.append(rowdata.getGuideName());
                      line.append("|");
                   line.append(rowdata.getUniversityFaculty());
                      line.append("|");
                   line.append(rowdata.getDegree());
                      line.append("|");
                   line.append(rowdata.getSubmittedOn());
                      line.append("|");
                   line.append(rowdata.getAcceptanceYear());
                      line.append("|");
                    line.append(rowdata.getCollation1());
                      line.append("|");
                    line.append(rowdata.getNotes());
                      line.append("|");
                    line.append(rowdata.getAbstract_());
                     line.append("|");
                    line.append(rowdata.getAddress());
                     line.append("|");
                    line.append(rowdata.getState1());
                     line.append("|");
                   line.append(rowdata.getCountry());
                     line.append("|");
                    line.append(rowdata.getEmail());
                     line.append("|");
                    line.append(rowdata.getFrmrFrq());
                     line.append("|");
                    line.append(rowdata.getFrqDate());
                     line.append("|");
                    line.append(rowdata.getIssn());
                     line.append("|");
                    line.append(rowdata.getVolumeLocation());
                     line.append("|");
                    line.append(rowdata.getProductionYear());
                     line.append("|");
                    line.append(rowdata.getSource1());
                     line.append("|");
                    line.append(rowdata.getDuration());
                     line.append("|");
                    line.append(rowdata.getSeries());
                     line.append("|");
                    line.append(rowdata.getPhysicalForm());
                     line.append("|");
                    line.append(rowdata.getColour());
                     line.append("|");
                    line.append(rowdata.getTypeOfDisc());
                     line.append("|");
                    line.append(rowdata.getFileType());
                     line.append("|");
                    line.append(rowdata.getAccessionNo());
                     line.append("|");
                    line.append(rowdata.getRecordNo());
                     line.append("|");
                    line.append(rowdata.getCallNo());
                     line.append("|");
                    line.append(rowdata.getVolumeNo());
                     line.append("|");
                    line.append(rowdata.getLocation());
                     line.append("|");
                    line.append(rowdata.getShelvingLocation());
                     line.append("|");
                    line.append(rowdata.getIndexNo());
                     line.append("|");
                    line.append(rowdata.getNoOfPages());
                     line.append("|");
                    line.append(rowdata.getPhysicalWidth());
                     line.append("|");
                    line.append(rowdata.getBindType());
                     line.append("|");
                    line.append(rowdata.getStatus());
                     line.append("|");
			line.append("\n");

            }
        }
        //write data in the file

               boolean res=UserLog.WriteTextFile(line.toString(), filename);
		if(res==false){
		               request.setAttribute("msg1", "Export in Flat file has error");
		}else{
               session.setAttribute("type", "text");
               request.setAttribute("msg", "The Data has been successfully exported and saved");
               session.setAttribute("file", uploadForm.getCombo_table_name()+user_id+".txt");
		}
            
        }
        if (uploadForm.getButton().equals("Back"))
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
        List<Export> lst = (ArrayList<Export>)DAO.ViewAllTable(table_name,library_id,sublibrary_id) ;
        
        for (int row = 0; row < lst.size(); row++)
        {
            if(tableName.equalsIgnoreCase("bibliographic_details"))
            {
                BibliographicDetails  rowdata = (BibliographicDetails) lst.get(row).getBibliographicDetails();
                l = new Label(0, row +1, String.valueOf(rowdata.getId().getBiblioId()), cf);
                    s.addCell(l);
                l = new Label(1, row +1, String.valueOf(rowdata.getId().getLibraryId()), cf);
                    s.addCell(l);
                l = new Label(2, row +1, String.valueOf(rowdata.getId().getSublibraryId()), cf);
                    s.addCell(l);
                l = new Label(3, row +1, String.valueOf(rowdata.getDocumentType()), cf);
                    s.addCell(l);
                l = new Label(4, row +1, String.valueOf(rowdata.getBookType()), cf);
                    s.addCell(l);
                l = new Label(5, row +1, String.valueOf(rowdata.getAccessionType()), cf);
                    s.addCell(l);
                l = new Label(6, row +1, String.valueOf(rowdata.getTitle()), cf);
                    s.addCell(l);
                    l = new Label(7, row +1, String.valueOf(rowdata.getSubtitle()), cf);
                    s.addCell(l);
                    l = new Label(8, row +1, String.valueOf(rowdata.getAltTitle()), cf);
                    s.addCell(l);
                    l = new Label(9, row +1, String.valueOf(rowdata.getStatementResponsibility()), cf);
                    s.addCell(l);
                    l = new Label(10, row +1, String.valueOf(rowdata.getMainEntry()), cf);
                    s.addCell(l);
                    l = new Label(11, row +1, String.valueOf(rowdata.getAddedEntry()), cf);
                    s.addCell(l);
                    l = new Label(12, row +1, String.valueOf(rowdata.getAddedEntry1()), cf);
                    s.addCell(l);
                    l = new Label(13, row +1, String.valueOf(rowdata.getAddedEntry2()), cf);
                    s.addCell(l);
                    l = new Label(14, row +1, String.valueOf(rowdata.getAddedEntry3()), cf);
                    s.addCell(l);
                    l = new Label(15, row +1, String.valueOf(rowdata.getPublisherName()), cf);
                    s.addCell(l);
                    l = new Label(16, row +1, String.valueOf(rowdata.getPublicationPlace()), cf);
                    s.addCell(l);
                    l = new Label(17, row +1, String.valueOf(rowdata.getPublishingYear()), cf);
                    s.addCell(l);
                    l = new Label(18, row +1, String.valueOf(rowdata.getCallNo()), cf);
                    s.addCell(l);
                    l = new Label(19, row +1, String.valueOf(rowdata.getPartsNo()), cf);
                    s.addCell(l);
                    l = new Label(20, row +1, String.valueOf(rowdata.getSubject()), cf);
                    s.addCell(l);
                    l = new Label(21, row +1, String.valueOf(rowdata.getEntryLanguage()), cf);
                    s.addCell(l);
                    l = new Label(22, row +1, String.valueOf(rowdata.getIsbn10()), cf);
                    s.addCell(l);
                    l = new Label(23, row +1, String.valueOf(rowdata.getIsbn13()), cf);
                    s.addCell(l);
                    l = new Label(24, row +1, String.valueOf(rowdata.getLccNo()), cf);
                    s.addCell(l);
                    l = new Label(25, row +1, String.valueOf(rowdata.getEdition()), cf);
                    s.addCell(l);
                    l = new Label(26, row +1, String.valueOf(rowdata.getNoOfCopies()), cf);
                    s.addCell(l);
                    l = new Label(27, row +1, String.valueOf(rowdata.getAuthorName()), cf);
                    s.addCell(l);
                    l = new Label(28, row +1, String.valueOf(rowdata.getGuideName()), cf);
                    s.addCell(l);
                    l = new Label(29, row +1, String.valueOf(rowdata.getUniversityFaculty()), cf);
                    s.addCell(l);
                    l = new Label(30, row +1, String.valueOf(rowdata.getDegree()), cf);
                    s.addCell(l);
                    l = new Label(31, row +1, String.valueOf(rowdata.getSubmittedOn()), cf);
                    s.addCell(l);
                    l = new Label(32, row +1, String.valueOf(rowdata.getAcceptanceYear()), cf);
                    s.addCell(l);
                    l = new Label(33, row +1, String.valueOf(rowdata.getCollation1()), cf);
                    s.addCell(l);
                    l = new Label(34, row +1, String.valueOf(rowdata.getNotes()), cf);
                    s.addCell(l);
                    l = new Label(35, row +1, String.valueOf(rowdata.getAbstract_()), cf);
                    s.addCell(l);
                    l = new Label(36, row +1, String.valueOf(rowdata.getAddress()), cf);
                    s.addCell(l);
                    l = new Label(37, row +1, String.valueOf(rowdata.getState1()), cf);
                    s.addCell(l);
                    l = new Label(38, row +1, String.valueOf(rowdata.getCountry()), cf);
                    s.addCell(l);
                    l = new Label(39, row +1, String.valueOf(rowdata.getEmail()), cf);
                    s.addCell(l);
                    l = new Label(40, row +1, String.valueOf(rowdata.getFrmrFrq()), cf);
                    s.addCell(l);
                    l = new Label(41, row +1, String.valueOf(rowdata.getFrqDate()), cf);
                    s.addCell(l);
                    l = new Label(42, row +1, String.valueOf(rowdata.getIssn()), cf);
                    s.addCell(l);
                    l = new Label(43, row +1, String.valueOf(rowdata.getVolumeLocation()), cf);
                    s.addCell(l);
                    l = new Label(44, row +1, String.valueOf(rowdata.getProductionYear()), cf);
                    s.addCell(l);
                    l = new Label(45, row +1, String.valueOf(rowdata.getSource1()), cf);
                    s.addCell(l);
                    l = new Label(46, row +1, String.valueOf(rowdata.getDuration()), cf);
                    s.addCell(l);
                    l = new Label(47, row +1, String.valueOf(rowdata.getSeries()), cf);
                    s.addCell(l);
                    l = new Label(48, row +1, String.valueOf(rowdata.getTypeOfDisc()), cf);
                    s.addCell(l);
                    l = new Label(49, row +1, String.valueOf(rowdata.getFileType()), cf);
                    s.addCell(l);
                    l = new Label(50, row +1, String.valueOf(rowdata.getDateAcquired()), cf);
                    s.addCell(l);
              
            }
            if(tableName.equalsIgnoreCase("accession_register"))
            {
                AccessionRegister  rowdata = (AccessionRegister) lst.get(row).getAccessionRegister();
 
                l = new Label(0, row +1, String.valueOf(rowdata.getId().getLibraryId()), cf);
                    s.addCell(l);
                l = new Label(1, row +1, String.valueOf(rowdata.getId().getSublibraryId()), cf);
                    s.addCell(l);
                l = new Label(2, row +1, String.valueOf(rowdata.getBiblioId()), cf);
                    s.addCell(l);
                l = new Label(3, row +1, String.valueOf(rowdata.getAccessionNo()), cf);
                    s.addCell(l);
                l = new Label(4, row +1, String.valueOf(rowdata.getId().getRecordNo()), cf);
                    s.addCell(l);
                l = new Label(5, row +1, String.valueOf(rowdata.getVolumeNo()), cf);
                    s.addCell(l);
                l = new Label(6, row +1, String.valueOf(rowdata.getLocation()), cf);
                    s.addCell(l);
                    l = new Label(7, row +1, String.valueOf(rowdata.getShelvingLocation()), cf);
                    s.addCell(l);
                    l = new Label(8, row +1, String.valueOf(rowdata.getIndexNo()), cf);
                    s.addCell(l);
                    l = new Label(9, row +1, String.valueOf(rowdata.getNoOfPages()), cf);
                    s.addCell(l);
                    l = new Label(10, row +1, String.valueOf(rowdata.getPhysicalWidth()), cf);
                    s.addCell(l);
                    l = new Label(11, row +1, String.valueOf(rowdata.getBindType()), cf);
                    s.addCell(l);
                    l = new Label(12, row +1, String.valueOf(rowdata.getPhysicalForm()), cf);
                    s.addCell(l);
                    l = new Label(13, row +1, String.valueOf(rowdata.getPhysicalDescription()), cf);
                    s.addCell(l);
                    l = new Label(14, row +1, String.valueOf(rowdata.getColour()), cf);
                    s.addCell(l);
                    l = new Label(15, row +1, String.valueOf(rowdata.getDateAcquired()), cf);
                    s.addCell(l);
 
                    
            }
             if(tableName.equalsIgnoreCase("document_details"))
            {
                DocumentDetails  rowdata = (DocumentDetails) lst.get(row).getDocumentDetails();
 
                l = new Label(0, row +1, String.valueOf(rowdata.getId().getDocumentId()), cf);
                    s.addCell(l);
                l = new Label(1, row +1, String.valueOf(rowdata.getBiblioId()), cf);
                    s.addCell(l);
                l = new Label(2, row +1, String.valueOf(rowdata.getId().getLibraryId()), cf);
                    s.addCell(l);
                l = new Label(3, row +1, String.valueOf(rowdata.getId().getSublibraryId()), cf);
                    s.addCell(l);
                l = new Label(4, row +1, String.valueOf(rowdata.getDocumentType()), cf);
                    s.addCell(l);
                 l = new Label(5, row +1, String.valueOf(rowdata.getBookType()), cf);
                    s.addCell(l);
                l = new Label(6, row +1, String.valueOf(rowdata.getAccessionType()), cf);
                    s.addCell(l);
                    l = new Label(7, row +1, String.valueOf(rowdata.getDateAcquired()), cf);
                    s.addCell(l);
                l = new Label(8, row +1, String.valueOf(rowdata.getTitle()), cf);
                    s.addCell(l);
                    l = new Label(9, row +1, String.valueOf(rowdata.getSubtitle()), cf);
                    s.addCell(l);
                    l = new Label(10, row +1, String.valueOf(rowdata.getAltTitle()), cf);
                    s.addCell(l);
                    l = new Label(11, row +1, String.valueOf(rowdata.getStatementResponsibility()), cf);
                    s.addCell(l);
                    l = new Label(12, row +1, String.valueOf(rowdata.getMainEntry()), cf);
                    s.addCell(l);
                    l = new Label(13, row +1, String.valueOf(rowdata.getAddedEntry()), cf);
                    s.addCell(l);
                    l = new Label(14, row +1, String.valueOf(rowdata.getAddedEntry1()), cf);
                    s.addCell(l);
                    l = new Label(15, row +1, String.valueOf(rowdata.getAddedEntry2()), cf);
                    s.addCell(l);
                    l = new Label(16, row +1, String.valueOf(rowdata.getAddedEntry3()), cf);
                    s.addCell(l);
                    l = new Label(17, row +1, String.valueOf(rowdata.getPublisherName()), cf);
                    s.addCell(l);
                    l = new Label(18, row +1, String.valueOf(rowdata.getPublicationPlace()), cf);
                    s.addCell(l);
                    l = new Label(19, row +1, String.valueOf(rowdata.getPublishingYear()), cf);
                    s.addCell(l);
                    l = new Label(20, row +1, String.valueOf(rowdata.getPartsNo()), cf);
                    s.addCell(l);
                    l = new Label(21, row +1, String.valueOf(rowdata.getSubject()), cf);
                    s.addCell(l);

                    l = new Label(22, row +1, String.valueOf(rowdata.getEntryLanguage()), cf);
                    s.addCell(l);
                    l = new Label(23, row +1, String.valueOf(rowdata.getIsbn10()), cf);
                    s.addCell(l);
                    l = new Label(24, row +1, String.valueOf(rowdata.getIsbn13()), cf);
                    s.addCell(l);
                    l = new Label(25, row +1, String.valueOf(rowdata.getLccNo()), cf);
                    s.addCell(l);
                    l = new Label(26, row +1, String.valueOf(rowdata.getEdition()), cf);
                    s.addCell(l);
                    l = new Label(27, row +1, String.valueOf(rowdata.getNoOfCopies()), cf);
                    s.addCell(l);
                    l = new Label(28, row +1, String.valueOf(rowdata.getAuthorName()), cf);
                    s.addCell(l);
                    l = new Label(29, row +1, String.valueOf(rowdata.getGuideName()), cf);
                    s.addCell(l);
                    l = new Label(30, row +1, String.valueOf(rowdata.getUniversityFaculty()), cf);
                    s.addCell(l);
                    l = new Label(31, row +1, String.valueOf(rowdata.getDegree()), cf);
                    s.addCell(l);
                    l = new Label(32, row +1, String.valueOf(rowdata.getSubmittedOn()), cf);
                    s.addCell(l);
                    l = new Label(33, row +1, String.valueOf(rowdata.getAcceptanceYear()), cf);
                    s.addCell(l);
                    l = new Label(34, row +1, String.valueOf(rowdata.getCollation1()), cf);
                    s.addCell(l);
                    l = new Label(35, row +1, String.valueOf(rowdata.getNotes()), cf);
                    s.addCell(l);
                    l = new Label(36, row +1, String.valueOf(rowdata.getAbstract_()), cf);
                    s.addCell(l);
                    l = new Label(37, row +1, String.valueOf(rowdata.getAddress()), cf);
                    s.addCell(l);
                    l = new Label(38, row +1, String.valueOf(rowdata.getState1()), cf);
                    s.addCell(l);
                    l = new Label(39, row +1, String.valueOf(rowdata.getCountry()), cf);
                    s.addCell(l);
                    l = new Label(40, row +1, String.valueOf(rowdata.getEmail()), cf);
                    s.addCell(l);
                    l = new Label(41, row +1, String.valueOf(rowdata.getFrmrFrq()), cf);
                    s.addCell(l);
                    l = new Label(42, row +1, String.valueOf(rowdata.getFrqDate()), cf);
                    s.addCell(l);
                    l = new Label(43, row +1, String.valueOf(rowdata.getIssn()), cf);
                    s.addCell(l);
                    l = new Label(44, row +1, String.valueOf(rowdata.getVolumeLocation()), cf);
                    s.addCell(l);
                    l = new Label(45, row +1, String.valueOf(rowdata.getProductionYear()), cf);
                    s.addCell(l);
                    l = new Label(46, row +1, String.valueOf(rowdata.getSource1()), cf);
                    s.addCell(l);
                    l = new Label(47, row +1, String.valueOf(rowdata.getDuration()), cf);
                    s.addCell(l);
                    l = new Label(48, row +1, String.valueOf(rowdata.getSeries()), cf);
                    s.addCell(l);
                    l = new Label(49, row +1, String.valueOf(rowdata.getPhysicalForm()), cf);
                    s.addCell(l);
                    l = new Label(50, row +1, String.valueOf(rowdata.getColour()), cf);
                    s.addCell(l);
                    l = new Label(51, row +1, String.valueOf(rowdata.getTypeOfDisc()), cf);
                    s.addCell(l);
                    l = new Label(52, row +1, String.valueOf(rowdata.getFileType()), cf);
                    s.addCell(l);
                    l = new Label(53, row +1, String.valueOf(rowdata.getAccessionNo()), cf);
                    s.addCell(l);
                    l = new Label(54, row +1, String.valueOf(rowdata.getRecordNo()), cf);
                    s.addCell(l);
                    l = new Label(55, row +1, String.valueOf(rowdata.getCallNo()), cf);
                    s.addCell(l);
                    l = new Label(56, row +1, String.valueOf(rowdata.getVolumeNo()), cf);
                    s.addCell(l);
                    l = new Label(57, row +1, String.valueOf(rowdata.getLocation()), cf);
                    s.addCell(l);
                    l = new Label(58, row +1, String.valueOf(rowdata.getShelvingLocation()), cf);
                    s.addCell(l);
                    l = new Label(59, row +1, String.valueOf(rowdata.getIndexNo()), cf);
                    s.addCell(l);
                    l = new Label(60, row +1, String.valueOf(rowdata.getNoOfPages()), cf);
                    s.addCell(l);
                    l = new Label(61, row +1, String.valueOf(rowdata.getPhysicalWidth()), cf);
                    s.addCell(l);
                    l = new Label(62, row +1, String.valueOf(rowdata.getBindType()), cf);
                    s.addCell(l);
                    l = new Label(63, row +1, String.valueOf(rowdata.getStatus()), cf);
                    s.addCell(l);
 
            }
        }



    }
}
