package org.iitk.brihaspati.modules.utils;
/*
 * @(#)CommentedProperties.java
 *
 *  Copyright (c) 2013 ETRG,IIT Kanpur. 
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
 *  
 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 *
 */
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.util.Vector;
import org.apache.commons.lang.StringUtils;
/**
*  @author <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
*/

/**
 * The CommentedProperties class is an extension of java.util.Properties
 * to allow retention of comment lines and blank (whitespace only) lines
 * in the properties file.
 */
public class CommentedProperties extends java.util.Properties {

	/**
	 * Use a Vector to keep a copy of lines that are a comment or 'blank'
	 */
	public Vector lineData = new Vector(0, 1);

	/**
	 * Use a Vector to keep a copy of lines containing a key, i.e. they are a property.
	 */
	public Vector keyData = new Vector(0, 1);

	/**
	 * Load properties from the specified InputStream. 
	 * Overload the load method in Properties so we can keep comment and blank lines.
	 * @param   inStream   The InputStream to read.
	 */
	public void load(InputStream inStream) 
	{
		try 	
		{
			// The file must be encoded using ISO-8859-1.
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "ISO-8859-1"));
			String line;
			/**
 			 * Used a Vector to store all keys temporary .	 	
 			 */
			Vector tmpKey = new Vector(0, 1);

