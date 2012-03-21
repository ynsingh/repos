package com.erp.nfes;

import java.io.File;

public class CreateDir {
	
//	function to create directory.
	public static void CreateFolder(String url,String dir) {
		File file=new File(url+"/"+dir);
		if(file.mkdir())
		{
			System.out.println("Created Directory "+url+"/"+dir);
		}
		else
		{
			System.out.println("Error occured while creating Directory "+url+"/"+dir);
		}
		
	}//end of function.
}