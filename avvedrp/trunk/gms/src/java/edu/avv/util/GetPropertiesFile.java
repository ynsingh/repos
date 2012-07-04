package edu.avv.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertiesFile{
	static Properties properties = new Properties();   
	public static Properties GetPropertiesFileFromClassPath(String propFileName){
		try {
			InputStream inputStream = GetPropertiesFile.class.getClassLoader().getResourceAsStream(propFileName);
			if(inputStream == null){
				throw new FileNotFoundException("property file '" + propFileName
				    + "' not found in the classpath");
        	}
            properties.load(inputStream);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}
    public static Properties GetPropertiesFileFromCONF(String propFileName){
    	try {
    		properties.load(new FileInputStream("../conf/"+propFileName));
    		/* Uncomment next line and comment above line to read properties file from clsasspath */
    		
		} catch (Exception e) {
			e.printStackTrace();
			properties = GetPropertiesFileFromClassPath(propFileName);
		}
    	return properties;
	}
    public static String GetPropertiesFromPropertiesFile(String propFileName,String p){
      return GetPropertiesFileFromCONF(propFileName).getProperty(p);
    }
}
