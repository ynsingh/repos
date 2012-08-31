/*
 * @(#) DocumentUpload.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
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

package in.ac.dei.edrp.client.Shared;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Label;
/**
 * 
 * @version 1.0 8 MAY 2012
 * @author UPASANA KULSHRESTHA
 */

public class DocumentUpload extends FlexTable {
    private FileUpload fileUpload = new FileUpload();
    private FormPanel form = new FormPanel();
    private String docId;
    private String docLabel;

    public DocumentUpload() {
    }

    public DocumentUpload(String label, String width) {
        form.add(fileUpload);
        docLabel=label;
        Label lbl = new Label(label);
        lbl.setWidth(width);
        this.setWidget(0, 0, lbl);
        this.setWidget(0, 1, form);
    }

    public void uploadFile(String folderName) throws Exception {
        form.setAction(GWT.getModuleBaseURL() + "upload?folderName=" +
            folderName + "&fileName=" + docId);
        form.setEncoding(FormPanel.ENCODING_MULTIPART);
        form.setMethod(FormPanel.METHOD_POST);
        fileUpload.setName(docId);
        form.submit();
    }

    public boolean hasFile() {
        return (fileUpload.getFilename() != "");
    }

    public boolean hasDocFile() {
        return (fileUpload.getFilename().toLowerCase().endsWith(".doc"));
    }
    
    public boolean hasDocxFile() {
        return (fileUpload.getFilename().toLowerCase().endsWith(".docx"));
    }

    public boolean hasPdfFile() {
        return (fileUpload.getFilename().toLowerCase().endsWith(".pdf"));
    }

    public boolean hasJpgFile() {
        return (fileUpload.getFilename().toLowerCase().endsWith(".jpg"));
    }

    public String getFileName() {
        return fileUpload.getFilename();
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocId() {
        return docId;
    }

    public String getDocLabel() {
        return docLabel;
    }
}
