<?php
  /**
   * Course.php
   * Model Course
   */
class Model_Course extends RedBean_SimpleModel{
	public function update() {
		//since we are already logged in
		//and have checked the institute ownership in controller
		//test everything over here
		$degree = R::findOne('degree','name = ?',array($this->degree));
		if(!$degree)//if no such degree exists
		{
			//This is during development
			//we insert the degree into the table
			$degree = R::dispense('degree');
			$degree->name = $this->degree;
			$d_id = R::store($degree);
		}
		else
			$d_id = $degree->id;
		//We also unset our own degree attribute and replace it with the much better degree id
		$this->degree = $d_id;
		
		//Now do the same things for the discipline
		//Also match some other things
		if(strlen($this->discipline)<10)
			throw new Exception("Discipline length");
		$discipline = R::findOne('discipline','name = ?',array(
			$this->discipline2?$this->discipline2:$this->discipline)	//Use discipline2 if possible
		);
		if(!$discipline){
			$discipline = R::dispense('discipline');
			$discipline->name = $this->discipline2?$this->discipline2:$this->discipline;
			$disc_id = R::store($discipline);
		}
		else
			$disc_id = $discipline->id;
		$this->discipline = $disc_id;
		unset($this->discipline2);//remove the second discipline input
		//Check the number of seats
		if(($this->seats<1||$this->seats>500)||(!ctype_digit($this->seats)))
			throw new Exception("Invalid number of seats");
	}
}
?>
