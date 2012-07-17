package in.ac.dei.edrp.cms.common.utility;

/*
 * @(#) PdfRevenueReportView.java
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

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


public class PdfRevenueReportView extends AbstractPdfView {
    @SuppressWarnings("unchecked")
    protected void buildPdfDocument(Map model, Document document,
        PdfWriter writer, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
    	String filePath="";
    	File file=null;
        List<List<String>> sheetData = (List<List<String>>) model.get("revenueData");
        String sep = System.getProperty("file.separator");
        float gridcol[]=new float[sheetData.get(1).size()];
        
        gridcol[0]=2;
        gridcol[1]=5;
        gridcol[2]=12;
        int jj=3;
        
        for(int dd=0;dd<sheetData.get(1).size()-5;dd++){
        	
        	gridcol[jj++]=2;
        }
        
        gridcol[jj++]=3;
        gridcol[jj++]=3;
        
        PdfPTable table = new PdfPTable(gridcol);
        table.setWidthPercentage(100f);
        Font f = new Font(10);
        System.out.println("inside pdf generation "+sheetData.size());
        for (int i = 1; i < sheetData.size(); i++) {
            for (int j = 0; j < sheetData.get(i).size(); j++) {
            	 System.out.println("inside pdf generationsssssssssssss "+sheetData.get(i).size());
            	 System.out.println("data is  "+sheetData.get(i).get(j) +" : "+f);
            	PdfPCell cell = new PdfPCell(new Phrase(sheetData.get(i).get(j), f));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }
        }
        
        if(request.getAttribute("path").equals("NEW")){
            filePath = getServletContext().getRealPath("/")+"AwardSheet";
            file = new File(filePath);
            file.mkdir();

    		filePath = filePath+sep+request.getParameter("sessionStart")+"-"+request.getParameter("sessionEnd"); // Session
    		file = new File(filePath);
    		file.mkdir();
    		
    		filePath = filePath+sep+sheetData.get(0).get(0); // Entity Name
    		file = new File(filePath);
    		file.mkdir();
    		
    		filePath = filePath+sep+request.getParameter("semesterStartDate")+"-"+request.getParameter("semesterEndDate");
    		file = new File(filePath);
    		file.mkdir();
    		
    		writer = PdfWriter.getInstance(document, new FileOutputStream(filePath+sep+
    				sheetData.get(0).get(1)+"_"+sheetData.get(0).get(2)+"_"+sheetData.get(0).get(3)+"_"+
    				sheetData.get(0).get(4)+"_"+sheetData.get(0).get(5)+" ("+sheetData.get(0).get(7)+")" + ".pdf"));
    		
        }else{
        	filePath = getServletContext().getRealPath("/")+"AwardSheet";
        	file = new File(filePath);
        	file.mkdir();
		
        	HttpSession session=request.getSession(true);
        	filePath = filePath+sep+session.getAttribute("userId"); // employeeID
        	file = new File(filePath);
        	file.mkdir();
		
        	filePath = filePath+sep+sheetData.get(0).get(0); // Entity Name
        	file = new File(filePath);
        	file.mkdir();
		
        	filePath = filePath+sep+sheetData.get(0).get(5); // Course Code
        	file = new File(filePath);
        	file.mkdir();
        
		
        	if(sheetData.get(0).get(7).equalsIgnoreCase("I")){    // Internal, External, Remedial
        		filePath = filePath+sep+"Internal";
        	}else if(sheetData.get(0).get(7).equalsIgnoreCase("E")){
        		filePath = filePath+sep+"External";
        	}else if(sheetData.get(0).get(7).equalsIgnoreCase("R")){
        		filePath = filePath+sep+"Remedial";
        	}
		
        	file = new File(filePath);
        	file.mkdir();
        	
    		writer = PdfWriter.getInstance(document, new FileOutputStream(filePath+sep+sheetData.get(0).get(0)+"_"+
    				sheetData.get(0).get(1)+"_"+sheetData.get(0).get(2)+"_"+sheetData.get(0).get(3)+"_"+
    				sheetData.get(0).get(4)+"_"+sheetData.get(0).get(5)+" ("+sheetData.get(0).get(7)+")" + ".pdf"));
        }


        
        document.setPageSize(PageSize.A4.rotate());
        String sesionDate=sheetData.get(0).get(8).substring(0,4).concat("-").concat(sheetData.get(0).get(9).substring(2,4));
        
        Phrase header1= new Phrase(sheetData.get(0).get(11).toUpperCase(),FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD,
				new Color(0, 0, 0)));
//        Phrase header2=new Phrase("\n"+"Session: "+sesionDate+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t                               "+"Course Teacher:\t\t"+sheetData.get(0).get(10)+"\n"+"Class & Branch : " +
//                sheetData.get(0).get(1)+"("+sheetData.get(0).get(2)+")                         \t\t Sem. :"+sheetData.get(0).get(4)+
//                "\t\t     Course No. :"+sheetData.get(0).get(5)+"\t\t     Title :"+sheetData.get(0).get(6));
        Phrase header2;
        if(sheetData.get(0).get(2).equalsIgnoreCase("None")){
        	header2=new Phrase("\n"+"Session: "+sesionDate+"                                                         "+"Course Teacher:   "+sheetData.get(0).get(10)+"\n"+"Class & Branch : " +
                    sheetData.get(0).get(1)+"                                          "           +sheetData.get(0).get(4)+
                    "            Course Code :"+sheetData.get(0).get(5)+"       Title :"+sheetData.get(0).get(6));
        }else{
        	header2=new Phrase("\n"+"Session: "+sesionDate+"                                                         "+"Course Teacher:   "+sheetData.get(0).get(10)+"\n"+"Class & Branch : " +
                sheetData.get(0).get(1)+"("+sheetData.get(0).get(2)+")                               "+sheetData.get(0).get(4)+
                "            Course Code :"+sheetData.get(0).get(5)+"          Title :"+sheetData.get(0).get(6));
        }
        Phrase headers=new Phrase();
        headers.add(header1);
        headers.add(header2);
        HeaderFooter headerFooter = new HeaderFooter(headers,false);
        headerFooter.setBorderWidth(0);
        document.setHeader(headerFooter);
        HeaderFooter footer = new HeaderFooter(new Phrase("Displayed on Notice Board:\n"+
           " From                       To                                Signatures of Course Teacher(s)                Signature of H.O.D.                 Signature of Dean/Principal" ),false); 
        footer.setBorderWidth(0);
        document.setFooter(footer);        
        document.open();

        Paragraph p =new Paragraph();
        document.add(p);
        document.add(table);
        document.close();
    }
}
