package org.iitk.brihaspati.modules.utils;

/*
 * @(#)ManifestParser.java
 *
 *  Copyright (c) 2009-2010 ETRG,IIT Kanpur.
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

import java.io.FileReader;
import java.util.Hashtable;
import java.util.Vector;
import nanoxml.XMLElement;

/**This class Read Manifest file with attributes and values
* @author: <a href="mailto:seema_020504@yahoo.com">seema pal</a>
* @author: <a href="mailto:kshuklak@rediffmail.com">kishore kumar shukla</a>
* @modify 06-07-2010
* @author <a href="mailto:palseema30@gmail.com">Seema Pal</a>
*/


public class ManifestParser {
	
	
	public static Vector parse(String filename) throws Exception {
		Vector result = new Vector();
		Vector runtimes = new Vector();
		FileReader reader = null;
		//Map for identfier and identifieref
		Hashtable idenHash = new Hashtable();		
		try {
			XMLElement manifest = new XMLElement();
			reader = new FileReader(filename);
			manifest.parseFromReader(reader);
			XMLElement metadata = null;
			XMLElement organizations = null;
			XMLElement resources = null;
			
			Vector idseq = new Vector();
			Vector manifest_child = manifest.getChildren();
			for ( int i = 0; i < manifest_child.size(); i++ ) {
				XMLElement child = (XMLElement) manifest_child.elementAt(i);	
				if (child.getName().equals("metadata")) metadata = child ;
				else if (child.getName().equals("organizations")) organizations = child ;
				else if (child.getName().equals("resources")) resources = child ;
			}
			//enumerate elements from organizations
			Hashtable itemHash = new Hashtable();

			//this to store runtimes metadata set in imsmanifest
			Hashtable runtimeHash = new Hashtable();
			XMLElement organization = null;
			Vector organizations_child = organizations.getChildren();
			for ( int i = 0; i < organizations_child.size(); i++ ) {
				organization = (XMLElement) organizations_child.elementAt(i);	
			//}
				Vector organization_child = organization.getChildren();
				for ( int j = 0; j < organization_child.size(); j++ ) {
					XMLElement e = (XMLElement) organization_child.elementAt(j);
					String element_name = e.getName();
					if ( element_name.equals("item") ) {
						int itemLevel = 0;
						processItem(e, idseq, itemHash, runtimes, itemLevel, idenHash);
					}
				}
			}
			//enumerate elements from resources
			XMLElement resource = null;
			if ( resources != null ) {
				Vector resources_child = resources.getChildren();
				for ( int i = 0; i < resources_child.size(); i++ ) {
					XMLElement e = (XMLElement) resources_child.elementAt(i);
					String idref = (String) e.getAttribute("identifier");
					String start_page = (String) e.getAttribute("href");
					Vector v = e.getChildren();
					String pages = "";
					for ( int k = 0; k < v.size(); k++ ) {
						XMLElement t = (XMLElement) v.elementAt(k);
						if ( t.getName().equals("file") ) {
							pages += "|" + (String) t.getAttribute("href");	
						}
					}
					Item item = (Item) itemHash.get(idref);
					if ( item != null ) {
				
						item.start_page = start_page;
						item.pages = pages;
					}
				}
			}
			if ( idseq.size() > 0 ) {
				for ( int i = 0; i < idseq.size(); i++ ) {
					Item item = (Item) idseq.elementAt(i);
					if ( item.start_page == null ) item.start_page = "undefined";
					if ( item.pages == null ) item.pages = "|undefined";
					result.add(item.level + "|" + item.idref + "|" + item.start_page + "|" + item.title + item.pages);
				}
			} else {
				throw new Exception("Can't require items in manifest file!!");	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if ( reader != null ) reader.close();
		}
		
		Vector container = new Vector();
		container.add(result);
		container.add(runtimes);
		container.add(idenHash);
		
		return container;
	}
	
	static void processItem(XMLElement e, Vector idseq, Hashtable itemHash, Vector runtimes, int itemLevel, Hashtable idenHash) {
		String content = "";
		String idref = e.getAttribute("identifierref") != null ? (String) e.getAttribute("identifierref") : "null";
		String iden = e.getAttribute("identifier") != null ? (String) e.getAttribute("identifier") : "null";
		idenHash.put(iden, idref);	
		Vector childs = e.getChildren();
		for ( int i = 0; i < childs.size(); i++ ) {
			XMLElement elm = (XMLElement) childs.elementAt(i);
			String s = elm.getName();
			if ( s.equals("title") ) content = elm.getContent();
			else if ( s.indexOf("adlcp:") == 0 ) {
				int ind = s.indexOf(":");
				String runt = idref + "|" + s.substring(ind+1) + "=" + elm.getContent();
				runtimes.add(runt);
			}
		}

		Item item = new Item();
		item.level = Integer.toString(itemLevel);
		item.idref = idref;
		item.title = content;
		itemHash.put(idref, item);
		idseq.add(item);  //to store the correct sequence			
		
		//iterate thru next items in iterative manner
		Vector v = e.getChildren();
		itemLevel++;
		for ( int k = 0; k < v.size(); k++ ) {
			XMLElement itm = (XMLElement) v.elementAt(k);
			if ( itm.getName().equals("item") ) {
				processItem(itm, idseq, itemHash, runtimes, itemLevel, idenHash);
			}
		}					
	}		
	
	static class Item {
		String level;
		String idref;
		String iden;
		String title;
		String start_page;
		String pages;	
	}
	
	public static void main(String args[]) throws Exception {
		String dir = args[0];
		String filename = dir + "\\imsmanifest.xml";
		System.out.println(filename);
		Vector container = parse(filename);
		Vector v = (Vector) container.elementAt(0);
		Vector r = (Vector) container.elementAt(1);
		for ( int i = 0; i < v.size(); i++ ) {
			System.out.println((String) v.elementAt(i));
		}
		System.out.println("=====");
		for ( int i = 0; i < r.size(); i++ ) {
			System.out.println((String) r.elementAt(i));
		}
	}	
}
