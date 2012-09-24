//ACTION TO IMPORT DATA
package com.myapp.struts.cataloguing;
import com.myapp.struts.utility.UserLog;
import com.myapp.struts.cataloguingDAO.BibliographicEntryDAO;
import com.myapp.struts.cataloguingDAO.DAO;
import com.myapp.struts.hbm.AccessionRegister;
import com.myapp.struts.hbm.AccessionRegisterId;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsId;
import com.myapp.struts.hbm.BibliographicDetailsLang;
import com.myapp.struts.hbm.BibliographicDetailsLangId;
import com.myapp.struts.hbm.DocumentDetails;
import com.myapp.struts.hbm.DocumentDetailsId;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import com.myapp.struts.hbm.TempExcellImport;
import com.myapp.struts.utility.AppPath;
import com.myapp.struts.utility.LoggerUtils;
import com.myapp.struts.utility.StringRegEx;
import com.myapp.struts.utility.UserLog;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class ExeltoDatabaseAction extends org.apache.struts.action.Action {

    
    ArrayList log=new ArrayList();
    public String library_id;
    public String sublibrary_id;
    private static Logger log4j =LoggerUtils.getLogger();
    TempExcellImport genericobj = new  TempExcellImport();
     List<TempExcellImport> temp = new  ArrayList<TempExcellImport>();
       BibliographicEntryDAO bibdao=new BibliographicEntryDAO();
         DAO daoobj = new DAO();
         
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        try
        {
          
            StrutsUploadForm uploadForm = (StrutsUploadForm) form;
            HttpSession session = request.getSession();
              session.removeAttribute("importlog");
            library_id=(String)session.getAttribute("library_id");
            sublibrary_id=(String)session.getAttribute("sublibrary_id");
            int row_no = 0,count=0;
            String table_name = uploadForm.getCombo_table_name();
            int size = daoobj.columnname(table_name).size();
            int sheet_column_size=0;
            int i=0;
            if (session.getAttribute("no_columns") != null)
            {
              sheet_column_size = (Integer) session.getAttribute("no_columns");
            }
            InputStream inputStream = null;
            FormFile myFile = uploadForm.getExcelFile();
            inputStream = (myFile.getInputStream());
            POIFSFileSystem fileSystem = null;
            fileSystem = new POIFSFileSystem(inputStream);
            HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workBook.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();
            String cellvalue = "";
            String map_table[] = new String[Math.max(sheet_column_size ,size)];
            map_table = (String[]) daoobj.selectedcombo(map_table, (size), uploadForm);
           int noc=1;
            if (uploadForm.getButton().equals("Back"))
            {
                return mapping.findForward("Back");
            }
            if (uploadForm.getButton().equals("Import Data"))
            {
  
              i=0;
                rows.next();
                  s:
                   while(rows.hasNext())
                    {
                       Row row = rows.next();
                     i++;
                        // once get a row its time to iterate through cells.
                        Iterator<Cell> cells = row.cellIterator();
                        while (cells.hasNext())
                        {
                            Cell cell = cells.next();
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            switch (cell.getCellType())
                            {
                                case HSSFCell.CELL_TYPE_BOOLEAN:
                                {
                                    // cell type numeric.
                                    if (cell.getBooleanCellValue() == true)
                                    {
                                        cellvalue = "True";
                                    }
                                    else
                                    {
                                        cellvalue = "false";
                                    }
                                }

                                case HSSFCell.CELL_TYPE_STRING:
                                {
                                    // cell type string.
                                    RichTextString richTextString = cell.getRichStringCellValue();
                                    cellvalue = cell.getRichStringCellValue().getString();
                                    break;
                                }
                                default:
                                {
                                    // types other than String and Numeric.
                                    cellvalue = "Unable to read";
                                    break;
                                }
                            }
                            int column_index = cell.getColumnIndex();
                            if (table_name.equals("bibliographic_details"))
                            {
                                    
                                    if(size>column_index)
                                    {
                                           
                                           genericobj.setLibraryId(library_id);
                                           genericobj.setSublibraryId(sublibrary_id);
                                         if (map_table[column_index].equals("document_type"))
                                         {
                                            genericobj.setdocumentCategory(cellvalue.trim());
                                              if(genericobj.getdocumentCategory().isEmpty()==true)
                                            {
                                            log.add("Mandatory fields  missing at record no"+(i+1));
                                            session.setAttribute("importlog", log);
                                            continue s;
                                            }

                                         }
                                        if (map_table[column_index].equals("book_type"))
                                        {
                                            genericobj.setBookType(StringRegEx.titlecase(cellvalue.trim()));
                                             if(genericobj.getBookType().isEmpty()==true)
                                            {
                                            log.add("Mandatory fields  missing at record no"+(i+1));
                                            session.setAttribute("importlog", log);
                                            continue s;
                                            }

                                        }

                                        if (map_table[column_index].equals("date_acquired"))
                                        {
                                            if(cellvalue.isEmpty()==false)
                                            {
                                                genericobj.setDateAcquired(cellvalue.trim());


                                            if(UserLog.isValidDate(genericobj.getDateAcquired())==false)
                                            {
                                            log.add("Date Acquired Format is wrong at record no"+(i+1));
                                            session.setAttribute("importlog", log);
                                            continue s;
                                            }
                                            }

                                        }
                                        if (map_table[column_index].equals("title"))
                                        {
                                            genericobj.setTitle(cellvalue.trim());

                                            if(genericobj.getTitle().isEmpty()==true)
                                            {
                                            log.add("Mandatory fields  missing at record no"+(i+1));
                                            session.setAttribute("importlog", log);
                                            continue s;
                                            }


                                        }
                                        if (map_table[column_index].equals("subtitle"))
                                        {
                                            genericobj.setSubtitle(cellvalue.trim());
                                        }
                                          if (map_table[column_index].equals("subtitle1"))
                                        {
                                            genericobj.setSubtitle1(cellvalue.trim());
                                        }
                                        if (map_table[column_index].equals("alt_title"))
                                        {
                                            genericobj.setAltTitle(cellvalue.trim());

                                        }
                                           if (map_table[column_index].equals("alt_title1"))
                                        {
                                            genericobj.setAltTitle1(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("statement_responsibility"))
                                        {
                                            genericobj.setStatementResponsibility(cellvalue.trim());
                                             if(genericobj.getStatementResponsibility().isEmpty()==true)
                                            {
                                            log.add("Mandatory fields  missing at record no"+(i+1));
                                            session.setAttribute("importlog", log);
                                            continue s;
                                            }
                                        }
                                            if (map_table[column_index].equals("statement_responsibility1"))
                                        {
                                            genericobj.setStatementResponsibility1(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("main_entry"))
                                        {
                                            genericobj.setMainEntry(cellvalue.trim());

                                              if(genericobj.getMainEntry().isEmpty()==true)
                                            {
                                            log.add("Mandatory fields  missing at record no"+(i+1));
                                            session.setAttribute("importlog", log);
                                            continue s;
                                            }

                                        }
                                             if (map_table[column_index].equals("main_entry1"))
                                        {
                                            genericobj.setMainEntry1(cellvalue.trim());



                                        }
                                        if (map_table[column_index].equals("added_entry"))
                                        {
                                            genericobj.setAddedEntry(cellvalue.trim());

                                        }
                                            if (map_table[column_index].equals("added_entrymli"))
                                        {
                                            genericobj.setAddedEntrymli(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("added_entry1"))
                                        {
                                            genericobj.setAddedEntry1(cellvalue.trim());

                                        }
                                            if (map_table[column_index].equals("added_entry11"))
                                        {
                                            genericobj.setAddedEntry11(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("added_entry2"))
                                        {
                                            genericobj.setAddedEntry2(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("added_entry21"))
                                        {
                                            genericobj.setAddedEntry21(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("added_entry3"))
                                        {
                                            genericobj.setAddedEntry3(cellvalue.trim());

                                        }
                                       if (map_table[column_index].equals("added_entry31"))
                                        {
                                            genericobj.setAddedEntry31(cellvalue.trim());

                                        }
//                                           if (map_table[column_index].equals("editor1"))
//                                        {
//                                            genericobj.setEditor1(cellvalue.trim());
//
//                                        }
//                                           if (map_table[column_index].equals("editor2"))
//                                        {
//                                            genericobj.setEditor2(cellvalue.trim());
//
//                                        }
//                                           if (map_table[column_index].equals("editor3"))
//                                        {
//                                            genericobj.setEditor3(cellvalue.trim());
//
//                                        }
//                                           if (map_table[column_index].equals("translator1"))
//                                        {
//                                            genericobj.setTranslator1(cellvalue.trim());
//
//                                        }
//                                           if (map_table[column_index].equals("translator2"))
//                                        {
//                                            genericobj.setTranslator2(cellvalue.trim());
//
//                                        }
//                                           if (map_table[column_index].equals("translator3"))
//                                        {
//                                            genericobj.setTranslator3(cellvalue.trim());
//
//                                        }
                                        if (map_table[column_index].equals("publisher_name"))
                                        {
                                            genericobj.setPublisherName(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("publication_place"))
                                        {
                                            genericobj.setPublicationPlace(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("publishing_year"))
                                        {
                                            genericobj.setPublishingYear(cellvalue.trim());

                                        }
                                           if (map_table[column_index].equals("publisher_name1"))
                                        {
                                            genericobj.setPublisherName1(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("publication_place1"))
                                        {
                                            genericobj.setPublicationPlace1(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("publishing_year1"))
                                        {
                                            genericobj.setPublishingYear1(cellvalue.trim());

                                        }

                                        if (map_table[column_index].equals("call_no"))
                                        {
                                            genericobj.setCallNo(cellvalue.trim());
                                             if(genericobj.getCallNo().isEmpty()==true)
                                            {
                                            log.add("Mandatory fields  missing at record no"+(i+1));
                                            session.setAttribute("importlog", log);
                                            continue s;
                                            }

                                        }
                                        if (map_table[column_index].equals("subject"))
                                        {
                                            genericobj.setSubject(cellvalue.trim());

                                        }
                                         if (map_table[column_index].equals("subject1"))
                                        {
                                            genericobj.setSubject1(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("entry_language"))
                                        {
                                            genericobj.setEntryLanguage(cellvalue.trim());

                                        }
                                           if (map_table[column_index].equals("title1"))
                                        {
                                            genericobj.setTitle1(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("isbn10"))
                                        {
                                            genericobj.setIsbn10(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("isbn13"))
                                        {
                                            genericobj.setIsbn13(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("LCC_no"))
                                        {
                                            genericobj.setLccNo(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("edition"))
                                        {
                                            genericobj.setEdition(cellvalue.trim());

                                        }
                                            if (map_table[column_index].equals("edition1"))
                                        {
                                            genericobj.setEdition1(cellvalue.trim());

                                        }
//                                        if (map_table[column_index].equals("no_of_copies"))
//                                        {
//                                            genericobj.setNoOfCopies(cellvalue.trim());
//                                            if(genericobj.getNoOfCopies().isEmpty()==true)
//                                            {
//                                            log.add("Mandatory fields  missing at record no"+i);
//                                            session.setAttribute("importlog", log);
//                                            continue s;
//                                            }
//                                            if(AppPath.IsNumber(genericobj.getNoOfCopies())==false)
//                                            {
//                                            log.add("No of Copies is not a number at record no"+i);
//                                            session.setAttribute("importlog", log);
//                                            continue s;
//                                            }
//
//
//
//                                        }
                                        if (map_table[column_index].equals("collation1"))
                                        {
                                            genericobj.setCollation1(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("notes"))
                                        {
                                            genericobj.setNotes(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("abstract"))
                                        {
                                            genericobj.setAbstract_(cellvalue.trim());

                                        }
                                       if (map_table[column_index].equals("series"))
                                       {
                                            genericobj.setSeries(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("collation11"))
                                        {
                                            genericobj.setCollation11(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("notes1"))
                                        {
                                            genericobj.setNotes1(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("abstract1"))
                                        {
                                            genericobj.setAbstract1(cellvalue.trim());

                                        }
                                       if (map_table[column_index].equals("series1"))
                                       {
                                            genericobj.setSeries1(cellvalue.trim());

                                        }

                                        if (map_table[column_index].equals("accession_no"))
                                        {
                                            genericobj.setAccessionNo(cellvalue.trim());
                                            if(genericobj.getAccessionNo().isEmpty()==true)
                                            {
                                            log.add("Mandatory Fields is empty at record no"+(i+1));
                                            session.setAttribute("importlog", log);
                                            continue s;
                                            }
                                            if(AppPath.IsNumber(genericobj.getAccessionNo())==false)
                                            {
                                            log.add("Accession No is not a number at record no"+(i+1));
                                            session.setAttribute("importlog", log);
                                            continue s;
                                            }

                                        }
                                        if (map_table[column_index].equals("volume_no"))
                                        {
                                            genericobj.setVolumeNo(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("volume_no1"))
                                        {
                                            genericobj.setVolumeNo1(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("location"))
                                        {
                                            genericobj.setLocation(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("shelving_location"))
                                        {
                                            genericobj.setShelvingLocation(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("index_no"))
                                        {
                                            genericobj.setIndexNo(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("bind_type"))
                                        {
                                            genericobj.setBindType(cellvalue.trim());

                                        }

                                        if (map_table[column_index].equals("shelving_location1"))
                                        {
                                            genericobj.setShelvingLocation1(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("index_no1"))
                                        {
                                            genericobj.setIndexNo1(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("bind_type1"))
                                        {
                                            genericobj.setBindType1(cellvalue.trim());

                                        }

                                        if (map_table[column_index].equals("clref_no")) {
                                            genericobj.setRefNo(cellvalue.trim());

                                        }
                                           if (map_table[column_index].equals("no_of_pages")) {
                                            genericobj.setNoOfPages(cellvalue.trim());

                                        }
                                            if (map_table[column_index].equals("no_of_pages1")) {
                                            genericobj.setNoOfPages1(cellvalue.trim());

                                        }
//                                            if (map_table[column_index].equals("subject2")) {
//                                            genericobj.setSubject1(cellvalue.trim());
//
//                                        }
//                                        if (map_table[column_index].equals("subject3")) {
//                                            genericobj.setSubject2(cellvalue.trim());
//
//                                        }
                                    }
                                    else
                                    {
                                         break;
                                    }

                            }
                        }//Cell Column Close
                 
                   if((genericobj.getCallNo()!=null && genericobj.getCallNo().isEmpty()==false) && (genericobj.getIsbn10()!=null &&  genericobj.getIsbn10().isEmpty()==false) && (genericobj.getTitle()!=null && genericobj.getTitle().isEmpty()==false) && (genericobj.getMainEntry()!=null && genericobj.getMainEntry().isEmpty()==false))
                        {

                            //Add Data in Bibliographic Details
                            BibliographicDetails bibobj;
                            BibliographicDetailsId bibid;
                            BibliographicDetailsLang bibobjmli=new BibliographicDetailsLang();
                            BibliographicDetailsLangId bibobjmliId=new BibliographicDetailsLangId();
                            AccessionRegister accessionobj = new AccessionRegister();
                            AccessionRegisterId accessionidobj = new AccessionRegisterId();
                            DocumentDetails documentobj = new DocumentDetails();
                            DocumentDetailsId documentIdobj = new DocumentDetailsId();
                          
                                            //Check for Duplicate CallNO/Title/ISBN10/MainEntry Combination
                                            BibliographicDetails  uniTitle =(BibliographicDetails) daoobj.DuplicateTitleCallno(library_id,sublibrary_id,genericobj.getCallNo(),genericobj.getTitle(),genericobj.getIsbn10(),genericobj.getMainEntry());
                                           
                                            if(uniTitle!=null)
                                            {
                                                //Check for Duplicate Accession No Entry in Accession Register
                                                List  uniTitle1 =(List) daoobj.duplicateAccessionno(library_id,sublibrary_id,genericobj.getAccessionNo());
                                                if(uniTitle1!=null)
                                                {if(uniTitle1.isEmpty()==false)
                                                {
                                                    log.add("AccessionNo already Exist  cannot import at record no"+(i+1));
                                                    session.setAttribute("importlog", log);
                                                    continue s;
                                                }}

                                                Integer maxdoc = bibdao.returnMaxDocumentId(library_id, sublibrary_id);
                                                Integer maxrecord = (Integer) bibdao.returnMaxRecord(library_id, sublibrary_id);
                                                bibobj=(BibliographicDetails)uniTitle;

                                                //Add Data in Accession Register
                                                accessionidobj.setRecordNo(maxrecord);
                                                accessionidobj.setLibraryId(library_id);
                                                accessionidobj.setSublibraryId(sublibrary_id);
                                                accessionobj.setId(accessionidobj);
                                                accessionobj.setBiblioId(bibobj.getId().getBiblioId());
                                                accessionobj.setAccessionNo(genericobj.getAccessionNo());
                                                accessionobj.setVolumeNo(genericobj.getVolumeNo());
                                                accessionobj.setLocation(genericobj.getLocation());
                                                accessionobj.setShelvingLocation(genericobj.getShelvingLocation());
                                                accessionobj.setIndexNo(genericobj.getIndexNo());
                                                accessionobj.setNoOfPages(genericobj.getNoOfPages());
                                                accessionobj.setPhysicalDescription(genericobj.getCollation1());
                                                accessionobj.setBindType(genericobj.getBindType());
                                                accessionobj.setDateAcquired(genericobj.getDateAcquired());

                                                documentIdobj.setDocumentId(maxdoc);
                                                documentIdobj.setLibraryId(library_id);
                                                documentIdobj.setSublibraryId(sublibrary_id);
                                                documentobj.setId(documentIdobj);
                                                documentobj.setStatus("available");
                                                documentobj.setBiblioId(bibobj.getId().getBiblioId());
                                                documentobj.setRecordNo(maxrecord);
                                                documentobj.setDocumentType(genericobj.getdocumentCategory());
                                                documentobj.setBookType(genericobj.getBookType());
                                                documentobj.setDateAcquired(genericobj.getDateAcquired());
                                                documentobj.setSeries(genericobj.getSeries());
                                                documentobj.setTitle(genericobj.getTitle());
                                                documentobj.setSubtitle(genericobj.getSubtitle());
                                                documentobj.setAltTitle(genericobj.getAltTitle());
                                                documentobj.setStatementResponsibility(genericobj.getStatementResponsibility());
                                                documentobj.setMainEntry(genericobj.getMainEntry());
                                                documentobj.setAddedEntry(genericobj.getAddedEntry());
                                                documentobj.setAddedEntry1(genericobj.getAddedEntry1());
                                                documentobj.setAddedEntry2(genericobj.getAddedEntry2());
                                                documentobj.setAddedEntry3(genericobj.getAddedEntry3());
                                                documentobj.setPublisherName(genericobj.getPublisherName());
                                                documentobj.setPublicationPlace(genericobj.getPublicationPlace());
                                                documentobj.setPublishingYear(genericobj.getPublishingYear());
                                                documentobj.setSubject(genericobj.getSubject());
                                                documentobj.setEntryLanguage(genericobj.getEntryLanguage());
                                                documentobj.setIsbn10(genericobj.getIsbn10());
                                                documentobj.setIsbn13(genericobj.getIsbn13());
                                                documentobj.setLccNo(genericobj.getLccNo());
                                                documentobj.setEdition(genericobj.getEdition());
                                                documentobj.setCollation1(genericobj.getCollation1());
                                                documentobj.setNotes(genericobj.getNotes());
                                                documentobj.setAbstract_(genericobj.getAbstract_());
                                                documentobj.setAccessionNo(genericobj.getAccessionNo());
                                                documentobj.setCallNo(genericobj.getCallNo());
                                                documentobj.setRecordNo(maxrecord);
                                                documentobj.setVolumeNo(genericobj.getVolumeNo());
                                                documentobj.setLocation(genericobj.getLocation());
                                                documentobj.setShelvingLocation(genericobj.getShelvingLocation());
                                                documentobj.setIndexNo(genericobj.getIndexNo());
                                                documentobj.setNoOfPages(genericobj.getNoOfPages());
                                                documentobj.setBindType(genericobj.getBindType());
                                                documentobj.setDateAcquired(genericobj.getDateAcquired());
                                                documentobj.setSeries(genericobj.getSeries());
                                                daoobj.insertAccessionRegister(accessionobj);
                                                daoobj.insertDocumentsDetails(documentobj);
//                                                List  TotalEntry =(List) DAO.getTotalNumberDocument(library_id,sublibrary_id,genericobj.getCallNo(),genericobj.getTitle());
//                                                uniTitle.setNoOfCopies(TotalEntry.size());
//                                                daoobj.update(uniTitle);
                                                count++;
                                            }
                                            else if(uniTitle==null)
                                            {
                                              
                                                 //Check for Duplicate Accession No Entry in Accession Register
                                                List  uniTitle1 =(List) daoobj.duplicateAccessionno(library_id,sublibrary_id,genericobj.getAccessionNo());
                                                if(uniTitle1!=null)
                                                {if(uniTitle1.isEmpty()==false)
                                                {
                                                    log.add("AccessionNo already Exist  cannot import at record no"+(i+1));
                                                        session.setAttribute("importlog", log);
                                                    continue s;
                                                }}
                                                System.out.println("i am here");
                                                Integer maxdoc = bibdao.returnMaxDocumentId(library_id, sublibrary_id);
                                                Integer maxrecord = (Integer) bibdao.returnMaxRecord(library_id, sublibrary_id);
                                                int bib_id=bibdao.returnMaxBiblioId(library_id,sublibrary_id);
                                                System.out.println("i am here111");
                                                bibid = new BibliographicDetailsId();
                                                bibid.setBiblioId(bib_id);
                                                bibid.setLibraryId(library_id);
                                                bibid.setSublibraryId(sublibrary_id);
                                                bibobj = new BibliographicDetails();
                                                bibobj.setId(bibid);
                                                System.out.println("i am here112");
                                                bibobj.setDocumentType(genericobj.getdocumentCategory());
                                                bibobj.setBookType(genericobj.getBookType());
                                                bibobj.setTitle(genericobj.getTitle());
                                                bibobj.setSubtitle(genericobj.getSubtitle());
                                                bibobj.setAltTitle(genericobj.getAltTitle());
                                                bibobj.setStatementResponsibility(genericobj.getStatementResponsibility());
                                                bibobj.setMainEntry(genericobj.getMainEntry());
                                                bibobj.setAddedEntry(genericobj.getAddedEntry());
                                                bibobj.setAddedEntry1(genericobj.getAddedEntry1());
                                                bibobj.setAddedEntry2(genericobj.getAddedEntry2());
                                                bibobj.setAddedEntry3(genericobj.getAddedEntry3());
                                                bibobj.setPublisherName(genericobj.getPublisherName());
                                                bibobj.setPublicationPlace(genericobj.getPublicationPlace());
                                                bibobj.setPublishingYear(Integer.parseInt(genericobj.getPublishingYear()));
                                                bibobj.setCallNo(genericobj.getCallNo());
                                          //      bibobj.setEditor1(genericobj.getEditor1());
                                          //      bibobj.setEditor2(genericobj.getEditor2());
                                          //      bibobj.setEditor3(genericobj.getEditor3());
                                         //       bibobj.setTranslator1(genericobj.getTranslator1());
                                          //      bibobj.setTranslator2(genericobj.getTranslator2());
                                          //      bibobj.setTranslator3(genericobj.getTranslator3());
                                                bibobj.setSubject(genericobj.getSubject());
                                            //    bibobj.setSubject1(genericobj.getSubject1());
                                            //    bibobj.setSubject2(genericobj.getSubject2());
                                                bibobj.setEntryLanguage(genericobj.getEntryLanguage());
                                               // bibobj.setBooklang(genericobj.getBooklang());
                                                bibobj.setIsbn10(genericobj.getIsbn10());
                                                bibobj.setIsbn13(genericobj.getIsbn13());
                                                bibobj.setLccNo(genericobj.getLccNo());
                                                bibobj.setEdition(genericobj.getEdition());
                                                bibobj.setCollation1(genericobj.getCollation1());
                                                bibobj.setNotes(genericobj.getNotes());
                                                bibobj.setAbstract_(genericobj.getAbstract_());
                                                bibobj.setSeries(genericobj.getSeries());
                                                bibobj.setDateAcquired(genericobj.getDateAcquired());

                                                //MLI IMPORT
                                              if(genericobj.getEntryLanguage().isEmpty()==false){
                                                bibobjmliId = new BibliographicDetailsLangId();
                                                bibobjmliId.setBiblioId(bib_id);
                                                bibobjmliId.setLibraryId(library_id);
                                                bibobjmliId.setSublibraryId(sublibrary_id);
                                                bibobjmli = new BibliographicDetailsLang();
                                                bibobjmli.setId(bibobjmliId);
                                                bibobjmli.setTitle(genericobj.getTitle1());
                                                bibobjmli.setMainEntry(genericobj.getMainEntry());
                                                bibobjmli.setCallNo(genericobj.getCallNo());
                                                bibobjmli.setStatementResponsibility(genericobj.getStatementResponsibility());
                                                bibobjmli.setDocumentType(genericobj.getdocumentCategory());
                                                bibobjmli.setBookType(genericobj.getBookType());
                                                bibobjmli.setEntryLanguage(genericobj.getEntryLanguage());
                                                bibobjmli.setSubtitle(genericobj.getSubtitle1());
                                                bibobjmli.setAltTitle(genericobj.getAltTitle1());
                                                bibobjmli.setStatementResponsibility(genericobj.getStatementResponsibility1());
                                                bibobjmli.setMainEntry(genericobj.getMainEntry1());
                                                bibobjmli.setAddedEntry(genericobj.getAddedEntrymli());
                                                bibobjmli.setAddedEntry1(genericobj.getAddedEntry11());
                                                bibobjmli.setAddedEntry2(genericobj.getAddedEntry21());
                                                bibobjmli.setAddedEntry3(genericobj.getAddedEntry31());
                                                bibobjmli.setPublisherName(genericobj.getPublisherName1());
                                                bibobjmli.setPublicationPlace(genericobj.getPublicationPlace1());
                                                bibobjmli.setPublishingYear(genericobj.getPublishingYear1());
                                                bibobjmli.setSubject(genericobj.getSubject1());
                                                bibobjmli.setEdition(genericobj.getEdition1());
                                                bibobjmli.setCollation1(genericobj.getCollation11());
                                                bibobjmli.setNotes(genericobj.getNotes1());
                                                bibobjmli.setAbstract_(genericobj.getAbstract1());
                                                bibobjmli.setSeries(genericobj.getSeries1());



                                              }


                                                System.out.println("i am here114");
                                                System.out.println("i am here115");
                                                System.out.println("i am here acc");
                                                //Add Data in Accession Register
                                                accessionidobj.setRecordNo(maxrecord);
                                                accessionidobj.setLibraryId(library_id);
                                                accessionidobj.setSublibraryId(sublibrary_id);
                                                accessionobj.setId(accessionidobj);
                                                accessionobj.setBiblioId(bib_id);
                                                accessionobj.setAccessionNo(genericobj.getAccessionNo());
                                                accessionobj.setVolumeNo(genericobj.getVolumeNo());
                                                accessionobj.setLocation(genericobj.getLocation());
                                                accessionobj.setShelvingLocation(genericobj.getShelvingLocation());
                                                accessionobj.setIndexNo(genericobj.getIndexNo());
                                                accessionobj.setNoOfPages(genericobj.getNoOfPages());
                                                accessionobj.setPhysicalDescription(genericobj.getCollation1());
                                                accessionobj.setBindType(genericobj.getBindType());
                                                accessionobj.setDateAcquired(genericobj.getDateAcquired());
                                                System.out.println("i am here doc");
                                                documentIdobj.setDocumentId(maxdoc);
                                                documentIdobj.setLibraryId(library_id);
                                                documentIdobj.setSublibraryId(sublibrary_id);
                                                documentobj.setId(documentIdobj);
                                                documentobj.setStatus("available");
                                                documentobj.setBiblioId(bib_id);
                                                documentobj.setRecordNo(maxrecord);
                                                documentobj.setDocumentType(genericobj.getdocumentCategory());
                                                documentobj.setBookType(genericobj.getBookType());
                                                documentobj.setDateAcquired(genericobj.getDateAcquired());
                                                documentobj.setSeries(genericobj.getSeries());
                                                documentobj.setTitle(genericobj.getTitle());
                                                documentobj.setSubtitle(genericobj.getSubtitle());
                                                documentobj.setAltTitle(genericobj.getAltTitle());
                                                documentobj.setStatementResponsibility(genericobj.getStatementResponsibility());
                                                documentobj.setMainEntry(genericobj.getMainEntry());
                                                documentobj.setAddedEntry(genericobj.getAddedEntry());
                                                documentobj.setAddedEntry1(genericobj.getAddedEntry1());
                                                documentobj.setAddedEntry2(genericobj.getAddedEntry2());
                                                documentobj.setAddedEntry3(genericobj.getAddedEntry3());
                                                documentobj.setPublisherName(genericobj.getPublisherName());
                                                documentobj.setPublicationPlace(genericobj.getPublicationPlace());
                                                documentobj.setPublishingYear(genericobj.getPublishingYear());
                                                documentobj.setSubject(genericobj.getSubject());
                                                documentobj.setEntryLanguage(genericobj.getEntryLanguage());
                                                documentobj.setIsbn10(genericobj.getIsbn10());
                                                documentobj.setIsbn13(genericobj.getIsbn13());
                                                documentobj.setLccNo(genericobj.getLccNo());
                                                documentobj.setEdition(genericobj.getEdition());
                                                documentobj.setCollation1(genericobj.getCollation1());
                                                documentobj.setNotes(genericobj.getNotes());
                                                documentobj.setAbstract_(genericobj.getAbstract_());
                                                documentobj.setAccessionNo(genericobj.getAccessionNo());
                                                documentobj.setCallNo(genericobj.getCallNo());
                                                documentobj.setRecordNo(maxrecord);
                                                documentobj.setVolumeNo(genericobj.getVolumeNo());
                                                documentobj.setLocation(genericobj.getLocation());
                                                documentobj.setShelvingLocation(genericobj.getShelvingLocation());
                                                documentobj.setIndexNo(genericobj.getIndexNo());
                                                documentobj.setNoOfPages(genericobj.getNoOfPages());
                                                documentobj.setBindType(genericobj.getBindType());
                                                documentobj.setDateAcquired(genericobj.getDateAcquired());
                                                documentobj.setSeries(genericobj.getSeries());
                                                //insert in three table jointly
                                                if(genericobj.getEntryLanguage().isEmpty()==false){
                                                daoobj.insertImport(bibobj,accessionobj,documentobj,bibobjmli);
                                                }
                                                else{
                                                daoobj.insertImport(bibobj,accessionobj,documentobj);
                                                }
//                                                List  TotalEntry =(List) DAO.getTotalNumberDocument(library_id,sublibrary_id,genericobj.getCallNo(),genericobj.getTitle());
//                                                uniTitle.setNoOfCopies(TotalEntry.size());
//                                                daoobj.update(uniTitle);
                                                count++;
                                            }
                        }
                        else if((genericobj.getCallNo()!=null && genericobj.getCallNo().isEmpty()==false) && (genericobj.getIsbn10()==null ||  genericobj.getIsbn10().isEmpty()==true) && (genericobj.getTitle()!=null && genericobj.getTitle().isEmpty()==false) && (genericobj.getMainEntry()!=null && genericobj.getMainEntry().isEmpty()==false))
                        {
                            log4j.error("In LOg");
                            //Add Data in Bibliographic Details
                            BibliographicDetails bibobj;
                            BibliographicDetailsId bibid;
                            BibliographicDetailsLang bibobjmli=new BibliographicDetailsLang();
                            BibliographicDetailsLangId bibobjmliId=new BibliographicDetailsLangId();

                            AccessionRegister accessionobj = new AccessionRegister();
                            AccessionRegisterId accessionidobj = new AccessionRegisterId();
                            DocumentDetails documentobj = new DocumentDetails();
                            DocumentDetailsId documentIdobj = new DocumentDetailsId();

                                            //Check for Duplicate CallNO/Title/ISBN10/MainEntry Combination
                                            BibliographicDetails  uniTitle =(BibliographicDetails) daoobj.DuplicateTitleCallno(library_id,sublibrary_id,genericobj.getCallNo(),genericobj.getTitle(),genericobj.getMainEntry());
                                            
                                            if(uniTitle!=null)
                                            {
                                                
                                                 log4j.error("In LOg1");
                                                //Check for Duplicate Accession No Entry in Accession Register
                                                List  uniTitle1 =(List) daoobj.duplicateAccessionno(library_id,sublibrary_id,genericobj.getAccessionNo());
                                                if(uniTitle1!=null)
                                                {if(uniTitle1.isEmpty()==false)
                                                {
                                                    log.add("AccessionNo already Exist  cannot import at record no"+(i+1));
                                                        session.setAttribute("importlog", log);
                                                    continue s;
                                                }}
                                                Integer maxdoc = bibdao.returnMaxDocumentId(library_id, sublibrary_id);
                                                Integer maxrecord = (Integer) bibdao.returnMaxRecord(library_id, sublibrary_id);
                                                 bibobj=(BibliographicDetails)uniTitle;
                                                //Add Data in Accession Register
                                                accessionidobj.setRecordNo(maxrecord);
                                                accessionidobj.setLibraryId(library_id);
                                                accessionidobj.setSublibraryId(sublibrary_id);
                                                accessionobj.setId(accessionidobj);
                                                accessionobj.setBiblioId(bibobj.getId().getBiblioId());
                                                accessionobj.setAccessionNo(genericobj.getAccessionNo());
                                                accessionobj.setVolumeNo(genericobj.getVolumeNo());
                                                accessionobj.setLocation(genericobj.getLocation());
                                                accessionobj.setShelvingLocation(genericobj.getShelvingLocation());
                                                accessionobj.setIndexNo(genericobj.getIndexNo());
                                                accessionobj.setNoOfPages(genericobj.getNoOfPages());
                                                accessionobj.setPhysicalDescription(genericobj.getCollation1());
                                                accessionobj.setBindType(genericobj.getBindType());
                                                accessionobj.setDateAcquired(genericobj.getDateAcquired());

                                                documentIdobj.setDocumentId(maxdoc);
                                                documentIdobj.setLibraryId(library_id);
                                                documentIdobj.setSublibraryId(sublibrary_id);
                                                documentobj.setId(documentIdobj);
                                                documentobj.setStatus("available");
                                                documentobj.setBiblioId(bibobj.getId().getBiblioId());
                                                documentobj.setRecordNo(maxrecord);
                                                documentobj.setDocumentType(genericobj.getdocumentCategory());
                                                documentobj.setBookType(genericobj.getBookType());
                                                documentobj.setDateAcquired(genericobj.getDateAcquired());
                                                documentobj.setSeries(genericobj.getSeries());
                                                documentobj.setTitle(genericobj.getTitle());
                                                documentobj.setSubtitle(genericobj.getSubtitle());
                                                documentobj.setAltTitle(genericobj.getAltTitle());
                                                documentobj.setStatementResponsibility(genericobj.getStatementResponsibility());
                                                documentobj.setMainEntry(genericobj.getMainEntry());
                                                documentobj.setAddedEntry(genericobj.getAddedEntry());
                                                documentobj.setAddedEntry1(genericobj.getAddedEntry1());
                                                documentobj.setAddedEntry2(genericobj.getAddedEntry2());
                                                documentobj.setAddedEntry3(genericobj.getAddedEntry3());
                                                documentobj.setPublisherName(genericobj.getPublisherName());
                                                documentobj.setPublicationPlace(genericobj.getPublicationPlace());
                                                documentobj.setPublishingYear(genericobj.getPublishingYear());
                                                documentobj.setSubject(genericobj.getSubject());
                                                documentobj.setEntryLanguage(genericobj.getEntryLanguage());
                                                documentobj.setIsbn10(genericobj.getIsbn10());
                                                documentobj.setIsbn13(genericobj.getIsbn13());
                                                documentobj.setLccNo(genericobj.getLccNo());
                                                documentobj.setEdition(genericobj.getEdition());
                                                documentobj.setCollation1(genericobj.getCollation1());
                                                documentobj.setNotes(genericobj.getNotes());
                                                documentobj.setAbstract_(genericobj.getAbstract_());
                                                documentobj.setAccessionNo(genericobj.getAccessionNo());
                                                documentobj.setCallNo(genericobj.getCallNo());
                                                documentobj.setRecordNo(maxrecord);
                                                documentobj.setVolumeNo(genericobj.getVolumeNo());
                                                documentobj.setLocation(genericobj.getLocation());
                                                documentobj.setShelvingLocation(genericobj.getShelvingLocation());
                                                documentobj.setIndexNo(genericobj.getIndexNo());
                                                documentobj.setNoOfPages(genericobj.getNoOfPages());
                                                documentobj.setBindType(genericobj.getBindType());
                                                documentobj.setDateAcquired(genericobj.getDateAcquired());
                                                documentobj.setSeries(genericobj.getSeries());
                                                daoobj.insertAccessionRegister(accessionobj);
                                                daoobj.insertDocumentsDetails(documentobj);
                                               //  List  TotalEntry =(List) DAO.getTotalNumberDocument(library_id,sublibrary_id,genericobj.getCallNo(),genericobj.getTitle());
                                              //  uniTitle.setNoOfCopies(TotalEntry.size());
                                             //   daoobj.update(uniTitle);
                                                count++;
                                            
                                            }
                                            else
                                            {
                                                
                                                 //Check for Duplicate Accession No Entry in Accession Register
                                                List  uniTitle1 =(List) daoobj.duplicateAccessionno(library_id,sublibrary_id,genericobj.getAccessionNo());
                                                if(uniTitle1!=null)
                                                {if(uniTitle1.isEmpty()==false)
                                                {
                                                    log.add("AccessionNo already Exist  cannot import at record no"+(i+1));
                                                        session.setAttribute("importlog", log);
                                                    continue s;
                                                }}
                                                Integer maxdoc = bibdao.returnMaxDocumentId(library_id, sublibrary_id);
                                                Integer maxrecord = (Integer) bibdao.returnMaxRecord(library_id, sublibrary_id);
                                                int bib_id=bibdao.returnMaxBiblioId(library_id,sublibrary_id);

                                                bibobj = new BibliographicDetails();
                                                bibid = new BibliographicDetailsId();
                                                bibid.setBiblioId(bib_id);
                                                bibid.setLibraryId(library_id);
                                                bibid.setSublibraryId(sublibrary_id);
                                                bibobj.setId(bibid);
                                                bibobj.setDocumentType(genericobj.getdocumentCategory());
                                                bibobj.setBookType(genericobj.getBookType());
                                                bibobj.setTitle(genericobj.getTitle());
                                                bibobj.setSubtitle(genericobj.getSubtitle());
                                                bibobj.setAltTitle(genericobj.getAltTitle());
                                                bibobj.setStatementResponsibility(genericobj.getStatementResponsibility());
                                                bibobj.setMainEntry(genericobj.getMainEntry());
                                                bibobj.setAddedEntry(genericobj.getAddedEntry());
                                                bibobj.setAddedEntry1(genericobj.getAddedEntry1());
                                                bibobj.setAddedEntry2(genericobj.getAddedEntry2());
                                                bibobj.setAddedEntry3(genericobj.getAddedEntry3());

                                              //  bibobj.setEditor1(genericobj.getEditor1());
                                               // bibobj.setEditor2(genericobj.getEditor2());
                                             //   bibobj.setEditor3(genericobj.getEditor3());
                                             //   bibobj.setTranslator1(genericobj.getTranslator1());
                                            //    bibobj.setTranslator2(genericobj.getTranslator2());
                                            //    bibobj.setTranslator3(genericobj.getTranslator3());
                                            //    bibobj.setSubject1(genericobj.getSubject1());
                                           //     bibobj.setSubject2(genericobj.getSubject2());
                                            //    bibobj.setBooklang(genericobj.getBooklang());

                                                bibobj.setPublisherName(genericobj.getPublisherName());
                                                bibobj.setPublicationPlace(genericobj.getPublicationPlace());
                                                bibobj.setPublishingYear(Integer.parseInt(genericobj.getPublishingYear()));
                                                bibobj.setCallNo(genericobj.getCallNo());
                                                bibobj.setSubject(genericobj.getSubject());
                                                bibobj.setEntryLanguage(genericobj.getEntryLanguage());
                                                bibobj.setIsbn10(genericobj.getIsbn10());
                                                bibobj.setIsbn13(genericobj.getIsbn13());
                                                bibobj.setLccNo(genericobj.getLccNo());
                                                bibobj.setEdition(genericobj.getEdition());
                                                bibobj.setCollation1(genericobj.getCollation1());
                                                bibobj.setNotes(genericobj.getNotes());
                                                bibobj.setAbstract_(genericobj.getAbstract_());
                                                bibobj.setSeries(genericobj.getSeries());
                                                bibobj.setDateAcquired(genericobj.getDateAcquired());

                                                //bibobj.setNoOfCopies(Integer.parseInt(genericobj.getNoOfCopies()));
                                                //ADD MLI
                                                 if(genericobj.getEntryLanguage().isEmpty()==false){
                                                bibobjmliId = new BibliographicDetailsLangId();
                                                bibobjmliId.setBiblioId(bib_id);
                                                bibobjmliId.setLibraryId(library_id);
                                                bibobjmliId.setSublibraryId(sublibrary_id);
                                                bibobjmli = new BibliographicDetailsLang();
                                                bibobjmli.setId(bibobjmliId);
                                                bibobjmli.setTitle(genericobj.getTitle1());
                                                bibobjmli.setMainEntry(genericobj.getMainEntry());
                                                bibobjmli.setCallNo(genericobj.getCallNo());
                                                bibobjmli.setStatementResponsibility(genericobj.getStatementResponsibility());
                                                bibobjmli.setDocumentType(genericobj.getdocumentCategory());
                                                bibobjmli.setBookType(genericobj.getBookType());
                                                bibobjmli.setEntryLanguage(genericobj.getEntryLanguage());
                                               bibobjmli.setSubtitle(genericobj.getSubtitle1());
                                                bibobjmli.setAltTitle(genericobj.getAltTitle1());
                                                bibobjmli.setStatementResponsibility(genericobj.getStatementResponsibility1());
                                                bibobjmli.setMainEntry(genericobj.getMainEntry1());
                                                bibobjmli.setAddedEntry(genericobj.getAddedEntrymli());
                                                bibobjmli.setAddedEntry1(genericobj.getAddedEntry11());
                                                bibobjmli.setAddedEntry2(genericobj.getAddedEntry21());
                                                bibobjmli.setAddedEntry3(genericobj.getAddedEntry31());
                                                bibobjmli.setPublisherName(genericobj.getPublisherName1());
                                                bibobjmli.setPublicationPlace(genericobj.getPublicationPlace1());
                                                bibobjmli.setPublishingYear(genericobj.getPublishingYear1());
                                                bibobjmli.setSubject(genericobj.getSubject1());
                                                bibobjmli.setEdition(genericobj.getEdition1());
                                                bibobjmli.setCollation1(genericobj.getCollation11());
                                                bibobjmli.setNotes(genericobj.getNotes1());
                                                bibobjmli.setAbstract_(genericobj.getAbstract1());
                                                bibobjmli.setSeries(genericobj.getSeries1());
                                                
                                                 }


                                                //Add Data in Accession Register
                                                accessionidobj.setRecordNo(maxrecord);
                                                accessionidobj.setLibraryId(library_id);
                                                accessionidobj.setSublibraryId(sublibrary_id);
                                                accessionobj.setId(accessionidobj);
                                                accessionobj.setBiblioId(bib_id);
                                                accessionobj.setAccessionNo(genericobj.getAccessionNo());
                                                accessionobj.setVolumeNo(genericobj.getVolumeNo());
                                                accessionobj.setLocation(genericobj.getLocation());
                                                accessionobj.setShelvingLocation(genericobj.getShelvingLocation());
                                                accessionobj.setIndexNo(genericobj.getIndexNo());
                                                accessionobj.setNoOfPages(genericobj.getNoOfPages());
                                                accessionobj.setPhysicalDescription(genericobj.getCollation1());
                                                accessionobj.setBindType(genericobj.getBindType());
                                                accessionobj.setDateAcquired(genericobj.getDateAcquired());

                                                documentIdobj.setDocumentId(maxdoc);
                                                documentIdobj.setLibraryId(library_id);
                                                documentIdobj.setSublibraryId(sublibrary_id);
                                                documentobj.setId(documentIdobj);
                                                documentobj.setStatus("available");
                                                documentobj.setBiblioId(bib_id);
                                                documentobj.setRecordNo(maxrecord);
                                                documentobj.setDocumentType(genericobj.getdocumentCategory());
                                                documentobj.setBookType(genericobj.getBookType());
                                                documentobj.setDateAcquired(genericobj.getDateAcquired());
                                                documentobj.setSeries(genericobj.getSeries());
                                                documentobj.setTitle(genericobj.getTitle());
                                                documentobj.setSubtitle(genericobj.getSubtitle());
                                                documentobj.setAltTitle(genericobj.getAltTitle());
                                                documentobj.setStatementResponsibility(genericobj.getStatementResponsibility());
                                                documentobj.setMainEntry(genericobj.getMainEntry());
                                                documentobj.setAddedEntry(genericobj.getAddedEntry());
                                                documentobj.setAddedEntry1(genericobj.getAddedEntry1());
                                                documentobj.setAddedEntry2(genericobj.getAddedEntry2());
                                                documentobj.setAddedEntry3(genericobj.getAddedEntry3());
                                                documentobj.setPublisherName(genericobj.getPublisherName());
                                                documentobj.setPublicationPlace(genericobj.getPublicationPlace());
                                                documentobj.setPublishingYear(genericobj.getPublishingYear());
                                                documentobj.setSubject(genericobj.getSubject());
                                                documentobj.setEntryLanguage(genericobj.getEntryLanguage());
                                                documentobj.setIsbn10(genericobj.getIsbn10());
                                                documentobj.setIsbn13(genericobj.getIsbn13());
                                                documentobj.setLccNo(genericobj.getLccNo());
                                                documentobj.setEdition(genericobj.getEdition());
                                                documentobj.setCollation1(genericobj.getCollation1());
                                                documentobj.setNotes(genericobj.getNotes());
                                                documentobj.setAbstract_(genericobj.getAbstract_());
                                                documentobj.setAccessionNo(genericobj.getAccessionNo());
                                                documentobj.setCallNo(genericobj.getCallNo());
                                                documentobj.setRecordNo(maxrecord);
                                                documentobj.setVolumeNo(genericobj.getVolumeNo());
                                                documentobj.setLocation(genericobj.getLocation());
                                                documentobj.setShelvingLocation(genericobj.getShelvingLocation());
                                                documentobj.setIndexNo(genericobj.getIndexNo());
                                                documentobj.setNoOfPages(genericobj.getNoOfPages());
                                                documentobj.setBindType(genericobj.getBindType());
                                                documentobj.setDateAcquired(genericobj.getDateAcquired());
                                                documentobj.setSeries(genericobj.getSeries());
                                                log4j.error("Upload Data");
                                                //insert in three table jointly
                                                 if(genericobj.getEntryLanguage().isEmpty()==false){
                                                daoobj.insertImport(bibobj,accessionobj,documentobj,bibobjmli);
                                                }
                                                else{
                                                daoobj.insertImport(bibobj,accessionobj,documentobj);
                                                }
                                             //   List  TotalEntry =(List) DAO.getTotalNumberDocument(library_id,sublibrary_id,genericobj.getCallNo(),genericobj.getTitle());
                                             //   uniTitle.setNoOfCopies(TotalEntry.size());
                                             //   daoobj.update(uniTitle);
                                                count++;
                                            }


                    }

                                           // log4j.error("Row NO=" + i+genericobj.getTitle());
               
               }
               
                String userid=(String)session.getAttribute("login_id");
                String nameOfTextFile = userid+"importlog.txt";
                UserLog.ErrorListLog(log, nameOfTextFile);
                request.setAttribute("msg1", "Import Process Completed : " + count + "    : records successfully added in Catalog outoff total "+i+" records");
                session.setAttribute("importlog", log);    
                return mapping.findForward("success");
            }//first while
    }
          catch (org.apache.poi.poifs.filesystem.OfficeXmlFileException e) {
            request.setAttribute("error", "Please input only xcell file supported with upto windoz xp");
            return mapping.findForward("Back");
        } catch (java.lang.IllegalArgumentException e) {
            request.setAttribute("error", "Please input only xcell file supported with upto windoz xp");
            return mapping.findForward("Back");
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the specified path.");
            // e.printStackTrace();
            request.setAttribute("error", e);
            return mapping.findForward("Back");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg1", "Error In Importing due to "+e.getMessage());
           request.setAttribute("error", e);
            return mapping.findForward("Back");
        }
        return null;
    }
       public boolean Datatype(String t)
       {
            char d=t.charAt(0);
            switch(d){
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                     return true;

        default:
            return false;
    }
    }
}

class MyExcep extends Exception{
String Mess;
MyExcep(String field ){
this.Mess=field;

}
}