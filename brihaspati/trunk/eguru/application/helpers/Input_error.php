<?php

class Input_error {

    public $input;
    public $errorMsg;

    public function __construct($input, $error_message) {
        $this->input = $input;
        $this->errorMsg = $error_message;
    }
}
?>
