
<html>
<head>
<style>
/*.multiselect {
  width: 200px;
}

#checkboxes{width:90%;}
.selectBox {
  position: relative;
}



.selectBox select {
  width: 90%;
  font-weight: bold;
}

.overSelect {
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
}

#checkboxes {
  display: none;
  border: 1px black solid;
  background-color:white;
}

#checkboxes label {
  display: block;

}

#checkboxes label:hover {
 background-color: #1e90ff;
}*/
</style>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url('assets/css'); ?>/jquery.multiselect.css">
    	<script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-2.1.3.min.js" ></script>
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery.multiselect.js" ></script>
    	<script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.js" ></script>
	<style>
			
	</style>
</head>
<body>
<!---<div class="multiselect">
   			 	<div class="selectBox" onclick="showCheckboxes()">
      					<select style="height:37px;font-size:18px;font-weight:bold;">
        					<option>Select Reservation Type</option>
     					 </select>
     					 <div class="overSelect"></div>
   				 </div>
   				 <div id="checkboxes">
     				 	<label for="one">
        				<input type="checkbox" id="one" />Diffrently Abled</label>
      					<label for="two">
        				<input type="checkbox" id="two" />Supernumerary Seats</label>
      					<label for="three">
       					<input type="checkbox" id="three" />N.C.C. Cadets</label>
					<label for="three">
       					<input type="checkbox" id="three" />N.S.S. Volunteers</label>
					<label for="three">
       					<input type="checkbox" id="three" />Sports</label>
					<label for="three">
       					<input type="checkbox" id="three" />Wards / Dependants of Defence Personnel</label>
					<label for="three">
       					<input type="checkbox" id="three" />Kasmiri Migrants</label>
   			 	</div>
 		     </div>--->
   <!-- <select name="basic[]" multiple="multiple" class="3col active" >
        <option value="AL">Alabama</option>
        <option value="AK">Alaska</option>
        <option value="AZ">Arizona</option>
        
    </select>

    <script>
    $(function () {
        $('select[multiple].active.3col').multiselect({
            columns: 1,
            placeholder: 'Select States',
            search: true,
            searchOptions: {
                'default': 'Search States'
            },
            selectAll: true
        });

    });
</script>-->
</body>
</html>
