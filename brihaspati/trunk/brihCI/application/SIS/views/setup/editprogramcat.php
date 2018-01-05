<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name editprogramcat.php 
  @author Raju Kamal(kamalraju8@gmail.com)
 -->

<html>
<title>Edit Program Category</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        
    </head>
    <body>
<script>
        function goBack() {
        window.history.back();
        }
    </script>

      <table>
            <tr colspan="2"><td>
                <div style="margin-left:30px;width:1000px;">
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                    ?>
                        <?php if(isset($_SESSION['err_message'])){?>
                          <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                        <?php
                    };
                ?>

                </div></br>
            </td></tr>
        </table>
       <table style="padding: 8px 8px 8px 30px;">
        <?php
            echo form_open('setup/editprogramcat/' . $prgcat_id);

            echo "<tr>";
                echo "<td>";
                    echo form_label('Category Program Name', 'procatname');
                echo "</td>";
                echo "<td>";
                    echo form_input($procatname);
                echo "</td>";
                echo "<td>";
                   echo "Example:Under Graduate,Post Graduate,Diploma  ";
                echo "</td>";
            echo "</tr>";

            //echo "<p>";
            echo "<tr>";
                echo "<td>";
                   echo form_label('Category Program Code', 'procatcode');
                    //echo "<br />";
                echo "</td>";
                 echo "<td>";
                 echo form_input($procatcode);
                echo "</td>";
                echo "<td>";
                 echo "Example:01,02,03,04 ";
                echo "</td>";
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('Category Program Short Name', 'catshrtname');
                //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($proshrtname);
                echo "</td>";
                echo "<td>";
                   echo " Example : UG,PG,R,etc ";
                     echo "</td>";
            echo "</tr>";

            echo "<tr>";
                echo "<td>";
                    echo form_label('Category Program Description', 'prodesc');
                echo "</td>";
                echo "<td>";
                    //echo "<br />";
                    echo form_input($prodesc);
                echo "</td>";
                echo "<td>";

                echo "</td>";
            echo "</tr>";
            //echo "</p>";

            // echo "<p>";

            echo "<tr>";
                echo "<td></td>";
                echo "<td>";
                    echo form_hidden('prgcat_id', $prgcat_id);
                    echo form_submit('submit', 'Update');
              //   echo anchor('setup/displaycategory', 'Back', array('class' => 'top_parent'));
               // echo "</p>";
            echo form_close();
            echo "<button onclick=\"goBack()\" >Back</button>";
            echo "</td>";
            echo "</tr>";

       ?>

        </table>
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>


