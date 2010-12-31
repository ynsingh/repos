class CourseActivity {

     Date CourseStartDate;
     String CourseID;
     String CourseDescription;
     Integer TotView;
     Integer TotSess;
     Integer TotTime;
     Integer TotViewRes;
     Integer IniThrdCount;
     Integer IniTotPosts;
 
   static mapping = {
   	      table 'course_activity'
	}

   Integer eventCount;
   String userId;
   String eventId;
   String userName;
   String siteId;
   String siteName;
   static transients = [ "eventCount","userId","eventId","userName","siteId","siteName" ]

}
