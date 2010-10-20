package in.ac.dei.edrp.pms.viewer;

import java.util.Calendar;
/** 
 * Creation date: 26-june-2010
 * This class is used for generating dynamic codes of project,task etc.
 * @author <a href="http://aniltiwaripms.blogspot.com" target="_blank">Anil Kumar Tiwari</a> 
 */
public class CodeGenerator
{
	public static String gettingCombineCode(String prefix_code,long maxvalue,int int_value_length)
	{
		Calendar cal=Calendar.getInstance();
		int y=cal.get(Calendar.YEAR);
		String no_zero="0",valid_code="";
			
		if(String.valueOf(maxvalue).length()<=int_value_length)
		{			
			while(no_zero.length()<=(int_value_length-1-String.valueOf(maxvalue).length()))
				no_zero=no_zero+"0";
			if(String.valueOf(maxvalue).length()<int_value_length)
			{
				//System.out.println("code="+String.valueOf(y)+org_id+" "+s+String.valueOf(maxvalue));
				valid_code=prefix_code+String.valueOf(y)+no_zero+String.valueOf(maxvalue);
			}
			else
			{
				valid_code=prefix_code+String.valueOf(y)+String.valueOf(maxvalue);
			}			
		}
		else
		{
			valid_code=prefix_code+String.valueOf(y)+String.valueOf(maxvalue);
		}		
		return valid_code;
	}
	public static String gettingCode(long maxvalue,int int_value_length)
	{
		Calendar cal=Calendar.getInstance();
		int y=cal.get(Calendar.YEAR);
		String no_zero="0",valid_code="";
			
		if(String.valueOf(maxvalue).length()<=int_value_length)
		{			
			while(no_zero.length()<=(int_value_length-1-String.valueOf(maxvalue).length()))
				no_zero=no_zero+"0";
			if(String.valueOf(maxvalue).length()<int_value_length)
			{
				//System.out.println("code="+String.valueOf(y)+org_id+" "+s+String.valueOf(maxvalue));
				valid_code=String.valueOf(y)+no_zero+String.valueOf(maxvalue);
			}
			else
			{
				valid_code=String.valueOf(y)+String.valueOf(maxvalue);
			}			
		}
		else
		{
			valid_code=String.valueOf(y)+String.valueOf(maxvalue);
		}	
		return valid_code;
	}
	
	public static String gettingTaskId(String prefix_code,long maxvalue,int int_value_length)
	{
		String no_zero="0",valid_code="";
			
		if(String.valueOf(maxvalue).length()<=int_value_length)
		{			
			while(no_zero.length()<=(int_value_length-1-String.valueOf(maxvalue).length()))
				no_zero=no_zero+"0";
			if(String.valueOf(maxvalue).length()<int_value_length)
			{
				//System.out.println("code="+String.valueOf(y)+org_id+" "+s+String.valueOf(maxvalue));
				valid_code=prefix_code+no_zero+String.valueOf(maxvalue);
			}
			else
			{
				valid_code=prefix_code+String.valueOf(maxvalue);
			}			
		}
		else
		{
			valid_code=prefix_code+String.valueOf(maxvalue);
		}	
		return valid_code;
	}
}