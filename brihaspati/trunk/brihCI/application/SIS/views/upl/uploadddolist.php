<!DOCTYPE html>
<html>
    <head>
        <title>Welcome </title>
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
                    echo "<b>Upload DDO List</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\" style=\"font-size:16px\">";
                    $help_uri = site_url()."/help/helpdoc#UploadDdoList";
                    echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";

                ;?>
            </tr>
        </table>
	<div>
	<?php
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
            <tr><td align="left" colspan=6><b>Note : The file extension should be in csv. The format of Designation list file is </b>
                </td>
            </tr>
            <tr><td><b> Campus Code</b> </td><td> <b>Department Code</b> </td><td><b> Scheme Code </b></td><td> <b>DDO Code</b> </td><td> <b>DDO Name</b> 
		</td>
		</tr>
		<tr><td> CU001 </td><td> MVC-ABT </td><td> 12304 </td><td> D123 </td><td>HODABTMVC  </td></tr>

		<?php echo form_open_multipart('upl/uploadddolist');?>
             <tr>
                    <td align="left" colspan="6">
                        <span id="lblError" style="color: red;font-size:15px;"></span><br/>
                        <div><label for="file" style="font-size:15px;"><b>Select File</b></label>
                        <input type='file'  id="fileUpload" name='userfile' size='20' accept=".csv, text/csv" />
                        <input type='submit' name="uploadddolist" id="btnUpload"  value='upload'/></div>
                    </td>

                </tr>

            <?php echo form_close();?>
        </table>
        <p> &nbsp; </p>
        <?php $this->load->view('template/footer'); ?>
    </body>
</html>
