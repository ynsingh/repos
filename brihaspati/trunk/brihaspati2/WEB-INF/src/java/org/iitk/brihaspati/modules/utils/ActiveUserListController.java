package org.iitk.brihaspati.modules.utils;

/*
 *  @(#) ActiveUserListController.java
 *  Copyright (c) 2010, 2012 ETRG,IIT Kanpur 
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

public class ActiveUserListController
{
	private Vector return_v=new Vector();
	private static ActiveUserListController aulc=null;
	private java.util.Hashtable ht = new java.util.Hashtable();
	private java.util.Hashtable temp = new java.util.Hashtable();
		
	public static ActiveUserListController getController(){
                if (aulc==null){
                        aulc=new ActiveUserListController();
                }
                return aulc;
        }
	/**
	 *
	 */
	protected synchronized java.util.Hashtable gettemp_Hashtable(){
                return temp;
        }	

        public synchronized Vector getempVector(String insid)
        {
                if(!(temp.containsKey(insid))) {
                        temp.put(insid,new Vector());
                }
                return (Vector)temp.get(insid);
        }
		
	protected synchronized java.util.Hashtable getHashtable(){
                return ht;
        }
		
	public synchronized Vector getUserListVector(Vector insid,int mode)
        {
		return_v.clear();
		try {
			for(int i=0;i<insid.size();i++) {
				Object e=insid.get(i);
				if(ht.containsKey(e.toString().trim())){
					Vector v=(Vector)ht.get(e.toString().trim());
					for(int j=0;j<v.size();j++) {			
						String u=v.get(j).toString().trim();
						if(!return_v.contains(u)){
							if((mode==0)){
								return_v.add(u);
								if(return_v.size()==5)
									break;
							}
							else if(mode==1){
								return_v.add(u);
							}
						}
					} v.clear();
				}
				if( (return_v.size()==5) && (mode==0))
				break;
			}
		}catch(Exception e){ ErrorDumpUtil.ErrorLog(" Exception in ActiveUserListController class ");}
		return return_v;
        }
		
}
