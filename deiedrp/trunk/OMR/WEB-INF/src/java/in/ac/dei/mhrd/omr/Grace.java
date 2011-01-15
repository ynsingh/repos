package in.ac.dei.mhrd.omr;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;
import in.ac.dei.mhrd.omr.img.Connect;


public class Grace {
	/**
	 * Method graceMarks for calculating grace marks corresponding to test id
	 * @param testId id of the test
	 * @return float
	 */
	public float graceMarks(int testId)
	{
	PreparedStatement ps=null;
	ResultSet rsGraceMarks=null;
	 ArrayList<Integer> wrongQues=new ArrayList<Integer>();
	 int totalSection;
	 int quesSection=0;
	 float marks=0;
	 System.out.println("in grace");
	 try{
	  Connection con = (Connection) Connect.prepareConnection();
	  ps=con.prepareStatement("select WrongQuestionNo from wrongquestion where TestId=? and status='G'");
	  ps.setInt(1, testId);
   	 rsGraceMarks=ps.executeQuery();
   	 		while(rsGraceMarks.next())
   	 			{
   	 			wrongQues.add(rsGraceMarks.getInt("WrongQuestionNo"));
   	 			}
   	 		if(!wrongQues.isEmpty())
   	 		{
   	 				rsGraceMarks=null;
   	 				ps=con.prepareStatement("select Total_section from testheader where TestId=?");
   	 				ps.setInt(1, testId);
   	 				rsGraceMarks=ps.executeQuery();
   	 				rsGraceMarks.next();
   	 				totalSection=rsGraceMarks.getInt("total_section");
   	 				System.out.println("ts "+totalSection);
   	 				rsGraceMarks=null;
   	 				ps=con.prepareStatement("select Section_number,No_of_question,Neg_Marks,Marks_each_question from testsectiondetail " +
   	 										"where TestId=?");
   	 				ps.setInt(1, testId);
   	 				rsGraceMarks=ps.executeQuery();
   	 					while(rsGraceMarks.next())
   	 					{
   	 						for(Integer wrong:wrongQues)
   	 						{
   	 							if(wrong<=(quesSection+rsGraceMarks.getInt("No_of_question")) && wrong>quesSection)
   	 							{
   	   	 						System.out.println("for wrong "+wrong);
   	   	 						marks=marks+rsGraceMarks.getFloat("Marks_each_question");
   	 							}
   	 						}
   	 					quesSection=quesSection+rsGraceMarks.getInt("No_of_question");
   	 					}
   	 					System.out.println("Grace marks "+marks);
   	 					rsGraceMarks.close(); 
   	 					
   	 		}
   	 		}catch (Exception e) {
   	 			System.out.println("Exception in grace "+e.getMessage());
				// TODO: handle exception
			}
   	 	return marks;
	 }
   	 /**
   	  * Method disacardMarks discarding marks as per discarded questions
   	  * @param testId discarding questions as per testId
   	  * @return ArrayList<Float>
   	  */			
   	 		public ArrayList<Float> discardMarks(int testId)
   	 				{
   	 					
   	 					int quesSection=0;
   	 					PreparedStatement ps=null;
   	 					Connection con=null;
   	 					ResultSet rsDiscard=null;
   	 					ArrayList<Integer> discardQues=new ArrayList<Integer>();
   	 					ArrayList<Float> discardMarks=new ArrayList<Float>();
   	 					float marks=0;
   	 					int totalSection=0;
   	 					System.out.println("in discard");
   	 				try{
   	 					con=Connect.prepareConnection();
   	 					ps=con.prepareStatement("select WrongQuestionNo from wrongquestion where TestId=? and status='D'");
   	 					ps.setInt(1, testId);
   	 					rsDiscard=ps.executeQuery();
   	 						while(rsDiscard.next())
   	 						{
   	 							discardQues.add(rsDiscard.getInt("WrongQuestionNo"));
   	 						}
   	 					for(Integer wrong:discardQues)
							{
								System.out.println("ques "+wrong);
							}
   	 						rsDiscard=null;
   	 						if(!discardQues.isEmpty())
   	 						{
   	 						ps=con.prepareStatement("select Total_section from testheader where TestId=?");
   	 						ps.setInt(1, testId);
   	 						rsDiscard=ps.executeQuery();
   	 						rsDiscard.next();
   	 						totalSection=rsDiscard.getInt("Total_section");
   	 						System.out.println("ts"+totalSection);
   	 						rsDiscard=null;
   	 						ps=con.prepareStatement("select Section_number,No_of_question,Neg_Marks,Marks_each_question from testsectiondetail " +
													"where TestId=?");
   	 						ps.setInt(1, testId);
   	 						rsDiscard=ps.executeQuery();
   	 								while(rsDiscard.next())
   	 								{
   	 								System.out.println("no of ques "+rsDiscard.getInt("No_of_question"));
   	 							System.out.println("Meq "+rsDiscard.getFloat("Marks_each_question"));
   	 									for(Integer wrong:discardQues)
   	 									{
   	 									if(wrong<=(quesSection+rsDiscard.getInt("No_of_question")) && wrong>quesSection)
   	    	 							{
   	    	   	 						System.out.println("for wrong "+wrong);
   	    	   	 						marks=marks+rsDiscard.getFloat("Marks_each_question");
   	    	 							}
   	 									}
   	 							discardMarks.add(marks);
   	 							marks=0;
   	 							quesSection=quesSection+rsDiscard.getInt("No_of_question");
   	 								}
   	 							for(Float wrong:discardMarks)
   	 							{
   	 								System.out.println("Marks "+wrong);
   	 							}
   	 						}
   	 				}catch (Exception e) {
		 System.out.println("Exception in graceDiscard"+e.getMessage());
	} 
	 return discardMarks;
	}
}
