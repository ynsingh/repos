/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import org.apache.struts.upload.FormFile;
/**
 *
 * @author faraz
 */
public class ImageUploadActionForm extends org.apache.struts.action.ActionForm {
    
    private byte[] uploadedFile;

    public byte[] getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(byte[] uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    private FormFile img;
    private String filename;
    /**
     *
     */
    public ImageUploadActionForm() {
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

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    
}
