        <?php include("admin_header.php");?>
<html>
<head>
<title>File Upload with PHP</title>
<link href="stylenew.css" rel="stylesheet" type="text/css" />
</head>
<body>
<?php

        $UploadDirectory        = 'uploads/'; //Upload Directory, ends with slash & make sure folder exist
        //$SuccessRedirect      = 'success.html'; //Redirect to a URL after success

        if (!@file_exists($UploadDirectory)) {
                //destination folder does not exist
                die("Make sure Upload directory exist!");
        }
        if(isset($_FILES['file_array'])){
                $name_array = $_FILES['file_array']['name'];
                $tmp_name_array = $_FILES['file_array']['tmp_name'];
                $type_array = $_FILES['file_array']['type'];
                $size_array = $_FILES['file_array']['size'];
                $error_array = $_FILES['file_array']['error'];

                $file_title = array();
                $file_title[0] = "erpmission";
                $file_title[1] = "brihaspatisync";
                $file_title[2] = "brihaspati";
                $file_title[3] = "payroll";
                $file_title[4] = "bgas";
                $file_title[5] = "pico";
                for($i = 0; $i < count($tmp_name_array); $i++){
                        $file_ext= substr($name_array[$i], strrpos($name_array[$i], '.'));
                        if(move_uploaded_file($tmp_name_array[$i], $UploadDirectory.$file_title[$i].$file_ext)){
                             //   echo $name_array[$i]." upload is complete<br>";
				$temp =1;
                        } 

//else {
  //                              echo "move_uploaded_file function failed for ".$name_array[$i]."<br>";
    //                    }
                }
		if($temp == 1)
		?>
		<div id = "message"> 
		<?php
		echo "File upload is complete";
		?>
		</div>
		<?php
        } ?>
<div id="theForm">
<form action="" enctype="multipart/form-data" method="post" >
 <table>
 <td>  
<tr>
 <label>ERP MISSION
    </label>
    <label>File
    <span class="small">Choose a File</span>
    </label>
<input type="file" name="file_array[]"/>
</td>
</tr>
<td>
<tr>
    <label>BRIHASPATISync
     </label>
    <label>File
    <span class="small">Choose a File</span>
    </label>
<input type="file" name="file_array[]"/>
</td>
</tr>
 <td>
<tr>
    <label>BRIHASPATI
     </label>
    <label>File
    <span class="small">Choose a File</span>
    </label>
<input type="file" name="file_array[]"/>
</td>
</tr>
 <td>
<tr>
    <label>PAYROLL
     </label>
    <label>File
    <span class="small">Choose a File</span>
    </label>
<input type="file" name="file_array[]"/>
</td>
<td>
<tr>
    <label>BGAS
     </label>
    <label>File
    <span class="small">Choose a File</span>
    </label>
<input type="file" name="file_array[]"/>
</td>
</tr>
<td>
<tr>
    <label>PICO
     </label>
    <label>File
    <span class="small">Choose a File</span>
    </label>
<input type="file" name="file_array[]"/>
</td>
</tr>
<button type="submit" class="red-button" id="sendmail">Upload</button>
<button type="reset" class="red-button" value="Reset">Reset</button>
</table>
	</form>
</div>
</body>
</html>
<?php include("footer.php");
?>


