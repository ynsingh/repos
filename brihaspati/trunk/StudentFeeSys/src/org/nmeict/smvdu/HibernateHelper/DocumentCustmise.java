package org.nmeict.smvdu.HibernateHelper;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
@ManagedBean(name="dc")
@RequestScoped
public class DocumentCustmise {

	
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {  
	    Document pdf = (Document) document;  
	    pdf.open();  
	    pdf.setPageSize(PageSize.A4);  
	} 
}
