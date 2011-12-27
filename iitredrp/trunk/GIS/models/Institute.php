<?php
  /**
   * Institute.php
   * Model institute
   */
class Model_Institute extends RedBean_SimpleModel{
	public function update() {
		if(strlen($this->name)<8)
			throw new Exception('Minimum Length of Institute name is 10 characters');
		if(!is_numeric($this->latitude))
			throw new Exception('Invalid Latitude');
		if(!is_numeric($this->longitude))
			throw new Exception('Invalid Longitude');
		if(strlen($this->address)<15)
			throw new Exception('Minimum Length of Address is 15 characters');
		if(strlen($this->name)<8)
			throw new Exception('Minimum Length of Phone Number is 8 characters');		
	}
	/**
	 * Returns the total number of seats
	 * in the institute
	 */
	public function getSeats(){
		$seats = 0;
		$courses = R::find('course','institute_id=?',array($this->id));
		foreach($courses as $c)
			$seats += $c->seats;
		return $seats;
	}
	/**
	 * Counts the number of courses that this institute offers
	 */
	 public function numCourses(){
		 $courses = R::find('course','institute_id=?',array($this->id));
		 return count($courses);
	 }
}
?>
