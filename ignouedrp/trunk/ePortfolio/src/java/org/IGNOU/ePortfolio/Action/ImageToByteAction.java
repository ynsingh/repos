/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.IGNOU.ePortfolio.Action;

import com.opensymphony.xwork2.ActionSupport;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import static org.IGNOU.ePortfolio.Action.ReadPropertiesFile.*;

/**
 *
 * @author Amit
 */
public class ImageToByteAction extends ActionSupport implements ServletRequestAware{

        byte[] imageInByte = null;
	String userId;
        private String Filepath=ReadPropertyFile("Filepath");
 
	private HttpServletRequest servletRequest;
 
	public String getUserId() {
		return userId;
	}
 
	public void setUserId(String userId) {
		this.userId = userId;
	}
 
	public  ImageToByteAction() {
		System.out.println("ImageAction");
	}
 
    @Override
	public String execute() {
                
		return SUCCESS;
	}
 
	public byte[] getCustomImageInBytes() {
         	BufferedImage originalImage;
		try {
			originalImage = ImageIO.read(getImageFile(this.userId));
			// convert BufferedImage to byte array
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "png", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		return imageInByte;
	}
 
	private File getImageFile(String userId) {
		File file = new File(Filepath + "/"+userId+"/", userId.substring(0, 4)+".png");
                if(file.exists()){
                    return file;
                }
                else{
                   file=new File(Filepath + "/" + "user.png");
                return file;
                }
	}
 
	public String getCustomContentType() {
		return "image/png";
	}
 
	public String getCustomContentDisposition() {
		return ""+userId.substring(0, 4)+".png";
	}
 
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.servletRequest = request;
 
	}

    /**
     * @return the Filepath
     */
    public String getFilepath() {
        return Filepath;
    }

    /**
     * @param Filepath the Filepath to set
     */
    public void setFilepath(String Filepath) {
        this.Filepath = Filepath;
    }
    
}
