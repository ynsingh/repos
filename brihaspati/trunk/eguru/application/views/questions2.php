<!DOCTYPE html>
<html>
    <head>
        <title>EGURU | HOME</title>
        <link href="<?php echo base_url(); ?>/static/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="<?php echo base_url(); ?>/static/css/login.css" rel="stylesheet" type="text/css">
        <script src="<?php echo base_url(); ?>/static/js/jquery-1.10.2.js"></script>
        <!--<script src="/static/js/bootstrap.responsive.min.js" ></script>-->
        <script src="<?php echo base_url(); ?>/static/js/bootstrap.min.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/jquery-1.10.2.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/jquery.validate.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/student/questions.js"></script>
    </head>
    <body>
        <?php
        if (!isset($_SESSION['email']))
            header("Location:/eguru/");
        if ($data['all_test_given'])
            header('Location :/student/profile_student');
        include 'header_login.php';
        ?>
        <form class="well-large" id="questions2" style="margin-left: 120px" name = "form2" method="post" action="/student/test2/">  
            <td align="left" valign="top"><div class="control-group"><p>1.Once I understand:<br/>
                    <div class="control-group">
                        <input type="radio" name="preferred_color1" value=1 />
                        (a) all the parts, I understand the whole thing. <br />
                        <input type="radio" name="preferred_color1" value=2 />
                        (b) the whole thing, I see how the parts fit. <br />
                    </div>
                    <p>&nbsp;</p>
                    <div class="control-group">  2. I find it easier:<br />
                        <input type="radio" name="preferred_color2" value=1 />
                        (a) to learn facts. <br />
                        <input type="radio" name="preferred_color2" value=2 />
                        (b) to learn concepts. <br />

                    </div>
                    <p>&nbsp;</p>
                    <div class="control-group"> <p>3. When I am reading for enjoyment, I like writers to:<br />
                            <input type="radio" name="preferred_color3" value=1 />
                            (a) clearly say what they mean. <br />
                            <input type="radio" name="preferred_color3" value=2 />
                            (b) say things in creative, interesting ways. <br />
                    </div>
                    </p>


                    <p>&nbsp;</p>
                    <div class="control-group"> <p>4. When I meet people at a party, I am more likely to remember: <br/>
                            <input type="radio" name="preferred_color4" value=1 />
                            (a) what they looked like.  <br />
                            <input type="radio" name="preferred_color4" value=2 />
                            (b) what they said about themselves.  <br />
                    </div>
                    </p>


                    <p>&nbsp;</p>
                    <div class="control-group"> <p>5. I prefer courses that emphasize:<br/>
                            <input type="radio" name="preferred_color5" value=1 />
                            (a) concrete material (facts, data). 
                            <br />
                            <input type="radio" name="preferred_color5" value=2 />
                            (b) abstract material (concepts, theories). 
                            <br />
                        </p>

                    </div>
                    <p>&nbsp;</p>
                    <div class="control-group"> <p>6. When considering a body of information, I am more likely to<br/>
                            <input type="radio" name="preferred_color6" value=1 />
                            (a) focus on details and miss the big picture.
                            <br />
                            <input type="radio" name="preferred_color6" value=2 />
                            (b) try to understand the big picture before getting into the details. 
                            <br />

                        </p>
                    </div>

                    <p>&nbsp;</p>
                    <div class="control-group"><p>7. Some teachers start their lectures with an outline of what they will cover. Such outlines are:<br/>
                            <input type="radio" name="preferred_color7" value=1 />
                            (a) somewhat helpful to me. <br />
                            <input type="radio" name="preferred_color7" value=2 />
                            (b) very helpful to me. <br />
                        </p>
                    </div>

                    <p>&nbsp;</p>

                    <br/>
                    </p>
                    </p>
                    <p><input name="submit" type="submit" value="Submit" />&nbsp;</p>
        </form>
