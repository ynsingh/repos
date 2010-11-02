package org.iitk.brihaspati.modules.screens.call.Timetable;

import java.io.File;
import java.util.ArrayList;

public class Utils implements Constants {

	private static ArrayList<String> ERROR_MSGS = new ArrayList<String>();
	
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

		
}
