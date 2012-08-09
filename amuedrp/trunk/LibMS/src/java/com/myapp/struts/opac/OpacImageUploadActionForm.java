/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import org.apache.struts.upload.FormFile;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author edrp01
 */
public class OpacImageUploadActionForm extends org.apache.struts.action.ActionForm {
    
   private FormFile img;
    private String filename;

    public OpacImageUploadActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the img
     */
    public FormFile getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(FormFile img) {
        this.img = img;
        System.out.println("ImageUploadActionForm:"+img.getFileSize());
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    

}
