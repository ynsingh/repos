<?
	$send2="ynsingh69@gmail.com";
	$subject = "Bill Uploaded";
        $message = "Dear Sir, Your Telephone bill has been uploaded. I request to you, please check your telephone bill. In case of any difficiency, please let me know. Thank you for using this system. For any query, please contact  <br> Telephone Incharge <br>sanchar@iitk.ac.in";

        $header="From:sanchar@iitk.ac.in";
        if(mail($send2, $subject, $message, $header))
		{
		echo"hello , upload bill mail has sent successfull..!";
		}
	else
		{
		echo"hello , upload bill mail could not sent sucessfull..!";
		}
?>

