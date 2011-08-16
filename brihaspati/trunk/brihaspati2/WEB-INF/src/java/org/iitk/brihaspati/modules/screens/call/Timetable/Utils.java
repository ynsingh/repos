package org.iitk.brihaspati.modules.screens.call.Timetable;

import java.io.File;
import java.util.ArrayList;

import java.io.*;
import java.util.*;

import jxl.*;
import jxl.read.biff.BiffException;

import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.RunData;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.*;

import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.screens.call.Timetable.*;


public class Utils implements Constants {

	private static ArrayList<String> ERROR_MSGS = new ArrayList<String>();
	
	public static void clearErrorMsg() {
		ERROR_MSGS = new ArrayList<String>();
	}

	public static void addErrorMsg(String arg) {
		ERROR_MSGS.add(arg);
	}
	
	public static ArrayList<String> getErrorMsgs() {
		return ERROR_MSGS;
	}
	
	 public static boolean checkDirectoryPath(String path) throws TimetableException {
		String rootPath = path;
		boolean created = false;
		File root = new File(rootPath);
		created = createDirectory(root);
		return created;
	}
	
	public static boolean createDirectory(File file) throws TimetableException {
		String message = "Error while creating directory: ";
		boolean created = false;
		if(!file.isDirectory()) {
			if(!file.mkdirs()) {
				throw new TimetableException(message + file.toString());
			}
			created = true;
		}
		return created;
	}


	public static void getUploadedFiles(RunData data, String department) throws Exception
	{
		User user = data.getUser();
		String username = user.getName();
		
		String path = "/reports/" + username + "/" + department;
		String filesPath=data.getServletContext().getRealPath(path)+ "/";
		
		Utils.checkDirectoryPath(filesPath);
		
		File directory = new File(filesPath);
		File[] dir = directory.listFiles();	

		Hashtable allFiles = new Hashtable();
		Hashtable xmlFiles = new Hashtable();
		
		/* searching in the department directory for dirs. */
		for (int index = 0; index < dir.length; index++) {
			
			File uploadDir = dir[index].getAbsoluteFile();
			System.out.println(uploadDir.toString());
			
			if(uploadDir.isDirectory()) {
				
				File[] files = uploadDir.listFiles();
				/* searching in the uploaded dir for xls file - should be only one. */
				for(int counter = 0 ; counter < files.length; counter++) {
					
					String fileName = files[counter].toString();
					if(fileName.endsWith("xls")) {
						StringTokenizer st = new StringTokenizer(fileName, "/");
						while(st.hasMoreElements())
							fileName = st.nextToken();

						String xmlFile = fileName.substring(0, fileName.length() - 4) + ".xml";
						String file = fileName;
						String date = uploadDir.getName();
						System.out.println(file + " | " + date + " | " + xmlFile);
						allFiles.put(date, file);
						xmlFiles.put(date, xmlFile);
						/* only one xls file in one directory*/
						break;
					}
				}
			}
		}
		
		data.getSession().setAttribute("xmlFiles", xmlFiles);
		data.getSession().setAttribute("allFiles", allFiles);
		return;
	}

	public static void getDepartments( RunData data, Context context) throws Exception
	{
		User user = data.getUser();
		String username = user.getName();
		String path = "/reports/" + username + "/" ;
		String filesPath=data.getServletContext().getRealPath(path);
		Utils.checkDirectoryPath(filesPath);
		
		Hashtable<String, String> dirs = new Hashtable<String, String>();
		File directory = new File(filesPath);
		File[] dir = directory.listFiles();	
		for (int index = 0; index < dir.length; index++) {
			File uploadDir = dir[index].getAbsoluteFile();
			if(uploadDir.isDirectory()) {
				String dirName = uploadDir.getName();
				StringTokenizer st = new StringTokenizer(dirName, "~");
				String value = st.nextToken();
				String key = st.nextToken();
				System.out.println("dir: " + dirName);
				dirs.put(key, value);
			}
		}
		data.getSession().setAttribute("directories", dirs);
	}
		
	public static boolean deleteDirectory(File path) {
		if( path.exists() ) {
			File[] files = path.listFiles();
			for(int i=0; i<files.length; i++) {
				if(files[i].isDirectory()) {
					deleteDirectory(files[i]);
				}
				else {
					files[i].delete();
				}
			}
		}
		return( path.delete() );
	}

}
