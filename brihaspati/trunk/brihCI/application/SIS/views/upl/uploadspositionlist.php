<!DOCTYPE html>
<!-- @name uploadspositionlist.php 
  @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
  @author Manorama Pal (palseema30@gmail.com) 
  @author Om Prakash (omprakashkgp@gmail.com)  upload csv file for staff position registration in SIS  
 -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Upload Staff Position List</title>
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $("#btnUpload").on('click',function(){
                    var allowedFiles = [".csv", "text/csv"];
                    var fileUpload = $("#fileUpload");
                    var lblError = $("#lblError");
                    var regex = new RegExp("([a-zA-Z0-9\s_\\.\-:])+(" + allowedFiles.join('|') + ")$");
                    if (!regex.test(fileUpload.val().toLowerCase())) {
                        lblError.html("Please upload files having extensions: <b>" + allowedFiles.join(', ') + "</b> only.");
                        return false;
                    }
                    lblError.html('');
                    return true;
                });
            });
        </script>  
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
        <?php $this->load->view('template/menu'); ?>     
        <table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
                  
        <div>
        <table width="100%;">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\" style=\"font-size:16px\">";
                    echo "<b>Upload Staff Position List</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\" style=\"font-size:16px\">";
                    $help_uri = site_url()."/help/helpdoc#UploadStaffList";
                    echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";
                 
                ;?>
            </tr>
        </table>
        <div>  
            <?php
                // display user message area
                /*if(isset($_SESSION)) {
                    echo $this->session->flashdata('flash_data');
                }*/
                if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
                    echo "<div  class=\"isa_success\">";
                    echo $_SESSION['success'];
                    echo "</div>";
                }
                if((isset($_SESSION['error'])) && (($_SESSION['error'])!='')){
                    echo "<div  class=\"isa_error\">";
                    echo $_SESSION['error'];
                    echo "</div>";
                }
            ;?>
        </div>
         <table   style="font-size:13px;" >
            <!-- OP: instruction for upload csv file for staff position  ragistration -->
            <tr><td align="left"><b>Note : The file extension should be in csv. The format of Staff Position list file is </b>
                </td>
            </tr>
            <tr><td align="left"><br/><b>Campus Code &nbsp;&nbsp; Authority(UCO)Code &nbsp;&nbsp;&nbsp;&nbsp; Department Code &nbsp;&nbsp; Scheme Code &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Group &nbsp;&nbsp;&nbsp; Designation Code &nbsp;&nbsp;&nbsp; Working Type </b><br/>
                       CU002&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DEAN-MVC &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; MVC-BTIC &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; UPSEM01 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; D &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 12 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Teaching <br/>
                        <b> Group Post &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Employee Type &nbsp;&nbsp;&nbsp;&nbsp; Plan/non Plan &nbsp;&nbsp;&nbsp;&nbsp; Pay Band &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Method of Recruitment &nbsp;&nbsp;&nbsp;&nbsp; Position Sanction Strength &nbsp;&nbsp;&nbsp;&nbsp; Present Strength </b><br/>  AssistantProfessor &nbsp;&nbsp;&nbsp;&nbsp; Temporary &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Plan&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PB1(15000-30000)1500&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;direct&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0
                </td>
            </tr>
            <?php echo form_open_multipart('upl/uploadspositionlist');?>
             <tr>
                    <td align="left">
                        <span id="lblError" style="color: red;font-size:15px;"></span><br/>
                        <div><label for="file" style="font-size:15px;"><b>Select File</b></label>
                        <input type='file'  id="fileUpload" name='userfile' size='20' accept=".csv, text/csv" />
		        <input type='submit' name="uploadsposition" id="btnUpload"  value='upload'/></div>
                    </td> 
                                 
                </tr>
            
            <?php echo form_close();?>
        </table>    
        <p> &nbsp; </p>
        <?php $this->load->view('template/footer'); ?>

    </body>
</html>

               

