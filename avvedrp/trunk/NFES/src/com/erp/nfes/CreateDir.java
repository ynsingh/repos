
 /* Created on Dec 07, 2010
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.erp.nfes;
import java.io.File;

/**
 * @author ahis
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class CreateDir {
	
//	function to create directory.
	public void CreateFolder(String url,String dir) {
		
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
