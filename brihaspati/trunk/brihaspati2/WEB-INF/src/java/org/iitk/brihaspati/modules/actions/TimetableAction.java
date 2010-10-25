package org.iitk.brihaspati.modules.actions;

import java.io.*;
import java.util.*;

import jxl.Workbook;
import jxl.demo.XML;
import jxl.read.biff.BiffException;

import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.RunData;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.*;

import org.iitk.brihaspati.modules.utils.MultilingualUtil;
import org.iitk.brihaspati.modules.screens.call.Timetable.TimetableException;
import org.iitk.brihaspati.modules.screens.call.Timetable.Methods;

public class TimetableAction extends SecureAction
{
	private Log log = LogFactory.getLog(this.getClass());
	String xmlFile=new String();
	
	public void doUpload(RunData data, Context context) throws Exception
	{
		FileItem fileItem = data.getParameters().getFileItem("file");
		String fileName = fixFileName( fileItem.getName() );
		
		StringTokenizer st=new StringTokenizer(fileName,".");
		String file = st.nextToken();
		String fileExtension = st.nextToken();
		if(!fileExtension.equals("xls")) {
			context.put("msg", "Only xls files are supported currently.");
			return;
		}
		

		User user = data.getUser();
	    String username = user.getName();
		Date date = new Date();
		String department = (String)data.getSession().getAttribute("department");
		
		String newDir = date.getDate() + "-" + date.getMonth() + "-" + new String("" + (date.getYear() + 1900))
		+ " " + date.getHours() + ":" + date.getMinutes()+ ":" + date.getSeconds()	;
	
		String path = "/reports/" + username + "/" + department + "/" + newDir;
		String dirPath = data.getServletContext().getRealPath(path) + "/";
		Methods.checkDirectoryPath(dirPath);
		
		String filePath = dirPath + file;
		System.out.println("Filepath for uploaded file is " + filePath);

		String xlsFile = filePath + "." + fileExtension;
		fileItem.write(new File(xlsFile));
		System.out.println(xlsFile + " file has been uploaded.");

		/* Generate xml file of the uploaded xls file at same place */
		
		try {
			System.out.println("Generating xml file for " + fileName);
			
			xmlFile = filePath + ".xml";
			System.out.println("xmlfile is : " + xmlFile);
			
			generateXml(xlsFile);
			System.out.println("Generated XML successfully.");
		} catch(Throwable t) {
			t.printStackTrace();
			System.out.println("SEVERE: Error in generating xml file");
			context.put("msg", "Error in uploaded file. Please review it.");
			return;
		}
		
		doInit(data, context);
		return;
	}
	
	public void generateXml(String xlsFile) throws BiffException, IOException {
		String encoding = "UTF8";
		boolean formatInfo = false;

		String file=xlsFile;
		Workbook w = Workbook.getWorkbook(new File(file));
		new XML(w,System.out, encoding, formatInfo, xmlFile);
	}
	
	public void doInit( RunData data, Context context) throws Exception
	{
		getDepartments(data,context);
		String department = (String)data.getSession().getAttribute("department");
		if(department.equals("")) 
			return;
		
		getUploadedFiles(data, department);
		return;
	}
	
	public void doManagedepartment(RunData data, Context context) throws Exception
	{
		User user = data.getUser();
		String username = user.getName();
		String path = "/reports/" + username + "/" ;
		String filesPath=data.getServletContext().getRealPath(path);
		String mode = data.getParameters().getString("mode");
		File directory = new File(filesPath);
		File[] dir = directory.listFiles();	

		if(mode.equals("get")) {
			/*Vector dirs = new Vector();	
			for(int index = 0 ; index < dir.length; ++index)
			{
				File uploadDir = dir[index].getAbsoluteFile();
				if(uploadDir.isDirectory()) {
					String dirName = uploadDir.toString();
					StringTokenizer st = new StringTokenizer(dirName, "-");
					while(st.hasMoreElements())
						dirName = st.nextToken();
					dirs.add(dirName);
				}
			}
			System.out.println("Entering the page to manage departments.");
			data.getRequest().setAttribute("dirList", dirs);*/
		}
		else if(mode.equals("update")) {
			System.out.println("Updating department list.");
			String[] departments = data.getParameters().getStrings("department");
			int len = departments.length;

			/** DELETE **/
			for(int index = 0 ; index < dir.length; ++index)
			{
				File uploadDir = dir[index].getAbsoluteFile();
				if(uploadDir.isDirectory()) {
					String dirName = uploadDir.getName();
					System.out.println(dirName);
					//	StringTokenizer st = new StringTokenizer(dirName, "-");
					//	dirName = st.nextToken();
					boolean flag = false;
					for(int i = 0 ; i < len; ++i)
						if(dirName.equals(departments[i])) {
							flag = true;
							break;
						}
					if(!flag) {
						boolean success = deleteDirectory(uploadDir);
						if(!success)
							System.out.println(uploadDir + " Not Deleted");
						else 
							System.out.println(uploadDir + " Deleted");
					}
				}
			}
			/** CREATE **/
			directory = new File(filesPath);
			dir = directory.listFiles();	
			for(int i = 0; i < len; ++i)
			{
				System.out.println(departments[i]);
				boolean flag = false;
				for(int index = 0 ; index < dir.length; ++index)
				{
					File uploadDir = dir[index].getAbsoluteFile();
					String dirName = uploadDir.getName();
					if(departments[i].equals(dirName)) {
						flag = true;
						break;
					}	
				}
				if(!flag) {
					String filePath = filesPath + "/" + departments[i];
					Methods.checkDirectoryPath(filePath);
				}
			}
			data.getSession().setAttribute("department", "");
			doInit(data, context);
		}
	}

	
	public void doSetdepartment( RunData data, Context context) throws Exception
	{
		String department = data.getParameters().getString("department", "");
		data.getSession().setAttribute("department", department);
		
		System.out.println("Department is " + department);
		log.info("Department is " + department);
		getUploadedFiles(data, department);
		
		StringTokenizer st = new StringTokenizer(department, "~");
		data.getSession().setAttribute("department_display", st.nextToken());
		return;
	}
	
	public void getDepartments( RunData data, Context context) throws Exception
	{
		User user = data.getUser();
		String username = user.getName();
		String path = "/reports/" + username + "/" ;
		String filesPath=data.getServletContext().getRealPath(path);
		Methods.checkDirectoryPath(filesPath);
		
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


	public void getUploadedFiles(RunData data, String department) throws Exception
	{
		User user = data.getUser();
		String username = user.getName();
		
		String path = "/reports/" + username + "/" + department;
		String filesPath=data.getServletContext().getRealPath(path)+ "/";
		
		Methods.checkDirectoryPath(filesPath);
		
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
	
	/*
	 * Fix file name if uploaded from a windows client
	 */
	public static String fixFileName(String longFile)
	{
		int loc = longFile.lastIndexOf("\\");
		return longFile.substring( (loc+1), longFile.length() );
	}
	
	static public boolean deleteDirectory(File path) {
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

	/*
	 * Default action to perform if the specified action cannot be executed.
	 */
	public void doPerform( RunData data,Context context ) throws Exception
	{
		String LangFile=data.getUser().getTemp("LangFile").toString();  
		String msg=MultilingualUtil.ConvertedString("action_msg",LangFile);
		String action=data.getParameters().getString("actionName","");
		
		if(action.equals("eventSubmit_doUpload")) {
			doUpload(data, context);
		}
		else if(action.equals("eventSubmit_doSetdepartment"))
		{
			doSetdepartment(data, context);
		}
		else
		{
			data.setMessage(msg);
		}	
	}
}