			while ((line = reader.readLine()) != null) 
			{
				char c = 0;
				int pos = 0;
				// Leading whitespaces must be deleted first.
				while ( pos < line.length() && Character.isWhitespace(c = line.charAt(pos))) 
				{
					pos++;
				}
	
				// If empty line or begins with a comment character, save this line
				// in lineData and save a "" in keyData.
				if ((line.length() - pos) == 0 || line.charAt(pos) == '#' || line.charAt(pos) == '!') 
				{
					lineData.add(line);
					keyData.add("");
					continue;
				}

				// The characters up to the next Whitespace, ':', or '='
				// describe the key.  But look for escape sequences.
				// Try to short-circuit when there is no escape char.
				int start = pos;
				boolean needsEscape = line.indexOf('\\', pos) != -1;
				StringBuffer key = needsEscape ? new StringBuffer() : null;
		
				while ( pos < line.length() && ! Character.isWhitespace(c = line.charAt(pos++)) && c != '=' && c != ':') 
				{
					if (needsEscape && c == '\\') 
					{
						if (pos == line.length()) 
						{
							// The line continues on the next line.  If there
							// is no next line, just treat it as a key with an
							// empty value.
							line = reader.readLine();
							if (line == null)
								line = "";
								pos = 0;
								while ( pos < line.length() && Character.isWhitespace(c = line.charAt(pos)))
									pos++;
						} 
						else 
						{
							c = line.charAt(pos++);
							switch (c) 
							{
								case 'n':
									key.append('\n');
									break;
								case 't':
									key.append('\t');
									break;	
								case 'r':
									key.append('\r');
									break;
								case 'u':
									if (pos + 4 <= line.length()) {
										char uni = (char) Integer.parseInt(line.substring(pos, pos + 4), 16);
										key.append(uni);
										pos += 4;
									}   // else throw exception?
									break;
								default:
									key.append(c);
									break;
							}
						}
					} 
					else if (needsEscape)
						key.append(c);
				}

				boolean isDelim = (c == ':' || c == '=');
	
				String keyString;
				if (needsEscape)
					keyString = key.toString();
				else if (isDelim || Character.isWhitespace(c))
					keyString = line.substring(start, pos - 1);
				else
					keyString = line.substring(start, pos);
	
				while ( pos < line.length() && Character.isWhitespace(c = line.charAt(pos)))
					pos++;

				if (! isDelim && (c == ':' || c == '=')) 
				{
					pos++;
					while ( pos < line.length() && Character.isWhitespace(c = line.charAt(pos)))
						pos++;
				}

				// Short-circuit if no escape chars found.
				if (!needsEscape) 
				{
                                        //ErrorDumpUtil.ErrorLog("inside needsEscape---------->"+keyString+"---------line.substring(pos)----"+line.substring(pos));
					//Before adding any new key in tmpKey vector, first check existence of that key in vector 
					//if exist, then check no. of that key in a vector i.e. duplicate key
					//if count value not equal to -1 it means there is no duplicate key in vector.
					//	else add that key with count to get no. of duplicate key.
					int count = duplicateKeyCount(keyString, tmpKey);
                                        tmpKey.add(keyString);
					if(count != -1){
                                                put(keyString+"_"+count, line.substring(pos));
						// keyString in keyData.
                                                keyData.add(keyString+"_"+count);
                                        }
                                        else{
                                                put(keyString, line.substring(pos));
						// keyString in keyData.
                                                keyData.add(keyString);
                                        }
					// Save a "" in lineData and save this
                                        lineData.add("");
				//put(keyString, line.substring(pos));
				//lineData.add("");
				//keyData.add(keyString);
				continue;
				}
	
				// Escape char found so iterate through the rest of the line.
				StringBuffer element = new StringBuffer(line.length() - pos);
				while (pos < line.length()) 
				{
					c = line.charAt(pos++);
					if (c == '\\') 
					{
						if (pos == line.length()) 
						{
							// The line continues on the next line.
							line = reader.readLine();
	
							// We might have seen a backslash at the end of
							// the file.  The JDK ignores the backslash in
							// this case, so we follow for compatibility.
							if (line == null)
								break;
	
							pos = 0;
							while ( pos < line.length() && Character.isWhitespace(c = line.charAt(pos)))
								pos++;
							element.ensureCapacity(line.length() - pos + element.length());
						} 
						else 
						{
							c = line.charAt(pos++);
							switch (c) 
							{
								case 'n':
									element.append('\n');
									break;
								case 't':
									element.append('\t');
									break;
								case 'r':
									element.append('\r');
									break;
								case 'u':
									if (pos + 4 <= line.length()) 
									{
										char uni = (char) Integer.parseInt(line.substring(pos, pos + 4), 16);
										element.append(uni);
										pos += 4;
									}   // else throw exception?
									break;
								default:
									element.append(c);
									break;
							}
						}
					} 
					else
						element.append(c);
				}
				put(keyString, element.toString());
				// Save a "" in lineData and save this
				// keyString in keyData.
				lineData.add("");
				keyData.add(keyString);
			}
		}
		catch(Exception x)
		{
			ErrorDumpUtil.ErrorLog("Exception inside load method (CommentedProperties.java)!!"+x);
		}
	}

	/**
	 * Write the properties to the specified OutputStream.
	 * 
	 * Overloads the store method in Properties so we can put back comment	
	 * and blank lines.													  
	 * 
	 * @param out	The OutputStream to write to.
	 * @param header Ignored, here for compatability w/ Properties.
	 * 
	 * @exception IOException
	 */
	public void store(OutputStream out, String header) 
	{
		try
		{
			// The file must be encoded using ISO-8859-1.
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, "ISO-8859-1"));

			// We ignore the header, because if we prepend a commented header
			// then read it back in it is now a comment, which will be saved
			// and then when we write again we would prepend Another header...
	
			String line;
			String key;
			StringBuffer s = new StringBuffer ();
	
			for (int i=0; i<lineData.size(); i++) 
			{
				line = (String) lineData.get(i);
				key = (String) keyData.get(i);
				if (key.length() > 0) 
				{  // This is a 'property' line, so rebuild it
					//If key has "_" then get index of that and get key before it.
					int j=key.lastIndexOf("_");
	                                if(j!=-1)
        	                        {
						String newKey = StringUtils.substringBeforeLast(key, "_");
						formatForOutput (newKey, s, true);
					}
                                	else
                                        	formatForOutput (key, s, true);
					s.append ('=');
					formatForOutput ((String) get(key), s, false);
					writer.println (s);
				} 
				else 
				{  // was a blank or comment line, so just restore it
					writer.println (line);
				}
			} 
			writer.flush ();
		}
		catch(Exception e)
		{
			ErrorDumpUtil.ErrorLog("Exception inside store method (CommentedProperties.java)!!"+e);
		}
	}
	/**
	 * Need this method from Properties 
	 * 
	 * @param str	- the string to format
	 * @param buffer - buffer to hold the string
	 * @param key	- true if str the key is formatted, false if the value is formatted
	 */
	private void formatForOutput(String str, StringBuffer buffer, boolean key)
	{
		try
		{
			if (key) 
			{
				buffer.setLength(0);
				buffer.ensureCapacity(str.length());
			} 
			else
				buffer.ensureCapacity(buffer.length() + str.length());
			boolean head = true;
			int size = str.length();
			for (int i = 0; i < size; i++) 
			{
				char c = str.charAt(i);
				switch (c) 
				{
					case '\n':
						buffer.append("\\n");
						break;
					case '\r':
						buffer.append("\\r");
						break;
					case '\t':
						buffer.append("\\t");
						break;
					case ' ':
						buffer.append(head ? "\\ " : " ");
						break;
					case '\\':
					//case '!':
					case '#':
					case '=':
					//case ':':
						buffer.append('\\').append(c);
						break;
					default:
					if (c < ' ' || c > '~') 
					{
						String hex = Integer.toHexString(c);
						buffer.append("\\u0000".substring(0, 6 - hex.length()));
						buffer.append(hex);
					} 
					else
						buffer.append(c);
				}	
				if (c != ' ')
					head = key;
			}
		}
		catch(Exception ex)
		{
			ErrorDumpUtil.ErrorLog("Exception inside formatForOutput method (CommentedProperties.java)!!"+ex);
		}
	}
	/**
	 * Add a Property to the end of the CommentedProperties. 
	 * 
	 * @param   keyString	 The Property key.
	 * @param   value		 The value of this Property.
	 */
	public void add(String keyString, String value)
	{
		put(keyString, value);
		//For adding new line of key and its value to the end of properties file.
		//lineData.add("");
		//keyData.add(keyString);
	}

	/**
	 * Add a comment or blank line or comment to the end of the CommentedProperties. 
	 * 
	 * @param   line The string to add to the end, make sure this is a comment
	 *			   or a 'whitespace' line.
	 */
	public void addLine(String line)
	{
		lineData.add(line);
		keyData.add("");
	}

	/**
 	 * Get no. of duplicate key in a vector.
 	 *
 	 * @param key The key that has to be checked in a vector.
 	 * @param V Vector for checking key.
         */ 
	public int duplicateKeyCount(String key, Vector v)
        {
                int i = -1;
                for(int j=0; j<v.size(); j++)
                {
                        if(v.get(j).equals(key))
                                i = i + 1;
                }
                return i;
        }
}
