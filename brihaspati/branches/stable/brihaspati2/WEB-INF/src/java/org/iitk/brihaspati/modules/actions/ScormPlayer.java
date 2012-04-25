package org.iitk.brihaspati.modules.actions;

/*
 * @(#) ScormPlayer.java
 *
 *  Copyright (c) 2009 ETRG,IIT Kanpur.
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
 */

import java.io.File;
import java.util.Vector;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.io.FileNotFoundException;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.turbine.services.servlet.TurbineServlet;

import org.iitk.brihaspati.modules.utils.XmlWriter;
import org.iitk.brihaspati.modules.utils.FileEntry;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.utils.SystemIndependentUtil;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlWriter;

/**This class contain the code Create, Delete, Update
* @author: <a href="mailto:seema_020504@yahoo.com">seema pal</a>
* @author: <a href="mailto:kshuklak@rediffmail.com">kishore kumar shukla</a>
*/


public class ScormPlayer extends SecureAction {
		String LangFile ="";

	public void doUpload(RunData data, Context context){ 
               try{
			ParameterParser pp=data.getParameters();
			LangFile=data.getUser().getTemp("LangFile").toString();
        	        User user = data.getUser();
                	String course_id=(String)user.getTemp("course_id");
		        context.put("courseid",course_id);
                	String coursesRealPath=TurbineServlet.getRealPath("/Courses");
                	String Scormpackage="";
			FileItem file34=pp.getFileItem("file");
         		FileItem file = pp.getFileItem("file");
                	String fileName=file.getName();
			File fileDir=new File(TurbineServlet.getRealPath("/Courses")+"/"+course_id+"/Scormpackage/");
		       	if(fileName.endsWith(".zip"))
		  	{
				File f=new File(TurbineServlet.getRealPath("/Courses")+"/"+course_id+"/TempArea12/");
                		if(!f.exists())
                			f.mkdirs();
				File scormDir1=new File(TurbineServlet.getRealPath("/Courses")+"/"+course_id+"/TempArea12/"+fileName);
				file.write(scormDir1);
				boolean scorm=isValidManifest(scormDir1);
                                if(scorm){
					File scormDir=new File(TurbineServlet.getRealPath("/Courses")+"/"+course_id+"/Scormpackage/");
					if(!scormDir.exists())
			               	scormDir.mkdirs();
					File scormDir2=new File(TurbineServlet.getRealPath("/Courses")+"/"+course_id+"/Scormpackage/"+fileName);
					try{
						/**
				                *Author move File from one Directory to another
                        			*/
                        			String read=data.getServletContext().getRealPath("/Courses")+"/"+course_id+"/TempArea12/"+fileName;
						f=null;
                        			f=new File(read);
        					String writefile=data.getServletContext().getRealPath("/Courses")+"/"+course_id+"/Scormpackage/"+fileName;
						File fo=new File(writefile);
                        			/**
                        			* Here we  move  file in another directory
                        			*/
                        			f.renameTo(fo);
						//this code for unzip the file//
						boolean found =false;
						File file1 = new File(fileName);
                        			int index = file1.getName().lastIndexOf('.');
                        			if (index>0&& index <= file1.getName().length() - 2 )
                        			{
                                			String topic=file1.getName().substring(0, index);
                					if(fileDir.exists())
							{
                						String str[]=fileDir.list();
                						for(int i=0;i<str.length;i++)
                						{
									String filename2=str[i];
									if(filename2.equals(topic))
									{
										found=true;
										//data.setMessage("Package is already exists !!");
										data.setMessage(MultilingualUtil.ConvertedString("brih_package",LangFile)+" "+MultilingualUtil.ConvertedString("brih_is",LangFile)+" "+MultilingualUtil.ConvertedString("Wikiaction6",LangFile));
									}
								}
							}
							if(found==false)
							{
								File f1=new File(TurbineServlet.getRealPath("/Courses")+"/"+course_id+"/Scormpackage"+"/"+topic);
                        					if(!f1.exists())
                        					f1.mkdirs();
								String Path=(TurbineServlet.getRealPath("/Courses")+"/"+course_id+"/Scormpackage"+"/"+fileName);
								File zipSourceFile=new File(Path);

								String destination=(TurbineServlet.getRealPath("/Courses")+"/"+course_id+"/Scormpackage"+"/"+topic);
								File destinationFile=new File(destination);
								try{
									unpackZip(zipSourceFile,destinationFile);
								}catch(Exception e){data.setMessage("unzip problem");}
								zipSourceFile.delete();
								String xmlfile="/PackageList.xml";
								String type="",PDate="";
								String filepath=coursesRealPath+"/"+course_id+"/"+"/Scormpackage";
								uploadxml(filepath,xmlfile,topic,type,PDate,data);
								//data.setMessage("successfull Uploaded !!");
								data.setMessage(MultilingualUtil.ConvertedString("brih_package",LangFile)+" "+MultilingualUtil.ConvertedString("brih_Uploaded",LangFile)+" "+MultilingualUtil.ConvertedString("brih_successfully",LangFile));
							}
						}//iftopic
						//-------------------unzip close-------------//
					}
					catch(Exception ex){ErrorDumpUtil.ErrorLog(ex.getMessage());}
				}//scorm
				else {
					scormDir1.delete();
                                       	f.delete();
					//data.setMessage("The zip file is not contain scorm(1.2 & 2004) complaint  imsmaniifest.xml file  !!");
					data.setMessage(MultilingualUtil.ConvertedString("scorm_mesg",LangFile));
				}
			 }//ifzip
			 else
                              	//data.setMessage("File not in a zip format");
				data.setMessage(MultilingualUtil.ConvertedString("upload_msg3",LangFile));
		}
		catch(Exception e){
                 	       	ErrorDumpUtil.ErrorLog("The Error in  Upload Method "+e.getMessage());
		}
 	}
	public boolean isValidManifest(File file)
	{	
		try {	
        		File manifestFile = null;
                	File tempFile = File.createTempFile("~rld-", ".xml");
                	tempFile.deleteOnExit();
			manifestFile = findManifest(file, tempFile);
			//ErrorDumpUtil.ErrorLog("manifestFile---------   "+manifestFile);
                	if(manifestFile == null) { 
				return false;
			}
			else 
                	{
                		manifestFile = file;
				return true;
                	}
		}
		catch(Exception e){ ErrorDumpUtil.ErrorLog("The Error in isValidManifest "+e.getMessage()); return false;}	
        }

