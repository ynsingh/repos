
package com.myapp.struts.cataloguing;
import org.apache.struts.upload.FormFile;
public class catImageUploadActionForm extends org.apache.struts.action.ActionForm {
    
    private FormFile img;
    private String filename;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    private String comment;
    public catImageUploadActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public FormFile getImg() {
        return img;
    }

    public void setImg(FormFile img) {
        this.img = img;
        System.out.println("ImageUploadActionForm:"+img.getFileSize());
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    
}
