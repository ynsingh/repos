package org.iitk.brihaspati.modules.screens.call.Timetable;

public class Student {
	//int id;
	String enrolment;
	String name;
	
	/*public int getId() {
		return id;
	}*/
	
	public Student(String enrolment,String name) {
		this.enrolment = enrolment;
		this.name = name;
	}

	public String getEnrolment() {
		return enrolment;
	}
}
