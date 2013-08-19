package org.iitk.brihaspati.modules.utils;

/*@(#)StringUtil.java
 *  Copyright (c) 2003-2006,2010, 2011 ETRG,IIT Kanpur. http://www.iitk.ac.in/
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
 */
/**
  * This class handle the illegal and xml character
  * @author <a href="mailto:nksngh_p@yahoo.co.in">Nagendra Kumar Singh</a>
  * @author <a href="mailto:sharad23nov@yahoo.com">Sharad Singh</a>
  * @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
  * @modified date 15-09-2010
  */

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.util.StringTokenizer;

public class StringUtil
{
	/**
	 * This method checks for illegal characters in the string
	 * @param to_be_checked String contains the string to be checked for illegal characters
	 * @return int
	 * These characters are invalid to avoid sql querry injection. These need to be removed and instead escaping and de-escaping routines need to be
	 * inserted and used where direct sql querries are made.
	 */

	public static int checkString(String to_be_checked)
	{
		int x=-1;
		//char illegal_characters[]={'!' , '@' , '#' , '$' , '%' , '^' , '&' , '*' , '(' , ')' , '-' , '=' , '+' , '<' , ',' , '>' , '.' , '/' , '?' ,  ';' , '\"' , '\'' , '{' , '[' , '}' , ']' } ;
		//char illegal_characters[]={'!' , '#' , '$' , '%' , '^' , '&' , '*' , '(' , ')' , '-' , '=' , '+' , '<' , ',' , '>' , '/' , '?' ,  ';' , '\"' , '\'' , '{' , '[' , '}' , ']' } ;
		char illegal_characters[]={'!' , '#' , '$' , '%' , '^' , '&' , '*' , '(' , ')' ,  '=' , '+' , '<' , ',' , '>' , '/' , '?' ,  ';' , '\"' , '\'' , '{' , '[' , '}' , ']' } ;
		for(int a=0;a<illegal_characters.length;a++)
		{
		    x=to_be_checked.indexOf(illegal_characters[a]);
		    if(x!=-1)
			break;
		}
		return(x);
		// returns -1 if illegal character not present, else
		// returns the location of first occurance of illegal
		// character.
	}

	/**
	 * This method checks for the number of tokens present in the string
	 * @param to_be_checked String contains the string to be checked for number of tokens
	 * @param start_index int contains starting index for search 
	 * @param end_index int contains end index for search
	 * @return int specifies number of delimiters
	 */

	public static int checkNumberofTokens(String to_be_checked,int start_index,int end_index)
	{
		String Temp=to_be_checked.substring(start_index,end_index);
		StringTokenizer check=new StringTokenizer(Temp,";");
		int number_of_delim=check.countTokens();
		return(number_of_delim);
	}
	

	/**
	 * This method replaces the special characters with their equivalent string for XML
	 * @param data String contains the string to be checked for XML special characters
	 * @return nothing
	 */

	public static String replaceXmlSpecialCharacters(String data){
		int[] special={34,38,39,60,62,47};

		/**
		 * ASCII values
		 * " = 34 , & = 38 , ' = 39, < = 60 , > = 62, / = 47 
		 */

		StringBuffer sb=new StringBuffer(data);
		
		for(int i=0;i<6;i++){
			int character=special[i];
			int start_index=0;
			int from_index=0;
			int last_index=0;
			while(from_index <= last_index){
				start_index=data.indexOf(character,from_index);
				last_index=data.lastIndexOf(character);
				
				if(start_index!=-1){
					int index=start_index+1;
					if(character==38){
						if(index+4<=data.length()){
                                                        if(!(data.substring(index,index+4).equals("amp;"))){
                                                                sb.replace(start_index,index,"&amp;");
                                                        }
                                                }
                                                else{
                                                        sb.replace(start_index,index,"&amp;");
                                                }
					}
					else if(character==60){
						sb.replace(start_index,index,"&lt;");
					}
					else if(character==62){
						sb.replace(start_index,index,"&gt;");
					}
					else if(character==39){
						sb.replace(start_index,index,"&apos;");
					}
					else if(character==34){
						sb.replace(start_index,index,"&quot;");
					}
					else if(character==47){
						sb.replace(start_index,index,"&amp;frasl;");
					}
					data=new String(sb);
					from_index=data.indexOf(";",start_index);
				}
			}
		}
		return(data);
	}

	/**
	 * This method insert the specified charaters between the tokens if the tokens are empty
	 * @param sourceFile String contains the path of the file which is to be read and
	 *		     uploaded
	 * @param destinationFile String contains the path where the file has to be stored
	 * 			  after changes 
	 * @param delimiter char contains the character which is the delimiter in the file
	 */

