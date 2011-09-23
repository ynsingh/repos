
package com.myapp.struts.utility;

/**
 *
 * @author EDRP-AMU-06 Kedar Kumar
 */






public class pathConversion {
 

	public static String getPath(String path)
        {
            path=path.substring(0,path.lastIndexOf("/"));
            path=path.substring(0,path.lastIndexOf("/"));
            path=path.substring(0,path.lastIndexOf("/"));
            path=path+"/web/";
            return path;
            
        }
      
       


      
}

