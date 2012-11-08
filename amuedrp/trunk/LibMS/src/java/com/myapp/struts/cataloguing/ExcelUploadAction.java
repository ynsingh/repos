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
import org.apache.struts.upload.FormFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/**
 *
 * @author EdRP-04
 */
public class ExcelUploadAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
	/*	try {
			StrutsUploadForm uploadForm = (StrutsUploadForm)form;;
	        FormFile myFile = uploadForm.getExcelFile();

	        System.out.println(myFile.getContentType());

	        Workbook inputWorkbook = Workbook.getWorkbook(myFile.getInputStream());

	        Sheet sheet = inputWorkbook.getSheet(0);

	        int numberOfRows = sheet.getRows();
	        int numberOfColumns = sheet.getColumns();

	        for (int row = 0; row < numberOfRows; row ++ ) {
	        	for (int column = 0; column < numberOfColumns; column ++ ) {
	        			Cell cell = sheet.getCell(column,row);
	        			
                                        System.out.print(cell.getContents() + "|");
	        	}
	        	System.out.println("");
	        }

	        LabelCell labelCell = sheet.findLabelCell("Age");
	        int ageColumnNumber = labelCell.getColumn();
	        double ageSum = 0;
	        for (int row = 1; row < numberOfRows; row ++ ) {
	        	Cell cell = sheet.getCell(ageColumnNumber,row);
	        	if (CellType.NUMBER.equals(cell.getType())){
	        		ageSum = ageSum + Integer.parseInt(cell.getContents());
	        	}
	        }

	        double averageAge =  ageSum / (numberOfRows - 1);
	        System.out.println("Sum Age : " + ageSum);
	        System.out.println("Average Age : " + averageAge);


	        WritableWorkbook writableWorkbook = Workbook.createWorkbook(response.getOutputStream());
			WritableSheet writableSheet = writableWorkbook.createSheet("Demo", 0);
			writableSheet.addCell(new Label(0, 0, "Hello World"));

	        jxl.write.Formula formula = new jxl.write.Formula(labelCell.getColumn(), sheet.getRows(), "AVERAGE(B2:B10)");
	        writableSheet.addCell(formula);

	        request.setAttribute("workbook", writableWorkbook);


		} catch (IOException e) {
			System.out.println(e.getMessage());
			return mapping.findForward("error");
		} catch (JXLException e) {
			System.out.println(e.getMessage());
			return mapping.findForward("error");
		}*/
HSSFWorkbook workBook=new HSSFWorkbook();
HSSFSheet sheet=workBook.createSheet();
HSSFRow headerRow=sheet.createRow((short)0);
HSSFCell headerCell=headerRow.createCell(0);
headerCell.setCellValue("Bowling Score");

        try {
			StrutsUploadForm uploadForm = (StrutsUploadForm)form;;
                        FormFile myFile = uploadForm.getExcelFile();
                        String v="God";
                        //FileInputStream fileInputStream = new FileInputStream("poi-test.xls");
                        System.out.println("****************** myfile ::::"+myFile.getInputStream());
			HSSFWorkbook workbook = new HSSFWorkbook(myFile.getInputStream());
                        HSSFSheet worksheet1 = workbook.createSheet("test2.xls");
                        HSSFRow row2=worksheet1.createRow(1);
                        HSSFCell cell1=row2.createCell(1);
                        //System.out.println("Workkkkkkkkkkkkk"+worksheet1.getFirstRowNum());
                       //String cellvallue=cell1.setCellValue(true);
                        cell1.setCellValue(v);
			 System.out.println("Workkkkkkkkkkkkk"+cell1.getStringCellValue());
                         workbook.getSheet("test2.xsl");
                         uploadForm.setExcelFile(myFile);
                         

                        HSSFSheet worksheet = workbook.getSheet("test.xls");
			HSSFRow row1 = worksheet.getRow(0);
			HSSFCell cellA1 = row1.getCell(0);
			String a1Val = cellA1.getStringCellValue();
			HSSFCell cellB1 = row1.getCell( 1);
			String b1Val = cellB1.getStringCellValue();
			HSSFCell cellC1 = row1.getCell(2);
			boolean c1Val = cellC1.getBooleanCellValue();
			HSSFCell cellD1 = row1.getCell(3);
			Date d1Val = cellD1.getDateCellValue();

			System.out.println("A1: " + a1Val);
			System.out.println("B1: " + b1Val);
			System.out.println("C1: " + c1Val);
			System.out.println("D1: " + d1Val);
                        return mapping.findForward("success");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	

		return mapping.findForward(SUCCESS);

	}

}
