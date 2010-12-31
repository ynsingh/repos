class PerStudentSummary {

	String userId;
	String studentName;
	int viewCount;
	int sessionCount;
	int timeSpent;
	int resourcesCount;
	
	static mapping = {
	      table 'student_activity_per_student_summary_fact'
	}


	
}