	 /**
         * Method to check if imsmanifest.xml file exists in a zip file.
         * @param zipFile
         * @returns the file if found, null if not found
         */
		
        public File findManifest(File zipFile, File tempFile) throws FileNotFoundException, IOException {
		try{
			//ErrorDumpUtil.ErrorLog(zipFile+"\n\n"+tempFile);
                	return extractZipEntry(zipFile, "imsmanifest.xml", tempFile);
                        //ErrorDumpUtil.ErrorLog("zipFile"+zipFile);
		}catch(Exception e){ErrorDumpUtil.ErrorLog("The Error in find manifestfile "+e.getMessage());return null;}
        }
	
	public static File extractZipEntry(File zipFile, String entryName, File outFile) throws IOException {
		File outFile1=null;
		try{
			outFile1=outFile;
			
	                ZipInputStream zIn;
        	        ZipEntry zipEntry;
                	int bytesRead;
	                final int bufSize = 5024;
        	        byte buf[] = new byte[bufSize];
                	boolean foundEntry = false;
		
	                // Ensure that the parent Folder exists
        	        if(!outFile1.getParentFile().exists()) {
                	        outFile1.getParentFile().mkdirs();
                	}
		
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(zipFile), bufSize);
                	zIn = new ZipInputStream(in);
			//	Find the entry	
                	while((zipEntry = zIn.getNextEntry()) != null) {
                        	String zipEntryName = zipEntry.getName();
                        	if(zipEntryName.equals(entryName)) {
                                	foundEntry = true;
                                	break;
                        	}
                        	zIn.closeEntry();
                	}
			//If we didn't get it return
                	if(foundEntry == false) {
                       		return null;
                	}
			
			// Extract it and save to outFile
                	BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFile1), bufSize);
                	while((bytesRead = zIn.read(buf)) != -1) {
                        	out.write(buf, 0, bytesRead);
                        	
					try {	Thread.sleep(2); } catch(InterruptedException ex) {
                                	ex.printStackTrace(); 
                        		}
                	}
			out.flush();
                	out.close();
			
                	zIn.close();
			
			// Restore time stamp
                	return outFile1;
		}catch(Exception e){ErrorDumpUtil.ErrorLog("The Error in extractZipEntry "+e.getMessage()); return outFile1; }
		
	}
        // Method for launch
        public void doLaunch(RunData data, Context context)
	{
               try{
                        ParameterParser pp=data.getParameters();
			LangFile=data.getUser().getTemp("LangFile").toString();
                        User user = data.getUser();
                        String course_id=(String)user.getTemp("course_id");
                        context.put("courseid",course_id);
			String ziptopic =pp.getString("ziptopic","");
                        context.put("ziptopic",ziptopic);
			String type =pp.getString("type","");
                        context.put("type",type);
			String PDate="",topicname="";
                        String courseRealPath=TurbineServlet.getRealPath("/Courses");
			String filepath=(courseRealPath+"/"+course_id+"/"+"/Scormpackage");
                        XmlWriter xmlWriter=null;
                        String xmlfile="/PackageList.xml";
                        TopicMetaDataXmlReader topicmetadata=null;
                        topicmetadata=new TopicMetaDataXmlReader(filepath+xmlfile);
                        Vector collect=topicmetadata.getFileDetails();
                        if(collect!=null)
                        {
                        	for(int i=0;i<collect.size();i++)
                                {//for
                                	String filename =((FileEntry) collect.elementAt(i)).getName();
                                        String status =((FileEntry) collect.elementAt(i)).getAlias();
                                        String pdate =((FileEntry) collect.elementAt(i)).getPDate();
                                        if(ziptopic.equals(filename))
					{
						topicname=filename;
					}
                                }//for	
                                xmlWriter=TopicMetaDataXmlWriter.WriteXml_New(filepath,xmlfile);
                                TopicMetaDataXmlWriter.appendFileElement(xmlWriter,topicname,type,PDate);
                                xmlWriter.writeXmlFile();
				Vector str=DeleteEntry(filepath,xmlfile,topicname,data);
                                if(type.equals("Launch"))
                                //data.setMessage("Package Launch successfully");
				data.setMessage(MultilingualUtil.ConvertedString("brih_package",LangFile)+" "+MultilingualUtil.ConvertedString("brih_Launch",LangFile)+" "+MultilingualUtil.ConvertedString("brih_successfully",LangFile));
                                else
                                //data.setMessage("Package Unlaunch !!");
				data.setMessage(MultilingualUtil.ConvertedString("brih_package",LangFile)+" "+MultilingualUtil.ConvertedString("brih_Unlaunch",LangFile));

                        }//

        	}
        	catch(Exception e){ErrorDumpUtil.ErrorLog("The Error in Launch Method "+e.getMessage());}
	}
