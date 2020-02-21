package org.iitk.brihaspati.modules.actions;

/*
 * @(#) Upload Ans Action.java	
 *
 *  Copyright (c) 2005-2006, 2008-2010, 2010-2011,2012-2013, 2020 ETRG,IIT Kanpur. 
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or 
 *  without modification, are permitted provided that the following 
 *  conditions are met:
 * 
 *  Redistributions of source code must retain the above copyright  
 *  notice, this  list of conditions and the following disclaimer.
 * 
 *  Redistribution in binary form must reproducuce the above copyright 
 *  notice, this list of conditions and the following disclaimer in 
 *  the documentation and/or other materials provided with the 
 *  distribution.
 * 
 * 
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
//JDK
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.apache.velocity.context.Context;

import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;

//import org.iitk.brihaspati.modules.utils.MailNotificationThread;

/**
 * @author <a href="mailto:nksinghiitk@yahoo.co.in">Nagendra Kumar Singh</a> 
 */
public class UploadAnsAction extends SecureAction_Instructor
{
	/**
	 * This method performs the action for registering the student in the course
	 * @param data RunData
	 * @param context Context
	 * @return nothing
	 * @see UserManagement from Utils
	 */
	private Log log = LogFactory.getLog(this.getClass());
	private String LangFile=null;
	private String msg1=null;
	private String[] msg;
	/**
     	* Size of the buffer to read/write data
     	*/
	private static final int BUFFER_SIZE = 4096;

        public void doUploadAnsCopy(RunData data, Context context)
        {
		User user=data.getUser();
                LangFile=(String)user.getTemp("LangFile");
		String dir=(String)user.getTemp("course_id");
                ParameterParser pp=data.getParameters();
		FileItem file = pp.getFileItem("file1");
		ErrorDumpUtil.ErrorLog(" The error is "+file.toString());
                String fileName=file.getName();
		String uploadRealPath=TurbineServlet.getRealPath("/Courses/"+dir);
		if(fileName.endsWith("zip"))
                {
			try{
				File filePath=new File(uploadRealPath+"/AnsCopy/");
				filePath.mkdirs();
				File filePath1=new File(filePath+"/"+fileName);
        	                file.write(filePath1);
				unzip(filePath1.toString(),filePath.toString());
	                        msg1=MultilingualUtil.ConvertedString("c_msg5",LangFile);
                                data.setMessage(msg1);
			}
			catch(Exception ex){data.setMessage("The Error in Uploading!! "+ex);}
		} 
                else
                {
                	msg1=MultilingualUtil.ConvertedString("upload_msg3",LangFile);
                        data.setMessage(msg1);
                        setTemplate(data,"call,Answerbook,UploadAnsBook.vm");
		}


	}

	/**
     * Extracts a zip file specified by the zipFilePath to a directory specified by
     * destDirectory (will be created if does not exists)
     * @param zipFilePath
     * @param destDirectory
     * @throws IOException
     */
    public void unzip(String zipFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        // iterates over entries in the zip file
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // if the entry is a file, extracts it
                extractFile(zipIn, filePath);
            } else {
                // if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }
    /**
     * Extracts a zip entry (file entry)
     * @param zipIn
     * @param filePath
     * @throws IOException
     */
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

	public void doPerform(RunData data,Context context) throws Exception{
		String action=data.getParameters().getString("actionName","");
		context.put("action",action);
		User user=data.getUser();
                LangFile=(String)user.getTemp("LangFile");  
                if(action.equals("eventSubmit_doUpload"))
                        doUploadAnsCopy(data,context);
		else
			data.setMessage(MultilingualUtil.ConvertedString("usr_prof2",LangFile));
	}
}

