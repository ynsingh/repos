package org.iitk.brihaspati.modules.screens.call.tunnel;
/*
 * @(#)VetStringAnalyzer.java
 *
 *  Copyright (c) 2011-12 ETRG,IIT Kanpur.
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

/**@author: <a href="mailto:seema_020504@yahoo.com">Seemapal</a>
*@author: <a href="mailto:kshuklak@rediffmail.com">Kishore Kumar shukla</a>
*/


import java.util.Vector;


public class VetStringAnalyzer{
	
	public static void main(String[] args) {
		//String s = "(101-11)(11-1)(1)-00";
		String s = args[0];
		Vector list = new Vector();
		if ( doProcess(s, list) == 1 ) {
			System.out.println("TRUE");
			for ( int i = 0; i < list.size(); i++ ) {
				System.out.println( (String) list.elementAt(i) );	
			}
		} else {
			System.out.println("FALSE");
		}
	}	
	
	public static int doProcess(String s, Vector list) {
		int group_num = 0;
		StringBuffer temp = new StringBuffer("");
		StringBuffer r = new StringBuffer();
		boolean in = false;
		for ( int i = 0; i < s.length(); i++ ) {
			char c = s.charAt(i);
			if ( c == '(' ) {
				in = true;
				temp = new StringBuffer("");
				continue;
			}
			if ( c == ')' ) {
				//process    
				group_num++;
				if ( temp.toString().indexOf("-") > 0 ) {
					String s1 = temp.substring(0, temp.toString().indexOf("-"));
					String s2 = temp.substring(temp.toString().indexOf("-") + 1);
					int q = getQualified(s1, s2);
					r.append(Integer.toString(q));
					if ( q == 1 ) {
						//System.out.println("group_num = " + group_num);
						list.addElement(Integer.toString(group_num));
					}
				} else {
					r.append(temp);
					if ( Integer.parseInt(temp.toString().trim()) == 1 ) {
						//System.out.println("group_num = " + group_num);
						list.addElement(Integer.toString(group_num));
					}
				}
				in = false;
				continue;
			}			
			if ( in ) {
				temp.append(c);
				continue;
			}
			r.append(c);
		}
		//process
		
		int result = 0;
		if ( r.toString().indexOf("-") > 0 ) {
			String s1 = r.substring(0, r.toString().indexOf("-"));
			String s2 = r.substring(r.toString().indexOf("-") + 1);
			result = getQualified(s1, s2);
		} else {
			result = Integer.parseInt(r.toString().trim());
		}
		return result;
	}
	
	static int getQualified(String s1, String s2) {
		
		int count = s1.length();
		
		int[] data = new int[count], condition = new int[count - 1];
		for ( int i=0; i < s1.length(); i++ ) {
			data[i] = Integer.parseInt(new Character(s1.charAt(i)).toString());
			//data[i] = s1.charAt(i);
		}
		for ( int i=0; i < s2.length(); i++ ) {
			condition[i] = Integer.parseInt(new Character(s2.charAt(i)).toString());
			//condition[i] = s2.charAt(i);
		}
		return getQualified(data, condition);		
	}
	
	static int getQualified(int[] data, int[] condition) {
		int x = data[0];
		for ( int i = 1; i < data.length; i++ ) {
			if ( condition[i-1] == 1 ) {
				x = x & data[i];
			} else {
				x = x | data[i];	
			}
		}
		return x;
	}		
	
}
