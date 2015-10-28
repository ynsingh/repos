
<!DOCTYPE html>
<html>
    <head>
        <title>EGURU | HOME</title>
        <link href="<?php echo base_url(); ?>/static/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="<?php echo base_url(); ?>/static/css/login.css" rel="stylesheet" type="text/css">
        <link href="<?php echo base_url(); ?>/static/css/jquery-ui.min.css" rel="stylesheet" type="text/css">
        <link href="<?php echo base_url(); ?>/static/css/autocomplete.css" rel="stylesheet" type="text/css">
        <script src="<?php echo base_url(); ?>/static/js/jquery-1.10.2.js"></script>
        <!--<script src="/static/js/bootstrap.responsive.min.js" ></script>-->
        <script src="<?php echo base_url(); ?>/static/js/bootstrap.min.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/jquery-1.10.2.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/jquery.validate.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/jquery-ui.min.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/jquery.form.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/admin/sub_hod_dashboard.js"></script>
    </head>
    <style>
        #edit{
          margin: 10px 0 20px;  
  	}
        </style>
    <body style="background-color:#82B4D4;color:black;">
        <?php
        include'header_login.php';
        if (!isset($_SESSION['email']))
            header("Location:/eguru/");
        ?>
        <div class="container-fluid">

            <table>
                <tr style="cursor: pointer">
                    <td style="padding-right: 10px"><text style="text-decoration: underline" class="material_change" value="descriptive">Descriptive</text></td>
                    <td style="padding-right: 10px"><text class="material_change" value="ppt" style="text-decoration: underline">PPT</td>
                    <td style="padding-right: 10px"><text class="material_change" value= "video"style="text-decoration: underline">Video</td>
                </tr>
            </table>
        </div>
        <h3 style="margin-left:20px">Upload the material below </h3> 
    <center>
        <form id="material_submit" method="POST" enctype="multipart/form-data" >     
            <table style="text-align:right">
                <tr>
                    <td class="control-group">
                        Type-<select name="type" id="type">
                            <option value=""></option>
                            <option value="descriptive">Descriptive</option>
                            <option value="ppt">PPT</option>
                            <option value="video">Video</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="control-group">
                        Hardness-<select name="hardness" id="hardness">
                            <option value=""></option>
                            <option value="1">Level 1</option>
                            <option value="2">Level 2</option>
                            <option value="3">Level 3</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="control-group">
                        Chapter(Module)/Unit/Sub-unit- <input type="text" name="topic" id= "topic" />
                    </td>
		    <td>
			 <div id="help" class="modal hide fade in span6" style="display: none;topic:20%;left:46% ">
                                            <div class="modal-header" style="height:35px">
                                                <a class="close" data-dismiss="modal">x</a>
                                                <h3 style="float:left">HELP</h3>
                                            </div>											
                                            <div style="text-align:left;padding:22px;"><p>
						Uploaded the material according to following instructions:<br>
						A. When a chapter is going to be inserted follow this format-<br>
							<h4>&ltChapter number&gt. 0 &ltChapter name&gt</h4>
						Example:<h4> 1                  . 0 Memory Management</h4>
						Note: Chapter and Module both are same.<br>
						B. When unit of a particular chapter is going to be inserted follow this format-
							 <h4>&lt Chapter number&gt.&lt Unit number&gt&ltUnit name&gt</h4>
						Example:<h4>1.2 Virtual Memory</h4>
						C. When a sub-unit is going to be inserted under a particular unit of chapter follow this format-
							 <h4>&lt Chapter number&gt.&lt Unit number&gt.&ltSub unit number&gt &lt Sub unit name&gt</h4>
						Example:<h4>1.2.3 Demand Paging</h4>					
						
						</p>
                                            </div>				

                                        </div>
                                        <p><a data-toggle="modal" href="#help"  class="btn btn-primary adjust right"><i class="icon-user icon-white"></i>HELP</a></p>

			</td>
                </tr>
                <tr>
                    <td class="control-group">
                        Upload <input type="file" name="userfile" id="file_material">
                    </td>
                    <td style=" padding-left: 10px">  Or </td>
                    <td style=" padding-left: 10px" class="control-group">
                        Link <input type="text" name="link" id="link">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" class="btn btn-primary" name="submit">
                    </td>
                </tr>
            </table>
        </form>
    </center>
    <?php $count = 1 ?>
    <div class="accordion">
        <?php foreach ($topics_list->result_array() as $row) {
            ?>    
            <div class="accordion-group <?=$row['type']?> type">
                <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapse<?= $count ?>">
                        <h2><?php echo $row['name'];
            ?></h2>
                    </a>
                </div>
                <div id="collapse<?= $count ?>" class="accordion-body collapse ">
                    <div class="accordion-inner">
                        <b><h3> Material Link-:<?php
                                if ($row['link'] === "nothing") {
                                    echo "<a href='/uploads/" . $data['subject_id'] . "/" . $row['file_name'] . "' target='_blank'>Link</a></h3></b>";
                                } else {
                                    echo "<a style='color:blue' href='" . $row['link'] . "' target='_blank'>Link</a></h3></b>";
                                }
                                echo"<br> <b><h3>Hardness-:";
                                echo $row['hardness'] . "</h3></b>";
                                echo "<br><b><h3>Type-:" . $row['type'] . "</h3></b>";
                                echo "<button type='button'  id='" . $row['id'] . "' class='btn btn-primary delete'> Delete</button>";
                                ?> <form id="edit" method="post" action='/admin/material_edit/'><input type=hidden name ="id" value= "<?=$row['id']?>" ><input type=submit class='btn btn-primary' value="Edit"></form>
                                
                                </div>
                                </div>
                                </div>            
    <?php $count++;
} ?>
                            </div>
                            <?php include 'footer.php'; ?>                        
                            </body> 
                            <script>
                                var data_autocomplete = <?= json_encode($data_autocomplete); ?>
                            </script>            
