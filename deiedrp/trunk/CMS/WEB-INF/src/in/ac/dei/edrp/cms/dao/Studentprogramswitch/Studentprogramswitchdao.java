package in.ac.dei.edrp.cms.dao.Studentprogramswitch;

import java.util.List;

import in.ac.dei.edrp.cms.domain.studentprogramswitch.Studentprogramswitch;

public interface Studentprogramswitchdao {
	
	//List <Studentprogramswitch> getStudentforSwitch();

	List<Studentprogramswitch> getProgramswitches() ;

	List<Studentprogramswitch> getstudentforswitches(Studentprogramswitch student);

	List<Studentprogramswitch> getsemesterdate(Studentprogramswitch switchdetail);

	List<Studentprogramswitch> switchstudent(Studentprogramswitch semesterdetail);

	

	

}
