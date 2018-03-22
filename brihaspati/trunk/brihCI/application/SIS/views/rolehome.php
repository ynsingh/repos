
<!--@filename rolehome.php
@author Manorama Pal(palseema30@gmail.com)
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome  </title>
        <?php $this->load->view('template/header'); ?>
       
            
        <!--<link rel="stylesheet" type="text/css" href="<//?php echo base_url(); ?>assets/css/bootstrap.min.css">
        <script type="text/javascript" src="<//?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<//?php echo base_url();?>assets/js/bootstrap.min.js" ></script>-->
            
        <style>
            /* The Modal (background) */
            /* The Modal (background) */
            .modal fade {
                display: none; /* Hidden by default */
                position: fixed; /* Stay in place */
                z-index: 1; /* Sit on top */
                padding-top: 100px; /* Location of the box */
                left: 0;
                top: 0;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                overflow: auto; /* Enable scroll if needed */
                background-color: rgb(0,0,0); /* Fallback color */
                background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
            }
    
            /* Modal Content */
            .modal-content {
                background-color: #fefefe;
                margin: auto;
                padding: 20px;
                border: 1px solid #888;
                width: 80%;
            }

            /* The Close Button */
            .close {
                color: #aaaaaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: #000;
                text-decoration: none;
                cursor: pointer;
            }
        </style>
    
        <script type="text/javascript">
            /* $(window).on('load',function(){
                $('#myModal').modal('show');
            });*/
            $(document).ready(function() {
                $('#my-modal').modal('show');
            });
        </script>
    </head>
    
    <body onLoad="$('#my-modal').modal('show');">
        
        <div id="my-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close">
                        <?php echo anchor('home/logout', '&times', array('title' => 'Logout','class' => 'top_parent'));?>
                    </span>
                </div>
                <div class="modal-body">
                    <table align="center">  
                
			<?php  
			foreach($this->roles as $row){ 
                             
                            if($row->roleid == 1){ 
                                echo "<tr><td><p>"; 
                                echo anchor("home", "Login As Administrator" ,array('title' => ' Login As Admin ' , 'class' => 'top_parent'));
                                echo "</p></td></tr>";
			    }

                            if($row->roleid == 2){
                                echo "<tr><td><p>"; 
                                echo anchor("facultyhome", "Login As Faculty" ,array('title' => ' Login As Faculty ' , 'class' => 'top_parent'));
                                echo "</p></td></tr>";
                            }
                
                            if($row->roleid == 3){
                                echo "<tr><td><p>";
                                echo anchor("student/studenthome", "Login As Student" ,array('title' => ' Login As Student ' , 'class' => 'top_parent'));
                                echo"</p></td></tr>";
                            }
              
                            if($row->roleid == 4){
                                echo "<tr><td><p>";
                                echo anchor("staffhome", "Login As Staff" ,array('title' => ' Login As Staff ' , 'class' => 'top_parent'));
                                echo"</p></td></tr>";
                            }
              
                            if($row->roleid == 5){
                                echo "<tr><td><p>";
                                echo anchor("hodhome", "Login As HoD" ,array('title' => ' Login As HoD ' , 'class' => 'top_parent'));
                                echo"</p></td></tr>";
                            }
              
                            if($row->roleid == 6){
                                echo "<tr><td><p>";
                                echo anchor("coehome", "Login As CoE" ,array('title' => ' Login As CoE ' , 'class' => 'top_parent'));
                                echo"</p></td></tr>";
                            }
              
                            if($row->roleid == 7){
                                echo "<tr><td><p>";
                                echo anchor("accontoffhome", "Login As Account Officer" ,array('title' => ' Login As Account Officer ' , 'class' => 'top_parent'));
                                echo"</p></td></tr>";
                            }
                            if($row->roleid == 10){
                                echo "<tr><td><p>";
                                echo anchor("uohome", "Login As University Officer" ,array('title' => ' Login As University Officer ' , 'class' => 'top_parent'));
                                echo"</p></td></tr>";
                            }
                            
		}
		?>
                    </table>
                </div>
           
            </div> 
        </div>
    <div>
	<?php $this->load->view('template/footer'); ?>
	
    </div>
</body>   
</html>    


