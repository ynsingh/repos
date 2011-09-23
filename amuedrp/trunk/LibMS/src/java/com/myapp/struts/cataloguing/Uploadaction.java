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
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
//~--- JDK imports ------------------------------------------------------------

import java.io.FileNotFoundException;
import java.io.InputStream;

import java.util.Iterator;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
/**
 *
 * @author EdRP-04
 */
public class Uploadaction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        StrutsUploadForm uploadForm = (StrutsUploadForm) form;
       
        DAO dataaccess = new DAO();
        HttpSession session = request.getSession();
        String library_id,sublibrary_id;

        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");




            try
            {


                   InputStream inputStream = null;
                    uploadForm.getExcelFile().getInputStream().read();
                    
                    FormFile myFile = uploadForm.getExcelFile();
                      session.setAttribute("myFile", myFile);
                    HSSFWorkbook workbook = new HSSFWorkbook(myFile.getInputStream());
                    inputStream = myFile.getInputStream();
            
                    POIFSFileSystem fileSystem = null;

            
                    fileSystem = new POIFSFileSystem(inputStream);
                    
                    HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
                    HSSFSheet sheet = workBook.getSheetAt(0);
                    Iterator<Row> rows = sheet.rowIterator();
                    Row row;
                   
                    int i = 0;
                    String table_name = uploadForm.getCombo_table_name();
                   
                    List<String> obj=new ArrayList<String>();
                    int l=0;
                    for (int k = 0; k < DAO.columnname(table_name).size(); k++) {
                        if( k==0)
                            continue; 
                         obj.add(DAO.columnname(table_name).get(k).toString());
                    }
                  
                    String map_table[] = new String[obj.size()];
                    for(int j=0;j<obj.size();j++)
                    map_table[j]=obj.get(j);



                    System.out.println("maptable:::::::::::::::::::::::::"+map_table.length);
                    session.setAttribute("table", map_table);
                 
                    //get Data type of fields
                       List<String> obj1=new ArrayList<String>();
                  l=0;
                    for (int k = 0; k < DAO.datatype(table_name).size(); k++) {
                        if(k==51 || k==52|| k==53 || k==0)
                            continue;
                         obj1.add(DAO.datatype(table_name).get(k).toString());
                    }
                    
                    String map_column[] = new String[obj1.size()];
                    for(int j=0;j<obj1.size();j++)
                    map_column[j]=obj1.get(j);



                    System.out.println("mapcolumn:::::::::::::::::::::::::"+map_column.length);
                    session.setAttribute("table_datatype", map_column);
                 

                    
                    request.setAttribute("table_size", obj.size());
                 
                 
                    System.out.println("table size from upload action:::::::::::::::: " +DAO.columnname(table_name).size());
                    session.setAttribute("table_name", table_name);


                //   rows.next(); rows.next();
                        while (rows.hasNext())
                        {

                            row = rows.next();

                        
                            Iterator<Cell> cells = row.cellIterator();

                            


                            while (cells.hasNext())
                            {
                                Cell cell = cells.next();
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                              
                                if ((cell.getRowIndex() == 0))
                                {

                                 
                                        if (i == 0) {
                                            uploadForm.setCell0(cell.getRichStringCellValue().getString());

                                        }
                                        if (i == 1) {
                                            uploadForm.setCell1(cell.getRichStringCellValue().getString());

                                           
                                        }
                                        if (i == 2) {
                                            uploadForm.setCell2(cell.getRichStringCellValue().getString());
                                        }
                                        if (i == 3) {
                                            uploadForm.setCell3(cell.getRichStringCellValue().getString());
                                        }
                                        if (i == 4) {
                                            uploadForm.setCell4(cell.getRichStringCellValue().getString());
                                        }
                                        if (i == 5) {


                                            uploadForm.setCell5(cell.getRichStringCellValue().getString());
                                        }
                                          if (i == 6) {


                                            uploadForm.setCell6(cell.getRichStringCellValue().getString());
                                        }
                                        if (i == 7) {


                                            uploadForm.setCell7(cell.getRichStringCellValue().getString());
                                        }
                                         if (i == 8) {


                                            uploadForm.setCell8(cell.getRichStringCellValue().getString());
                                        }
                                         if (i == 9) {


                                            uploadForm.setCell9(cell.getRichStringCellValue().getString());
                                        }
                                         if (i == 10) {


                                            uploadForm.setCell10(cell.getRichStringCellValue().getString());
                                        }
                                         if (i == 11) {


                                            uploadForm.setCell11(cell.getRichStringCellValue().getString());
                                        }
                                         if (i == 12) {


                                            uploadForm.setCell12(cell.getRichStringCellValue().getString());
                                        }
                                         if (i == 13) {


                                            uploadForm.setCell13(cell.getRichStringCellValue().getString());
                                        }
                                         if (i == 14) {


                                            uploadForm.setCell14(cell.getRichStringCellValue().getString());
                                        }
                                         if (i == 15) {


                                            uploadForm.setCell15(cell.getRichStringCellValue().getString());
                                        }
                                         if (i == 16) {


                                            uploadForm.setCell16(cell.getRichStringCellValue().getString());
                                        }
                                         if (i == 17) {


                                            uploadForm.setCell17(cell.getRichStringCellValue().getString());
                                        }
                                         if (i == 18) {


                                            uploadForm.setCell18(cell.getRichStringCellValue().getString());
                                        }
                                         if (i == 19) {


                                            uploadForm.setCell19(cell.getRichStringCellValue().getString());
                                        }
                                         if (i == 20) {


                                            uploadForm.setCell20(cell.getRichStringCellValue().getString());
                                        }
                                          if (i ==21 ) {


                                            uploadForm.setCell21(cell.getRichStringCellValue().getString());
                                        }
                                        if (i ==22 ) {


                                            uploadForm.setCell22(cell.getRichStringCellValue().getString());
                                        }
                                        if (i ==23 ) {


                                            uploadForm.setCell23(cell.getRichStringCellValue().getString());
                                        }
                                        if (i == 24) {


                                            uploadForm.setCell24(cell.getRichStringCellValue().getString());
                                        }
                                        if (i ==25 ) {


                                            uploadForm.setCell25(cell.getRichStringCellValue().getString());
                                        }
                                        if (i == 26) {


                                            uploadForm.setCell26(cell.getRichStringCellValue().getString());
                                        }
                                        if (i == 27) {


                                            uploadForm.setCell27(cell.getRichStringCellValue().getString());
                                        }
                                        if (i ==28 ) {


                                            uploadForm.setCell28(cell.getRichStringCellValue().getString());
                                        }
                                        if (i ==29 ) {


                                            uploadForm.setCell29(cell.getRichStringCellValue().getString());
                                        }
                                        if (i ==30 ) {


                                            uploadForm.setCell30(cell.getRichStringCellValue().getString());
                                        }
                                        if (i == 31) {


                                            uploadForm.setCell31(cell.getRichStringCellValue().getString());
                                        }
                                        if (i == 32) {


                                            uploadForm.setCell32(cell.getRichStringCellValue().getString());
                                        }
                                        if (i ==33 ) {


                                            uploadForm.setCell33(cell.getRichStringCellValue().getString());
                                        }
                                        if (i ==34 ) {


                                            uploadForm.setCell34(cell.getRichStringCellValue().getString());
                                        }
                                        if (i ==35 ) {


                                            uploadForm.setCell35(cell.getRichStringCellValue().getString());
                                        }
                                        if (i == 36) {


                                            uploadForm.setCell36(cell.getRichStringCellValue().getString());
                                        }
                                        if (i ==37 ) {


                                            uploadForm.setCell37(cell.getRichStringCellValue().getString());
                                        }
                                        if (i ==38 ) {


                                            uploadForm.setCell38(cell.getRichStringCellValue().getString());
                                        }
                                        if (i ==39 ) {


                                            uploadForm.setCell39(cell.getRichStringCellValue().getString());
                                        }
                                        if (i ==40 ) {


                                            uploadForm.setCell40(cell.getRichStringCellValue().getString());
                                        }
                                        if (i == 41) {


                                            uploadForm.setCell41(cell.getRichStringCellValue().getString());
                                        }
                                        if (i == 42) {


                                            uploadForm.setCell42(cell.getRichStringCellValue().getString());
                                        }
                                        if (i ==43 ) {


                                            uploadForm.setCell43(cell.getRichStringCellValue().getString());
                                        }
                                        if (i == 44) {


                                            uploadForm.setCell44(cell.getRichStringCellValue().getString());
                                        }
                                        if (i ==45 ) {


                                            uploadForm.setCell45(cell.getRichStringCellValue().getString());
                                        }
                                        if (i ==46 ) {


                                            uploadForm.setCell46(cell.getRichStringCellValue().getString());
                                        }
                                        if (i == 47) {


                                            uploadForm.setCell47(cell.getRichStringCellValue().getString());
                                        }
                                         if (i == 48) {


                                            uploadForm.setCell48(cell.getRichStringCellValue().getString());
                                        }
                                        if (i ==49 ) {


                                            uploadForm.setCell49(cell.getRichStringCellValue().getString());
                                        }
                                        if (i ==50 ) {


                                            uploadForm.setCell50(cell.getRichStringCellValue().getString());
                                        }
                                        if (i ==51 ) {


                                            uploadForm.setCell51(cell.getRichStringCellValue().getString());
                                        }
                                          if (i ==52 ) {


                                            uploadForm.setCell52(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==53 ) {


                                            uploadForm.setCell53(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==54 ) {


                                            uploadForm.setCell54(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==55 ) {


                                            uploadForm.setCell55(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==56 ) {


                                            uploadForm.setCell56(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==57 ) {


                                            uploadForm.setCell57(cell.getRichStringCellValue().getString());
                                        }
                                            if (i == 58) {


                                            uploadForm.setCell58(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==59 ) {


                                            uploadForm.setCell59(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==60 ) {


                                            uploadForm.setCell60(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==61 ) {


                                            uploadForm.setCell61(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==62 ) {


                                            uploadForm.setCell62(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==63 ) {


                                            uploadForm.setCell63(cell.getRichStringCellValue().getString());
                                        }
                                            if (i == 64) {


                                            uploadForm.setCell64(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==65 ) {


                                            uploadForm.setCell65(cell.getRichStringCellValue().getString());
                                        }
                                            if (i == 66) {


                                            uploadForm.setCell66(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==67 ) {


                                            uploadForm.setCell67(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==68 ) {


                                            uploadForm.setCell68(cell.getRichStringCellValue().getString());
                                        }
                                            if (i == 69) {


                                            uploadForm.setCell69(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==70 ) {


                                            uploadForm.setCell70(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==71 ) {


                                            uploadForm.setCell71(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==72 ) {


                                            uploadForm.setCell72(cell.getRichStringCellValue().getString());
                                        }
                                            if (i == 73) {


                                            uploadForm.setCell73(cell.getRichStringCellValue().getString());
                                        }
                                            if (i == 74) {


                                            uploadForm.setCell74(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==75 ) {


                                            uploadForm.setCell75(cell.getRichStringCellValue().getString());
                                        }
                                            if (i == 76) {


                                            uploadForm.setCell76(cell.getRichStringCellValue().getString());
                                        }
                                            if (i == 77) {


                                            uploadForm.setCell77(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==78 ) {


                                            uploadForm.setCell78(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==79 ) {


                                            uploadForm.setCell79(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==80 ) {


                                            uploadForm.setCell80(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==81 ) {


                                            uploadForm.setCell81(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==82 ) {


                                            uploadForm.setCell82(cell.getRichStringCellValue().getString());
                                        }
                                            if (i == 83) {


                                            uploadForm.setCell83(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==84 ) {


                                            uploadForm.setCell84(cell.getRichStringCellValue().getString());
                                        }
                                            if (i == 85) {


                                            uploadForm.setCell85(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==86 ) {


                                            uploadForm.setCell86(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==87 ) {


                                            uploadForm.setCell87(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==88 ) {


                                            uploadForm.setCell88(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==89 ) {


                                            uploadForm.setCell89(cell.getRichStringCellValue().getString());
                                        }
                                            if (i == 90) {


                                            uploadForm.setCell90(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==91 ) {


                                            uploadForm.setCell91(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==92 ) {


                                            uploadForm.setCell92(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==93 ) {


                                            uploadForm.setCell93(cell.getRichStringCellValue().getString());
                                        }
                                            if (i == 94) {


                                            uploadForm.setCell94(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==95 ) {


                                            uploadForm.setCell95(cell.getRichStringCellValue().getString());
                                        }
                                            if (i == 96) {


                                            uploadForm.setCell96(cell.getRichStringCellValue().getString());
                                        }
                                            if (i == 97) {


                                            uploadForm.setCell97(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==98 ) {


                                            uploadForm.setCell98(cell.getRichStringCellValue().getString());
                                        }
                                            if (i == 99) {


                                            uploadForm.setCell99(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==100 ) {


                                            uploadForm.setCell100(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==101 ) {


                                            uploadForm.setCell101(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==102 ) {


                                            uploadForm.setCell102(cell.getRichStringCellValue().getString());
                                        }
                                            if (i ==103 ) {


                                            uploadForm.setCell103(cell.getRichStringCellValue().getString());
                                        }
                                            if (i == 104) {


                                            uploadForm.setCell104(cell.getRichStringCellValue().getString());
                                        }
                                            if (i == 105) {


                                            uploadForm.setCell105(cell.getRichStringCellValue().getString());
                                        }

                                   
                                    i++;
                                }
                   
                        }

                    }

               
                        session.setAttribute("no_columns", i);
                        request.setAttribute("msg", "data has been successfully read from file");
                        return mapping.findForward(SUCCESS);
            }
catch (org.apache.poi.OldFileFormatException e)
{
                    request.setAttribute("error", "Please input only xcell file supported with upto windoz xp");
                    return mapping.findForward("Back");
                }
        catch (java.lang.IllegalArgumentException e) {
                    request.setAttribute("error", "Please input only xcell file supported with upto windoz xp");
                    return mapping.findForward("Back");
                }
        catch (FileNotFoundException e) {
                    System.out.println("File not found in the specified path.");
                    // e.printStackTrace();
                    request.setAttribute("error", e);
                    return mapping.findForward("Back");
                }





    catch (Exception e)
    {
                  request.setAttribute("error", "Either file is not Selected or Corrupted");
                    return mapping.findForward("Back");
            }





    }
}
