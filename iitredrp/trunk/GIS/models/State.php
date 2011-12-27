<?php
  /**
   * Model State
   */
class Model_State extends RedBean_SimpleModel{
	public function update() {
		echo "<br>";
		print_r($this);
		echo "Updating";
	}
}
?>
