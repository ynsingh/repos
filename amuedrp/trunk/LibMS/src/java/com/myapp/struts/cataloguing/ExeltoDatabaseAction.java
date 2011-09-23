/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.cataloguing;


import com.myapp.struts.cataloguingDAO.DAO;
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
}
