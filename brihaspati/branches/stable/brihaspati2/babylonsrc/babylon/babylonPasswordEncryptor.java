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
//  babylonPasswordEncryptor.java
//

import java.security.*;
import babylon.*;


public class babylonPasswordEncryptor
{
    public static String hashAlgorithm = "SHA";
    private MessageDigest passwordEncryptor;
    protected boolean canEncrypt = true;

    public babylonPasswordEncryptor()
    {
	try {
	    passwordEncryptor =
		MessageDigest.getInstance(hashAlgorithm);
	}
	catch (Exception e) {
	    // A number of exceptions can happen here, including the one
	    // in which the Java implementation doesn't implement the
	    // MessageDigest stuff.  In any of these cases, password
	    // encryption will not be available
	    canEncrypt = false;
	}
    }

    public String encryptPassword(String original)
    {
	if (original.equals("") || !canEncrypt)
	    return (original);

	byte[] enc = null;
	try {
	    synchronized (passwordEncryptor)
		{
		    passwordEncryptor.reset();
		    enc = passwordEncryptor.digest(original.getBytes());
		}

	    // All set
	    return (new String(enc, "ISO-8859-1"));
	}
	catch (Exception e) {
	    // Doesn't work - disable encryption
	    canEncrypt = false;
	    return (original);
	}
    }
}
