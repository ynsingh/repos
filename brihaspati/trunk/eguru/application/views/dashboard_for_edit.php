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
        <script src="<?php echo base_url(); ?>/static/js/admin/material_edit.js"></script>
    </head>
    <body>
        <?php
        include 'header_login.php';
        if (!isset($_SESSION['email']))
            header("Location:/eguru/");
        ?>

        <h3 style="margin-left:20px">Make changes in the material below </h3> 
    <center>
        <form id="material_edit"  method="POST" enctype="multipart/form-data" >     
            <table style="text-align:right">
                <tr>
                    <td class="control-group">
                        Type-<select name="type" id="type">
                            <option value="<?= $data_to_edit['type'] ?>"> <?= $data_to_edit['type'] ?></option>
                            <option value="descriptive">Descriptive</option>
                            <option value="ppt">PPT</option>
                            <option value="video">Video</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="control-group">
                        Hardness-<select name="hardness" id="hardness">
                            <option value="<?= $data_to_edit['hardness'] ?>"><?= $data_to_edit['hardness'] ?></option>
                            <option value="1">Level 1</option>
                            <option value="2">Level 2</option>
                            <option value="3">Level 3</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="control-group">
                        Topic- <input type="text" name="topic" id= "topic" value="<?= $data_to_edit['name'] ?>" />
                    </td>
                </tr>
                <?php if ($data_to_edit['link'] == "nothing") { ?>
                    <tr>
                        <td class="control-group">
                            Your given material -<a href="/uploads/<?= $data['subject_id'] ?>/<?= $data_to_edit['file_name'] ?>" target='_blank'>Link</a>
                        </td><td style="padding-left: 12px"><text style="color: red;text-decoration: underline;" href id ="upload_call">Upload new material</text></td></tr>
                    <tr><td id="upload"><input type="file" name="userfile" id="file_material">
                        </td>
                    </tr>
                <?php } else { ?>
                    <td  class="control-group">
                        Link <input type="text" name="link" value="<?= $data_to_edit['link'] ?>"> 
                    </td></tr><?php } ?>                
                <tr>
                    <td>
                        <input type="hidden" value="<?= $data_to_edit['id'] ?>" name="id">
                        <input type="submit" class="btn btn-primary" name="submit">
                    </td>
                </tr>
            </table>
        </form>
    </center>
    <?php include 'footer.php';?>
</body>
<script>
    var data_autocomplete = <?= json_encode($data_autocomplete); ?>
</script> 
