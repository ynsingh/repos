 <!DOCTYPE html>
       <div class="navbar" >
    <div class="navbar-inner">
        <div class="container-fluid" >
            <div class="row-fluid">                
                <div class="span10">
                  <h1>EGURU</h1>
                </div>    
                <div class="span5">
                    <a class="brand" href="<?php echo base_url(); ?>index.php/eguru/index">ELECTRONIC EGURU</a>
                </div>

                <div class="container-fluid">
                    <ul class="nav">
                        <li class="active"><a href="<?php echo base_url(); ?>index.php/eguru/index">HOME</a></li>
                        <li><a href="<?php echo base_url(); ?><?php echo base_url(); ?>index.php/eguru/about">ABOUT</a></li>
                        <li><a href="<?php echo base_url(); ?>index.php/eguru/contact">CONTACT</a></li>
                        <li><a href="<?php echo base_url(); ?>index.php/eguru/admin">ADMIN</a></li>
                    </ul>
                   <form action="/registration/stu_reg_form">
                   <button class="btn btn-success">
                        REGISTER
                    </button>
                        </form>
                    <div id="signin" class="modal hide fade in span6" style="display: none; ">
                        <div class="modal-header">
                            <a class="close" data-dismiss="modal">x</a>
                            <h3>Sign-In</h3>
                        </div>
                        <form method="post" id="user_signin" action="/eguru/user_login" novalidate="novalidate">
                            <div class="modal-body span8">
                                <div class="control-group">
                                    <label>Username :</label>
                                    <div class="controls">
                                        <input type="text" name="username" class="span12 required" placeholder="UserName" required/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label>Password : </label>
                                    <div class="controls">
                                        <input type="password" name="password" class="span12 required" placeholder="Password" required/>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="submit" name="signin" value="Sign In" class="btn btn-success"/> 
                                <a href="#" class="btn" data-dismiss="modal">Close</a>
                            </div>
                        </form>
                    </div>
                    <p><a data-toggle="modal" href="#signin" id="login" class="btn btn-info adjust right"><i class="icon-user icon-white"></i> Sign-In</a></p>
                </div>
            </div>
        </div>
    </div>
</div>


