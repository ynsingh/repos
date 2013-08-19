package org.iitk.brihaspati.modules.utils;

/*
 *  @(#) ActiveUserCourseController.java
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
 *  EVEN IF ADVISE OF THE POSSIBILITY OF SUCH DAMAGE.
 *  
 */

import java.util.Vector;
import java.util.Hashtable;

/**
 * @author <a href="mailto:smita37uiet@gmail.com">Smita Pal</a>
 */
public class ActiveUserCourseController
{
        private static ActiveUserCourseController aucc=null;
        private java.util.Hashtable ht = new java.util.Hashtable();
        private java.util.Hashtable temphashtable = new java.util.Hashtable();

        /**
         * Controller for this class to use as a singleton.
         */
        public static ActiveUserCourseController getController(){
                if (aucc==null){
                        aucc=new ActiveUserCourseController();
                }
                return aucc;
        }
	/**
         * This method is used to assign blank vector according to groupid    
         * and clear vector after using to ActiveUserCourseListThread class .
         */
        protected synchronized Vector getempVector(int groupid)
        {
                if(!(temphashtable.containsKey(groupid))) {
                        temphashtable.put(groupid,new Vector());
                }
                return (Vector)temphashtable.get(groupid);
        }

	/**
         * This method is used to assign blank vector according to inst id    
         * and return a vector(all final user list) 
         */
        private synchronized Vector getOriginalVector(int groupid)
        {
                if(!(ht.containsKey(groupid))) {
                        ht.put(groupid,new Vector());
                }
                return (Vector)ht.get(groupid);
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
         * This method is used to get all user list according to groupid. 
         */
        public synchronized Vector getUserListVector(int groupid,String username)
        {
                Vector return_v=new Vector();
                try {
                                return_v.addAll(return_v.size(),getOriginalVector(groupid));
				boolean flag=false;
                                        for(int i=0;i<return_v.size();i++) {
                                                if((return_v.get(i).toString()).startsWith(username)) {                                                         flag=true;
                                                }
                                        }
                                        if(!flag)
						return_v.add(0,username+" "+"(0"+" hours"+" "+"0"+" min)");				    
                        	return_v = new Vector<String>(new java.util.LinkedHashSet<String>(return_v));
                } catch(Exception e){ ErrorDumpUtil.ErrorLog("Error in Controller----"+e); }
                return return_v;
        }
}

