/*
 * @(#) AddWaterMark.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproducuce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
package in.ac.dei.edrp.cms.common.utility;

import java.io.File;
import java.io.FileOutputStream;

import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

/**
 * Class to add Water Mark on Files
 * @author Dheeraj Singh
 * @date Sep 14 2012
 * @version 1.0
 */
public class AddWaterMark{
	
	/*
	 * Method to add Water Mark on PDF Files
	 **/
	public static void addWaterMark(String filePath,String folderPath,String imgPath){
		try{
			PdfReader pdfReader = new PdfReader(filePath);
			int number_of_pages = pdfReader.getNumberOfPages();
			PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(folderPath+"Watermark.pdf"));
			int ii = 0;
			Image waterMark = Image.getInstance(imgPath);
			PdfContentByte addMark;
			while (ii < number_of_pages) {
				  ii++;
				  float x = pdfReader.getPageSizeWithRotation(ii).getWidth() - waterMark.getWidth();
				  float y = pdfReader.getPageSizeWithRotation(ii).getHeight() - waterMark.getHeight();
				  waterMark.setAbsolutePosition(x/2, y/2);
				  addMark = pdfStamper.getUnderContent(ii);
				  addMark.addImage(waterMark);
	        }
			pdfStamper.close();
			
			File fileDel = new File(filePath);
			fileDel.delete();
			
			File fileRenTo = new File(filePath);
			
			File fileRen = new File(folderPath+"Watermark.pdf");
			fileRen.renameTo(fileRenTo);
		}catch(Exception ex){
			System.out.println("Exception During Adding Water Mark "+ex);
		}
	}
}
