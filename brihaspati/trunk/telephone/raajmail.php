<?
	$send2="ynsingh69@gmail.com";
	$subject = "Registration Approval";
        $message = "Dear Sir,Your Telephone billing registartion has been approved. Now you are authorised to see your telephone bill. Thank you for using this system. For any query, please contact  <br> Telephone Incharge <br>sanchar@iitk.ac.in";

        $header="From:sanchar@iitk.ac.in";
        if(mail($send2, $subject, $message, $header))
		{
		echo"hello , mail has sent successfull..!";
		}
	else
		{
		echo"hello , mail could not sent sucessfull..!";
		}
?>

