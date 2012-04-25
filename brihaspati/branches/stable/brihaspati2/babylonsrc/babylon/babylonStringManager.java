package babylon;

//  Babylon Chat
//  Copyright (C) 1997-2002 J. Andrew McLaughlin
// 
//  This program is free software; you can redistribute it and/or modify it
//  under the terms of the GNU General Public License as published by the Free
//  Software Foundation; either version 2 of the License, or (at your option)
//  any later version.
// 
//  This program is distributed in the hope that it will be useful, but
//  WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
//  or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
//  for more details.
//  
//  You should have received a copy of the GNU General Public License along
//  with this program; if not, write to the Free Software Foundation, Inc.,
//  59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
//
//  babylonStringManager.java
//


import java.util.*;
import java.io.*;
import java.net.*;
import babylon.*;


public class babylonStringManager
{
    // This is the internationalization class.  This will return strings
    // to display to the user, appropriate to the user's locale

    public String language = "";
    protected Hashtable strings = null;


    public babylonStringManager(URL baseURL, String lang)
    {
	// To hold all our keys and values
	strings = new Hashtable();
	language = lang;
	//language = locale.getLanguage();
	//language = "es"; // Test Spanish
	//language = "nl"; // Test Dutch
	//language = "de"; // Test German
	//language = "fr"; // Test French

	try {
	    // Use the default locale to pick the name of the language file
	    readLanguage(baseURL, "babylonLanguage_" + language);
	}
	catch (FileNotFoundException e) {
	    // The language we are trying to use does not appear to be
	    // supported, or at least there was some error reading the
	    // file.  Try English by default instead
	    try {
		readLanguage(baseURL, "babylonLanguage_en");
	    }
	    catch (FileNotFoundException f) {
		// No dice.  Not much point in continuing with no strings,
		// but who knows
		System.out.println("Error retrieving language file.");
		f.printStackTrace();
		return;
	    }

	    // OK, we have English at least.
	    System.out.println("Error retrieving client locale " +
			       "language!  Using English as the default.");
	}
    }

    private void readLanguage(URL baseURL, String languageFileName)
	throws FileNotFoundException
    {
	// Try the name of the resource bundle based on the language
	// file name name.  This is the normal, default way that works
	// most of the time
	ResourceBundle bundle = null;
	try {
	    bundle = ResourceBundle.getBundle(languageFileName);
	    
	    // Read the strings from the resource bundle
	    readFromResourceBundle(bundle);
	}
	catch (MissingResourceException mre) {
	    // Couldn't read it as a resource bundle.  Maybe this is a
	    // Netscape 4.x browser client which erroneously munges the
	    // properties file name, and doesn't use the prescribed
	    // fallback pattern? Try to read the correct language manually
	    // instead

	    System.out.println("Reading language as a ResourceBundle " +
			       "failed.  Attempting to read the file " +
			       "manually.");
	    try {
		readFromFile(new URL(baseURL.getProtocol(),
				     baseURL.getHost(),
				     baseURL.getFile() + languageFileName +
				     ".properties"));
	    }
	    catch (Exception e) {
		// Looks like the thing does not exist, or at least we
		// couldn't read it.
		throw (new FileNotFoundException());
	    }
	}
    }

    private void readFromResourceBundle(ResourceBundle bundle)
	throws MissingResourceException
    {
	// Get all of the strings for the Hashtable using a ResourceBundle.
	// This is the preferred default.
	
	// Get all of the keys
	Enumeration keys = bundle.getKeys();

	// Get all of the strings from the resource bundle based on
	// the keys and put them in a HashMap
	while (keys.hasMoreElements())
	    {
		    String key = (String) keys.nextElement();
		    String value = bundle.getString(key);
		    //System.out.println(value);
		    if (strings.containsKey(key))
			System.out.println("Duplicate language key: " + key +
					   " oldvalue: " + strings.get(key) +
					   " newvalue: " + value);
		    else
			strings.put(key, value);
	    }
    }

    private void readFromFile(URL fileURL)
	throws FileNotFoundException, IOException 
    {
	// If reading our strings is not possible using a ResourceBundle,
	// then attempt to read them manually from a file

	BufferedReader in =
	    new BufferedReader(new InputStreamReader(fileURL.openStream()));

	String inputLine;

	// Read the file line by line
	while ((inputLine = in.readLine()) != null)
	    {
		// Break the line up by whitespace
		StringTokenizer tokens = new StringTokenizer(inputLine);

		// Each line of data needs three elements:
		// key = value1 [value2 ... valueN]
		// If we have less than two elements, it might be an empty
		// line, or a comment, or a malformed
		if (tokens.countTokens() < 2)
		    continue;

		String key = tokens.nextToken();

		// If the line starts with #, it is a comment
		if (key.startsWith("#"))
		    continue;

		// First token is the key
		
		// Next should be 'equals'
		if (!tokens.nextToken().equals("="))
		    // Malformed?
		    continue;

		String value = "";
		while (tokens.hasMoreTokens())
		    value += tokens.nextToken(" ") + " ";

		//System.out.println(key + " = " + value);

		// Add it to our list
		if (strings.containsKey(key))
		    System.out.println("Duplicate language key: " + key +
				       " oldvalue: " + strings.get(key) +
				       " newvalue: " + value);
		else
		    strings.put(key, value);
	    }
	in.close();
    }

    public String getTranslator()
    {
	// Returns information about who translated
	return ((String) strings.get("translator"));
    }

    public String get(String key)
    {
	// This function just returns values for 'global' keys (i.e. ones
	// that start with 'global.'

	String fullKey = "global." + key;

	// Find it
	String returnString = (String) strings.get(fullKey);
	
	if (returnString == null)
	    {
		System.out.println("Language key " + fullKey + " not found");
		return ("");
	    }

	return (returnString);
    }

    public String get(Class callerClass, String key)
    {
	// This class returns the string matching the key, from the
	// hash map

	// Each key begins with the name of the class to which it
	// belongs
	String fullKey = callerClass.getName() + "." + key;
  //System.out.println(fullKey );

// This line is generated by nagendra because the value of fullkey is begining with babylon.This gives the error for creating text in chat applet.so remove babylon from fullkey.

        fullKey=fullKey.substring(8);            //remove babylon from fullkey
	
	// Find it
	String returnString = (String) strings.get(fullKey);
	//  System.out.println( " not found " + returnString);
	//String returnString = (String) strings.get("babylonPanel.thickness");
          //System.out.println( " not found " + returnString);
	if (returnString == null)
	    {
		System.out.println("Language key " + fullKey + " not found");
		return ("");
	    }

	return (returnString);
    }
}
