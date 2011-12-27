<?php
  /**
   * User.php
   * Model User
   */
class Model_User extends RedBean_SimpleModel{
	var $userid;
	public function __toString(){
		return $this->userid;
	}
}
?>