//---------------------------------------------------unpack zip method------------------//
	public static void unpackZip(File zipFile, File targetFolder) throws IOException {
                unpackZip(zipFile, targetFolder, null);
        }

	/**
         * Extracts all entries out of the zip file to the specified folder
         * Target folder is created if it doesn't exist
         * Returns true or false to indicate if the progress cancel was pressed
         * @param zipFile
         * @param targetFolder
         * @param progressMonitor an optional ProgressMonitor.  This can be null.
         * @return false if the progressMonitor is cancelled
         * @throws IOException
         */
	public static boolean unpackZip(File zipFile, File targetFolder, IProgressMonitor progressMonitor) throws IOException {
            	targetFolder.mkdirs();

                BufferedInputStream in = null;
                BufferedOutputStream out = null;
                ZipInputStream zIn = null;
                ZipEntry zipEntry;
                int bytesRead;
                final int bufSize = 512;
                byte buf[] = new byte[bufSize];

                in = new BufferedInputStream(new FileInputStream(zipFile), bufSize);
                zIn = new ZipInputStream(in);
		try {
                        while((zipEntry = zIn.getNextEntry()) != null) {
                                // Don't add directories
                                if(!zipEntry.isDirectory()) {
                                        File outFile = new File(targetFolder, zipEntry.getName());

                                        // Ensure that the parent Folder exists
                                        if(!outFile.getParentFile().exists()) {
                                            outFile.getParentFile().mkdirs();
                                        }

                                        out = new BufferedOutputStream(new FileOutputStream(outFile), bufSize);

                                        // If we have a Progress Monitor, display name
                                        if(progressMonitor != null) {
                                            progressMonitor.setNote(zipEntry.getName());
                                        }

                                        int sleep_count = 0;

                                        while((bytesRead = zIn.read(buf)) != -1) {
                                                out.write(buf, 0, bytesRead);

                                                // Allow other things to happen every 40 chunks
                                                if(sleep_count >= 40) {
                                                        try {
                                                                Thread.sleep(2);
                                                        } catch(InterruptedException ex) {
                                                                ex.printStackTrace();
                                                        }
                                                        sleep_count = 0;
                                                }

                                                sleep_count++;

                                                // If we have a Progress Monitor
                                                if(progressMonitor != null && progressMonitor.isCanceled()) {
                                                        out.flush();
                                                        out.close();
                                                        zIn.close();
                                                        return false;
                                                }
                                        }
				 // Restore time stamp
                                        outFile.setLastModified(zipEntry.getTime());

                                        // Close File
                                        out.flush();
                                        out.close();
                                }

                                zIn.closeEntry();
                        }

                        zIn.close();

                }
                // We'll catch this exception to close the file otherwise it remains locked
                catch(IOException ex) {
                        zIn.close();
                        if(out != null) {
                                out.flush();
                                out.close();
                        }
                        // And throw it again
                        throw ex;
                }

                return true;
        }
