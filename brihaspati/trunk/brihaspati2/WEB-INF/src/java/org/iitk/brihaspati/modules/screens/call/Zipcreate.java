package org.iitk.brihaspati.modules.screens.call;
/*
 * @(#)Zipcreate.java   
 *
 *  Copyright (c) 2014 ETRG,IIT Kanpur. 
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
i *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 *  
 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 * 
 */




import java.util.Vector;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.velocity.context.Context;
import org.apache.turbine.util.RunData;
import org.apache.turbine.om.security.User;
import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.parser.ParameterParser;
import  org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.turbine.services.servlet.TurbineServlet;
import org.iitk.brihaspati.modules.utils.TopicMetaDataXmlReader;
import org.iitk.brihaspati.modules.utils.FileEntry;
import javax.servlet.ServletOutputStream;

/**
 * @author <a href="mailto:sisaudiya.dewan17@gmail.com">Dewanshu Singh Sisaudiya</a>
**/


public class Zipcreate extends SecureScreen
{

	private List<String> fileList = new ArrayList<String>();
	private String OUTPUT_ZIP_FILE = "";
	private String SOURCE_FOLDER = ""; // SourceFolder path
	private ServletOutputStream out;

	public void doBuildTemplate(RunData data,Context context)
	{
           try{

		User user=data.getUser();
		ParameterParser pp = data.getParameters();
		String Dest = pp.getString("dest_folder", ""); 
		String courseid=(String)user.getTemp("course_id");
		SOURCE_FOLDER = TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Assignment/"+Dest);
		OUTPUT_ZIP_FILE = TurbineServlet.getRealPath("/Courses"+"/"+courseid+"/Assignment/"+"/"+Dest+".zip");
		generateFileList(new File(SOURCE_FOLDER));
   		zipIt(OUTPUT_ZIP_FILE);
		File fileHnd=new File(OUTPUT_ZIP_FILE);
                        FileInputStream fis=new FileInputStream(OUTPUT_ZIP_FILE);
                        out=data.getResponse().getOutputStream();
                        String mimeTp="application/x-download";
                        data.getResponse().setHeader("Content-Type",mimeTp);
                        data.getResponse().setHeader("Content-Disposition","inline;filename="+Dest+".zip");
                        int readCount;
                        byte[] buf=new byte[4*1024];
                        while((readCount=fis.read(buf)) !=-1)
                        {
                                out.write(buf,0,readCount);
                        }
                        fis.close();
                        fileHnd.delete();
		}
			catch(Exception ex)
                {
                        data.setMessage("The error in getZip File !!"+ex);
                }
	}

	public void zipIt(String zipFile)
	{
		byte[] buffer = new byte[1024];
		String source = "";
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		String zipFile_name = "";
	   	try{
			try
      			{
         			source = SOURCE_FOLDER.substring(SOURCE_FOLDER.lastIndexOf("/") + 1, SOURCE_FOLDER.length());
      			}
     			catch (Exception e)
     			{
        			source = SOURCE_FOLDER;
     			}
     			fos = new FileOutputStream(zipFile);
     			zos = new ZipOutputStream(fos);

     			FileInputStream in = null;

    			for (String file : fileList)
     			{
				String user_name = "";
				String full_name = "";
				String roll_no = "";
				TopicMetaDataXmlReader topicmetadata=null;
		        	Vector Assignmentlist1=new Vector();
	       		 	topicmetadata=new TopicMetaDataXmlReader(SOURCE_FOLDER+"/__file.xml");
       	 			Assignmentlist1=topicmetadata.getAssignmentDetails();
        			if(Assignmentlist1!=null)
        			{
   	     				for(int c=0;c<Assignmentlist1.size();c++)
             				{
	      		     			String filereader1 =((FileEntry) Assignmentlist1.elementAt(c)).getfileName();
        	       				if(filereader1.startsWith(file))
						{
							String extension = filereader1.substring(filereader1.lastIndexOf(".") + 1, filereader1.length());
							try{
							roll_no =((FileEntry) Assignmentlist1.elementAt(c)).getRollnm();
							if(!roll_no.equals("") && !roll_no.equals(null))
							{	
								zipFile_name = roll_no;
							}
							else{
								full_name =((FileEntry) Assignmentlist1.elementAt(c)).getFullname();
								if(!full_name.equals("") && !full_name.equals(null))
								{
                                                                        zipFile_name = full_name;
								}
								else{
									user_name =((FileEntry) Assignmentlist1.elementAt(c)).getUserName();	

									zipFile_name = user_name;
								}
							}
							}catch(Exception en){
								user_name =((FileEntry) Assignmentlist1.elementAt(c)).getUserName();    
                                                                ErrorDumpUtil.ErrorLog("if roll number and full nmae is null"+user_name);

                                                                        zipFile_name = user_name;
							}
							zipFile_name = zipFile_name + "." +extension;
                				}
             				}			
        			}

        			ZipEntry ze = new ZipEntry(source + File.separator + zipFile_name);
        			zos.putNextEntry(ze);
        			try
        			{
           				in = new FileInputStream(SOURCE_FOLDER + File.separator + file);
           				int len;
           				while ((len = in.read(buffer)) > 0)
           				{
              					zos.write(buffer, 0, len);
           				}	
        			}
        			finally
        			{
           				in.close();
        			}
     			}

     				zos.closeEntry();
				fileList.clear();

			}
  			catch (Exception ex)
  			{
     				ex.printStackTrace();
				ErrorDumpUtil.ErrorLog("Error in zipIt() method is: "+ex);
  			}
  			finally
  			{
     				try
     				{
        				zos.close();
	     			}
     				catch (IOException e)
     				{
        			e.printStackTrace();
     				}
  			}
	}

	public void generateFileList(File node)
	{

  	// add file only
	if (node.isFile())
	{
		fileList.add(generateZipEntry(node.toString()));

	}

	if (node.isDirectory())
  		{
			String[] subNote = node.list();
			for (String filename : subNote)
     			{
				if(!(filename.startsWith("AssignmentFile")|| filename.endsWith("xml")))
	        		generateFileList(new File(node, filename));
     			}
  		}
	}

	private String generateZipEntry(String file)
	{
		return file.substring(SOURCE_FOLDER.length() + 1, file.length());
	}
}

