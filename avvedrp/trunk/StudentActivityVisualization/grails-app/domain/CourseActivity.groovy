class CourseActivity {

/*
     Date CourseStartDate;
     String CourseID;
     String CourseDescription;
     Integer TotView;
     Integer TotSess;
     Integer TotTime;
     Integer TotViewRes;
     Integer IniThrdCount;
     Integer IniTotPosts;
	 */
 
   static mapping = {
   	      table 'course_activity'
	}

   Integer eventCount;
   String userId;
   String eventId;
   String userName;
   
   Integer instId;
   String instName;
   Integer crsId;
   String crsName;
   
   static transients = [ "eventCount","userId","eventId","userName","instId","instName","crsId","crsName" ]

}
