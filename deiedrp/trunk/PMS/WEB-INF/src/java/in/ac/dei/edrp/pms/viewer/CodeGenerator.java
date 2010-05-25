package in.ac.dei.edrp.pms.viewer;

import java.util.Calendar;
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