package org.bss.brihaspatisync.reflector.network.udp;
/**
 * @(#)UDP.java
 *
 *
 * @author 
 * @version 1.00 2009/2/2
 */
import java.util.*;

public class UDP extends ListResourceBundle{
	protected Object [][] getContents(){
		Object resources [][]=new Object [3][2];
		resources [0][0]="str0_port";
		resources [0][1]="9090";
		
		resources [1][0]="str1_port";
		resources [1][1]="9999";
		
		resources [2][0]="str_ip";
		resources [2][1]="172.28.44.86";
		
		return resources;
	}

      	
    
    
    
}
