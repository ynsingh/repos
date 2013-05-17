package org.iitk.brihaspati.modules.utils;

/*
 *  @(#) ActiveUserListController.java
 *  Copyright (c) 2013, ETRG,IIT Kanpur 
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
 */

import java.util.Vector;
import java.util.Hashtable;

/**
 * @author <a href="mailto:smita37uiet@gmail.com">Smita Pal</a>
 */

public class ActiveUserListController
{
	private static ActiveUserListController aulc=null;
	private java.util.Hashtable ht = new java.util.Hashtable();
	private java.util.Hashtable temphashtable = new java.util.Hashtable();

	/**
         * Controller for this class to use as a singleton.
         */		
	public static ActiveUserListController getController(){
                if (aulc==null){
                        aulc=new ActiveUserListController();
                }
                return aulc;
        }

	/**
	 * This method is used to assign blank vector according to inst id    
	 * and clear vector after using to ActiveUserListThread class .
	 */
        protected synchronized Vector getempVector(String insid)
        {
                if(!(temphashtable.containsKey(insid.trim()))) {
                        temphashtable.put(insid,new Vector());
                }
                return (Vector)temphashtable.get(insid);
        }
		
	/**
         * This method is used to assign blank vector according to inst id    
	 * and return a vector(all final user list) 
         */	
	private synchronized Vector getOriginalVector(String insid)
        {
                if(!(ht.containsKey(insid.trim()))) {
                        ht.put(insid,new Vector());
                }
                return (Vector)ht.get(insid);
        }

	/**
         * This method is used to return hash table .
         **/
        protected synchronized java.util.Hashtable getTempHashtable(){
                return temphashtable;
        }

	/**
	 * This method is used to return hash table .
	 **/	
	protected synchronized java.util.Hashtable getHashtable(){
                return ht;
        }
	
	/**
	 * This method is used to get all user list according to inst id 
	 */
	public synchronized Vector getUserListVector(Vector insid,int mode)
        {
		Vector return_v=new Vector();
		try {
			for(int i=0;i<insid.size();i++) {
				Object e=insid.get(i);
				return_v.addAll(return_v.size(),getOriginalVector(e.toString().trim()));
				if(mode==0) {
					if(return_v.size()>5) {
						return_v.subList(5,return_v.size()).clear();
						break;
					}
				}
			}
			return_v = new Vector<String>(new java.util.LinkedHashSet<String>(return_v));
		} catch(Exception e){ ErrorDumpUtil.ErrorLog("Error in Controller----"+e); }
		return return_v;
        }
}
