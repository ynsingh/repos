package org.bss.brihaspatisync.reflector.buffer_mgt;

/**
 * MyComparator.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG,IIT Kanpur.
 */


import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Comparator;
import java.util.Collections;
	

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> 
 */


class MyComparator implements Comparator{
	public int compare(Object obj1, Object obj2){
        	int result=0;
                Map.Entry e1 = (Map.Entry)obj1 ;
                Map.Entry e2 = (Map.Entry)obj2 ;
                Integer value1 = (Integer)e1.getValue();
                Integer value2 = (Integer)e2.getValue();
		if(value1.compareTo(value2)==0){
                	String word1=(String)e1.getKey();
                        String word2=(String)e2.getKey();
                        result=word1.compareToIgnoreCase(word2);
		} else{
                	result=value2.compareTo( value1 );
              	}
                return result;
	}
}