	public static void insertCharacter(String sourceFile, String destinationFile,char delimiter, char insertCharacter)
	{
		try{
			FileReader fr=new FileReader(sourceFile);
	                BufferedReader br=new BufferedReader(fr);

        	        String line;		

			FileOutputStream fout=new FileOutputStream(destinationFile);
			while( (line=br.readLine())!=null ){
				StringBuffer sb=new StringBuffer(line);
				int startIndex=line.indexOf(delimiter);
				int endIndex=line.length()-1;

				while( (startIndex <= endIndex) && (startIndex > 0) )
				{
					int nextIndex=startIndex+1;
					try{
						if(line.charAt(nextIndex)==delimiter)
						{
							sb.insert(nextIndex,insertCharacter);
						}
					}catch(Exception e){
						sb.insert(nextIndex,insertCharacter);
					}
			
					line=new String(sb);
					startIndex=line.indexOf(delimiter,nextIndex+1);
					endIndex=line.length()-1;
				}
				fout.write(line.getBytes());
				fout.write(("\n").getBytes());
			}
			fout.close();
		}
		catch(Exception e){
		}
	}

	 /**
         * This method checks for the number of lines present in the file
         * @param file file contains number of lines
         * @return long specifies number of lines
         */

        public static long checkNumberoflines(File file )
        {
                long numLines=0;
	try{
		BufferedReader in =new BufferedReader(new FileReader(file));
                String line;
                do {
	                line = in.readLine();
        	        if (line != null)
                	{
                		numLines++;
                        }
                }
                while (line != null);
	}
	catch(Exception ex){ErrorDumpUtil.ErrorLog("Error in String Util (checkNumberoflines) block==>"+ex);}
	                return(numLines);
        }

	 /**
          * This method insert the specified charaters between the tokens if the tokens are empty
          * @param sourceFile String contains the path of the file which is to be read 
          * @param destinationFile String contains the path where the file having marks stored after change 
          * @param destinationtmpFile String contains the path where the file having formula if exist
	  *                            in any cell
          * @param delimiter char contains the character which is the delimiter in the file
          */

	
	public static void insertCharSpreadsheet(String sourceFile, String destinationFile,String destinationtmpFile,char delimiter, char insertCharacter)
        {
                try{
			/**
			 *@param fr FileReader that read marks file  
			 */
			
                        FileReader fr=new FileReader(sourceFile);
                        BufferedReader br=new BufferedReader(fr);

                        String line ;
                        FileOutputStream fout=new FileOutputStream(destinationFile);
                        FileOutputStream fout1=new FileOutputStream(destinationtmpFile);
                        while( (line=br.readLine())!=null ){//first while
                                StringBuffer sb=new StringBuffer(line);
				/**
				 *@param startIndex Integer contains index of delimiter 
				 *@param endIndex Integer contains length of line
				 */
                                int startIndex=line.indexOf(delimiter);
                                int endIndex=line.length()-1;

                                while( (startIndex <= endIndex) && (startIndex > 0) )
                                {//2 while
					/**
					 *@param nextIndex Integer gives next index from start index
					 */
                                        int nextIndex=startIndex+1;
                                        try{
						/**
						 *At next index of line, if there is a delimiter it shows 
						 *blank space then insert '-' at that place 
						 */
                                                if(line.charAt(nextIndex)==delimiter)
                                                {
                                                        sb.insert(nextIndex,insertCharacter);
                                                }
                                        }catch(Exception e){
                                                sb.insert(nextIndex,insertCharacter);
                                        }
					/**
					 *after inserting '-', it gives new string
					 *then again check index of delimiter it gives start index
					 *now find end index 
					 */
                                        line=new String(sb);
                                        startIndex=line.indexOf(delimiter,nextIndex+1);
                                        endIndex=line.length()-1;
				}//end of 2 while	
				/**
				 *@param Tok String tokenizer, tokenize string with "," 
				 */
				StringTokenizer Tok = new StringTokenizer(line,",");
				while (Tok.hasMoreTokens())
                                {//3 while
	                                /**
	                                 *@param c Getting next token in the String
	                                 */
                                	String c = Tok.nextToken();
					/**
					 *@param pos Integer that contain index of '#'in each token
					 */
					int pos = c.indexOf("#");
					/**
					 * if value of pos is positive it shows token having '#'
					 * that gives cell having formula
					 */
					if(pos > 0)
					{
						StringTokenizer Tok2 = new StringTokenizer(c,"#");
	                                        while(Tok2.hasMoreTokens())
	                                                {// 4 while
	                                                        /**
	                                                         * @param resValue save result of formula in string
	                                                         */
	                                                        String resValue = Tok2.nextToken();  
	                                                        /**
	                                                         * @param frmValue save formula in another string
	                                                         */
	                                                        String frmValue = Tok2.nextToken();  
								/**
				                                 * write result in MARK.txt file
				                                 */
				                                fout.write((resValue).getBytes());
				                                /**
				                                 * write formula in TMPMARK.txt file
			        	                         */
				                                fout1.write((frmValue).getBytes());
				                                fout1.write(("\n").getBytes());
	                                    		}//end of 4 while
	                         	}// end of if
	                                /**
        	                         * else cell not having formula then write in MARK.txt file
                	                 */
                        	        else
                                	fout.write(c.getBytes());
	                                fout.write((",").getBytes());
				}//end of 3 while
                                fout.write(("\n").getBytes());
			}//end of 1 while
                                fout.close();//close 1 file 
                                fout1.close();//close 2 file
	
		}// end of try
                catch(Exception e){
                }
        }// end of method
				
}
