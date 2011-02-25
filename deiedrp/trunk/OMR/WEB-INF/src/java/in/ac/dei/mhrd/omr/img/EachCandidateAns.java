/*
 * @(#) EachCandidateAns.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */

package in.ac.dei.mhrd.omr.img;

import java.util.*;


/**
 * This class holds the answers marked by the candidate
 * Creation date:09-28-2010
 * @Author Anshul Agarwal
 * @version 1.0
 */
public class EachCandidateAns {
    public byte[] response;


    /*
     * initialize Response to 0 initially
     */
    public EachCandidateAns(int no_of_ques) {
    	response = new byte[no_of_ques+1];

        for (int i = 0; i < response.length; i++) {
            response[i] = 0;
        }
    }
    private EachCandidateAns(){
    }
    

    /**
     * This method displays the ques and answers marked by the student	
     */
    public void displayAns() {
       int total_attempt = 0;
             System.out.println();
       /* for (int i = 1; i < response.length; i++) {
            if (response[i] != 0) {
                System.out.print("Q : " + i + " Ans : " + this.response[i]);
                                total_attempt++;
                                System.out.println("total attempt:" + total_attempt);
            }
        }*/

       
    }
}
