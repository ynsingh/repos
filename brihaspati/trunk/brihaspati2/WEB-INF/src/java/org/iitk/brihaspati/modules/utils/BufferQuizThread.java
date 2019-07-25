package org.iitk.brihaspati.modules.utils;
import java.util.LinkedList; 
import java.util.Queue;  
import java.util.*; 
import java.io.*; 
import java.util.ArrayList;  
import java.lang.Thread;
public class BufferQuizThread extends Thread{  
     public void run(String quizid, String uid, String tscore, String utime, String seq, String eval){  
        
        try{
	
            // Displaying the thread that is running 
            System.out.println ("Thread " + 
                  Thread.currentThread().getId() + 
                  " is running"); 
storage(quizid,uid,tscore,utime,seq,eval);
getMap(quizid,uid,tscore,utime,seq,eval);
  Thread.sleep(10000);
        } 
       
	catch(InterruptedException e){System.out.println(e);}  
         
       
     }  
public static Map storage(String quizid, String uid, String tscore, String utime, String seq, String eval){
//Queue q = new LinkedList(); 
//ArrayList arrlist = new ArrayList(5); 
Map<String, String> score = new LinkedHashMap<>();
        score.put("QuizID",quizid);
        score.put("UserID",uid);
        score.put("TotalScore",tscore);
        score.put("UsedTime",utime);
	score.put("seq",seq);
	score.put("evaluate",eval);
//Set<Map.Entry<String, String>> employeeSalaryEntries = employeeSalary.entrySet();
//Object[] objectArray = score.entrySet().toArray();
	//System.out.println("array elements "+Arrays.toString(objectArray));
//Arrays.toString(objectArray);
        //Iterator scoreIterator = score.entrySet().iterator();
        //while (scoreIterator.hasNext()) {
          //  Map.Entry<String, String> entry = (Map.Entry)scoreIterator.next();
//arrlist.add(entry);}  
//q.add(score);//(Arrays.toString(objectArray)); 
System.out.println("Linked Map"+score);
 
    // Display contents of the queue. 
    //System.out.println("Elements of queue-"+score.get("QuizID")); 
  return score;
}
public void getMap(String quizid, String uid, String tscore, String utime, String seq, String eval)
{   Map m=storage(quizid,uid,tscore,utime,seq,eval);
//Object[] Array;
System.out.println("Value for key is: "+m.get("QuizID"));
//HashMap<String, String> element = new HashMap<>();
//	Iterator iterator = q.iterator();
//while(iterator.hasNext()){
//Map.Entry<String, String> e =(Map.Entry)iterator.next();
 //element.put(q.element.getKey(),q.element.getValue()) ;    
//System.out.println("Get Elements of queue-"+element); 
}

//public void queueToxml()

   //  public static void main(String args[]){
	
     // BufferQuizThread t1=new BufferQuizThread();  
     
    //  t1.start();  

       
       
    }  
