
package org.iitk.brihaspati.modules.utils.security;

/*@(#)ReadNWriteInTxt.java
 *  Copyright (c) 2012 ETRG,IIT Kanpur. http://www.iitk.ac.in/
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
 */

/**
 * This class provide the facility to read and write the text file in tag value pair
 * @author <a href="mailto:nksinghiitk@gmail.com">Nagendra Kumar Singh</a>
 */
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;

import org.apache.commons.lang.StringUtils;

public class ReadNWriteInTxt{

	/**
  	 * Method To Write File
  	 */
	public static void writeF(String fileName, String value) {
    		try {
			//ErrorDumpUtil.ErrorLog("I am read and write txt in write ");
			File fFile = new File(fileName);
			if(!fFile.exists()){
				fFile.createNewFile();
			}
			removeLineFromFile(fileName, value);
//			ErrorDumpUtil.ErrorLog("I am read and write txt in write after create file ");
			FileWriter fstream = new FileWriter(fFile,true);
			BufferedWriter out = new BufferedWriter(fstream);
			//Writer out = new OutputStreamWriter(new FileOutputStream(fFile));
		      	out.write(value + System.getProperty("line.separator"));
//			ErrorDumpUtil.ErrorLog("I am read and write txt in write after create file "+tagName+" = "+value + System.getProperty("line.separator"));
			out.flush();
		      	out.close();
    		}
		catch(IOException ex){
			log("The Exception in writeF in ReadNWriteInTxt util java"+ex );
//			ErrorDumpUtil.ErrorLog("The Exception in writeF in ReadNWriteInTxt util java"+ex );
                }
  	}
	/**
  	 * Method To Read File
  	 */
	public static String readF(String fileName,String tagName) {
		String value=null;
		String name=null;
		
    		try {
			File fFile = new File(fileName); 
			Scanner scanner = new Scanner(new FileReader(fFile));
      			//first use a Scanner to get each line
	            	while ( scanner.hasNextLine() ){
				//use a second Scanner to parse the content of each line 
				Scanner scanner1 = new Scanner(scanner.nextLine());
				scanner1.useDelimiter(";");
				if ( scanner1.hasNext() ){
					name = scanner1.next();
					if(name.equals(tagName)){
				        	value = scanner1.next();
						break;
					}
				}
				else {
				        log("Empty or invalid line. Unable to process.");
				}
				//no need to call scanner.close(), since the source is a String
                	}
		log("Name is : " + name.trim() + ", and Value is : " + value.trim());
               	scanner.close();
                }
		catch(IOException ex){
			log("The Exception in readF in ReadNWriteInTxt util java"+ex );
                }
	return value;
	}
	/**
	 * Method To delete line from a file
	 **/
	public static void removeLineFromFile(String file, String lineToRemove) {
		try {
      			File inFile = new File(file);
		      	if (!inFile.isFile()) {
        			System.out.println(" is not an existing file");
        		return;
      			}
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
      			BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
      			
			String ToRemove=StringUtils.substringBefore(lineToRemove,";");
      			String line = null;
			while ((line = br.readLine()) != null) {
        	        	if (!line.trim().startsWith(ToRemove)) {
					pw.println(line);
          				pw.flush();
        			}
      			}
      			pw.close();
      			br.close();

			if (!inFile.delete()) {
			        System.out.println("Could not delete file");
		        	return;
      			} 

			if (!tempFile.renameTo(inFile))
			        System.out.println("Could not rename file");
      
    		}
    		catch (FileNotFoundException ex) {
      			ex.printStackTrace();
    		}
    		catch (IOException ex) {
      			ex.printStackTrace();
    		}
  	}
	private static void log(Object aObject){
		System.out.println(String.valueOf(aObject));
  	}
}
