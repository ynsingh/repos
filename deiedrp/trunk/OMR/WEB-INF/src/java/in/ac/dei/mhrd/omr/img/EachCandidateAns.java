package in.ac.dei.mhrd.omr.img;

import java.util.*;


/**
 * @Author Anshul Agarwal
 * This class display the answers marked by the student
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
    

    //This function displays the ques and answers marked by the student	
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