public interface IProgressMonitor {

    /**
     * Set a note
     * @param name
     */
    void setNote(String name);

    /**
     * Cancel the operation
     * @return
     */
    boolean isCanceled();

    /**
     * Close it
     */
    void close();
}
//---------------------------------------------------unpack zip method------------------//
// Method for deletion 
	public void doDelete(RunData data, Context context)
	{ 
               try{
			ParameterParser pp=data.getParameters();
			LangFile=data.getUser().getTemp("LangFile").toString();
        	        User user = data.getUser();
                	String course_id=(String)user.getTemp("course_id");
		        context.put("courseid",course_id);
            		String ziptopic =pp.getString("ziptopic","");
			String xmlfile="/PackageList.xml";
                	String courseRealPath=TurbineServlet.getRealPath("/Courses");
			String filepath=(courseRealPath+"/"+course_id+"/"+"/Scormpackage");
			File scormDir=new File(filepath+"/"+ziptopic);
			SystemIndependentUtil.deleteFile(scormDir);
			Vector str=DeleteEntry(filepath,xmlfile,ziptopic,data);
                        //data.setMessage("Scorm topic deleted successfully");
			data.setMessage(MultilingualUtil.ConvertedString("brih_package",LangFile)+" "+MultilingualUtil.ConvertedString("c_msg9",LangFile));

	}
	catch(Exception e){ErrorDumpUtil.ErrorLog("The Error in Launch"+e.getMessage());}
}
	

	/**
        * This method is invoked when no button corresponding to
        * @param data RunData
        * @param context Context
        * @exception Exception, a generic exception
        * @return nothing
        */

        public void doPerform(RunData data,Context context)
        {
                try{
                        String action=data.getParameters().getString("actionName","");
                        if(action.equals("eventSubmit_doUpload")) {
				doUpload(data,context);
			}
                        else if(action.equals("eventSubmit_doLaunch")) {
				doLaunch(data,context);
			}
                        else if(action.equals("eventSubmit_doDelete")) {
				doDelete(data,context);
			}
                        else if(action.equals("eventSubmit_doUnlaunch")) {
                                doLaunch(data,context);
                        }
                        else {
                                String LangFile=data.getUser().getTemp("LangFile").toString();
                                String msg=MultilingualUtil.ConvertedString("action_msg",LangFile);
                                data.setMessage(msg);
                        }
                } catch(Exception ex){	data.setMessage("The error in Scormplayer action !!"+ex);	}
        }
	//------------------this method for upload file recording in xml file--------------\\
	public void uploadxml(String filepath,String xmlfile,String ziptopic,String type,String PDate,RunData data)
	{
		try
		{
			LangFile=data.getUser().getTemp("LangFile").toString();
                        XmlWriter xmlWriter=null;
                        boolean found=false;
			File pathlistxml= new File(filepath+"/"+xmlfile);
			if(!pathlistxml.exists())
                        {
                                xmlWriter=new XmlWriter(filepath+"/"+xmlfile);
                        }
			else
                        {
                                TopicMetaDataXmlReader topicmetadata=null;
                                topicmetadata=new TopicMetaDataXmlReader(filepath+"/"+xmlfile);
                                Vector collect=topicmetadata.getFileDetails();
                                if(collect!=null)
                                {
                                        for(int i=0;i<collect.size();i++)
                                        {//for
                                                String filename =((FileEntry) collect.elementAt(i)).getName();
                                                if(ziptopic.equals(filename))
                                                        found=true;
                                                        //data.setMessage("This package is already exsist !!");
							data.setMessage(MultilingualUtil.ConvertedString("brih_package",LangFile)+" "+MultilingualUtil.ConvertedString("brih_is",LangFile)+" "+MultilingualUtil.ConvertedString("Wikiaction6",LangFile));
                                        }//for
                                }//if
                        }//else

                        if(found==false)
                        {
                                xmlWriter=TopicMetaDataXmlWriter.WriteXml_New(filepath,xmlfile);
                                TopicMetaDataXmlWriter.appendFileElement(xmlWriter,ziptopic,type,PDate);
                                xmlWriter.writeXmlFile();
                         }//
		}
		catch(Exception e){ErrorDumpUtil.ErrorLog("The Error in  "+e.getMessage());}
	}
	public  Vector DeleteEntry(String filePath,String xmlfile,String ziptopic,RunData data)
        {
                Vector Read=null;
                try
                {
                        XmlWriter xmlWriter=null;
                        int seq=-1;
                        TopicMetaDataXmlReader tr =new TopicMetaDataXmlReader(filePath+xmlfile);
                        Read=tr.getFileDetails();
                        if(Read != null)
                        {
                                for(int n=0;n<Read.size();n++)
                                {
                                        String name =((FileEntry)Read.elementAt(n)).getName();
                                        if(ziptopic.equals(name))
                                        {
                                                seq=n;
                                                break;
                                        }
                                }
                        }
                        /**
                        Here we deleting the particular entry from the "xml" file
                        */
                        xmlWriter=TopicMetaDataXmlWriter.WriteXml_New(filePath,xmlfile);
                        xmlWriter.removeElement("File",seq);
                        xmlWriter.writeXmlFile();
                }//try
                catch(Exception e){
                                   ErrorDumpUtil.ErrorLog("Error in method:DeleteEntry !!"+e);
                                   data.setMessage("See ExceptionLog !! " );
                                }
                return Read;
        }//readmethod

}


