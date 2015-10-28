<?php
if($data=='student')
    header("Location :/student/profile_student/");
else if($data=='sub_hod')
    header("Location :/admin/profile_subject_hod/");
else
    header("Location :/admin/profile_department_hod/");
?>
