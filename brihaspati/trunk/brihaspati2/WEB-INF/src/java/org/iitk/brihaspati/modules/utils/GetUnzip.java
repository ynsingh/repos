package org.iitk.brihaspati.modules.utils;
/*
 * @(#)GetUnzip.java
 *
 *  Copyright (c) 2005,2012 ETRG,IIT Kanpur.
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
 *
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 *
 */


import java.io.FileOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;

//import java.util.zip.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;

/**
 * This class Read zip file and Extract specified path
 * @author <a href="mailto:madhavi_mungole@hotmail.com">Madhavi Mungole</a>
 * @author <a href="mailto:awadhesh_trivedi@yahoo.co.in">Awadhesh Kumar Trivedi</a>
 * @author <a href="mailto:tejdgurung20@gmail.com">Tej Bahadur</a>
 * @modified date: 07-02-2012 
 */

public class GetUnzip
{

//  private ZipInputputStream fis;
  private ZipFile zipfile;
  private String destDir;
	/***********modify the construtor to 3 parameter, send it the RunData as third Arg *************/
  public GetUnzip(String source,String destination) throws IOException
  {
     zipfile=new ZipFile( source );
     if(destination==null)
     {
	destDir="";
     }
     else
     {
	destDir=destination+"/";
	
     }					
     doUnZip();
     zipfile.close();	
   
  }
  
  public void doUnZip() throws IOException
  {
     Enumeration entries=zipfile.entries();		
     while(entries.hasMoreElements())
     {
	ZipEntry ze=(ZipEntry)entries.nextElement();
	File f=new File(destDir+ze.getName());
	if(ze.isDirectory())
	{
	   f.mkdirs();
	}
	else
	{
	   FileOutputStream fos=new FileOutputStream(f);
	   InputStream in=zipfile.getInputStream(ze);
  	   int len;
           byte[] buf = new byte[1024];

	   while ((len = in.read(buf)) > 0)
	   {
              fos.write(buf, 0, len);
           }
	
           fos.close(); 	
       }
    }
  }

/**
 * This class reads zip file and Extract it in specified folder.
 */

 public static void UnzipFileIntoDirectory(String SourceZipfile,String DestinationDir) {
        try{
                ZipFile zipFile=new ZipFile(SourceZipfile);
                Enumeration files = zipFile.entries();
                File f = null;
                FileOutputStream fos = null;

                while (files.hasMoreElements()) {
                        try {
                                ZipEntry entry = (ZipEntry) files.nextElement();
                                InputStream eis = zipFile.getInputStream(entry);
                                byte[] buffer = new byte[1024];
                                int bytesRead = 0;

                                f = new File(DestinationDir + File.separator + entry.getName());

                                if (entry.isDirectory()) {
                                        f.mkdirs();
                                        continue;
                                }
                                else {
                                        f.getParentFile().mkdirs();
                                        f.createNewFile();
                                }

                                fos = new FileOutputStream(f);

                                while ((bytesRead = eis.read(buffer)) != -1) {
                                        fos.write(buffer, 0, bytesRead);
                                }
                        }
                        catch (IOException e) {
                                e.printStackTrace();
                                continue;
                        }
                        finally {
                                if (fos != null) {
                                        try {
                                                fos.close();
                                        } catch (IOException e) {
                                        // ignore
                                        }
                                }
                        }
                }
        }
        catch(Exception e){
        }
  }
}
