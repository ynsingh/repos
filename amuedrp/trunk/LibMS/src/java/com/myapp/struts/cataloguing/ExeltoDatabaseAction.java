/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.cataloguing;


import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import com.myapp.struts.cataloguingDAO.DAO;
import com.myapp.struts.hbm.AccessionRegister;
import com.myapp.struts.hbm.AccessionRegisterId;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsId;
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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;

/**
 *
 * @author khushnood
 */
public class ExeltoDatabaseAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    public String library_id;
    public String sublibrary_id;


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try{

        StrutsUploadForm uploadForm = (StrutsUploadForm) form;

        // Object genericobj=new Object();
         TempExcellImport genericobj = new  TempExcellImport();




         TempExcellImport obj = new  TempExcellImport();
        DAO dataaccess = new DAO();


        HttpSession session = request.getSession();
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");


        dataaccess.TruncateTempTable(library_id,sublibrary_id);
        int row_no = 0;

        String table_name = uploadForm.getCombo_table_name();
        int size = DAO.columnname(table_name).size();
        int sheet_column_size=0;
          if (session.getAttribute("no_columns") != null)
          {
                                sheet_column_size = (Integer) session.getAttribute("no_columns");
          }


        System.out.println("I Am here");
        InputStream inputStream = null;




            FormFile myFile = uploadForm.getExcelFile();


           // HSSFWorkbook workbook = new HSSFWorkbook(myFile.getInputStream());
            inputStream = (myFile.getInputStream());
       //     System.out.println("++++++++++++++++my file  :::::::" + myFile.getFileName().toString());


        POIFSFileSystem fileSystem = null;


            fileSystem = new POIFSFileSystem(inputStream);
            HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
            HSSFSheet sheet = workBook.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();
            String cellvalue = "";


            String map_table[] = new String[Math.max(sheet_column_size ,size)];

            map_table = (String[]) DAO.selectedcombo(map_table, (size), uploadForm);
            for (int j = 0; j < size-1; j++) {
                System.out.println("selected combo box is" + j + ":::::: " + map_table[j]);
            }


            if (uploadForm.getButton().equals("Back")) {

                return mapping.findForward("Back");
            }
            if (uploadForm.getButton().equals("Import Data")) {



                rows.next();
                while (rows.hasNext()) {
                     System.out.println("this is row no::::::::: " + row_no);



                    Row row = rows.next();
                    row_no = row.getRowNum();


                    // once get a row its time to iterate through cells.
                    Iterator<Cell> cells = row.cellIterator();



                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        cell.setCellType(Cell.CELL_TYPE_STRING);

                            switch (cell.getCellType())
                            {
                                case HSSFCell.CELL_TYPE_BOOLEAN:
                                {

                                    // cell type numeric.
                                    if (cell.getBooleanCellValue() == true) {
                                        cellvalue = "True";
                                    }   {
                                        cellvalue = "false";
                                    }





                                }

                                case HSSFCell.CELL_TYPE_STRING: {

                                    // cell type string.
                                    RichTextString richTextString = cell.getRichStringCellValue();
                                    cellvalue = cell.getRichStringCellValue().getString();
                                    System.out.println("String value: " + richTextString.getString());

                                    break;
                                }


                                default:
                                {

                                    // types other than String and Numeric.
                                    cellvalue = "Unable to read";
                                    System.out.println("Type not supported.");

                                    break;
                                }

                            }

                            int column_index = cell.getColumnIndex();
                            System.out.println(table_name);
                            if (table_name.equals("bibliographic_details"))
                            {
                                System.out.println("column index::::::::::::." + column_index);






                                         if(size>column_index){

                                           if (map_table[column_index].equals("library_id")) {
                                           genericobj.setLibraryId(cellvalue.trim());


                                        }   if (map_table[column_index].equals("sublibrary_id")) {
                                            genericobj.setSublibraryId(cellvalue.trim());


                                        }
                                         if (map_table[column_index].equals("document_type")) {
                                            genericobj.setDocumentType(cellvalue.trim());

                                        }   if (map_table[column_index].equals("book_type")) {
                                            genericobj.setBookType(cellvalue.trim());

                                        }   if (map_table[column_index].equals("accession_type")) {
                                            genericobj.setAccessionType(cellvalue.trim());


                                        }   if (map_table[column_index].equals("date_acquired")) {
                                            genericobj.setDateAcquired(cellvalue.trim());


                                        }   if (map_table[column_index].equals("title")) {
                                            genericobj.setTitle(cellvalue.trim());

                                        }   if (map_table[column_index].equals("subtitle")) {
                                            genericobj.setSubtitle(cellvalue.trim());


                                        }   if (map_table[column_index].equals("alt_title")) {
                                            genericobj.setAltTitle(cellvalue.trim());

                                        }   if (map_table[column_index].equals("statement_responsibility")) {
                                            genericobj.setStatementResponsibility(cellvalue.trim());

                                        }   if (map_table[column_index].equals("main_entry")) {
                                            genericobj.setMainEntry(cellvalue.trim());

                                        }   if (map_table[column_index].equals("added_entry")) {
                                            genericobj.setAddedEntry(cellvalue.trim());

                                        }   if (map_table[column_index].equals("added_entry1")) {
                                            genericobj.setAddedEntry1(cellvalue.trim());

                                        }   if (map_table[column_index].equals("added_entry2")) {
                                            genericobj.setAddedEntry2(cellvalue.trim());

                                        }   if (map_table[column_index].equals("added_entry3")) {
                                            genericobj.setAddedEntry3(cellvalue.trim());

                                        }   if (map_table[column_index].equals("publisher_name")) {
                                            genericobj.setPublisherName(cellvalue.trim());

                                        }   if (map_table[column_index].equals("publication_place")) {
                                            genericobj.setPublicationPlace(cellvalue.trim());

                                        }   if (map_table[column_index].equals("publishing_year")) {
                                            genericobj.setPublishingYear(cellvalue.trim());

                                        }   if (map_table[column_index].equals("call_no")) {
                                            genericobj.setCallNo(cellvalue.trim());

                                        }
                                           if (map_table[column_index].equals("parts_no")) {
                                            genericobj.setPartsNo(cellvalue.trim());

                                        }
                                         if (map_table[column_index].equals("subject")) {
                                            genericobj.setSubject(cellvalue.trim());

                                        }   if (map_table[column_index].equals("entry_language")) {
                                            genericobj.setEntryLanguage(cellvalue.trim());

                                        }   if (map_table[column_index].equals("isbn10")) {
                                            genericobj.setIsbn10(cellvalue.trim());

                                        }   if (map_table[column_index].equals("isbn13")) {
                                            genericobj.setIsbn13(cellvalue.trim());

                                        }   if (map_table[column_index].equals("LCC_no")) {
                                            genericobj.setLccNo(cellvalue.trim());

                                        }   if (map_table[column_index].equals("edition")) {
                                            genericobj.setEdition(cellvalue.trim());

                                        }
                                           if (map_table[column_index].equals("no_of_copies")) {
                                            genericobj.setNoOfCopies(cellvalue.trim());

                                        }
                                           if (map_table[column_index].equals("author_name")) {
                                            genericobj.setAuthorName(cellvalue.trim());

                                        }   if (map_table[column_index].equals("guide_name")) {
                                            genericobj.setGuideName(cellvalue.trim());

                                        }   if (map_table[column_index].equals("university_faculty")) {
                                            genericobj.setUniversityFaculty(cellvalue.trim());

                                        }   if (map_table[column_index].equals("degree")) {
                                            genericobj.setDegree(cellvalue.trim());

                                        }   if (map_table[column_index].equals("submitted_on")) {
                                            genericobj.setSubmittedOn(cellvalue.trim());

                                        }   if (map_table[column_index].equals("acceptance_year")) {
                                            genericobj.setAcceptanceYear(cellvalue.trim());

                                        }   if (map_table[column_index].equals("collation1")) {
                                            genericobj.setCollation1(cellvalue.trim());

                                        }   if (map_table[column_index].equals("notes")) {
                                            genericobj.setNotes(cellvalue.trim());

                                        }   if (map_table[column_index].equals("abstract")) {
                                            genericobj.setAbstract_(cellvalue.trim());

                                        }   if (map_table[column_index].equals("address")) {
                                            genericobj.setAddress(cellvalue.trim());

                                        }   if (map_table[column_index].equals("state1")) {
                                            genericobj.setState1(cellvalue.trim());

                                        }   if (map_table[column_index].equals("country")) {
                                            genericobj.setCountry(cellvalue.trim());

                                        }   if (map_table[column_index].equals("email")) {
                                            genericobj.setEmail(cellvalue.trim());

                                        }   if (map_table[column_index].equals("frmr_frq")) {
                                            genericobj.setFrmrFrq(cellvalue.trim());

                                        }   if (map_table[column_index].equals("frq_date")) {
                                            genericobj.setFrqDate(cellvalue.trim());

                                        }   if (map_table[column_index].equals("issn")) {
                                            genericobj.setIssn(cellvalue.trim());

                                        }   if (map_table[column_index].equals("volume_location")) {
                                            genericobj.setVolumeLocation(cellvalue.trim());

                                        }
                                           if (map_table[column_index].equals("production_year")) {
                                            genericobj.setProductionYear(cellvalue.trim());

                                        }
                                           if (map_table[column_index].equals("source1")) {
                                            genericobj.setSource1(cellvalue.trim());

                                        }   if (map_table[column_index].equals("duration")) {
                                            genericobj.setDuration(cellvalue.trim());

                                        }   if (map_table[column_index].equals("series")) {
                                            genericobj.setSeries(cellvalue.trim());

                                        }   if (map_table[column_index].equals("type_of_disc")) {
                                            genericobj.setTypeOfDisc(cellvalue.trim());

                                        }   if (map_table[column_index].equals("file_type")) {
                                            genericobj.setFileType(cellvalue.trim());

                                        }

                                            if (map_table[column_index].equals("accession_no")) {
                                            genericobj.setAccessionNo(cellvalue.trim());

                                        }
                                            if (map_table[column_index].equals("record_no")) {
                                            genericobj.setRecordNo(cellvalue.trim());

                                        }
                                            if (map_table[column_index].equals("volume_no")) {
                                            genericobj.setVolumeNo(cellvalue.trim());

                                        }
                                            if (map_table[column_index].equals("location")) {
                                            genericobj.setLocation(cellvalue.trim());

                                        }
                                            if (map_table[column_index].equals("shelving_location")) {
                                            genericobj.setShelvingLocation(cellvalue.trim());

                                        }
                                            if (map_table[column_index].equals("index_no")) {
                                            genericobj.setIndexNo(cellvalue.trim());

                                        }
                                            if (map_table[column_index].equals("physical_width")) {
                                            genericobj.setPhysicalWidth(cellvalue.trim());

                                        }
                                            if (map_table[column_index].equals("physical_form")) {
                                            genericobj.setPhysicalForm(cellvalue.trim());

                                        }
                                            if (map_table[column_index].equals("physical_description")) {
                                            genericobj.setPhysicalDescription(cellvalue.trim());

                                        }
                                            if (map_table[column_index].equals("colour")) {
                                            genericobj.setColour(cellvalue.trim());

                                        }

                                         if (map_table[column_index].equals("bind_type")) {
                                            genericobj.setBindType(cellvalue.trim());

                                        }
                                         if (map_table[column_index].equals("status")) {
                                            genericobj.setStatus(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("book_language")) {
                                            genericobj.setStatus(cellvalue.trim());

                                        }
                                        if (map_table[column_index].equals("ref_no")) {
                                            genericobj.setStatus(cellvalue.trim());

                                        }





                                         }else{
                                         break;
                                         }




                            }
                            System.out.println("column index::::::::::::." + column_index);

                            System.out.println("This is the values in the table :::1::::::" + map_table[cell.getColumnIndex()] + "::::::::::::::::::::");

                        }

                        System.out.println("++++++++++++++++++++++++++insert funcion is going to call");

                        dataaccess.insertgeneric(genericobj);
                        System.out.println("++++++++++++++++++++++++++insert funcion is  called");


                    }
              System.out.println("Temp Table Record Conversion OK");



        TempExcellImport temobj = new TempExcellImport();
        TempExcellImport temobj2 = new TempExcellImport();
        BibliographicDetails bibobj = new BibliographicDetails();
        BibliographicDetailsId bibid = new BibliographicDetailsId();
        AccessionRegister accessionobj = new AccessionRegister();
        AccessionRegisterId accessionidobj = new AccessionRegisterId();
        DocumentDetails documentobj = new DocumentDetails();
        DocumentDetailsId documentIdobj = new DocumentDetailsId();
        BibliopgraphicEntryDAO bibdao=new BibliopgraphicEntryDAO();

        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");
        DAO daoobj = new DAO();
        int distinctTitleSize = 0;

        try {
              ArrayList<Integer> biblist=new ArrayList<Integer>();
            distinctTitleSize = daoobj.distintTitle(library_id,sublibrary_id).size();


            String distinctTitleArray[] = new String[distinctTitleSize];
            List distinctCallNo= (List<String>)daoobj.distintTitle(library_id,sublibrary_id);
            System.out.println(distinctTitleSize+".........................");
            for (int i = 0; i < distinctTitleSize; i++) {


            //for(int k=0;k<distinctCallNo.size();k++){

                    List  uniTitle =(List<String>) daoobj.distintTitleCallno(library_id,sublibrary_id,distinctCallNo.get(i).toString());
                    if(uniTitle.size()>1){
                    boolean result= DAO.TruncateTempTable(library_id,sublibrary_id);
                    throw new MyExcep("CAllNo and Title are not uniques combination"+uniTitle.get(0).toString());


                   }
                   //Check for Duplicate Accession No Entry

                     List  uniTitle1 =(List<String>) daoobj.distintTitleAccessionno(library_id,sublibrary_id);
                    if(uniTitle1.size()>1){
                    boolean result= DAO.TruncateTempTable(library_id,sublibrary_id);
                    throw new MyExcep("AccessionNo is duplicate cannot import"+uniTitle1.get(0).toString());


                   }











                    distinctTitleArray[i] = uniTitle.get(0).toString();

          //  }

            }


      //      if (uploadForm.getButton().equals("Import Data")) {
                for (int j = 0; j < distinctTitleSize; j++) {
                 //   System.out.println(distinctTitleArray[j]+"   "+distinctCallNo.get(j).toString());
                    List list = daoobj.AllrecordForGivenTitle(distinctTitleArray[j],distinctCallNo.get(j).toString());

                          //  for(int i=0;i<list.size();i++)
                        //    {
                    temobj = (TempExcellImport) list.get(0);
                    System.out.println("+++++++++++++++distint title arry++++" + distinctTitleArray[0]);

//BibliographicDetails ob=DAO.searchBiblio(library_id,sublibrary_id,distinctTitleArray[j],distinctCallNo.get(j).toString());
//if(ob==null)
//{
                        int bib_id=bibdao.returnMaxBiblioId(temobj.getLibraryId(),temobj.getSublibraryId());
                        bibid.setBiblioId(bib_id);

                    bibid.setLibraryId(temobj.getLibraryId());
                    bibid.setSublibraryId(temobj.getSublibraryId());
                    bibobj.setId(bibid);
                    bibobj.setDocumentType(temobj.getDocumentType());
                    bibobj.setBookType(temobj.getBookType());
                    bibobj.setAccessionType(temobj.getAccessionType());
                    bibobj.setTitle(temobj.getTitle());
                    bibobj.setSubtitle(temobj.getSubtitle());
                    bibobj.setAltTitle(temobj.getAltTitle());
                    bibobj.setStatementResponsibility(temobj.getStatementResponsibility());
                    bibobj.setMainEntry(temobj.getMainEntry());
                    bibobj.setAddedEntry(temobj.getAddedEntry());
                    bibobj.setAddedEntry1(temobj.getAddedEntry1());
                    bibobj.setAddedEntry2(temobj.getAddedEntry2());
                    bibobj.setAddedEntry3(temobj.getAddedEntry3());
                    bibobj.setPublisherName(temobj.getPublisherName());
                    bibobj.setPublicationPlace(temobj.getPublicationPlace());
                    bibobj.setPublishingYear(temobj.getPublishingYear());

                    bibobj.setCallNo(temobj.getCallNo());
                    if(temobj.getPartsNo()!=null)
                    {
                       String t=temobj.getPartsNo();
                        boolean type=Datatype(t);
                        System.out.println(t+"........"+type);
                        if(type==false)
                        throw new MyExcep("Parts No");
                    if(Integer.parseInt(temobj.getPartsNo())<0)
                    {

                    //delete all record;
                        if(!biblist.isEmpty()){
                    System.out.println("In Part No");
                    bibdao.deleteBib(biblist,temobj.getLibraryId(),temobj.getSublibraryId());
                    boolean result= DAO.TruncateTempTable(temobj.getLibraryId(),temobj.getSublibraryId());


                    request.setAttribute("msg1", "Data Type Mismatch in Parts No Field Cannot Import Data Try Again");
                    return mapping.findForward(SUCCESS);
                        }

                    }else{
                    bibobj.setPartsNo(Integer.parseInt(temobj.getPartsNo()));
                    }
                    }
                    bibobj.setSubject(temobj.getSubject());
                    bibobj.setEntryLanguage(temobj.getEntryLanguage());
                   /* if(temobj.getIsbn10()!=null)
                    { BibliographicDetails bibob=bibdao.search1Isbn10(temobj.getIsbn10(),temobj.getLibraryId(),temobj.getSublibraryId());
                      if(bibob!=null)
                      {
                          bibdao.deleteBib(biblist,temobj.getLibraryId(),temobj.getSublibraryId());
                       boolean result=DAO.TruncateTempTable(temobj.getLibraryId().trim(),temobj.getSublibraryId().trim());
                       System.out.println(result);

                       request.setAttribute("msg1", "ISBN10 already exist cannot import"+temobj.getIsbn10());
                        return mapping.findForward(SUCCESS);
                        //break;

                      }


                    }*/

                    bibobj.setIsbn10(temobj.getIsbn10());
                    bibobj.setIsbn13(temobj.getIsbn13());
                    bibobj.setLccNo(temobj.getLccNo());
                    bibobj.setEdition(temobj.getEdition());
                    if(temobj.getNoOfCopies()!=null)
                    {
                        String t=temobj.getNoOfCopies();
                        boolean type=Datatype(t);
                        System.out.println(t+"........"+type);
                        if(type==false)
                        throw new MyExcep("No of Copies");
                    if(Integer.parseInt(temobj.getNoOfCopies())<0)
                    {

                    //delete all record;
                if(!biblist.isEmpty()){
                         bibdao.deleteBib(biblist,temobj.getLibraryId(),temobj.getSublibraryId());
                      boolean result=DAO.TruncateTempTable(temobj.getLibraryId().trim(),temobj.getSublibraryId().trim());



                        request.setAttribute("msg1", "Data Type Mismatch in No of Copies Field Cannot Import");
                        return mapping.findForward(SUCCESS);
                        }

                    }else{
                    bibobj.setNoOfCopies(Integer.parseInt(temobj.getNoOfCopies()));
                    }
                    }

                    bibobj.setAuthorName(temobj.getAuthorName());
                    bibobj.setGuideName(temobj.getGuideName());
                    bibobj.setUniversityFaculty(temobj.getUniversityFaculty());
                    bibobj.setDegree(temobj.getDegree());
                    bibobj.setSubmittedOn(temobj.getSubmittedOn());
                    bibobj.setAcceptanceYear(temobj.getAcceptanceYear());
                    bibobj.setCollation1(temobj.getCollation1());
                    bibobj.setNotes(temobj.getNotes());
                    bibobj.setAbstract_(temobj.getAbstract_());
                    bibobj.setAddress(temobj.getAddress());
                    bibobj.setState1(temobj.getState1());
                    bibobj.setCountry(temobj.getCountry());
                    bibobj.setEmail(temobj.getEmail());
                    bibobj.setFrmrFrq(temobj.getFrmrFrq());
                    bibobj.setFrqDate(temobj.getFrqDate());
                    bibobj.setIssn(temobj.getIssn());

                    daoobj.insertBibligraphicDetails(bibobj);
                    biblist.add(bib_id);

//}


                }


                // for accession register
                System.out.println("No of Titles..............."+distinctTitleSize+biblist.size());

                for (int j = 0; j < distinctTitleSize; j++) {
 System.out.println(biblist.get(j).toString());
                    BibliographicDetails obj1=daoobj.getTitle(library_id,sublibrary_id,Integer.parseInt(biblist.get(j).toString()));
  System.out.println(":::::::" + obj1.getTitle()+"  "+obj1.getCallNo());
                    List<TempExcellImport> list=daoobj.getTempRecord(library_id,sublibrary_id,obj1.getTitle(),obj1.getCallNo());



                    System.out.println("*****************list size  vvvvvvvvvvvvvvvvvvvv:::::::" + list.size()+ "  "+biblist.get(0)+biblist.get(1)+biblist.get(2));
                    for (int k = 0; k < list.size(); k++) {
                        temobj2 = (TempExcellImport) list.get(k);
                        System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG" + temobj2.getTitle() + "dsffffff" + temobj2.getSno());


                            accessionidobj.setRecordNo(bibdao.returnMaxRecord(temobj2.getLibraryId(), temobj2.getSublibraryId()));
                            accessionidobj.setLibraryId(library_id);
                            accessionidobj.setSublibraryId(sublibrary_id);
                            accessionobj.setId(accessionidobj);

            accessionobj.setBiblioId(obj1.getId().getBiblioId());
                        /*     if(temobj2.getAccessionNo()!=null)
                    { DocumentDetails  bibob=bibdao.searchAccession(temobj2.getLibraryId(),temobj2.getSublibraryId(),temobj2.getAccessionNo());
                      if(bibob!=null)
                      {
                               bibdao.deleteBib(biblist,temobj.getLibraryId(),temobj.getSublibraryId());
                     //   DAO.TruncateTempTable();

                       request.setAttribute("msg1", "Accession No already exist cannot import"+temobj2.getAccessionNo());
                        return mapping.findForward(SUCCESS);


                      }


                        }*/




                            accessionobj.setAccessionNo(temobj2.getAccessionNo());
                            accessionobj.setVolumeNo(temobj2.getVolumeNo());
                            accessionobj.setLocation(temobj2.getLocation());
                            accessionobj.setShelvingLocation(temobj2.getShelvingLocation());
                            accessionobj.setIndexNo(temobj2.getIndexNo());
                            accessionobj.setNoOfPages(temobj2.getNoOfPages());
                            accessionobj.setPhysicalWidth(temobj2.getPhysicalWidth());

                            accessionobj.setBindType(temobj2.getPhysicalForm());
                            accessionobj.setPhysicalDescription(temobj2.getPhysicalDescription());
                            accessionobj.setPhysicalForm(temobj2.getPhysicalForm());
                            accessionobj.setColour(temobj2.getColour());

                            System.out.println("*******12*****************"+accessionidobj.getRecordNo()+temobj2.getAccessionNo());

                            daoobj.insertAccessionRegister(accessionobj);


                            documentIdobj.setDocumentId(bibdao.returnMaxDocumentId(temobj2.getLibraryId(), temobj2.getSublibraryId()));
                            documentIdobj.setLibraryId(temobj2.getLibraryId());
                            documentIdobj.setSublibraryId(temobj2.getSublibraryId());

                            documentobj.setId(documentIdobj);
                            documentobj.setStatus(temobj2.getStatus());

                            documentobj.setBiblioId(obj1.getId().getBiblioId());
                            documentobj.setDocumentType(temobj2.getDocumentType());
                            documentobj.setBookType(temobj2.getBookType());
                            documentobj.setAccessionType(temobj2.getAccessionType());
                            documentobj.setDateAcquired(temobj2.getDateAcquired());
                            documentobj.setTitle(temobj2.getTitle());
                            documentobj.setSubtitle(temobj2.getSubtitle());
                            documentobj.setAltTitle(temobj2.getAltTitle());
                            documentobj.setStatementResponsibility(temobj2.getStatementResponsibility());
                            documentobj.setMainEntry(temobj2.getMainEntry());
                            documentobj.setAddedEntry1(temobj2.getAddedEntry1());
                            documentobj.setAddedEntry2(temobj2.getAddedEntry2());
                            documentobj.setAddedEntry3(temobj2.getAddedEntry3());
                            documentobj.setPublisherName(temobj2.getPublisherName());
                            documentobj.setPublicationPlace(temobj2.getPublicationPlace());
                            documentobj.setPublishingYear(temobj2.getPublishingYear());
                        if(temobj2.getPartsNo()!=null)
                        {
                       String t=temobj2.getPartsNo();
                        boolean type=Datatype(t);
                        if(type==false)
                        throw new MyExcep("Parts No");

                        if(Integer.parseInt(temobj2.getPartsNo())<0)
                            {

                    //delete all record;
      bibdao.deleteBib(biblist,temobj.getLibraryId(),temobj.getSublibraryId());
                      boolean result= DAO.TruncateTempTable(temobj.getLibraryId(),temobj.getSublibraryId());




                        request.setAttribute("msg1", "Data Type Mismatch in PartNo Field Cannot Import");
                        return mapping.findForward(SUCCESS);

                    }else{
                      documentobj.setPartsNo(Integer.parseInt(temobj2.getPartsNo()));
                    }

}
                            documentobj.setSubject(temobj2.getSubject());
                            documentobj.setEntryLanguage(temobj2.getEntryLanguage());
                            documentobj.setIsbn10(temobj2.getIsbn10());
                            documentobj.setIsbn13(temobj2.getIsbn13());
                            documentobj.setLccNo(temobj2.getLccNo());
                            documentobj.setEdition(temobj2.getEdition());

if(temobj2.getNoOfCopies()!=null){
     String t=temobj2.getNoOfCopies();
                        boolean type=Datatype(t);
                        if(type==false)
                        throw new MyExcep("No of Copies in Document Details");
                           if(Integer.parseInt(temobj2.getNoOfCopies())<0)
                    {

                    //delete all record;

      bibdao.deleteBib(biblist,temobj.getLibraryId(),temobj.getSublibraryId());
                  boolean result= DAO.TruncateTempTable(temobj.getLibraryId(),temobj.getSublibraryId());



                        request.setAttribute("msg1", "Data Type Mismatch in No of Copies  Field Cannot Import");
                        return mapping.findForward(SUCCESS);

                    }else{
                     documentobj.setNoOfCopies(Integer.parseInt(temobj2.getNoOfCopies()));
                    }

}


                            documentobj.setAuthorName(temobj2.getAuthorName());
                            documentobj.setGuideName(temobj2.getGuideName());
                            documentobj.setUniversityFaculty(temobj2.getUniversityFaculty());
                            documentobj.setDegree(temobj2.getDegree());
                            documentobj.setSubmittedOn(temobj2.getSubmittedOn());
                            documentobj.setAcceptanceYear(temobj2.getAcceptanceYear());
                            documentobj.setCollation1(temobj2.getCollation1());
                            documentobj.setNotes(temobj2.getNotes());
                            documentobj.setAbstract_(temobj2.getAbstract_());
                            documentobj.setAddress(temobj2.getAddress());
                            documentobj.setState1(temobj2.getState1());
                            documentobj.setCountry(temobj2.getCountry());
                            documentobj.setEmail(temobj2.getEmail());
                            documentobj.setFrmrFrq(temobj2.getFrmrFrq());
                            documentobj.setFrqDate(temobj2.getFrmrFrq());
                            documentobj.setIssn(temobj2.getIssn());
                            documentobj.setVolumeLocation(temobj2.getVolumeLocation());
if(temobj2.getProductionYear()!=null){
     String t=temobj2.getProductionYear();
                        boolean type=Datatype(t);
                        if(type==false)
                        throw new MyExcep("Productuion Year");


                           if(Integer.parseInt(temobj2.getProductionYear())<0)
                    {

                    //delete all record;

      bibdao.deleteBib(biblist,temobj.getLibraryId(),temobj.getSublibraryId());
                      boolean result= DAO.TruncateTempTable(temobj.getLibraryId(),temobj.getSublibraryId());



                        request.setAttribute("msg1", "Data Type Mismatch in Pushing Year  Field Cannot Import");
                        return mapping.findForward(SUCCESS);

                    }else{
                     documentobj.setProductionYear(Integer.parseInt(temobj2.getProductionYear()));
                    }
}
                            documentobj.setSource1(temobj2.getSource1());
                            documentobj.setDuration(temobj2.getDuration());
                            documentobj.setSeries(temobj2.getDuration());
                            documentobj.setPhysicalForm(temobj2.getPhysicalForm());
                            documentobj.setColour(temobj2.getColour());
                            documentobj.setTypeOfDisc(temobj2.getTypeOfDisc());
                            documentobj.setAccessionNo(temobj2.getAccessionNo());

                       if(temobj2.getRecordNo()!=null){

                          String t=temobj2.getRecordNo();
                        boolean type=Datatype(t);
                        if(type==false)
                        throw new MyExcep("Record No");

                               if(Integer.parseInt(temobj2.getRecordNo())<0)
                    {

                    //delete all record;
      bibdao.deleteBib(biblist,temobj.getLibraryId(),temobj.getSublibraryId());
                     boolean result= DAO.TruncateTempTable(temobj.getLibraryId(),temobj.getSublibraryId());




                        request.setAttribute("msg1", "Data Type Mismatch in Record No  Field Cannot Import");
                        return mapping.findForward(SUCCESS);

                    }else{
                     documentobj.setRecordNo(Integer.parseInt(temobj2.getRecordNo()));
                    }
                       }
                            documentobj.setCallNo(temobj2.getCallNo());
                            documentobj.setVolumeNo(temobj2.getVolumeNo());
                            documentobj.setLocation(temobj2.getLocation());
                            documentobj.setShelvingLocation(temobj2.getShelvingLocation());
                            documentobj.setIndexNo(temobj2.getIndexNo());
                            documentobj.setNoOfPages(temobj2.getNoOfPages());
                            documentobj.setPhysicalWidth(temobj2.getPhysicalWidth());
                            documentobj.setBindType(temobj2.getBindType());





                        daoobj.insertDocumentsDetails(documentobj);

                    }

                }
                request.setAttribute("msg2", "data has been successfully added");
                return mapping.findForward(SUCCESS);

        } catch (MyExcep e) {
            request.setAttribute("msg1", "Data Type Mismatch cannot import"+e.Mess);
            mapping.findForward("failure");
        }







              }//first while

            if (uploadForm.getButton().equals("Back"))
            {
                return mapping.findForward("Back");

            }


        request.setAttribute("msg2", "data has been successfully added from  file <br> " + row_no + "    : records successfully added in data base");
         return mapping.findForward(SUCCESS);
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
            request.setAttribute("msg1", "Error In Importing due to "+e.getMessage());
           request.setAttribute("error", e);
            return mapping.findForward("Back");
        }








    }
       public boolean Datatype(String t){
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