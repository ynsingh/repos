package org.bss.brihaspatisync.reflector.network.audio;

/**
 * AudioQueue.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur
 */

import java.util.Vector;

/**
 * A simple FIFO queue class which causes the calling thread to wait
 * if the queue is empty and notifies threads that are waiting when it
 * is not empty.
 */

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on dec2011
 */

public class AudioQueue {

	private Vector vector = new Vector();
    	private boolean stopWaiting=false;
    	private boolean waiting=false;
    	private static AudioQueue aq=null;

    	/**
     	 * Controller for the AudioQueue
     	 */
    	public static AudioQueue getController(){
    		if(aq==null)
    			aq=new AudioQueue();
    		return aq;
    	}

    	/**
     	 * Put the object into the queue.
     	 */
    	public synchronized void put(Object object) {
		vector.addElement(object);
		System.out.println("Size of Audio queue is : "+vector.size());
		notify();
    	}

    	/**
     	 * Get the first object out of the queue. Return null if the queue
     	 * is empty.
     	 */
    	public synchronized Object removeElement() {
		Object object = peek();
		if (object != null)
	    		vector.removeElementAt(0);
	    	System.out.println("return object at position 0");
		return object;
    	}
	

    	/**
     	 * Peek to see if something is available.
     	 */
    	public Object peek() {
		if (isEmpty())
		    	return null;
		return vector.elementAt(0);
    	}

    	/**
     	 * Is the queue empty?
     	 */
    	public boolean isEmpty() {
		return vector.isEmpty();
    	}

    	/**
     	 *
     	 */
     	public synchronized void clearQueue(){
     		vector.clear();
     	}

    	/**
     	 * How many elements are there in this queue?
     	 */
    	public int size() {
		return vector.size();
    	}

    	public synchronized void manageQueue(){
    		if(size()>100){
    			for(int i=0;i<size();i++)
    				if((i%2==0)||(i%3==0))
    					vector.remove(i);
    		}
     		vector.clear();
     	}
}

